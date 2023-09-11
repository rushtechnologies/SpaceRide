package Spaceride;

import Main.Juego;
import Model.User;
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
public class Inicio extends JFrame implements ActionListener{

    public User user1;
    public User user2;
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
    public JLabel player1;
    public ImageIcon player1Img;
    public ImageIcon scaledPlayer1Img;
    public JLabel player2;
    public ImageIcon player2Img;
    public ImageIcon scaledPlayer2Img;
    public JButton back;
    public ImageIcon backImg;
    public ImageIcon scaledBackImg;
    public JButton jugar;
    public ImageIcon jugarImg;
    public ImageIcon scaledJugarImg;
    public JButton configuracion;
    public ImageIcon configuracionImg;
    public ImageIcon scaledConfiguracionImg;

    public Inicio(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Space Ride");
        ((JPanel) getContentPane()).setOpaque(false);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        logoImg = new ImageIcon(this.getClass().getResource("../resources/logo.png"));
        this.setIconImage(logoImg.getImage());
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        maxwidth = dimension.getWidth();
        maxheight = dimension.getHeight();
        backgroundImg = new ImageIcon(this.getClass().getResource("../resources/bdgame.jpg"));
        scaledBackground = new ImageIcon(backgroundImg.getImage().getScaledInstance((int) maxwidth, (int) maxheight, Image.SCALE_DEFAULT));
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
        int halfx = ((int) maxwidth / 2) - 100;
        int halfy = (int) maxheight / 2;
        logo = new JLabel();
        scaledlogoImg = new ImageIcon(logoImg.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        logo.setBounds(halfx + 25, 0, 150, 150);
        logo.setIcon(scaledlogoImg);
        this.add(logo);
        tittle = new JLabel();
        tittle.setBounds(halfx - 150, 200, 500, 50);
        tittle.setText(read(user1.getUserid()) + " vs. " + read(this.user2.getUserid()));
        tittle.setFont(customFont);
        tittle.setForeground(Color.WHITE);
        this.add(tittle);
        player1 = new JLabel();
        player1.setBounds(100, 200, 250, 67);
        player1Img = new ImageIcon(this.getClass().getResource("../resources/1preview.fw.png"));
        scaledPlayer1Img = new ImageIcon(player1Img.getImage().getScaledInstance(250, 67, Image.SCALE_DEFAULT));
        player1.setIcon(scaledPlayer1Img);
        this.add(player1);
        player2 = new JLabel();
        player2.setBounds(halfx + 375, 200, 250, 67);
        player2Img = new ImageIcon(this.getClass().getResource("../resources/2preview.fw.png"));
        scaledPlayer2Img = new ImageIcon(player2Img.getImage().getScaledInstance(250, 67, Image.SCALE_DEFAULT));
        player2.setIcon(scaledPlayer2Img);
        this.add(player2);
        back = new JButton();
        back.setBorder(null);
        back.setBounds(0, 0, 50, 50);
        backImg = new ImageIcon(this.getClass().getResource("../resources/back.fw.png"));
        scaledBackImg = new ImageIcon(backImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        back.setIcon(scaledBackImg);
        back.addActionListener(this);
        this.add(back);
        jugar = new JButton();
        jugar.setBorder(null);
        jugar.setBounds(halfx, halfy - 50, 200, 75);
        jugarImg = new ImageIcon(this.getClass().getResource("../resources/Jugar.fw.png"));;
        scaledJugarImg = new ImageIcon(jugarImg.getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT));
        jugar.setIcon(scaledJugarImg);
        jugar.addActionListener(this);
        this.add(jugar);
        configuracion = new JButton();
        configuracion.setBounds(halfx, halfy + 125, 200, 75);
        configuracion.setBorder(null);
        configuracionImg = new ImageIcon(this.getClass().getResource("../resources/Configuracion.fw.png"));;
        scaledConfiguracionImg = new ImageIcon(configuracionImg.getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT));
        configuracion.setIcon(scaledConfiguracionImg);
        configuracion.addActionListener(this);
        this.add(configuracion);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jugar){
            Main.Juego play = new Main.Juego(user1, user2);
            dispose();
        } else{
            if (ae.getSource() == configuracion){
                
            } else{
                if (ae.getSource() == back){
                    AreaDificultad2 regresa = new AreaDificultad2(user1, user2.getUserid());
                }
            }
        }
    }

    private static String read(int uId) {
        com.rushtechnologies.spaceride.webservice.Read_Service service = new com.rushtechnologies.spaceride.webservice.Read_Service();
        com.rushtechnologies.spaceride.webservice.Read port = service.getReadPort();
        return port.read(uId);
    }
        
}