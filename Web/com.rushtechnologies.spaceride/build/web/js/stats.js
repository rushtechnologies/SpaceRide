/**
 * SpaceRide player stats pie chart and profile web pae
 *
 * Request player stats pie chart div and user navigation in profile web page
 *
 * Todos los derechos reservados Rush Technologies S.A. de C.V. © 2019
 *
 * Javascript
 * 
 * JQuery AJAX
 *
 * LICENSE: This source file is subject to version 3.01 of the PHP license
 * that is available through the world-wide-web at the following URI:
 * http://www.php.net/license/3_01.txt.  If you did not receive a copy of
 * the PHP License and are unable to obtain it through the web, please
 * send a note to license@php.net so we can mail you a copy immediately.
 *
 * @category   AJAX Request and User Interface
 * @package    web.js
 * @author     Carlos Huerta García <huerta2502@gmail.com>
 * @copyright  2019 © Rush Technologies S.A. de C.V.
 * @license    http://www.php.net/license/3_01.txt  PHP License 3.01
 */

/* global google */

$(document).ready(
        function () {
            $.ajax({
                url: "ChartController",
                dataType: "JSON",
                success: function (result) {
                    google.charts.load('current', {'packages': ['corechart']});
                    google.charts.setOnLoadCallback(function () {
                        drawChart(result);
                    });
                }
            });
            function drawChart(result) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Juegos');
                data.addColumn('number', 'Victorias/Derrotas');
                var dataArray = [];
                var victoriasderrotas = ['Derrotas', 'Victorias'];
                $.each(result, function(i, object){
                    dataArray.push([victoriasderrotas[i], object.count]);
                });
                data.addRows(dataArray);
                var piechart_options = {
                    title: 'Victorias vs. Derrotas',
                    width: 350,
                    height: 250
                };
                var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
                piechart.draw(data, piechart_options);
            }
        }
);

function inicio() {
    document.getElementById("inicio").style.display = "block";
    document.getElementById("actualizar").style.display = "none";
    document.getElementById("dialog").style.display = "none";
    document.getElementById("admin").style.display = "none";
    document.getElementById("dialoga").style.display = "none";
    document.getElementById("botonans").style.display = "block";
    document.getElementById("er").style.display = "none";
    document.getElementById("dialoger").style.display = "none";
}
function actualizar() {
    document.getElementById("inicio").style.display = "none";
    document.getElementById("actualizar").style.display = "block";
    document.getElementById("dialog").style.display = "block";
    document.getElementById("admin").style.display = "none";
    document.getElementById("dialoga").style.display = "none";
    document.getElementById("botonans").style.display = "none";
    document.getElementById("er").style.display = "none";
    document.getElementById("dialoger").style.display = "none";
}
function admin() {
    document.getElementById("inicio").style.display = "none";
    document.getElementById("actualizar").style.display = "none";
    document.getElementById("dialog").style.display = "none";
    document.getElementById("admin").style.display = "block";
    document.getElementById("dialoga").style.display = "block";
    document.getElementById("botonans").style.display = "none";
    document.getElementById("er").style.display = "none";
    document.getElementById("dialoger").style.display = "none";
}
function er() {
    document.getElementById("inicio").style.display = "none";
    document.getElementById("actualizar").style.display = "none";
    document.getElementById("dialog").style.display = "none";
    document.getElementById("admin").style.display = "none";
    document.getElementById("dialoga").style.display = "none";
    document.getElementById("botonans").style.display = "none";
    document.getElementById("er").style.display = "block";
    document.getElementById("dialoger").style.display = "block";
}
function Fact() {

    if (document.getElementById("user").value === "" || document.getElementById("mail").value === "" || document.getElementById("pass").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msj").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("user").value === "") {
            alert("Debes ingresar tu nombre correctamente");
            document.getElementById("user").focus();
        }
        if (document.getElementById("mail").value === "") {
            alert("Debes ingresar tu correo  correctamente");
            document.getElementById("mail").focus();
        } else {
            if (document.getElementById("mail").value.length < 6) {
                alert("Correo de la forma (x@x.x)");
                document.getElementById("mail").focus();
            } else {
                if (document.getElementById("mail").value.indexOf('@') === -1 || document.getElementById("mail").value.indexOf('.') === -1) {
                    alert("Correo de la forma (x@x.x)");
                    document.getElementById("mail").focus();
                }
            }
        }
        if (document.getElementById("pass").value === "" || document.getElementById("passs").value.length < 8) {
            alert("Debes ingresar tu contraseña correctamente");
            document.getElementById("pass").focus();
        }
        return false;
    }

}
function Factn() {

    if (document.getElementById("pass2").value === "" || document.getElementById("npass").value === "" || document.getElementById("npass2").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msj").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("pass2").value === "" || document.getElementById("pass2").value.length < 8) {
            alert("Debes ingresar tu contraseña correctamente");
            document.getElementById("pass2").focus();
        }
        if (document.getElementById("npass").value === "" || document.getElementById("passs").value.length < 8) {
            alert("Debes ingresar tu nueva contraseña correctamente");
            document.getElementById("npass").focus();
        }
        if (document.getElementById("npass2").value === "" || document.getElementById("pass2s").value.length < 8) {
            alert("Debes repetir tu contraseña correctamente");
            document.getElementById("npass2").focus();
        }
        return false;
    } else {
        if (document.getElementById("npass").value !== document.getElementById("npass2").value) {
            alert("Las contraseñas no coinciden");
            document.getElementById("npass2").focus();
            document.getElementById("msj").innerHTML = "Las contraseñas no coinciden";
            return false;
        }
    }

}
function Fans() {
    var descartar = confirm("¿Está seguro de responder y aprobar la pregunta frecuente?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Frep() {
    var descartar = confirm("¿Está seguro de registrar el reporte de evento?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Frem() {
    var descartar = confirm("¿Está seguro de registrar el reporte de mantenimiento?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Fansc() {
    var descartar = confirm("¿Está seguro de descartar la pregunta frecuente?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Fasign() {
    var descartar = confirm("¿Está seguro de asignar el reporte de evento?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Fsol() {
    var descartar = confirm("¿Está seguro de la solución al reporte de evento?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Fsom() {
    var descartar = confirm("¿Está seguro de la resolución del reporte de mantenimiento?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}

function Fval() {
    var descartar = confirm("¿Está seguro de validar la solución?");
    if (descartar === true) {
        return true;
    } else {
        return false;
    }
}