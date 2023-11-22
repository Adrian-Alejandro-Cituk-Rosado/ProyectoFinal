package net.adriancituk.joseaguilar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.repositorydb.FacturasRepository;

@Service
public class FacturasServiceImpl implements IFacturasService {
	@Autowired
	private FacturasRepository facturasRepo;
	@Override
	public void guardar(Factura factura) {
		facturasRepo.save(factura);
		
	}

	@Override
	public void eliminar(Integer id) {
		facturasRepo.deleteById(id);
		
	}

	@Override
	public List<Factura> buscarTodos() {
		return facturasRepo.findAll();
	}

}
