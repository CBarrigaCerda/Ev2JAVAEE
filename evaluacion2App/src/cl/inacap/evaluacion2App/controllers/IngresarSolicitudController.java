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
		
		//Metodo monje tibetano Sebastian Araya
		List<String> errores = new ArrayList<>();
		
		List<Solicitud> solicitudes = solicitudesDAO.getAll();
		
		//captura de variables e iniciacion de variables
		
		String rut = request.getParameter("rut-txt").trim();
		String nombre = request.getParameter("nombre-txt");
		String tipo = request.getParameter("tipo-txt");
		String numSolAux = request.getParameter("num-txt");
		AtomicInteger num = new AtomicInteger();
		int numSol = 0;
		
		//validacion rut
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
		
		//validacion nombre
		if (nombre.isEmpty()) {
			errores.add("Debe ingresar un nombre valido.");
		} else {
			if (!nombre.contains(" ")) {
				errores.add("Debe ingresar un nombre valido.");
			}
		}
		
		
		//validar numero de solicitud para retiro
		if (tipo.contains("Retiro")) {
			try {
				numSol = Integer.parseInt(numSolAux);
			} catch (Exception Ex) {
				errores.add("Debe ingresar un número de solicitud.");
			}
			
		}
		
		//comprobacion de numero de solicitud
		if (solicitudes.isEmpty()) {
			num.incrementAndGet();
		} else {
			Solicitud sol = solicitudes.get(solicitudes.size()-1);
			num.set(sol.getNumeroSolicitud().get());
			num.incrementAndGet();
		}
		
		//registrar solicitud
		if (errores.isEmpty()) {
			//agregar
			Cliente cliente = new Cliente();
			Solicitud solicitud = new Solicitud();
			cliente.setRut(rut);
			cliente.setNombre(nombre);
			solicitud.setTipo(tipo);
			solicitud.setCliente(cliente);
			solicitud.setNumSolicitudOriginal(numSol);
			solicitud.setNumeroSolicitud(num);
			solicitudesDAO.save(solicitud);
			response.sendRedirect("VerSolicitudController.do");
		} else {
			//mostrar errores
			request.setAttribute("errores", errores);
			doGet(request, response);
		}
		
		
	}
}
