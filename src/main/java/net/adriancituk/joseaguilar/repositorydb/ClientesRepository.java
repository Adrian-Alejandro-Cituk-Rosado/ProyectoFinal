package net.adriancituk.joseaguilar.repositorydb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.adriancituk.joseaguilar.model.Cliente;
import java.util.List;
import java.util.Optional;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
     List<Cliente> findByEmail(String email);
     Optional<Cliente> findById(Integer id);
     List<Cliente> findByNombreAndApellido(String nombre, String apellido);
     List<Cliente> findByNombre(String nombre);
     List<Cliente> findByNombreAndId(String nombre, Integer id);

}
