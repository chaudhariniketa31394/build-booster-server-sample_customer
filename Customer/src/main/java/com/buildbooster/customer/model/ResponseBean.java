package com.buildbooster.customer.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter @Setter
public class ResponseBean implements Serializable {	
private static final long serialVersionUID = 1L;	
	private transient Object payload;	
	private String message;

}
