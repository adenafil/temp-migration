package ade.animelist.database.repository;

import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingRepositoryImpl implements SettingRepository{
    @Override
    public boolean update(String username, String password, String path) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "UPDATE user set username = ? , password = ? , path_profile = ?  where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, path);
            preparedStatement.setString(4, configRepository.getCurrentUsername());

            int update = preparedStatement.executeUpdate();

            System.out.println(update);

            if (update > 0) {
                LoginRepository loginRepository = new LoginRepositoryImpl();
                loginRepository.doesUsernameAndPasswordExist(username, password);
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return false;
    }

    @Override
    public String getPassword() {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {

            String sql = "select password from user where username = ?";
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, configRepository.getCurrentUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String getPath() {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {

            String sql = "select path_profile from user where username = ?";
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, configRepository.getCurrentUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("path_profile");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
