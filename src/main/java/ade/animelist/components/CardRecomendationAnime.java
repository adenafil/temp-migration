package ade.animelist.components;

import ade.animelist.controller.Controller;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardRecomendationAnime {
    JPanel cardPanel;

    AnimePage animePage = new AnimePage();
    private static final int CARD_WIDTH = 300;
    private static final int CARD_HEIGHT = 400;
    private static int[] x = {0, 350, 700, 1050, 1400};
    public static int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private static int normalY = 20;
    ImageIcon refreshImg = ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL("https://img.icons8.com/ios-filled/50/ffffff/refresh--v1.png"), 20, 20);

    JLabel refresh;

    public JLabel getLabelRefresh() {
        refresh = new JLabel("refresh");
        refresh.setOpaque(true);
        refresh.setBackground(Color.decode("#333b48"));
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        refresh.setPreferredSize(new Dimension(1600, 30));
        refresh.setAlignmentX(JLabel.LEFT);
        refresh.setIcon(refreshImg);

        return refresh;
    }

    public JPanel getCard() {

        if (refresh == null) {
            refresh = getLabelRefresh();
        }


        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());


        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1920, 400));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
//        scrollPane.setBackground(Color.BLACK);
//        scrollPane.setForeground(Color.YELLOW);
//        scrollPane.setBackground(Color.RED);
        System.out.println(scrollPane.getBackground());
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel topAnime = new JLabel("Rekomendasi Anime");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        topAnime.setPreferredSize(new Dimension(1600, 30));
        topAnime.setAlignmentX(JLabel.LEFT);

        // bikin getter refresh sehingga ntar lu bisa add Event Listener di sini
//        refresh = new JLabel("refresh");
//        refresh.setOpaque(true);
//        refresh.setBackground(Color.decode("#333b48"));
//        refresh.setForeground(Color.WHITE);
//        refresh.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
//        refresh.setPreferredSize(new Dimension(1600, 30));
//        refresh.setAlignmentX(JLabel.LEFT);
//        refresh.setIcon(refreshImg);


        JPanel panel = new JPanel();
        panel.add(topAnime);
        panel.add(refresh);
        panel.setPreferredSize(new Dimension(1920, 500));
        panel.setBackground(Color.decode("#333b48"));
        panel.setLayout(new FlowLayout());
//        panel.setLayout(null);
//        panel.setBounds(20, 20, 1000, 1000);
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    public void removePanel() {
        this.cardPanel.removeAll();
    }


    public void addCard(String titleAnime, ImageIcon imgAnime, int id) {

        if (titleAnime.length() > 40) {
            String temp = "";
            for (int i = 0; i < 40; i++) {
                temp += titleAnime.charAt(i);
            }

            String titik = ".....";
            temp = temp + titik;

            titleAnime = temp;
        }


        System.out.println("debug : " + x[index]);

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setBackground(Color.decode("#333b48"));

        JLabel img = new JLabel();
        img.setOpaque(true);
        img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 100));
        img.setBackground(Color.gray);
        img.setIcon(imgAnime);

        JLabel title = new JLabel("<html><p> " + titleAnime + " </p></html>");
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(CARD_WIDTH, 60));
        title.setForeground(Color.WHITE);
        title.setBackground(Color.decode("#333b48"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 22));

        card.add(img);
        card.add(title);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x[index];
        gbc.gridy = normalY;
        gbc.insets = new Insets(10, 10, 10, 10);
        cardPanel.add(card, gbc);

        JLabel idAnime = new JLabel(id + "");
        idAnime.setFont(new Font(null, Font.BOLD, 0));

        index++;
        if (index > 4) {
            index = 0;
            normalY += 300;
        }

        card.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        System.out.println("mama huhu");
                        System.out.println(title.getText());
                        System.out.println(id);
                        try {
//                            Controller.navbar.getRecomendationAnimeDiv().removeAll();
//                            Controller.navbar.getTopAnime().removeAll();
                            Controller.navbar.syncDelete();
                            Controller.navbar.removeRecomdendationCardComponent();
                            Controller.navbar.removeTopCardComponent();
                            Controller.navbar.bingung = 999;

                            Controller.addComponent(animePage.getAnimePageById(id));
                            Controller.doScync();
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );

        Controller.navbar.logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                animePage.removeContainer();
                System.out.println("mamama");
//                Controller.navbar.addTopCardAnime();
//                Controller.navbar.addRecomendationAnime();
            }
        });


    }

    public JLabel getRefresh() {
        System.out.println(refresh == null);
        return this.refresh;
    }

}

