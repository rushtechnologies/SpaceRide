<%-- 
    Document   : altas
    Created on : 23/03/2019, 03:17:30 PM
    Author     : Lenovo
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
                var id = li.id;
                document.getElementById("accion").value = id;
                document.getElementById("reporte").submit();
            }</script>
        <script>
            function llenartodos() {
                var responsable, descripcion, solucion;
                responsable = document.getElementById("Responsable").value;
                descripcion = document.getElementById("Descripcion").value;
                solucion = document.getElementById("Solucion").value;
                if (descripcion === "") {
                    alert("El campo de solucion es el unico que puede quedar vacio para su futura modificacion, favor de llenar la descripcion");
                } else {
                    if (solucion === "") {
                        document.getElementById("dato").value = 1;
                    } else
                        document.getElementById("dato").value = 2;
                    alert('Reporte generado');
                    document.getElementById("forma").submit();
                }

            }

        </script>
    </head>
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
    <body>

        <nav style="background-color: #50A6C3;" class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp" style="color:#FFFFFF;">SpaceRide</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="inicio.jsp"  style="color: #FFFFFF;">Inicio</a></li>
                    <li class="active"><a href="" onclick="window.location = 'altas.jsp'" style="background-color: #4DC8BB; color: #FFFFFF; ">Generar Reporte</a></li>
                    <li id="borrar" onclick="ventana(this)"><a style="color: #FFFFFF;">Borrar Reporte</a></li>
                    <li id="modificar" onclick="ventana(this)"><a style="color: #FFFFFF;">Modificar Reporte</a></li>
                    <li id="consultar" onclick="ventana(this)"><a style="color: #FFFFFF;">Consultar Reporte</a></li>
                </ul>
            </div>
        </nav>

        <div class="container text-center">
            <h3>Generar Reporte</h3>
            <br>
            <p>Genera un reporte de mantenimiento.</p>
            <br><br>
        </div>

        <div class="container">
            <div style="border-color: #F3BF1F;" class="panel panel-primary">
                <div style="background-color: #F3BF1F; border-color:#F3BF1F;" class="panel-heading">
                    <div style="background-color: #F3BF1F;"class="text-center">Reporte de mantenimiento</div>
                </div>
                <div class="panel-body">

                    <form id="forma" action="CtrlMantenimiento" method="post">



                        <div class="row">
                            <div class="col-md-6">
                                <label class="col-md-4 control-label">Responsable</label>
                                <div class="col-md-8">
                                    <%if(id.equals("1")){ out.print("<p>" + nom + "</p>" + "<input type='text'  style='visibility: hidden;' value='"+id+"' class='form-control' name='Responsable' id='Responsable'>");} else{if(id.equals("4")){%>
                                    <select class="form-control" id="Responsable" name="Responsable">
                                        <option value="1">Rafael</option>
                                        <option value="4">Carlos</option>
                                    </select>
                                    <br><br>
                                    <%}}%>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label class="col-md-4 control-label">Modulo</label>
                                <div class="col-md-8">
                                    <select class="form-control" name="modulo">
                                        <option value="Registo y login">Registro y login</option>
                                        <option value="Juego">Juego</option>
                                        <option value="FAQ's">FAQ's</option>
                                        <option value="Dificultad y Area">Dificultad y Area</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <input type="text" style="visibility: hidden" class="form-control" id="dato" name="dato">


                        <div class="row text-center">
                            <div class="col-md-6 text-center">
                                <label class="col-md-4 control-label">Descripci&oacute;n</label>

                            </div>
                            <div style="padding-left: 17.5%;">
                                <div class="col-md-8">
                                    <textarea  cols="40"  class="form-control" rows="6" id="Descripcion" name="Descripcion"></textarea>
                                </div>
                            </div>
                        </div>


                        <div class="row text-center">
                            <div class="col-md-6 text-center">
                                <label  class="col-md-4 control-label">Soluci&oacute;n</label>
                            </div>
                            <div style="padding-left: 17.5%;">
                                <div class="col-md-8">
                                    <textarea  class="form-control" cols="40" rows="6" id="Solucion" name="Solucion"></textarea>
                                </div>
                            </div>
                        </div>

                        <br><br><br><br>

                        <div class="row">
                            <div class="col-sm-offset-5 col-sm-2 text-center">
                                <input type="button" onclick="llenartodos()" value="Generar" style="background-color:#F5622F; border-color:#F5622F;"  id="boton" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <form id="reporte" action="reportes.jsp" method="post">
            <input type="text" id="accion" name="accion" style="visibility: hidden;">
            <input type="submit" value="ya we" id="report" style="visibility: hidden;">
        </form>
    </body>
</html>
