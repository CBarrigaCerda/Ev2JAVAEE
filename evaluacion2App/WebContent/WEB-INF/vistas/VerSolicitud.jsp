<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<main class="container mt-4">
	<div class="columns is-centered">
		<div class="column is-8">
			<table class="table is-bordered is-fullwidth">
				<thead>
					<tr>
						<th>Nro de Atención</th>
						<th>Nombre de Cliente</th>
						<th>Tipo de Solicitud</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="solicitud" items="${solicitudes}">
						<tr>
							<td>${solicitud.numeroSolicitud}</td>
							<td>${solicitud.cliente.nombre}</td>
							<td>${solicitud.tipo}</td>
							<td>
								<a href="VerSolicitudController.do?" class="has-text-danger">Atender</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</main>
</body>
</html>