package com.revature.pokemondb.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name = "fanart_comments", schema = "pokemon_db")
public class ArtComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@JoinColumn(name = "fanart_id", referencedColumnName="id")
	private int fanartId;
	@JoinColumn(name = "user_id", referencedColumnName="id")
	private int author;
	@Column(name = "comment_content")
	private String content;
	private Integer likes;
	private Integer reports;
	@Column(name = "is_flagged")
	private Boolean isFlagged;
	@Column(name="posted_at")
	private Date postDate;	
}
