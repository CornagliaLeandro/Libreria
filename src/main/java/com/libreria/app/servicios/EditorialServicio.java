package com.libreria.app.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.libreria.app.entidades.Editorial;
import com.libreria.app.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

	@Autowired
	EditorialRepositorio er;
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial crearEditorial(String nombre) throws Exception {

		if (nombre == null || nombre.isEmpty()) {
			throw new Exception("Error al crear editorial");
		} else {
			Editorial e = verifyByName(nombre);
			e.setNombre_editorial(nombre);
//			e.setAlta(true);
			return er.save(e);
		}

	}
	@Transactional(readOnly = true)
	public List<Editorial> listaEditoriales() {

		return er.findAll();
	}

	// dar de alta
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial alta(String id) {

		Editorial e = er.getById(id);
		e.setAlta(true);
		return er.save(e);

	}

	// dar de baja
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial darDeBaja(String id) {
		Editorial e = er.getById(id);

		e.setAlta(false);
		return er.save(e);

	}

	// eliminar
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) {

		er.deleteById(id);
	}
	
	//modificar
	
 	public Editorial modificar(String id, String nombre) {
 		
 		Editorial e = er.getById(id);
 		e.setNombre_editorial(nombre);
 		
 		return er.save(e);
 		
 	}
 	
 	//buscar por id
 		public Editorial buscarPorid(String id) {
 			Editorial e = er.findById(id).get();
 			return e;
 		}

	// verificar por nombre
	public Editorial verifyByName(String nombre_editorial) {
		Editorial e = er.editorialPorNombre(nombre_editorial);

		if (e == null) {
			e = er.save(new Editorial(nombre_editorial));
		}
		return e;

	}

}
