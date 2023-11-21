package net.adriancituk.joseaguilar.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.adriancituk.joseaguilar.model.Cliente;


public interface IClientesService {
	List<Cliente>buscarTodas();
	Cliente buscarPorId(Integer idCliente);
	void guardar(Cliente cliente);
	void eliminar(Integer idCliente);
	List<Cliente>buscarByExample(Example <Cliente>example);
	//Page<Cliente>buscarTodas(Pageable page);
}
