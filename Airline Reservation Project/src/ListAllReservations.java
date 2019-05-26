import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ListAllReservations {

    @FXML
    private Label reservationDataLabel;

	public void initialize() {	
		
		//ArrayList of FlightData objects (all flights from flight file)
		ArrayList<PassengerData> passengers = GatherData.getPassengerData();
		
		//creates data String and adds header line
    	String data = "ID\t\t\t\t" + "Name\t\t\t" + "SeatNumber\t\t" + "Flight\n";
    	PassengerData passenger;	//creates PassengerData object
    	
    	//loops though the passengers array and adds all items to data String
    	for(int x = 0; x < passengers.size(); x++) {
    		passenger = passengers.get(x);
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
		
	    reservationDataLabel.setText(data); //adds all flight data to reservationDataLabel
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

}
