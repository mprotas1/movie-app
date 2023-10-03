package com.protas.movieapp.repository;

import com.protas.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT t FROM cinema_user t WHERE t.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
