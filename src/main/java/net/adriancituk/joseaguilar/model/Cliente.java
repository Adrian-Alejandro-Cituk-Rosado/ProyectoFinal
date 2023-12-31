package net.adriancituk.joseaguilar.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente {
	private String imagen="no-image.png";
	@Id
	@GeneratedValue(strategy=(GenerationType.IDENTITY))
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private Date createAt;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;
	public void reset() {
		this.imagen=null;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	@Override
	public String toString() {
		return "Cliente [imagen=" + imagen + ", id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", createAt=" + createAt + ", facturas=" + facturas + "]";
	}
	
	
	
	
}
