
public class FlightData {

	private String flightNumber, flightDate, departureTime, arrivalTime, departureCity, destinationCity;
	private int availableSeats;

	public FlightData() {
	}

	void setFlightNumber(String flightNum) {
		flightNumber = flightNum;
	}

	String getFlightNumber() {
		return flightNumber;
	}

	void setFlightDate(String date) {
		flightDate = date;
	}

	String getFlightDate() {
		return flightDate;
	}

	void setDepartureTime(String departTime) {
		departureTime = departTime;
	}

	String getDepartureTime() {
		return departureTime;
	}

	void setArrivalTime(String arriveTime) {
		arrivalTime = arriveTime;
	}

	String getArrivalTime() {
		return arrivalTime;
	}

	void setDepartureCity(String departCity) {
		departureCity = departCity;
	}

	String getDepartureCity() {
		return departureCity;
	}

	void setDestinationCity(String destCity) {
		destinationCity = destCity;
	}

	String getDestinationCity() {
		return destinationCity;
	}

	void setSeats(int seats) {
		availableSeats = seats;
	}

	int getSeats() {
		return availableSeats;
	}
}
