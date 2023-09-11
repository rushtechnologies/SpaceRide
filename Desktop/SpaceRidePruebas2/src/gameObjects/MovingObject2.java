/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Juego.Logica;
import Main.Juego;
import Math.Vector2D2;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public abstract class MovingObject2 extends GameObject2 {

    protected Vector2D2 velocidad2;
    protected AffineTransform at;
    protected double angle2;
    protected double velocidadmax;
    protected Logica lg;

    public MovingObject2(Vector2D2 posicion2, Vector2D2 velocidad2, double velocidadmax, BufferedImage textura2, Logica lg) {
        super(posicion2, textura2);
        this.velocidad2 = velocidad2;
        this.lg = lg;
        this.velocidadmax = velocidadmax;
        angle2 = 0;
    }

    protected Vector2D2 getCenter() {
        return new Vector2D2(posicion2.getX2() + textura2.getWidth() / 2, posicion2.getY2() + textura2.getHeight() / 2);
    }

    public void collwidth2() throws InterruptedException {
        ArrayList<MovingObject1> mv1 = lg.getMv1();
        ArrayList<MovingObject2> mv2 = lg.getMv2();
        int index2 = mv1.size() - 1;
        MovingObject1 m1 = mv1.get(index2);

        for (int i = 0; i < mv2.size(); i++) {
            MovingObject2 m2 = mv2.get(i);
            if (i != mv2.size() - 1) {
                double medida1 = m1.textura1.getWidth() / 2;

                double distancia = m1.getCenter().red(m2.getCenter()).getMagnitud();

                if (m2.equals(this)) {
                    continue;
                }

                if (distancia < medida1 && mv2.contains(this)) {
                    lg.getMv2().remove(m2);
                    
                   
                   
                    JOptionPane.showMessageDialog(null,"Pausa");
                    
                    Juego.canvas.removeKeyListener(Juego.tec);
                    Juego.tec.reinicio();
                }
            }
        }
        
    }

    protected void destroy() {
        lg.getMv2().remove(this);
    }
}
