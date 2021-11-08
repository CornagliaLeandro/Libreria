package com.libreria.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.app.entidades.Autor;
import com.libreria.app.entidades.Editorial;
import com.libreria.app.entidades.Libro;
import com.libreria.app.servicios.AutorServicio;
import com.libreria.app.servicios.EditorialServicio;
import com.libreria.app.servicios.LibroServicio;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired 
	private AutorServicio as;
	
	@Autowired
	private EditorialServicio es;
	
	@Autowired
	private LibroServicio ls;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/autor")
	public String listaAutores(ModelMap modelo) {
		
		try {
			List<Autor> autores = as.listarAutores();
			modelo.addAttribute("autor", autores);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "lista_autores";
		}
		
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/editorial")
	public String listarEditoriales(ModelMap modelo) {
		
		try {
			List<Editorial> editoriales = es.listaEditoriales();
			modelo.addAttribute("editorial", editoriales);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "lista_Editoriales";
		}

	}
	
	@SuppressWarnings("finally")
	@GetMapping("/libro")
	public String listarLibros(ModelMap modelo) {
		
		try {
			List<Libro> libros = ls.listarLibro();
			modelo.addAttribute("libro", libros);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "lista_libros";
		}
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}
	
	@PostMapping("/registrar")
	public String registar(String nombre, String apellido, String email, String clave, String repetir) {
		System.out.print("Nombre: " + nombre);
		System.out.print("Apellido: " + apellido);
		System.out.print("Email: " + email);
		System.out.print("Clave: " + clave);
		System.out.print("Repetir: " + repetir);
	
		
		return "registro";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

}
