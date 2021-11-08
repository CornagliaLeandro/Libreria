package com.libreria.app.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.app.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
	
	@Autowired
	EditorialServicio es; 
	
	
	@GetMapping("/guardar")
	public String guardar() {
		
		return "guardar_editoriales";
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/guardar")
	public String guardarEditorial(@RequestParam("nombre") String nombre)  {
		
		try	{
			es.crearEditorial(nombre);
			
			
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/editorial";
		}
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
		
		try {
			es.darDeBaja(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/editorial";
		}
		
		
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
		
		try {
			es.alta(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/editorial";
		}
	}
	
	
	@SuppressWarnings("finally")
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap modelo) {
		
		try {
			modelo.addAttribute("editorial", es.buscarPorid(id));
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "/editorial_modificar";
		}
		
		
		
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/modificar/{id}")
	public String modificarEditorial(@RequestParam("id") String id,@RequestParam("nombre") String nombre) {
		
		try {
			es.modificar(id, nombre);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/editorial";
		}
		
		
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		
		try {
			es.eliminar(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			return "redirect:/editorial";
		}
		
		
	}
	

}
