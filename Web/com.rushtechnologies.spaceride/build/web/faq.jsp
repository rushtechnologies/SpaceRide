<%-- 
    Document   : faq
    Created on : 21/02/2019, 07:48:56 PM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" import="com.rushtechnologies.spaceride.Controller.Controller, com.rushtechnologies.spaceride.ImpDAO.ImpFaq, com.rushtechnologies.spaceride.DAO.iFaq, java.util.List, java.util.ArrayList, com.rushtechnologies.spaceride.Models.Faq"%>
<%
    Controller controller = new Controller();
    controller.createFaqs();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Soporte SpaceRide</title>
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
        <link rel="stylesheet" type="text/css" href="css/styles_faq.css"/>
    </head>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer">
            <div class="mdl-layout__drawer navigate">
                <span class="mdl-layout-title"><h3>Soporte</h3></span>
                <nav class="mdl-navigation nav">
                    <form method="post" action="faq.jsp">
                        <%
                            iFaq faq = new ImpFaq();
                            List<String> temas = faq.readAllFaq_tema();
                            List<String> repeated = new ArrayList<String>();
                            for (int i = 0; i < temas.size(); i++) {
                                String tema = temas.get(i);
                                if (i == 0) {
                                    out.println("<button value = '" + tema + "' name = 'submitsr' type = 'submit' class = 'mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link'>" + tema + "</button><br/>");
                                    repeated.add(tema);
                                } else {
                                    if (!repeated.contains(tema)) {
                                        out.println("<button value = '" + tema + "' name = 'submitsr' type = 'submit' class = 'mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link'>" + tema + "</button><br/>");
                                        repeated.add(tema);
                                    }
                                }
                            }
                        %>
                    </form>
                    <br/>
                    <p class="mdl-button mdl-button--accent mdl-navigation__link">No encontré solución</p>
                    <button type="button" onclick="nosolution();" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link">Preguntar</button>
                    <br/>
                    <button type="button" onclick="chatlogin();" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link">Chat de soporte</button>
                    <br/>
                    <br/>
                    <br/>
                    <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent mdl-navigation__link" href="index.html"><b>Página de Inicio</b></a>
                </nav>
            </div>
            <main class="mdl-layout__content content">
                <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
                    <div class="mdl-layout__header-row">
                        <span class="mdl-layout-title"><h3><b>SpaceRide</b></h3></span>
                        <div class="mdl-layout-spacer"></div>
                        <nav class="mdl-navigation mdl-layout--large-screen-only">
                            <a class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--accent" href="index.html"><b>Página de Inicio</b></a>
                        </nav>
                    </div>
                </header>
                <section id="answer" class="answer">
                    <br/><br/>
                    <%
                        if (request.getParameter("submitsr") == null) {
                            out.println("<div class='demo-card-wide mdl-card mdl-shadow--4dp'><div class='mdl-card__title'><h3>¿En qué podemos ayudarte?</h3></div><div class='mdl-card__supporting-text'><h4>Selecciona un tema dependinedo tu problema o duda acerca de SpaceRide, si no encuentras respuesta puedes ingresar una nueva pregunta y será respondida por nuestro equipo de soporte a la brevedad posible.</h4></div></div>");
                        } else {
                            for (String tema : repeated) {
                                if (repeated.contains(request.getParameter("submitsr"))) {
                                    if (request.getParameter("submitsr").equals(tema)) {
                                        List<Faq> faqs = faq.readAllFaq(tema);
                                        if (faqs.size() == 0) {
                                            out.println("<div class='demo-card-wide mdl-card mdl-shadow--4dp'><div class='mdl-card__title'><h3>Tema vacío</h3></div><div class='mdl-card__supporting-text'><h4>Las pregunta sobre este tema se encentran pendientes de respuesta y/o aprobación.</h4></div></div>");
                                        } else {
                                            for (Faq f : faqs) {
                                                out.println("<div class='demo-card-wide mdl-card mdl-shadow--4dp'><div class='mdl-card__title'><h3>" + f.getFaq_pregunta() + "</h3></div><div class='mdl-card__supporting-text'><h4>" + f.getFaq_respuesta() + "</h4></div></div><br/><br/><br/>");
                                            }
                                        }
                                    }
                                } else {
                                    out.println("<div class='demo-card-wide mdl-card mdl-shadow--4dp'><div class='mdl-card__title'><h3>¿En qué podemos ayudarte?</h3></div><div class='mdl-card__supporting-text'><h4>Selecciona un tema dependinedo tu problema o duda acerca de SpaceRide, si no encuentras respuesta puedes ingresar una nueva pregunta y será respondida por nuestro equipo de soporte a la brevedad posible.</h4></div></div>");
                                }
                            }
                        }
                    %>
                    <div class="mdl-layout__obfuscator"></div>
                    <div class="fill"></div>
                </section>
                <section id="nosolution" class="nosolution">
                    <div class="page-content">
                        <br/><br/>
                        <dialog class="dialogsr mdl-dialog" id="dialog">
                            <form action="Controller" method="post">
                                <h3 class="mdl-dialog__title">Ingresa tu pregunta o duda y  la resolveremos a la brevedad posible</h3>
                                <div class="mdl-dialog__content">
                                    <p class="mdl-color-text--primary-dark" id="msj"></p>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select">
                                        <select id="topic" name="topic" class="mdl-textfield__input">
                                            <option selected></option>
                                            <%
                                                for (String tema : repeated) {
                                                    out.println("<option value='" + tema + "'>" + tema + "</option>");
                                                }
                                            %>
                                            <option value="Otro">Otro</option>
                                        </select>
                                        <label for="topic" class="mdl-textfield__label">Tema</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="topic">Seleccione el tema con el que se relacione su duda o pregunta.</div>
                                    <br/>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <textarea class="mdl-textfield__input" type="text" rows="2" name="q" id="q" autocomplete="on" style="margin-top: 0px; margin-bottom: 0px; height: 100px;" onkeypress="alphap()" pattern="[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]"></textarea>
                                        <label class="mdl-textfield__label" for="q">Pregunta</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="q">Ingrese tu duda o pregunta.</div>
                                    <br/>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <input class="mdl-textfield__input" type="text" name="user" id="user" onkeypress="alpha()" pattern="[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]"/>
                                        <label class="mdl-textfield__label" for="user">Usuario</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="user">Ingrese su nombre de usuario previamente registrado.</div>
                                    <br/>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <input class="mdl-textfield__input" type="password" name="pass" id="pass" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                        <label class="mdl-textfield__label" for="pass">Contraseña</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="pass">Ingrese su contraseña.</div>
                                    <br/>
                                    <br/>
                                    <p class="fp" id="fp">¿Olvidó su contraseña?</p>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="fp">Intente ingresar su contraseña y de ser usuario podrá recuperar su contraseña.</div>
                                    <br/>
                                    <center><div id="g-recaptcha" class="g-recaptcha" data-sitekey="6LdM9pEUAAAAALBEK1DUwfgdZQjMTVGDvr4YMBO-"></div></center>
                                    <input type="hidden" name="grec" value="" id="grec"/>
                                    <br/>
                                </div>
                                <div class="mdl-dialog__actions--full-width">
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="Createq" onclick="return Fpreg();">Preguntar</button>
                                </div>
                            </form>
                        </dialog>
                        <br/>
                        <br/>
                    </div>
                </section>
                <section id="chatlogin" class="chatlogin">
                    <div class="page-content">
                        <br/><br/>
                        <dialog class="dialogsr mdl-dialog" id="dialog2">
                            <form action="Controller" method="post">
                                <h3 class="mdl-dialog__title">Inicia sesión para acceder al chat de soporte con un operador</h3>
                                <div class="mdl-dialog__content">
                                    <p class="mdl-color-text--primary-dark" id="msj2"></p>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <input class="mdl-textfield__input" type="text" name="user2" id="user2" onkeypress="alpha()" pattern="[a-zA-Z]+(\s*[a-zA-Z]*)*[a-zA-Z]"/>
                                        <label class="mdl-textfield__label" for="user2">Usuario</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="user2">Ingrese su nombre de usuario previamente registrado.</div>
                                    <br/>
                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                        <input class="mdl-textfield__input" type="password" name="pass2" id="pass2" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/>
                                        <label class="mdl-textfield__label" for="pass2">Contraseña</label>
                                    </div>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="pass2">Ingrese su contraseña.</div>
                                    <br/>
                                    <br/>
                                    <p class="fp" id="fp2">¿Olvidó su contraseña?</p>
                                    <div class="mdl-tooltip mdl-tooltip--large" for="fp2">Intente ingresar su contraseña y de ser usuario podrá recuperar su contraseña.</div>
                                    <br/>
                                    <center><div id="g-recaptcha" class="g-recaptcha" data-sitekey="6LdM9pEUAAAAALBEK1DUwfgdZQjMTVGDvr4YMBO-"></div></center>
                                    <input type="hidden" name="grec" value="" id="grec2"/>
                                    <br/>
                                </div>
                                <div class="mdl-dialog__actions--full-width">
                                    <button type="submit" name="submitsr" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--primary" value="ChatLogin" onclick="return Fchatlogin();">Iniciar Chat de Soporte</button>
                                </div>
                            </form>
                        </dialog>
                        <br/>
                        <br/>
                    </div>
                </section>
                <div class="fill"></div>
                <footer class="mdl-mini-footer footer">
                    <div class="mdl-mini-footer--left-section">
                        <ul class="mdl-mini-footer--link-list">
                            <li><a href="index.html">Página de Inicio</a></li>
                            <li><a href="game.html">Jugar</a></li>
                            <li><a href="about.html">Acerca de</a></li>
                        </ul>
                    </div>
                    <div class="mdl-mini-footer--right-section">
                        <h6>Space Ride por Rush Techologies</h6>
                    </div>
                </footer>
                <div class="mdl-layout__obfuscator"></div>
            </main>
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script async defer src="https://www.google.com/recaptcha/api.js"></script>
        <script type="text/javascript" src="js/spaceride.js"></script>
        <script type="text/javascript" src="js/faqs.js"></script>
        <script type="text/javascript" defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    </body>
</html>