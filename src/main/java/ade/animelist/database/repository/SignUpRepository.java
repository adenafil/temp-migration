package ade.animelist.database.repository;

import ade.animelist.database.entity.User;

public interface SignUpRepository {
    boolean insert(User user);
}
