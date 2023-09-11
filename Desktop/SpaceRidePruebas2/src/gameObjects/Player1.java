/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Graphics.Assets;
import Input.Teclas;
import Juego.Logica;
import Math.Vector2D1;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player1 extends MovingObject1 {

    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

    private Vector2D1 rotar;
    private Vector2D1 acelera;
    private final double ACC = 0.09;
    private final double Delta = 0.05;
    private final Cronometro cr;

    public Player1(Vector2D1 posicion1, Vector2D1 velocidad, double velocidadmax, BufferedImage textura1, Logica lg) {
        super(posicion1, velocidad, velocidadmax, textura1, lg);
        rotar = new Vector2D1(0, 1);
        acelera = new Vector2D1();
        cr = new Cronometro();
    }

    @Override
    public void update() {

        if (Teclas.disparar1 && !cr.corre()) {
            lg.getMv1().add(0, new Disparos1(getCenter().add(rotar.scale(textura1.getWidth())), rotar, 10, angle+Math.PI, Assets.disparop1, lg));
            cr.run(130);
        }

        if (Teclas.RIGHT) {
            angle += Delta;
        }
        if (Teclas.LEFT) {
            angle -= Delta;
        }

        if (Teclas.UP) {
            acelera = rotar.scale(ACC);
        } else if (Teclas.DOWN) {
            acelera = rotar.scale(-ACC);
        } else {
            if (velocidad.getMagnitud() != 0) {
                acelera = (velocidad.scale(-1).normalizar()).scale(ACC / 2);
            }
        }

        velocidad = velocidad.add(acelera);

        velocidad = velocidad.limit(velocidadmax);

        rotar = rotar.setDirection(angle + (Math.PI) * 1 / 2);

        posicion1 = posicion1.add(velocidad);

        if (posicion1.getX1() > largo - 70) {
            posicion1.setX1(largo - 71);
        }
        if (posicion1.getX1() < 0) {
            posicion1.setX1(1);
        }
        if (posicion1.getY1() > alto / 2 - 100) {
            posicion1.setY1(alto / 2 - 101);
        }
        if (posicion1.getY1() < 0) {
            posicion1.setY1(1);
        }
        cr.update();
        try {
            collwith();
        } catch (InterruptedException ex) {
            Logger.getLogger(Player1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion1.getX1(), posicion1.getY1());
        at.rotate(angle, Assets.player1.getWidth() / 2, Assets.player1.getHeight() / 2);
        g2d.drawImage(Assets.player1, at, null);
    }

}
