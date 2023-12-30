package ade.animelist.database.repository;

import net.sandrohc.jikan.model.anime.Anime;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AddAnimeToDbRepository {
    boolean add(int idUser, int idAnime, String statusWatching, int currentEps, int maxEps, String nama);
    boolean addProgressWatchingAnime(int idUser, int idAnime, String statusWatching, int currentEps);
    List<Anime> getAllAnimeListUser();
    List<Anime> getAllAnimeListInDatabaseUser();
    List<CompletableFuture<Anime>> getAllAnimeListUserAsync();
    List<Anime> joinAllAsyncResults(List<CompletableFuture<Anime>> animeFuture);
    boolean doesThisAnimeExistInDatabase(int malid);
    String getStatusByMalId(int malId);
    int getCurrentEpsByMalId(int malId);
}
