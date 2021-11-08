package com.libreria.app.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy="uuid")
	private String id_editorial;
	
	private String nombre_editorial;
	private Boolean alta = true;
	
	
	
	public Editorial() {
		
	}
	
	
	public Editorial(String nombre_editorial) {
		this.nombre_editorial = nombre_editorial;
	}


	public String getId_editorial() {
		return id_editorial;
	}
	public void setId_editorial(String id_editorial) {
		this.id_editorial = id_editorial;
	}
	public String getNombre_editorial() {
		return nombre_editorial;
	}
	public void setNombre_editorial(String nombre_editorial) {
		this.nombre_editorial = nombre_editorial;
	}
	public Boolean getAlta() {
		return alta;
	}
	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	@Override
	public String toString() {
		return nombre_editorial;
	}
	
	
	
}
