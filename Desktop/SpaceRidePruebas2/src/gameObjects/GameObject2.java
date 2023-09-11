/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Math.Vector2D2;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameObject2 {

    protected BufferedImage textura2;
    protected Vector2D2 posicion2;

    public GameObject2(Vector2D2 posicion2, BufferedImage textura2) {
        this.posicion2 = posicion2;
        this.textura2 = textura2;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Vector2D2 getPosicion2() {
        return posicion2;
    }

    public void setPosicion2(Vector2D2 posicion2) {
        this.posicion2 = posicion2;
    }

}
