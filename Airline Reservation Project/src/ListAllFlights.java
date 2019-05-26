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

public class ListAllFlights {
	
    @FXML
    private Label flightDataLabel;
	
	public void initialize() {	
		
		//ArrayList of FlightData objects (all flights from flight file)
		ArrayList<FlightData> flights = GatherData.getFlightData();
		
		//creates data String and adds header line
    	String data = "Flight#  \t" + "FDate\t  " + "DTime\t" + "ATime\t" + "DepartCity\t" + "DestCity\t    " + "AvailableSeats\n";
    	FlightData flight;	//creates FlightData object
    	
    	//loops though the flights array and adds all items to data String
    	for(int x = 0; x < flights.size(); x++) {
    		flight = flights.get(x);
    		
    		if(flight.getDepartureCity().length() < 8 && flight.getDestinationCity().length() < 8)
    			data += flight.getFlightNumber() + "   \t" + flight.getFlightDate() + "\t  " + flight.getDepartureTime() 
    				+ "\t" + flight.getArrivalTime() + "\t" + flight.getDepartureCity() + "\t\t" + flight.getDestinationCity() + "\t\t   " + flight.getSeats() + "\n";
    		else if(flight.getDepartureCity().length() < 8)
    			data += flight.getFlightNumber() + "   \t" + flight.getFlightDate() + "\t  " + flight.getDepartureTime() 
				+ "\t" + flight.getArrivalTime() + "\t" + flight.getDepartureCity() + "\t\t" + flight.getDestinationCity() + "\t   " + flight.getSeats() + "\n";
    		else if(flight.getDestinationCity().length() < 8)
    			data += flight.getFlightNumber() + "   \t" + flight.getFlightDate() + " \t  " + flight.getDepartureTime() 
    				+ "\t" + flight.getArrivalTime() + "\t" + flight.getDepartureCity() + "\t" + flight.getDestinationCity() + "\t\t   " + flight.getSeats() + "\n";
    		else
    			data += flight.getFlightNumber() + "   \t" + flight.getFlightDate() + "\t  " + flight.getDepartureTime() 
    				+ "\t" + flight.getArrivalTime() + "\t" + flight.getDepartureCity() + "   \t" + flight.getDestinationCity() + "\t\t   " + flight.getSeats() + "\n";
    	}
		
	    flightDataLabel.setText(data); //adds all flight data to flightDataLabel
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
