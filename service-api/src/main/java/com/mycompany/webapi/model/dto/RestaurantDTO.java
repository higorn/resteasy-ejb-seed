package com.mycompany.webapi.model.dto;

public class RestaurantDTO {

	private Integer id;
	private String name;

	public RestaurantDTO() {
	}

	public RestaurantDTO(final Integer id, final String nome) {
		this.id = id;
		this.name = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
