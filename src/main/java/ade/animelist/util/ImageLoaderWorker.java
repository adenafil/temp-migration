package ade.animelist.util;

import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ImageLoaderWorker extends SwingWorker<List<ImageIcon>, Void> {
    private final Collection<Anime> animeCollection;

    public ImageLoaderWorker(Collection<Anime> animeCollection) {
        this.animeCollection = animeCollection;
    }

    @Override
    protected List<ImageIcon> doInBackground() throws Exception {
        return animeCollection.parallelStream()
                .map(adebayor -> ImageRenderer.createImageIconByUrl(adebayor.images.getJpg().largeImageUrl))
                .collect(Collectors.toList());
    }

    @Override
    protected void done() {
        try {
            List<ImageIcon> imageIcons = get();
            // Handle the loaded ImageIcons, for example, update your UI
            for (ImageIcon imageIcon : imageIcons) {
                // Update your UI with the loaded ImageIcon
                // You may call addAnime or perform any other UI updates
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle exceptions if necessary
        }
    }

    // Example of how you might use this worker in your ActionListener
    // ...
}
