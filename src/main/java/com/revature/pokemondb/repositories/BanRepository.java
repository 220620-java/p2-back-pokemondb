package com.revature.pokemondb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.BannedUser;

@Repository
public interface BanRepository extends JpaRepository<BannedUser, Long> {

}
