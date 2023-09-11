/* 
 *
 * @author CARLOSHG
 */
function Flogingame() {

    if (document.getElementById("userl").value === "" || document.getElementById("passl").value === "" || document.getElementById("user2").value === "" || document.getElementById("pass2").value === "") {
        alert("Por favor llene todos los campos solicitados");
        if (document.getElementById("userl").value === "" || document.getElementById("passl").value === "") {
            document.getElementById("msjl").innerHTML = "Por favor llene todos los campos solicitados";
            if (document.getElementById("userl").value === "") {
                alert("Debe ingresar tu nombre");
                document.getElementById("userl").focus();
            } else {
                if (document.getElementById("passl").value === "" || document.getElementById("pass").value.length < 8) {
                    alert("Debe ingresar tu contraseña");
                    document.getElementById("passl").focus();
                }
            }
        } else {
            if (document.getElementById("user2").value === "" || document.getElementById("pass2").value === "") {
                document.getElementById("msj2").innerHTML = "Por favor llene todos los campos solicitados";
                if (document.getElementById("user2").value === "") {
                alert("Debe ingresar tu nombre");
                document.getElementById("user2").focus();
            } else {
                if (document.getElementById("pass2").value === "" || document.getElementById("pass2").value.length < 8) {
                    alert("Debe ingresar tu contraseña");
                    document.getElementById("pass2").focus();
                }
            }
            }
        }

        return false;
    }
}
