<%-- 
    Document   : index
    Created on : 5/04/2019, 12:21:25 PM
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Reporte de mantenimiento</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="css.css" type="text/css" rel="stylesheet">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body style="background-color: #4DC8BB;">

        <div class="wrapper fadeInDown">
            <div id="formContent">
                <form method="post">
                    <div style="text-align: center">
                        <br>
                        <h3 style="alignment-adjust: middle;">Ingresa como gerente o desarrollador</h3>
                        <br><br>
                        <p style="left: 45%;">Nombre del gerente o desarrollador:</p>
                        <input type="text"  class="fadeIn second" id="nom" name="nom" style="left: 47%;" maxlength="10">
                        <br><br>
                        <p style="left: 45%;">Contraseña: </p>
                        <input type="password"  class="fadeIn second" id="num" name="num" style="left: 47%;" maxlength="10">
                        <br><br>
                        <input type="submit"  class="fadeIn fourth" value="Entrar" name="entrar">
                    </div>
                </form>
            </div>
        </div>
        <%
            HttpSession ses = request.getSession(true);
            if (request.getParameter("entrar") != null) {

                String nomr = request.getParameter("nom").toString();
                String numr = request.getParameter("num").toString();
                if ((nomr.equals("Rafael") && numr.equals("more1015")) || (nomr.equals("Carlos") && numr.equals("1234"))) {
                    if (nomr.equals("Rafael")) {
                        ses.setAttribute("idres", "1");
                        ses.setAttribute("nomres", request.getParameter("nom"));
                    }
                    if (nomr.equals("Carlos")) {
                        ses.setAttribute("idres", "4");
                        ses.setAttribute("nomres", request.getParameter("nom"));
                    }
                    out.println("<script>window.location='inicio.jsp';</script>");
                } else {
                    out.print("<script>alert('Datos incorrectos')</script>");
                }
            } else {
                ses.invalidate();
            }


        %>
    </body>
</html>
