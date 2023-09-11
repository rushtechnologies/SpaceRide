/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Implement.impMantenimiento;
import Interfaz.Mantenimiento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author salva
 */
@WebServlet(name = "CtrlMantenimiento", urlPatterns = {"/CtrlMantenimiento"})
public class CtrlMantenimiento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String urlAltas = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/altas.jsp";
            String urlConsultaTodo = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/reportes.jsp.jsp";
            String urlConsultaEspec = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/consultas.jsp";
            String urlCambios = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/reportes.jsp";
            String urlBajas = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/reportes.jsp";
            String index = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/inicio.jsp";
            String urlCheck = request.getHeader("Referer");
            String urlMenu = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/Reporte_mantenimiento/inicio.jsp";
            
            
            /*
            
            String urlAltas = "http://"+"localhost:8080"+"/Reporte_mantenimiento/altas.jsp";
            String urlConsultaTodo = "http://"+"localhost:8080"+"/Reporte_mantenimiento/reportes.jsp.jsp";
            String urlConsultaEspec = "http://"+"localhost:8080"+"/Reporte_mantenimiento/consultas.jsp";
            String urlCambios = "http://"+"localhost:8080"+"/Reporte_mantenimiento/reportes.jsp";
            String urlBajas = "http://"+"localhost:8080"+"/Reporte_mantenimiento/reportes.jsp";
            String index = "http://"+"localhost:8080"+"/Reporte_mantenimiento/inicio.jsp";
            String urlCheck = request.getHeader("Referer");
            String urlMenu = "http://"+"localhost:8080"+"/Reporte_mantenimiento/inicio.jsp";
            
            */

            String Descripcion, Solucion, Folio,Modulo;
            String Status= "En revision";
            int idres;
            Mantenimiento imp = new impMantenimiento();
            Boolean check;
            HttpSession mante = request.getSession();
            int folio, dato;
            if (urlCheck != null && !urlCheck.equals("")) {
                if (urlCheck.equals(urlAltas)) {
                    idres = Integer.parseInt(request.getParameter("Responsable"));
                    Descripcion = request.getParameter("Descripcion");
                    Modulo = request.getParameter("modulo");
                    dato = Integer.parseInt(request.getParameter("dato"));
                    if (dato == 1) {
                        Solucion = "Por el momento no hay solucion";
                        Status = "Asignado";
                    } else {
                        Solucion = request.getParameter("Solucion");
                         Status="Terminado";
                    }

                    if (Status != null && !Status.equals("") &&  Modulo != null && !Modulo.equals("") && Descripcion != null && !Descripcion.equals("")) {
                        check = imp.Altas(Modulo, Descripcion, Status, Solucion, idres);
                        if (check) {
                            response.sendRedirect(urlMenu);
                        } else {
                            response.sendRedirect(index);
                            System.out.println("Error en CtrlAltas");
                        }
                    } else {
                        response.sendRedirect(index);
                    }
                } else if (urlCheck.equals(urlConsultaTodo)) {
                    String[][] requestTodo = imp.RequestTodo();

                    out.println("</table></html>");
                    if (requestTodo[0][0] != null && !requestTodo[0][0].equals("Empty") && !requestTodo[0][0].equals("Error")) {
                        mante.setAttribute("RequestTodo", requestTodo);
                        response.sendRedirect(urlConsultaTodo);
                    } else if (requestTodo[0][0].equals("Empty")) {
                        mante.setAttribute("RequestTodo", "Empty");
                        response.sendRedirect(urlConsultaTodo);
                    } else if (requestTodo[0][0].equals("Error")) {
                        mante.setAttribute("RequestTodo", "Error");
                        response.sendRedirect(urlConsultaTodo);
                    }

                    /* out.println("<script>alert('No jala');</script>");
                     out.println("<html><table style='border: 2px solid black; border-spacing: 5px;'>");
                     out.println("<tr>");
                     out.println("<td>" + "Folio" + "</td>");
                     out.println("</tr>");
                     for (int i = 0; i < requestTodo[0].length; i++) {
                     System.out.println(requestTodo.length);
                     out.println("<tr>");
                     out.println("<td>" + requestTodo[i][0] + "</td>");
                     out.println("</tr>");
                     }*/
                } else if (urlCheck.contains(urlConsultaEspec)) {
                    Folio = request.getParameter("Folio");
                    if (Folio != null && !Folio.equals("")) {
                        folio = Integer.parseInt(Folio);
                        String[] requestEspec = imp.RequestEspecifico(folio);
                        if (requestEspec != null && !requestEspec[0].equals("Empty") && !requestEspec[0].equals("Error")) {
                            mante.setAttribute("RequestTodo", requestEspec);
                            response.sendRedirect(urlConsultaTodo);
                        } else if (requestEspec[0].equals("Empty")) {
                            mante.setAttribute("RequestTodo", "Empty");
                            response.sendRedirect(urlConsultaEspec);
                        } else if (requestEspec[0].equals("Error")) {
                            mante.setAttribute("RequestTodo", "Error");
                            response.sendRedirect(urlConsultaEspec);
                        }
                    } else {
                        response.sendRedirect(index);
                    }
                } else if (urlCheck.equals(urlCambios)) {
                    Descripcion = request.getParameter("Descripcion");
                    Solucion = request.getParameter("Solucion");
                    Folio = request.getParameter("Folio");
                    if (Descripcion != null && !Descripcion.equals("") && Solucion != null && !Solucion.equals("") && Folio != null && !Folio.equals("")) {
                        folio = Integer.parseInt(Folio);
                        check = imp.Update(Descripcion, Solucion,Status, folio);
                        if (check) {
                            response.sendRedirect(urlMenu);
                        } else {
                            System.out.println("Error en CtrlCambios");
                            response.sendRedirect(index);
                        }
                    } else {
                        response.sendRedirect(index);
                    }
                } else if (urlCheck.equals(urlBajas)) {
                    Folio = request.getParameter("Folio");
                    if (Folio != null && !Folio.equals("")) {
                        folio = Integer.parseInt(Folio);
                        check = imp.Delete(folio);
                        if (check) {
                            response.sendRedirect(urlMenu);
                        } else {
                            response.sendRedirect(index);
                            System.out.println("Error en CtrlBajas");
                        }
                    } else {
                        response.sendRedirect(index);
                    }
                } else if (urlCheck.equals(index)) {
                    String url = mante.getAttribute("url").toString();
                    if (url != null && !url.equals("")) {
                        if (url.equals("borrar")) {
                            String[][] todo = imp.RequestTodo();
                            int tamaño = todo.length;
                            String array = "";
                            for (int i = 0; i < tamaño; i++) {
                                array += todo[i][0] + "&";
                                array += todo[i][1] + "&";
                                array += todo[i][2] + "&";
                                array += todo[i][3] + "&";
                                array += todo[i][4] + "&";
                                array += todo[i][5] + "*";
                            }
                            mante.setAttribute("datos", array);
                            response.sendRedirect(urlBajas);
                        }
                    }
                } else {
                    response.sendRedirect(index);
                }
            } else {
                response.sendRedirect(index);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrlMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrlMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
