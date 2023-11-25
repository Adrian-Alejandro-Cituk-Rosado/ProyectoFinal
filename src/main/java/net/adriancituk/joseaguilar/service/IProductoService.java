package net.adriancituk.joseaguilar.service;

import java.util.List;

import org.springframework.data.domain.Example;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.model.Producto;




public interface IProductoService {
	/** Ejercicio: Implementar método para registrar un usuario nuevo. 
	 * 	1. Usar la plantilla del archivo formRegistro.html
	 * 	2. El método para mostrar el formulario para registrar y el método para guardar el usuario deberá 
	 * 	   estar en el Controlador HomeController.
	 * 	3. Al guardar el usuario se le asignará el perfil USUARIO y la fecha de Registro
	 * 	   sera la fecha actual del sistema.
	 * @param usuario
	 */
	void guardar(Producto vacante);
	
	// Ejercicio: Método que elimina un usuario de la base de datos.
	void eliminar(Integer idProducto);
	
	// Ejercicio: Implementar método que recupera todos los usuarios. Usar vista de listUsuarios.html
	List<Producto> buscarTodos();
	Producto buscarPorId(Integer idProducto);

	List<Producto>buscarByExample(Example <Producto>example);
	
}
