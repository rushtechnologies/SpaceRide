/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Graphics.Assets;
import Input.Teclas;
import Juego.Logica;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Model.User;

/**
 *
 * @author Lenovo
 */
public final class Juego extends JFrame{

    Toolkit t = Toolkit.getDefaultToolkit();
    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;


    public static Canvas canvas;
    private boolean run=false;

    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 60;
    private final double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    private Logica log;
    public static Teclas tec;
    public User user1;
    public User user2;
    
     public Juego(User user1, User user2) {
        
        this.user1 = user1;
        this.user2 = user2;
        canvas = new Canvas();
        tec = new Teclas();
        ventana();
        start();
        System.out.println(user1.toString()+user2.toString());
       
        
    }

    private void ventana(){
        setTitle("SpaceRide");
        setSize(largo, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        canvas.setPreferredSize(new Dimension(largo, alto));
        canvas.setMaximumSize(new Dimension(largo, alto));
        canvas.setMinimumSize(new Dimension(largo, alto));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(tec);
        
        setVisible(true);
    }
   

    private void update() {
        tec.update();
        log.update();
    }

    private void draw() {
        bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        //-----------------------
        
        

        g.fillRect(0, 0, largo, alto);

        
        
        g.drawImage(Assets.fondo, 0, 0, largo, alto, null);
        
        log.draw(g);

        g.drawString("" + AVERAGEFPS, 10, 20);

        //---------------------
        g.dispose();
        bs.show();
    }

    private void init() {
        Assets.init();
        log = new Logica();
    }

    public void start() {   
        run=true;
        exe();
    }

   
    private void exe(){
        long now;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();
        
        while (run != false) {
        /*System.out.println("run2 "+run);
        System.out.println("pausa2 "+p.isPausa());*/
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
            time += (now - lastTime);
            lastTime = now;

            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
            }
            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
            
            
        }
      
    }
  
}
