;
import ade.animelist.api.JikanAPI;
import ade.animelist.controller.Controller;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.common.Recommendation;
import net.sandrohc.jikan.model.common.RecommendationSimple;
import net.sandrohc.jikan.model.enums.AgeRating;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import javax.swing.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class TestAPI {
    static Jikan jikan;

    static {
        jikan = new Jikan();
    }

    @Test
    void getImageAndTitle() throws JikanQueryException {
        Collection<Anime> results = jikan.query().anime().search()
                .query("Naruto")
                .limit(10)
                .execute()
                .collectList()
                .block();

        for (Anime anime : results) {
            System.out.println(anime.title);
            System.out.println(anime.images.getJpg().largeImageUrl);
            System.out.println(anime.rating == AgeRating.PG13);

            ImageIcon icon = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);
        }
    }

    @Test
    void testGetPopulerAnime() throws JikanQueryException {
        Collection<Anime> results = jikan.query().anime().top()
                .limit(5)
                .execute()
                .collectList()
                .block();

//        results.forEach(e -> System.out.println(e.title));
        results.parallelStream().forEach(e -> System.out.println(e.title));
    }

    @Test
    void testGetPopulerAnimeAgain() throws JikanQueryException {
        JikanAPI.getTopAnime();

    }

    @Test
    void testGetRecomendattionAnime() throws JikanQueryException {
        for (int i = 0; i < 5; i++) {
            Anime anime = jikan.query().random().anime().execute().block();
            System.out.println(anime.title);
        }
    }

//    public Mono<Anime> getRecommendationAnimeAsync() {
//        return Mono.fromCallable(() -> jikan.query().random().anime().execute().block());
//    }

    public Mono<List<Anime>> getRecommendationAnimeAsync() {
        List<Anime> animeList = null;
        return Mono.fromCallable(() -> {
            for (int i = 0; i < 5; i++) {
                animeList.add(jikan.query().random().anime().execute().block());
            }
            return animeList != null ? animeList : List.of();
        });
    }


    @Test
    public void getTestRandomAnime() {
        getRecommendationAnimeAsync()
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                        animeList -> {
                            ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                            imageLoaderWorker.execute();
                            animeList.forEach(bayor -> System.out.println(bayor.title));
                        },
                        throwable -> {
                            System.out.println("error : " + throwable.getMessage());
                        }
                );
    }

    @Test
    void getAnimeRandom() {
        // Gunakan StepVerifier untuk menangani observasi reaktif
        StepVerifier.create(JikanAPI.getRecommendationAnimeAsync())
                .consumeNextWith(animeList -> {
                    // Lakukan sesuatu dengan daftar anime yang diterima
                    animeList.forEach(e -> System.out.println(e.title));
                })
                .verifyComplete();
    }


}
