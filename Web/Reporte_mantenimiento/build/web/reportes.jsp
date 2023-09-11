<%-- 
    Document   : reportes
    Created on : 5/04/2019, 08:46:47 PM
    Author     : Lenovo
--%>

<%@page import="Implement.impMantenimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script>function ventana(li) {
                var id = li.id;
                document.getElementById("accion").value = id;

                document.getElementById("change").submit();
            }
        </script>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession();
            String accion = request.getParameter("accion");
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


        <form id="change" onsubmit="alert('xdxd')" action="reportes.jsp" method="post">
            <input type="text" id="accion" name="accion" style="visibility: hidden;">
            <input type="submit" value="ya we" id="change" style="visibility: hidden;">
        </form>

        <%               impMantenimiento imp = new impMantenimiento();

            if (id.equals("1") || (id.equals("4") && accion.equals("borrar")) || (id.equals("4") && accion.equals("modificar"))) {
                try {
                    String[][] todo = imp.RequestDesarollador(Integer.parseInt(id));
                    if (todo == null) {
                        out.print("No hay registros");
                    } else {
                        out.println("<html>");

                        out.println("<form action='reporte.jsp'>");
                        if (todo.length > 0) {

                            out.println("<table class='table table-dark'>");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<td scope='col' class'text-center'>Folio</td>");
                            out.println("<td scope='col' class'text-center'>Modulo</td>");
                            out.println("<td scope='col' class'text-center'>Descripcion</td>");
                            out.println("<td scope='col' class'text-center'>Solucion</td>");
                            out.println("<td scope='col' class'text-center'>Status</td>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");

                            for (int i = 0; i < (todo.length); ++i) {
                                out.println("<tr>");
                                out.println("<td scope='row'>" + "<input style='background-color:#F5622F; border-color:#F5622F;' type='submit' value='" + todo[i][0] + "' name='boton'>" + "</td>");
                                out.println("<td scope='row'>" + todo[i][1] + "</td>");
                                out.println("<td scope='row'>" + todo[i][2] + "</td>");
                                out.println("<td scope='row'>" + todo[i][3] + "</td>");
                                out.println("<td scope='row'>" + todo[i][4] + "</td>");
                                out.println("</tr>");
                            }

                            out.println("</tbody>");
                            out.println("</table><input type='text' id='accion' value='" + accion + "' name='accion2' style='visibility: hidden;'></form></html>");
                        } else {
                            out.print("No hay registros");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error no mms" + e.toString());
                    out.println("<script>alert('No hay registros');</script>");
                    out.println("<script>window.location='inicio.jsp'</script>");
                    //response.sendRedirect("http://"+request.getLocalAddr()+":8080/Reporte_mantenimiento/inicio.jsp");
                }
            } else if (id.equals("4") && accion.equals("consultar")) {
                try {
                    String[][] todo = imp.RequestTodo();
                    if (todo == null) {
                        out.print("No hay registros");
                    } else {
                        out.println("<html>");

                        out.println("<form action='reporte.jsp'>");
                        if (todo.length > 0) {

                            out.println("<table class='table table-dark'>");
                            out.println("<thead>");
                            out.println("<tr>");
                            out.println("<td scope='col' class'text-center'>Folio</td>");
                            out.println("<td scope='col' class'text-center'>Modulo</td>");
                            out.println("<td scope='col' class'text-center'>Descripcion</td>");
                            out.println("<td scope='col' class'text-center'>Solucion</td>");
                            out.println("<td scope='col' class'text-center'>Responsable</td>");
                            out.println("<td scope='col' class'text-center'>Status</td>");
                            out.println("</tr>");
                            out.println("</thead>");
                            out.println("<tbody>");

                            for (int i = 0; i < (todo.length); ++i) {
                                out.println("<tr>");
                                out.println("<td scope='row'>" + "<input style='background-color:#F5622F; border-color:#F5622F;' type='submit' value='" + todo[i][0] + "' name='boton'>" + "</td>");
                                out.println("<td scope='row'>" + todo[i][1] + "</td>");
                                out.println("<td scope='row'>" + todo[i][2] + "</td>");
                                out.println("<td scope='row'>" + todo[i][4] + "</td>");
                                if (todo[i][5].equals("1")) {
                                    out.println("<td scope='row'>" + "Rafael" + "</td>");
                                } else if (todo[i][5].equals("4")) {
                                    out.println("<td scope='row'>" + "Carlos" + "</td>");
                                }
                                out.println("<td scope='row'>" + todo[i][3] + "</td>");
                                out.println("</tr>");
                            }

                            out.println("</tbody>");
                            out.println("</table><input type='text' id='accion' value='" + accion + "' name='accion2' style='visibility: hidden;'></form></html>");
                        } else {
                            out.print("No hay registros");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error no mms" + e.toString());
                    out.println("<script>alert('No hay registros');</script>");
                    out.println("<script>window.location='inicio.jsp'</script>");
                    //response.sendRedirect("http://"+request.getLocalAddr()+":8080/Reporte_mantenimiento/inicio.jsp");
                }
            }

        %>


        <div class="container text-center">
            <form id="form2" action="reporte.jsp">
                Busca por numero de folio:<input type="text" id="numfol" name="boton">
                <input type="submit" class="btn btn-primary" value="Buscar">
            </form>
        </div>




        <%                if (accion.equals("borrar")) {
                    out.println("<script>document.getElementById('borrar').className ='active';</script>");
                    out.println("<script>document.getElementById('borrar2').style.backgroundColor='#4DC8BB';</script>");
                } else if (accion.equals("modificar")) {
                    out.println("<script>document.getElementById('modificar').className ='active';</script>");
                    out.println("<script>document.getElementById('modificar2').style.backgroundColor = '#4DC8BB';</script>");
                } else if (accion.equals("consultar")) {
                    out.println("<script>document.getElementById('consultar').className ='active';</script>");
                    out.println("<script>document.getElementById('consultar2').style.backgroundColor = '#4DC8BB';</script>");
                }
            }
        %>

    </body>
</html>
