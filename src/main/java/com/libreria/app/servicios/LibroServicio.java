package com.libreria.app.servicios;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.app.entidades.Autor;
import com.libreria.app.entidades.Editorial;
import com.libreria.app.entidades.Libro;
import com.libreria.app.repositorios.LibroRepositorio;

@Service
public class LibroServicio {
	
	@Autowired
	private LibroRepositorio lr;
	
	@Autowired
	private AutorServicio as;
	
	@Autowired
	private EditorialServicio es;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejPrestados, String nombre_autor, String nombre_editorial )throws Exception {
		
		validar(isbn, titulo, anio, ejemplares, ejPrestados, nombre_autor, nombre_editorial);
		
		Libro l = new Libro();
		
		l.setIsbn(isbn);
		l.setTitulo(titulo);
		l.setAnio(anio);
		l.setEjemplares(ejemplares);
		l.setEjemplaresPrestados(ejPrestados);
		l.setEjemplaresRestantes(ejemplares - ejPrestados);
		l.setAlta(true);
		l.setAutor(as.crearAutor(nombre_autor));
		l.setEditorial(es.crearEditorial(nombre_editorial));
		return lr.save(l);
	}
	
	@Transactional(readOnly = true)
	public List<Libro> listarLibro(){
		
		return lr.findAll();
	}
	
	
	public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejPrestados, String nombre_autor, String nombre_editorial)throws Exception {
		
		
		if (isbn == null) {
			throw new Exception("Isbninvalido");
		}
		
		if (titulo == null || titulo.isEmpty() || titulo.contains("  ")) {
			throw new Exception("Titulo invalido");
		}
		
		if (anio == null) {
			throw new Exception("Anio invalido");
		}
		
		if (ejemplares == null) {
			throw new Exception("Ejemplares invalido");
		}
		
		if (ejPrestados == null) {
			throw new Exception("Ejemplares Prestados invalido");
		}
		if (nombre_autor == null || nombre_autor.isEmpty() || nombre_autor.contains("  ")) {
			throw new Exception("Autor invalido");
		}
		
		if (nombre_editorial == null || nombre_editorial.isEmpty() || nombre_editorial.contains("  ")) {
			throw new Exception("Editorial invalido");
		}
		
		
	}
	
	//dar de baja
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro baja(String id) {
		
		Libro l = lr.getById(id);
		l.setAlta(false);
		return lr.save(l);
		
	}
	
	//dar de alta
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro alta(String id) {
		
		Libro l = lr.getById(id);
		l.setAlta(true);
		return lr.save(l);
		
	}
	
	//modificar
	
	
	public Libro modificar(String id, Long isbn, String titulo, Integer anio, Integer ej, Integer ejp, String nombre_autor, String editorial_nombre) {
		
		Autor a = as.verificarPorNombre(nombre_autor);
		Editorial e = es.verifyByName(editorial_nombre);
		Libro l = lr.getById(id);
		l.setIsbn(isbn);
		l.setTitulo(titulo);
		l.setAnio(anio);
		l.setEjemplares(ej);
		l.setEjemplaresPrestados(ejp);
		l.setEjemplaresRestantes(l.getEjemplares() - l.getEjemplaresPrestados());
		l.setAutor(a);
		l.setEditorial(e);
		return lr.save(l);
		
	}
	
	//buscar por id
	
	public Libro buscarPorId(String id) {
		Libro l = lr.getById(id);
		return l;
	}
	
	// elminar
	public void eliminar(String id) {
		
		lr.deleteById(id);
		
	}

}
