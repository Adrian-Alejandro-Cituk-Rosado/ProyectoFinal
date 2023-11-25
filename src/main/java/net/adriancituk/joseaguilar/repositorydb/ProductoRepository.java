package net.adriancituk.joseaguilar.repositorydb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.adriancituk.joseaguilar.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	 List<Producto> findByNombreProductoContaining(String nombre);
	
	
}
