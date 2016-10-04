package termproject;
import java.util.Scanner;
import java.util.Date;
/*
	Gannon Faul
	CSC 240 -- Term Project
	12/11/15
*/
class Seat{
	private Passenger person;
	private int row;
	private int aisle;
	private Boolean isTaken;

	//Default constructor
	public Seat(){
		person = new Passenger();
		row = 0;
		aisle = 0;
		isTaken = false;
	}

	//Constructs an open seat in row "seatRow" and aisle "seatAisle"
	public Seat(int seatRow, int seatAisle, Boolean status){
		person = new Passenger();
		row = seatRow;
		aisle = seatAisle;
		isTaken = status;
	}

	//Sets name of passenger of seat to fName lName
	public void setPassengerName(String fName, String lName){
		this.person.setFirstName(fName);
		this.person.setLastName(lName);
	}

	//Sets isTaken to boolean "status"
	public void setIsTaken(Boolean status){
		this.isTaken = status;
	}

	//Sets seat aisle to "seatAisle"
	public void setAisle(int seatAisle){
		this.aisle = seatAisle;
	}

	//Sets seat row to "seatRow"
	public void setRow(int seatRow){
		this.row = seatRow;
	}

	//Returns first name of passenger reserving the seat
	public String getPassengerFirstName(){
		return this.person.getFirstName();
	}

	//Returns last name of passenger reserving the seat
	public String getPassengerLastName(){
		return this.person.getLastName();
	}

	//Returns status of the seat
	public Boolean getIsTaken(){
		return this.isTaken;
	}

	//Returns the seat aisle
	public int getAisle(){
		return this.aisle;
	}

	//Returns the seat row
	public int getRow(){
		return this.row;
	}

	public void printSeat(){
		this.person.printPassenger();
		System.out.println("Row: " + (this.row + 1) + "     Aisle: " + (this.aisle + 1));
	}
}

