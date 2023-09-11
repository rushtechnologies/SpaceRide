<%-- 
    Document   : exit
    Created on : 7/11/2018, 06:21:00 PM
    Author     : CARLOSHG
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="apple-touch-icon-precomposed" href="images/icono.png">
        <link rel="shortcut icon" href="images/icono.png">
        <link rel="icon" sizes="192x192" href="images/icono.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-red.min.css"/>
        <title>SpaceRide</title>
    </head>
    <body>
        <h1>Exit</h1>
        <%
            HttpSession sesion = request.getSession();
            if(sesion==null){
                response.sendRedirect("http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/");
            }else{
                sesion.invalidate();
                response.sendRedirect("http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/com.rushtechnologies.spaceride/");
            }
        %>
    </body>
</html>
