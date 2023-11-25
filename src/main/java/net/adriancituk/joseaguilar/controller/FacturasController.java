package net.adriancituk.joseaguilar.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	    model.addAttribute("cliente", clienteSeleccionado); // Añade el cliente al modelo

	    return "facturas/facturasUsuario";
	}

    @PostMapping("/save")
    public String guardarFactura(@ModelAttribute("factura") Factura factura, BindingResult result, RedirectAttributes attributes) {
    	 System.out.println("Factura recibida: " + factura.toString());
    	
    	// Antes de guardar la factura, establece el cliente correctamente
        Cliente cliente = serviceClientes.buscarPorId(factura.getCliente().getId());
        
        if (cliente != null) {
            factura.setCliente(cliente);
        } else {
            // Manejo de error si el cliente no se encuentra
            // Puedes agregar un mensaje de error o redirigir a una página de error
            return "redirect:/error";
        }

        // Ahora puedes guardar la factura
        serviceFacturas.guardar(factura);
        attributes.addFlashAttribute("msg", "Factura Guardada");

        return "redirect:/facturas/facturasUsuario";
    }
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
    	System.out.println("Borrando usuario con id: "+idUsuario);
		serviceFacturas.eliminar(idUsuario);
		attributes.addFlashAttribute("msg","La factura fue eliminada!");
    	
		return "redirect:/facturas/facturasUsuario";
	}
    @ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("facturas",serviceFacturas.buscarTodos());
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
