package Spaceride;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author CARLOSHG
 */
public class AreaDificultad extends JFrame implements ActionListener {

    public int user1id;
    public int user2id;
    public String gamearea = "";
    public String gamedifficulty = "";
    public final String[] area = {"Matemáticas", "Física", "Español", "Historia Universal", "Geografía", "Inglés", "Química", "Biología", "Historia de México", "Astronomía", "Entretenimiento", "Arte", "Tecnología", "Conocimientos Generales"};
    public final String[] areaimg = {"Matematicas", "Fisica", "Espanol", "HistoriaUniversal", "Geografia", "Ingles", "Quimica", "Biologia", "HistoriaMexico", "Astronomia", "Entretenimiento", "Arte", "Tecnologia", "ConocimientosGenerales"};
    public final String[] difficulty = {"Fácil", "Medio", "Difícil", "Me siento con suerte"};
    public final String[] difficultyimg = {"Facil", "Medio", "Dificil", "Mesientoconsuerte"};
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
    public JButton[] areas = new JButton[area.length];
    public ImageIcon[] areasimgs = new ImageIcon[area.length];
    public ImageIcon[] scaledareasimgs = new ImageIcon[area.length];
    public JButton[] difficulties = new JButton[difficulty.length];
    public ImageIcon[] difficultiesimgs = new ImageIcon[difficulty.length];
    public ImageIcon[] scaleddifficultiesimgs = new ImageIcon[difficulty.length];

    public AreaDificultad(int user1id, int user2id) {

        this.user1id = user1id;
        this.user2id = user2id;
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
        backgroundImg = new ImageIcon(this.getClass().getResource("../resources/background.jpg"));
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
        tittle.setBounds(halfx - 150, 150, 500, 50);
        tittle.setText(read(this.user1id) + " vs. " + read(this.user2id));
        tittle.setFont(customFont);
        tittle.setForeground(Color.WHITE);
        this.add(tittle);
        back = new JButton();
        back.setBorder(null);
        back.setBounds(0, 0, 50, 50);
        backImg = new ImageIcon(this.getClass().getResource("../resources/back.fw.png"));
        scaledBackImg = new ImageIcon(backImg.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        back.setIcon(scaledBackImg);
        back.addActionListener(this);
        this.add(back);
        bodyColor = new Color(0, 67, 117);
        description = new JLabel();
        description.setBounds(halfx - 325, 225, 800, 50);
        description.setText("Selecciona el área del desafio de conocimientos de " + read(this.user1id));
        description.setFont(bodycustomFont);
        description.setForeground(bodyColor);
        this.add(description);
        int x = halfx - 350;
        int y = 300;
        int width = 150;
        int height = 50;
        for (int i = 0; i < area.length; i++) {
            String resource = "../resources/" + areaimg[i] + ".fw.png";
            areasimgs[i] = new ImageIcon(this.getClass().getResource(resource));
            scaledareasimgs[i] = new ImageIcon(areasimgs[i].getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            areas[i] = new JButton();
            areas[i].setIcon(scaledareasimgs[i]);
            areas[i].setBorder(null);
            areas[i].setBounds(x, y, width, height);
            areas[i].addActionListener(this);
            this.add(areas[i]);
            if (i < 5) {
                if (i != 4) {
                    x += 175;
                } else {
                    x = halfx - 350;
                    y = 375;
                }
            } else {
                if (i > 4 && i < 10) {
                    if (i != 9) {
                        x += 175;
                    } else {
                        x = halfx - 300;
                        y = 450;
                    }
                } else {
                    if (i > 9 && i < 14) {
                        if (i != 13) {
                            x += 200;
                        }
                    }
                }
            }
        }
        description2 = new JLabel();
        description2.setBounds(halfx - 250, 550, 700, 50);
        description2.setText("Selecciona la dificultad del desafio de conocimientos");
        description2.setFont(bodycustomFont);
        description2.setForeground(bodyColor);
        this.add(description2);
        x = halfx - 250;
        y = 625;
        for (int i = 0; i < difficulty.length; i++) {
            String resource = "../resources/" + difficultyimg[i] + ".fw.png";
            difficultiesimgs[i] = new ImageIcon(this.getClass().getResource(resource));
            scaleddifficultiesimgs[i] = new ImageIcon(difficultiesimgs[i].getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            difficulties[i] = new JButton();
            difficulties[i].setIcon(scaleddifficultiesimgs[i]);
            difficulties[i].setBorder(null);
            difficulties[i].setBounds(x, y, width, height);
            difficulties[i].addActionListener(this);
            this.add(difficulties[i]);
            x += 175;
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == areas[0]) {
            gamearea = area[0];
            if (gamedifficulty.equals("")) {
                JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
            } else {
                User game = new User(user1id, gamearea, gamedifficulty);
                AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                dispose();
            }
        } else {
            if (ae.getSource() == areas[1]) {
                gamearea = area[1];
                if (gamedifficulty.equals("")) {
                    JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                } else {
                    User game = new User(user1id, gamearea, gamedifficulty);
                    AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                    dispose();
                }
            } else {
                if (ae.getSource() == areas[2]) {
                    gamearea = area[2];
                    if (gamedifficulty.equals("")) {
                        JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                    } else {
                        User game = new User(user1id, gamearea, gamedifficulty);
                        AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                        dispose();
                    }
                } else {
                    if (ae.getSource() == areas[2]) {
                        gamearea = area[2];
                        if (gamedifficulty.equals("")) {
                            JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                        } else {
                            User game = new User(user1id, gamearea, gamedifficulty);
                            AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                            dispose();
                        }
                    } else {
                        if (ae.getSource() == areas[3]) {
                            gamearea = area[3];
                            if (gamedifficulty.equals("")) {
                                JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                            } else {
                                User game = new User(user1id, gamearea, gamedifficulty);
                                AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                dispose();
                            }
                        } else {
                            if (ae.getSource() == areas[4]) {
                                gamearea = area[4];
                                if (gamedifficulty.equals("")) {
                                    JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                } else {
                                    User game = new User(user1id, gamearea, gamedifficulty);
                                    AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                    dispose();
                                }
                            } else {
                                if (ae.getSource() == areas[5]) {
                                    gamearea = area[5];
                                    if (gamedifficulty.equals("")) {
                                        JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                    } else {
                                        User game = new User(user1id, gamearea, gamedifficulty);
                                        AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                        dispose();
                                    }
                                } else {
                                    if (ae.getSource() == areas[6]) {
                                        gamearea = area[6];
                                        if (gamedifficulty.equals("")) {
                                            JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                        } else {
                                            User game = new User(user1id, gamearea, gamedifficulty);
                                            AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                            dispose();
                                        }
                                    } else {
                                        if (ae.getSource() == areas[7]) {
                                            gamearea = area[7];
                                            if (gamedifficulty.equals("")) {
                                                JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                            } else {
                                                User game = new User(user1id, gamearea, gamedifficulty);
                                                AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                dispose();
                                            }
                                        } else {
                                            if (ae.getSource() == areas[8]) {
                                                gamearea = area[8];
                                                if (gamedifficulty.equals("")) {
                                                    JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                } else {
                                                    User game = new User(user1id, gamearea, gamedifficulty);
                                                    AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                    dispose();
                                                }
                                            } else {
                                                if (ae.getSource() == areas[9]) {
                                                    gamearea = area[9];
                                                    if (gamedifficulty.equals("")) {
                                                        JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                    } else {
                                                        User game = new User(user1id, gamearea, gamedifficulty);
                                                        AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                        dispose();
                                                    }
                                                } else {
                                                    if (ae.getSource() == areas[10]) {
                                                        gamearea = area[10];
                                                        if (gamedifficulty.equals("")) {
                                                            JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                        } else {
                                                            User game = new User(user1id, gamearea, gamedifficulty);
                                                            AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                            dispose();
                                                        }
                                                    } else {
                                                        if (ae.getSource() == areas[11]) {
                                                            gamearea = area[11];
                                                            if (gamedifficulty.equals("")) {
                                                                JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                            } else {
                                                                User game = new User(user1id, gamearea, gamedifficulty);
                                                                AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                                dispose();
                                                            }
                                                        } else {
                                                            if (ae.getSource() == areas[12]) {
                                                                gamearea = area[12];
                                                                if (gamedifficulty.equals("")) {
                                                                    JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                                } else {
                                                                    User game = new User(user1id, gamearea, gamedifficulty);
                                                                    AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                                    dispose();
                                                                }
                                                            } else {
                                                                if (ae.getSource() == areas[13]) {
                                                                    gamearea = area[13];
                                                                    if (gamedifficulty.equals("")) {
                                                                        JOptionPane.showMessageDialog(areas[7], "Selecciona la dificultad para " + gamearea);
                                                                    } else {
                                                                        User game = new User(user1id, gamearea, gamedifficulty);
                                                                        AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                                                                        dispose();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (ae.getSource() == difficulties[0]) {
            gamedifficulty = difficulty[0];
            if (gamearea.equals("")) {
                JOptionPane.showMessageDialog(areas[7], "Selecciona el área en dificultad " + gamedifficulty);
            } else {
                User game = new User(user1id, gamearea, gamedifficulty);
                AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                dispose();
            }
        } else {
            if (ae.getSource() == difficulties[1]) {
                gamedifficulty = difficulty[1];
                if (gamearea.equals("")) {
                    JOptionPane.showMessageDialog(areas[7], "Selecciona el área en dificultad " + gamedifficulty);
                } else {
                    User game = new User(user1id, gamearea, gamedifficulty);
                    AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                    dispose();
                }
            } else {
                if (ae.getSource() == difficulties[2]) {
                    gamedifficulty = difficulty[2];
                    if (gamearea.equals("")) {
                        JOptionPane.showMessageDialog(areas[7], "Selecciona el área en dificultad " + gamedifficulty);
                    } else {
                        User game = new User(user1id, gamearea, gamedifficulty);
                        AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                        dispose();
                    }
                } else {
                    if (ae.getSource() == difficulties[3]) {
                        gamedifficulty = difficulty[3];
                        if (gamearea.equals("")) {
                            JOptionPane.showMessageDialog(areas[7], "Selecciona el área en dificultad " + gamedifficulty);
                        } else {
                            User game = new User(user1id, gamearea, gamedifficulty);
                            AreaDificultad2 configura = new AreaDificultad2(game, user2id);
                            dispose();
                        }
                    }
                }
            }
        }
        if (ae.getSource() == back){
            Inicio2 regresa = new Inicio2(user1id);
        }
    }

    private static String read(int uId) {
        com.rushtechnologies.spaceride.webservice.Read_Service service = new com.rushtechnologies.spaceride.webservice.Read_Service();
        com.rushtechnologies.spaceride.webservice.Read port = service.getReadPort();
        return port.read(uId);
    }

}