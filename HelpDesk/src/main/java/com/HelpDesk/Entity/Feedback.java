package com.HelpDesk.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 300, nullable = false)
	private String answer;
	
	@Column(nullable = true)
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issues feedIssue;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(String answer, int rating, Issues feedIssue) {
		super();
		this.answer = answer;
		this.rating = rating;
		this.feedIssue = feedIssue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Issues getFeedIssue() {
		return feedIssue;
	}

	public void setFeedIssue(Issues feedIssue) {
		this.feedIssue = feedIssue;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", answer=" + answer + ", rating=" + rating + ", feedIssue=" + feedIssue + "]";
	}
	
	
}