package net.adriancituk.joseaguilar.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import net.adriancituk.joseaguilar.service.IClientesService;
import net.adriancituk.joseaguilar.utill.Utileria;


@Controller
public class HomeController {
	@Value("${clientes.ruta.imagenes}")
	private String ruta;
	@Autowired
	IClientesService serviceClientes;
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
	public String ver(@PathVariable("id") int idVacante,Model model) {
		Cliente vacante= serviceClientes.buscarPorId(idVacante);
		model.addAttribute("cliente", vacante);
	
		return "/facturas/listFacturas";
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
}
