package com.rushtechnologies.spaceride.Controller;

import com.rushtechnologies.spaceride.DAO.iArea;
import com.rushtechnologies.spaceride.DAO.iChat;
import com.rushtechnologies.spaceride.DAO.iEventReport;
import com.rushtechnologies.spaceride.DAO.iFaq;
import com.rushtechnologies.spaceride.DAO.iMaintenanceReport;
import com.rushtechnologies.spaceride.DAO.iMuerto;
import com.rushtechnologies.spaceride.DAO.iPregunta;
import com.rushtechnologies.spaceride.DAO.iUsuario;
import com.rushtechnologies.spaceride.Encrypt.Encrypt;
import com.rushtechnologies.spaceride.ImpDAO.ImpArea;
import com.rushtechnologies.spaceride.ImpDAO.ImpChat;
import com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import com.rushtechnologies.spaceride.ImpDAO.ImpMuerto;
import com.rushtechnologies.spaceride.ImpDAO.ImpPregunta;
import com.rushtechnologies.spaceride.ImpDAO.ImpUsuario;
import com.rushtechnologies.spaceride.Mail.Mail;
import com.rushtechnologies.spaceride.Models.Area;
import com.rushtechnologies.spaceride.Models.EventReport;
import com.rushtechnologies.spaceride.Models.Faq;
import com.rushtechnologies.spaceride.Models.MaintenanceReport;
import com.rushtechnologies.spaceride.Models.Muerto;
import com.rushtechnologies.spaceride.Models.Pregunta;
import com.rushtechnologies.spaceride.Models.Usuario;
import java.io.BufferedReader;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * SpaceRide Servlet Controller for HTML web pages
 *
 * Data manipulation from de HTML forms to SQL database and security
 * validations for all the web pages.
 *
 * Todos los derechos reservados Rush Technologies S.A. de C.V. © 2019
 *
 * Java version 8.211
 * 
 * Java EE 7
 *
 * LICENSE: This source file is subject to version 3.01 of the PHP license
 * that is available through the world-wide-web at the following URI:
 * http://www.php.net/license/3_01.txt.  If you did not receive a copy of
 * the PHP License and are unable to obtain it through the web, please
 * send a note to license@php.net so we can mail you a copy immediately.
 *
 * @category   Servlet Controller
 * @package    com.rushtechnologies.spaceride.Controllers
 * @author     Carlos Huerta García <huerta2502@gmail.com>
 * @copyright  2019 © Rush Technologies S.A. de C.V.
 * @license    http://www.php.net/license/3_01.txt  PHP License 3.01
 */

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private final iUsuario user = new ImpUsuario();
    private final iMuerto muerto = new ImpMuerto();
    private final iPregunta question = new ImpPregunta();
    private final iArea area = new ImpArea();
    private final iFaq faq = new ImpFaq();
    private final iChat c = new ImpChat();
    private final iEventReport er = new ImpEventReport();
    private final iMaintenanceReport mr = new ImpMaintenanceReport();
    private final Encrypt encrypt = new Encrypt();
    private String referer = "", index = "", stats = "", fp = "", game = "", faqs = "", choose = "", listener = "", answer = "", chats = "";
    public static final String[] temaschoose = {"", "Art", "Ast", "Bio", "Ent", "Esp", "Fis", "Geo", "HMe", "HUn", "Ing", "Mat", "Qui", "Tec", "GKN"};
    public static final List<String> temachoose = new ArrayList<String>(Arrays.asList(temaschoose));
    public static final String[] temas = {"", "Arte", "Astronomía", "Biología", "Entretenimiento", "Español", "Física", "Geografía", "Historia de México", "Histora Universal", "Inglés", "Matemáticas", "Química", "Tecnología", "Conocimientos Generales"};

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        index = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/";
        stats = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/stats.jsp";
        fp = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/fp.jsp";
        game = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/game.html";
        faqs = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/faq.jsp";
        choose = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/choose.jsp";
        listener = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/listener.jsp";
        answer = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/answer.jsp";
        chats = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/chat.jsp";
        referer = request.getHeader("Referer");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='css/styles_index.css'/>");
            out.println("<link rel='stylesheet' href='https://code.getmdl.io/1.3.0/material.indigo-red.min.css'/>");
            out.println("<script src='js/spaceride.js' type = 'text/javascript'></script>");
            out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\" type=\"text/javascript\"></script>");
            out.println("<title>Servlet Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>com.rushtechnologies.spaceride Servlet Controller at " + request.getContextPath() + "</h1>");
            out.print("<h2>" + referer + "</h2>");
            out.print("<h3>" + request.getParameter("submitsr") + "</h3>");
            if (request.getParameter("submitsr") != null && referer != null && !referer.equals("") && referer.contains(index) && (request.getParameter("submitsr").equals("LogIn") || request.getParameter("submitsr").equals("SignIn") || request.getParameter("submitsr").equals("UpdateD") || request.getParameter("submitsr").equals("UpdateP") || request.getParameter("submitsr").equals("Createq") || request.getParameter("submitsr").equals("LogInGame") || temachoose.contains(request.getParameter("submitsr").substring(0, 3)) || request.getParameter("submitsr").equals("Comenzar") || request.getParameter("submitsr").equals("Responder") || request.getParameter("submitsr").contains("ResponderFaq") || request.getParameter("submitsr").contains("DescartarFaq") || request.getParameter("submitsr").equals("ChatLogin") || request.getParameter("submitsr").contains("Chat") || request.getParameter("submitsr").equals("EnviarChat") || request.getParameter("submitsr").contains("BorrarRE") || request.getParameter("submitsr").contains("Reportar") || request.getParameter("submitsr").contains("Createqr") || request.getParameter("submitsr").contains("Asignars") || request.getParameter("submitsr").contains("Resolver") || request.getParameter("submitsr").contains("Validare") || request.getParameter("submitsr").contains("Creamrer") || request.getParameter("submitsr").contains("Deletefq"))) {
                if (request.getParameter("submitsr").equals("LogIn") && referer.contains(index)) {
                    HttpSession sesion;
                    int empty = 0;
                    String nombre = "";
                    if (!request.getParameter("user").equals("") && request.getParameter("user") != null) {
                        nombre = ESAPI.encoder().encodeForHTML(request.getParameter("user"));
                    } else {
                        ++empty;
                    }
                    String contra = "";
                    if (!request.getParameter("pass").equals("") && request.getParameter("pass") != null && request.getParameter("pass").length() >= 8) {
                        contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass"));
                    } else {
                        ++empty;
                    }
                    if (empty > 0) {
                        out.println("<script>invalid()</script>");
                        response.sendRedirect(index);
                    } else {
                        int[] login;
                        login = user.loginUser(nombre, contra);
                        int id = login[0];
                        int caselogin = login[1];
                        switch (caselogin) {
                            case 0:
                                out.println("<script>failedLogin()</script>");
                                response.sendRedirect(index);
                                break;
                            case 1:
                                sesion = request.getSession(true);
                                sesion.setAttribute("id", id);
                                response.sendRedirect(stats);
                                break;
                            case 2:
                                sesion = request.getSession(true);
                                sesion.setAttribute("id", id);
                                response.sendRedirect(fp);
                                break;
                            default:
                                out.println("<script>failedLogin()</script>");
                                response.sendRedirect(index);
                                break;
                        }
                    }
                } else {
                    if (request.getParameter("submitsr").equals("SignIn") && referer.contains(index)) {
                        HttpSession sesion;
                        int empty = 0;
                        String nombre = "";
                        if (!request.getParameter("user").equals("") && request.getParameter("user") != null) {
                            nombre = ESAPI.encoder().encodeForHTML(request.getParameter("user"));
                        } else {
                            ++empty;
                        }
                        String contra = "";
                        if (!request.getParameter("pass").equals("") && request.getParameter("pass") != null && request.getParameter("pass2") != null && request.getParameter("pass").equals(request.getParameter("pass2"))) {
                            contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass"));
                        } else {
                            ++empty;
                        }
                        String correo = "";
                        if (!request.getParameter("mail").equals("") && request.getParameter("mail") != null) {
                            correo = ESAPI.encoder().encodeForHTML(request.getParameter("mail"));
                        } else {
                            ++empty;
                        }
                        boolean recaptcha = false;
                        if (!request.getParameter("g-recaptcha-response").equals("") && request.getParameter("g-recaptcha-response") != null) {
                            recaptcha = isCaptchaValid(request.getParameter("g-recaptcha-response"));
                        } else {
                            if (!request.getParameter("grec").equals("") && request.getParameter("grec") != null) {
                                recaptcha = isCaptchaValid(request.getParameter("grec"));
                            } else {
                                ++empty;
                            }
                        }
                        if (empty > 0 || !recaptcha) {
                            out.println("<script>invalid();</script>");
                            response.sendRedirect(index);
                        } else {
                            int admin = 0;
                            if (request.getParameter("mail").equals("chavaramos.ipn@gmail.com") || request.getParameter("mail").equals("brenbetto17190@hotmail.com") || request.getParameter("mail").equals("huerta2502@hotmail.com") || request.getParameter("mail").equals("rhayyim@hotmail.com")) {
                                switch (request.getParameter("mail")) {
                                    case "brenbetto17190@hotmail.com":
                                        admin = 1;
                                        break;
                                    case "chavaramos.ipn@gmail.com":
                                        admin = 2;
                                        break;
                                    case "rhayyim@hotmail.com":
                                        admin = 3;
                                        break;
                                    case "huerta2502@hotmail.com":
                                        admin = 4;
                                        break;
                                }
                            }
                            Usuario usuario = new Usuario(0, nombre, contra, correo, admin);
                            boolean signin = user.createUser(usuario);
                            if (signin) {
                                int[] login = user.loginUser(nombre, contra);
                                int id = login[0];
                                Mail email = new Mail();
                                nombre = ESAPI.encoder().decodeForHTML(user.readUser(id).getU_nombre());
                                correo = ESAPI.encoder().decodeForHTML(user.readUser(id).getU_correo());
                                String de = "rushtechnologiessadecv@gmail.com";
                                String clave = "Rt2018cecyt9";
                                String mensaje = "Hola estimado: " + nombre + "\n" + "\n" + "Nos llena de gusto darte de bienvenida a SpaceRide," + "\n" + "Juega y aumenta tus conocimientos las veces que quieras, el juego no tiene ningún costo." + "\n" + "\n" + "Atte. Rush Tecnologies S.A. de C.V.";
                                String asunto = "Bienvenido s SpaceRide";
                                try {
                                    boolean envio = email.enviarCorreo(de, clave, correo, mensaje, asunto);
                                    if (envio) {
                                        out.println("<script>mailContact();</script>");
                                    } else {
                                        out.println("<script>error();</script>");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                } finally {
                                    sesion = request.getSession(true);
                                    sesion.setAttribute("id", id);
                                    response.sendRedirect(stats);
                                }
                            } else {
                                out.println("<script>exists();</script>");
                                response.sendRedirect(index);
                            }
                        }
                    } else {
                        if (request.getParameter("submitsr").equals("UpdateD") && referer.contains(stats)) {
                            HttpSession sesion = request.getSession();
                            int id = Integer.parseInt(sesion.getAttribute("id").toString());
                            int empty = 0;
                            String nombre = "";
                            if (!request.getParameter("user").equals("") && request.getParameter("user") != null) {
                                nombre = ESAPI.encoder().encodeForHTML(request.getParameter("user"));
                            } else {
                                ++empty;
                            }
                            String contra = "";
                            if (!request.getParameter("pass").equals("") && request.getParameter("pass") != null) {
                                contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass"));
                            } else {
                                ++empty;
                            }
                            String correo = "";
                            if (!request.getParameter("mail").equals("") && request.getParameter("mail") != null) {
                                correo = ESAPI.encoder().encodeForHTML(request.getParameter("mail"));
                            } else {
                                ++empty;
                            }
                            if (empty > 0) {
                                out.println("<script>invalid();</script>");
                                response.sendRedirect(stats);
                            } else {
                                if (user.readUser(id).getU_contra().equals(contra)) {
                                    Usuario usuario = new Usuario(id, nombre, contra, correo, 0);
                                    boolean updatedata = user.updateUserData(usuario);
                                    if (updatedata) {
                                        out.println("<script>alert('Datos del usuario actualizados');</script>");
                                        response.sendRedirect(stats);
                                    } else {
                                        out.println("<script>error();</script>");
                                        response.sendRedirect(stats);
                                    }
                                } else {
                                    out.println("<script>alert('Contraseña incorrecta, revisa tus datos');</script>");
                                    response.sendRedirect(stats);
                                }
                            }
                        } else {
                            if (request.getParameter("submitsr").equals("UpdateP") && referer.contains(stats)) {
                                HttpSession sesion = request.getSession();
                                int id = Integer.parseInt(sesion.getAttribute("id").toString());
                                int empty = 0;
                                String contra = "";
                                if (!request.getParameter("pass2").equals("") && request.getParameter("pass2") != null) {
                                    contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass2"));
                                } else {
                                    ++empty;
                                }
                                String ncontra = "";
                                if (!request.getParameter("npass").equals("") && request.getParameter("npass") != null && request.getParameter("npass2") != null && request.getParameter("npass").equals(request.getParameter("npass2"))) {
                                    ncontra = ESAPI.encoder().encodeForHTML(request.getParameter("npass"));
                                } else {
                                    ++empty;
                                }
                                if (empty > 0) {
                                    out.println("<script>invalid();</script>");
                                    response.sendRedirect(stats);
                                } else {
                                    if (user.readUser(id).getU_contra().equals(contra)) {
                                        boolean updatePsd = user.updateUserPsd(id, ncontra);
                                        if (updatePsd) {
                                            out.println("<script>alert('Contrseña del usuario actualizada');</script>");
                                            response.sendRedirect(stats);
                                        } else {
                                            out.println("<script>error();</script>");
                                            response.sendRedirect(stats);
                                        }
                                    } else {
                                        out.println("<script>alert('Contraseña incorrecta, revisa tus datos');</script>");
                                        response.sendRedirect(stats);
                                    }
                                }
                            } else {
                                if (request.getParameter("submitsr").equals("LogInGame") && referer.contains(game)) {
                                    HttpSession sesion;
                                    int empty = 0;
                                    String nombre1 = "";
                                    if (!request.getParameter("user1").equals("") && request.getParameter("user1") != null) {
                                        nombre1 = ESAPI.encoder().encodeForHTML(request.getParameter("user1"));
                                    } else {
                                        ++empty;
                                    }
                                    String contra1 = "";
                                    if (!request.getParameter("pass1").equals("") && request.getParameter("pass1") != null && request.getParameter("pass1").length() >= 8) {
                                        contra1 = ESAPI.encoder().encodeForHTML(request.getParameter("pass1"));
                                    } else {
                                        ++empty;
                                    }
                                    String nombre2 = "";
                                    if (!request.getParameter("user2").equals("") && request.getParameter("user2") != null) {
                                        nombre2 = ESAPI.encoder().encodeForHTML(request.getParameter("user2"));
                                    } else {
                                        ++empty;
                                    }
                                    String contra2 = "";
                                    if (!request.getParameter("pass2").equals("") && request.getParameter("pass2") != null && request.getParameter("pass2").length() >= 8) {
                                        contra2 = ESAPI.encoder().encodeForHTML(request.getParameter("pass2"));
                                    } else {
                                        ++empty;
                                    }
                                    if (empty > 0) {
                                        out.println("<script>invalid()</script>");
                                        response.sendRedirect(game);
                                    } else {
                                        createPreguntas();
                                        int[] login1 = user.loginUser(nombre1, contra1), login2 = user.loginUser(nombre2, contra2);
                                        int id1 = login1[0], id2 = login2[0];
                                        int caselogin1 = login1[1], caselogin2 = login2[1];
                                        if ((caselogin1 == 0 && caselogin2 == 0) || caselogin1 == 0 || caselogin2 == 0) {
                                            out.println("<script>failedLogin()</script>");
                                            response.sendRedirect(game);
                                        } else {
                                            if (caselogin1 == 1 && caselogin2 == 1 && id1 != id2) {
                                                sesion = request.getSession(true);
                                                question.updateResetP_seleccion();
                                                sesion.setAttribute("id1", id1);
                                                sesion.setAttribute("id2", id2);
                                                response.sendRedirect(choose);
                                            } else {
                                                if ((caselogin1 == 2 && caselogin2 == 2) || caselogin1 == 2 || caselogin2 == 2) {
                                                    out.println("<script>failedLogin()</script>");
                                                    sesion = request.getSession(true);
                                                    if (caselogin1 == 2) {
                                                        sesion.setAttribute("id", id1);
                                                        response.sendRedirect(fp);
                                                    } else {
                                                        if (caselogin2 == 2) {
                                                            sesion.setAttribute("id", id2);
                                                            response.sendRedirect(fp);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (request.getParameter("submitsr").equals("Createq") && referer.contains(faqs)) {
                                        HttpSession sesion;
                                        int empty = 0;
                                        String topic = "";
                                        if (!request.getParameter("topic").equals("") && request.getParameter("topic") != null) {
                                            topic = ESAPI.encoder().encodeForHTML(request.getParameter("topic"));
                                        } else {
                                            ++empty;
                                        }
                                        String q = "";
                                        if (!request.getParameter("q").equals("") && request.getParameter("q") != null) {
                                            q = ESAPI.encoder().encodeForHTML(request.getParameter("q"));
                                        } else {
                                            ++empty;
                                        }
                                        String nombre = "";
                                        if (!request.getParameter("user").equals("") && request.getParameter("user") != null) {
                                            nombre = ESAPI.encoder().encodeForHTML(request.getParameter("user"));
                                        } else {
                                            ++empty;
                                        }
                                        String contra = "";
                                        if (!request.getParameter("pass").equals("") && request.getParameter("pass") != null) {
                                            contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass"));
                                        } else {
                                            ++empty;
                                        }
                                        boolean recaptcha = false;
                                        if (!request.getParameter("g-recaptcha-response").equals("") && request.getParameter("g-recaptcha-response") != null) {
                                            recaptcha = isCaptchaValid(request.getParameter("g-recaptcha-response"));
                                        } else {
                                            if (!request.getParameter("grec").equals("") && request.getParameter("grec") != null) {
                                                recaptcha = isCaptchaValid(request.getParameter("grec"));
                                            } else {
                                                ++empty;
                                            }
                                        }
                                        if (empty > 0 || !recaptcha) {
                                            out.println("<script>invalid()</script>");
                                            response.sendRedirect(faqs);
                                        } else {
                                            int[] login;
                                            login = user.loginUser(nombre, contra);
                                            int id = login[0];
                                            int caselogin = login[1];
                                            switch (caselogin) {
                                                case 0:
                                                    out.println("<script>failedLogin()</script>");
                                                    response.sendRedirect(faqs);
                                                    break;
                                                case 1:
                                                    Faq f = new Faq(0, q, null, topic, 1, false, id, 1);
                                                    boolean create = faq.createFaq(f);
                                                    if (create) {
                                                        out.println("<script>alert('Pregunta frecuente registrada');</script>");
                                                        response.sendRedirect(faqs);
                                                    } else {
                                                        out.println("<script>error()</script>");
                                                        response.sendRedirect(faqs);
                                                    }
                                                    break;
                                                case 2:
                                                    sesion = request.getSession(true);
                                                    sesion.setAttribute("id", id);
                                                    out.println("<script>failedLogin()</script>");
                                                    response.sendRedirect(fp);
                                                    break;
                                                default:
                                                    out.println("<script>failedLogin()</script>");
                                                    response.sendRedirect(faqs);
                                                    break;
                                            }
                                        }
                                    } else {
                                        if (temachoose.contains(request.getParameter("submitsr").substring(0, 3)) && referer.contains(choose)) {
                                            HttpSession sesion = request.getSession();
                                            int id1 = 0, id2 = 0, empty = 0, difficulty = 5;
                                            if (sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null) {
                                                id1 = Integer.parseInt(sesion.getAttribute("id1").toString());
                                                id2 = Integer.parseInt(sesion.getAttribute("id2").toString());
                                            } else {
                                                empty++;
                                            }
                                            if ("0123".contains(request.getParameter("submitsr").substring(3))) {
                                                difficulty = Integer.parseInt(request.getParameter("submitsr").substring(3));
                                            } else {
                                                ++empty;
                                            }
                                            String area = "";
                                            int area_id = 0;
                                            for (int i = 1; i < temachoose.size(); i++) {
                                                if (request.getParameter("submitsr").substring(0, 3).equals(temachoose.get(i))) {
                                                    area = temas[i];
                                                    area_id = i;
                                                }
                                            }
                                            if (area_id == 0) {
                                                ++empty;
                                            }
                                            if (empty > 0) {
                                                response.sendRedirect(choose);
                                            } else {
                                                if (muerto.readMuerto() != null) {
                                                    int exist = muerto.readMuerto().getM_u_id1();
                                                    Muerto m = new Muerto(id1, id2, area_id, difficulty, 0);
                                                    boolean updatem = muerto.updateMuerto(m, exist);
                                                    if (updatem) {
                                                        sesion.setAttribute("area_id", area_id);
                                                        sesion.setAttribute("area", area);
                                                        sesion.setAttribute("dif", difficulty);
                                                        sesion.setAttribute("id1", id1);
                                                        sesion.setAttribute("id2", id2);
                                                        response.sendRedirect(listener);
                                                    } else {
                                                        response.sendRedirect(choose);
                                                    }
                                                } else {
                                                    Muerto m = new Muerto(id1, id2, area_id, difficulty, 0);
                                                    boolean createm = muerto.createMuerto(m);
                                                    if (createm) {
                                                        sesion.setAttribute("area_id", area_id);
                                                        sesion.setAttribute("area", area);
                                                        sesion.setAttribute("dif", difficulty);
                                                        sesion.setAttribute("id1", id1);
                                                        sesion.setAttribute("id2", id2);
                                                        response.sendRedirect(listener);
                                                    } else {
                                                        response.sendRedirect(choose);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (request.getParameter("submitsr").equals("Comenzar") && referer.contains(listener)) {
                                                HttpSession sesion = request.getSession();
                                                if (sesion != null && sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null && sesion.getAttribute("area_id") != null && sesion.getAttribute("area") != null && sesion.getAttribute("dif") != null) {
                                                    int idm = 0;
                                                    boolean whilepass = false;
                                                    while (user.readU_muerto() == null) {
                                                        if (user.readU_muerto() != null) {
                                                            idm = user.readU_muerto().getU_id();
                                                            sesion.setAttribute("idm", idm);
                                                            response.sendRedirect(answer);
                                                        }
                                                        whilepass = true;
                                                    }
                                                    if (user.readU_muerto() != null && !whilepass) {
                                                        idm = user.readU_muerto().getU_id();
                                                        sesion.setAttribute("idm", idm);
                                                        response.sendRedirect(answer);
                                                    }
                                                } else {
                                                    response.sendRedirect(listener);
                                                }
                                            } else {
                                                if (request.getParameter("submitsr").equals("Responder") && referer.contains(answer)) {
                                                    HttpSession sesion = request.getSession();
                                                    if (sesion.getAttribute("idm") != null && sesion.getAttribute("p_id") != null && sesion.getAttribute("p") != null && sesion.getAttribute("r1") != null && sesion.getAttribute("r2") != null && sesion.getAttribute("r3") != null && sesion.getAttribute("r4") != null && sesion.getAttribute("correcta") != null && sesion.getAttribute("area") != null && sesion.getAttribute("dif") != null && sesion.getAttribute("area_id") != null && sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null) {
                                                        int idm = Integer.parseInt(sesion.getAttribute("idm").toString());
                                                        int idv = 0;
                                                        String[] r = {"", sesion.getAttribute("r1").toString(), sesion.getAttribute("r2").toString(), sesion.getAttribute("r3").toString(), sesion.getAttribute("r4").toString()};
                                                        int correcta = Integer.parseInt(sesion.getAttribute("correcta").toString());
                                                        String tema = sesion.getAttribute("area").toString();
                                                        int u_id1 = Integer.parseInt(sesion.getAttribute("id1").toString());
                                                        int u_id2 = Integer.parseInt(sesion.getAttribute("id2").toString());
                                                        int area_id = Integer.parseInt(sesion.getAttribute("area_id").toString());
                                                        if (idm == u_id1) {
                                                            idv = u_id2;
                                                        } else {
                                                            if (idm == u_id2) {
                                                                idv = u_id1;
                                                            } else {
                                                                response.sendRedirect(answer);
                                                            }
                                                        }
                                                        String ans = request.getParameter("answer");
                                                        if (ans.equals("") || ans == null) {
                                                            out.println("<script>alert('Seleccione una respuesta');</script>");
                                                            response.sendRedirect(answer);
                                                        } else {
                                                            int a = 0;
                                                            switch (ans) {
                                                                case "r1":
                                                                    a = 1;
                                                                    break;
                                                                case "r2":
                                                                    a = 2;
                                                                    break;
                                                                case "r3":
                                                                    a = 3;
                                                                    break;
                                                                case "r4":
                                                                    a = 4;
                                                                    break;
                                                            }
                                                            if (a != 0) {
                                                                if (a == correcta) {
                                                                    out.println("<script>(function(){alert('Respuesta correcta, recupera una vida');});</script>");
                                                                    boolean update = muerto.updateM_correcta(u_id1, 1);
                                                                    boolean restart = user.updateU_muertoReset(u_id1, u_id2);
                                                                    if (update && restart) {
                                                                        sesion.setAttribute("here", "here");
                                                                        response.sendRedirect(choose);
                                                                    } else {
                                                                        response.sendRedirect(answer);
                                                                    }
                                                                } else {
                                                                    out.println("<script>(function(){alert('Respuesta incorrecta, la respuesta correcta era " + r[correcta] + "');});</script>");
                                                                    boolean update = muerto.updateM_correcta(u_id1, 2);
                                                                    if (update) {
                                                                        Area ar = area.readArea(idm);
                                                                        if (ar.getArea_max_id() == 0 || ar.getArea_max() == 0) {
                                                                            ImpArea imparea = new ImpArea();
                                                                            boolean updatea = imparea.updateArea_area(idm, tema, ar, area_id);
                                                                            boolean updateum = user.updateFirstU_derrotas(idm, area_id);
                                                                            if (updateum && updatea) {
                                                                                boolean updateuv = user.updateU_victoria(idv);
                                                                                boolean restart = user.updateU_muertoReset(u_id1, u_id2);
                                                                                if (updateuv && restart) {
                                                                                    sesion.setAttribute("here", "here");
                                                                                    response.sendRedirect(choose);
                                                                                } else {
                                                                                    response.sendRedirect(answer);
                                                                                }
                                                                            } else {
                                                                                response.sendRedirect(answer);
                                                                            }
                                                                        } else {
                                                                            boolean updateum = user.updateU_derrotas(idm);
                                                                            boolean updatea = false;
                                                                            ImpArea imparea = new ImpArea();
                                                                            if (temas[ar.getArea_max_id()].equals(tema)) {
                                                                                updatea = imparea.updateArea_area(idm, temas[ar.getArea_max_id()], ar, ar.getArea_max_id());
                                                                            } else {
                                                                                updatea = imparea.updateArea_area(idm, tema, ar, area_id);
                                                                            }
                                                                            if (updateum && updatea) {
                                                                                boolean updateuv = user.updateU_victoria(idv);
                                                                                boolean restart = user.updateU_muertoReset(u_id1, u_id2);
                                                                                if (updateuv && restart) {
                                                                                    sesion.setAttribute("here", "here");
                                                                                    response.sendRedirect(choose);
                                                                                } else {
                                                                                    response.sendRedirect(answer);
                                                                                }
                                                                            } else {
                                                                                response.sendRedirect(answer);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                response.sendRedirect(answer);
                                                            }
                                                        }
                                                    } else {
                                                        response.sendRedirect(answer);
                                                    }
                                                } else {
                                                    if (request.getParameter("submitsr").contains("ResponderFaq") && referer.contains(stats)) {
                                                        HttpSession sesion = request.getSession();
                                                        int empty = 0;
                                                        int u_id = 0;
                                                        if (sesion != null && sesion.getAttribute("id") != null) {
                                                            u_id = Integer.parseInt(sesion.getAttribute("id").toString());
                                                        } else {
                                                            ++empty;
                                                        }
                                                        int faq_id = 0;
                                                        String strfaq_id = request.getParameter("submitsr").substring(12);
                                                        try {
                                                            faq_id = Integer.parseInt(strfaq_id);
                                                        } catch (NumberFormatException nfe) {
                                                            System.out.println("Error at ResponderFaq: " + nfe.getMessage());
                                                            empty++;
                                                        }
                                                        String ans = "";
                                                        if (request.getParameter("answer") != null && !request.getParameter("answer").equals("")) {
                                                            ans = ESAPI.encoder().encodeForHTML(request.getParameter("answer"));
                                                        } else {
                                                            ++empty;
                                                        }
                                                        if (empty > 0) {
                                                            out.println("<script>alert('Ingrese la respuesta correspondiente');</script>");
                                                            response.sendRedirect(stats);
                                                        } else {
                                                            boolean update = faq.updateFaq_respuesta(faq_id, ans, u_id);
                                                            if (update) {
                                                                out.println("<script>alert('Pregunta frecuente respondida correctamente');</script>");
                                                                response.sendRedirect(stats);
                                                            } else {
                                                                out.println("<script>alert('Error al responder la pregunta frecuente');</script>");
                                                                response.sendRedirect(stats);
                                                            }
                                                        }
                                                    } else {
                                                        if (request.getParameter("submitsr").contains("DescartarFaq") && referer.contains(stats)) {
                                                            HttpSession sesion = request.getSession();
                                                            if (sesion != null && sesion.getAttribute("id") != null) {
                                                                int empty = 0;
                                                                int faq_id = 0;
                                                                String strfaq_id = request.getParameter("submitsr").substring(12);
                                                                try {
                                                                    faq_id = Integer.parseInt(strfaq_id);
                                                                } catch (NumberFormatException nfe) {
                                                                    System.out.println("Error: " + nfe.getMessage());
                                                                    empty++;
                                                                }
                                                                if (empty > 0) {
                                                                    out.println("<script>alert('Error al obtener los datos de la pregunt frecuente a descartar');</script>");
                                                                    response.sendRedirect(stats);
                                                                } else {
                                                                    boolean delete = faq.deleteFaq(faq_id);
                                                                    if (delete) {
                                                                        out.println("<script>alert('Pregunta frecuente descartada exitosamente');</script>");
                                                                        response.sendRedirect(stats);
                                                                    } else {
                                                                        out.println("<script>alert('Error al descartar la pregunta frecuente');</script>");
                                                                        response.sendRedirect(stats);
                                                                    }
                                                                }
                                                            } else {
                                                                response.sendRedirect(index);
                                                            }
                                                        } else {
                                                            if (request.getParameter("submitsr").equals("ChatLogin") && referer.contains(faqs)) {
                                                                int empty = 0;
                                                                String nombre = "";
                                                                if (request.getParameter("user2") != null && !request.getParameter("user2").equals("")) {
                                                                    nombre = ESAPI.encoder().encodeForHTML(request.getParameter("user2"));
                                                                } else {
                                                                    ++empty;
                                                                }
                                                                String contra = "";
                                                                if (request.getParameter("pass2") != null && !request.getParameter("pass2").equals("") && request.getParameter("pass2").length() >= 8) {
                                                                    contra = ESAPI.encoder().encodeForHTML(request.getParameter("pass2"));
                                                                } else {
                                                                    ++empty;
                                                                }
                                                                if (empty > 0) {
                                                                    out.println("<script>invalid()</script>");
                                                                    response.sendRedirect(index);
                                                                } else {
                                                                    int[] login = user.loginUser(nombre, contra);
                                                                    int u_id = login[0];
                                                                    int caselogin = login[1];
                                                                    HttpSession sesion;
                                                                    switch (caselogin) {
                                                                        case 0:
                                                                            out.println("<script>failedLogin()</script>");
                                                                            response.sendRedirect(faqs);
                                                                            break;
                                                                        case 1:
                                                                            sesion = request.getSession(true);
                                                                            sesion.setAttribute("u_id", u_id);
                                                                            boolean create = c.createChat(2, u_id);
                                                                            if (create) {
                                                                                sesion.setAttribute("isSupport", "no");
                                                                                response.sendRedirect(chats);
                                                                            } else {
                                                                                if (c.readAllChat(2, u_id).getChat_U_Id() == u_id) {
                                                                                    sesion.setAttribute("isSupport", "no");
                                                                                    response.sendRedirect(chats);

                                                                                } else {
                                                                                    response.sendRedirect(faqs);
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 2:
                                                                            sesion = request.getSession(true);
                                                                            sesion.setAttribute("id", u_id);
                                                                            response.sendRedirect(fp);
                                                                            break;
                                                                        default:
                                                                            out.println("<script>failedLogin()</script>");
                                                                            response.sendRedirect(faqs);
                                                                            break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (request.getParameter("submitsr").substring(0, 4).equals("Chat") && referer.contains(stats)) {
                                                                    String encrypted = request.getParameter("submitsr").substring(4);
                                                                    int u_id = 0;
                                                                    int empty = 0;
                                                                    try {
                                                                        u_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                    } catch (NumberFormatException e) {
                                                                        System.out.println("Error at Chat: " + e.getMessage());
                                                                        ++empty;
                                                                    }
                                                                    if (empty > 0) {
                                                                        response.sendRedirect(stats);
                                                                    } else {
                                                                        HttpSession sesion;
                                                                        sesion = request.getSession();
                                                                        sesion.setAttribute("isSupport", "yes");
                                                                        sesion.setAttribute("u_id", u_id);
                                                                        response.sendRedirect(chats);
                                                                    }
                                                                } else {
                                                                    if (request.getParameter("submitsr").equals("EnviarChat") && referer.contains(chats)) {
                                                                        HttpSession httpSession = request.getSession();
                                                                        int empty = 0;
                                                                        String msg = "";
                                                                        if (request.getParameter("msg") != null && !request.getParameter("msg").equals("")) {
                                                                            msg = ESAPI.encoder().encodeForHTML(request.getParameter("msg"));
                                                                        } else {
                                                                            ++empty;
                                                                        }
                                                                        if (empty > 0) {
                                                                            out.println("<script>invalid()</script>");
                                                                            response.sendRedirect(chats);
                                                                        } else {
                                                                            int u_id = Integer.parseInt(httpSession.getAttribute("u_id").toString());
                                                                            int chat_id = Integer.parseInt(httpSession.getAttribute("chat_id").toString());
                                                                            boolean isSupport = false;
                                                                            if (httpSession.getAttribute("isSupport").toString().equals("yes")) {
                                                                                isSupport = true;
                                                                            }
                                                                            boolean updateMsgs, updateTime;
                                                                            String msgs, time = c.readAllChat(2, u_id).getChat_Time();
                                                                            if (isSupport) {
                                                                                msgs = c.readAllChat(2, u_id).getChat_S_Msgs();
                                                                                if (msgs.equals("")) {
                                                                                    msgs = msg + "/";
                                                                                } else {
                                                                                    msgs += msg + "/";
                                                                                }
                                                                                time += "1";
                                                                                updateMsgs = c.updateChat_S_Msgs(2, u_id, msgs);
                                                                                updateTime = c.updateChatTime(chat_id, time);
                                                                                if (updateMsgs && updateTime) {
                                                                                    response.sendRedirect(chats);
                                                                                } else {
                                                                                    out.println("<script>error();</script>");
                                                                                    response.sendRedirect(chats);
                                                                                }
                                                                            } else {
                                                                                msgs = c.readAllChat(2, u_id).getChat_U_Msgs();
                                                                                if (msgs.equals("")) {
                                                                                    msgs = msg + "/";
                                                                                } else {
                                                                                    msgs += msg + "/";
                                                                                }
                                                                                time += "2";
                                                                                updateMsgs = c.updateChat_U_Msgs(2, u_id, msgs);
                                                                                updateTime = c.updateChatTime(chat_id, time);
                                                                                if (updateMsgs && updateTime) {
                                                                                    response.sendRedirect(chats);
                                                                                } else {
                                                                                    out.println("<script>error();</script>");
                                                                                    response.sendRedirect(chats);
                                                                                }
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (request.getParameter("submitsr").substring(0, 8).equals("BorrarRE") && referer.contains(stats)) {
                                                                            String encrypted = request.getParameter("submitsr").substring(8);
                                                                            int er_id = 0, empty = 0;
                                                                            try {
                                                                                er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                            } catch (NumberFormatException e) {
                                                                                System.out.println("Error at BorrarRE: " + e.getMessage());
                                                                                ++empty;
                                                                            }
                                                                            if (empty > 0) {
                                                                                response.sendRedirect(stats);
                                                                            } else {
                                                                                boolean delete = er.deleteEventReport(er_id);
                                                                                if (delete) {
                                                                                    out.println("<script>alert('El reporte de evento ha sido borrado');</script>");
                                                                                    response.sendRedirect(stats);
                                                                                } else {
                                                                                    out.println("<script>alert('No se ha podido borrar del reporte de evento');</script>");
                                                                                    response.sendRedirect(stats);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (request.getParameter("submitsr").substring(0, 8).equals("Reportar") && referer.contains(stats)) {
                                                                                String encrypted = request.getParameter("submitsr").substring(8);
                                                                                HttpSession httpSession = request.getSession();
                                                                                int u_id = 0, empty = 0, s_id = 0;
                                                                                try {
                                                                                    u_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                    s_id = Integer.parseInt(httpSession.getAttribute("id").toString());
                                                                                } catch (NumberFormatException e) {
                                                                                    System.out.println("Error at Reportar: " + e.getMessage());
                                                                                    ++empty;
                                                                                }
                                                                                String description = "";
                                                                                if (request.getParameter("description") != null && !request.getParameter("description").equals("")) {
                                                                                    description = ESAPI.encoder().encodeForHTML(request.getParameter("description"));
                                                                                } else {
                                                                                    ++empty;
                                                                                }
                                                                                /*String category = "";
                                                                                if (request.getParameter("category") != null && !request.getParameter("category").equals("")) {
                                                                                    category = ESAPI.encoder().encodeForHTML(request.getParameter("category"));
                                                                                } else {
                                                                                    ++empty;
                                                                                }*/
                                                                                if (empty > 0) {
                                                                                    response.sendRedirect(stats);
                                                                                } else {
                                                                                    String transcript = c.readAllChat(s_id, u_id).getChat_U_Msgs();
                                                                                    EventReport eventReport = new EventReport(0, u_id, transcript, description, /*category*/ "", null, null, 4, null);
                                                                                    boolean create = er.createEventReport(eventReport);
                                                                                    if (create) {
                                                                                        out.println("<script>alert('Reporte de evento registrado');</script>");
                                                                                        response.sendRedirect(stats);
                                                                                    } else {
                                                                                        out.println("<script>alert('No se registro el reporte de evento');</script>");
                                                                                        response.sendRedirect(stats);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (request.getParameter("submitsr").substring(0, 8).equals("Createqr") && referer.contains(stats)) {
                                                                                    String encrypted = request.getParameter("submitsr").substring(8);
                                                                                    HttpSession httpSession = request.getSession();
                                                                                    int er_id = 0, empty = 0, s_id = 0;
                                                                                    try {
                                                                                        er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                        s_id = Integer.parseInt(httpSession.getAttribute("id").toString());
                                                                                    } catch (NumberFormatException e) {
                                                                                        System.out.println("Error at Createqr: " + e.getMessage());
                                                                                        ++empty;
                                                                                    }
                                                                                    if (empty > 0) {
                                                                                        response.sendRedirect(stats);
                                                                                    } else {
                                                                                        EventReport eventReport = er.readEventReport(er_id);
                                                                                        boolean create = faq.createFaq(new Faq(0, eventReport.getER_Description(), eventReport.getER_Solution(), eventReport.getER_Category(), 1, true, s_id, s_id));
                                                                                        if (create) {
                                                                                            out.println("<script>alert('Pregunta frecuente registrada');</script>");
                                                                                            response.sendRedirect(stats);
                                                                                        } else {
                                                                                            out.println("<script>alert('No se registro la pregunta frecuente');</script>");
                                                                                            response.sendRedirect(stats);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (request.getParameter("submitsr").substring(0, 8).equals("Asignars") && referer.contains(stats)) {
                                                                                        String encrypted = request.getParameter("submitsr").substring(8);
                                                                                        int er_id = 0, empty = 0, s_id = 0;
                                                                                        try {
                                                                                            er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                        } catch (NumberFormatException e) {
                                                                                            System.out.println("Error at Asignars: " + e.getMessage());
                                                                                            ++empty;
                                                                                        }
                                                                                        if (request.getParameter("support") != null && !request.getParameter("support").equals("")) {
                                                                                            s_id = Integer.parseInt(encrypt.decrypt(request.getParameter("support")));
                                                                                        } else {
                                                                                            ++empty;
                                                                                        }
                                                                                        if (empty > 0) {
                                                                                            response.sendRedirect(stats);
                                                                                        } else {
                                                                                            boolean update = er.updateERChief(s_id, er_id);
                                                                                            if (update) {
                                                                                                out.println("<script>alert('Reporte de evento asignado');</script>");
                                                                                                response.sendRedirect(stats);
                                                                                            } else {
                                                                                                out.println("<script>alert('No se asigno el reporte de evento');</script>");
                                                                                                response.sendRedirect(stats);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (request.getParameter("submitsr").substring(0, 8).equals("Resolver") && referer.contains(stats)) {
                                                                                            String encrypted = request.getParameter("submitsr").substring(8);
                                                                                            int er_id = 0, empty = 0;
                                                                                            try {
                                                                                                er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                            } catch (NumberFormatException e) {
                                                                                                System.out.println("Error at Resolver: " + e.getMessage());
                                                                                                ++empty;
                                                                                            }
                                                                                            String solution = "";
                                                                                            if (request.getParameter("answer") != null && !request.getParameter("answer").equals("")) {
                                                                                                solution = ESAPI.encoder().encodeForHTML(request.getParameter("answer"));
                                                                                            } else {
                                                                                                ++empty;
                                                                                            }
                                                                                            if (empty > 0) {
                                                                                                response.sendRedirect(stats);
                                                                                            } else {
                                                                                                boolean update = er.updateERSupport(solution, er_id);
                                                                                                List<MaintenanceReport> maintenanceReports = mr.readAllMR();
                                                                                                for(MaintenanceReport maintenanceReport: maintenanceReports) {
                                                                                                    if(maintenanceReport.getMR_Description().equals(er.readEventReport(er_id).getER_Description())) {
                                                                                                        update = mr.updateMRDeveloper(solution, maintenanceReport.getMR_Id());
                                                                                                    }
                                                                                                }
                                                                                                if (update) {
                                                                                                    out.println("<script>alert('Reporte de evento solucionado');</script>");
                                                                                                    response.sendRedirect(stats);
                                                                                                } else {
                                                                                                    out.println("<script>alert('No se soluciono el reporte de evento');</script>");
                                                                                                    response.sendRedirect(stats);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (request.getParameter("submitsr").substring(0, 8).equals("Validare") && referer.contains(stats)) {
                                                                                                String encrypted = request.getParameter("submitsr").substring(8);
                                                                                                int er_id = 0, empty = 0;
                                                                                                try {
                                                                                                    er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                                } catch (NumberFormatException e) {
                                                                                                    System.out.println("Error at Validare: " + e.getMessage());
                                                                                                    ++empty;
                                                                                                }
                                                                                                if (empty > 0) {
                                                                                                    response.sendRedirect(stats);
                                                                                                } else {
                                                                                                    boolean update = er.updateERUser(er_id, "Solucionado");
                                                                                                    if (update) {
                                                                                                        out.println("<script>alert('Reporte de evento validado');</script>");
                                                                                                        response.sendRedirect(stats);
                                                                                                    } else {
                                                                                                        out.println("<script>alert('No se validó el reporte de evento');</script>");
                                                                                                        response.sendRedirect(stats);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (request.getParameter("submitsr").substring(0, 8).equals("Creamrer") && referer.contains(stats)) {
                                                                                                    String encrypted = request.getParameter("submitsr").substring(8);
                                                                                                    int er_id = 0, empty = 0;
                                                                                                    try {
                                                                                                        er_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                                    } catch (NumberFormatException e) {
                                                                                                        System.out.println("Error at Creamrer: " + e.getMessage());
                                                                                                        ++empty;
                                                                                                    }
                                                                                                    if (empty > 0) {
                                                                                                        response.sendRedirect(stats);
                                                                                                    } else {
                                                                                                        MaintenanceReport maintenanceReport = new MaintenanceReport(0, "Eventos", er.readEventReport(er_id).getER_Description(), "Pendiente", null, 4, null);
                                                                                                        boolean create = mr.createMaintenanceReport(maintenanceReport);
                                                                                                        boolean update = er.updateERMaintenance(er_id);
                                                                                                        if (create && update) {
                                                                                                            out.println("<script>alert('Reporte de mantenimiento creado');</script>");
                                                                                                            response.sendRedirect(stats);
                                                                                                        } else {
                                                                                                            out.println("<script>alert('No se ha podido registrar el reporte de mantenimiento');</script>");
                                                                                                            response.sendRedirect(stats);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    if (request.getParameter("submitsr").substring(0, 8).equals("Deletefq") && referer.contains(stats)) {
                                                                                                        String encrypted = request.getParameter("submitsr").substring(8);
                                                                                                        int faq_id = 0, empty = 0;
                                                                                                        try {
                                                                                                            faq_id = Integer.parseInt(encrypt.decrypt(encrypted));
                                                                                                        } catch (NumberFormatException e) {
                                                                                                            System.out.println("Error at Deletefq: " + e.getMessage());
                                                                                                            ++empty;
                                                                                                        }
                                                                                                        if (empty > 0) {
                                                                                                            response.sendRedirect(stats);
                                                                                                        } else {
                                                                                                            boolean delete = faq.deleteFaq(faq_id);
                                                                                                            if (delete) {
                                                                                                                out.println("<script>alert('Pregunta frecuente descartada exitosamente');</script>");
                                                                                                                response.sendRedirect(stats);
                                                                                                            } else {
                                                                                                                out.println("<script>alert('Error al descartar la pregunta frecuente');</script>");
                                                                                                                response.sendRedirect(stats);
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        response.sendRedirect(referer);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                response.sendRedirect(referer);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Validates Google reCAPTCHA V2.
     *
     * @param response reCAPTCHA response from client side.
     * (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public boolean isCaptchaValid(String response) {
        String secretKey = "6LdM9pEUAAAAAKeBxFReNsZqjLypCcU5clN9pWlD";
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?"
                    + "secret=" + secretKey
                    + "&response=" + response;
            String jsonText;
            try (InputStream res = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                    sb.append((char) cp);
                }
                jsonText = sb.toString();
            }
            JSONObject json = new JSONObject(jsonText);
            return json.getBoolean("success");
        } catch (IOException | JSONException e) {
            return false;
        }
    }

    /**
     * Create questions if not exists
     */
    public void createPreguntas() {
        final String[] preguntasMate = {"234 + 826 + 48", "25^2", "6^3", "201 - 36 - 45 - 125", "11/3 + 29/6 + 3/9", "¿Cómo sigue la siguiente secuencia lógica: 145, 122, 101, 82, 65, 50, ___?",
            "¿A cuánto equivale un cuatrillón?", "¿Cuanto equivale 0.125?", "(25 * 9)^1/2", "Si a + b= 29 , b + c = 45, a + c = 44 Encuentra a + b + c = ?",
            "La intregral de sec(x) es igual a:", "(21^2)(6)"};
        final String[] preguntasFis = {"¿Que es la gravedad?", "¿A que es igual la constante gravitacional (G)?", "¿Que es un satelite?", "¿La altitud de un avion respecto a que se mide?", "Es la distancia por debajo de la cual se espera que el espacio deje de tener una geometria clasica",
            "¿Quien dijo que los planetas orbitan entorno al Sol y en orbitas circulares?", "¿Cuanto tarda la luz del Sol viajar a la tierra?", "¿Quien desarrollo la teoria electromagnetica clasica?", "Formula de altura maxima para un MRUA",
            "Masa del Electron", "¿Que diferencias hay entre la vision clasica del mundo de Newton y la relativista de Einstein?", "¿Tiene alguna aplicacion practica la relatividad general?"};
        final String[] preguntasEsp = {"Llevan tilde en la ultima silaba", "Completa el enunciado ¿Que vas _____?", "Elije la palabra correctamente escrita", "Diferencia entre Porque y Por qué", "Se escribe con ___ si le sigue CUALQUIER tipo de consonante",
            "Nunca se introduce ___ antes de B o P", "Completa el siguiente enunciado: Mientras el ____ las palomas, yo agarro el perro", "Que palabra ha sido agregada por la RAE", "Palabra que ha sido agregada a la RAE",
            "Palabra que no ha sido agregada a la RAE", "De ¿Quien es el poema 'Puedo escribir versos mas tristes esta noche'", "Fue la primera novela mexicana. Trata de las aventuras de un picaro en la Nueva España"};
        final String[] preguntasHUni = {"¿Donde nacio Napoleon Bonaparte?", "¿De que pais fue presidente Abraham Lincoln?", "¿En que guerra Aldolf Hitler lidero Alemania?", "Nombre completo de Charles Chaplin", "De las 7 maravillas del mundo antiguo ¿Cual es la mas antigua?",
            "¿Quien invento la torre de Tesla?", "Año en que murio Mao Tse Tung", "Primer Presidente de raza negra",
            "¿Cuando se creo el Opera de Sidney?", "¿Quien fue uno de los personajes que firmaron la acta de independencia de los Estados Unidos de Norteamerica?",
            "¿Quien ordeno los ataques nucleares a Japon?", "¿Cuantos años duro en el poder Fidel Castro?"};
        final String[] preguntasGeo = {"¿Cual es el rio mas largo del mundo?", "¿Cual es el pais mas grande?", "¿Cual es el lago salado mas grande del mundo?", "¿Montaña mas alta del mundo?", "Volcan mas activo del mundo",
            "En que lugar se denominan tifones a un sistema tormentoso", "Continente mas grande del mundo", "Capital de Canada",
            "Capital de India", "El volcan mas grande del mundo",
            "Cataratas mas grande del mundo", "Cuanto mide el rio mas pequeño del mundo"};
        final String[] preguntasIng = {"I want to buy ___ computer next week", "A person from India its called", "Complete the sentence. I chopped the onions and _______ them with oil.",
            "What fruit fell from a tree and hit Isaac Newton's head?", "Definition of malodorous", "Complete the sentence. After she took out the spaghetti from the oven, she _________ cheese to add the special flavor.",
            "Complete the sentence. The food is almost ready I just have to ________ it.", "Translate 'Dientes de ajo' to english", "Whats the meaning of aloof?", "Complete the sentence. The road is very dark and I haven't seen anyone else driving thru, you can say is a ________ road", "Translate 'pañuelo' to english.", "Whats the meaning of Liaison?"};
        final String[] preguntasQui = {"Particula más pequeña de un elemento y que entra a formar parte de una reaccion química", "El numero de orbitas en las que circulan los electrones y se designan a partir del nucleo ", "Numero de electrones que corresponden al nivel de energia 'N'", "'La materia no se crea ni se destruye, solo se transforma'",
            "Se obtienen a partir de la combinacion de un elemento con el oxigeno", "'Las propiedades de los elementos son consecuencias periodicas de sus pesos atomicos'. Lo anterior fue propuesto por:", "Son atomos que teniendo el mismo numero atomico, varian en su masa atomica", "Esta tecnica de fechamiento se usa para rocas y fosiles de hasta 50,000 años",
            "Mezclas de apariencia turbia, heterogeneas, formadas por un liquido y un solido insoluble o poco soluble en el liquido que los contiene", "¿A que temperatura tiene el agua su maxima densidad?", "Es un sistema coloidal en el que finas particulas provenientes de un solidos se dispersan en un liquido", "¿Quien propuso la molecula que tiene la caracteristica de ser incoloro, homociclico, aromatico y es derivado del petroleo?"};
        final String[] preguntasBio = {"¿Que es la celula?", "¿Cuales son los tipos de celulas que existen?", "Definicion de Esterilizado",
            "¿Que es una proteina?", "¿Quien fue el padre de la microbiologia moderna?", "Junto al naturalista Charles Darwin ¿Quien impulso la idea de la Teoria Evolutiva?", "¿Cual es el elemento de menor tamaño que se puede considerar vivo?",
            "¿Que organismos forman las celulas procariotas?", "Teoria de la biogenesis:", "¿De que se trata la apoptosis?", "Donde nacio Gregor Mendel conocido como el padre de la genetica", "¿Que es una angiosperma?"};
        final String[] preguntasHMex = {"¿Cultura mas antigua de México?", "¿Cuando termino la conquista de México?", "Ultimo emperador de los Aztecas", "Primer Presidente de México",
            "¿En que año pudo votar por primera ves la mujer mexicana?", "¿Cuando adquirio su autonomia la UNAM?", "¿Cuando se fundo el IPN?", "¿Cuanto duro el Porfiriato?",
            "Nombre completo del personaje conocido como Pancho Villa", "¿En que año se inauguro la linea del metro que en ese entonces iba de Zaragoza - Chapultepec?", "¿Cuando se inauguro el Palacio de Bellas Artes?", "Partido Politico que se creo en 1929 y que hoy en dia es el PRI"};
        final String[] preguntasAstro = {"¿Cual es el sexto planeta en el sistema solar?", "Nombre de la galaxia donde esta Pluton", "¿Que es la luna?", "¿Que es el sol?",
            "Se le conoce como planeta rojo", "¿Quien formulo la E = mc^2?", "¿Cuanto es una año luz?", "¿Cual es la estrella mas cercana a la tierra?",
            "Nombre de la muerte de una estrella ", "Enorme esfera de gas en revolución que se mantiene unido gracias a la fuerza gravitacional", "Una estrella fugaz es un(a)...", "Es una enorme tormenta de viento de gas alrededor de un agujero negro.",};
        final String[] preguntasEnt = {"Año de estreno de: 'Star Wars: Una nueva esperanza'", "Grupo al que pertenecia Michael Jackson antes de volverse solista", "Pelicula mas taquillera del 2016:", "Director de las peliculas de Jurassic Park",
            "Juego que llevo a la quiebra a la productora de viedojuegos 'Atari', entre otros factores", "Deporte con mas seguidores alrededor del mundo", "Saga de libros mas popular", "Album mas vendido en la historia de la musica",
            "Primer galardonado como 'Mejor Actor' en los premios Oscar", "Año en que se lanza la primera videoconsola de sobremesa, la 'Magnavox Odyssey'", "Serie mas popular en la historia", "¿Quien filmo la primera pelicula en la historia (1988)?"};
        final String[] preguntasArte = {"Autor de Romeo y Julieta:", "Autor de la Mona Lisa:", "Pintora de nacionalidad mexicana:", "Los frescos de la capilla Sixtina son de:",
            "Creadores del cubismo:", "La Gioconda, también conocida cómo:", "Representante del surrealismo:", "Movimiento cultural de Europa Occidental entre los siglos XV y XVI:",
            "¿Por qué es tan especial el Templo Expiatorio de la Sagrada Familia?", "Sonata Claro de Luna o Sonata para Piano…:", "Artemisia Gentileschi fue una pintora representante del movimiento:", "La tragedia “El rey Lear” es una obra de:"};
        final String[] preguntasTec = {"¿Que componente no es interno de una computadora?", "¿Quien fue uno de los fundadores de Apple?", "Ejemplo de Sistemas Operativos de una PC", "¿De que estan hechos los procesadores de una PC?",
            "¿En que estado o ciudad se encuentra Silicon Valley?", "En la actualidad, ¿cual es la mayor capacidad de una memoria USB?", "¿Quienes fueron los que crearon el internet?", "¿Quien creo la primer laptop?",
            "¿Quien invento la tablet?", "¿Quien creo el primer telefono celular?", "El mas poderoso chip de Intel en 2018, el i9-7980XE ¿Cual es la frecuencia maxima utilizando la tecnologia Turbo Boost?", "¿Cuanto se supone que tiene de velocidad de internet la NASA?"};
        final String[] respuestasMate = {"1508", "1108", "1098", "1008", "625", "600", "500", "650", "236", "224", "248", "216", "10", "5", "0", "1",
            "53/6", "50/9", "76/3", "145/18", "35", "33", "28", "37", "10^4", "10^24", "10^20", "10^40", "2/32", "20/80", "30/210", "20/160",
            "15", "17", "14", "19", "60", "57", "59", "55", "ln(sec(x) - tan(x)) + c", "sec(x) * tan(x) + c", "ln(csc(x) - ctg(x)) + c", "ln(sec(x) + tan(x)) + c", "2424", "2646", "2572", "2712"};
        final String[] respuestasFis = {"Es la fuerza que da la propiedad de peso a los objetos", "Es la masa de un objeto", "Es una fuerza que impide que se mueva un objeto", "Es un fenomeno natural por el cual los objetos son atraidos entre si", "9.89 x 10^9", "9.89 x 10^-9", "6.67 x10 ^11", "6.67 x10 ^-11", "Es un invento del ser humano, que es enviado en una lanzadera espacial", "Permite la comunicacion en la tierra", "Es cualquier objeto que orbita alrededor de otro", "Son enviados al espacio para explorar otros planetas", "La torre de control", "El mar", "El suelo", "El aeropuerto mas cercano",
            "Planck", "Velocidad de años luz", "Años Luz", "Luz Parada", "Isaac Newton", "Nicolas Copernico", "Galileo Galilei", "Benjamin Franklin", "5 mins", "10 mins", "8 mins", "3 mins", "Arquimedes", "Aristoteles", "Isaac Newton", "James Clerk Maxwell",
            "Vo^2/2g", "Vo^2 - gt^2 /2", "Vo - gt", "Vot - gt^2 / 2", "1.6 x10^-27", "1.6 x10^-19", "1 UMA", "9.1 x10 ^-31", "La formulacion de Newton desaparece las curvaturas de espacio-tiempo", "La formulacion de Einstein desaparece la nocion del espacio", "La formulacion de Einstein desaparece la gravedad como una fuerza real", "La formulacion de Einstein desaparece la nocion del tiempo", "Si, se usa para calcular la masa de un agujero negro", "Si, para calcular el paso de tiempo en un agujero negro", "No tiene ninguna aplicacion practica", "Si, lo usa los GPS para ser mas precisos"};
        final String[] respuestasEsp = {"Agudas", "Graves", "Esdrujulas", "Sobreesdrujulas", "Haber", "A ver", "Ha ver", "Ninguna de las anteriores", "Imbasion", "Imvasion", "Imvasion", "Inbasion", "La primera es para preguntas y la segunda es para respuestas", "Son las mismas", "La primera es para respuestas y la segunda para preguntas", "Porque esta mal escrito",
            "V", "B", "S", "Ninguna de las anteriores", "M", "S", "N", "C", "O sea", "Oseá", "Ósea", "Osea", "Espanglish ", "Facebookear", "SMS", "Friky",
            "Papahuevos", "Noviamigo", "Selfie", "Googlear", "Espanglish", "Ño", "Whisky", "Papichulo", "Octavio Paz", "Pablo Neruda", "Charles Bukowski", "Mao Nervo", "Los bandidos de Rio Frio", "El periquillo de sarniento", "Astucia, el jefe de los Hermanos de la Hoja", "Pedro Paramo"};
        final String[] respuestasHUni = {"Francia", "Estados Unidos de America", "Inglaterra", "Italia", "Inglaterra", "Estados Unidos de America", "Argentina", "Canada", "Primera Guerra Mundial", "Guerra Fria", "Segunda Guerra Mundial", "Guerra de Vietnam", "Mason", "Spencer", "Burton", "Specter",
            "Piramide de Giza", "Jardines Colgantes de Babilonia", "El Coliseo", "La Gran Muralla", "Charles", "Sigmund", "Thomas", "Nikola", "1959", "1967", "1976", "1983", "Nelson Mandela", "Martin Luther King Jr.", "Mahatma Gandhi", "Barack Obama",
            "1973", "1974", "1972", "1977", "George Clinton", "John Hancock", "John Alsop", "Thomas Dispuesto", "Harry Truman", "Franklin D. Roosevelt", "John F. Kennedy", "Herbert Hoover", "46", "41", "44", "48"};
        final String[] respuestasGeo = {"Rio Nilo", "Rio Misisipi", "Rio Danubio", "Rio Amazonas", "Russia", "China", "Antartida", "Estados Unidos de America", "Lago Uron", "Lago Michigan", "Mar Caspio", "Lago Superior", "Monte Everest", "Monte Tai", "Monte Fuji", "Monte Shasta",
            "Monte Vesubio", "Popocatepetl", "Kilauea", "Monte Merapi", "Atlántico norte, en el Caribe y en nororiental del Pacífico", "Pacífico noroccidental", "Suroriental del océano Índico o en el Pacífico suroeste", "Zona suroccidental del oceano Índico", "America", "Asia", "Antartida", "Africa", "Vancouver", "Ontario", "Quebec", "Ottawa",
            "Nueva Delhi", "Bombay", "Agra", "Chennai", "Caldera de Yellowstone", "Etna", "Mauna Loa", "Monte Vesubio", "Cataratas del Niágara", "Cataratas del Iguazú", "Cataratas Victoria", "Salto Ángel", "50 metros", "57 metros", "100 metros", "61 metros"};
        final String[] respuestasIng = {"a", "an", "the", "a lot of", "Indu", "Indú", "Hindu", "Indian", "Grill", "Hydrate", "Season", "Soften", "Banana", "Apple", "Orange", "Pineapple",
            "Someone who is evil", "An object that damages someone", "The action of killing an animal", "That has a bad smell", "Cut", "Sliced", "Grated", "Prepared", "Season", "Brush", "Soften", "Bake", "Garlic Teeth", "Garlic Clove", "Garlic Fruit", "Garlic Seed",
            "Something that's amazing", "Not interested in something or someone", "Feeling betrayed", "Something or someone who is foreign", "Difficult", "Bumpy", "Lonesome", "Powerful", "Handkerchief", "Neckerchief", "Paper Towel", "Napkin", "To connect electronic devices", "To give maintenance to a service", "To provide a service", "To make and mantain a connection"};
        final String[] respuestasQui = {"Atomo", "Molecula", "Materia", "Masa", "5", "8", "9", "7", "24", "18", "32", "8", "Ley de Conservacion de la energia", "Ley de Conservacion de la materia", "Ley de Bohr", "Ley de Boyle-Mariotte",
            "Alcoholes", "Acidos", "Oxidos", "Bases", "Mendeleiev", "Dalton", "Lavoissier", "Rutherford", "Alcalinos", "Sales", "Isoboros", "Isotopos", "Uraniio 238", "Carbono 14", "Plomo 236", "Potasio 18",
            "Solvente", "Emulsion", "Soluto", "Suspension", "0 grados Centigrados", "10 grados Centigrados", "4 grados Centigrados", "5 grados Centigrados", "Sol", "Espumas", "Emulsiones solidas", "Emulsiones liquidas", "Klaus", "Kekulé", "Baeyer", "Thiele"};
        final String[] respuestasBio = {"Unidad fundamental de la vida", "Las particulas de menor tamaño posible", "Son particulas pequeñas de vida en forma de triangulo", "Particula pequeña que causa el cancer", "Vegetales y Animales", "Unicelulares y Pluricelulares", "Vivas y Muertas", "Procariotas y Eucariotas", "Creacion de Celulas", "Que se ha elimindao toda forma de vida", "Eliminar solo las bacterias o virus en una area abierta", "Cuando una celula guarda su informacion de forma de ADN", "Es una cadena de acidos nucleicos", "Provoca un cambio en la secuencia de un gen", "Unidad fundamental de la vida", "Es una cadena de aminoacidos",
            "Louis Pasteur", "Robert Koch", "Charles Darwin", "Alexander Fleming", "Charles Lyell", "Russel Wallace", "Jean-Baptiste Lamarck", "Thomas Mathus", "Atomo", "Microbio", "Celula", "Particulas sub-atomica", "Seres Humanos", "Animales", "Plantas y Flores", "Bacteria y Archea",
            "Se necesita una vida previa para generar vida", "Dice que existen las mutaciones", "Explica la evolucion de los seres vivos", "Explica la creacion de las celulas", "El nacimiento de nuevas celulas", "Incremento de la cantidad o el numero de proteinas de forma rapida", "Es la muerte celular", "Descomposicion de una proteina", "Inglaterra", "Alemania", "Republica Checa", "Suiza", "Plantas conocidas conmunmente como verduras", "Planta que da un fruto", "Planta Medicinal", "Planta con Flores"};
        final String[] respuestasHMex = {"Mexica", "Maya", "Olmeca", "Totonaca", "1521", "1518", "1527", "1520", "Nezahualcoyotl", "Cuitlahuac", "Moctezuma", "Cuauhtémoc", "Benito Juarez", "Maximiliano de Habsburgo", "Guadalupe Victoria", "Vicente Guerrero",
            "1953", "1949", "1956", "1955", "1929", "1931", "1935", "1919", "1929", "1935", "1936", "1928", "35", "31", "36", "30",
            "Francisco Arambula Arango", "Jose Trinidad Torres Villa", "Jose Doroteo Arango Arambula", "Francisco Trinidad Villa", "1970", "1973", "1964", "1969", "29 de Septiembre de 1934", "2 de Agosto de 1904", "21 de Diciembre de 1910", "6 de Julio de 1917", "Partido Nacional Revolucionario", "Partido de Accion Revolucionaria", "Partido de la Revolucion Mexicana", "Partido Liberal Mexicano"};
        final String[] respuestasAstro = {"Saturno", "Jupiter", "Mercurio", "Marte", "Messier 74", "Ojo Negro", "Renacuajo", "Via Lactea", "Planetoide", "Satelite", "Planeta", "Estrella", "Planetoide", "Satelite", "Planeta", "Estrella",
            "Marte", "Jupiter", "Mercurio", "Venus", "Stephen Hawking", "Isaac Newton", "Nikola Tesla", "Albert Einstein", "3x10^5 km/s", "3×10^6 m/s", "3000 km/s", "3×10^5 m/s", "Wolf359", "Sol", "Alfa Centauri", "Barnard",
            "Supernova", "Nebulosa", "Muerte Estelar", "Big Ban", "Satelite", "Estrella", "Meteoro", "Asteroide", "Pieza de polvo o piedra que golpea la atmósfera de la Tierra", "Estrella que se prende debido a explosiones dentro de su nucleo", "Estrella blanca en movimiento debido a un fenómeno astronómico", "Cuerpo Celeste que emite fuego al acercarse al sol", "Agujero Negro", "Tormenta Astronomica", "Tormenta Espacial", "Huracan Estelar"};
        final String[] respuestasEnt = {"1980", "1993", "1975", "1977", "The Jackson Five", "Backstreet Boys", "NSYNC", "The Jackson's Band", "Rogue One: Una Historia de Star Wars", "Buscando a Dory", "Captain America: Civil War", "Zootopia", "George Lucas", "Steven Spielberg", "Martin Scorsese", "Quentin Tarantino",
            "Pac-Man", "Mario Bros", "ET el Extraterrestre", "Crash Bandicoot", "Futbol Americano", "Baloncesto", "Futbol Soccer", "Voleibol", "Los Juegos del Hambre", "Harry Potter", "Divergente", "El señor de los anillos", "Thriller - Michael Jackson", "Back in Black - AC/DC", "Love me do - The Beatles", "The Bodyguard - Whitney Houston",
            "Charles Chaplin", "Richard Barthelmess", "Warner Baxter", "Emil Jannings", "1960", "1967", "1972", "1988", "Los Simpson", "I love Lucy", "Breaking Bad", "Friends", "William Kennedy Dickson", "William Friese-Greene", "Louis le Prince", "Eadweard Muybridge"};
        final String[] respuestasArte = {"Charles Dickens", "William Wordsworth", "William Shakespeare", "Miguel de Cervantes", "Miguel Ángel", "Rafael Sanzio", "Vincent van Gogh", "Leonardo da Vinci", "Frida Kahlo", "Georgia O'Keeffe", "Mary Cassatt", "Artemisia Gentileschi", "Diego Rivera", "Leonardo da Vinci", "Miguel Ángel", "Vincent van Gogh",
            "Pablo Ruiz Picasso y Georges Braque", "Vincent van Gogh y Leonardo da Vinci", "Miguel Ángel y Pablo Ruiz Picasso", "Georges Braque y Leonardo da Vinci", "Frida Kahlo", "María Magdalena", "Mona Lisa", "Tina Modotti", "Leonardo da Vinci", "Salvador Dalí", "Pablo Ruiz Picasso", "Diego Rivera", "Barroco", "Renacentista", "Romaticismo", "Clasicismo",
            "Por su tamaño", "Por su diseño renacentista", "Hasta la fecha no se termina", "Está abandonado desde el siglo XVIII", "No.12", "No.17", "No.14", "No.5", "Barroco", "Renacentista", "Romanticismo", "Luminismo", "Christopher Marlowe", "Oscar Wilde", "William Shakespeare", "Jane Austen"};
        final String[] respuestasTec = {"Microprocesador", "Tarjeta Madre", "Tarjeta de Memoria", "Mouse", "Bill Gates", "Hewlett Packard", "Carlos Slim", "Steve Jobs", "Palm OS", "iOS", "Unix", "Firefox OS", "Silicio", "Plastico", "Fibra Optica", "Vidrio",
            "London", "Sydney", "California", "Miami", "128 Gb", "1 Tb", "2 Tb", "3 Tb", "Robert kahn/ Vinton Cerf", "Bill Gates/ Steve Jobs", "Charles Babbage/ Howard H. Aiken", "Charles Babbage/ Vinton Cerf", "IBM", "Bill Moggridge", "Alan Kay", "Steve Jobs",
            "Bill Gates", "Steve Jobs", "Alan Kay", "John Tablet", "Steve Jobs", "Samsung", "Sony", "Martin Cooper", "5.3 GHz", "4.2 GHz", "4.5 GHz", "5.0 GHz", "64 Gbps", "87 Gbps", "97 Gbps", "91 Gbps"};
        final int[] respuestascorrectas = {2, 1, 4, 3, 1, 4, 2, 4, 1, 3, 4, 2, 4, 4, 3, 2, 1, 2, 3, 4, 1, 4, 3, 4, 1, 2, 3, 3, 2, 3, 4, 1, 1, 3, 2, 2, 1, 2, 3, 2, 1, 4, 3, 1, 1, 2, 1, 1, 4, 1, 3, 1, 3, 2, 2, 4, 1, 3, 4, 4, 1, 3, 4, 2, 4, 3, 1, 2, 2, 3, 1, 4, 1, 4, 3, 2, 3, 2, 4, 2, 4, 3, 1, 2, 1, 4, 2, 4, 1, 2, 3, 4, 1, 3, 3, 4, 3, 1, 4, 3, 1, 1, 3, 2, 3, 4, 1, 1, 1, 4, 2, 4, 1, 4, 1, 3, 1, 2, 1, 4,
            4, 1, 3, 2, 3, 3, 2, 1, 4, 3, 2, 3, 3, 4, 1, 3, 1, 3, 2, 2, 3, 3, 1, 3, 4, 4, 3, 1, 3, 3, 1, 2, 3, 4, 2, 4};
        int respuestas = 0;
        int dificultad = 1;
        if (question.readRandomPregunta("Matemáticas", 1) == null) {
            for (int i = 0; i < 12; i++) {
                if (i == 4) {
                    dificultad = 2;
                } else {
                    if (i == 8) {
                        dificultad = 3;
                    }
                }
                Pregunta pregunta = new Pregunta();
                pregunta = new Pregunta(0, preguntasMate[i], respuestasMate[respuestas], respuestasMate[respuestas + 1], respuestasMate[respuestas + 2], respuestasMate[respuestas + 3], respuestascorrectas[i], dificultad, "Matemáticas", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasFis[i], respuestasFis[respuestas], respuestasFis[respuestas + 1], respuestasFis[respuestas + 2], respuestasFis[respuestas + 3], respuestascorrectas[i + 12], dificultad, "Física", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasEsp[i], respuestasEsp[respuestas], respuestasEsp[respuestas + 1], respuestasEsp[respuestas + 2], respuestasEsp[respuestas + 3], respuestascorrectas[i + 24], dificultad, "Español", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasHUni[i], respuestasHUni[respuestas], respuestasHUni[respuestas + 1], respuestasHUni[respuestas + 2], respuestasHUni[respuestas + 3], respuestascorrectas[i + 36], dificultad, "Historia Universal", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasGeo[i], respuestasGeo[respuestas], respuestasGeo[respuestas + 1], respuestasGeo[respuestas + 2], respuestasGeo[respuestas + 3], respuestascorrectas[i + 48], dificultad, "Geografía", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasIng[i], respuestasIng[respuestas], respuestasIng[respuestas + 1], respuestasIng[respuestas + 2], respuestasIng[respuestas + 3], respuestascorrectas[i + 60], dificultad, "Inglés", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasQui[i], respuestasQui[respuestas], respuestasQui[respuestas + 1], respuestasQui[respuestas + 2], respuestasQui[respuestas + 3], respuestascorrectas[i + 72], dificultad, "Química", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasBio[i], respuestasBio[respuestas], respuestasBio[respuestas + 1], respuestasBio[respuestas + 2], respuestasBio[respuestas + 3], respuestascorrectas[i + 84], dificultad, "Biología", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasHMex[i], respuestasHMex[respuestas], respuestasHMex[respuestas + 1], respuestasHMex[respuestas + 2], respuestasHMex[respuestas + 3], respuestascorrectas[i + 96], dificultad, "Historia de México", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasAstro[i], respuestasAstro[respuestas], respuestasAstro[respuestas + 1], respuestasAstro[respuestas + 2], respuestasAstro[respuestas + 3], respuestascorrectas[i + 108], dificultad, "Astronomía", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasEnt[i], respuestasEnt[respuestas], respuestasEnt[respuestas + 1], respuestasEnt[respuestas + 2], respuestasEnt[respuestas + 3], respuestascorrectas[i + 120], dificultad, "Entretenimiento", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasArte[i], respuestasArte[respuestas], respuestasArte[respuestas + 1], respuestasArte[respuestas + 2], respuestasArte[respuestas + 3], respuestascorrectas[i + 132], dificultad, "Arte", false);
                question.createPregunta(pregunta);
                pregunta = new Pregunta(0, preguntasTec[i], respuestasTec[respuestas], respuestasTec[respuestas + 1], respuestasTec[respuestas + 2], respuestasTec[respuestas + 3], respuestascorrectas[i + 144], dificultad, "Tecnología", false);
                question.createPregunta(pregunta);
                respuestas += 4;
            }
        }
    }

    /**
     * Creates FAQs if not existis
     */
    public void createFaqs() {

        final String[] pregunta = {"¿Cómo recupero mi contraseña?",
            "¿Cómo reviso mis datos de juego?",
            "¿Por qué cuando creo un usuario o inicio sesión sigo en la página de origen?",
            "¿El área y la dificultad seleccionada es para todo el juego?",
            "¿Qué se debe hacer después de seleccionar el área y dificultad?",
            "¿Tiene algún costo jugar?",
            "¿Se tiene que comprar algún complemento para el juego?",
            "¿Cómo jugar SpaceRide?",
            "¿Mis datos están protegidos?",
            "¿Ambos jugadores deben estar registrados para poder jugar?",
            "¿Puedo cambiar la configuración inicial de los botones?",
            "¿El juego se guardará si la partida es interrumpida?",
            "¿Puedo jugarlo en mi dispositivo móvil?",
            "¿Cuánto tiempo tengo para responder cada pregunta?",
            "¿Necesito algún software adicional para jugar SpaceRide?",
            "¿Para qué usan mis datos de juego?",
            "¿Cuántos disparos puedo realizar?",
            "¿Hay vidas adicionales?",
            "¿Se puede juagar a control remoto?",
            "¿Hay más juegos como SpaceRide?",
            "¿Cuáles son los requerimientos mínimos para SpaceRide"};
        final String[] respuesta = {"Intente ingresar con la contraseña que recuerde y si tu nombre de usuario es correcto será redireccionado a una página de recuperción de contraseña.",
            "Inicie sesión desde la página de inicio y se mostrará su perfil con sus datos de juego.",
            "Si ocurre cuando crea un usuario es debido a que el usuario ya exitse; si ocurre al iniciar sesión el usuario que ha ingresado no está registrado.",
            "No, después de responder alguna pregunta tendrá oportunidad de seleccionar de nuevo el área y la dificultad.",
            "Dejar en espera la página web y jugar spaceride en la aplicación",
            "No, jugar es gratis, si estás interesado en la compra del desarrollo contáctanos al correo rushtechnologiessadecv@gmail.com.",
            "No, jugar es completamente gratuito al igual que las actualizaciones y las áreas disponibles para el juego.",
            "SpaceRide es un juego para 2 jugadores, cada jugador tiene 3 vidas deberá controlar una nave y acertar los disparos en la nave rival para ganar, al ser impactado se presentará una pregunta, en la página web, del área y dificultad seleccionada, al ser respondidas correctamente no se perderá ninguna vida, el juego terminará cuando algún jugador pierda las 3 vidas.",
            "Sí, al momento de registrarse la contraseña es cifrada y el correo no es público para los demás usuarios.",
            "Sí, ambos jugadores deben estar registrados e iniciar sesión poder jugar en la aplicación",
            "No, sólo se podrá jugar con la configuración de botones mostrada en el inicio de sesión.",
            "Actualmente el juego no cuenta con un sistema de guardado en caso de que la partida no se finalice y se desee continuar más tarde. ",
            "Por el momento la aplicación no está disponible en teléfonos móviles, pero se piensa implementar una versión para dispositivos Android.",
            "Después de ser impactado se contará con 1 minuto para responder cualquier pregunta sin importar a que área o dificultad pertenezca.",
            "Sólo se neceista instalar el juego y que los usuarios cuenten con una cuenta para jugar.",
            "Los datos de juego decada usuario son alamcenados para hacer un análisis estadístico de las áreas en las que más se quivocan y así mejoren en dicha área.",
            "Los disparos son ilimitados al momento de jugar.", "No, cada jugador cuenta sólo con 3 vidas.",
            "No, por el mometno sólo se puede jugar con la configuración de teclas prestablecidas.",
            "Por el momento RushTechnlogies sólo se ha enfocado en el desarrollo de SpaceRide como juego y LegalAdvice como aplicación social",
            "Navegador web, espacio disponible de 50 MB, 2GB de memoria RAM, sistema operativo Windows o MACOS"};
        final String[] tema = {"Cuenta", "Juego", "Cuenta", "Juego", "Juego", "Juego", "Juego", "Juego", "Seguridad", "Juego", "Juego", "Juego", "Juego", "Juego", "Juego", "Seguridad", "Juego", "Juego", "Juego", "Juego", "Requerimientos"};
        if (faq.readFaq(1) == null) {
            for (int i = 0; i < pregunta.length; i++) {
                Faq faquestion = new Faq(0, pregunta[i], respuesta[i], tema[i], 1, true, 1, 1);
                faq.createFaq(faquestion);
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
        //processRequest(request, response);
        response.sendRedirect(index);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Space Ride";
    }// </editor-fold>

}
