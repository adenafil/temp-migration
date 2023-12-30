package ade.animelist.controller;

import ade.animelist.components.*;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Controller {
    public static Navbar navbar = new Navbar();
    public static JFrame frame =  new JFrame();

//    static {
//        ImageRenderer.runConfig();
//    }


    public static void run() {
        SwingUtilities.invokeLater(Controller::createAndShowGUI);
    }

    public static void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);
        createLogin();

//        addComponent(navbar.getNavbar());
//        navbar.addTopCardAnime();
//        navbar.addRecomendationAnime();
//        try {
//            AnimePage animePage = new AnimePage();
//            addComponent(animePage.getAnimePageById(20));
//        } catch (JikanQueryException e) {
//            e.printStackTrace();
//        }
////        navbar.addHomeAnime();

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

    public static void createAdeAnimeList() {
        addComponent(navbar.getNavbar());
        navbar.addTopCardAnime();
        navbar.addRecomendationAnime();
    }

    public static void removeSignUp() {
        SignUp.container.removeAll();
        removeComponent(SignUp.container);
    }

    public static void createSignUp() {
        addComponent(SignUp.getSignUp());
    }

    public static void removeLogin() {
        Login.container.removeAll();
        removeComponent(Login.container);
    }

    public static void createLogin() {
        addComponent(Login.getLogin());
    }

    public static void createDasshboard() {
        addComponent(navbar.getNavbar());
        Dashboard.isOpened = true;
        addComponent(Dashboard.getDashboard());
    }

    public static void removeDashboard() {
        Dashboard.dashboardDiv.removeAll();
        removeComponent(Dashboard.dashboardDiv);
    }


    public static void removeComponent(JPanel div) {
        if (div != null) {
            frame.remove(div);
            frame.setVisible(true);
            frame.repaint();
            frame.revalidate();
        }
    }

    public static void doScync() {
        frame.repaint();
        frame.revalidate();
    }
}
