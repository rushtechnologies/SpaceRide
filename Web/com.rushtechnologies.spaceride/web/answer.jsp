<%-- 
    Document   : answer
    Created on : 7/03/2019, 11:20:54 AM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.DAO.iUsuario,com.rushtechnologies.spaceride.DAO.iPregunta, com.rushtechnologies.spaceride.ImpDAO.ImpPregunta, com.rushtechnologies.spaceride.Models.Pregunta, com.rushtechnologies.spaceride.DAO.iMuerto, com.rushtechnologies.spaceride.ImpDAO.ImpMuerto, com.rushtechnologies.spaceride.Controller.Controller"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Responder pregunta SpaceRide</title>
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
        <link rel="stylesheet" type="text/css" href="css/styles_answer.css"/>
    </head>
    <%
        HttpSession sesion = request.getSession();
        final String game = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/game.html";
        final String listener = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/listener.jsp";
        int u_id1 = 0, u_id2 = 0, area_id = 0, dif = 0, a_id = 0;
        String u1_name = "", u2_name = "", area_img = "", area = "", dificultad = "";
        final String[] areas_img = Controller.temas;
        final String[] areas = Controller.temaschoose;
        iUsuario u = new ImpUsuario();
        iPregunta pregunta = new ImpPregunta();
        iMuerto muerto = new ImpMuerto();
        Pregunta p = new Pregunta();
        if (sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null && sesion.getAttribute("area") != null && sesion.getAttribute("area_id") != null && sesion.getAttribute("dif") != null && sesion.getAttribute("idm") != null) {
            u_id1 = Integer.parseInt(sesion.getAttribute("id1").toString());
            u1_name = u.readUser(u_id1).getU_nombre();
            u_id2 = Integer.parseInt(sesion.getAttribute("id2").toString());
            u2_name = u.readUser(u_id2).getU_nombre();
            area_id = Integer.parseInt(sesion.getAttribute("area_id").toString());
            area = sesion.getAttribute("area").toString();
            area_img = areas_img[area_id];
            dif = Integer.parseInt(sesion.getAttribute("dif").toString());
            switch (dif) {
                case 0:
                    dificultad = "Me siento con suerte";
                    break;
                case 1:
                    dificultad = "Fácil";
                    break;
                case 2:
                    dificultad = "Medio";
                    break;
                case 3:
                    dificultad = "Difícil";
                    break;
            }
            if (sesion.getAttribute("here") != null) {
                if (sesion.getAttribute("here").toString().equals("here")) {
                    p = pregunta.readPregunta(u_id1, u_id2, area_id, dif);
                    sesion.setAttribute("here", "washere");
                } else {
                    p = new Pregunta(Integer.parseInt(sesion.getAttribute("p_id").toString()), sesion.getAttribute("p").toString(), sesion.getAttribute("r1").toString(), sesion.getAttribute("r2").toString(), sesion.getAttribute("r3").toString(), sesion.getAttribute("r4").toString(), Integer.parseInt(sesion.getAttribute("correcta").toString()), dif, area, true);
                }
            } else {
                p = pregunta.readPregunta(u_id1, u_id2, area_id, dif);
                sesion.setAttribute("here", "washere");
            }
            for (int i = 1; i < 14; i++) {
                if (areas[i].equalsIgnoreCase(p.getP_area())) {
                    a_id = i;
                    break;
                }
            }
            switch (p.getP_dif()) {
                case 0:
                    dificultad = "Me siento con suerte";
                    break;
                case 1:
                    dificultad = "Fácil";
                    break;
                case 2:
                    dificultad = "Medio";
                    break;
                case 3:
                    dificultad = "Difícil";
                    break;
            }
            boolean updatem = muerto.updateM_areaAndM_dif(u_id1, a_id, p.getP_dif());
            if (!updatem) {
                response.sendRedirect(listener);
            }
            sesion.setAttribute("p_id", p.getP_id());
            sesion.setAttribute("p", p.getP_p());
            sesion.setAttribute("r1", p.getP_r1());
            sesion.setAttribute("r2", p.getP_r2());
            sesion.setAttribute("r3", p.getP_r3());
            sesion.setAttribute("r4", p.getP_r4());
            sesion.setAttribute("correcta", p.getP_correcta());
            sesion.setAttribute("area", p.getP_area());
            sesion.setAttribute("dif", p.getP_dif());
        } else {
            sesion.invalidate();
            response.sendRedirect(game);
        }
    %>
    <body class="<%=area_img%>" onload="noback()">
        <div class="mdl-layout">
            <main class="mdl-layout__content">
                <header class="mdl-layout__header--transparent mdl-grid">
                    <br/>
                    <br/>
                    <div class="mdl-cell--4-col mdl-cell--4-col-phone mdl-cell--4-col-desktop">
                        <h3><%=u1_name%></h3>
                        <img src="images/1preview.fw.png" alt="player1" width="50%"/>
                    </div>
                    <div class="mdl-cell--4-col mdl-cell--4-col-phone mdl-cell--4-col-desktop">
                        <h3><%=p.getP_area()%> en <%=dificultad%></h3>
                    </div>    
                    <div class="mdl-cell--4-col mdl-cell--4-col-phone mdl-cell--4-col-desktop">
                        <h3><%=u2_name%></h3>
                        <img src="images/2preview.fw.png" alt="player2" width="50%"/>
                    </div>
                </header>
                <dialog class="dialogsr mdl-dialog">
                    <form action="Controller" method="post">
                        <h4 class="mdl-dialog__title">Responde la pregunta correspondiente para recuperar una vida</h4>
                        <div class="mdl-dialog__content">
                            <h5 id="pregunta"><%=p.getP_p()%></h5>
                            <div class="mdl-tooltip mdl-tooltip--large" for="pregunta">Selecciona la opción correcta para recuperar una vida</div>
                            <p class="mdl-color-text--primary-dark" id="msj"></p>
                            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="r1">
                                <input type="radio" id="r1" class="mdl-radio__button" name="answer" value="r1">
                                <span class="mdl-radio__label"><%=p.getP_r1()%></span>
                            </label>
                            <br/>
                            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="r2">
                                <input type="radio" id="r2" class="mdl-radio__button" name="answer" value="r2">
                                <span class="mdl-radio__label"><%=p.getP_r2()%></span>
                            </label>
                            <br/>
                            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="r3">
                                <input type="radio" id="r3" class="mdl-radio__button" name="answer" value="r3">
                                <span class="mdl-radio__label"><%=p.getP_r3()%></span>
                            </label>
                            <br/>
                            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="r4">
                                <input type="radio" id="r4" class="mdl-radio__button" name="answer" value="r4">
                                <span class="mdl-radio__label"><%=p.getP_r4()%></span>
                            </label>
                            <br/>
                        </div>
                        <div class="mdl-dialog__actions--full-width">
                            <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="Responder" onclick="return Fans();">Responder</button>
                        </div>
                    </form>
                </dialog>
                <div id="fill" class="fill"></div>
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
            </main>
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
        <script type="text/javascript" src="js/answer.js"></script>
        <script type="text/javascript" src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    </body>
</html>
