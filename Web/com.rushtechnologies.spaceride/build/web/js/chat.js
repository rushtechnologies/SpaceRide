/* 
 * @author CARLOSHG
 */

function reload() {
    setInterval(function () {
        window.location.reload();
    }, 60000);
}

function Fchat() {
    if (document.getElementById("msg").value === "") {
        alert("Debes ingresar el cuerpo mensaje a enviar");
        document.getElementById("msg").focus();
        return false;
    }
}