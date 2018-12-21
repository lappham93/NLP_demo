package com.mit.ai.responses;

public class SentimentPredictResponse {
	private int label;

	private double proba;

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public double getProba() {
		return proba;
	}

	public void setProba(double proba) {
		this.proba = proba;
	}

}
