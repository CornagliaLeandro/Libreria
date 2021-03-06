package com.libreria.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreria.app.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

}
