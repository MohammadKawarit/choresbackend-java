package org.example.db;

import org.example.core.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface UserDAO {
    @SqlUpdate("INSERT INTO users (name, email, password, role, parent_id) VALUES (:name, :email, :password, :role, :parentId)")
    void createUser(@Bind("name") String name, @Bind("email") String email, @Bind("password") String password,
                    @Bind("role") String role, @Bind("parentId") Integer parentId);

    @SqlQuery("SELECT * FROM users WHERE user_id = :id")
    User getUserById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users")
    List<User> getAllUsers();
}