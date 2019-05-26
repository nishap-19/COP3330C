import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ListSpecificReservation {

    @FXML
    private ComboBox<String> flightComboBox;

    @FXML
    private Label flightReservationLabel;

    @FXML
    private Label reservationDataLabel;

    //List of flight numbers
  	List<String> allFlights = GatherData.returnFlightNumbers();

  	//ObservableList of flight numbers
  	ObservableList<String> flightList = FXCollections.observableArrayList(allFlights);
	
	public void initialize() {	
	    flightComboBox.setItems(flightList);	//adds flight numbers to flightComboBox
	}
    
    @FXML
    void cancelAndReturnHome(ActionEvent event) throws IOException {
		Parent parent =
				FXMLLoader.load(getClass().getResource("AirlineReservationMain.fxml"));

			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
			stage.setTitle("Airline Reservation System"); // displayed in window's title bar
			stage.setScene(scene); // attach scene to stage
			stage.show(); // display the stage
			
		    stage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();
		    });
    }
    

    @FXML
    void selectFlight(ActionEvent event) {
    	
    	String flightNumber = flightComboBox.getValue();
    	
    	//ArrayList of PassengerData objects (all flights from reservations file)
    	ArrayList<PassengerData> passengers = GatherData.getPassengerData();
    	//ArrayList of PassengerData objects (only reservations with correct flight number)
    	ArrayList<PassengerData> selectPassengers = new ArrayList<PassengerData>();
    	
    	//adds reservations with correct flight number to ArrayList
    	for(int x = 0; x < passengers.size(); x++) {
    		if(passengers.get(x).getFlightNumber().equals(flightNumber))
    			selectPassengers.add(passengers.get(x));
    	}
    	
    	//creates data String and adds header line
    	String data = "ID\t\t\t\t" + "Name\t\t\t" + "SeatNumber\t\t" + "Flight\n";
    	PassengerData passenger;	//creates PassengerData object
    	
    	//loops though the passengers array and adds all items to data String
    	for(int x = 0; x < selectPassengers.size(); x++) {
    		passenger = selectPassengers.get(x);
    		String id = "", name = "";
    		
    		for(int y = 0; y < 10-passenger.getPassengerID().length(); y++) {
    			id += " ";
    		}
    		
    		for(int y = 0; y < 25-passenger.getPassengerName().length(); y++) {
    			name += " ";
    		}
    		if(passenger.getPassengerName().length()<11)
    			name += "  ";
    		if(passenger.getPassengerName().length()<14)
    			name += " ";
    		
    		data += passenger.getPassengerID() + id + "\t\t" + passenger.getPassengerName() + name + "\t" 
    				+ passenger.getSeatNumber() + "\t\t\t" + passenger.getFlightNumber() + "\n";
    	}
		
    	flightReservationLabel.setText("Flight Reservations: " + flightNumber);	//updates label with flight number
	    reservationDataLabel.setText(data); //adds all flight data to reservationDataLabel
    }

}
