package net.adriancituk.joseaguilar.repositorydb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.adriancituk.joseaguilar.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
