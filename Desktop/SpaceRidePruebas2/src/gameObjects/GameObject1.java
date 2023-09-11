/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import Math.Vector2D1;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameObject1 {

    protected BufferedImage textura1;
    protected Vector2D1 posicion1;

    public GameObject1(Vector2D1 posicion1, BufferedImage textura1) {
        this.posicion1 = posicion1;
        this.textura1 = textura1;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Vector2D1 getPosicion1() {
        return posicion1;
    }

    public void setPosicion1(Vector2D1 posicion1) {
        this.posicion1 = posicion1;
    }

}
