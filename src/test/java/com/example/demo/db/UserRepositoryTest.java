package com.example.demo.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = { RepositoryTestConfiguration.class })
public class UserRepositoryTest {

    @Autowired private UserRepository repository;
    private User saved;

    @BeforeEach
    public void setup() {

        assertEquals(0L, repository.count());

        User toSave = User.builder().username("test").password("test").build();
        saved = repository.save(toSave);

        assertEquals(1L, repository.count());
    }

    @Test
    public void test_existing_delete() {

        repository.deleteById(saved.getId()); // automatically generated function
        assertEquals(0L, repository.count()); // works
    }

    @Test
    public void test_delete_by_id_with_user() {

        User deleted = repository.removeById(saved.getId()); // derived query
        assertEquals(saved, deleted); // works
        assertEquals(0L, repository.count()); // doesn't work -> only SELECT statement called, not DELETE
    }

    @Test
    public void test_delete_by_username_with_void() {

        repository.deleteByUsername(saved.getUsername()); // derived query
        assertEquals(0L, repository.count()); // doesn't work
    }

    @Test
    public void test_delete_by_username_with_long() {

        long deletedCount = repository.removeByUsername(saved.getUsername()); // derived query
        // throws "java.lang.ClassCastException: com.example.demo.db.User cannot be cast to java.lang.Long"
        assertEquals(1L, deletedCount);
        assertEquals(0L, repository.count());
    }

    @Test
    public void test_delete_by_id_with_query_and_void() {

        repository.customDeleteByIdWithQuery(saved.getId()); // custom query method
        assertEquals(0L, repository.count()); // works
    }

    @Test
    public void test_delete_by_id_with_query_and_long() {

        long deletedCount = repository.customDelete2ByIdWithQuery(saved.getId()); // custom query method
        assertEquals(1L, deletedCount); // works
        assertEquals(0L, repository.count()); // works
    }
}
