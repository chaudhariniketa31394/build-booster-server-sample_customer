package com.buildbooster.customer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shippingAddress")
//@Component
@ToString
@AllArgsConstructor
@Data
public class ShippingAddress {

@Id
@GeneratedValue
@ApiModelProperty(notes = "id of the shipping address")
private Long id;

@NotNull
@ApiModelProperty(notes = "mailing address1 of the company")
private String mailingAddress1;

@NotNull
@ApiModelProperty(notes = "mailing address2 of the company")
private String mailingAddress2;

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
@ApiModelProperty(notes = "name of the company")
private String comapnyName;

@ManyToOne
@JoinColumn(name = "comanyId" )
private BusinessDetails BusinessDetails;


}
