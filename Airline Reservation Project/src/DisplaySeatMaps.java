import java.io.IOException;
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

public class DisplaySeatMaps {

    @FXML
    private ComboBox<String> flightComboBox;

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

    private String flightNumber;
    
    //List of flight numbers
  	List<String> allFlights = GatherData.returnFlightNumbers();

  	//ObservableList of flight numbers
  	ObservableList<String> flightList = FXCollections.observableArrayList(allFlights);
  	
	SeatMapData map = new SeatMapData();	//creates a SeatMapData object to generate a seat map
	
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
    void viewMap(ActionEvent event) {
    	flightNumber = flightComboBox.getValue();	//stores flight number selected
    	
    	flightMapHeaderLabel.setText("Flight Seat Map: " + flightNumber);
 
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

}
