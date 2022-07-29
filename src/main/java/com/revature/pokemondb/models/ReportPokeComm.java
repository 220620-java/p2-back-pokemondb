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

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name="report_poke_comm", schema = "pokemon_db")
public class ReportPokeComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="comment_id")
	private PokemonComments commentId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User author;
	@Column(name="is_reported")
	private Boolean isReported;
	@Column(name="report_reason")
	private String reportReason;
	
	/*Constructors*/
	
	public ReportPokeComm() {
		super();
		this.id = 0;
		this.commentId = null;
		this.author = null;
		this.isReported = false;
		this.reportReason = "";
	}
	
	public ReportPokeComm(int id, PokemonComments commentId, User author, Boolean isReported, String reportReason) {
		super();
		this.id = id;
		this.commentId = commentId;
		this.author = author;
		this.isReported = isReported;
		this.reportReason = reportReason;
	}
	
	/*Overrides*/
	
	@Override
	public int hashCode() {
		return Objects.hash(author, commentId, id, isReported, reportReason);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportPokeComm other = (ReportPokeComm) obj;
		return Objects.equals(author, other.author) && Objects.equals(commentId, other.commentId) && id == other.id
				&& Objects.equals(isReported, other.isReported) && Objects.equals(reportReason, other.reportReason);
	}
	
	@Override
	public String toString() {
		return "ReportPokeComm [id=" + id + ", commentId=" + commentId + ", author=" + author + ", isReported="
				+ isReported + ", reportReason=" + reportReason + "]";
	}
	
	/*Getters and Setters*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PokemonComments getCommentId() {
		return commentId;
	}
	public void setCommentId(PokemonComments commentId) {
		this.commentId = commentId;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Boolean getIsReported() {
		return isReported;
	}
	public void setIsReported(Boolean isReported) {
		this.isReported = isReported;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	} 
}
