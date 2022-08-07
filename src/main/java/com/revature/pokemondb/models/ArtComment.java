package com.revature.pokemondb.models;

import java.sql.Date;
import java.time.LocalDate;
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
@Table(name = "fanart_comments", schema = "pokemon_db")
public class ArtComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "fanart_id")
	private FanartDTO fanartId;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserIdDTO author;
	@Column(name = "comment_content")
	private String content;
	private Integer likes;
	private Integer reports;
	@Column(name = "is_flagged")
	private Boolean isFlagged;
	@Column(name="posted_at")
	private Date postDate;
	
	/*Constructors*/
	public ArtComment() {
		this.id = 0;
		this.fanartId = null;
		this.author = null;
		this.content = "";
		this.likes = 0;
		this.reports = 0;
		this.isFlagged = false;
		this.postDate = Date.valueOf(LocalDate.now());
	}
	
	public ArtComment(int id, FanartDTO fanartId, UserIdDTO author, String content, Integer likes, Integer reports,
			Boolean isFlagged, Date postDate) {
		super();
		this.id = id;
		this.fanartId = fanartId;
		this.author = author;
		this.content = content;
		this.likes = likes;
		this.reports = reports;
		this.isFlagged = isFlagged;
		this.postDate = postDate;
	}
	
	/*Overrides*/
	
	@Override
	public String toString() {
		return "ArtComment [id=" + id + ", fanartId=" + fanartId + ", author=" + author + ", content=" + content
				+ ", likes=" + likes + ", reports=" + reports + ", isFlagged=" + isFlagged + ", postDate=" + postDate
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, content, fanartId, id, isFlagged, likes, postDate, reports);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtComment other = (ArtComment) obj;
		return author == other.author && Objects.equals(content, other.content) && fanartId == other.fanartId
				&& id == other.id && Objects.equals(isFlagged, other.isFlagged) && Objects.equals(likes, other.likes)
				&& Objects.equals(postDate, other.postDate) && Objects.equals(reports, other.reports);
	}

	/*Getters and Setters*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FanartDTO getFanartId() {
		return fanartId;
	}

	public void setFanartId(FanartDTO fanartId) {
		this.fanartId = fanartId;
	}

	public UserIdDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserIdDTO author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getReports() {
		return reports;
	}

	public void setReports(Integer reports) {
		this.reports = reports;
	}

	public Boolean getIsFlagged() {
		return isFlagged;
	}

	public void setIsFlagged(Boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
