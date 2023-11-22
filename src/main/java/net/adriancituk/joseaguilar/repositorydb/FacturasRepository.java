package net.adriancituk.joseaguilar.repositorydb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.adriancituk.joseaguilar.model.Factura;





public interface FacturasRepository extends JpaRepository<Factura, Integer> {

}
