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
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.owasp.esapi.ESAPI;

/**
 *
 * @author CARLOSHG
 */
public class Inicio1 extends JFrame implements ActionListener{

    public int id;
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
    public JButton back;
    public ImageIcon backImg;
    public ImageIcon scaledBackImg;
    public Color bodyColor;
    public JLabel description;
    public JLabel description2;
    public JTextField user;
    public JLabel userDescription;
    public JPasswordField pass;
    public JLabel passDescription;
    public JButton loginbutton;
    public ImageIcon loginImg;
    public ImageIcon scaledLoginImg;
    
    public Inicio1() {
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
        back = new JButton();
        back.setBorder(null);
        back.setBounds(0, 0, 50, 50);
        backImg = new ImageIcon(this.getClass().getResource("../resources/back.fw.png"));;
        scaledBackImg = new ImageIcon(backImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        back.setIcon(scaledBackImg);
        back.addActionListener(this);
        this.add(back);
        bodyColor = new Color(0, 67, 117);
        description = new JLabel();
        description.setBounds(halfx - 225, 225, 650, 50);
        description.setText("Los dos jugadores deben iniciar sesión para jugar");
        description.setFont(bodycustomFont);
        description.setForeground(bodyColor);
        this.add(description);
        description2 = new JLabel();
        description2.setBounds(halfx - 100, 300, 400, 50);
        description2.setText("Inicio de sesión del jugador 1");
        description2.setFont(bodycustomFont);
        description2.setForeground(bodyColor);
        this.add(description2);
        userDescription = new JLabel();
        userDescription.setBounds(halfx - 150, halfy, 300, 50);
        userDescription.setText("Usuario:");
        userDescription.setFont(bodycustomFont);
        userDescription.setForeground(bodyColor);
        this.add(userDescription);
        user = new JTextField();
        user.setFont(bodycustomFont);
        user.setForeground(Color.WHITE);
        user.setBackground(bodyColor);
        user.setBorder(null);
        user.setBounds(halfx + 50, halfy, 300, 50);
        this.add(user);
        passDescription = new JLabel();
        passDescription.setBounds(halfx - 150, halfy + 100, 150, 50);
        passDescription.setText("Contraseña:");
        passDescription.setFont(bodycustomFont);
        passDescription.setForeground(bodyColor);
        this.add(passDescription);
        pass = new JPasswordField();
        pass.setBackground(bodyColor);
        pass.setFont(bodycustomFont);
        pass.setForeground(Color.WHITE);
        pass.setBounds(halfx + 50, halfy + 100, 300, 50);
        pass.setBorder(null);
        this.add(pass);
        loginbutton = new JButton("Inicar Sesión");
        loginbutton.setBorder(null);
        loginbutton.setBounds(halfx, halfy + 225, 200, 75);
        loginImg = new ImageIcon(this.getClass().getResource("../resources/Iniciarsesion.fw.png"));;
        scaledLoginImg = new ImageIcon(loginImg.getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT));
        loginbutton.setIcon(scaledLoginImg);
        loginbutton.addActionListener(this);
        this.add(loginbutton);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginbutton){
            String usr = ESAPI.encoder().encodeForHTML(user.getText());
            String psd = pass.getText();
            int[] login = new int[login(usr, psd).size()];
            for (int i = 0; i < login(usr, psd).size(); i++) {
                login[i] = login(usr, psd).get(i);
            }
            id = login[0];
            int logincase = login[1];
            switch(logincase){
                case 0:
                    JOptionPane.showMessageDialog(user,"Usuario no encontrado, revisa tus datos");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(user,"Bienvenido de vuelta a Space Ride");
                    Inicio2  inicio2 = new Inicio2(id);
                    dispose();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(user,"Contraseña incorrecta");
                    break;
                default:
                    JOptionPane.showMessageDialog(user,"Error, intente más tarde");
                    user.setText("");
                    pass.setText("");
                    break;
            }
        } else{
            if(ae.getSource() == back){
                Bienvenido regresa = new Bienvenido();
                dispose();
            }
        }
    }

    private static java.util.List<java.lang.Integer> login(java.lang.String user, java.lang.String pass) {
        com.rushtechnologies.spaceride.webservice.Login_Service service = new com.rushtechnologies.spaceride.webservice.Login_Service();
        com.rushtechnologies.spaceride.webservice.Login port = service.getLoginPort();
        return port.login(user, pass);
    }
    
}