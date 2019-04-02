package com.lingar.SocialEvents.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "OtherChess")
public class Try1  extends ChessSquare{
	private @Id @GeneratedValue Long id;
	public Try1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Try1(String squareSign, String tool) {
		super(squareSign, tool);
		// TODO Auto-generated constructor stub
	}
	
	
}
