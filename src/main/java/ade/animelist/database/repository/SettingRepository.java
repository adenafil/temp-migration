package ade.animelist.database.repository;

public interface SettingRepository {
    boolean update(String username, String password, String path);
    String getPassword();
    String getPath();
}
