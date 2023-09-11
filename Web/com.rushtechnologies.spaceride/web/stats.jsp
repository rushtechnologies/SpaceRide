<%-- 
    Document   : stats
    Created on : 7/11/2018, 08:27:24 AM
    Author     : CARLOSHG
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true" import="java.util.List, com.rushtechnologies.spaceride.Models.Faq, com.rushtechnologies.spaceride.ImpDAO.ImpFaq, com.rushtechnologies.spaceride.DAO.iFaq, com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.DAO.iUsuario, com.rushtechnologies.spaceride.DAO.iArea, com.rushtechnologies.spaceride.ImpDAO.ImpArea, com.rushtechnologies.spaceride.Models.Area, com.rushtechnologies.spaceride.Controller.Controller, com.rushtechnologies.spaceride.Models.Chat, com.rushtechnologies.spaceride.DAO.iChat, com.rushtechnologies.spaceride.ImpDAO.ImpChat, com.rushtechnologies.spaceride.DAO.iEventReport, com.rushtechnologies.spaceride.DAO.iMaintenanceReport, com.rushtechnologies.spaceride.ImpDAO.ImpEventReport, com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport, com.rushtechnologies.spaceride.Models.EventReport, com.rushtechnologies.spaceride.Models.MaintenanceReport, com.rushtechnologies.spaceride.Encrypt.Encrypt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Perfil SpaceRide</title>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="description" content="The game your brain love."/>
        <meta name="mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
        <meta name="msapplication-TileColor" content="#3372DF"/>
        <meta name="apple-mobile-web-app-title" content="Space Ride"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="apple-touch-icon-precomposed" href="images/icono.png">
        <link rel="shortcut icon" href="images/icono.png">
        <link rel="icon" sizes="192x192" href="images/icono.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-red.min.css"/>
        <link rel="stylesheet" href="css/styles_stats.css">
    </head>
    <body onload="nobackbutton()">
        <%
            HttpSession sesion = request.getSession();
            int id = 0;
            if (sesion != null) {
                id = Integer.parseInt(sesion.getAttribute("id").toString());
            } else {
                response.sendRedirect("http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/");
            }
            iUsuario user = new ImpUsuario();
            String nombre = user.readUser(id).getU_nombre();
            String correo = user.readUser(id).getU_correo();
        %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                    <div class="mdl-layout-spacer"></div>
                    <nav class="mdl-navigation mdl-layout--large-screen-only">
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="#Inicio" onclick="inicio()">Perfil</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="#Actualizar" onclick="actualizar()">Actualizar información</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="">Descarga</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="<%="http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/exit.jsp"%>">Cerrar Sesión</a>
                    </nav>
                </div>
            </header>
            <div class="mdl-layout__drawer navigate">
                <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                <nav class="mdl-navigation">
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="#Inicio" onclick="inicio()">Perfil</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="#Actualizar" onclick="actualizar()">Actualizar información</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="">Descarga</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="<%="http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/exit.jsp"%>">Cerrar Sesión</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html">Página de Inicio</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="faq.jsp">Soporte</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="about.html">Acerca de</a>
                </nav>
            </div>
            <main class="mdl-layout__content">
                <section id="inicio">
                    <div class="page-content">
                        <h2><%=nombre%></h2>
                        <h3>Datos de juego</h3>
                        <%      iArea area = new ImpArea();
                            Area ar = area.readArea(id);
                            if (ar.getArea_max() != 0) {
                                out.println("<h5>Victorias vs. Derrotas</h5><div class='piechart_div' id='piechart_div'></div><br/><h5>El área de conocimientos en la que más te equivocas es: " + Controller.temas[ar.getArea_max_id()] + ", con un total de " + ar.getArea_max() + " equivocaciones. Te sugerimos estudiar más sobre los temas que integra ésta área de conocimientos</h5><br/>");
                                out.println("<table class='mdl-data-table mdl-js-data-table mdl-shadow--2dp'><thead><tr><th class='mdl-data-table__cell--non-numeric'>Área de conocimientos</th><th>Número de equivocaciones</th></tr></thead><tbody>");
                                for (int i = 1; i < (Controller.temas.length - 1); i++) {
                                    out.println("<tr><td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric'>" + Controller.temas[i] + "</td>");
                                    switch (i) {
                                        case 1:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_arte() + "</td>");
                                            break;
                                        case 2:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_astro() + "</td>");
                                            break;
                                        case 3:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_bio() + "</td>");
                                            break;
                                        case 4:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_ent() + "</td>");
                                            break;
                                        case 5:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_esp() + "</td>");
                                            break;
                                        case 6:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_fis() + "</td>");
                                            break;
                                        case 7:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_geo() + "</td>");
                                            break;
                                        case 8:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_hmex() + "</td>");
                                            break;
                                        case 9:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_huni() + "</td>");
                                            break;
                                        case 10:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_ing() + "</td>");
                                            break;
                                        case 11:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_mate() + "</td>");
                                            break;
                                        case 12:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_qui() + "</td>");
                                            break;
                                        case 13:
                                            out.println("<td class='mdl-js-ripple-effect'>" + ar.getArea_tec() + "</td>");
                                            break;
                                    }
                                }
                                out.println("</tbody></table><br/><br/>");
                            } else {
                                out.println("<h4>¡Bienvenido a SpaceRide! Empieza a jugar para generar tus datos de juego.</h4><br/><br/>");
                            }

                        %>
                    </div>
                </section>
                <%      iEventReport er = new ImpEventReport();
                    iMaintenanceReport mr = new ImpMaintenanceReport();
                    Encrypt encrypt = new Encrypt();
                    if (user.readUser(id).getU_type() != 0) {
                        String tipo = "";
                        switch (user.readUser(id).getU_type()) {
                            case 1:
                                tipo = "Operador";
                                break;
                            case 2:
                                tipo = "Ingeniero de Soporte";
                                break;
                            case 3:
                                tipo = "Desarrollador";
                                break;
                            case 4:
                                tipo = "Gerente";
                                break;
                        }
                        out.println("<div id = 'botonans'><br/><h2>Soporte: " + tipo);
                        if (user.readUser(id).getU_type() == 1) {
                            iFaq faq = new ImpFaq();
                            out.println("</h2><h3>Preguntas Frecuentes</h3>");
                            out.println("<a class='mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' href='faq.jsp' >Registrar Preguntas Frecuentes</a><br/><br/>");
                            out.println("<a class='mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' href='#admin' onclick='admin()'>Gestionar Preguntas Frecuentes</a><br/><br/>");
                            out.println("<br/><h3>Borrar Preguntas Frecuentes<h3/><form action = 'Controller' method = 'post'>");
                            List<Faq> faqs = faq.readAllFaq();
                            if (!faqs.isEmpty()) {
                                for (Faq f : faqs) {
                                    out.println("<button type = 'submit' name = 'submitsr' class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' value = 'Deletefq" + encrypt.encryptid(f.getFaq_id()) + "' onclick = 'return Fansc();'>Borrar " + f.getFaq_pregunta() + "</button><br/>");
                                }
                            }
                            iChat c = new ImpChat();
                            List<Chat> chats = c.readSAllChats(id);
                            if (!chats.isEmpty()) {
                                out.println("<br/><h3>Chat de Soporte</h3><form action = 'Controller' method = 'post'>");
                                for (Chat chat : chats) {
                                    out.println("<button type = 'submit' name = 'submitsr' class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' value = 'Chat" + encrypt.encryptid(chat.getChat_U_Id()) + "'>Responder a chat de soporte de " + user.readUser(chat.getChat_U_Id()).getU_nombre() + "</button><br/><br/>");
                                }
                                out.println("</form>");
                            } else {
                                out.println("<br/><h3>No se han encontrado chats</h3>");
                            }
                            out.println("<br/><br/><h3>Reportes de Eventos</h3>");
                            out.println("<a class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' onclick = 'er();'>Registrar Reporte de Eventos</a><br/><br/>");
                            List<EventReport> OperatorEventReports = er.readAllER();
                            if (!OperatorEventReports.isEmpty()) {
                                out.println("<form action = 'Controller' method = 'post'>");
                                for (EventReport eventReport : OperatorEventReports) {
                                    if (eventReport.getER_Status().equals("Pendiente")) {
                                        out.println("<button type = 'submit' name = 'submitsr' class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' value = 'BorrarRE" + encrypt.encryptid(eventReport.getER_Id()) + "'>Borrar el reporte " + eventReport.getER_Description() + "</button>");
                                    }
                                    if (eventReport.getER_Status().equals("Solucionado")) {
                                        out.println("<button type = 'submit' name = 'submitsr' class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' value = 'Createqr" + encrypt.encryptid(eventReport.getER_Id()) + "'>Registrar pregunta frecuente del reporte " + eventReport.getER_Description() + "</button><br/><br/>");
                                    }
                                }
                                out.println("</form>");
                            } else {
                                out.println("<h3>No se han encontrado eventos</h3>");
                            }
                            out.println("<br/><br/></div>");
                            out.println("<section id = 'er' class = 'er'><br/><br/><br/>");
                            out.println("<dialog class='dialogsr mdl-dialog' id='dialoger'><h4 class='mdl-dialog__title'>Registrar Reportes de Evento</h4>");
                            out.println("<div class='mdl-dialog__content'>");
                            if (!chats.isEmpty()) {
                                for (Chat chat : chats) {
                                    List<EventReport> eventReports = er.readERUser(chat.getChat_U_Id());
                                    if (!eventReports.isEmpty()) {
                                        for (EventReport eventReport : eventReports) {
                                            if (!eventReport.getER_Transcript().equals(chat.getChat_U_Msgs())) {
                                                out.println("<form action = 'Controller' method = 'post'>");
                                                out.println("<p id = 'msj'></p>");
                                                out.println("<h4>Reportar evento de " + user.readUser(chat.getChat_U_Id()).getU_nombre() + "</h4>");
                                                out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='description' id='description' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='description'>Descripción del evento</label></div><div class='mdl-tooltip mdl-tooltip--large' for='description'>Ingrese una breve descripción del evento.</div>");
                                                //out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label'><input class='mdl-textfield__input' type='text' name='category' id='category' onkeypress='alpha()'/><label class='mdl-textfield__label' for='category'>Categoría</label></div><div class='mdl-tooltip mdl-tooltip--large' for='category'>Ingrese la categoria del reporte de evento.</div><br/><br/>");
                                                out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Reportar" + encrypt.encryptid(chat.getChat_U_Id()) + "' onclick='return Frep();'>Reportar Evento</button></div>");
                                                out.println("</form><br/><br/><br/>");
                                            } else {
                                                out.println("<h3>No se han encontrado eventos</h3>");
                                            }
                                        }
                                    } else {
                                        out.println("<form action = 'Controller' method = 'post'>");
                                        out.println("<p id = 'msj'></p>");
                                        out.println("<h4>Reportar evento de " + user.readUser(chat.getChat_U_Id()).getU_nombre() + "</h4>");
                                        out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='description' id='description' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='description'>Descripción del evento</label></div><div class='mdl-tooltip mdl-tooltip--large' for='description'>Ingrese una breve descripción del evento.</div>");
                                        //out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label'><input class='mdl-textfield__input' type='text' name='category' id='category' onkeypress='alpha()'/><label class='mdl-textfield__label' for='category'>Categoría</label></div><div class='mdl-tooltip mdl-tooltip--large' for='category'>Ingrese la categoria del reporte de evento.</div><br/><br/>");
                                        out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Reportar" + encrypt.encryptid(chat.getChat_U_Id()) + "' onclick='return Frep();'>Reportar Evento</button></div>");
                                        out.println("</form><br/><br/><br/>");
                                    }
                                }
                            } else {
                                out.println("<h3>No se han encontrado eventos</h3>");
                            }
                            out.println("</dialog><br/><br/></section><br/><br/><br/><br/>");
                            out.println("<section id = 'admin' class = 'admin'><br/><br/><br/>");
                            out.println("<dialog class='dialogsr mdl-dialog' id='dialoga'><h4 class='mdl-dialog__title'>Gestionar Preguntas Frecuentes</h4>");
                            out.println("<div class='mdl-dialog__content'>");
                            faqs = faq.readAllFaqAdmin();
                            if (!faqs.isEmpty()) {
                                for (Faq f : faqs) {
                                    out.println("<form action = 'Controller' method = 'post'>");
                                    out.println("<h4>Pregunta: " + f.getFaq_pregunta() + "</h4>");
                                    out.println("<p id = 'msj'></p>");
                                    out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='answer' id='answer' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='answer'>Respuesta</label></div><div class='mdl-tooltip mdl-tooltip--large' for='answer'>Ingrese tu duda o pregunta.</div>");
                                    out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='ResponderFaq" + f.getFaq_id() + "' onclick='return Fans();'>Responder</button><br/><br/><br/><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent' value='DescartarFaq" + f.getFaq_id() + "' onclick='return Fansc();'>Descartar</button></div>");
                                    out.println("</form><br/><br/><br/>");
                                }
                            } else {
                                out.println("<h4>No se han resgistrado nuevas preguntas frecuentes</h4>");
                            }
                            out.println("</dialog><br/><br/></section><br/><br/><br/><br/>");
                        } else {
                            if (user.readUser(id).getU_type() == 2) {
                                out.println("</h2><h3>Reportes de Evento</h3>");
                                out.println("<a class='mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' href='#admin' onclick='admin()'>Responder Reportes de Evento en Revisión</a><br/><br/></div>");
                                out.println("<br/><br/><br/>");
                                out.println("<section id = 'admin' class = 'admin'><br/><br/><br/>");
                                out.println("<dialog class='dialogsr mdl-dialog' id='dialoga'><h4 class='mdl-dialog__title'>Responder Reportes de Evento en Revisión</h4>");
                                out.println("<div class='mdl-dialog__content'>");
                                int empty = 0;
                                List<EventReport> eventReports = er.readERSupport(3);
                                if (!eventReports.isEmpty()) {
                                    for (EventReport eventReport : eventReports) {
                                        if (eventReport.getER_Status().equals("En revisión")) {
                                            out.println("<form action = 'Controller' method = 'post'>");
                                            out.println("<h4>Evento: " + eventReport.getER_Description() + "</h4>");
                                            out.println("<p id = 'msj'></p>");
                                            out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='answer' id='answer' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='answer'>Solución</label></div><div class='mdl-tooltip mdl-tooltip--large' for='answer'>Ingrese la solución al reporte de soporte.</div>");
                                            out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Resolver" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick='return Fsol();'>Solucionar</button><br/><br/><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent' value='Creamrer" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick='return Fsol();'>Reportar a mantenimiento</button></div>");
                                            out.println("</form>");
                                            out.println("<br/>");
                                        } else {
                                            ++empty;
                                        }
                                    }
                                    if (empty > 0) {
                                        out.println("<h4>No se han encontrado eventos pendintes de solución</h4>");
                                    }
                                } else {
                                    out.println("<h4>No se han encontrado eventos pendintes de solución</h4>");
                                }
                                for (EventReport eventReport : eventReports) {
                                    if (eventReport.getER_Status().equals("Mantenimiento")) {
                                        out.println("<form action = 'Controller' method = 'post'>");
                                        out.println("<h4>Evento: " + eventReport.getER_Description() + "</h4>");
                                        out.println("<p id = 'msj'></p>");
                                        out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='answer' id='answer' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='answer'>Solución</label></div><div class='mdl-tooltip mdl-tooltip--large' for='answer'>Ingrese la solución al reporte de soporte.</div>");
                                        out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Resolver" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick='return Fsol();'>Solucionar</button></div>");
                                        out.println("</form>");
                                        out.println("<br/>");
                                    } else {
                                        ++empty;
                                    }
                                }
                                if (empty > 0) {
                                    out.println("<h4>No se han encontrado eventos resueltos en mantenimineto</h4>");
                                }
                                out.println("</dialog><br/><br/></section><br/><br/><br/><br/>");
                            } else {
                                if (user.readUser(id).getU_type() == 3) {
                                    out.println("</h2><h3>Reportes de Mantenimiento</h3>");
                                    out.println("<a class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' href='#admin' onclick='admin()'>Registar Reporte de  Mantenimiento</a><br/><br/>");
                                    out.println("<a class = 'mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' onclick = 'er();'>Resolver Reportes de Mantenimiento</a><br/><br/>");
                                    out.println("<br/><br/></div>");
                                    out.println("<section id = 'admin' class = 'admin'><br/><br/><br/>");
                                    out.println("<dialog class='dialogsr mdl-dialog' id='dialoga'><h4 class='mdl-dialog__title'>Registar Reporte de  Mantenimiento</h4>");
                                    out.println("<div class='mdl-dialog__content'>");
                                    out.println("<form action = 'Controller' method = 'post'>");
                                    out.println("<p id = 'msj'></p>");
                                    out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label'><input class='mdl-textfield__input' type='text' name='module' id='module' onkeypress='alpha()'/><label class='mdl-textfield__label' for='module'>Módulo</label></div><div class='mdl-tooltip mdl-tooltip--large' for='module'>Ingrese el módulo al que se le aplicará el mantenimiento.</div><br/><br/>");
                                    out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='description' id='description' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='description'>Descripción del mantenimiento</label></div><div class='mdl-tooltip mdl-tooltip--large' for='description'>Ingrese una breve descripción del mantenimiento que se realizará.</div>");
                                    out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='CreateMR" + encrypt.encryptid(3) + "' onclick='return Frem();'>Responder</button><br/></div>");
                                    out.println("</form>");
                                    out.println("<br/>");
                                    out.println("</dialog><br/><br/></section><br/><br/><br/>");
                                    out.println("<section id = 'er' class = 'er'><br/><br/><br/>");
                                    out.println("<dialog class='dialogsr mdl-dialog' id='dialoger'><h4 class='mdl-dialog__title'>Resolver Reportes de Mantenimiento</h4>");
                                    out.println("<div class='mdl-dialog__content'>");
                                    List<MaintenanceReport> maintenanceReports = mr.readDeveloperMR(1);
                                    if (!maintenanceReports.isEmpty()) {
                                        for (MaintenanceReport maintenanceReport : maintenanceReports) {
                                            if (!maintenanceReport.getMR_Status().equals("Desarrollado")) {
                                                out.println("<form action = 'Controller' method = 'post'>");
                                                out.println("<h4>Módulo: " + maintenanceReport.getMR_Module() + "</h4>");
                                                out.println("<h4>Tarea: " + maintenanceReport.getMR_Description() + "</h4>");
                                                out.println("<p id = 'msj'></p>");
                                                out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='answer' id='answer' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='answer'>Solución</label></div><div class='mdl-tooltip mdl-tooltip--large' for='answer'>Ingrese una breve descripción de la resolución del reporte de mantenimiennto.</div>");
                                                out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='UpdateMR" + encrypt.encryptid(3) + "' onclick='return Fsom();'>Responder</button><br/></div>");
                                                out.println("</form>");
                                                out.println("<br/>");
                                            } else {
                                                out.println("<h3>Reporte Desarrollado: " + maintenanceReport.getMR_Solution());
                                            }
                                        }
                                    } else {
                                        out.println("<h3>No se han encontrado reportes de mantenimiento</h3>");
                                    }
                                    out.println("</dialog><br/><br/></section><br/><br/><br/><br/>");
                                } else {
                                    if (user.readUser(id).getU_type() == 4) {
                                        out.println("</h2><h2>Soporte</h2><h3>Reportes de Evento</h3>");
                                        if (!er.readERSupport(4).isEmpty()) {
                                            for (EventReport eventReport : er.readERSupport(4)) {
                                                out.println("<form action = 'Controller' method = 'post'>");
                                                out.println("<h5>Asignar evento " + eventReport.getER_Description() + "</h5>");
                                                out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select'><select id='support' name='support' class='mdl-textfield__input'><option selected></option>");
                                                out.println("<option value='" + encrypt.encryptid(3) + "'>" + user.readUser(3).getU_nombre() + "</option>");
                                                out.println("</select><label for='support' class='mdl-textfield__label'>Responsable de soporte</label></div><div class='mdl-tooltip mdl-tooltip--large' for='support'>Seleccione al responsable de soporte encargado de dar solución al evento reportado.</div>");
                                                out.println("<button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Asignars" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick='return Fasign();'>Asignar</button><br/><br/>");
                                                out.println("</form>");
                                            }
                                        } else {
                                            out.println("<h3>No se han encontrado reportes de rvento pendientes</h3>");
                                        }
                                        List<EventReport> eventReports = er.readAllER();
                                        if (!eventReports.isEmpty()) {
                                            out.println("<h3>Reportes</h3><br/><table class='mdl-data-table mdl-js-data-table mdl-shadow--2dp'><thead><tr><tr><th class='mdl-data-table__cell--non-numeric ertable'>Folio</th><th class='mdl-data-table__cell--non-numeric ertable'>Descripción</th><th  class='mdl-data-table__cell--non-numeric ertable'>Estado</th><th class='mdl-data-table__cell--non-numeric ertable'>Solución</th><th  class='mdl-data-table__cell--non-numeric ertable'>Fecha de revisión</th><th  class='mdl-data-table__cell--non-numeric ertable'>Responsable</th></tr></thead><tbody>");
                                            for (EventReport eventReport : eventReports) {
                                                out.println("<tr><td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + encrypt.encryptid(eventReport.getER_Id()).substring(0, 8) + "</td>");
                                                out.println("<td  class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Description() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Status() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Solution() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Timestamp().toString() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + user.readUser(eventReport.getER_S_Id()).getU_nombre() + "</td>");
                                            }
                                            out.println("</tbody></table><br/><br/>");
                                        }
                                        out.println("<br/><br/><h2>Mantenimiento</h2><h3>Reportes de Mantenimiento</h3>");
                                        out.println("<a class='mdl-button--raised mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent' href='#admin' onclick='admin()'>Registar Reporte de  Mantenimiento</a><br/><br/>");
                                        /*
                                        if (!er.readERSupport(4).isEmpty()) {
                                            for (EventReport eventReport : er.readERSupport(4)) {
                                                out.println("<form action = 'Controller' method = 'post'>");
                                                out.println("<h5>Asignar evento " + eventReport.getER_Description() + "</h5>");
                                                out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select'><select id='support' name='support' class='mdl-textfield__input'><option selected></option>");
                                                out.println("<option value='" + encrypt.encryptid(3) + "'>" + user.readUser(3).getU_nombre() + "</option>");
                                                out.println("</select><label for='support' class='mdl-textfield__label'>Responsable de soporte</label></div><div class='mdl-tooltip mdl-tooltip--large' for='support'>Seleccione al responsable de soporte encargado de dar solución al evento reportado.</div>");
                                                out.println("<button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='Asignars" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick='return Fasign();'>Asignar</button><br/><br/>");
                                                out.println("</form>");
                                            }
                                        } else {
                                            out.println("<h3>No se han encontrado reportes de rvento pendientes</h3>");
                                        }
                                        ESO PERO DE MANTENIMIENTO
                                         */
                                        List<MaintenanceReport> maintenanceReports = mr.readAllMR();
                                        if (!maintenanceReports.isEmpty()) {
                                            out.println("<h3>Reportes</h3><table class='mdl-data-table mdl-js-data-table mdl-shadow--2dp'><thead><tr><tr><th class='mdl-data-table__cell--non-numeric ertable'>Folio</th><th class='mdl-data-table__cell--non-numeric ertable'>Descripción</th><th  class='mdl-data-table__cell--non-numeric ertable'>Estado</th><th class='mdl-data-table__cell--non-numeric ertable'>Solución</th><th  class='mdl-data-table__cell--non-numeric ertable'>Fecha de revisión</th><th  class='mdl-data-table__cell--non-numeric ertable'>Responsable</th></tr></thead><tbody>");
                                            for (MaintenanceReport maintenanceReport : maintenanceReports) {
                                                out.println("<tr><td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + encrypt.encryptid(maintenanceReport.getMR_Id()).substring(0, 8) + "</td>");
                                                out.println("<td  class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + maintenanceReport.getMR_Description() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + maintenanceReport.getMR_Status() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + maintenanceReport.getMR_Solution() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + maintenanceReport.getMR_Timestamp().toString() + "</td>");
                                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + user.readUser(maintenanceReport.getMR_S_Id()).getU_nombre() + "</td>");
                                            }
                                            out.println("</tbody></table><br/><br/>");
                                        } else {
                                            out.println("<h3>No se han encontrado Reportes de Mantenimiento</h3>");
                                        }
                                        out.println("</div><section id = 'admin' class = 'admin'><br/><br/><br/>");
                                        out.println("<dialog class='dialogsr mdl-dialog' id='dialoga'><h4 class='mdl-dialog__title'>Registar Reporte de  Mantenimiento</h4>");
                                        out.println("<div class='mdl-dialog__content'>");
                                        out.println("<form action = 'Controller' method = 'post'>");
                                        out.println("<p id = 'msj'></p>");
                                        out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label'><input class='mdl-textfield__input' type='text' name='module' id='module' onkeypress='alpha()'/><label class='mdl-textfield__label' for='module'>Módulo</label></div><div class='mdl-tooltip mdl-tooltip--large' for='module'>Ingrese el módulo al que se le aplicará el mantenimiento.</div><br/><br/>");
                                        out.println("<div class='mdl-textfield mdl-js-textfield mdl-textfield--floating-label' style = 'width: 90%'><textarea class='mdl-textfield__input' type = 'text' rows='2' name='description' id='description' autocomplete='off' style='margin-top: 0px; margin-bottom: 0px; height: 100px;' onkeypress='alphap()'></textarea><label class='mdl-textfield__label' for='description'>Descripción del mantenimiento</label></div><div class='mdl-tooltip mdl-tooltip--large' for='description'>Ingrese una breve descripción del mantenimiento que se realizará.</div>");
                                        out.println("<div class='mdl-dialog__actions--full-width'><button type='submit' name='submitsr' class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' value='CreateMR" + encrypt.encryptid(3) + "' onclick='return Frem();'>Responder</button></div>");
                                        out.println("</form>");
                                        out.println("<br/>");
                                        out.println("</dialog><br/><br/></section><br/><br/><br/>");
                                    }
                                }
                            }
                        }
                    } else {
                        if (er.readERUser(id).size() != 0) {
                            out.println("<h3>Reportes</h3><br/><table class='mdl-data-table mdl-js-data-table mdl-shadow--2dp'><thead><tr><tr><th class='mdl-data-table__cell--non-numeric ertable'>Folio</th><th class='mdl-data-table__cell--non-numeric ertable'>Descripción</th><th  class='mdl-data-table__cell--non-numeric ertable'>Estado</th><th class='mdl-data-table__cell--non-numeric ertable'>Solución</th><th  class='mdl-data-table__cell--non-numeric ertable'>Validar</th></tr></thead><tbody>");
                            for (EventReport eventReport : er.readERUser(id)) {
                                out.println("<tr><td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + encrypt.encryptid(eventReport.getER_Id()).substring(0, 8) + "</td>");
                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Description() + "</td>");
                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Status() + "</td>");
                                out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>" + eventReport.getER_Solution() + "</td>");
                                if (eventReport.getER_Status().equals("Revisado")) {
                                    out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'><form action = 'Controller' method = 'post'><button name = 'submitsr' class = 'mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary' type = 'submit' value = 'Validare" + encrypt.encryptid(eventReport.getER_Id()) + "' onclick = 'return Fval();'>Validar</button></form></td><tr/>");
                                } else {
                                    out.println("<td class='mdl-js-ripple-effect mdl-data-table__cell--non-numeric ertable'>No disponible</td><tr/>");
                                }
                            }
                            out.println("</tbody></table><br/><br/>");
                        }
                    }
                %>
                <section id="actualizar" class = "actualizar">
                    <div class="page-content">
                        <br/>
                        <dialog class="dialogsr mdl-dialog" id="dialog">
                            <h4 class="mdl-dialog__title">Actualizar Información</h4>
                            <div class="mdl-dialog__content">
                                <table border="0" width="100%" style="border-spacing: 25px">
                                    <tr>
                                        <td width="50%">
                                            <h5>Actualizar datos</h5>
                                            <form action="Controller" method="post">
                                                <b>
                                                    <p class="mdl-color-text--primary-dark" id="msj"></p>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="text" name="user" value="<%=nombre%>" id="user" autocomplete="off" onkeypress="alpha()" pattern="[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]{1,50}$"/>
                                                        <label class="mdl-textfield__label" for="user">Nombre (usuario)</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="user">Ingrese su nuevo nombre de usuario de máximo 50 caracteres.</div>
                                                    <br/>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="email" name="mail" value="<%=correo%>" id="mail" autocomplete="off" onkeypress="email()" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                                                        <label class="mdl-textfield__label" for="mail">Correo electrónico</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="mail">Ingrese su nuevo correo electrónico (xx@xx.xx) para contactarle en caso de ser necesario.</div>
                                                    <br/>
                                                    <br/>
                                                    <p>Ingrese su contraseña para permirtir la actualización de sus datos.</p>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="password" name="pass" id="pass" autocomplete="off" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                                        <label class="mdl-textfield__label" for="pass">Contraseña (mínimo 8 caracteres)</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="pass">Ingrese su contraseña.</div>
                                                </b>
                                                <br/>
                                                <br/>
                                                <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="UpdateD" onclick="return Fact();">Actualizar datos</button>
                                            </form>
                                        </td>
                                        </td>
                                        <td class="mdl-color--primary"></td>
                                        <td>
                                            <h5>Actualizar contraseña</h5>
                                            <form action="Controller" method="post">
                                                <b>
                                                    <p class="mdl-color-text--primary-dark" id="msjp"></p>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="password" name="pass2" id="pass2" autocomplete="off" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                                        <label class="mdl-textfield__label" for="pass2">Contraseña (mínimo 8 caracteres)</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="pass2">Ingrese su contraseña actual.</div>
                                                    <br/>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="password" name="npass" id="npass" autocomplete="off" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                                        <label class="mdl-textfield__label" for="npass">Nueva contraseña (mínimo 8 caracteres)</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="npass">Ingrese su nueva contraseña de mínimo 8 caracteres, se recomienda que incluya caracteres espepciales, máximo 50 caracteres.</div>
                                                    <br/>
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <input class="mdl-textfield__input" type="password" name="npass2" id="npass2" autocomplete="off" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                                        <label class="mdl-textfield__label" for="npass2">Repetir nueva contraseña</label>
                                                    </div>
                                                    <div class="mdl-tooltip mdl-tooltip--large" for="npass2">Repita la nueva contraseña que ingresó previamente.</div>
                                                </b>
                                                <br/>
                                                <br/>
                                                <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="UpdateP" onclick="return Factn();">Actualizar contraseña</button>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="mdl-dialog__actions--full-width"></div>
                        </dialog>
                        <br/>
                        <br/>
                        <br/>
                    </div>
                </section>
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
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/stats.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
        <script type="text/javascript" src="js/updatecp.js"></script>
        <script src="http://www.gstatic.com/charts/loader.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    </body>
</html>