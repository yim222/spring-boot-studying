package com.lingar.SocialEvents.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChessSquare2 {
	
	private @Id @GeneratedValue Long id;
	private String squareSign ; private String tool ; 
	
	public ChessSquare2 (){}

	public String getSquareSign() {
		return squareSign;
	}

	public void setSquareSign(String squareSign) {
		this.squareSign = squareSign;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	@Override
	public String toString() {
		return "ChessSquare [squareSign=" + squareSign + ", tool=" + tool + "]";
	}

	public ChessSquare2(String squareSign, String tool) {
		super();
		this.squareSign = squareSign;
		this.tool = tool;
	}
	
	
	
	
}
