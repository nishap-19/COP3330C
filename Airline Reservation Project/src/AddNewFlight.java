import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class AddNewFlight {

	@FXML
	private TextField flightNumberTextField;

	@FXML
	private TextField flightDateTextField;

	@FXML
	private TextField departureTimeTextField;

	@FXML
	private TextField arrivalTimeTextField;

	@FXML
	private TextField departureCityTextField;

	@FXML
	private TextField destinationCityTextField;

	@FXML
	private TextField availableSeatsTextField;

	@FXML	//allows the user to return to the main menu
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
	void createNewFlight(ActionEvent event) {
	
		try {
			if(flightNumberTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(flightDateTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(departureTimeTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(arrivalTimeTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(departureCityTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(destinationCityTextField.getText().length() == 0)
					throw new NullPointerException();
			
			if(flightNumberTextField.getText().length() > 6)
				throw new StringIndexOutOfBoundsException();
			else if(departureTimeTextField.getText().length() != 5 || arrivalTimeTextField.getText().length() != 5)
				throw new InputMismatchException();
			
			FlightData newFlight = new FlightData();	//creates FlightData object
			
			//adds all fields to FlightData object
			newFlight.setFlightNumber(flightNumberTextField.getText());
			newFlight.setFlightDate(flightDateTextField.getText());
			newFlight.setDepartureTime(departureTimeTextField.getText()); 
			newFlight.setArrivalTime(arrivalTimeTextField.getText()); 
			newFlight.setDepartureCity(departureCityTextField.getText()); 
			newFlight.setDestinationCity(destinationCityTextField.getText());
			newFlight.setSeats(70);
	
			//creates an ArrayList of FlightData objects (all flights in flights.txt)
			ArrayList<FlightData> flights = GatherData.getFlightData();
			flights.add(newFlight);	//adds newFlight to the flights array
			
			//calls method to update flights.txt
			GatherData.updateFlightData(flights);
			
			//calls method to make a new seat map file
			SeatMapData map = new SeatMapData();
			map.printSeatMapToFile(newFlight.getFlightNumber());
		
			Alert confirm = new Alert(Alert.AlertType.INFORMATION);
			confirm.setTitle("Add New Flight");
			confirm.setContentText("Flight " + newFlight.getFlightNumber() + " has been created!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			
			try {
				cancelAndReturnHome(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(NullPointerException ex) {
			Alert confirm = new Alert(Alert.AlertType.ERROR);
			confirm.setTitle("Error");
			confirm.setContentText("Please fill in all entries!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			return;
		}catch(StringIndexOutOfBoundsException ex) {
			Alert confirm = new Alert(Alert.AlertType.ERROR);
			confirm.setTitle("Error");
			confirm.setContentText("Please enter only up to 6 characters for flight id!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			return;
		}catch(InputMismatchException ex) {
			Alert confirm = new Alert(Alert.AlertType.ERROR);
			confirm.setTitle("Error");
			confirm.setContentText("Please use format HH:MM for date!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			return;
		}
	}
}

