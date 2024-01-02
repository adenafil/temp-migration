package ade.animelist.components;

import ade.animelist.components.Navbar;
import ade.animelist.database.repository.ConfigRepository;
import ade.animelist.database.repository.ConfigRepositoryImpl;
import ade.animelist.database.repository.SettingRepository;
import ade.animelist.database.repository.SettingRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class Setting {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Navbar navbar = new Navbar();
        ConfigRepository configRepository = new ConfigRepositoryImpl();
        SettingRepository settingRepository = new SettingRepositoryImpl();

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
        settingContainer.setBackground(Color.decode("#333b48"));

        JPanel nganggur = new JPanel();
        nganggur.setOpaque(true);
        nganggur.setPreferredSize(new Dimension(1920, 50));
        nganggur.setMaximumSize(new Dimension(1920, 50));
        nganggur.setBackground(Color.decode("#333b48"));

        JPanel kotak = new JPanel();
        kotak.setOpaque(true);
        kotak.setPreferredSize(new Dimension(1000, 700));
        kotak.setMaximumSize(new Dimension(1000, 700));
        kotak.setBackground(Color.decode("#333b48"));
//        kotak.setBorder(BorderFactory.createLineBorder(Color.BLUE, 20));

        JLabel setting = new JLabel("Setting Profile");
        setting.setOpaque(true);
        setting.setPreferredSize(new Dimension(1000, 60));
        setting.setMaximumSize(new Dimension(1000, 60));
        setting.setBackground(Color.decode("#333b48"));
        setting.setForeground(Color.WHITE);
        setting.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        setting.setHorizontalAlignment(SwingConstants.CENTER);

        kotak.add(setting);

        JPanel usernameContainer = new JPanel();
        usernameContainer.setOpaque(true);
        usernameContainer.setPreferredSize(new Dimension(1000, 170));
        usernameContainer.setMaximumSize(new Dimension(1000, 170));
        usernameContainer.setBackground(Color.decode("#333b48"));

        JLabel username = new JLabel("Username");
        username.setOpaque(true);
        username.setPreferredSize(new Dimension(900, 50));
        username.setMaximumSize(new Dimension(900, 50));
        username.setBackground(Color.decode("#333b48"));
        username.setForeground(Color.WHITE);
        username.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        username.setHorizontalAlignment(SwingConstants.LEFT);

        usernameContainer.add(username);

        JTextField userNameField = new JTextField();
        userNameField.setOpaque(true);
        userNameField.setPreferredSize(new Dimension(900, 80));
        userNameField.setMaximumSize(new Dimension(900, 80));
        userNameField.setBackground(Color.decode("#333b48"));
        userNameField.setBackground(Color.WHITE);
        userNameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        userNameField.setText(configRepository.getCurrentUsername());

        usernameContainer.add(userNameField);

        JPanel passwordContainer = new JPanel();
        passwordContainer.setOpaque(true);
        passwordContainer.setPreferredSize(new Dimension(1000, 190));
        passwordContainer.setMaximumSize(new Dimension(1000, 190));
        passwordContainer.setBackground(Color.decode("#333b48"));

        JLabel password = new JLabel("Password");
        password.setOpaque(true);
        password.setPreferredSize(new Dimension(900, 50));
        password.setMaximumSize(new Dimension(900, 50));
        password.setBackground(Color.decode("#333b48"));
        password.setForeground(Color.WHITE);
        password.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        password.setHorizontalAlignment(SwingConstants.LEFT);

        passwordContainer.add(password);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setOpaque(true);
        passwordTextField.setPreferredSize(new Dimension(900, 80));
        passwordTextField.setMaximumSize(new Dimension(900, 80));
        passwordTextField.setBackground(Color.decode("#333b48"));
        passwordTextField.setBackground(Color.WHITE);
        passwordTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        passwordTextField.setText(settingRepository.getPassword());

        passwordContainer.add(passwordTextField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setOpaque(true);
        showPassword.setPreferredSize(new Dimension(900, 30));
        showPassword.setMaximumSize(new Dimension(900, 30));
        showPassword.setBackground(Color.decode("#333b48"));
        showPassword.setForeground(Color.WHITE);
        showPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        showPassword.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            passwordTextField.setEchoChar(checkBox.isSelected() ? '\0' : '•');
        });

        passwordContainer.add(showPassword);

        JPanel profileContainer = new JPanel();
        profileContainer.setOpaque(true);
        profileContainer.setPreferredSize(new Dimension(1000, 120));
        profileContainer.setMaximumSize(new Dimension(1000, 120));
        profileContainer.setBackground(Color.decode("#333b48"));
//        profileContainer.setBackground(Color.PINK);

        JLabel profile = new JLabel("Set Your Profile PATH");
        profile.setOpaque(true);
        profile.setPreferredSize(new Dimension(900, 50));
        profile.setMaximumSize(new Dimension(900, 50));
        profile.setBackground(Color.decode("#333b48"));
        profile.setForeground(Color.WHITE);
        profile.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        profile.setHorizontalAlignment(SwingConstants.LEFT);

        profileContainer.add(profile);

        JPanel pathContainer = new JPanel();
        pathContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 6, 0));
        pathContainer.setOpaque(true);
        pathContainer.setPreferredSize(new Dimension(910, 100));
        pathContainer.setMaximumSize(new Dimension(910, 100));
        pathContainer.setBackground(Color.decode("#333b48"));


        JButton browseProfile = new JButton("Select File");
        browseProfile.setOpaque(true);
        browseProfile.setPreferredSize(new Dimension(100, 30));
        browseProfile.setMaximumSize(new Dimension(100, 30));
        browseProfile.setFocusable(false);
        browseProfile.setFocusPainted(false);

        JTextField pathTextField = new JTextField();
        pathTextField.setOpaque(true);
        pathTextField.setPreferredSize(new Dimension(790, 50));
        pathTextField.setMaximumSize(new Dimension(790, 50));
        pathTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        pathTextField.setText(settingRepository.getPath());

        browseProfile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath();

                pathTextField.setText(selectedPath);
            }
        });

        pathContainer.add(browseProfile);
        pathContainer.add(pathTextField);
        profileContainer.add(pathContainer);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(true);
        buttonContainer.setPreferredSize(new Dimension(1000, 100));
        buttonContainer.setMaximumSize(new Dimension(1000, 100));
        buttonContainer.setBackground(Color.decode("#333b48"));

        JPanel nganggurProfile = new JPanel();
        nganggurProfile.setOpaque(true);
        nganggurProfile.setPreferredSize(new Dimension(1000, 30));
        nganggurProfile.setMaximumSize(new Dimension(1000, 30));
        nganggurProfile.setBackground(Color.decode("#333b48"));

        JButton saveChangesBtn = new JButton("Save Changes");
        saveChangesBtn.setOpaque(true);
        saveChangesBtn.setPreferredSize(new Dimension(200, 50));
        saveChangesBtn.setMaximumSize(new Dimension(200, 50));
        saveChangesBtn.setForeground(Color.WHITE);
        saveChangesBtn.setBackground(Color.ORANGE);
        saveChangesBtn.setFocusable(false);
        saveChangesBtn.setBorderPainted(false);
        saveChangesBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        saveChangesBtn.addActionListener(e -> {

            if (userNameField.getText().isBlank() || passwordTextField.getText().isBlank() || pathTextField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "TextField Tidak Boleh Kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // PRNYA DISINI GUYS
            boolean result = settingRepository.update(userNameField.getText(), passwordTextField.getText(), pathTextField.getText());
            if (result) {
                JOptionPane.showMessageDialog(null, "Berhasil Melakukan Perubahan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal Melakukan Perubahan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonContainer.add(nganggurProfile);
        buttonContainer.add(saveChangesBtn);

        kotak.add(usernameContainer);
        kotak.add(passwordContainer);
        kotak.add(profileContainer);
        kotak.add(buttonContainer);

        settingContainer.add(nganggur);

        settingContainer.add(kotak);

        frame.add(settingContainer);

        frame.setVisible(true);
    }
}
