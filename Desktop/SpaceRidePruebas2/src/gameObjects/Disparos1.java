/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Juego.Logica;
import Math.Vector2D1;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Disparos1 extends MovingObject1 {

    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    public Disparos1(Vector2D1 posicion1, Vector2D1 velocidad, double velocidadmax, double angle, BufferedImage textura1, Logica lg) {
        super(posicion1, velocidad, velocidadmax, textura1, lg);

        this.angle = angle;
        this.velocidad = velocidad.scale(velocidadmax);
    }

    @Override
    public void update() {
        posicion1 = posicion1.add(velocidad);
        if (posicion1.getX1() < 0 || posicion1.getX1() > largo || posicion1.getY1() < 0 || posicion1.getY1() > alto - 30) {
            destroy();
        }
        try {
            collwith();
        } catch (InterruptedException ex) {
            Logger.getLogger(Disparos1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion1.getX1() - (textura1.getWidth() / 2), posicion1.getY1() );
        at.rotate(angle, textura1.getWidth() / 2, 0);

        g2d.drawImage(textura1, at, null);
    }

    @Override
    public Vector2D1 getCenter() {
        return new Vector2D1(posicion1.getX1() + textura1.getWidth() / 2, posicion1.getY1() + textura1.getWidth() / 2);
    }

}
