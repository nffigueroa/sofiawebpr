
package Fuentes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
 

public class ImagenFondo implements Border{
    public BufferedImage back;
    public String ruta_libre =null;
    public ImagenFondo(String ruta){
        try {
            ruta_libre=ruta;
            URL imagePath = new URL(getClass().getResource(ruta).toString());
            back = ImageIO.read(imagePath);
        } catch (Exception ex) {            
        }
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource(ruta_libre));
        g.drawImage(imagenFondo.getImage(),0,0,width, height, null);
    }
    
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
 
    public boolean isBorderOpaque() {
        return true;
    }
 
}
