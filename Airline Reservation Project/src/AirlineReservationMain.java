import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AirlineReservationMain {

	@FXML	//launcher for DisplaySeatMaps
	void displaySeatMaps(ActionEvent event) throws IOException {
		Parent seat_map_parent =
			FXMLLoader.load(getClass().getResource("DisplaySeatMaps.fxml"));

		Scene seat_map_scene = new Scene(seat_map_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
		stage.setTitle("Passenger Seat Maps"); // displayed in window's title bar
		stage.setScene(seat_map_scene); // attach scene to stage
		stage.show(); // display the stage
		
	    stage.setOnCloseRequest(evt -> {
	        // prevent window from closing
	        evt.consume();
	    });
	}

	@FXML	//closes AirlineReservationSystem
	void exitProgram(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();	//terminates the window of the GUI
	}

	@FXML	//launcher for ListAllFlightsScene
	void listAllFlights(ActionEvent event) throws IOException {
		Parent seat_map_parent =
				FXMLLoader.load(getClass().getResource("ListAllFlights.fxml"));

			Scene seat_map_scene = new Scene(seat_map_parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
			stage.setTitle("All Flights"); // displayed in window's title bar
			stage.setScene(seat_map_scene); // attach scene to stage
			stage.show(); // display the stage
			
		    stage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();
		    });
	}

	@FXML	//launcher for ListAllReservtions scene
	void listAllReservations(ActionEvent event) throws IOException {
		Parent seat_map_parent =
				FXMLLoader.load(getClass().getResource("ListAllReservations.fxml"));

			Scene seat_map_scene = new Scene(seat_map_parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
			stage.setTitle("All Reservations"); // displayed in window's title bar
			stage.setScene(seat_map_scene); // attach scene to stage
			stage.show(); // display the stage
			
		    stage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();
		    });
	}

	@FXML	//launcher for ListSpecificReservation scene
	void listSpecificResearvation(ActionEvent event) throws IOException {
		Parent seat_map_parent =
				FXMLLoader.load(getClass().getResource("ListSpecificReservation.fxml"));

			Scene seat_map_scene = new Scene(seat_map_parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
			stage.setTitle("All Reservations"); // displayed in window's title bar
			stage.setScene(seat_map_scene); // attach scene to stage
			stage.show(); // display the stage
			
		    stage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();
		    });
	}

	@FXML	//launcher for AddNewFlight scene
	void newFlight(ActionEvent event) throws IOException {
		Parent new_flight_parent =
			FXMLLoader.load(getClass().getResource("AddNewFlight.fxml"));

		Scene new_flight_scene = new Scene(new_flight_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
		stage.setTitle("Add New Flight"); // displayed in window's title bar
		stage.setScene(new_flight_scene); // attach scene to stage
		stage.show(); // display the stage
		
	    stage.setOnCloseRequest(evt -> {
	        // prevent window from closing
	        evt.consume();
	    });
	}

	@FXML	//launcher for NewReservation scene
	void newReservation(ActionEvent event) throws IOException {
		Parent new_reservation_parent =
			FXMLLoader.load(getClass().getResource("NewReservation.fxml"));

		Scene new_reservation_scene = new Scene(new_reservation_parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();   	    
		stage.setTitle("Make New Reservation"); // displayed in window's title bar
		stage.setScene(new_reservation_scene); // attach scene to stage
		stage.show(); // display the stage
		
	    stage.setOnCloseRequest(evt -> {
	        // prevent window from closing
	        evt.consume();
	    });
	}

}