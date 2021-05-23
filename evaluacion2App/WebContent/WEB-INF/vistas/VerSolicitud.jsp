<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<main class="container mt-4">
	<div class="columns is-centered">
		<div class="column is-3">
			<form method="POST" action="VerSolicitudController.do">
				<div class="card">
					<div class="card-header has-background-link">
						<span class=card-header-title>Filtro de solicitudes</span>
					</div>
					<div class="card-content">
						<div class="field">
							<label class="label" for="tipo-select">Tipo de solicitud</label>
							<div class="control">
								<div class="select">
									<select name="tipo-select>" id="tipo-select">
										<option>Todas</option>
										<option>Solicitud de Cédula de Identidad</option>
										<option>Retiro de Cédula de Identidad</option>
										<option>Solicitud de Certificado de Nacimiento</option>
										<option>Solicitud de Certificado de Defunción</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer has-text-centered">
						<div class="card-footer-item">
							<button type="submit" class="button">Filtrar</button>
						</div>	
					</div>
				</div>
			</form>
		</div>
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
							<td><a
								href="VerSolicitudController.do?solicitudEliminar=${solicitud.numeroSolicitud}"
								class="has-text-danger">Atender</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</main>
</body>
</html>