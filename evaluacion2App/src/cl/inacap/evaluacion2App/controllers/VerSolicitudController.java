package cl.inacap.evaluacion2App.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.evaluacion2Model.dao.SolicitudesDAOLocal;
import cl.inacap.evaluacion2Model.dto.Solicitud;

@WebServlet("/VerSolicitudController.do")
public class VerSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private SolicitudesDAOLocal solicitudesDAO;
	
    public VerSolicitudController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Iniciar variable AtomicInteger
		AtomicInteger numSolicitud = new AtomicInteger();
		if (request.getParameter("solicitudEliminar") != null) {
			String numSolicitudAux = request.getParameter("solicitudEliminar");
			//Ingresar variable en AtomicInteger
			numSolicitud.set(Integer.parseInt(numSolicitudAux));
			List<Solicitud> busqueda = solicitudesDAO.filterByNumber(numSolicitud);
			Solicitud solicitudAtendida = busqueda.isEmpty()? null:busqueda.get(0);
			if (solicitudAtendida != null) {
				solicitudesDAO.delete(solicitudAtendida);
			}
		}
	
		String tipo = request.getParameter("tipo-select");
		
		if (tipo != null) {
			if (!tipo.contains("Todas")) {
				List<Solicitud> solicitudes = solicitudesDAO.filterByName(tipo);
				request.setAttribute("solicitudes", solicitudes);
			}else {
				List<Solicitud> solicitudes = solicitudesDAO.getAll();
				
				request.setAttribute("solicitudes", solicitudes);
			}
		} else {
			List<Solicitud> solicitudes = solicitudesDAO.getAll();
			
			request.setAttribute("solicitudes", solicitudes);
		}

		request.getRequestDispatcher("WEB-INF/vistas/VerSolicitud.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
