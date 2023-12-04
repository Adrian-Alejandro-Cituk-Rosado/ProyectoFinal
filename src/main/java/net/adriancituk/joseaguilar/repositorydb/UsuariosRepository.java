package net.adriancituk.joseaguilar.repositorydb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.adriancituk.joseaguilar.model.Usuario;







public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
