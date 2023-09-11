<%-- 
    Document   : reporte
    Created on : 7/04/2019, 11:30:42 PM
    Author     : Lenovo

--%>

<%@page import="Implement.impMantenimiento"%>
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
                document.getElementById("change").submit();
            }
        </script>
        <script>
            function llenartodo() {
                var descripcion, solucion;

                descripcion = document.getElementById("Descripcion").value;
                solucion = document.getElementById("Solucion").value;
                if (descripcion === "") {
                    alert("El campo de solucion es el unico que puede quedar vacio para su futura modificacion, favor de llenar la descripción");
                } else {
                    if (solucion === "") {
                        document.getElementById("Solucion").value = "Por el momento no se ha encontrado solucion";
                    }
                    document.getElementById("form").submit();
                }

            }
        </script>
    </head>
    <body>

        <%
            HttpSession ses = request.getSession();
            String accion = request.getParameter("accion2");
            String id = "";
            String nom = "";
            if ((!accion.equals("borrar") && !accion.equals("consultar") && !accion.equals("modificar")) || accion == null || accion.equals("") || ses.getAttribute("idres") == null || ses.getAttribute("idres").equals("") || ses.getAttribute("nomres") == null || ses.getAttribute("nomres").equals("")) {
                out.println("<script>alert('No se ha iniciado sesion');</script>");
                out.println("<script>window.location='index.jsp';</script>");
            } else {
                id = ses.getAttribute("idres").toString();
                nom = ses.getAttribute("nomres").toString();

        %>

        <nav style="background-color: #50A6C3;" class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a style="color: #FFFFFF;" href="index.jsp" class="navbar-brand" onclick="window.location = 'index.jsp'">SpaceRide</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="inicio.jsp"  style=" color: #FFFFFF;">Inicio</a></li>
                    <li><a href="altas.jsp" onclick=" window.location = 'altas.jsp'" style="color: #FFFFFF; ">Generar Reporte</a></li>
                    <li id="borrar" onclick="ventana(this)"><a id="borrar2" style="color: #FFFFFF;">Borrar Reporte</a></li>
                    <li id="modificar" onclick="ventana(this)"><a  id="modificar2" style="color: #FFFFFF;">Modificar Reporte</a></li>
                    <li id="consultar" onclick="ventana(this)"><a id="consultar2" style="color: #FFFFFF;">Consultar Reporte</a></li>
                </ul>
            </div>
        </nav>

        <form id="change" action="reportes.jsp" method="post">
            <input type="text" id="accion" name="accion" style="visibility: hidden;">
            <input type="submit" value="ya we" id="change" style="visibility: hidden;">
        </form>

        <%  impMantenimiento imp2 = new impMantenimiento();
            String[][] todo = imp2.RequestTodo();
            int folio = Integer.parseInt(request.getParameter("boton"));
            impMantenimiento imp = new impMantenimiento();
            String[] datos = imp.RequestEspecifico(folio);
            boolean encontrado = false;
            String responsable = "Nulo";

            if (todo.length > 0) {
                for (int i = 0; i < (todo.length); ++i) {
                    if (folio == Integer.parseInt(todo[i][0])) {
                        encontrado = true;
                        break;
                    }
                }
            }

            if (encontrado) {
                String dia = datos[6].substring(3, 5);
                String mes = datos[6].substring(0, 2);
                String año = datos[6].substring(6, 10);
                String fecha = año + "-" + mes + "-" + dia;

                if (datos[5].equals("1")) {
                    responsable = "Rafael";
                }
                if (datos[5].equals("4")) {
                    responsable = "Carlos";
                }

        %>


        <div class="container">
            <div style="border-color: #F3BF1F;" class="panel panel-primary">
                <div style="background-color: #F3BF1F; border-color:#F3BF1F;" class="panel-heading">
                    <div style="background-color: #F3BF1F;"class="text-center">Reporte de mantenimiento</div>
                </div>
                <div class="panel-body">

                    <form id="form" action="actions.jsp" method="post">

                        <div class="row">
                            <div class="col-md-6">
                                <label class="col-md-4 control-label">Fecha</label>
                                <div class="col-md-8">
                                    <%out.println("<p>" + fecha + "</p>");%>

                                </div>
                            </div>

                            <div class="col-md-6">
                                <label  class="col-md-4 control-label">Folio</label>
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-3 col-md-8 col-lg-4">
                                            <%out.println("<p>" + datos[0] + "</p>");
                                                out.println("<input type='text' value='" + datos[0] + "' style='visibility: hidden;' name='valor'>");%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="col-md-4 control-label">Modulo</label>
                                <div class="col-md-8">
                                    <%out.println("<p>" + datos[1] + "</p>");%>

                                </div>
                            </div>

                            <div class="col-md-6">
                                <label  class="col-md-4 control-label">Responsable</label>
                                <div class="col-md-8">
                                    <div class="row">
                                        <div class="col-xs-4 col-sm-3 col-md-8 col-lg-4">
                                            <%out.println("<p>" + responsable + "</p>");
                                                out.println("<input type='text' value='" + datos[0] + "' style='visibility: hidden;' name='valor'>");%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="col-md-4 control-label">Estatus</label>
                                <div class="col-md-8">
                                    <%if(datos[3].equals("Desarrollado"))
                                    out.println("<input type='text' class='form-control' value='" + datos[3] + "' name='estatus2'>");
                                    else
                                    out.println("<input type='text' class='form-control' value='" + datos[3] + "' name='estatus2'>");
                                    %>
                                </div>
                            </div>
                        </div>



                        <div class="row text-center">
                            <div class="col-md-6 text-center">
                                <label class="col-md-4 control-label">Descripci&oacute;n</label>

                            </div>
                            <div style="padding-left: 17.5%;">
                                <div class="col-md-8">
                                    <%out.println("<textarea  cols='40' class='form-control' readonly='readonly' rows='6' id='Descripcion' name='Descripcion'>" + datos[2] + "</textarea>");%> 
                                </div>
                            </div>
                        </div>


                        <div class="row text-center">
                            <div class="col-md-6 text-center">
                                <label  class="col-md-4 control-label">Soluci&oacute;n</label>
                            </div>
                            <div style="padding-left: 17.5%;">
                                <div class="col-md-8">
                                    <%out.println("<textarea  class='form-control' readonly='readonly' cols='40' rows='6' id='Solucion' name='Solucion'>" + datos[4] + "</textarea>");
                                    out.println("<input type='text' id='accion' value='"+accion+"' name='accion3' style='visibility: hidden;'>");
                                    %>
                                </div>
                            </div>
                        </div>
                        <br><br><br>
                        <div class="row">
                            <div class="col-sm-offset-5 col-sm-2 text-center">
                                <input type="button" onclick="llenartodo()" style="background-color:#F5622F; border-color:#F5622F;"  id="boton" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                    <br><br>
                </div>
            </div>
        </div>
        <%
            } else {

                out.println("<script>alert('No existe el registro');</script>");
                out.println("<script>window.location='inicio.jsp';</script>");
            }

        %>

        <%                 if (accion.equals("borrar")) {
                    out.println("<script>document.getElementById('borrar').className ='active';</script>");
                    out.println("<script> document.getElementById('borrar2').style.backgroundColor='#4DC8BB';</script>");
                    out.println("<script>document.getElementById('boton').value='Borrar';</script>");
                } else if (accion.equals("modificar")) {
                    out.println("<script>document.getElementById('modificar').className ='active';</script>");
                    out.println("<script>document.getElementById('modificar2').style.backgroundColor = '#4DC8BB';</script>");
                    out.println("<script>document.getElementById('boton').value='Modificar';</script>");
                    out.println("<script>document.getElementById('Solucion').readOnly = false;</script>");
                    out.println("<script>document.getElementById('Descripcion').readOnly = false;</script>");
                } else if (accion.equals("consultar")) {
                    out.println("<script>document.getElementById('consultar').className ='active';</script>");
                    out.println("<script>document.getElementById('consultar2').style.backgroundColor = '#4DC8BB';</script>");
                    out.println("<script>document.getElementById('boton').style.display  = 'none';</script>");
                }
            }
        %>

    </body>
</html>
