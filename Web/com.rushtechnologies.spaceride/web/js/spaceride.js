/* 
 *
 * @author CARLOSHG
 */
function alpha() {
    var letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
    var x = event.keyCode;
    var letra = String.fromCharCode(x);
    var n = letras.indexOf(letra);
    if (n === -1) {
        if (x === 13) {
            event.returnValue = true;
        } else {
            event.returnValue = false;
            alert("Ingresa sólo letras de la A-Z o a-z");
            document.getElementById("msj").innerHTML = "Ingresa sólo letras de la A-Z o a-z";
        }
    }
}
function alphap() {
    var letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz,:;.-_()/&"';
    var x = event.keyCode;
    var letra = String.fromCharCode(x);
    var n = letras.indexOf(letra);
    if (n === -1) {
        if (x === 13) {
            event.returnValue = true;
        } else {
            event.returnValue = false;
            alert("Ingresa sólo letras de la A-Z o a-z");
            document.getElementById("msj").innerHTML = "Ingresa sólo letras de la A-Z o a-z";
        }
    }
}
function email() {
    var letras = "@._-1234567890ABCDEFGHIKKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var x = event.keyCode;
    var letra = String.fromCharCode(x);
    var n = letras.indexOf(letra);
    if (n === -1) {
        if (x === 13) {
            event.returnValue = true;
        } else {
            event.returnValue = false;
            alert("Ingresa sólo letras de la A-Z o a-z");
            document.getElementById("msj").innerHTML = "Ingresa sólo letras de la A-Z o a-z";
        }
    }
}
function noback() {
    window.location.hash = 'no-back-button';
    window.location.hash = 'Again-No-back-button';
    window.onhashchange = function () {
        window.location.hash = 'no-back-button';
    };
}
function invalid() {
    alert("Campos solicitados inválidos");
}
function failedLogin() {
    alert("Usuario no encontrado, revise los datos ingresados.");
}
function failedLoginpass() {
    alert("Contraseña incorrecta, redireccionando a recuperar contraseña.");
}
function failedFp() {
    alert("Correo incorrecto, revise el correo ingresado.");
}
function exists() {
    alert("El usuario que se intenta registrar ya existe, intenta con Acceder");
}
function error() {
    alert("Ocurrió un error, intente más tarde");
    document.getElementById("msj").innerHTML = "Ocurrió un error, intente más tarde";
}
function mailpass() {
    alert("Ya puede recuperar su contraseña en su correo electrónico, a continuación accederá a sus estadísticas de juego en Space Ride");
}
function updated() {
    alert("Sus datos han sido actualizados");
}
function updatedPass() {
    alert("Su contraseña ha sido actualizada");
}
