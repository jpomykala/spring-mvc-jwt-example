package me.jpomykala.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.jpomykala.model.user.User;

/**
 * Created by Evelan-E6540 on 06/09/2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
