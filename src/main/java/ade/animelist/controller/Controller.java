package ade.animelist.controller;

import ade.animelist.components.CardSearchAnime;
import ade.animelist.components.CardTopAnime;
import ade.animelist.components.Navbar;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private static Navbar navbar = new Navbar();
    public static JFrame frame =  new JFrame();

    public static void run() {
        SwingUtilities.invokeLater(Controller::createAndShowGUI);
    }

    public static void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);

        addComponent(navbar.getNavbar());
        navbar.addTopCardAnime();
        navbar.addRecomendationAnime();
//        navbar.addHomeAnime();

//        CardSearchAnime searchAnime = new CardSearchAnime();
//        CardTopAnime cardTopAnime = new CardTopAnime();
//        addComponent(cardTopAnime.getCard());
//        frame.add(navbar.getNavbar());
//        addComponent(navbar.getNavbar());
//        navbar.setCardSearchAnime(searchAnime);
//        frame.add(searchAnime.getCard());
//        frame.add(cardTopAnime.getCard());

        frame.setVisible(true);

    }

    public static void addComponent(JPanel div) {
        frame.add(div);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }

    public static void removeComponent(JPanel div) {
        frame.remove(div);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }
}
