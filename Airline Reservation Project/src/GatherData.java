import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class GatherData {

	private static ArrayList<FlightData> allFlights;
	private static ArrayList<PassengerData> flightPassangers;
	
	GatherData(){}

	//returns an array of FlightData objects
	public static ArrayList<FlightData> getFlightData() {

		//initializes ArrayList of FlightData objects
		allFlights = new ArrayList<FlightData>();

		try {
			//finds the flight.txt file
			Scanner flightFile = new Scanner(new File(System.getProperty("user.dir") + "/flights.txt"));
			Scanner dataScan;	//Scanner object to scan each line of the file
			String data;	//Scanner object to scan each data item in the line given
			String flightNumber, flightDate, departureTime, arrivalTime, departureCity, destinationCity;
			int availableSeats;

			flightFile.nextLine();	//skips the header line of the file

			//runs the command to add flights to the ArrayList until there is no more data
			while (flightFile.hasNext()) {

				data = flightFile.nextLine();	//reads in a line of text
				dataScan= new Scanner(data);	//reads each item in the line of text

				//scans in all data items and sets to variables
				flightNumber = dataScan.next();
				flightDate = dataScan.next();
				departureTime = dataScan.next();
				arrivalTime = dataScan.next();
				departureCity = dataScan.next();
				destinationCity = dataScan.next();
				availableSeats = dataScan.nextInt();

				//Create FlightData object
				FlightData flight = new FlightData();
				flight.setFlightNumber(flightNumber);
				flight.setFlightDate(flightDate);
				flight.setDepartureTime(departureTime);
				flight.setArrivalTime(arrivalTime);
				flight.setDepartureCity(departureCity);
				flight.setDestinationCity(destinationCity);
				flight.setSeats(availableSeats);

				allFlights.add(flight);	//adds new flight object to ArrayList
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return allFlights;
	}
	
	//updates flights.txt
	public static void updateFlightData(ArrayList<FlightData> flights) {
		//finds the flights.txt file
		File flightFile = new File(System.getProperty("user.dir") + "/flights.txt");
		try {
			//creates Formatter object to use to write to file, will overwrite pre-existing contents
			Formatter output = new Formatter(flightFile);
			
			//writes header to file
			output.format("%-10s %-10s %-10s %-10s %-15s %-13s %s%n", "Flight#", "FDate", "DTime", "ATime", "DepartCity", "DestCity", "AvailableSeats");
			
			//writes all flights from flights array to file
			for(int i = 0; i < flights.size(); i++) {
				FlightData flight = flights.get(i);	//gets individual FlightData objects from flight array
				
				//prints contents of each FlightData object to file
				output.format("%-10s %-10s %-10s %-10s %-15s %-18s %d%n", flight.getFlightNumber(), 
					flight.getFlightDate(), flight.getDepartureTime(), flight.getArrivalTime(), 
					flight.getDepartureCity(), flight.getDestinationCity(), flight.getSeats());
			}	
			//close output Formatter
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//returns an ArrayList of PassengerData objects
	public static ArrayList<PassengerData> getPassengerData(){

		//initializes array of PassengerData objects
		flightPassangers = new ArrayList<PassengerData>();

		try {
			//finds the reservations.txt file
			Scanner reservationFile = new Scanner(new File(System.getProperty("user.dir") + "/reservations.txt"));
			Scanner dataScan;	//Scanner object to scan each line of the file
			String data;	//Scanner object to scan each data item in the line given
			String passengerID, passengerName, seatNumber, flightNumber;

			reservationFile.nextLine();	//skips the header line of the file

			//runs the command to add passengers to the ArrayList until there is no more data
			while (reservationFile.hasNext()) {

				data = reservationFile.nextLine();	//reads in a line of text
				dataScan= new Scanner(data);	//reads each item in the line of text

				//scans in all data items and sets to variables
				passengerID = dataScan.next();
				passengerName = dataScan.next() + " " + dataScan.next();
				seatNumber = dataScan.next();
				flightNumber = dataScan.next();

				//Create PassengerData object
				PassengerData passenger = new PassengerData();
				passenger.setPassengerID(passengerID);
				passenger.setPassengerName(passengerName);
				passenger.setSeatNumber(seatNumber);
				passenger.setFlightNumber(flightNumber);

				flightPassangers.add(passenger);	//adds new passenger object to ArrayList
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return flightPassangers;
	}
	
	public static void updatePassengerData(ArrayList<PassengerData> passengers) {
		//finds the reservation.txt file
		File reservationFile = new File(System.getProperty("user.dir") + "/reservations.txt");
		try {
			//creates Formatter object to use to write to file, will overwrite pre-existing contents
			Formatter output = new Formatter(reservationFile);
			
			//writes header to file
			output.format("%-11s %-18s %-13s %s%n", "ID", "Name", "SeatNumber", "Flights");
			
			//writes all flights from flights array to file
			for(int i = 0; i < passengers.size(); i++) {
				PassengerData passenger = passengers.get(i);	//gets individual PassengerData objects from passenger array
				
				//prints contents of each ReservationData object to file
				output.format("%-11s %-18s %-13s %s%n", passenger.getPassengerID(), passenger.getPassengerName(), passenger.getSeatNumber(), passenger.getFlightNumber());
			}	
			//close output Formatter
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//returns an ArrayList of flight numbers
	public static ArrayList<String> returnFlightNumbers() {

		//ArrayList to hold flight numbers
		ArrayList<String> allFlights = new ArrayList<String>();

		//ArrayList to hold FlightData of all flights
		ArrayList<FlightData> flights = GatherData.getFlightData();
		
		//checks if a flight has available seats
		for(int x = 0; x < flights.size(); x++) {
			if(flights.get(x).getSeats() != 0)	//checks number of seat available
				allFlights.add(flights.get(x).getFlightNumber());	//adds flight to ArrayList
		}
		
		return allFlights;
	}
	
	//returns an ArrayList of seat numbers
	public static ArrayList<String> returnAvailableSeats(String flightNumber){
		
		//
		ArrayList<String> availableSeats = new ArrayList<String>();
		
		//creates SeatMapData object
		SeatMapData seatMap = new SeatMapData();
		
		seatMap.loadSeatMapFromFile(flightNumber);
		
		//copies char array from file
		char[][] allSeats = seatMap.getSeatMap();
		
		for(int x = 0; x < allSeats.length; x++) {
			for(int y = 1; y < allSeats[x].length; y++) {
				String seat;
				
				if(allSeats[x][y] != 'X') {
					if(x == 9)
						seat = x+1 + String.valueOf(allSeats[x][y]);
					else
						seat = x+1 + String.valueOf(allSeats[x][y]);
					availableSeats.add(seat);
				}
			}
		}
		
		return availableSeats;
	}
}
