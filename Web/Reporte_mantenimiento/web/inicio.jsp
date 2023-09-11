<%-- 
    Document   : inicio
    Created on : 5/04/2019, 11:33:07 AM
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reporte de mantenimiento</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script>function ventana(li) {
            var id=li.id;
            document.getElementById("accion").value = id;  document.getElementById("reporte").submit();
            }</script>
    </head>

    <body>
        <%
            HttpSession ses = request.getSession();
            String id = "";
            String nom = "";

            if (ses.getAttribute("idres") == null || ses.getAttribute("idres").equals("") || ses.getAttribute("nomres") == null || ses.getAttribute("nomres").equals("")) {
                out.println("<script>alert('No se ha iniciado sesion');</script>");
                out.println("<script>window.location='index.jsp';</script>");
            } else {
                id = ses.getAttribute("idres").toString();
                nom = ses.getAttribute("nomres").toString();
            }
        %>


        <nav style="background-color: #50A6C3;" class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a style="color: #FFFFFF;" href="index.jsp" class="navbar-brand" onclick="window.location = 'index.jsp'">SpaceRide</a>
                </div>
                <ul class="nav navbar-nav">
                    <li id="inicio" class="active"><a href="inicio.jsp"  style="background-color: #4DC8BB;color: #FFFFFF;">Inicio</a></li>
                    <li><a href="altas.jsp" onclick=" window.location = 'altas.jsp'" style="color: #FFFFFF; ">Generar Reporte</a></li>
                    <li id="borrar" onclick="ventana(this)"><a style="color: #FFFFFF;">Borrar Reporte</a></li>
                    <li id="modificar" onclick="ventana(this)"><a style="color: #FFFFFF;">Modificar Reporte</a></li>
                    <li id="consultar" onclick="ventana(this)"><a style="color: #FFFFFF;">Consultar Reporte</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h3>Inicio</h3>
            <p>Genera tu reporte de mantenimiento</p>
        </div>
        <form id="reporte" action="reportes.jsp" method="post">
        <input type="text" id="accion" name="accion" style="visibility: hidden;">
        <input type="submit" value="ya we" id="report" style="visibility: hidden;">
        </form>
    </body>
</html>
