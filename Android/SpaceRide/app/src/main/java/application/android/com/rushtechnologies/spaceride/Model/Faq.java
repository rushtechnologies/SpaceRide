package application.android.com.rushtechnologies.spaceride.Model;

import java.sql.Timestamp;

public class Faq {

    private int faq_id;
    private String faq_pregunta;
    private String faq_respuesta;
    private String faq_tema;
    private int faq_prioridad;
    private boolean faq_aprobacion;
    private int faq_u_id;
    private int faq_a_id;

    public Faq() {
    }

    public Faq(int faq_id, String faq_pregunta, String faq_respuesta, String faq_tema, int faq_prioridad, boolean faq_aprobacion, int faq_u_id, int faq_a_id) {
        this.faq_id = faq_id;
        this.faq_pregunta = faq_pregunta;
        this.faq_respuesta = faq_respuesta;
        this.faq_tema = faq_tema;
        this.faq_prioridad = faq_prioridad;
        this.faq_aprobacion = faq_aprobacion;
        this.faq_u_id = faq_u_id;
        this.faq_a_id = faq_a_id;
    }

    public int getFaq_id() {
        return faq_id;
    }

    public void setFaq_id(int faq_id) {
        this.faq_id = faq_id;
    }

    public String getFaq_pregunta() {
        return faq_pregunta;
    }

    public void setFaq_pregunta(String faq_pregunta) {
        this.faq_pregunta = faq_pregunta;
    }

    public String getFaq_respuesta() {
        return faq_respuesta;
    }

    public void setFaq_respuesta(String faq_respuesta) {
        this.faq_respuesta = faq_respuesta;
    }

    public String getFaq_tema() {
        return faq_tema;
    }

    public void setFaq_tema(String faq_tema) {
        this.faq_tema = faq_tema;
    }

    public int getFaq_prioridad() {
        return faq_prioridad;
    }

    public void setFaq_prioridad(int faq_prioridad) {
        this.faq_prioridad = faq_prioridad;
    }

    public boolean isFaq_aprobacion() {
        return faq_aprobacion;
    }

    public void setFaq_aprobacion(boolean faq_aprobacion) {
        this.faq_aprobacion = faq_aprobacion;
    }

    public int getFaq_u_id() {
        return faq_u_id;
    }

    public void setFaq_u_id(int faq_u_id) {
        this.faq_u_id = faq_u_id;
    }

    public int getFaq_a_id() {
        return faq_a_id;
    }

    public void setFaq_a_id(int faq_a_id) {
        this.faq_a_id = faq_a_id;
    }

    @Override
    public String toString() {
        return "Faq{" + "faq_id=" + faq_id +
                ", faq_pregunta=" + faq_pregunta +
                ", faq_respuesta=" + faq_respuesta +
                ", faq_tema=" + faq_tema +
                ", faq_prioridad=" + faq_prioridad +
                ", faq_aprobacion=" + faq_aprobacion +
                ", faq_u_id=" + faq_u_id +
                ", faq_a_id=" + faq_a_id +
                '}';
    }

}
