package ade.animelist.database.repository;

import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public boolean updateUsername(String newUsername) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "update user set username = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, configRepository.getCurrentUsername());

            int update = preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println("200 OK");
                User user = new User(newUsername, null, configRepository.getCurrentUserId());
                configRepository.writeConfig(user);
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePassword(String updatePassword) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "update user set password = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatePassword);
            preparedStatement.setString(2, configRepository.getCurrentUsername());

            int update = preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println("200 OK");
                User user = new User(configRepository.getCurrentUsername(), updatePassword, configRepository.getCurrentUserId());
                configRepository.writeConfig(user);
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateProfilePath(String newPath) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "update user set path_profile = ? where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPath);
            preparedStatement.setString(2, configRepository.getCurrentUsername());

            int update = preparedStatement.executeUpdate();

            if (update > 0) {
                System.out.println("200Ok");
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}