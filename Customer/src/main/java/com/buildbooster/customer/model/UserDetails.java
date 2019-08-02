package com.buildbooster.customer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="userDetails")
//@Component
@ToString
@AllArgsConstructor
@Data
public class UserDetails {
		

	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "id of the user")
	private Long id;

	@NotNull
	@ApiModelProperty(notes = "first name of the user")
	private String firstName;

	@NotNull
	@ApiModelProperty(notes = "last name of the user")
	private String lastName;

	@NotNull
	@Email
	@ApiModelProperty(notes = "email of the user")
	private String email;

	@NotNull
	@ApiModelProperty(notes = "password of the user")
	private String password;

	@NotNull
	@ApiModelProperty(notes = "phone number of the user")
	private String phone;
	
	@NotNull
	@ApiModelProperty(notes = "role of the user")
	private String role;

	@NotNull
	@ApiModelProperty(notes = "activation status of the user")
	private String ActivationStatus;

	@NotNull
	@ApiModelProperty(notes = "spending limit of the user")
	private String spendingLimit;

	@ManyToOne
	@JoinColumn(name = "companyId" )
	private BusinessDetails BusinessDetails;


}
