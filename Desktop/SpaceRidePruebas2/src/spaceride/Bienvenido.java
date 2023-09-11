package Spaceride;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author CARLOSHG
 */
public class Bienvenido extends JFrame implements ActionListener{
    
    public Dimension dimension;
    public double maxwidth;
    public double maxheight;
    public ImageIcon backgroundImg;
    public ImageIcon scaledBackground;
    public JLabel backgroundLabel;
    public URL fontFileURL;
    public File fontFile;
    public Font customFont;
    public Font bodycustomFont;
    public ImageIcon logoImg;
    public ImageIcon scaledlogoImg;
    public JLabel logo;
    public JLabel tittle;
    public JLabel description;
    public JButton back;
    public ImageIcon backImg;
    public ImageIcon scaledBackImg;
    public Color bodyColor;
    public Color inactive;
    public JLabel logindescription;
    public JLabel logindescription2;
    public JButton login;
    public ImageIcon loginImg;
    public ImageIcon scaledLoginImg;
    public JLabel signinDescription;
    public JButton signin;
    public ImageIcon signinImg;
    public ImageIcon scaledSigninImg;
    public final String spacerideindex = "http://localhost:8080/com.rushtechnologies.spaceride/";

    public Bienvenido() {
        
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Space Ride");
        ((JPanel)getContentPane()).setOpaque(false);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        logoImg = new ImageIcon(this.getClass().getResource("../resources/logo.png"));
        this.setIconImage(logoImg.getImage());
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        maxwidth = dimension.getWidth();
        maxheight = dimension.getHeight();
        backgroundImg = new ImageIcon(this.getClass().getResource("../resources/background.jpg"));
        scaledBackground = new ImageIcon(backgroundImg.getImage().getScaledInstance((int)maxwidth, (int)maxheight, Image.SCALE_DEFAULT));
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(scaledBackground);
        this.getLayeredPane().add(backgroundLabel, JLayeredPane.FRAME_CONTENT_LAYER);
        backgroundLabel.setBounds(0, 0, scaledBackground.getIconWidth(), scaledBackground.getIconHeight());
        try {
            fontFileURL = this.getClass().getResource("../Resources/Roboto-Regular.ttf");
            fontFile = Paths.get(fontFileURL.toURI()).toFile();
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(36f);
            bodycustomFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
            this.setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        } catch (IOException | FontFormatException | URISyntaxException e) {
            System.out.println("Error: Cannot set custom font" + e.getMessage());
        }
        bodyColor = new Color(0, 67, 117);
        inactive = new Color(223, 112, 0);
        this.setAlwaysOnTop(true);
        int halfx = ((int)maxwidth / 2) - 100;
        int halfy = (int)maxheight / 2;
        logo = new JLabel();
        scaledlogoImg = new ImageIcon(logoImg.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        logo.setBounds(halfx + 25, 0, 150, 150);
        logo.setIcon(scaledlogoImg);
        this.add(logo);
        tittle = new JLabel();
        tittle.setBounds(halfx - 250, 150, 700, 50);
        tittle.setText("Space Ride, el juego que ama tu cerebro");
        tittle.setFont(customFont);
        tittle.setForeground(Color.WHITE);
        this.add(tittle);
        description = new JLabel();
        description.setBounds(halfx - 375, 225, 950, 50);
        description.setText("Space Ride es para dos jugadores dispuestos a demostrar quién sabe más");
        description.setFont(bodycustomFont);
        description.setForeground(Color.WHITE);
        this.add(description);
        back = new JButton();
        back.setBorder(null);
        back.setBounds(0, 0, 50, 50);
        backImg = new ImageIcon(this.getClass().getResource("../resources/back.fw.png"));;
        scaledBackImg = new ImageIcon(backImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        back.setIcon(scaledBackImg);
        back.addActionListener(this);
        this.add(back);
        logindescription = new JLabel();
        logindescription.setBounds(halfx - 137, halfy - 100, 475, 50);
        logindescription.setText("Inicia sesión para jugar en Space Ride");
        logindescription.setFont(bodycustomFont);
        logindescription.setForeground(bodyColor);
        this.add(logindescription);
        logindescription2 = new JLabel();
        logindescription2.setBounds(halfx - 125, halfy, 450, 50);
        logindescription2.setText("(cada usuario debe de inicar sesión)");
        logindescription2.setFont(bodycustomFont);
        logindescription2.setForeground(bodyColor);
        this.add(logindescription2);
        login = new JButton();
        login.setBorder(null);
        login.setBounds(halfx, halfy + 100, 200, 75);
        loginImg = new ImageIcon(this.getClass().getResource("../resources/Iniciarsesion.fw.png"));;
        scaledLoginImg = new ImageIcon(loginImg.getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT));
        login.setIcon(scaledLoginImg);
        login.addActionListener(this);
        this.add(login);
        signinDescription = new JLabel();
        signinDescription.setBounds(halfx - 100, halfy + 200, 400, 50);
        signinDescription.setText("Regístrate en nuestro sitio web");
        signinDescription.setFont(bodycustomFont);
        signinDescription.setForeground(bodyColor);
        this.add(signinDescription);
        signin = new JButton();
        signin.setBounds(halfx, halfy + 275, 200, 75);
        signin.setBorder(null);
        signinImg = new ImageIcon(this.getClass().getResource("../resources/Registrarse.fw.png"));;
        scaledSigninImg = new ImageIcon(signinImg.getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT));
        signin.setIcon(scaledSigninImg);
        signin.addActionListener(this);
        this.add(signin);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == login){
            Inicio1 inicio1 = new Inicio1();
            dispose();
        } else {
            if (ae.getSource() == signin) {
                if (java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                        try {
                            URI uri = new URI(spacerideindex);
                            desktop.browse(uri);
                        } catch (URISyntaxException | IOException e) {
                            System.out.println("Error al direccionar a página web: " + e.getMessage());
                        }
                    }
                }
            } else{
                if(ae.getSource() == back){
                    dispose();
                }
            }
        }

    }

}
