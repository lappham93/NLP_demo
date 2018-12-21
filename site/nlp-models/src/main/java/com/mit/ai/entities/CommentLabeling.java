package com.mit.ai.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mit.common.entities.TimeDoc;

@Document(collection = "comment_labeling")
public class CommentLabeling extends TimeDoc<Long> {
	@Id
	private long id;
	private String comment;
	private int label;
	private int predict;
	
	public CommentLabeling() {
		super();
	}
	
	public CommentLabeling(String comment, int label, int predict) {
		super();
		this.comment = comment;
		this.label = label;
		this.predict = predict;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public int getPredict() {
		return predict;
	}

	public void setPredict(int predict) {
		this.predict = predict;
	}

}
