package com.revature.pokemondb.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name="rate_fanart", schema = "pokemon_db")
public class RateArt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="fanart_id")
	private FanartDTO fanartId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserIdDTO author;
	@Column(name="is_liked")
	private Boolean isLiked;
	
	/*Constructors*/
	
	public RateArt() {
		this.id = 0;
		this.fanartId = null;
		this.author = null;
		this.isLiked = false;
	}
	
	public RateArt(int id, FanartDTO fanart_id, UserIdDTO author, Boolean isLiked) {
		super();
		this.id = id;
		this.fanartId = fanart_id;
		this.author = author;
		this.isLiked = isLiked;
	}
	
	/*Overrides*/
	
	@Override
	public int hashCode() {
		return Objects.hash(author, fanartId, id, isLiked);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RateArt other = (RateArt) obj;
		return Objects.equals(author, other.author) && Objects.equals(fanartId, other.fanartId) && id == other.id
				&& Objects.equals(isLiked, other.isLiked);
	}
	
	/*Getters and Setters*/
	
	@Override
	public String toString() {
		return "RateArt [id=" + id + ", fanart_id=" + fanartId + ", author=" + author + ", isLiked=" + isLiked + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public FanartDTO getFanartId() {
		return fanartId;
	}
	public void setFanart_id(FanartDTO fanartId) {
		this.fanartId = fanartId;
	}
	public UserIdDTO getAuthor() {
		return author;
	}
	public void setAuthor(UserIdDTO author) {
		this.author = author;
	}
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	
}
