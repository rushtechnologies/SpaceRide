<%-- 
    Document   : actions
    Created on : 8/04/2019, 12:47:35 AM
    Author     : Lenovo
--%>

<%@page import="Implement.impMantenimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String accion = request.getParameter("accion3");
            impMantenimiento imp = new impMantenimiento();
            int folio = Integer.parseInt(request.getParameter("valor"));
            if (accion.equals("borrar")) {
                boolean borra = imp.Delete(folio);
                if (borra) {
                    out.println("<script>alert('Reporte eliminado');</script>");
                    out.println("<script>window.location='inicio.jsp'</script>");
                }

            }
            if ((accion.equals("modificar"))) {
                String estatus = request.getParameter("estatus2");
                String descripcion = request.getParameter("Descripcion");
                String solucion = request.getParameter("Solucion");
                if (solucion.equals("")) {
                    estatus = "En revision";
                } else {
                    if (!estatus.equals("Solucionado")) {
                        estatus = "Desarrollado";
                    }
                }
                boolean upd = imp.Update(descripcion, solucion, estatus, folio);

                if (upd) {
                    out.println("<script>alert('Reporte actualizado');</script>");
                    out.println("<script>window.location='inicio.jsp'</script>");
                }
            }
        %>
    </body>
</html>
