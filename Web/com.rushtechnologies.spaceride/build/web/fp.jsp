<%-- 
    Document   : fp
    Created on : 7/11/2018, 08:27:06 AM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="org.owasp.esapi.ESAPI, com.rushtechnologies.spaceride.DAO.iUsuario, com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.Models.Usuario, com.rushtechnologies.spaceride.Mail.Mail"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="description" content="The game your brain love."/>
        <meta name="mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
        <meta name="msapplication-TileColor" content="#3372DF"/>
        <meta name="apple-mobile-web-app-title" content="Space Ride"/>
        <link rel="apple-touch-icon-precomposed" href="images/icono.png">
        <link rel="shortcut icon" href="images/icono.png">
        <link rel="icon" sizes="192x192" href="images/icono.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-red.min.css"/>
        <link rel="stylesheet" href="css/styles_fp.css">
        <title>¿Olvidó su contraseña?</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            int id = 0;
            String index = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/";
            String stats = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/stats.jsp";
            String fp = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/fp.jsp";
            String referer = request.getHeader("Referer");
            if (sesion != null && sesion.getAttribute("id") != null && Integer.parseInt(sesion.getAttribute("id").toString()) != 0 && referer.contains(index) && !referer.equals("")) {
                id = Integer.parseInt(sesion.getAttribute("id").toString());
            } else {
                response.sendRedirect(index);
            }
            iUsuario user = new ImpUsuario();
            String nombre = ESAPI.encoder().decodeForHTML(user.readUser(id).getU_nombre());
            String correo = ESAPI.encoder().decodeForHTML(user.readUser(id).getU_correo());
            if (request.getParameter("submitsr") != null) {
                if (request.getParameter("submitsr").equals("Recuperar")) {
                    int empty = 0;
                    String correofp = "";
                    if (!request.getParameter("mail").equals("") && request.getParameter("mail") != null) {
                        correofp = ESAPI.encoder().encodeForHTML(request.getParameter("mail"));
                    } else {
                        ++empty;
                    }
                    if (empty > 0) {
                        out.println("<script>invalid();</script>");
                        response.sendRedirect(index);
                    } else {
                        if (ESAPI.encoder().decodeForHTML(correofp).equals(correo)) {
                            String contra = ESAPI.encoder().decodeForHTML(user.readUser(id).getU_contra());
                            String de = "rushtechnologiessadecv@gmail.com";
                            String clave = "Rt2018cecyt9";
                            String asunto = "Space Ride por Rush Technologies, recuperar contraseña";
                            String mensaje = "Hola estimado: " + nombre + "\n" + "\n" + "Se ha recibido su solicitud para recuperar su contraseña y en respuesta," + "\n" + "Su contraseña es: " + contra + " (Obtenida a partir del descifrado de su contraseña ingresada)" + "\n" + "\n" + "No la olvide ;-)" + "\n" + "\n" + "Atte. Rush Tecnologies S.A. de C.V.";
                            Mail mail = new Mail();
                            try {
                                boolean envio = mail.enviarCorreo(de, clave, correo, mensaje, asunto);
                                if (envio) {
                                    out.println("<script>mailpass();</script>");
                                    response.sendRedirect(stats);
                                } else {
                                    out.println("<script>error();</script>");
                                    response.sendRedirect(fp);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            out.println("<script>failedFp();</script>");
                            response.sendRedirect(fp);
                        }
                    }
                } else {
                    response.sendRedirect(index);
                }
            }
        %>
        <div class="mdl-layout mdl-js-layout">
            <main class="mdl-layout__content" style="width: 100%;">
                <dialog class="dialogsr mdl-dialog">
                    <form action="fp.jsp" method="post">
                        <h4 class="mdl-dialog__title"><%=nombre%>, ¿Olvidó su contraseña?</h4>
                        <div class="mdl-dialog__content">
                            <b>
                                <p>Ingrese el correo electrónico con el que registró su usuario. Se le enciará un correo a este mismo con su contraseña</p>
                                <p class="mdl-color-text--primary-dark" id="msj"></p>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 90%">
                                    <input class="mdl-textfield__input" type="email" name="mail" id="mail" autocomplete="off" onkeypress="email()" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                                    <label class="mdl-textfield__label" for="mail">Correo electrónico</label>
                                </div>
                                <div class="mdl-tooltip mdl-tooltip--large" for="mail">Ingrese su correo electrónico (xx@xx.xx) para recuperar su contraseña.</div>
                            </b>
                        </div>
                        <div class="mdl-dialog__actions--full-width">
                            <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="Recuperar" onclick="return Ffp();">Recuperar Contraseña</button>
                            <br/>
                            <br/>
                            <br/>
                            <button type="button" class="mdl-button mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" onclick="location.href = 'index.html'">Cancelar</button>
                        </div>
                    </form>
                </dialog>
            </main>
            <footer class="mdl-mini-footer">
                <div class="mdl-mini-footer--left-section">
                    <ul class="mdl-mini-footer--link-list">
                        <li><a href="index.html">Página de Inicio</a></li>
                        <li><a href="game.html">Jugar</a></li>
                        <li><a href="faq.jsp">Soporte</a></li>
                        <li><a href="about.html">Acerca de</a></li>
                    </ul>
                </div>
                <div class="mdl-mini-footer--right-section">
                    <h6>Space Ride por Rush Techologies</h6>
                </div>
            </footer>
            <div class="mdl-layout__obfuscator"></div>
        </div>
        <script type="text/javascript" src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
        <script type="text/javascript" src="js/fp.js"></script>
    </body>
</html>