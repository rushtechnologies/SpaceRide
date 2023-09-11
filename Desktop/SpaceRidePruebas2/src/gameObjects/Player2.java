package gameObjects;

import Graphics.Assets;
import Input.Teclas;
import Juego.Logica;
import Math.Vector2D2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player2 extends MovingObject2 {

    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private Vector2D2 rotar2;
    private Vector2D2 acelera;
    private final double ACC = 0.09;
    private final double Delta = 0.05;
    private final Cronometro cr;

    public Player2(Vector2D2 posicion2, Vector2D2 velocidad2, double velocidadmax, BufferedImage textura2, Logica lg) {
        super(posicion2, velocidad2, velocidadmax, textura2, lg);
        rotar2 = new Vector2D2(0, 1);
        acelera = new Vector2D2();
        cr = new Cronometro();
    }

    @Override
    public void update() {

        if (Teclas.disparar2 && !cr.corre()) {
            lg.getMv2().add(0, new Disparos2(getCenter().add(rotar2.scale(textura2.getWidth())), rotar2, 10, angle2, Assets.disparop2, lg));
            cr.run(130);
        }

        if (Teclas.D) {
            angle2 += Delta;
        }
        if (Teclas.A) {
            angle2 -= Delta;
        }

        if (Teclas.S) {
            acelera = (rotar2.scale(-ACC));
        } else if (Teclas.W) {
            acelera = rotar2.scale(ACC);
        } else {
            if (velocidad2.getMagnitud() != 0) {
                acelera = (velocidad2.scale(-1).normalizar()).scale(ACC / 2);
            }
        }

        rotar2 = rotar2.setDirection(angle2 - Math.PI / 2);

        velocidad2 = velocidad2.add(acelera);

        velocidad2 = velocidad2.limit(velocidadmax);

        rotar2 = rotar2.setDirection(angle2 - Math.PI / 2);

        posicion2 = posicion2.add(velocidad2);

        if (posicion2.getX2() > largo - 70) {
            posicion2.setX2(largo - 71);
        }
        if (posicion2.getX2() < 0) {
            posicion2.setX2(1);
        }
        if (posicion2.getY2() > alto - 100) {
            posicion2.setY2(alto - 101);
        }
        if (posicion2.getY2() < (alto / 2 - 25)) {
            posicion2.setY2(alto / 2 - 26);
        }
        cr.update();
        try {
            collwidth2();
        } catch (InterruptedException ex) {
            Logger.getLogger(Player2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(posicion2.getX2(), posicion2.getY2());
        at.rotate(angle2, Assets.player2.getWidth() / 2, Assets.player2.getHeight() / 2);
        g2d.drawImage(Assets.player2, at, null);

    }

    @Override
    public Vector2D2 getCenter() {
        return new Vector2D2(posicion2.getX2() + textura2.getWidth() / 2, posicion2.getY2() + textura2.getHeight() / 2);
    }
}
