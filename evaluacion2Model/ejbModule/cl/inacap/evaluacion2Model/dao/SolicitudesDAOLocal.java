package cl.inacap.evaluacion2Model.dao;

import java.util.List;

import javax.ejb.Local;

import cl.inacap.evaluacion2Model.dto.Solicitud;

@Local
public interface SolicitudesDAOLocal {

	void save (Solicitud solicitud);
	List<Solicitud> getAll();
	void delete (Solicitud solicitud);
	List<Solicitud> filterByNumber (int numeroSolicitud);
}
