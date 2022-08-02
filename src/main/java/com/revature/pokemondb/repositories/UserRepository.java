package com.revature.pokemondb.repositories;

import java.sql.Date;
import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtProcessing;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

    @Modifying
    @Query("update users u set u.token_issued_at = :tokenDate where u.id = :id")
    public void updateTokenIssued (@Param(value = "id") long id, @Param(value = "tokenDate") DateTimeAtProcessing tokenDate);
}
