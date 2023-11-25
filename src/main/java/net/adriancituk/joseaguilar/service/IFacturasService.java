package net.adriancituk.joseaguilar.service;

import java.util.List;

import net.adriancituk.joseaguilar.model.Factura;



public interface IFacturasService {
	
	void guardar(Factura factura);
	
	
	void eliminar(Integer id);
	
	
	List<Factura> buscarTodos();
	
	Factura buscarPorId(Integer id);
	
}
