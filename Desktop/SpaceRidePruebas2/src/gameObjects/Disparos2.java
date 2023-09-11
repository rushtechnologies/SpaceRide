/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Juego.Logica;
import Math.Vector2D2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Disparos2 extends MovingObject2 {

    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public Disparos2(Vector2D2 posicion2, Vector2D2 velocidad2, double velocidadmax, double angle2, BufferedImage textura2, Logica lg) {
        super(posicion2, velocidad2, velocidadmax, textura2, lg);
        this.angle2 = angle2;
        this.velocidad2 = velocidad2.scale(velocidadmax);
    }

    @Override
    public void update() {
        posicion2 = posicion2.add(velocidad2);
        if (posicion2.getX2() < 0 || posicion2.getX2() > largo || posicion2.getY2() < 0 || posicion2.getY2() > alto - 30) {
            destroy();
        }
        try {
            collwidth2();
        } catch (InterruptedException ex) {
            Logger.getLogger(Disparos2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion2.getX2() - (textura2.getWidth() / 2), posicion2.getY2());
        at.rotate(angle2, textura2.getWidth() / 2, 0);

        g2d.drawImage(textura2, at, null);
    }

}
