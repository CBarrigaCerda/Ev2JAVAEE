package cl.inacap.evaluacion2Model.dto;

import java.util.concurrent.atomic.AtomicInteger;

public class Solicitud {

	private AtomicInteger numeroSolicitud;
	private int numSolicitudOriginal;
	private String tipo;
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public AtomicInteger getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setNumeroSolicitud(AtomicInteger numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setNumSolicitudOriginal(int numSolicitudOriginal) {
		this.numSolicitudOriginal = numSolicitudOriginal;
	}
	
	
}
