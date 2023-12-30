package ade.animelist.database.repository;

public interface LoginRepository {
    boolean doesUsernameAndPasswordExist(String username, String password);
}
