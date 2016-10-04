package termproject;
import java.util.Scanner;
import java.util.Date;
/*
	Gannon Faul
	CSC 240 -- Term Project
	12/11/15
*/
class Reservation{

	// -----------------
	//    MAIN METHOD
	// -----------------

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String runAgain = "Y";
		String readyToReserve = "Y";
		String flightDestCode;
		String flightDateCode;
		int flightDestIndex;
		int flightDateIndex;
		int userRow = 0;
		int userAisle = 0;
		Boolean valid;
		String userFirstName;
		String userLastName;

		//Initialize flight schedule
		Flights schedule = new Flights();
		System.out.println();
		greetUser();

		System.out.print("Would you like to reserve a flight? (Y/N): ");
		runAgain = input.next();
		System.out.println();

		while(runAgain.equals("Y") || runAgain.equals("y")){
			flightDestCode = desiredDestination();
			valid = validDestination(flightDestCode);

			//Enter loop if user enters invalid destination, exit loop when valid
			while(!valid){
				System.out.println();
				System.out.println("We're sorry, that is not a valid destination.");
				System.out.println("Please ensure that you enter the code exactly as it appears above.");
				System.out.println("Capitalization counts!");
				System.out.println();
				flightDestCode = desiredDestination();
				valid = validDestination(flightDestCode);
			}

			flightDateCode = desiredDate();
			valid = validDate(flightDateCode);

			//Enter loop if user enters invalid date, exit loop when valid
			while(!valid){
				System.out.println();
				System.out.println("We're sorry, that is not a valid date.");
				System.out.println("Please ensure that you enter the code exactly as it appears above.");
				System.out.println();
				flightDateCode = desiredDate();
				valid = validDate(flightDateCode);
			}

			//Get index for array of flights
			switch(flightDestCode){
				case "LAX": flightDestIndex = 0;
					break;
				case "MDW": flightDestIndex = 1;
					break;
				case "LGA": flightDestIndex = 2;
					break;
				default: flightDestIndex = 0;
					break;		
			}

			switch(flightDateCode){
				case "0101": flightDateIndex = 0;
					break;
				case "0317": flightDateIndex = 1;
					break;
				case "0704": flightDateIndex = 2;
					break;
				default: flightDateIndex = 0;
					break;		
			}

			//Print out the seating chart for the plane
			System.out.println();
			System.out.println("Thank you! The following seats are available: ");
			System.out.println();

			schedule.getFlight(flightDestIndex, flightDateIndex, flightDestCode, flightDateCode);

			System.out.print("Is there an open seat you would like to reserve? (Y/N): ");
			readyToReserve = input.next();
			System.out.println();

			//Exit if user does not wish to reserve a seat
			if(!(readyToReserve.equals("Y") || readyToReserve.equals("y"))){
				goodBye();
				return;
			}

			readyToReserve = "N";

			//Obtain seat, repeat until user is satisfied with seat choice, or exits program
			while(!(readyToReserve.equals("Y") || readyToReserve.equals("y"))){
				//Prompt user for desired seat
				userRow = desiredRow();
				userAisle = desiredAisle();

				//Check desired seat for availability
				while(schedule.checkSeatStatus(flightDestIndex, flightDateIndex, (userRow - 1), (userAisle - 1))){
					System.out.println();
					System.out.println("We're sorry, that seat has already been reserved.");
					System.out.print("Would you like to see the seating chart again? (Y/N): ");
					readyToReserve = input.next();
					if(readyToReserve.equals("Y") || readyToReserve.equals("y")){
						System.out.println();
						schedule.getFlight(flightDestIndex, flightDateIndex, flightDestCode, flightDateCode);
					}
					userRow = desiredRow();
					userAisle = desiredAisle();
				}

				System.out.println();
				System.out.println("Okay, that seat is available!");
				System.out.print("Are you satisfied with your seating choice? (Y/N): ");
				readyToReserve = input.next();
				if(!(readyToReserve.equals("Y") || readyToReserve.equals("y"))){
					System.out.print("Would you like to choose a different seat? (Y/N): ");
					readyToReserve = input.next();
					if(!(readyToReserve.equals("Y") || readyToReserve.equals("y"))){
						goodBye();
						return;
					}
					readyToReserve = "N";
				}
			}

			//Prompt user for passenger name
			System.out.println();
			System.out.println("Wonderful! Now we need the name of the person flying with us.");
			userFirstName = userFName();
			userLastName = userLName();

			//Reserve Seat
			schedule.makeReservation(
				flightDestIndex, 
				flightDateIndex, 
				(userRow - 1),
				(userAisle - 1),
				userFirstName,
				userLastName);

			System.out.println();
			System.out.println("Congratulations, your seat is booked!");
			System.out.println();

			//Print reservation
			schedule.getFlightReservation(flightDestIndex, flightDateIndex, userRow - 1, userAisle - 1);

			System.out.print("Would you like to make another reservation? (Y/N): ");
			runAgain = input.next();
			System.out.println();
		}
		goodBye();
	}


	// -----------------
	//	    METHODS
	// -----------------


	//Begins and greets the user
	public static void greetUser(){
		System.out.println("Welcome to the Faul Airlines Reservation Program!");
		System.out.println("We are a small airline, and only fly from Denver to 3 cities:");
		System.out.println();
		System.out.println("         Airport              3-Letter Code");
		System.out.println("   New York - Laguardia:          LGA");
		System.out.println("   Los Angeles:                   LAX");
		System.out.println("   Chicago - Midway:              MDW");
		System.out.println();
		System.out.println("Additionally, we only fly 3 days a year:");
		System.out.println();
		System.out.println("         Date                 4-Digit Code:");
		System.out.println("   New Year's Day:                0101");
		System.out.println("   St. Patrick's Day:             0317");
		System.out.println("   Independence Day:              0704");
		System.out.println();
	}

	//Prompts user for desired destination
	public static String desiredDestination(){
		String dest;
		Scanner inputDest = new Scanner(System.in);
		System.out.print("Please enter the 3-Letter Code of your destination: ");
		dest = inputDest.next();
		return dest;
	}

	//Checks to see that desination is a valid
	public static Boolean validDestination(String dest){
		if(dest.equals("LAX")){
			return true;
		} else if(dest.equals("LGA")){ 
			return true;
		} else if(dest.equals("MDW")){ 
			return true;
		} else{
			return false;
		}
	}

	//Prompts user for date of desired flight
	public static String desiredDate(){
		String day;
		Scanner inputDay = new Scanner(System.in);
		System.out.print("Please enter the 4-Digit Code for the date of your flight: ");
		day = inputDay.next();
		return day;
	}

	//Checks to see that the date is valid
	public static Boolean validDate(String day){
		if(day.equals("0101")){
			return true;
		} else if(day.equals("0317")){
			return true;
		} else if(day.equals("0704")){
			return true;
		} else{
			return false;
		}
	}

	//Prompts user for desired seating row
	public static int desiredRow(){
		int seatRow;
		Scanner inputRow = new Scanner(System.in);
		System.out.print("Please enter the row (1-20) of the seat you would like to reserve: ");
		seatRow = inputRow.nextInt();
		while(seatRow > 20 || seatRow < 1){
			System.out.println();
			System.out.println("We're sorry, the row you have chosen is invalid.");
			System.out.print("Please enter the row (1-20) of the seat you would like to reserve: ");
			seatRow = inputRow.nextInt();
		}
		return seatRow;
	}

	//Prompts user for desired seating row
	public static int desiredAisle(){
		int seatAisle;
		Scanner inputAisle = new Scanner(System.in);
		System.out.print("Please enter the aisle(1-6) of the seat you would like to reserve: ");
		seatAisle = inputAisle.nextInt();
		while(seatAisle > 6 || seatAisle < 1){
			System.out.println();
			System.out.println("We're sorry, the row you have chosen is invalid.");
			System.out.print("Please enter the aisle (1-6) of the seat you would like to reserve: ");
			seatAisle = inputAisle.nextInt();
		}
		return seatAisle;
	}

	//Prompts user for and returns first name
	public static String userFName(){
		String fName;
		Scanner inputFName = new Scanner(System.in);
		System.out.print("Please enter the first name of the passenger: ");
		fName = inputFName.next();
		return fName;

	}

	//Prompts user for and returns last name
	public static String userLName(){
		String lName;
		Scanner inputLName = new Scanner(System.in);
		System.out.print("Please enter the last name of the passenger: ");
		lName = inputLName.next();
		return lName;
	}

	//Thanks the user and closes the program
	public static void goodBye(){
		System.out.println();
		System.out.println("Thank you for using the Faul Airlines Reservation Program.");
		System.out.println("Have a great day!");
		System.out.println();
	}





}










