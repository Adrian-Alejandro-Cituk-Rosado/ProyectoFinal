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
import net.adriancituk.joseaguilar.service.IFacturasService;



@Controller
@RequestMapping("/facturas")
public class FacturasController {
	@Autowired
	private IFacturasService serviceFacturas;
	
	public String mostrarIndex(Model model) {

		List<Factura> lista = serviceFacturas.buscarTodos();
		model.addAttribute("facturas", lista);
    	
    	return "facturas/facturasUsuario";
	}
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
    	System.out.println("Borrando usuario con id: "+idUsuario);
		serviceFacturas.eliminar(idUsuario);
		attributes.addFlashAttribute("msg","La factura fue eliminada!");
    	
		return "redirect:/facturas/facturasUsuario";
	}
}
