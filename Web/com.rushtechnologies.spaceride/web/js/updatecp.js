/* 
 * @author: CARLOSHG
 */
window.onload = function () {
    var firstInput = document.getElementById("npass");
    var myInput = document.getElementById('npass2');
    firstInput.oncopy = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjp").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    firstInput.onpaste = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjp").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    myInput.onpaste = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjp").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    myInput.oncopy = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjp").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
};