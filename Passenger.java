package termproject;
import java.util.Scanner;
import java.util.Date;
/*
	Gannon Faul
	CSC 240 -- Term Project
	12/11/15
*/
class Passenger{
	private String firstName;
	private String lastName;

	//
	public Passenger(){
		firstName = "UNRESERVED";
		lastName = "SEAT";
	}

	public Passenger(String passengerFirstName, String passengerLastName){
		firstName = passengerFirstName;
		lastName = passengerLastName;
	}

	//Sets the first name of the passenger to passengerFirstName
	public void setFirstName(String passengerFirstName){
		this.firstName = passengerFirstName;
	}

	//Returns the first name of the passenger
	public String getFirstName(){
		return this.firstName;
	}

	//Sets the last name of the passenger to passengerLastName
	public void setLastName(String passengerLastName){
		this.lastName = passengerLastName;
	}

	//Returns the first name of the passenger
	public String getLastName(){
		return this.lastName;
	}

	//Prints the full passenger name to the Console
	public void printPassenger(){
		System.out.println("Passenger: " + this.firstName + " " + this.lastName);
	}
}

