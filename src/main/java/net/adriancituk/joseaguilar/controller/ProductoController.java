package net.adriancituk.joseaguilar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.model.Producto;
import net.adriancituk.joseaguilar.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService serviceProducto;
	
	public String mostrarIndex(Model model) {

		List<Producto> lista = serviceProducto.buscarTodos();
		model.addAttribute("productos", lista);
    	
    	return "productos/formProductos";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
    	System.out.println("Borrando producto con id: "+idUsuario);
    	serviceProducto.eliminar(idUsuario);
		attributes.addFlashAttribute("msg","El producto fue eliminado!");
    	
		return "redirect:/productos/formProductos";
	}
}
