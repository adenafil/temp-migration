import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;
import ade.animelist.database.repository.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
}
