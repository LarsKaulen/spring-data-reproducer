package com.example.demo.db;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    User removeById(Integer id);
    void deleteByUsername(String username);
    long removeByUsername(String username);

    @Modifying
    @Query("DELETE FROM USER U WHERE U.USER_ID = :id")
    void customDeleteByIdWithQuery(@Param("id") Integer id);

    @Modifying
    @Query("DELETE FROM USER U WHERE U.USER_ID = :id")
    long customDelete2ByIdWithQuery(@Param("id") Integer id);
}
