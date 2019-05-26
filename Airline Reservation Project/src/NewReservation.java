
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewReservation  {

	@FXML
	private TextField idTextField;
	
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

	@FXML
	private ComboBox<String> flightComboBox;

	@FXML
	private ComboBox<String> seatComboBox;
	
    @FXML
    private Label flightMapHeaderLabel;

	@FXML
    private Label seatLayoutLabel1;

    @FXML
    private Label seatLayoutLabel2;

    @FXML
    private Label seatLayoutLabel3;

    @FXML
    private Label seatLayoutLabel4;

    @FXML
    private Label seatLayoutLabel5;

    @FXML
    private Label seatLayoutLabel6;

    @FXML
    private Label seatLayoutLabel7;

    @FXML
    private Label seatLayoutLabel8;

    @FXML
    private Label seatLayoutLabel9;

    @FXML
    private Label seatLayoutLabel10;
	
	private String flightNumber, seatSelected;
	
	//List of flight numbers
	List<String> allFlights = GatherData.returnFlightNumbers();

	//ObservableList of flight numbers
	ObservableList<String> flightList = FXCollections.observableArrayList(allFlights);
	//uninitialized ObservableList for available seats
	ObservableList<String> availableSeats;
	
	SeatMapData map = new SeatMapData();	//creates a SeatMapData object to genterate a seat map
	
	public void initialize() {	
	    flightComboBox.setItems(flightList);	//adds flight numbers to flightComboBox
	}

    @FXML
    void selectFlight() {
    	flightNumber = flightComboBox.getValue();	//stores flight number selected
    	flightComboBox.setDisable(true);
    	flightMapHeaderLabel.setText("Flight Seat Map: " + flightNumber);
    	
    	//List of seat numbers
    	List<String> allSeats = GatherData.returnAvailableSeats(flightNumber);
    	
    	//initialized ObservableList for available seats
    	availableSeats = FXCollections.observableArrayList(allSeats);
    	
    	seatComboBox.setItems(availableSeats);	//adds seat numbers to seatComboBox
    	seatComboBox.setDisable(false);;	//enables seatComboBox
    	
    	map.loadSeatMapFromFile(flightNumber);	//updates the seat map array in SeatMaps
    	
    	char[][] seatMap = map.getSeatMap();	//creates 2D array to store seat map data
    	String row = "";
    	String[] rows = new String[seatMap.length];	//creates an array to store each row of the seat map
    	
    	//adds all available seats to rows array
    	for(int x = 0; x < seatMap.length; x++) {
    		for(int y = 0; y < seatMap[x].length; y++) {
    			if(x == 9 && y == 0)
    				row += 10 + "\t ";
    			else if(y == 0 || y == 5)
    				row += String.valueOf(seatMap[x][y]) + "\t ";
    			else if(y == 2)
    				row += String.valueOf(seatMap[x][y]) + "\t    ";
    			else
    				row += String.valueOf(seatMap[x][y]) + " ";
    		}
    		rows[x] = row;
    		row = "";
    	}
    	
    	//creates seat map display for the selected flight
    	seatLayoutLabel1.setText(rows[0]);
    	seatLayoutLabel2.setText(rows[1]);
    	seatLayoutLabel3.setText(rows[2]);
    	seatLayoutLabel4.setText(rows[3]);
    	seatLayoutLabel5.setText(rows[4]);
    	seatLayoutLabel6.setText(rows[5]);
    	seatLayoutLabel7.setText(rows[6]);
    	seatLayoutLabel8.setText(rows[7]);
    	seatLayoutLabel9.setText(rows[8]);
    	seatLayoutLabel10.setText(rows[9]);
    }

    @FXML
    void selectSeatNumber(ActionEvent event) {
    	flightComboBox.setDisable(true);
    	seatSelected = seatComboBox.getValue().toString();
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
	void submitReservation(ActionEvent event) {
		try {
			
			if(idTextField.getText().length() == 0)
				throw new NullPointerException();
			else if(firstNameTextField.getText().length() == 0 || lastNameTextField.getText().length() == 0)
				throw new NullPointerException();
				
			if(idTextField.getText().length() > 10)
				throw new StringIndexOutOfBoundsException();
			
			//creates a new PassengerData object
			PassengerData newPassenger = new PassengerData();
			newPassenger.setPassengerID(idTextField.getText());
			newPassenger.setPassengerName(firstNameTextField.getText() + " " + lastNameTextField.getText());
			newPassenger.setSeatNumber(seatSelected);
			newPassenger.setFlightNumber(flightNumber);
			
			//creates an ArrayList of PassengerData objects (all passengers in reservations.txt)
			ArrayList<PassengerData> passengers = GatherData.getPassengerData();
			passengers.add(newPassenger);
			
			//calls method to update reservations file
			GatherData.updatePassengerData(passengers);
			
			//calls method to update the seat map file (all flights in flights.txt)
			map.updateSeatMapFile(flightNumber, seatSelected);
			
			//creates an ArrayList of FlightData objects
			ArrayList<FlightData> flights = GatherData.getFlightData();
			
			//finds the flight selected and decreases the number of available seats
			for(int x =0; x < flights.size(); x++) {
				if(flights.get(x).getFlightNumber().equals(flightNumber))
					flights.get(x).setSeats(flights.get(x).getSeats()-1);
			}
			
			//calls method to update flights file
			GatherData.updateFlightData(flights);
		
			//displays confirmation for booking seat
			Alert confirm = new Alert(Alert.AlertType.INFORMATION);
			confirm.setTitle("Booking New Reservation");
			confirm.setContentText("Reservation for " + newPassenger.getPassengerName() + " for seat " +
					newPassenger.getSeatNumber() + " on flight " + newPassenger.getFlightNumber() + 
					" has been booked!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			
			try {
				cancelAndReturnHome(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(StringIndexOutOfBoundsException ex) {
			Alert confirm = new Alert(Alert.AlertType.ERROR);
			confirm.setTitle("Error");
			confirm.setContentText("Please enter only up to 6 characters for flight id!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			return;
		}catch(NullPointerException ex) {
			Alert confirm = new Alert(Alert.AlertType.ERROR);
			confirm.setTitle("Error");
			confirm.setContentText("Please fill in all entries!");
			confirm.setHeaderText(null);
			confirm.showAndWait();
			return;
		}
	}
}
