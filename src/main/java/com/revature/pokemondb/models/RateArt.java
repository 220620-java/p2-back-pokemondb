package com.revature.pokemondb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate_fanart")
public class RateArt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int fanart_id;
	@Column(name="user_id")
	private int author;
	@Column(name="is_liked")
	private Boolean isLiked;
}
