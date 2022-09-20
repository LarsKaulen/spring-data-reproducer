# Derived Delete Queries with Spring Data JDBC

This is a small reproducer for a problem with derived delete queries in Spring Data JDBC.

In `com.example.demo.db.UserRepository` are several derived delete queries and explicit delete queries (using the `@Query` annotation) defined.
They follow the examples in the [Spring Data JDBC documentation](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#repositories.core-concepts).

In `com.example.demo.db.UserRepositoryTest` those functions are tested.
Strangely, only those explicitly defined with the `@Query` annotation (and the automatically existing `void deleteById(Integer id)`) work.
The derived queries either finish successfully but actually don't delete anything (see `UserRepositoryTest#test_delete_by_id_with_user` and `UserRepositoryTest#test_delete_by_username_with_void`) or they fail due to a `ClassCastException` (see `UserRepositoryTest#test_delete_by_username_with_long`).
