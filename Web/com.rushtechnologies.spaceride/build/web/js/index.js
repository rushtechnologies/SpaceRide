/* 
 *
 * @author CARLOSHG
 */
function modal() {
    document.getElementById("modal").style.display = "block";
    document.getElementById("modal2").style.display = "none";
}
function closemodal() {
    document.getElementById("modal").style.display = "none";
}
function modal2() {
    document.getElementById("modal2").style.display = "block";
    document.getElementById("modal").style.display = "none";
}
function closemodal2() {
    document.getElementById("modal2").style.display = "none";
}
function Flogin() {

    if (document.getElementById("userl").value === "" || document.getElementById("passl").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msjl").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("userl").value === "") {
            alert("Debes ingresar tu nombre");
            document.getElementById("userl").focus();
        } else {
            if (document.getElementById("passl").value === "" || document.getElementById("pass").value.length < 8) {
                alert("Debes ingresar tu contraseña");
                document.getElementById("passl").focus();
            }
        }

        return false;
    }
}
function Fsignin() {

    if (document.getElementById("users").value === "" || document.getElementById("passs").value === "" || document.getElementById("passs").value.length < 8 || document.getElementById("pass2s").value.length < 8 || document.getElementById("pass2s").value === "" || document.getElementById("mails").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msjs").innerHTML = "Por favor llene todos los campos solicitados";
        if (document.getElementById("users").value === "") {
            alert("Debes ingresar tu nombre correctamente");
            document.getElementById("users").focus();
        }
        if (document.getElementById("passs").value === "" || document.getElementById("passs").value.length < 8) {
            alert("Debes ingresar tu contraseña correctamente");
            document.getElementById("passs").focus();
        }
        if (document.getElementById("pass2s").value === "" || document.getElementById("pass2s").value.length < 8) {
            alert("Debes repetir tu contraseña correctamente");
            document.getElementById("pass2s").focus();
        }
        if (document.getElementById("mails").value === "") {
            alert("Debes ingresar tu correo  correctamente");
            document.getElementById("mails").focus();
        } else {
            if (document.getElementById("mails").value.length < 6) {
                alert("Correo de la forma (x@x.x)");
                document.getElementById("mails").focus();
            } else {
                if (document.getElementById("mails").value.indexOf('@') === -1 || document.getElementById("mails").value.indexOf('.') === -1) {
                    alert("Correo de la forma (x@x.x)");
                    document.getElementById("mails").focus();
                }
            }
        }
        return false;
    } else {
        if (document.getElementById("passs").value !== document.getElementById("pass2s").value) {
            alert("Las contraseñas no coinciden");
            document.getElementById("pass2s").focus();
            document.getElementById("msjss").innerHTML = "Las contraseñas no coinciden";
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

}