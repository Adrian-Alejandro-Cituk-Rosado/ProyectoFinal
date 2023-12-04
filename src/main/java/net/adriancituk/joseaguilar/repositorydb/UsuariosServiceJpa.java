package net.adriancituk.joseaguilar.repositorydb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.adriancituk.joseaguilar.model.Usuario;
import net.adriancituk.joseaguilar.service.IUsuariosService;


@Service
@Primary
public class UsuariosServiceJpa implements IUsuariosService {
	 @Autowired
		private UsuariosRepository usuariosRepo;
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
		
	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);

		
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return usuariosRepo.findAll(page);
	}


	

}
