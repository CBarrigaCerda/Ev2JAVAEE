package cl.inacap.evaluacion2Model.dto;

import java.util.List;

public class Cliente {

	private String nombre;
	private String rut;
	private List<Solicitud> solicitud;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}
