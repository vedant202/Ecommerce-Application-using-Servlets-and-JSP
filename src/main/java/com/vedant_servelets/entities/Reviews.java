package com.vedant_servelets.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reviews implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUreviewsId = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewsId;

	private int rating;
	private String comment;
//	private LocalDateTime date;
	private String reviewerName;
	private String reviewerEmail;

	public Reviews() {
		super();
	}

	public Reviews(int rating, String comment, String reviewerName, String reviewerEmail) {
		super();

		this.rating = rating;
		this.comment = comment;
		this.reviewerName = reviewerName;
		this.reviewerEmail = reviewerEmail;
	}

	public Reviews(long reviewsId, int rating, String comment, String reviewerName, String reviewerEmail) {
		super();
		this.reviewsId = reviewsId;
		this.rating = rating;
		this.comment = comment;
		this.reviewerName = reviewerName;
		this.reviewerEmail = reviewerEmail;
	}

	public long getreviewsId() {
		return reviewsId;
	}

	public void setreviewsId(long reviewsId) {
		this.reviewsId = reviewsId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReviewerEmail() {
		return reviewerEmail;
	}

	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}

	@Override
	public String toString() {
		return "Reviews [reviewsId=" + reviewsId + ", rating=" + rating + ", comment=" + comment + ", reviewerName="
				+ reviewerName + ", reviewerEmail=" + reviewerEmail + "]";
	}

}
