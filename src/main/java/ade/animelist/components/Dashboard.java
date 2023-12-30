package ade.animelist.components;

import ade.animelist.controller.Controller;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import ade.animelist.database.repository.ConfigRepository;
import ade.animelist.database.repository.ConfigRepositoryImpl;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard {
    private static int indexListenerCollection = 0;
    public static JPanel dashboardDiv = new JPanel();
    private static ConfigRepository configRepository = new ConfigRepositoryImpl();
    public static boolean isOpened = false;
    public static JPanel getDashboard() {
        dashboardDiv.setOpaque(true);
        dashboardDiv.setPreferredSize(new Dimension(1920, 980));
        dashboardDiv.setMaximumSize(new Dimension(1920, 980));
        dashboardDiv.setBackground(Color.decode("#333b48"));

        // container for welcoming name
        JPanel containerFeat = new JPanel();
        containerFeat.setOpaque(true);
        containerFeat.setLayout(new BoxLayout(containerFeat, BoxLayout.Y_AXIS));
        containerFeat.setPreferredSize(new Dimension(1920, 600));
        containerFeat.setMaximumSize(new Dimension(1920, 600));
        containerFeat.setBackground(Color.decode("#333b48"));

        // add to container bg below navbar
        dashboardDiv.add(containerFeat);

        // logic name
        JPanel containerName = new JPanel();
        containerName.setOpaque(true);
        containerName.setPreferredSize(new Dimension(1920, 100));
        containerName.setMaximumSize(new Dimension(1920, 100));
        containerName.setBackground(Color.decode("#333b48"));

        JLabel name = new JLabel("Welcome, @" + configRepository.getCurrentUsername());
        name.setForeground(Color.WHITE);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));

        containerName.add(name);

        containerFeat.add(containerName);

        // logic profile
        JPanel containerProfile = new JPanel();
        containerProfile.setOpaque(true);
        containerProfile.setPreferredSize(new Dimension(1920, 350));
        containerProfile.setMaximumSize(new Dimension(1920, 350));
        containerProfile.setBackground(Color.decode("#333b48"));

        JPanel profile = new JPanel();
        profile.setOpaque(true);
        profile.setPreferredSize(new Dimension(350, 350));
        profile.setMaximumSize(new Dimension(350, 350));
        profile.setBackground(Color.decode("#" + getHexaColor(configRepository.getCurrentUsername())));

        JLabel textIfProfileNotExist = new JLabel(configRepository.getCurrentUsername().toUpperCase().charAt(0) + "");
        textIfProfileNotExist.setOpaque(true);
        textIfProfileNotExist.setPreferredSize(new Dimension(350, 350));
        textIfProfileNotExist.setMaximumSize(new Dimension(350, 350));
        textIfProfileNotExist.setBackground(Color.decode("#" + getHexaColor(configRepository.getCurrentUsername())));
        textIfProfileNotExist.setFont(new Font(Font.SERIF, Font.BOLD, 250));
        textIfProfileNotExist.setVerticalAlignment(SwingConstants.CENTER);
        textIfProfileNotExist.setHorizontalAlignment(SwingConstants.CENTER);

        profile.add(textIfProfileNotExist);

        containerProfile.add(profile);

        containerFeat.add(containerProfile);

        JPanel containerButton = new JPanel();
        containerButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        containerButton.setOpaque(true);
        containerButton.setPreferredSize(new Dimension(1920, 150));
        containerButton.setMaximumSize(new Dimension(1920, 150));
        containerButton.setBackground(Color.decode("#333b48"));

        JButton myCollectionBtn = new JButton("My Collection");
        myCollectionBtn.setOpaque(true);
        myCollectionBtn.setBackground(Color.ORANGE);
        myCollectionBtn.setForeground(Color.decode("#333b48"));
        myCollectionBtn.setPreferredSize(new Dimension(300, 70));
        myCollectionBtn.setMaximumSize(new Dimension(300, 70));
        myCollectionBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        myCollectionBtn.setBorder(BorderFactory.createEmptyBorder());
        myCollectionBtn.setFocusable(false);

        myCollectionBtn.addActionListener(e -> {
            Dashboard.isOpened = false;
            CardCollection.isOpened = true;
            dashboardDiv.removeAll();
            Controller.removeComponent(dashboardDiv);

            JPanel card = CardCollection.getCard();


            AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();


            if (indexListenerCollection  == 0) {
                listAnimeuser.getAllAnimeListInDatabaseUser().forEach(
                        maguire -> {
                            CardCollection.addCard(
                                    maguire.title,
                                    null,
                                    maguire.malId
                            );
                        }
                );
            }
//            else {
//                listAnimeuser.getAllAnimeListUser().forEach(
//                        luAsikBang -> {
//                            ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
//                            System.out.println("is image null ? " + ade);
//                            CardCollection.addCard(
//                                    luAsikBang.title,
//                                    ade,
//                                    luAsikBang.malId
//                            );
//                        }
//                );
//            }


            CardCollection.setIndex(0);
////            for (int i = 0; i < 20; i++) {
////                ImageIcon imgTes = ImageRenderer.createImageIconByURL("https://cdn.myanimelist.net/images/anime/13/17405.jpg");
////                CardCollection.addCard("naruto", imgTes, 20);
////            }
//
            Controller.addComponent(card);
//
//            CardCollection.setIndex(0);
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(listAnimeuser.getAllAnimeListUser());
                    imageLoaderWorker.execute();

                    listAnimeuser.getAllAnimeListUser().forEach(
                            luAsikBang -> {
//                                CardCollection.addCard(
//                                        luAsikBang.title,
                                        ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl);
//                                        luAsikBang.malId
//                                );
                            }
                    );

                    if (indexListenerCollection == 0) {
                        indexListenerCollection++;
                    } else {
                        listAnimeuser.getAllAnimeListUser().forEach(
                                luAsikBang -> {
                                CardCollection.addCard(
                                        luAsikBang.title,
                                    ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl),
                                        luAsikBang.malId
                                );
                                }
                        );
                        Controller.addComponent(card);
                    }

                    CardCollection.setIndex(0);

                    Thread.sleep(5000);

                    return null;
                }
//
//                @Override
//                protected void done() {
////                    JOptionPane.showMessageDialog(null, "Image Renderer is successful", "Informasi", JOptionPane.INFORMATION_MESSAGE);
//                    CardCollection.cardPanel.removeAll();
//                    CardCollection.cardPanel = new JPanel();
//                    listAnimeuser.getAllAnimeListUser().forEach(
//                            luAsikBang -> {
//                                CardCollection.addCard(
//                                        luAsikBang.title,
//                                ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl),
//                                        luAsikBang.malId
//                                );
//                            }
//                    );
//
//                }
            };

            worker.execute();


        });


        JButton signOutBtn = new JButton("Sign Out");
        signOutBtn.setOpaque(true);
        signOutBtn.setBackground(Color.ORANGE);
        signOutBtn.setForeground(Color.decode("#333b48"));
        signOutBtn.setPreferredSize(new Dimension(200, 70));
        signOutBtn.setMaximumSize(new Dimension(200, 70));
        signOutBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        signOutBtn.setBorder(BorderFactory.createEmptyBorder());
        signOutBtn.setFocusable(false);

        JButton settingBtn = new JButton("Setting");
        settingBtn.setOpaque(true);
        settingBtn.setBackground(Color.ORANGE);
        settingBtn.setForeground(Color.decode("#333b48"));
        settingBtn.setPreferredSize(new Dimension(200, 70));
        settingBtn.setMaximumSize(new Dimension(200, 70));
        settingBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        settingBtn.setBorder(BorderFactory.createEmptyBorder());
        settingBtn.setFocusable(false);


        containerButton.add(myCollectionBtn);
        containerButton.add(settingBtn);
        containerButton.add(signOutBtn);

        containerFeat.add(containerButton);

        return dashboardDiv;
    }

    public static String getHexaColor(String name) {
        int hashCode = name.hashCode();

        // Tetapkan nilai tetap untuk komponen merah dan hijau
        int red = 100;
        int green = 150;

        // Gunakan nilai hashCode untuk komponen biru
        int blue = Math.abs(hashCode % 256); // Ambil nilai absolute untuk menghindari nilai negatif

        Color color = new Color(red, green, blue);

        return Integer.toHexString(color.getRGB()).substring(2).toUpperCase();

    }
}
