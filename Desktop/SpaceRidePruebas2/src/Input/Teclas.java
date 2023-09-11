
package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclas implements KeyListener{
    
    private final boolean [] teclas = new boolean[255];
    
    public static boolean UP,LEFT,RIGHT,DOWN,W,A,S,D,disparar1,disparar2;
    
    public Teclas(){
          UP=false;
          DOWN=false;
          RIGHT=false;
          LEFT=false;
          A=false;
          W=false;
          S=false;
          D=false;
          disparar1=false;
          disparar2=false;
    }
    
    public void update(){
    
        UP = teclas[KeyEvent.VK_UP];
        DOWN = teclas[KeyEvent.VK_DOWN];
        LEFT = teclas[KeyEvent.VK_LEFT];
        RIGHT = teclas[KeyEvent.VK_RIGHT];
        A = teclas[KeyEvent.VK_A];
        W = teclas[KeyEvent.VK_W];
        D = teclas[KeyEvent.VK_D];
        S = teclas[KeyEvent.VK_S];
        disparar1 = teclas[KeyEvent.VK_ENTER];
        disparar2 = teclas[KeyEvent.VK_SPACE];
    }
    
    public void reinicio(){
          for(int i=0; i< teclas.length;i++){
              teclas[i]=false;
          }
    }
    
    
    @Override
    public void keyPressed(KeyEvent ke) {
         teclas[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
         teclas[ke.getKeyCode()] = false;
           }
    
    @Override
    public void keyTyped(KeyEvent ke) {

    }
}
