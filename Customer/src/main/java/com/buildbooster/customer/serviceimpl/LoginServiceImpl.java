package com.buildbooster.customer.serviceimpl;


import com.buildbooster.customer.model.BusinessDetails;
import com.buildbooster.customer.model.ResponseBean;
import com.buildbooster.customer.repository.IBusinessDetailsRepository;
import com.buildbooster.customer.repository.ILoginRepository;
import com.buildbooster.customer.request.LoginRequest;
import com.buildbooster.customer.service.ILoginService;
import com.buildbooster.customer.utils.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private ResponseBean responseBean;
	
	@Autowired
	private IBusinessDetailsRepository iBusinessDetailsRepository;
	
	    private static final Random RANDOM = new SecureRandom();
	    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private static final int ITERATIONS = 10000;
	    private static final int KEY_LENGTH = 256;

	/**
	 *
	 * @param loginDto- stores login details
	 * @return- response bean with success as message
	 */

	@Override
	public ResponseEntity<ResponseBean> validate(LoginRequest loginDto) {

		//ResponseBean responseBean = new ResponseBean();
		try {
			BusinessDetails businessDetails = iBusinessDetailsRepository.getBidByEmail(loginDto.getEmail());
			String salt = UtilityClass.salt;
			boolean passwordMatch = verifyUserPassword(loginDto.getPassword(),
					businessDetails.getPassword(), salt);
			if (passwordMatch) {
				//responseBean.setPayload(businessDetails);
				responseBean.setMessage("Login successfully");
			} else {
				System.out.println("Provided password is incorrect");
				responseBean.setMessage("Login Failed Please verify user name and password!!");
			}
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			responseBean.setMessage("Login failed");
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *
	 * @param providedPassword - stores password provided by user
	 * @param securedPassword  - stores secured password
	 * @param salt - stores salt
	 * @return- boolean with respect to password verified
	 */
		
	   public  static boolean verifyUserPassword(String providedPassword,
	            String securedPassword, String salt)
	    {
	        boolean returnValue = false;
	        
	        // Generate New secure password with the same salt
	        String newSecurePassword = generateSecurePassword(providedPassword, salt);
	        
	        // Check if two passwords are equal
	        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
	       
	        return returnValue;
		

	    }

	/**
	 *
	 * @param password- stores provided password
	 * @param salt - stores salt
	 * @return- return encoded password
	 */
	public static String generateSecurePassword(String password, String salt) {
	        String returnValue = null;
	        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
	 
	        returnValue = Base64.getEncoder().encodeToString(securePassword);
	 
	        return returnValue;
	    }

	/**
	 *
	 * @param password -stores the password
	 * @param salt - stores the salt
	 * @return- return byte of hsh function
	 */
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

	/**
	 *
	 * @param length -stores length to create salt
	 * @return return salt
	 */
	//ankit
	    public String getSalt(int length) {
	        StringBuilder returnValue = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }
	        return new String(returnValue);
	    }
	  
}