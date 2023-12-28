package ade.animelist.components;
import ade.animelist.api.JikanAPI;
import ade.animelist.controller.Controller;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.enums.AgeRating;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;


public class Navbar extends JFrame {
    JLabel logo;
    JTextField search;
    private String searchByUser;
    private CardSearchAnime cardSearchAnime = new CardSearchAnime();
    private CardTopAnime cardTopAnime = new CardTopAnime();
    private JPanel topAnimeDiv = cardTopAnime.getCard();
    private JPanel searchAnimeDiv = cardSearchAnime.getCard();
    private CardRecomendationAnime cardRecomendationAnime = new CardRecomendationAnime();

    private JPanel recomendationAnimeDiv = cardRecomendationAnime.getCard();
    int bingung = 0;

    public JPanel getNavbar() {
        // div
        JPanel divContainer = new JPanel();
        divContainer.setLayout(new BoxLayout(divContainer, BoxLayout.Y_AXIS));
        divContainer.setSize(1920, 100);
        divContainer.setOpaque(true);
        divContainer.setBackground(Color.ORANGE);

        // div
        JPanel divNav = new JPanel();
        divNav.setOpaque(true);
        divNav.setBackground(Color.ORANGE);

//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(1920, 1080);
//        this.setResizable(false);
//        this.setLayout(null);
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Component Logo
        logo = new JLabel("ADEANIMELIST");
        logo.setOpaque(true);
//        logo.setPreferredSize(new Dimension(200, 200));
//        logo.setLayout(null);
        logo.setBounds(10, 0, 400, 200);
        logo.setForeground(Color.BLACK);
        logo.setBackground(Color.ORANGE);
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        // Component JTextField

        search = new JTextField();
//        search.setBounds(500, 0, 1000, 40);
        search.setPreferredSize(new Dimension(500, 40));
        search.setText("Cari anime...");
        search.setFont(new Font(null, Font.BOLD, 20));

        // Component Dasboard
        JLabel dashboard = new JLabel("Dashbsoard");
        dashboard.setOpaque(true);
        dashboard.setBounds(1000, 0, 120, 100);
        dashboard.setFont(new Font(null, Font.PLAIN, 20));
        dashboard.setForeground(Color.BLACK);
        dashboard.setBackground(Color.ORANGE);

        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (cardTopAnime != null && cardRecomendationAnime != null) {
                    bingung = 3939;
                    cardTopAnime.removePanel();
                    cardRecomendationAnime.removePanel();

                    removeTopCardComponent();
                    removeRecomdendationCardComponent();
                }

                if (searchAnimeDiv != null) {
                    removeSearchAnimeCard();
                }

                if (cardTopAnime.animePage != null) {
                    cardTopAnime.animePage.removeContainer();
                }

                if (cardRecomendationAnime.animePage != null) {
                    cardRecomendationAnime.animePage.removeContainer();
                }

                if (cardSearchAnime.animePage != null) {
                    cardSearchAnime.animePage.removeContainer();
                }

                if (CardCollection.cardPanel != null) {
                    System.out.println("HEllo");
                    Controller.removeComponent(CardCollection.panel);
                    CardCollection.cardPanel.removeAll();
                    CardCollection.panel.removeAll();

                }

                if (CardCollection.animePage.contaienrDiv != null) {
                    System.out.println("IFELSE");
                    Controller.removeComponent(CardCollection.animePage.contaienrDiv);
                    CardCollection.animePage.contaienrDiv.removeAll();
                }



                Controller.addComponent(Dashboard.getDashboard());

            }
        });


        // Listener
        cardRecomendationAnime.refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                recomendationAnimeDiv.removeAll();
                removeRecomdendationCardComponent();

                recomendationAnimeDiv = cardRecomendationAnime.getCard();
                addRecomendationAnime();
                System.out.println("difarina");
            }
        });

        // listener
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("logo nih puh sepuh");

                System.out.println(cardRecomendationAnime.refresh);


                if (topAnimeDiv != null) {
                    System.out.println("mamamam nenene" + topAnimeDiv != null);
                    recomendationAnimeDiv.removeAll();
                    removeRecomdendationCardComponent();

                    topAnimeDiv.removeAll();
                    removeTopCardComponent();

                    topAnimeDiv = cardTopAnime.getCard();

                    recomendationAnimeDiv = cardRecomendationAnime.getCard();
                    addRecomendationAnime();
                }

                if (bingung == 33221 && CardCollection.animePage != null) {
                    System.out.println("MEssi");
                    Controller.removeComponent(CardCollection.animePage.contaienrDiv);
                    CardCollection.animePage.contaienrDiv.removeAll();
//                    addTopCardAnime();
                    addRecomendationAnime();
                    bingung = 0;
                }

                if (bingung < 100) {
                    removeSearchAnimeCard();
                    addTopCardAnime();
                    addRecomendationAnime();
                    bingung = 0;
                }

                // ini untuk adding up
                if (bingung == 999) {
                    System.out.println("hiihihj");
                    addTopCardAnime();
                    addRecomendationAnime();
                    bingung = 0;
                }

                if (bingung == 3939) {
                    addTopCardAnime();
                    addRecomendationAnime();
                    System.out.println("Mmaamma huthuthuthut");
                    Dashboard.dashboardDiv.removeAll();
                    Controller.removeComponent(Dashboard.dashboardDiv);
                    bingung = 0;
                }

                if (bingung == 6767) {
                    System.out.println("HEllo");
                    Controller.removeComponent(CardCollection.panel);
                    CardCollection.cardPanel.removeAll();
                    CardCollection.panel.removeAll();
                    addTopCardAnime();
                    addRecomendationAnime();
                    bingung = 0;
                }

            }
        });

        search.addActionListener(e -> {
//            if (cardSearchAnime.cardPanel != null) {
//                removeSearchAnime();
//            }
            bingung++;
            addSearchAnimeCard();

            if (cardTopAnime != null && cardRecomendationAnime != null) {
                cardTopAnime.removePanel();
                cardRecomendationAnime.removePanel();

                removeTopCardComponent();
                removeRecomdendationCardComponent();
            }

            if (CardCollection.cardPanel != null && CardCollection.panel != null) {
                Controller.removeComponent(CardCollection.panel);
                CardCollection.panel.removeAll();
            }

            if (Dashboard.dashboardDiv != null) {
                Dashboard.dashboardDiv.removeAll();
                Controller.removeComponent(Dashboard.dashboardDiv);
                bingung = 0;

            }
//            cardSearchAnime = new CardSearchAnime();
//            Controller.addComponent(cardSearchAnime.getCard());
            if (e.getSource() == search ) {
                cardSearchAnime.removeData();
                cardSearchAnime.setIndex(0);
                System.out.println(search.getText());
                setSearchByUser(search.getText());

                try {
                    JikanAPI.getTitleAndImageAnimeBySearchAsync(search.getText())
                            .subscribeOn(Schedulers.parallel())
                            .subscribe(
                                    animeList -> {
                                        ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                                        imageLoaderWorker.execute();
                                        animeList.stream().parallel().forEach(bayor -> addAnime(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId));
                                    },
                                    throwable -> {
                                        System.out.println("error : " + throwable.getMessage());
                                    }
                            );
                } catch (JikanQueryException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


//        add(dashboard);
//        add(search);
//        add(logo);
//
        divNav.add(Box.createVerticalStrut(80));
        divNav.add(logo);
        divNav.add(Box.createHorizontalStrut(500));
        divNav.add(search);
        divNav.add(Box.createHorizontalStrut(500));
        divNav.add(dashboard);


        divContainer.add(divNav);
//        add(divContainer);

        return divContainer;
    }

    public String getSearchByUser() {
        return searchByUser;
    }

    public void setSearchByUser(String search) {
        this.searchByUser = search;
    }


    public static void main(String[] args) {
        Navbar navbar = new Navbar();
        navbar.add(navbar.getNavbar());
        navbar.setVisible(true);
    }

    public void addTopCardAnime() {
        try {
            JikanAPI.getTopAnime()
                    .subscribeOn(Schedulers.parallel())
                    .subscribe(
                            animeList -> {
                                ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                                imageLoaderWorker.execute();
                                animeList.forEach(bayor -> cardTopAnime.addCard(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId));
                            },
                            throwable -> {
                                System.out.println("error : " + throwable.getMessage());
                            }
                    );
            Controller.addComponent(topAnimeDiv);
        } catch (JikanQueryException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addRecomendationAnime() {
        JikanAPI.getRecommendationAnimeAsync()
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                        animeList -> {
                            ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                            imageLoaderWorker.execute();
                            // fix indexing
                            CardRecomendationAnime.index = 0;
                            animeList.forEach(
                                    (bayor) -> {
                                        if (isHalalAnime(bayor)) {
                                            cardRecomendationAnime.addCard(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId);
                                        }
                                    }
                            );
                        },
                        throwable -> {
                            System.out.println("error : " + throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
        Controller.addComponent(recomendationAnimeDiv);
    }

    private boolean isHalalAnime(Anime anime) {
        return anime.rating == AgeRating.PG13;
    }

    public void addHomeAnime() {
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel home = new JPanel();
        home.setSize(1920, 1000);
        home.setLayout(new GridLayout());

        gbc.gridy = 1;

        home.add(topAnimeDiv, gbc);

        gbc.gridy = 2;

        home.add(recomendationAnimeDiv, gbc);

        Controller.addComponent(home);
    }
    public void addSearchAnimeCard() {
        Controller.addComponent(searchAnimeDiv);
    }

    public void removeSearchAnimeCard() {
        Controller.removeComponent(searchAnimeDiv);
    }

    public void removeTopCardComponent() {
        Controller.removeComponent(topAnimeDiv);
    }

    public void removeRecomdendationCardComponent() {
        Controller.removeComponent(recomendationAnimeDiv);
    }

    public CardSearchAnime getCardSearch() {
        return cardSearchAnime;
    }


    public CardTopAnime getCardTopAnime() {
        if (cardTopAnime.getCard() == null) {
            System.out.println("okay");
        }
        return cardTopAnime;
    }

    public void setCardTopAnime() {

    }

    public void setCardSearchAnime(CardSearchAnime cardSearchAnime) {
        this.cardSearchAnime = cardSearchAnime;
    }

    public void addAnime(String title,ImageIcon image, int id) {
        getCardSearch().addCard(title, image, id);
    }

    public JPanel getTopAnime() {
        return topAnimeDiv;
    }

    public JPanel getRecomendationAnimeDiv() {
        return  recomendationAnimeDiv;
    }

    public void syncDelete() {
        cardTopAnime.removePanel();
        cardRecomendationAnime.removePanel();

        removeTopCardComponent();
        removeRecomdendationCardComponent();
//
    }

}
