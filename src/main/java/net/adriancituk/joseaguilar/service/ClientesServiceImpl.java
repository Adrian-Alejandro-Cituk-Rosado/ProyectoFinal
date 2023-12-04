package net.adriancituk.joseaguilar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.repositorydb.ClientesRepository;


@Service
public class ClientesServiceImpl implements IClientesService {
	@Autowired
	private ClientesRepository clientesRepo;
	@Override
	public List<Cliente> buscarTodas() {
		// TODO Auto-generated method stub
		return clientesRepo.findAll();
	}

	@Override
	public Cliente buscarPorId(Integer idCliente) {
		Optional<Cliente> optional=clientesRepo.findById(idCliente);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Cliente cliente) {
		clientesRepo.save(cliente);
		
	}

	@Override
	public void eliminar(Integer idCliente) {
		clientesRepo.deleteById(idCliente);
		
	}

	@Override
	public List<Cliente> buscarByExample(Example<Cliente> example) {
		// TODO Auto-generated method stub
		return clientesRepo.findAll(example);
	}

	@Override
	public Page<Cliente> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return clientesRepo.findAll(page);
	}

}
