/* 
 *
 * @author CARLOSHG
 */
function onloadlistener() {
    document.getElementById("spinner").style.display = "none";
}
function onsubmitListener() {
    document.getElementById("spinner").style.display = "block";
    document.getElementById("submitsr").style.display = "none";
    $('html,body').animate({
        scrollTop: $("#spinner").offset().top
    }, 2000);
}
