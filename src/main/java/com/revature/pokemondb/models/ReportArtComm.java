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
@Table(name="report_art_comm", schema = "pokemon_db")
public class ReportArtComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="comment_id")
	private ArtComment commentId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User author;
	@Column(name="is_reported")
	private Boolean isReported;
	@Column(name="report_reason")
	private String reportReason;
	
	/*Constructors*/
	
	public ReportArtComm() {
		super();
		this.id = 0;
		this.commentId = null;
		this.author = null;
		this.isReported = false;
		this.reportReason = "";
	}
	
	public ReportArtComm(int id, ArtComment commentId, User author, Boolean isReported, String reportReason) {
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
		ReportArtComm other = (ReportArtComm) obj;
		return Objects.equals(author, other.author) && Objects.equals(commentId, other.commentId) && id == other.id
				&& Objects.equals(isReported, other.isReported) && Objects.equals(reportReason, other.reportReason);
	}
	
	@Override
	public String toString() {
		return "ReportArtComm [id=" + id + ", commentId=" + commentId + ", author=" + author + ", isReported="
				+ isReported + ", reportReason=" + reportReason + "]";
	}
	
	/*Getters and Setters*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArtComment getCommentId() {
		return commentId;
	}
	public void setCommentId(ArtComment commentId) {
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
