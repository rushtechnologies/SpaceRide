/* 
 * @author CARLOSHG
 */
/* global grecaptcha */

function nosolution() {
    document.getElementById("nosolution").style.display = "block";
    document.getElementById("dialog").style.display = "block";
    document.getElementById("answer").style.display = "none";
    document.getElementById("chatlogin").style.display = "none";
    document.getElementById("dialog2").style.display = "none";
}
function chatlogin() {
    document.getElementById("nosolution").style.display = "none";
    document.getElementById("dialog").style.display = "none";
    document.getElementById("answer").style.display = "none";
    document.getElementById("chatlogin").style.display = "block";
    document.getElementById("dialog2").style.display = "block";
}
function Fchatlogin() {
    if (document.getElementById("user2").value === "" || document.getElementById("pass2").value === "" || document.getElementById("pass2").value.length < 8){
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msj2").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("user2").value === "") {
            alert("Debes ingresar tu nombre de usuario");
            document.getElementById("user2").focus();
        }
        if (document.getElementById("pass2").value === "" || document.getElementById("pass2").value.length < 8) {
            alert("Debes ingresar tu contraseña correctamente");
            document.getElementById("pass2").focus();
        }
        return false;
    } else {
        var grec = "";
        var grec = grecaptcha.getResponse("g-recaptcha");
        document.getElementById("grec2").value = grec;
        if (grec === "" || grec === null) {
            alert("Por favor llene todos los campos solicitados");
            document.getElementById("msj2").innerHTML = "Por favor llene todos los campos solicitados";
            alert("Debes verificar el captcha");
            return false;
        } else {
            return true;
        }
    }
}
function Fpreg() {
    if (document.getElementById("user").value === "" || document.getElementById("pass").value === "" || document.getElementById("pass").value.length < 8 || document.getElementById("q").value === "" || document.getElementById("topic").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msj").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("topic").value === "") {
            alert("Debes selrccionar el tema con el que se relaciona tu pregunta");
            document.getElementById("topic").focus();
        }
        if (document.getElementById("q").value === "") {
            alert("Debes ingresar tu pregunta");
            document.getElementById("q").focus();
        }
        if (document.getElementById("user").value === "") {
            alert("Debes ingresar tu nombre de usuario");
            document.getElementById("user").focus();
        }
        if (document.getElementById("pass").value === "" || document.getElementById("pass").value.length < 8) {
            alert("Debes ingresar tu contraseña correctamente");
            document.getElementById("pass").focus();
        }
        return false;
    } else {
        var grec = "";
        var grec = grecaptcha.getResponse("g-recaptcha");
        document.getElementById("grec").value = grec;
        if (grec === "" || grec === null) {
            alert("Por favor llene todos los campos solicitados");
            document.getElementById("msj").innerHTML = "Por favor llene todos los campos solicitados";
            alert("Debes verificar el captcha");
            return false;
        } else {
            return true;
        }
    }
}