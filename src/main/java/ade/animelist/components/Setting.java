package ade.animelist.components;

import ade.animelist.components.Navbar;

import javax.swing.*;
import java.awt.*;

public class Setting {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Navbar navbar = new Navbar();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);
        frame.add(navbar.getNavbar());

        JPanel settingContainer = new JPanel();
        settingContainer.setOpaque(true);
        settingContainer.setPreferredSize(new Dimension(1920, 1000));
        settingContainer.setMaximumSize(new Dimension(1920, 1000));
        settingContainer.setBackground(Color.PINK);

        JPanel nganggur = new JPanel();
        nganggur.setOpaque(true);
        nganggur.setPreferredSize(new Dimension(1920, 50));
        nganggur.setMaximumSize(new Dimension(1920, 50));
        nganggur.setBackground(Color.RED);

        JPanel kotak = new JPanel();
        kotak.setOpaque(true);
        kotak.setPreferredSize(new Dimension(1000, 700));
        kotak.setMaximumSize(new Dimension(1000, 700));
        kotak.setBackground(Color.GRAY);

        JLabel setting = new JLabel("Setting Profile");
        setting.setOpaque(true);
        setting.setPreferredSize(new Dimension(1000, 60));
        setting.setMaximumSize(new Dimension(1000, 60));
        setting.setBackground(Color.ORANGE);
        setting.setForeground(Color.WHITE);
        setting.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        setting.setHorizontalAlignment(SwingConstants.CENTER);

        kotak.add(setting);

        settingContainer.add(nganggur);

        settingContainer.add(kotak);

        frame.add(settingContainer);

        frame.setVisible(true);
    }
}
