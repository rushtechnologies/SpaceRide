/* 
 *
 * @author CARLOSHG
 */
window.onload = function () {
    var firstInput = document.getElementById("passs");
    var myInput = document.getElementById('pass2s');
    firstInput.oncopy = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjs").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    firstInput.onpaste = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjs").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    myInput.onpaste = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjs").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
    myInput.oncopy = function (e) {
        e.preventDefault();
        alert("No puedes copiar y pegar en campos de contraseña");
        document.getElementById("msjs").innerHTML = "No puedes copiar y pegar en campos de contraseña";
    };
};