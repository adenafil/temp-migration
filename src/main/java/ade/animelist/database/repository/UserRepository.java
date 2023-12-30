package ade.animelist.database.repository;

public interface UserRepository {
    boolean updateUsername(String newUsername);
    boolean updatePassword(String updatePassword);
    boolean updateProfilePath(String newPath);
}
