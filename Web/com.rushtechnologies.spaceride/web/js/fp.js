/* 
 * @author CARLOSHG
 */

function Ffp() {

    if (document.getElementById("mail").value === "") {
        alert("Por favor llene todos los campos solicitados");
        document.getElementById("msj").innerHTML = "Por favor llene todos los campos solicitados";
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
        return false;
    }
}
