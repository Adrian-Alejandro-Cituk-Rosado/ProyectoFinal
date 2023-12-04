package net.adriancituk.joseaguilar.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.adriancituk.joseaguilar.model.Cliente;
import net.adriancituk.joseaguilar.model.Factura;
import net.adriancituk.joseaguilar.model.Perfil;
import net.adriancituk.joseaguilar.model.Usuario;
import net.adriancituk.joseaguilar.service.IClientesService;
import net.adriancituk.joseaguilar.service.IFacturasService;
import net.adriancituk.joseaguilar.service.IUsuariosService;
import net.adriancituk.joseaguilar.utill.Utileria;




@Controller
public class HomeController {
	@Value("${clientes.ruta.imagenes}")
	private String ruta;
	@Autowired
	IClientesService serviceClientes;
	@Autowired
	IFacturasService serviceFacturas;
	@Autowired
	IUsuariosService serviceUsuarios;
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Cliente>lista=serviceClientes.buscarTodas();
		model.addAttribute("clientes",lista);
	
		return"home";
	}
	@GetMapping("/create")
	public String crear(Cliente vacante,Model model) {
		
		return "/formCliente";
	}
	
	@PostMapping("/save")
	public String guardar(Cliente vacante,BindingResult result,RedirectAttributes attributes,
			@RequestParam("archivoImagen")MultipartFile multiPart) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
				
				}
			return "/formCliente";
			}
		if (!multiPart.isEmpty()) {
			//String ruta ="c:/empleos/img-vacantes/";
			String nombreImagen=Utileria.guardarArchivo(multiPart, ruta);
			if(nombreImagen != null) {
				vacante.setImagen(nombreImagen);
			}
		}
		serviceClientes.guardar(vacante);
		attributes.addFlashAttribute("msg","Registro Guardado");
		
		System.out.println(" Vacante: "+vacante);
		
		
		return "redirect:/";
		
	}
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante,RedirectAttributes attributes) {
		System.out.println("Borrando vacante con id: "+idVacante);
		serviceClientes.eliminar(idVacante);
		attributes.addFlashAttribute("msg","El usuario fue eliminado!");
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model) {
		Cliente vacante= serviceClientes.buscarPorId(idVacante);
		model.addAttribute("cliente", vacante);
	
		return "/formCliente";
	}
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable("id") int idCliente, Model model) {
	    Cliente cliente = serviceClientes.buscarPorId(idCliente);

	    if (cliente != null) {
	        List<Factura> facturasCliente = cliente.getFacturas();

	        model.addAttribute("cliente", cliente);
	        model.addAttribute("facturas", facturasCliente);

	        return "/facturas/listFacturas";
	    } else {
	        return "redirect:/error";
	    }
	}

	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("clientes",serviceClientes.buscarTodas());
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@GetMapping("/index")
	public String index(Authentication auth) {
		String username=auth.getName();
		System.out.println("Nombre del usuario: "+username);
		for(GrantedAuthority rol: auth.getAuthorities()) {
		System.out.println("Rol: "+rol.getAuthority());
		}
		return ("redirect:/");
	}
	
	@GetMapping("/signup")
	public String registrarse(Model model) {
	    model.addAttribute("usuario", new Usuario());
	    return "usuarios/formRegistro";
	}
	

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		usuario.setFechaRegistro(new Date());
	     usuario.setEstatus(1);
	     usuario.setPassword("{noop}" + usuario.getPassword());
	Perfil perfil =new Perfil();
	perfil.setId(1);
	
	usuario.agregar(perfil);
	
		// Ejercicio.
	serviceUsuarios.guardar(usuario);
	attributes.addFlashAttribute("msgType", "success");
	attributes.addFlashAttribute("msg", "Usuario Registrado");
		return "redirect:/";
	
	}

	
}
