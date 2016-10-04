package termproject;
import java.util.Scanner;
import java.util.Date;
/*
	Gannon Faul
	CSC 240 -- Term Project
	12/11/15
*/
class Airplane{
	private Seat[][] seats = new Seat[20][6];
	private String destination;
	private String date;


	//Default constructor
	public Airplane(){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 6; j++){
				seats[i][j] = new Seat(i, j, false);
			}
		}
		destination = "NO DESTINATION";
		date = "1332";
	}

	//Consructor generating a 20 row airplane to desination dest on date day
	public Airplane(String dest, String day){
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 6; j++){
				seats[i][j] = new Seat(i, j, false);
			}
		}
		destination = dest;
		date = day;
	}

	//Returns destination of flight
	public String getDestination(){
		return this.destination;
	}

	//Returns date of flight
	public String getDate(){
		return this.date;
	}

	//Sets destination of flight to dest
	public void setDestination(String dest){
		this.destination = dest;
	}

	//Sets date of flight to day
	public void setDate(String day){
		this.date = day;
	}

	public boolean checkSeat(int seatRow, int seatAisle){
		return this.seats[seatRow][seatAisle].getIsTaken();
	}

	public void reserveSeat(int seatRow, int seatAisle, String fName, String lName){
		this.seats[seatRow][seatAisle].setIsTaken(true);
		this.seats[seatRow][seatAisle].setPassengerName(fName, lName);
	}

	//Prints individual seat information
	public void printPlaneSeat(int rowIndex, int aisleIndex){
		System.out.println("Destination: " + this.destination);
		System.out.println("Date (mmdd): " + this.date);
		this.seats[rowIndex][aisleIndex].printSeat();
	}
	//Outputs the airplanes seat layout
	public void printAirplane(){
		for(int i = 0; i < 20; i++){
			if(i == 0){
				System.out.println("  First Class--$600");
			} else if(i == 3){
				System.out.println("Business Class--$200");
			} else if(i == 10){
				System.out.println("    Economy--$100");
			}
			if(i < 9){
				System.out.print(" ");
			}
			System.out.print(this.seats[i][0].getRow() + 1);
			System.out.print("  ");
			for(int j = 0; j < 6; j++){
				System.out.print("|");
				if(this.seats[i][j].getIsTaken()){
					System.out.print("X");
				} else{
					System.out.print(this.seats[i][j].getAisle() + 1);
				}
				if(j == 2){
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}	
		System.out.println();
	}
}


