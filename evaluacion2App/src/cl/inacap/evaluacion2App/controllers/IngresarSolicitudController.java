package cl.inacap.evaluacion2App.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.inacap.evaluacion2Model.dao.ClientesDAOLocal;
import cl.inacap.evaluacion2Model.dao.SolicitudesDAOLocal;
import cl.inacap.evaluacion2Model.dto.Cliente;
import cl.inacap.evaluacion2Model.dto.Solicitud;

@WebServlet("/IngresarSolicitudController.do")
public class IngresarSolicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudesDAOLocal solicitudesDAO;
	
    public IngresarSolicitudController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/vistas/IngresarSolicitud.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> errores = new ArrayList<String>();
		AtomicInteger numSolicitud = new AtomicInteger();
		String rut = request.getParameter("rut-txt").trim();
		if (rut.isEmpty()) {
			errores.add("Ingrese un RUT.");
		} else {
			try {
				String numerosRut = rut.substring(0, rut.length()-2),
						rutInvertido, auxRut, 
						digitoVerificador = rut.substring(rut.length()-1), auxVerificador;
				StringBuilder st = new StringBuilder (numerosRut);
				rutInvertido = st.reverse().toString();
				int multiplicador = 2, sumatoria = 0;

				for (int i = 0; i < rutInvertido.length(); ++i) {
					if (multiplicador > 7) {
						multiplicador = 2;
					}
					auxRut = rutInvertido.substring(i, (i+1));
					sumatoria = sumatoria + Integer.parseInt(auxRut) * multiplicador;
					++multiplicador;
				}
				
				auxVerificador = Integer.toString(11 - (sumatoria % 11));
				
				if (!digitoVerificador.equals(auxVerificador)) {
					errores.add("El RUT ingresado no es valido.");
				}
			} catch (Exception Ex) {
				errores.add("El RUT ingresado no es validdo.");
			}
		}
		
		String nombre = request.getParameter("nombre-txt");
		if (nombre.isEmpty()) {
			errores.add("Debe ingresar un nombre valido.");
		} else {
			if (!nombre.contains(" ")) {
				errores.add("Debe ingresar un nombre valido.");
			}
		}
		
		String tipo = request.getParameter("tipo-txt");
		
		if (errores.isEmpty()) {
			//agregar
			Cliente cliente = new Cliente();
			Solicitud solicitud = new Solicitud();
			cliente.setRut(rut);
			cliente.setNombre(nombre);
			solicitud.setTipo(tipo);
			solicitud.setCliente(cliente);
			solicitud.setNumeroSolicitud(numSolicitud.incrementAndGet());
			solicitudesDAO.save(solicitud);
		} else {
			//mostrar errores
			request.setAttribute("errores", errores);
		}
		doGet(request, response);
	}
}
