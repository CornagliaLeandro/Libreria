package com.libreria.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreria.app.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
	
	@Query("SELECT e FROM Editorial e WHERE e.nombre_editorial = :nombre")
	public Editorial editorialPorNombre(@Param("nombre")String nombre_editorial);

}
