<%-- 
    Document   : choose
    Created on : 8/03/2019, 04:55:00 PM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="com.rushtechnologies.spaceride.ImpDAO.ImpUsuario, com.rushtechnologies.spaceride.DAO.iUsuario"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Seleccionar Área y Difiucltad SpaceRide</title>
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
        <link rel="stylesheet" type="text/css" href="css/styles_game.css"/>
        <link rel="stylesheet" type="text/css" href="css/imagehover.min.css"/>
    </head>
    <body onload="noback()">
        <%
            HttpSession sesion = request.getSession();
            final String game = "http://localhost:" + request.getLocalPort() + "/com.rushtechnologies.spaceride/game.html";
            String u1_name = "", u2_name = "";
            iUsuario u = new ImpUsuario();
            if (sesion.getAttribute("id1") != null && sesion.getAttribute("id2") != null){
                u1_name = u.readUser(Integer.parseInt(sesion.getAttribute("id1").toString())).getU_nombre();
                u2_name = u.readUser(Integer.parseInt(sesion.getAttribute("id2").toString())).getU_nombre();
            } else {
                sesion.invalidate();
                response.sendRedirect(game);
            }
        %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                    <div class="mdl-layout-spacer"></div>
                    <nav class="mdl-navigation mdl-layout--large-screen-only navigateheader">
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="">Descarga</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="index.html#register">Crear Usuario</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="index.html#inses">Iniciar Sesión</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="index.html">Página de Inicio</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="faq.jsp.html">Preguntas Frecuentes</a>
                        <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="about.html">Acerca de</a>
                    </nav>
                </div>
            </header>
            <div class="mdl-layout__drawer navigate">
                <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                <nav class="mdl-navigation">
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href=""><b>Descarga</b></a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html#register"><b>Crear Usuario</b></a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html#inses"><b>Iniciar Sesión</b></a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html"><b>Página de Inicio</b></a>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="faq.jsp"><b>Preguntas Frecuentes</b></a><br/>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="about.html"><b>Acerca de</b></a>
                </nav>
            </div>
            <main class="mdl-layout__content mainchoose">
                <h3>Seleccione el área y la dificultad para jugar.</h3>
                <form action="Controller" method="post">
                    <section class="content2 mdl-grid">
                        <div class="mdl-cell--6-col mdl-cell--6-col-tablet c4">
                            <h3><%=u1_name%></h3>
                            <img src="images/1preview.fw.png" alt="player1" width="50%"/>
                        </div>
                        <div class="mdl-cell--6-col mdl-cell--6-col-tablet c4">
                            <h3><%=u2_name%></h3>
                            <img src="images/2preview.fw.png" alt="player2" width="50%"/>
                        </div>
                        <div class="mdl-cell--12-col"><br/></div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz art">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Art1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Art2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Art3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Art0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Arte
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz astro">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ast1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ast2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ast3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ast0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Astronomía
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz bio">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Bio1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Bio2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Bio3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Bio0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Biología
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz ent">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ent1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ent2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ent3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ent0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Entretenimiento
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz esp">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Esp1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Esp2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Esp3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Esp0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Español
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz fis">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Fis1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Fis2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Fis3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Fis0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Física
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz geo">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Geo1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Geo2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Geo3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Geo0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Geografía
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz hmex">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HMe1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HMe2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HMe3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HMe0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Historia de México
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz huni">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HUn1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HUn2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HUn3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="HUn0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Historia Universal
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz ing">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ing1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ing2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ing3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Ing0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Inglés
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz mat">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Mat1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Mat2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Mat3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Mat0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Matemáticas
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz qui">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Qui1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Qui2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Qui3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Qui0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Quimica
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-reveal-right tec">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Tec1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Tec2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Tec3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="Tec0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Tecnología
                            </div>
                        </div>
                        <div class="mdl-cell mdl-cell--4-col-desktop mdl-cell--4-col-phone demo-card-wide mdl-card mdl-shadow--2dp">
                            <figure class="mdl-card__title imghvr-shutter-in-out-horiz ran">
                                <figcaption class="tcen">
                                    <h5>Dificultad</h5>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="GKN1">Fácil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="GKN2">Medio</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="GKN3">Díficil</button>
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" value="GKN0">Me siento con suerte</button>
                                </figcaption>
                            </figure>
                            <div class="mdl-card__supporting-text">
                                Conocimientos Generales
                            </div>
                        </div>
                    </section>
                </form>
                <div class="mdl-layout__obfuscator"></div>
                <footer class="mdl-mini-footer">
                    <div class="mdl-mini-footer--left-section">
                        <ul class="mdl-mini-footer--link-list">
                            <li><a href="index.html">Página de Inicio</a></li>
                            <li><a href="FAQ.html">Preguntas frecuentes</a></li>
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
        <script type="text/javascript" src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
    </body>
</html>
