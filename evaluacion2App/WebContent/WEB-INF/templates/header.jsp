<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro Civil</title>
<link rel="stylesheet" href="vendor/bulma/css/bulma.min.css" />
</head>
<body>
	<header>
		<main>
			<nav class="navbar" role="navigation" aria-label="main navigation">
				<div class="navbar-brand">
					<a class="navbar-item"> </a> <a role="button" class="navbar-burger"
						aria-label="menu" aria-expanded="false"
						data-target="navbarBasicExample"> <span aria-hidden="true"></span>
						<span aria-hidden="true"></span> <span aria-hidden="true"></span>
					</a>
				</div>

				<div id="navbarBasicExample" class="navbar-menu">
					<div class="navbar-start">
						<a class="navbar-item" href="Index.jsp"> Home </a>
						<div class="navbar-item has-dropdown is-hoverable">
							<a class="navbar-link"> Acciones </a>

							<div class="navbar-dropdown">
								<a class="navbar-item" href="IngresarSolicitudController.do"> Ingresar Solicitud </a> <a
									class="navbar-item" href="#"> Atender Solicitud </a>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</main>
	</header>