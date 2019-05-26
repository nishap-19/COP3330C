
public class PassengerData {
	
	private String passengerID, passengerName, seatNumber, flightNumber;
	
	PassengerData(){}
	
	void setPassengerID(String id) {
		passengerID = id;
	}
	
	String getPassengerID() {
		return passengerID;
	}
	
	void setPassengerName(String name) {
		passengerName = name;
	}
	
	String getPassengerName() {
		return passengerName;
	}
	
	void setSeatNumber(String seat) {
		seatNumber = seat;
	}
	
	String getSeatNumber() {
		return seatNumber;
	}
	
	void setFlightNumber(String flight) {
		flightNumber = flight;
	}
	
	String getFlightNumber() {
		return flightNumber;
	}
}
