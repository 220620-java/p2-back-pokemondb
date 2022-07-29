package com.revature.pokemondb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report_poke_comm", schema = "pokemon_db")
public class ReportPokeComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int comment_id;
	@Column(name="user_id")
	private int author;
	@Column(name="is_reported")
	private Boolean isReported;
	@Column(name="report_reason")
	private String reportReason; 
}
