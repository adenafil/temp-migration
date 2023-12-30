package ade.animelist.components;

import ade.animelist.api.JikanAPI;
import ade.animelist.controller.Controller;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AnimePage {
    public static boolean isOpened = false;
    public static JPanel contaienrDiv = null;

    public static JPanel getAnimePageById(int id) throws JikanQueryException {
        // anime
        Anime anime = JikanAPI.getAnimeById(id);

        // container di bawah navbar
        contaienrDiv = new JPanel();
        contaienrDiv.setOpaque(true);
        contaienrDiv.setLayout(new BoxLayout(contaienrDiv, BoxLayout.Y_AXIS));
//        contaienrDiv.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        contaienrDiv.setPreferredSize(new Dimension(1920, 1000));
        contaienrDiv.setBackground(Color.decode("#333b48"));

        // buat judul
        JLabel judul = new JLabel(anime.title);
        judul.setOpaque(true);
//        judul.setPreferredSize(new Dimension(1920, 30));
        judul.setMaximumSize(new Dimension(1920, 30));
        judul.setBackground(Color.decode("#333b48"));
        judul.setForeground(Color.WHITE);
        judul.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        judul.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        // tambahkan judul ke container
        contaienrDiv.add(judul);

        // buat desc kotak2
        JPanel kotakAnimeDesc = new JPanel();
        kotakAnimeDesc.setLayout(new GridBagLayout());
        kotakAnimeDesc.setOpaque(true);
        kotakAnimeDesc.setMaximumSize(new Dimension(1920, 100));
        kotakAnimeDesc.setBackground(Color.decode("#333b48"));
//        anAnime.setForeground(Color.BLUE);
//

        GridBagConstraints gbcPeringkat = new GridBagConstraints();

//f
//        gbcPeringkat.anchor = GridBagConstraints.WEST;


        gbcPeringkat.insets = new Insets(10, 10, 10, 10);

        // kotak peringkat
        kotakAnimeDesc.add(createKotak("PERINGKAT", ""+anime.rank), gbcPeringkat);

        // kotak skor
        kotakAnimeDesc.add(createKotak("SKOR", "" + anime.score), gbcPeringkat);

        // Kotak Rating
        kotakAnimeDesc.add(createKotak("RATING", "" + anime.rating), gbcPeringkat);

        // Kotak Episodes
        kotakAnimeDesc.add(createKotak("TOTAL EPISODES", ""+anime.episodes), gbcPeringkat);

        // masukan kotak ke conatiner
        contaienrDiv.add(kotakAnimeDesc);

        // container
        JPanel imageAndSynopsis = new JPanel();
        imageAndSynopsis.setLayout(new BoxLayout(imageAndSynopsis, BoxLayout.X_AXIS));
        imageAndSynopsis.setMaximumSize(new Dimension(1920, 500));
        imageAndSynopsis.setOpaque(true);
        imageAndSynopsis.setBackground(Color.RED);

        // imageIcon

        ImageIcon animeIcon = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);

        JLabel imageAnime = new JLabel();
        imageAnime.setOpaque(true);
        imageAnime.setPreferredSize(new Dimension(450, 500));
        imageAnime.setMaximumSize(new Dimension(450, 1000));
        imageAnime.setBackground(Color.decode("#333b48"));
        imageAnime.setHorizontalAlignment(JLabel.CENTER);
        imageAnime.setIcon(ImageRenderer.setImageIconSize(animeIcon, 450, 500));
        imageAnime.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // the synopsis
        JLabel synopsis = new JLabel();
        synopsis.setOpaque(true);
        synopsis.setPreferredSize(new Dimension(1500, 500));
        synopsis.setMaximumSize(new Dimension(1500, 1000));
        synopsis.setBackground(Color.decode("#333b48"));
//        synopsis.setText(
//                "<html>" +
//                        "<p> There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.</p>"
//                        +
//                        "</html>"
//        );
        synopsis.setText(
                "<html>" + "<p>" + anime.synopsis + "</p>" + "</html>"
        );
        synopsis.setFont(new Font(Font.MONOSPACED, Font.TYPE1_FONT, 20));
        synopsis.setForeground(Color.WHITE);
        synopsis.setVerticalAlignment(SwingConstants.TOP);
        synopsis.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//
        imageAnime.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // adding up into container
        imageAndSynopsis.add(imageAnime);
        imageAndSynopsis.add(synopsis);

        // then adding up into another component
        contaienrDiv.add(imageAndSynopsis);

        // below synopsis and image anime or button adding up and substracting

        GridBagConstraints alignmentSection = new GridBagConstraints();

        alignmentSection.insets = new Insets(10, 10, 10, 10);

        JPanel sectionUserInterface = new JPanel();
        sectionUserInterface.setLayout(new GridBagLayout());
        sectionUserInterface.setOpaque(true);
        sectionUserInterface.setPreferredSize(new Dimension(1920, 300));
        sectionUserInterface.setMaximumSize(new Dimension(1920, 300));
        sectionUserInterface.setBackground(Color.decode("#333b48"));

        JButton button = new JButton("Add To Collection");
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(180, 40));
        button.setBackground(Color.ORANGE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        alignmentSection.gridx = 0;
        sectionUserInterface.add(button, alignmentSection);

        String[] statusOptions = {"WATCHING", "COMPLETED", "PLAN TO WATCH", "DROPPED"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setVisible(false);

        alignmentSection.gridx = 1;
        sectionUserInterface.add(statusComboBox, alignmentSection);


        JLabel episodeIndex = new JLabel("Your Progress Episode On : 1");
        episodeIndex.setOpaque(true);
        episodeIndex.setPreferredSize(new Dimension(200, 40));
        episodeIndex.setBorder(new LineBorder(Color.WHITE, 1));
        episodeIndex.setForeground(Color.WHITE);
        episodeIndex.setBackground(Color.decode("#333b48"));
        episodeIndex.setHorizontalAlignment(SwingConstants.CENTER);
        episodeIndex.setVisible(false);

        alignmentSection.gridx = 2;
        sectionUserInterface.add(episodeIndex, alignmentSection);

        JButton addAnime = new JButton("Add+");

        addAnime.setOpaque(true);
        addAnime.setPreferredSize(new Dimension(60, 40));
        addAnime.setBackground(Color.ORANGE);
        addAnime.setBorder(BorderFactory.createEmptyBorder());
        addAnime.setFocusPainted(false);
        addAnime.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        addAnime.setVisible(false);

        alignmentSection.gridx = 3;
        sectionUserInterface.add(addAnime, alignmentSection);

        JButton subtractAnime = new JButton("Subract-");

        subtractAnime.setOpaque(true);
        subtractAnime.setPreferredSize(new Dimension(100, 40));
        subtractAnime.setBackground(Color.ORANGE);
        subtractAnime.setBorder(BorderFactory.createEmptyBorder());
        subtractAnime.setFocusPainted(false);
        subtractAnime.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        subtractAnime.setVisible(false);

        alignmentSection.gridx = 4;
        sectionUserInterface.add(subtractAnime, alignmentSection);

        // TODOS
        // Buatlah sebuah button yang ketika add to list dan lain lain terhubung dengan database
        // save changes akan menmapilkan notif either berhasil menyimpan atau sebaliknya
        button.addActionListener(e -> {
            System.out.println(button.getText());

            System.out.println("difarina");
            statusComboBox.setVisible(true);
            episodeIndex.setVisible(true);
            addAnime.setVisible(true);
            subtractAnime.setVisible(true);

            button.setText("Save Changes");


            addAnime.setVisible(true);

        });
        contaienrDiv.add(sectionUserInterface);

//        Controller.navbar.search.addActionListener(e -> {
//            Controller.removeComponent(contaienrDiv);
//        });

        return  contaienrDiv;
//        frame.add(contaienrDiv);
//        frame.setVisible(true);

    }

    public static void removeContainer() {
        System.out.println("is it null = " + contaienrDiv == null + " s");
        Controller.removeComponent(contaienrDiv);
    }

    public static JPanel createKotak(String field, String value) {
        JPanel kotak = new JPanel();
        kotak.setOpaque(true);
        kotak.setPreferredSize(new Dimension(250, 80));
        kotak.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); // Menetapkan tinggi maksimum agar tidak dapat melebihi 80 piksel
        kotak.setBackground(Color.decode("#333b48"));
        kotak.setBorder(new LineBorder(Color.WHITE, 1));

        JLabel peringkatText = new JLabel(field);
        peringkatText.setOpaque(true);
        peringkatText.setPreferredSize(new Dimension(230, 25));
        peringkatText.setForeground(Color.WHITE);
        peringkatText.setBackground(Color.decode("#333b48"));
        peringkatText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        peringkatText.setHorizontalAlignment(SwingConstants.CENTER);
//        peringkatText.setVerticalAlignment(SwingConstants.CENTER);

        JLabel valuePeringkat = new JLabel(value);
        valuePeringkat.setOpaque(true);
        valuePeringkat.setPreferredSize(new Dimension(230, 25));
        valuePeringkat.setForeground(Color.WHITE);
        valuePeringkat.setBackground(Color.decode("#333b48"));
        valuePeringkat.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        valuePeringkat.setHorizontalAlignment(SwingConstants.CENTER);

        kotak.add(peringkatText);
        kotak.add(valuePeringkat);


        return kotak;
    }

}
