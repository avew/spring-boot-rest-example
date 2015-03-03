package com.aseprojali.repository;

import com.aseprojali.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by avew on 3/3/15.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
