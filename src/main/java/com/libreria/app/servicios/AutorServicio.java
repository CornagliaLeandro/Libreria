package com.libreria.app.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

import com.libreria.app.entidades.Autor;
import com.libreria.app.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired
	private AutorRepositorio ar;

	// crear un autor
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor crearAutor(String nombre) throws Exception {

		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("Error");
		}

	

		Autor a = verificarPorNombre(nombre);
		a.setNombre_autor(nombre);
		
		return ar.save(a);

	}

	// listar los autores para mostrar
	@Transactional(readOnly = true)
	public List<Autor> listarAutores() {

		return ar.findAll();

	}

//	//modificar un autor
//	public void modificarAutor(String id, String nombre) throws Exception {
//		Optional <Autor> respuesta = ar.findById(id).get();
//		
//		if(respuesta.isPresent()) {
//			Autor a = respuesta.get();
//			a.setNombre_autor(nombre);
//			ar.save(a);
//		}else {
//			throw new Exception("Error modificarAutor");
//		}
//		
//	}

	// eliminar un autor ingresando un id como parametro
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminarAutor(String id) {
		ar.deleteById(id);
	}

	// dar de alta

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor alta(String id) {

		Autor entidad = ar.getById(id);

		entidad.setAlta(true);
		return ar.save(entidad);
	}

	// dar de baja
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor baja(String id) {

		Autor entidad = ar.getById(id);

		entidad.setAlta(false);
		return ar.save(entidad);
	}
	
	//buscar por id
	public Autor buscarPorid(String id) {
		Autor a = ar.findById(id).get();
		return a;
	}
	
	
	//modificar
	
	public Autor modificar(String id, String nombre) {
		
		Autor a = ar.getById(id);
		a.setNombre_autor(nombre);
		
		return ar.save(a);
		
	}

	public Autor verificarPorNombre(String nombre) {
		Autor a = ar.autorPorNombre(nombre);
		if (a == null) {
			a = ar.save(new Autor(nombre));
		}
		return a;
	}

}
