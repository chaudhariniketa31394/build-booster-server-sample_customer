package com.buildbooster.customer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "location")
//@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {

	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "id of the location")
	private Long id;

	@NotNull
	@ApiModelProperty(notes = "name of the company")
	private String comapnyName;

	@NotNull
	@ApiModelProperty(notes = "name of the location")
	private String locationName;

	@NotNull
	@ApiModelProperty(notes = "mailing address of the company")
	private String mailingAddress;

	@NotNull
	@ApiModelProperty(notes = "city of the company")
	private String city;

	@NotNull
	@ApiModelProperty(notes = "state of the company")
	private String state;

	@NotNull
	@ApiModelProperty(notes = "zip code of the company")
	private String zipcode;

	@NotNull
	@ApiModelProperty(notes = "country of the company")
	private String country;

	@ManyToOne
	@JoinColumn(name = "comapnyId" )
	private BusinessDetails BusinessDetails;

}
