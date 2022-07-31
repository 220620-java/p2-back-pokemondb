package com.revature.pokemondb.services;

import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.repositories.ArtCommRepository;
import com.revature.pokemondb.repositories.FanartRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 */
@Service
public class FanartService {
	private FanartRepository artRepo;
	private ArtCommRepository commRepo;

	public Fanart getFanart(int id) {
		Optional<Fanart> artOpt = artRepo.findById(id);
		if (artOpt.isPresent()) {
			return artOpt.get();
		} else {
			return null;
		}
	}
}
