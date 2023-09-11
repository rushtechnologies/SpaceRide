/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *
 * @author Alumno
 */
import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player1;
        public static BufferedImage player2;
        public static BufferedImage disparop1;
        public static BufferedImage disparop2;
        public static BufferedImage fondo;
	
	public static void init()
	{
		player1 = Loader.ImageLoader("/ImaSound/Naves/P1old.png");
                player2 = Loader.ImageLoader("/ImaSound/Naves/P2old.png");
                disparop1 = Loader.ImageLoader("/ImaSound/Otros/DisparoP12.png");
                disparop2 = Loader.ImageLoader("/ImaSound/Otros/DisparoP22.png");
                fondo = Loader.ImageLoader("/ImaSound/Naves/Space.jpg");
	}
	
}

