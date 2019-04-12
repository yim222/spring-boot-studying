package com.lingar.SocialEvents.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lingar.SocialEvents.entities.ChessSquare;
import com.lingar.SocialEvents.entities.ChessSquare2;
import com.lingar.SocialEvents.entities.SocialEvent2;
import com.lingar.SocialEvents.entities.Try1;

@Component
public class DatabaseLoader2 implements CommandLineRunner {

	private final SocialEventRepository2 repository2;
	
	//First get the repository2 of the CRUD
	@Autowired
	public DatabaseLoader2(SocialEventRepository2 repository22) {
		this.repository2 = repository22;
		
	}

	//Then Do with that saving. 
	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Saving - data loader 2 ");
		System.out.println(this.repository2.count() + " = how much entities inside the table. ");
		
		//String[] myArray = {"I", "AM", "LINGAR"};
		
		//List<String> myArray2 = new ArrayList<String>();
		//myArray2.add("One");myArray2.add("Two");myArray2.add("Three");
		System.out.println("checks ... the general area of dataLoader .  ");
		
		//this.repository2.save(new SocialEvent("Crazy Party ", 0, 120, "a" , myArray2));
		System.out.println("checks ... the assignment ");
		//EventProperties evProps = new EventProperties(EventPropertiesGenerator.generateList());
		
		//this.repository2.save(new SocialEvent("New party " , "amm", 5, 22,  new EventProperties(EventPropertiesGenerator.generateList())));
		this.repository2.save(new SocialEvent2("trying1", 18, 36 , "Some more word " ));
		
		//this.repository2.save(new SocialEvent("Lesson ", 33, 40, "b" , myArray2)); 
		//myArray2.add("Happy");
		//this.repository2.save(new SocialEvent("Trip ", 20, 40, "c" , myArray2));
		
		System.out.println("Clean all chess Square");
		/*
		if(this.chessRepository.count() > 0 ){
		//	this.chessRepository.deleteAll();
		}
		
		this.chessRepository.save(new ChessSquare("A1", "Rook"));
		this.chessRepository.save(new ChessSquare("A2", "Bishop"));
		System.out.println("all the entities of chessSquare : \n"
				+ this.chessRepository.findAll());
		System.out.println("Trying to delete ");
		this.chessRepository.delete(new ChessSquare("A1", "Rook"));
		System.out.println("all the entities of chessSquare : \n"
				+ this.chessRepository.findAll());
		System.out.println("Not work - because I am use in new  " );
		ChessSquare c = new ChessSquare("A3", "Knight");
		this.chessRepository.save(c);
		System.out.println("all the entities of chessSquare : \n"
				+ this.chessRepository.findAll());
		this.chessRepository.delete(c);
		System.out.println("all the entities of chessSquare : \n"
				+ this.chessRepository.findAll());
		System.out.println("Now it's work because I did it with object ");
		
		System.out.println("This is how look the array paging : ");
		
		System.out.println(this.repository2.findAll());
		Try1 a = new Try1("C3", "Queen");
		
		this.repo.save(a);
		System.out.println("Assigning chessSquare2s:");
		
		ChessSquare2[] initialData = {
				new ChessSquare2("G5", "King"),
				new ChessSquare2("G6", "Queen"),
				new ChessSquare2("D6", "Pawn")
				
		};
		Iterable<ChessSquare2> iterable = Arrays.asList(initialData);
		this.chessSquareRepository2.saveAll(iterable );
		*/
		
	}
}