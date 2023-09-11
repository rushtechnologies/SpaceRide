/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

/**
 *
 * @author Lenovo
 */
public class Cronometro {

    private long delta, lastTime, time;
    private boolean run;

    public Cronometro() {
        delta = 0;
        lastTime = 0;
        run = false;
    }

    public void run(long time) {
        run = true;
        this.time = time;
    }

    public void update() {
        if (run) {
            delta += System.currentTimeMillis() - lastTime;
        }
        if (delta >= time) {
            run = false;
            delta = 0;
        }
        lastTime = System.currentTimeMillis();
    }

    public boolean corre() {
        return run;
    }
}
