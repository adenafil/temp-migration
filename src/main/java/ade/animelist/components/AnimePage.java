package ade.animelist.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AnimePage {
    static Navbar navbar = new Navbar();
    static JFrame frame =  new JFrame();

    public static void main(String[] args) {
        // Jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(new Dimension(1920, 1080));
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);
        frame.add(navbar.getNavbar());

        // container di bawah navbar
        JPanel contaienrDiv = new JPanel();
        contaienrDiv.setOpaque(true);
        contaienrDiv.setLayout(new BoxLayout(contaienrDiv, BoxLayout.Y_AXIS));
        contaienrDiv.setPreferredSize(new Dimension(1920, 1000));
        contaienrDiv.setBackground(Color.GREEN);

        // buat judul
        JLabel judul = new JLabel("Title : Sousou No Frieren - 2023");
        judul.setOpaque(true);
//        judul.setPreferredSize(new Dimension(1920, 30));
        judul.setMaximumSize(new Dimension(1920, 30));
        judul.setBackground(Color.RED);
        judul.setForeground(Color.WHITE);
        judul.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
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

        kotakAnimeDesc.add(createKotak("PERINGKAT", "1"), gbcPeringkat);


        kotakAnimeDesc.add(createKotak("SKOR", "9.12"), gbcPeringkat);
        kotakAnimeDesc.add(createKotak("RATING", "PG-13 - Teens 13"), gbcPeringkat);
        kotakAnimeDesc.add(createKotak("TOTAL EPISODES", "28"), gbcPeringkat);

        // masukan kotak ke conatiner
        contaienrDiv.add(kotakAnimeDesc);

        JPanel imageAndSynopsis = new JPanel();
        imageAndSynopsis.setLayout(new BoxLayout(imageAndSynopsis, BoxLayout.X_AXIS));
        imageAndSynopsis.setMaximumSize(new Dimension(1920, 500));
        imageAndSynopsis.setOpaque(true);
        imageAndSynopsis.setBackground(Color.RED);

        JLabel imageAnime = new JLabel();
        imageAnime.setOpaque(true);
        imageAnime.setPreferredSize(new Dimension(450, 500));
        imageAnime.setMaximumSize(new Dimension(450, 1000));
        imageAnime.setBackground(Color.GRAY);
        imageAnime.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel synopsis = new JLabel();
        synopsis.setOpaque(true);
        synopsis.setPreferredSize(new Dimension(1500, 500));
        synopsis.setMaximumSize(new Dimension(1500, 1000));
        synopsis.setBackground(Color.magenta);
        synopsis.setText(
                "<html>" +
                        "<p> There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.</p>"
                        +
                        "</html>"
        );
        synopsis.setFont(new Font(Font.MONOSPACED, Font.TYPE1_FONT, 20));
        synopsis.setForeground(Color.WHITE);
        synopsis.setVerticalAlignment(SwingConstants.TOP);
        synopsis.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//
        imageAnime.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imageAndSynopsis.add(imageAnime);
        imageAndSynopsis.add(synopsis);

        contaienrDiv.add(imageAndSynopsis);

        // below synopsis and image anime

        GridBagConstraints alignmentSection = new GridBagConstraints();

        alignmentSection.insets = new Insets(10, 10, 10, 10);

        JPanel sectionUserInterface = new JPanel();
        sectionUserInterface.setLayout(new GridBagLayout());
        sectionUserInterface.setOpaque(true);
        sectionUserInterface.setPreferredSize(new Dimension(1920, 300));
        sectionUserInterface.setMaximumSize(new Dimension(1920, 300));
        sectionUserInterface.setBackground(Color.CYAN);

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


        button.addActionListener(e -> {
            System.out.println("difarina");
            statusComboBox.setVisible(true);
            episodeIndex.setVisible(true);
            addAnime.setVisible(true);
            subtractAnime.setVisible(true);

            button.setText("Save Changes");

            // Repaint
            frame.revalidate();
            frame.repaint();

            addAnime.setVisible(true);

        });
        contaienrDiv.add(sectionUserInterface);


        frame.add(contaienrDiv);
        frame.setVisible(true);

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
