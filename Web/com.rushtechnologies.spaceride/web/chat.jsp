<%-- 
    Document   : chat
    Created on : 24/03/2019, 08:34:59 PM
    Author     : CARLOSHG
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="true" import="com.rushtechnologies.spaceride.DAO.iChat, com.rushtechnologies.spaceride.DAO.iUsuario, com.rushtechnologies.spaceride.ImpDAO.ImpChat, com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.Models.Chat, com.rushtechnologies.spaceride.Models.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat de Soporte</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="60">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <link rel="stylesheet" type="text/css" href="https://code.getmdl.io/1.3.0/material.indigo-deep_orange.min.css">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" href="css/styles_chat.css">
    </head>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title"><h3><b>Chat de Soporte</b></h3></span>
                    <div class="mdl-layout-spacer"></div>
                    <nav class="mdl-navigation mdl-layout--large-screen-only">
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="index.html">Página de Inicio</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="faq.jsp">Soporte</a>
                    </nav>
                </div>
            </header>
            <div class="mdl-layout__drawer navigate">
                <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                <nav class="mdl-navigation">
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html">Página de Inicio</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="faq.jsp">Soporte</a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="about.html">Acerca de</a>
                </nav>
            </div>
            <main class="mdl-layout__content">
                <%
                    HttpSession httpSession = request.getSession();
                    final String stats = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/stats.jsp", faqs = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/faq.jsp", index = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/", reload = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/chat.jsp";
                    String timestamp = "";
                    String operatorname = "";
                    String username = "";
                    if (httpSession != null && !httpSession.isNew() && (request.getHeader("Referer").contains(stats) || request.getHeader("Referer").contains(faqs) || request.getHeader("Referer").contains(reload))) {
                        boolean isSupport = false;
                        if (httpSession.getAttribute("isSupport").toString().equals("yes")) {
                            isSupport = true;
                        }
                        int u_id = Integer.parseInt(httpSession.getAttribute("u_id").toString()), s_msgscount = 1, u_msgscount = 1;
                        final iUsuario u = new ImpUsuario();
                        final iChat c = new ImpChat();
                        Chat chat = c.readAllChat(2, u_id);
                        httpSession.setAttribute("chat_id", chat.getChat_Id());
                        operatorname = u.readUser(chat.getChat_S_Id()).getU_nombre();
                        username = u.readUser(chat.getChat_U_Id()).getU_nombre();
                        timestamp = chat.getChat_Timestamp().toString();
                        String[] s_msgs, u_msgs;
                        out.println("<ol class='chat'><div class='day'>Operador: " + operatorname + " " + timestamp + "</div>");
                        if (!(chat.getChat_S_Msgs().equals("") && chat.getChat_U_Msgs().equals(""))) {
                            String time = c.readChatTime(chat.getChat_Id());
                            String[] times = new String[time.length()];
                            for (int i = 0; i < time.length(); i++) {
                                times[i] = time.substring(i, i + 1);
                            }
                            s_msgs = chat.getChat_S_Msgs().split("/");
                            u_msgs = chat.getChat_U_Msgs().split("/");
                            if (isSupport) {
                                for (int i = 0; i < time.length(); i++) {
                                    if (times[time.length() - 1 - i].equals("1")) {
                                        out.println("<li class='self'><div class='msg'><p>" + s_msgs[s_msgs.length - s_msgscount] + "</p></div></li>");
                                        ++s_msgscount;
                                    }
                                    if (times[time.length() - 1 - i].equals("2")) {
                                        out.println("<li class='other'><div class='msg'><div class='user'>" + username + "</div><p>" + u_msgs[u_msgs.length - u_msgscount] + "</p></div></li>");
                                        ++u_msgscount;
                                    }
                                }
                            } else {
                                for (int i = 0; i < time.length(); i++) {
                                    if (times[time.length() - 1 - i].equals("1")) {
                                        out.println("<li class='other'><div class='msg'><div class='user'>" + operatorname + "<span class='range admin'>Operador</span></div><p>" + s_msgs[s_msgs.length - s_msgscount] + "</p></div></li>");
                                        ++s_msgscount;
                                    }
                                    if (times[time.length() - 1 - i].equals("2")) {
                                        out.println("<li class='self'><div class='msg'><p>" + u_msgs[u_msgs.length - u_msgscount] + "</p></div></li>");
                                        ++u_msgscount;
                                    }
                                }
                            }
                        } else {
                            if (isSupport) {
                                out.println("<div class='day'>El usuario creo una sala de chat pero no dejó mensaje alguno.</div>");
                            } else {
                                out.println("<li class='other'><div class='msg'><div class='user'>" + operatorname + "<span class='range admin'>Operador</span></div><p>¿En qué puedo ayudarle?</p></div></li>");
                            }
                        }
                        out.println("</ol>");
                    } else {
                        response.sendRedirect(index);
                    }
                %>
                <div class="typezone">
                    <form action="Controller" method="post">
                        <div class="mdl-textfield mdl-js-textfield">
                            <textarea class="mdl-textfield__input" type="text" placeholder="Introduzca el mensaje." rows="2" name="msg" id="msg" autocomplete="on" style="margin-top: 0px; margin-bottom: 0px;" onkeypress="alphap()" pattern="[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]"></textarea>
                        </div>
                        <button type="submit" name="submitsr" class="send mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="EnviarChat" onclick="return Fchat();">Enviar</button>
                    </form>
                    <br/><br/>
                </div>
            </main>
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/chat.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
        <script type="text/javascript" src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    </body>
</html>
