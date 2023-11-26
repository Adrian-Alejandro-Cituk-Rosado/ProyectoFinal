package net.adriancituk.joseaguilar.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Factura")
public class Factura {
	@Id
	@GeneratedValue(strategy=(GenerationType.IDENTITY))
	private Integer id;
	private String folio;
	private String descripcion;
	private String observacion;
	private Date fecha;
    
	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
	private List<DetalleFactura> detalles;

	public double calcularTotal() {
        double total = 0.0;
        if (detalles != null) {
            for (DetalleFactura detalle : detalles) {
                total += detalle.calcularSubtotal();
            }
        }
        return total;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<DetalleFactura> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}
	@Override
	public String toString() {
	    return "Factura [id=" + id + ", folio=" + folio + ", descripcion=" + descripcion + ", observacion="
	            + observacion + ", fecha=" + fecha + ", cliente=" +
	            (cliente != null ? cliente.getId() + " - " + cliente.getNombre() : null) + "]";
	}
	
}
