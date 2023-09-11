/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Alumno
 */
public class Modelo {

    private String respuesta;
    private List<Object> pregunta = enviaPreg("Aleatorio", "Conocimientos Generales");

    public String getRespuesta() {
        respuesta = checarAnswer(1);
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public List<Object> getPregunta() {
        return pregunta;
    }

    public void setPregunta(List<Object> pregunta) {
        this.pregunta = pregunta;
    }

    private static java.util.List<java.lang.Object> enviaPreg(java.lang.String diff, java.lang.String area) {
        webservice.SpaceRide_Service service = new webservice.SpaceRide_Service();
        webservice.SpaceRide port = service.getSpaceRidePort();
        return port.enviaPreg(diff, area);
    }

    private static String checarAnswer(int answer) {
        webservice.SpaceRide_Service service = new webservice.SpaceRide_Service();
        webservice.SpaceRide port = service.getSpaceRidePort();
        return port.checarAnswer(answer);
    }

}
