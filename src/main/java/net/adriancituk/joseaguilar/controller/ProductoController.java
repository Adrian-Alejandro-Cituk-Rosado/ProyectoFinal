package net.adriancituk.joseaguilar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.model.Producto;
import net.adriancituk.joseaguilar.service.IProductoService;
import net.adriancituk.joseaguilar.utill.Utileria;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService serviceProducto;
	@GetMapping("/index")
	public String mostrarIndex(Model model) {

		List<Producto> lista = serviceProducto.buscarTodos();
		model.addAttribute("productos", lista);
    	
    	return "productos/listProductos";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
    	System.out.println("Borrando producto con id: "+idUsuario);
    	serviceProducto.eliminar(idUsuario);
		attributes.addFlashAttribute("msg","El producto fue eliminado!");
    	
		return "redirect:/productos/listProductos";
	}
    @GetMapping("/create")
	public String crear(Producto productos,Model model) {
		
		return "/productos/formProductos";
	}
    @PostMapping("/save")
	public String guardar(Producto vacante,BindingResult result,RedirectAttributes attributes) {
		
		serviceProducto.guardar(vacante);
		attributes.addFlashAttribute("msg","Producto Guardado");
		
		System.out.println(" Producto: "+vacante);
		
		
		return "redirect:/productos/index";
		
	}
    @GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model) {
		Producto vacante= serviceProducto.buscarPorId(idVacante);
		model.addAttribute("producto", vacante);
	
		return "/productos/formProductos";
	}
}
