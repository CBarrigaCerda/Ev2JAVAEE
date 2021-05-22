<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<c:if test="${errores != null}">
	<div class="columns is-centered mb-4">
		<div class="column is-6">
			<div class="notification is-warning">
				<p>${errores}</p>
			</div>
		</div>
	</div>
</c:if>
<main class="container mt-4">
	<div class="columns is-centered">
		<div class="column is-6">
			<form method="POST" action="IngresarSolicitudController.do">
				<div class="card">
					<div class="card-header has-background-primary">
						<span class="card-header-title">Ingreso de solicitud</span>
					</div>
					<div class="card-content">
						<div class="field">
							<label class="label" for="rut-txt">RUT</label>
							<div class="control">
								<input class="input" type="text" id="rut-txt" name="rut-txt" />
							</div>
						</div>
						<div class="field">
							<label class="label" for="nombre-txt">Nombre</label>
							<div class="control">
								<input class="input" type="text" id="nombre-txt"
									name="nombre-txt" />
							</div>
						</div>
						<div class="field">
							<label class="label" for="tipo-txt">Tipo de solicitud</label>
							<div class="control">
								<div class="select">
									<select name="tipo-txt" id="tipo-txt">
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
							<button type="submit" class="button is-primary">Registrar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</main>
</body>
</html>