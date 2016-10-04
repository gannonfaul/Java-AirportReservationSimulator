package termproject;
import java.util.Scanner;
import java.util.Date;
/*
	Gannon Faul
	CSC 240 -- Term Project
	12/11/15
*/
class Flights{
	private Airplane[][] departures = new Airplane[3][3];

	//Constructs the array of flights
	public Flights(){
		String day;
		String dest;
		for(int i = 0; i < 3; i++){
			if(i == 0){
				dest = "LAX";
			} else if(i == 1){
				dest = "MDW";
			} else{
				dest = "LGA";
			}
			for(int j = 0; j < 3; j++){
				if(j == 0){
					day = "0101";
				} else if(j == 1){
					day = "0317";
				} else{
					day = "0704";
				}

				departures[i][j] = new Airplane(dest, day);
			}
		}
	}

	//Prints flight
	public void getFlight(int destIndex, int dayIndex, String dest, String day){
		System.out.println("Flight to " + dest + " on " + day +":");
		this.departures[destIndex][dayIndex].printAirplane();
	}

	//Prints individual reservation
	public void getFlightReservation(int destIndex, int dayIndex, int rowIndex, int aisleIndex){
		this.departures[destIndex][dayIndex].printPlaneSeat(rowIndex, aisleIndex);
		System.out.println();
	}


	//Reserve seat on a flight, prompts for another seat if desired seat is taken
	public Boolean checkSeatStatus(int destIndex, int dayIndex, int seatRow, int seatAisle){
		Boolean notAvailable = this.departures[destIndex][dayIndex].checkSeat(seatRow, seatAisle);
		return notAvailable;
	}

	//Books a seat on a flight
	public void makeReservation(
		int destIndex, 
		int dayIndex, 
		int seatRow, 
		int seatAisle, 
		String fName,
		String lName){
		this.departures[destIndex][dayIndex].reserveSeat(seatRow, seatAisle, fName, lName);
	}

}

