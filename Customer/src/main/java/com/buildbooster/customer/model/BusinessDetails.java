package com.buildbooster.customer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="business_details")
//@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessDetails {

	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "id of the business")
	private Long id;	

	@NotNull
	@ApiModelProperty(notes = "type of the business")
	private String businessType;

	@NotNull
	@ApiModelProperty(notes = "name of the company")
	private String comapanyName;

	@NotNull
	@ApiModelProperty(notes = "mailingAddress1 of the company")
	private String mailingAddress1;

	@NotNull
	@ApiModelProperty(notes = "mailingAddress2 of the company")
	private String mailingAddress2;

	@NotNull
	@ApiModelProperty(notes = "city of the Company")
	private String city;

	@NotNull
	@ApiModelProperty(notes = "state of the Company")
	private String state;

	@NotNull
	@ApiModelProperty(notes = "zip code  of the Company")
	private String zipcode;

	@NotNull
	@ApiModelProperty(notes = "country of the Company")
	private String country;

	@NotNull
	@ApiModelProperty(notes = "phone number of the Company")
	private String phone;

	@NotNull
	@ApiModelProperty(notes = "fax number of the Company")
	private String fax;

	@NotNull
	@ApiModelProperty(notes = "email id of the Company")
	private String email;

	@NotNull
	@ApiModelProperty(notes = "shipping address of the Company")
	private String shippingAddress;

	@NotNull
	@ApiModelProperty(notes = "federal tax id of the Company")
	private String federalTaxId;

	@NotNull
	@ApiModelProperty(notes = "State tax id of the Company")
	private String stateTaxId;

	@NotNull
	@ApiModelProperty(notes = "first name of the admin")
	private String adminFirstName;

	@NotNull
	@ApiModelProperty(notes = "last name of the admin")
	private String adminLastName;

	@NotNull
	@ApiModelProperty(notes = "password of the admin")
	private String password;

	@NotNull
	@ApiModelProperty(notes = "role of the admin")
	private String userRole;

	@OneToMany(mappedBy="BusinessDetails")
	private List<Location> location ;

	@OneToMany(mappedBy="BusinessDetails")
	private List<UserDetails> user ;

	@OneToMany(mappedBy="BusinessDetails")
	private List<ShippingAddress> shippingAdd;

}
