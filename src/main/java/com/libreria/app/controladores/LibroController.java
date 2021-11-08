package com.libreria.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.app.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
	LibroServicio ls;

	@GetMapping("/guardar")
	public String guardarLibro() {
		
		return "guardar_libro";
	}
	
	
	
	
	@SuppressWarnings("finally")
	@PostMapping("/guardar")
	public String guardarAutor(@RequestParam("isbn") Long isbn, @RequestParam("titulo") String titulo, @RequestParam("anio") Integer anio, @RequestParam("ej") Integer ej, @RequestParam("ejp") Integer ejp, @RequestParam("autor") String nombre_autor, @RequestParam("editorial") String nombre_editorial) throws Exception{
		
		try {
			ls.crearLibro(isbn, titulo, anio, ej, ejp, nombre_autor, nombre_editorial);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return ("redirect:/libro");
		}
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
		
		try {
			ls.baja(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/libro";
		}
		
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
		try {
			ls.alta(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/libro";
		}
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap modelo) {
		
		try {
			modelo.addAttribute("libro", ls.buscarPorId(id));
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "/libro_modificar";
		}
		
		
		
		
		
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/modificar/{id}")
	public String modificarAutor(@RequestParam("id") String id,@RequestParam("isbn") Long isbn, @RequestParam("titulo") String titulo, @RequestParam("anio") Integer anio, @RequestParam("ej") Integer ej, @RequestParam("ejp") Integer ejp,@RequestParam("autor") String nombre_autor, @RequestParam("editorial") String editorial_nombre) {
		
		try {
			ls.modificar(id, isbn, titulo, anio, ej, ejp, nombre_autor, editorial_nombre);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/libro";
		}
		
		
	}

	@SuppressWarnings("finally")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id)  {
		
		try {
			ls.eliminar(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/libro";
		}
		
		
		
	}
	
	
}
