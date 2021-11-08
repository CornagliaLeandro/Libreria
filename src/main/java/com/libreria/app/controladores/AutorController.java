package com.libreria.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreria.app.servicios.AutorServicio;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorServicio as;

	@GetMapping("/guardar")
	public String guardar() {

		return "guardar_autor";
	}

	@SuppressWarnings("finally")
	@PostMapping("/guardar")
	public String guardarAutor(@RequestParam("nombre") String nombre) {

		try {
			as.crearAutor(nombre);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return "guardar_autor";
		} finally {
			return ("redirect:/autor");
		}

	}

	@SuppressWarnings("finally")
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {

		try {
			as.baja(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return "redirect:/autor";
		}

	}

	@SuppressWarnings("finally")
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {

		try {
			as.alta(id);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return "redirect:/autor";
		}

	}

	@SuppressWarnings("finally")
	@GetMapping("/eliminar/{id}")
	public String eliminarAutor(@PathVariable String id) {
		try {
			if (id.isEmpty()) {

				throw new Exception("Error al eliminar Autor");

			} else {
				as.eliminarAutor(id);

			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return "redirect:/autor";
		}

	}

	@SuppressWarnings("finally")
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap modelo) {

		try {
			modelo.addAttribute("autor", as.buscarPorid(id));
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return "/autor_modificar";
		}

	}

	@SuppressWarnings("finally")
	@PostMapping("/modificar/{id}")
	public String modificarAutor(@RequestParam("id") String id, @RequestParam("nombre") String nombre) {

		try {
			as.modificar(id, nombre);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			return "redirect:/autor";
		}

	}

}
