<%-- 
    Document   : listener
    Created on : 8/03/2019, 04:55:25 PM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.DAO.iUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Comienza a jugar SpaceRide</title>
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
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" type="text/css" href="https://code.getmdl.io/1.3.0/material.indigo-deep_orange.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/styles_listener.css"/>
    </head>
    <body onload="noback(); onloadlistener()">
        <%
            HttpSession sesion = request.getSession();
            final String game = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/game.html";
            String u1_name = "", u2_name = "";
            iUsuario u = new ImpUsuario();
            if (sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null && sesion.getAttribute("area") != null && sesion.getAttribute("area_id") != null && sesion.getAttribute("dif") != null) {
                u1_name = u.readUser(Integer.parseInt(sesion.getAttribute("id1").toString())).getU_nombre();
                u2_name = u.readUser(Integer.parseInt(sesion.getAttribute("id2").toString())).getU_nombre();
            } else {
                sesion.invalidate();
                response.sendRedirect(game);
            }
        %>
        <div class="mdl-layout">
            <div class="mdl-layout__content">
                <div class="mdl-grid">
                    <div class="mdl-cell--12-col">
                        <h2><b>Abre la aplicación SpaceRide que descargaste en la página de inicio y no cierres esta ventana.</b></h2>
                    </div>
                    <div class="mdl-cell--12-col">
                        <form action="Controller" method="post">
                            <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" id="submitsr" name="submitsr" value="Comenzar" onclick="onsubmitListener();">Comenzar</button>
                        </form>
                        <div id = "spinner">
                            <h2>Cargando...</h2>
                            <div class="spinner">
                                <div class="double-bounce1"></div>
                                <div class="double-bounce2"></div>
                            </div>
                        </div>
                        <br/>
                        <br/>
                    </div>
                    <div class="mdl-cell--6-col c4">
                        <img src="images/1preview.fw.png" alt="player1" width="50%"/>
                        <br/>
                        <br/>
                        <br/>
                        <div class="teclado">
                            <h5>Controles Jugador 1: <%=u1_name%></h5>
                            <h6>Dirección de la nave</h6>
                            <div class="bloque">
                                <div class="fila-teclas">
                                    <span class="tecla tecla-letra">
                                        <span>a</span>
                                        A la izquierda
                                    </span>
                                    <span class="tecla tecla-letra">
                                        <span>d</span>
                                        A la derecha
                                    </span>
                                    <span class="tecla tecla-letra">
                                        <span> </span>
                                        Disparo
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mdl-cell--6-col c4">
                        <img src="images/1preview.fw.png" alt="player1" width="50%"/>
                        <br/>
                        <br/>
                        <br/>
                        <div class="teclado">
                            <h5>Controles Jugador 2: <%=u2_name%></h5>
                            <h6>Dirección de la nave</h6>
                            <div class="bloque">
                                <div class="fila-teclas">
                                    <span class="tecla tecla-simple">
                                        <span>←</span>
                                        A la izquierda
                                    </span>
                                    <span class="tecla tecla-simple">
                                        <span>→</span>
                                        A la derecha
                                    </span>
                                    <span class="tecla tecla-enter">
                                        <span>Intro</span>
                                        Disparo
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="mdl-mini-footer">
                <div class="mdl-mini-footer--left-section">
                    <ul class="mdl-mini-footer--link-list">
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
        <script type="text/javascript" src="js/listener.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
    </body>
</html>
