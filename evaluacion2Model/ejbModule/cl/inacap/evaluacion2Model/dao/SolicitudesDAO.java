package cl.inacap.evaluacion2Model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import cl.inacap.evaluacion2Model.dto.Solicitud;

@Stateless
@LocalBean
public class SolicitudesDAO implements SolicitudesDAOLocal {

	private static List<Solicitud> solicitudes =
			new ArrayList<>();
	
    public SolicitudesDAO() {
        
    }

	@Override
	public void save(Solicitud solicitud) {
		solicitudes.add(solicitud);
	}

	@Override
	public List<Solicitud> getAll() {
		return solicitudes;
	}

	@Override
	public void delete(Solicitud solicitud) {
		solicitudes.remove(solicitud);		
	}

	@Override
	public List<Solicitud> filterByNumber(AtomicInteger numeroSolicitud) {
		return solicitudes.stream().filter(c->c.getNumeroSolicitud() == numeroSolicitud).collect(Collectors.
						toList());
	}
	
	public List<Solicitud> filterByName(String tipo){
		return solicitudes.stream().filter(c->c.getTipo().contains(tipo)).collect(Collectors.toList());
	}

}
