package gameObjects;

import Juego.Logica;
import Math.Vector2D1;
import Main.Juego;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public abstract class MovingObject1 extends GameObject1{

    protected Vector2D1 velocidad;
    protected AffineTransform at;
    protected double angle;
    protected double velocidadmax;
    protected final Logica lg;
    protected Player2 p2;
    protected int width, height;
    

    public MovingObject1(Vector2D1 posicion1, Vector2D1 velocidad, double velocidadmax, BufferedImage textura1, Logica lg) {
        super(posicion1, textura1);
        this.velocidad = velocidad;
        this.lg = lg;
        this.velocidadmax = velocidadmax;
        width = textura1.getWidth();
        height = textura1.getHeight();
        angle = 0;
    }

    protected Vector2D1 getCenter() {
        return new Vector2D1(posicion1.getX1() + textura1.getWidth() / 2, posicion1.getY1() + textura1.getHeight() / 2);
    }

    public void collwith() throws InterruptedException {
        ArrayList<MovingObject1> mv1 = lg.getMv1();
        ArrayList<MovingObject2> mv2 = lg.getMv2();
        int index2 = mv2.size() - 1;
        MovingObject2 m2 = mv2.get(index2);
        for (int i = 0; i < mv1.size(); i++) {
            MovingObject1 m1 = mv1.get(i);
            if (i != mv1.size() - 1) {
                double medida1 = m2.textura2.getWidth() / 2;

                double distancia = m2.getCenter().red(m1.getCenter()).getMagnitud();

                if (m1.equals(this)) {
                    continue;
                }

                if (distancia < medida1 && mv1.contains(this)) {
                    lg.getMv1().remove(m1);
                    
                   
                    JOptionPane.showMessageDialog(null,"Pausa");
                    Juego.canvas.removeKeyListener(Juego.tec);
                    Juego.tec.reinicio();
                }

            }
        }
    }

    protected void destroy() {
        lg.getMv1().remove(this);
    }

}
