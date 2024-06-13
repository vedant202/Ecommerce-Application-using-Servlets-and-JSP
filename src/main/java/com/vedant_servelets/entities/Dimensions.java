package com.vedant_servelets.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dimensions implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dimensionId;
	private double width;
	private double height;
	private double depth;



	public Dimensions() {
		super();
	}
	public Dimensions(double width, double height, double depth) {
		super();
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	public Dimensions(long dimensionId,double width, double height, double depth) {
		super();
		this.dimensionId = dimensionId;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}


	public long getId() {
		return dimensionId;
	}
	public void setId(long dimensionId) {
		this.dimensionId = dimensionId;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "Dimensions [id=" + dimensionId + ", width=" + width + ", height=" + height + ", depth=" + depth + "]";
	}



}
