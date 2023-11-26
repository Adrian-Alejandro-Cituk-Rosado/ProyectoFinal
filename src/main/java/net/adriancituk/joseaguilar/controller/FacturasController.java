package net.adriancituk.joseaguilar.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import net.adriancituk.joseaguilar.model.DetalleFactura;
import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.model.Producto;
import net.adriancituk.joseaguilar.service.IClientesService;
import net.adriancituk.joseaguilar.service.IFacturasService;
import net.adriancituk.joseaguilar.service.IProductoService;




@Controller
@RequestMapping("/facturas")
public class FacturasController {
	@Autowired
	private IFacturasService serviceFacturas;
	@Autowired
	private IClientesService serviceClientes;
	@Autowired
	private IProductoService serviceProductos;
	
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


	    Cliente clienteSeleccionado = serviceClientes.buscarPorId(idCliente);


	    if (clienteSeleccionado != null) {
	        factura.setCliente(clienteSeleccionado);
	    }


	    List<Producto> productos = serviceProductos.buscarTodos();
	    model.addAttribute("productos", productos);

	    model.addAttribute("factura", factura);
	    model.addAttribute("cliente", clienteSeleccionado); 

	    return "facturas/facturasUsuario";
	}

	@PostMapping("/save")
	public String guardarFactura(@ModelAttribute("factura") Factura factura, BindingResult result, RedirectAttributes attributes) {
		

	    Cliente cliente = serviceClientes.buscarPorId(factura.getCliente().getId());

	    if (cliente != null) {
	        factura.setCliente(cliente);
	        if (factura.getDetalles() != null) {

	            for (DetalleFactura detalle : factura.getDetalles()) {
	                detalle.setFactura(factura);
	                System.out.println("Detalle: " + detalle.toString());
	            }
	        }


	        serviceFacturas.guardar(factura);


	        attributes.addFlashAttribute("msg", "Factura Guardada");
	        return "redirect:/facturas/detalleFactura/" + factura.getId();

	    } else {
	        System.out.print("error");
	        return "redirect:/facturas/facturasUsuario";
	    }
	}

    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
    	// Ejercicio.
    	System.out.println("Borrando usuario con id: "+idUsuario);
		serviceFacturas.eliminar(idUsuario);
		attributes.addFlashAttribute("msg","La factura fue eliminada!");
    	
		return "redirect:/facturas/facturasUsuario";
	}
    @GetMapping("/search")
    public String buscar(@ModelAttribute("search")  Producto vacante,Model model) {
    	System.out.println("Buscando por: "+vacante);
    	ExampleMatcher matcher= ExampleMatcher.matching().withMatcher("nombreProducto", ExampleMatcher.GenericPropertyMatchers.contains());
    	Example<Producto>example=Example.of(vacante,matcher);
    	List<Producto>lista=serviceProductos.buscarByExample(example);
    	model.addAttribute("productos", lista);
    	
    	return "facturas/facturasUsuario";
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
	 @GetMapping("/detalleFactura/{id}")
	    public String mostrarDetalleFactura(@PathVariable("id") int idFactura, Model model) {
	        Factura factura = serviceFacturas.buscarPorId(idFactura);

	        if (factura != null) {
	            model.addAttribute("factura", factura);
	            return "facturas/detalleFactura";
	        } else {

	            return "redirect:/facturas/facturasUsuario";
	        }
	    }
}
