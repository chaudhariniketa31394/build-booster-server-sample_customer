package com.buildbooster.customer.serviceimpl;

import com.buildbooster.customer.model.*;
import com.buildbooster.customer.repository.IBusinessDetailsRepository;
import com.buildbooster.customer.repository.ILocationRepository;
import com.buildbooster.customer.repository.IShippingAddressRepository;
import com.buildbooster.customer.repository.IUserDetailsRepository;
import com.buildbooster.customer.service.IRegistrationService;
import com.buildbooster.customer.utils.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service
public class RegistrationServiceImpl implements IRegistrationService {
	
	@Autowired
	private IBusinessDetailsRepository businessuserRepository ;
	
	@Autowired
	public JavaMailSender sender;
	
	@Autowired
	private ILocationRepository locationRepository;
	
	@Autowired
	private IShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	private IUserDetailsRepository userDetailsRepository;


	@Autowired
	private ResponseBean responseBean;
	
	    private static final Random RANDOM = new SecureRandom();
	    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private static final int ITERATIONS = 10000;
	    private static final int KEY_LENGTH = 256;

	/**
	 *
	 * @param user - stores the business detail
	 * @return returns response bean dependeing on saved data
	 */

	public ResponseEntity<ResponseBean> saveUser(BusinessDetails user) {
	    	
	    	BusinessDetails businessDetails = null;
			//ResponseBean responseBean = new ResponseBean();
			try {
				String salt = UtilityClass.salt;
				String mySecurePassword = generateSecurePassword(user.getPassword(), salt);
		        System.out.println("My secure password = " + mySecurePassword);
				user.setPassword(mySecurePassword);		
				List<Location> locationlist = user.getLocation();
				 ListIterator<Location>
		         iterator = locationlist.listIterator(); 
				BusinessDetails user1 = businessuserRepository.save(user);
		    
		       while (iterator.hasNext()) { 
		        Location location = iterator.next();
		        location.setBusinessDetails(user1);
		   	    locationRepository.save(location);
		   	   System.out.println("businessuserId"+user1.getId());
		     } 	 
		     
		     List<UserDetails> userDetailslist = user.getUser();
		     ListIterator<UserDetails> useriterator = userDetailslist.listIterator();
		     while (useriterator.hasNext()) {
		    	 UserDetails userdetails = useriterator.next();
		    	 userdetails.setBusinessDetails(user1);
		    	 userDetailsRepository.save(userdetails);
		     }
		     List<ShippingAddress> shippingAddress = user.getShippingAdd();
				 ListIterator<ShippingAddress> additerator = shippingAddress.listIterator();
				 while (additerator.hasNext()) {
					 ShippingAddress shippingadd = additerator.next();
					 shippingadd.setBusinessDetails(user1);
					 shippingAddressRepository.save(shippingadd);
				 }
				
				UtilityClass.sendMessage(user,sender);
				responseBean.setPayload(businessDetails);
				responseBean.setMessage("Register successfully");
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			} catch (Exception e) {
				responseBean.setPayload(businessDetails);
				responseBean.setMessage("Registation failed");
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		
		 
		
	}

	@Override
	public ResponseEntity<ResponseBean> saveLocation(Location location, Long businessDetailId) {

		Optional<BusinessDetails> businessDetails = businessuserRepository.findById(businessDetailId);

		if(businessDetails.isPresent()) {


            BusinessDetails businessDetailInstance = businessDetails.get();

			Location location1 = new Location();
			location1.setCity(location.getCity());
			location1.setComapnyName(location.getComapnyName());
			location1.setCountry(location.getCountry());
			location1.setLocationName(location.getLocationName());
			location1.setMailingAddress(location.getMailingAddress());
			location1.setLocationName(location.getLocationName());
			location1.setState(location.getState());
			location1.setZipcode(location.getZipcode());
			location1.setBusinessDetails(businessDetailInstance);
			locationRepository.save(location);
			responseBean.setMessage("Location saved successfully");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		}
		else {
			responseBean.setMessage("Location cannot be saved since business detail id is not same");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<ResponseBean> saveBusiness(BusinessDetails businessDetail) {

		try {
		businessuserRepository.save(businessDetail);
		responseBean.setMessage("Business detail saved successfully");
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			responseBean.setMessage("Registation failed");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static byte[] hash(char[] password, byte[] salt) {
	        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
	        Arrays.fill(password, Character.MIN_VALUE);
	        try {
	            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	            return skf.generateSecret(spec).getEncoded();
	        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
	            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
	        } finally {
	            spec.clearPassword();
	        }
	    }
	 //ankit
	    public String getSalt(int length) {
	        StringBuilder returnValue = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }
	        return new String(returnValue);
	    }
	    public static String generateSecurePassword(String password, String salt) {
	        String returnValue = null;
	        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
	 
	        returnValue = Base64.getEncoder().encodeToString(securePassword);
	 
	        return returnValue;
	    }
}