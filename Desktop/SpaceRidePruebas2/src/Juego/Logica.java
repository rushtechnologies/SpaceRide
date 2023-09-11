package Juego;

import Graphics.Assets;
import Math.Vector2D1;
import Math.Vector2D2;
import gameObjects.MovingObject1;
import gameObjects.MovingObject2;
import gameObjects.Player1;
import gameObjects.Player2;
import java.awt.Graphics;
import java.util.ArrayList;

public class Logica {

    final int largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    final int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private final Player1 player1;
    private final Player2 player2;
    private ArrayList <MovingObject1> mv1=new ArrayList<>();
    private ArrayList <MovingObject2> mv2=new ArrayList<>();

    public Logica() {
        player1 = new Player1(new Vector2D1((largo / 2), 10), new Vector2D1(),4, Assets.player1,this);
        player2 = new Player2(new Vector2D2((largo / 2), alto - 100), new Vector2D2(),4, Assets.player2,this);
        mv1.add(player1);
        mv2.add(player2);
        
    }

    public void update() {
        for(int i=0; i < mv1.size();i++){
           mv1.get(i).update();
        }
        for(int i=0; i < mv2.size();i++){
            mv2.get(i).update();
        }
    }

    public ArrayList<MovingObject1> getMv1() {
        return mv1;
    }

    public void setMv1(ArrayList<MovingObject1> mv1) {
        this.mv1 = mv1;
    }

    public ArrayList<MovingObject2> getMv2() {
        return mv2;
    }

    public void setMv2(ArrayList<MovingObject2> mv2) {
        this.mv2 = mv2;
    }

    public void draw(Graphics g) {
         for(int i=0; i < mv1.size();i++){
           mv1.get(i).draw(g);
        }
        for(int i=0; i < mv2.size();i++){
            mv2.get(i).draw(g);
        }
    }
}
