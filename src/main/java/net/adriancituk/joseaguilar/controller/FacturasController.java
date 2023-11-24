package net.adriancituk.joseaguilar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.model.Producto;
import net.adriancituk.joseaguilar.service.IClientesService;
import net.adriancituk.joseaguilar.service.IFacturasService;



@Controller
@RequestMapping("/facturas")
public class FacturasController {
	@Autowired
	private IFacturasService serviceFacturas;
	@Autowired
	private IClientesService serviceClientes;
	
	public String mostrarIndex(Model model) {

		List<Factura> lista = serviceFacturas.buscarTodos();
		model.addAttribute("facturas", lista);
    	
    	return "facturas/facturasUsuario";
	}
    
	@GetMapping("/create/{id}")
	public String mostrarFormulario(@PathVariable("id") int idCliente, Model model) {
	    List<Cliente> clientes = serviceClientes.buscarTodas();
	    model.addAttribute("clientes", clientes);

	    Factura factura = new Factura();

	    // Busca el cliente por ID
	    Cliente clienteSeleccionado = serviceClientes.buscarPorId(idCliente);
	    
	    // Establece el cliente en la factura
	    if (clienteSeleccionado != null) {
	        factura.setCliente(clienteSeleccionado);
	    }

	    model.addAttribute("factura", factura);

	    return "facturas/facturasUsuario";
	}

    @PostMapping("/save")
    public String guardarFactura(@ModelAttribute("factura") Factura factura, BindingResult result, RedirectAttributes attributes) {
        // Tu lógica para guardar la factura
        serviceFacturas.guardar(factura);
        attributes.addFlashAttribute("msg", "Factura Guardada");

        return "redirect:/facturas/index";
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
