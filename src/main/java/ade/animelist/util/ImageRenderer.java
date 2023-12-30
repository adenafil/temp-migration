package ade.animelist.util;

import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import net.sandrohc.jikan.model.anime.Anime;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


public class ImageRenderer extends DefaultTableCellRenderer {
    private static final Map<String, ImageIcon> imageCache = new ConcurrentHashMap<>();

    public ImageRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            ImageIcon original = (ImageIcon) value;
            ImageIcon resizedImg = ImageRenderer.setImageIconSize(original, 200, 200);
            setIcon(resizedImg);
            setText(null);  // Clear text when displaying an icon
        } else {
            super.setValue(value);
        }
    }

//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//        // set the bg by odd or even
//        if (row % 2 == 0) {
//            renderer.setBackground(Color.WHITE);
//        } else {
//            renderer.setBackground(Color.decode("#f6f6f6"));
//        }
//
//        setHorizontalAlignment(SwingConstants.CENTER);
//
//        return renderer;
//    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // set the background color
        Color backgroundColor = (row % 2 == 0) ? Color.WHITE : Color.decode("#f6f6f6");
        renderer.setBackground(backgroundColor);

        // set the value
        if (value instanceof ImageIcon) {
            // If the value is an ImageIcon, display it
            setIcon(ImageRenderer.setImageIconSize((ImageIcon)value, 200, 200));
            setText("");
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
        } else {
            // If the value is not an ImageIcon, display text
            if (value != null) {
                setIcon(null);
                setText(value.toString());
            }
        }

        return renderer;
    }




    //    public static ImageIcon createImageIconByURL(String imgaeURL) throws IOException {
//        Image image = ImageIO.read(new URL(imgaeURL));
//        if (image != null) return new ImageIcon(image);
//        return null;
//    }
//
    public static ImageIcon createImageIconByURL(String imageURL) {
        // Check if the image is already in the cache
        ImageIcon cachedImage = imageCache.get(imageURL);
        if (cachedImage != null) {
            return cachedImage;
        }

        try {
            // Fetch and create the ImageIcon
            Image image = ImageIO.read(new URL(imageURL));
            if (image != null) {
                ImageIcon newImageIcon = new ImageIcon(image);

                // Cache the image for future use
                imageCache.put(imageURL, newImageIcon);

                return newImageIcon;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class ImageLoader extends SwingWorker<ImageIcon, Void> {
        private final String imageUrl;

        public ImageLoader(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        protected ImageIcon doInBackground() {
            try {
                BufferedImage bufferedImage = loadImageData(imageUrl);
                return new ImageIcon(bufferedImage);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception if necessary
            }
            return null;
        }

        private BufferedImage loadImageData(String imageUrl) throws IOException {
            URL url = new URL(imageUrl);
            return ImageIO.read(url);
        }
    }

    public static ImageIcon getCacheImageForCollectionPage(String imageUrl) {
        ImageIcon cachedImageIcon = imageCache.get(imageUrl);

        if (cachedImageIcon != null) {
            return cachedImageIcon;
        }

        return null;
    }

    public static ImageIcon createImageIconByUrl(String imageUrl) {
        ImageIcon cachedImageIcon = imageCache.get(imageUrl);

        if (cachedImageIcon != null) {
            return cachedImageIcon;
        }

        ImageRenderer.ImageLoader imageLoader = new ImageRenderer.ImageLoader(imageUrl);
        imageLoader.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                try {
                    ImageIcon imageIcon = imageLoader.get();
                    imageCache.put(imageUrl, imageIcon);
                    // You can perform additional actions here if needed
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle exception if necessary
                }
            }
        });

        imageLoader.execute();
        return null; // Return null temporarily; the actual ImageIcon will be set asynchronously
    }

    public static ImageIcon setImageIconSize(ImageIcon img, int width, int height) {
        Image image = img.getImage();
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        double aspectRatio = (double) originalWidth / originalHeight;

        int newWidth = width;
        int newHeight = (int) (width / aspectRatio);

        // Ensure the calculated height is not greater than the specified height

        if (newHeight > height) {
            newHeight = height;
            newWidth = (int) (height * aspectRatio);
        }

        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    public static void runConfig() {
        CompletableFuture.runAsync(() -> {
            AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
            ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(addAnimeToDbRepository.getAllAnimeListUser());
            imageLoaderWorker.execute();
        }).exceptionally(ex -> {
            ex.printStackTrace(); // Handle exceptions
            return null;
        });
    }


}
