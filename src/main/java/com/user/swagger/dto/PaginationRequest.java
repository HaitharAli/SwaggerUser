package com.user.swagger.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaginationRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4789595261763329230L;
	private int pageNo;
	private int pageSize;

}
