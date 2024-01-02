import ade.animelist.api.JikanAPI;
import ade.animelist.components.Setting;
import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;
import ade.animelist.database.repository.*;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.model.anime.Anime;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestDatabase {

    @Test
    void testConnection() {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSelect() {
        LoginRepository loginRepository = new LoginRepositoryImpl();

        boolean isValid = loginRepository.doesUsernameAndPasswordExist("user1", "password123");
        System.out.println(isValid);
    }

    @Test
    void testSignUp() {
        SignUpRepository signUpRepository = new SignUpRepositoryImpl();

        User user = new User("ade", "mama");

        boolean isValid = signUpRepository.insert(user);
        System.out.println(isValid);
    }

    @Test
    void testConfig() {
        ConfigRepository configRepository = new ConfigRepositoryImpl();
        User user = new User("ade", "mama", 1);
        configRepository.writeConfig(user);
    }

    @Test
    void getUserId() {
        ConfigRepository configRepository = new ConfigRepositoryImpl();
        System.out.println(configRepository.getCurrentUserId());
    }

    @Test
    void testGetCurrentUsername() {
        ConfigRepository configRepository = new ConfigRepositoryImpl();
        System.out.println(configRepository.getCurrentUsername());
    }

    @Test
    void testAddAnime() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        boolean is200 = addAnimeToDbRepository.add(7, 1005, "WATCHING", 5, 20, "Naruto");
        System.out.println("Log is " + is200);
    }

    @Test
    void testAddProgress() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        boolean is200 = addAnimeToDbRepository.addProgressWatchingAnime(7, 1, "WATCHING", 11);
        System.out.println("Log is " + is200);
    }

    @Test
    void testGetUserAnimeListInDb() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        List<Anime> animeList = addAnimeToDbRepository.getAllAnimeListUser();
        int i = 0;
        animeList.forEach(e -> System.out.println(i));
    }

    @Test
    void testGetUserAnimeListInDatabase() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        List<Anime> animeList = addAnimeToDbRepository.getAllAnimeListInDatabaseUser();
        animeList.forEach(e -> {
            System.out.println(e.title);
            System.out.println(e.malId);
        });
    }

    @Test
    void testCheckAnimeByIdAPI() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        boolean isThere = addAnimeToDbRepository.doesThisAnimeExistInDatabase(20);
        System.out.println("log " + isThere);
    }

    @Test
    void getStatusWatchingByMalId() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        String result = addAnimeToDbRepository.getStatusByMalId(20);
        System.out.println("log " + result);
    }

    @Test
    void getCurrentEpsById() {
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        int result = addAnimeToDbRepository.getCurrentEpsByMalId(20);
        System.out.println("log " + result);
    }

    @Test
    void testUpdateUserName() {
        UserRepository userRepository = new UserRepositoryImpl();
        boolean result = userRepository.updateUsername("mama");
        System.out.println("log " + result);
    }
    @Test
    void testUpdatePassword() {
        UserRepository userRepository = new UserRepositoryImpl();
        boolean result = userRepository.updatePassword("1234");
        System.out.println("log " + result);
    }

    @Test
    void testUpdatePath() {
        UserRepository userRepository = new UserRepositoryImpl();
        boolean result = userRepository.updateProfilePath("C:/Users/acern/Downloads/ade.jpg");
        System.out.println("log " + result);
    }

    @Test
    void testSetting() {
        SettingRepository settingRepository = new SettingRepositoryImpl();
        boolean result = settingRepository.update("mama", "1234", "C:/Users/acern/Downloads/ade.jpg");
        System.out.println("log " + result);
    }

    @Test
    void getCurrentUsername() {
        SettingRepository settingRepository = new SettingRepositoryImpl();
        String result = settingRepository.getPassword();
        System.out.println("log " + result);
    }

    @Test
    void getCurrentPath() {
        SettingRepository settingRepository = new SettingRepositoryImpl();
        String result = settingRepository.getPath();
        System.out.println("log " + result);
    }

}
