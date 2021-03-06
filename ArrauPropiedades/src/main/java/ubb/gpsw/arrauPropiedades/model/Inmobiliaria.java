package ubb.gpsw.arrauPropiedades.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the Inmobiliaria database table.
 * 
 */
@Entity
@NamedQuery(name="Inmobiliaria.findAll", query="SELECT i FROM Inmobiliaria i")
public class Inmobiliaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idInmobiliaria;

	private String calle;
	
//	private String ciudad;
    @NotNull
	private String comuna;

	private String correo;
	
	@NotNull
	private String nombre;

	private String numDepartamento;

	private String numero;

	private String region;

	private int telefono;
	
	@OneToMany(mappedBy="inmobiliaria", cascade = CascadeType.ALL)
	private List<Propiedad> propiedades;

	public Inmobiliaria() {
	}

	public int getIdInmobiliaria() {
		return this.idInmobiliaria;
	}

	public void setIdInmobiliaria(int idInmobiliaria) {
		this.idInmobiliaria = idInmobiliaria;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

//	public String getCiudad() {
//		return this.ciudad;
//	}
//
//	public void setCiudad(String ciudad) {
//		this.ciudad = ciudad;
//	}

	public String getComuna() {
		return this.comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumDepartamento() {
		return this.numDepartamento;
	}

	public void setNumDepartamento(String numDepartamento) {
		this.numDepartamento = numDepartamento;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}