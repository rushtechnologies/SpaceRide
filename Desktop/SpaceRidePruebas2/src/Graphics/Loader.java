/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loader {

    public static BufferedImage ImageLoader(String ruta) {
        try {
            return ImageIO.read(Loader.class.getResource(ruta));
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
