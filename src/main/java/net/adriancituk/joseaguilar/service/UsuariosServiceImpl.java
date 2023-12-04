package net.adriancituk.joseaguilar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.adriancituk.joseaguilar.model.Usuario;




@Service
public class UsuariosServiceImpl implements IUsuariosService{


	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}