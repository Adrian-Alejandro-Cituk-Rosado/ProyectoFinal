package net.adriancituk.joseaguilar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.model.Producto;
import net.adriancituk.joseaguilar.repositorydb.FacturasRepository;
import net.adriancituk.joseaguilar.repositorydb.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	private ProductoRepository productoRepo;
	@Override
	public void guardar(Producto producto) {
		productoRepo.save(producto);
		
	}

	@Override
	public void eliminar(Integer idProducto) {
		productoRepo.deleteById(idProducto);
		
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return productoRepo.findAll();
	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		Optional<Producto> optional=productoRepo.findById(idProducto);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}