package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {
	
	static List <City> city = new ArrayList<>();     // city list has all vertices
	static List <City> citys = new ArrayList<>();     // city list has all vertices 

	static List <Edges> edges = new ArrayList<>();   // edges list has all Edges 
	static List <Edges> edgess = new ArrayList<>();   // edges list has all Edges 
	
	
	
	
	    ComboBox<City> SourceBox = new ComboBox<>();  
	    ComboBox<City> TargetBox = new ComboBox<>();
	    
	    
		TextField DistanceField ;
		TextArea areaPath ;
		int virticesNumber;
		int edgesNumber;
		
		
		static double MxMin = 34.1707489947603;
		static double MxMax = 34.575060834817954;
		static double MyMin = 31.614521165206845;
		static double MyMax = 31.208163033163977;
		
		static double WxMax = 820;
		static double WyMax = 1126;
		//static double WyMax = 1200;

		
		
		
		CityMap Map;
		AnchorPane root = new AnchorPane();


	@Override
	public void start(Stage primaryStage) throws ParseException, IOException {
		nextScreen ();

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//___________________________________________________________________________________________________________________________
	
	public Pane firstScrren () {
		Stage stage = new Stage ();
		BorderPane pane = new BorderPane ();
		
		Label Title = new Label();
		Title.setText("Gaza Strip Cities");
		Title.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
		Title.setStyle("-fx-text-fill:blue");// set colour to the label font 

	    Image image1 = new Image("C:\\Users\\Lenovo\\Downloads\\gazaPhoto1.jpg"); // add image to the scene 
		ImageView imageView = new ImageView(image1);
		imageView.setFitWidth(600);
		imageView.setFitHeight(400);
		
		Button Read = new Button ("Read File ");
		Button Next = new Button ("Next");
		Read.setPrefWidth(200);
		Next.setPrefWidth(200);
		
		HBox hBox = new HBox(20);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(Read , Next);
		
		VBox vBox = new VBox (20);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(Title,imageView,hBox);
		
		Scene scen = new Scene (pane , 700 , 600);
		stage.setScene(scen);
		stage.setTitle("Gaza Screen");
		stage.show();
		
		
		pane.setPadding(new Insets (5 ,5,5,5));
		pane.setStyle("-fx-background-color:LIGHTBLUE");
		pane.setCenter(vBox);
		
		Next.setOnAction(e->{
			try {
				nextScreen ();
			} catch (ParseException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stage.close();
			
		});
		Read.setOnAction(e->{
			
		});
		
		return pane ;
	}
	
	
	//__________________________________________________________________________________________________________________________________
	
	public void nextScreen () throws ParseException, IOException {
		
		Stage stage = new Stage ();
		GridPane pane = new GridPane ();
		BorderPane Bpane= new BorderPane();
		
		Map = readFileCity (); // Invoke method
		Dijkstra dijkstra = new Dijkstra();
		
		
		Label Title = new Label();
		Title.setText("    Gaza Strip Map");
		Title.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,30)); // set font to the label
		Title.setStyle("-fx-text-fill:blue");// set colour to the label font 


		
		Label sLabel = new Label();
		sLabel.setText("Source");
		sLabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		sLabel.setStyle("-fx-text-fill:black");// set colour to the label font 

		Label tLabel = new Label();
		tLabel.setText("Target");
		tLabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		tLabel.setStyle("-fx-text-fill:black");// set colour to the label font 

		Label pLabel = new Label();
		pLabel.setText("Path");
		pLabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		pLabel.setStyle("-fx-text-fill:black");// set colour to the label font 

		Label Dlabel = new Label();
		Dlabel.setText("Distance");
		Dlabel.setFont(Font.font("Time New Roman" , FontWeight.BOLD , FontPosture.ITALIC ,15)); // set font to the label
		Dlabel.setStyle("-fx-text-fill:black");// set colour to the label font 

		DistanceField = new TextField(); 
		DistanceField.setPrefWidth(200);
		DistanceField.setPromptText("Total Distance");
	
		
	    areaPath = new TextArea();	
	    areaPath.setPrefSize(300, 250);
	    areaPath.setEditable(false);
	    areaPath.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
	    areaPath.setStyle("-fx-text-fill: black;");

	    
			
		Image image1 = new Image("C:\\Users\\Lenovo\\Downloads\\GazaMap.png" , 1000, 1000, false, false); // add image to the scene 
		ImageView imageView = new ImageView(image1);
		imageView.setFitWidth(820);
		imageView.setFitHeight(1126);
		//imageView.setLayoutX(0);
		//imageView.setLayoutY(0);

		
		root.getChildren().add(imageView);
		
		Button Run = new Button ("Run");
		Button Clean = new Button ("Clean");
		Button Close = new Button ("Close");
		Run.setPrefWidth(130);
		Clean.setPrefWidth(130);
		Clean.setDisable(true);
		Close.setPrefWidth(130);
		Run.setFont(Font.font("Time New Roman" , FontWeight.BOLD ,15));
		Clean.setFont(Font.font("Time New Roman" , FontWeight.BOLD ,15));
		Close.setFont(Font.font("Time New Roman" , FontWeight.BOLD ,15));

		
		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(Run , Clean , Close);
		
		HBox SBox = new HBox(20);
		SBox.setAlignment(Pos.CENTER);
		SBox.getChildren().addAll(sLabel , SourceBox);
		SourceBox.setPrefWidth(150);
		
		VBox PBox = new VBox(20);
		PBox.setAlignment(Pos.CENTER);
		PBox.getChildren().addAll(pLabel , areaPath);
		
		/*
		VBox MBox = new VBox(20);
		MBox.setAlignment(Pos.CENTER);
		MBox.getChildren().addAll(Title , imageView);
		*/
		
		HBox TBox = new HBox(20);
		TBox.setAlignment(Pos.CENTER);
		TBox.getChildren().addAll(tLabel , TargetBox);
		TargetBox.setPrefWidth(150);
		
		HBox DBox = new HBox(20);
		DBox.setAlignment(Pos.CENTER);
		DBox.getChildren().addAll(Dlabel , DistanceField);
		
		
		VBox vBox = new VBox (40);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(30);
		vBox.setLayoutX(1135);
		vBox.setLayoutY(40);	
		vBox.getChildren().addAll(SBox , TBox , PBox , DBox, buttonBox);
		
		
		/*
		pane.setPadding(new Insets (10,10,10,10));
		pane.setHgap(60); //horizontal gap in pixels
		pane.setVgap(40); //vertical gap in pixels
		pane.add(MBox, 1, 0);
		pane.add(vBox, 4, 0);
		pane.setStyle("-fx-background-color:LIGHTBLUE");
*/
				
		
		for(City city : city) {   // Add all cities to ComboBoxes and circles to the root pane
			Circle circle = buildCityCircle(city); // Invoke convert method to get a circle
			if (circle == null)
				continue;
			root.getChildren().add(circle); // Add circles to the root pane
			root.getChildren().add(createCityLabel(city));// Invoke method and put the city name in the root
			
			// add city name to combox 
			SourceBox.getItems().add(city);
			TargetBox.getItems().add(city);
			
		}
		
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(root);
		scrollPane.setPrefSize(1126, 820);
		
		
		AnchorPane ground = new AnchorPane();
		ground.getChildren().add(scrollPane);
		ground.getChildren().add(vBox);
		ground.setStyle("-fx-background-color:LIGHTBLUE");
		//ground.setBackground(background);
		
		
		//___________________________________________________________________________________________________________
		//Actions :- 
		
		Run.setOnAction(e->{
			Clean.setDisable(false);
			if (SourceBox.getValue() != null && TargetBox.getValue() != null) {
				List<City> path = dijkstra.shortestPath(SourceBox.getValue(),TargetBox.getValue(), Map);
				
				if (path == null) {	// If no path
					Alert alert = new Alert(Alert.AlertType.ERROR);// ERROR is a type of alert
					alert.setTitle("Error");
					alert.setHeaderText("No Path");	// Bold font above(summary)
					alert.setContentText("There is no path between " + SourceBox.getValue() + " and " + TargetBox.getValue());	// Content text
					alert.showAndWait();
					
					

				} else {	// Print in textField and textArea
					for (int i = 0; i < path.size() - 1; i++) {	// Loop on each city in the path
						City currentCity = path.get(i);	// current
						City nextCity= path.get(i + 1);	// next
						

						Circle markerSource = buildCityCircle(SourceBox.getValue());
						markerSource.setFill(Color.RED);
						
						Circle markerDestination = buildCityCircle(TargetBox.getValue());
						markerDestination.setFill(Color.RED);
						
						
						
						root.getChildren().add(drawCityConnection(currentCity, nextCity));// Invoke method to print the line

						

						areaPath.appendText("Step " + (i + 1) + " : " + currentCity.cityName + " ==> " + nextCity.cityName + "\n");
					}

					DistanceField.setText(Map.distance.get(TargetBox.getValue()) + " Km");
				}	// end else
						

			}
		});
		
		//______________________________________________________________________________________________________________________
		// to reset all pane :-
		
		Clean.setOnAction(e->{
			root.getChildren().clear();   // clear the root pane 
			ground.getChildren().clear();
			
			SourceBox.setValue(null);
			TargetBox.setValue(null);
			
			Clean.setDisable(true);
			root.getChildren().add(imageView);	// Add map to the root pane
			for (City cityes : city) {// Add circles to the map
				Circle circle = buildCityCircle(cityes);// Invoke convert method to get a circle
				if (circle == null)
					continue;
				root.getChildren().add(circle);// Add circles to the map
				root.getChildren().add(createCityLabel(cityes));// Add labels to the map
			}
			vBox.getChildren().clear();
			vBox.getChildren().addAll(SBox , TBox , PBox , DBox, buttonBox);

			areaPath.setText("");
			DistanceField.clear();

			ground.getChildren().add(vBox);
			ground.getChildren().add(scrollPane);
			

			
					try {
						Map = readFileCity ();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				

			
			
			
		});
		
		Close.setOnAction(e -> stage.close());

		
		
		Scene scene = new Scene(ground , 1600 , 880);
		stage.setScene(scene);
		stage.setTitle("Gaza Map ");
		stage.show();
		
	}
	
	
	//__________________________________________________________________________________________________________________________
	// Read file to build graph with all vertices (Cites) and all edges (Street) :- 
	
	
	public CityMap readFileCitys () throws IOException{
		CityMap g = new CityMap();
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\Desktop\\Gaza.txt"));
		String line ;
		boolean isCity = true ;  // if the line continue city name 
		String firstLine = reader.readLine();  // first lint with numOfCity and numOfEdges 
		
		System.out.println(firstLine);
		String[] part = firstLine.split(",");
		
		int  vartecNumber = Integer.parseInt(part[0])    ;
		int  edgesNumber = Integer.parseInt(part[1]);
		

		while ((line = reader.readLine()) != null) {
			

			if (line.equals(firstLine))
				continue;

			// If the numOfBuildings has been finished
			if (line.equals("Edges")) {
				isCity = false;
				System.out.println("end");
				
				continue;
			}
			
			else if (isCity) {// If the line contains of city name with it's x and y
				
				
				for (int i = 1 ; i <= vartecNumber ; i++) {
				String[] parts = line.split(",");
				String Name = parts[0]; // index is the City name
				
				
				double lat = Double.parseDouble(parts[1]); //  Latitude coordinate 
				double lon = Double.parseDouble(parts[2]); //  longitude coordinate
				
				
				double Xcoordenaat = FindX(lon);
				double Ycorrdanat = FindY(lat);
			
				City Newcity = new City(Name, lat, lon , Xcoordenaat , Ycorrdanat);
				System.out.println(Newcity.getCityName()+" , "+ Newcity.getLatitude()+" , "+Newcity.getLongitude()+" , " +Newcity.X+" ,"+Newcity.Y);
				city.add(Newcity); // Add all buildings to the list
				g.addCity(Newcity);

				}
			}	

			else {
				for (int j = 1 ; j <= edgesNumber ; j++) {
				String[] parts = line.split(",");
				if (parts.length >= 2) {
					
					
				    String source = parts[0].trim(); // sourCity
				    String destination = parts[1].trim(); // destinationCity
				    
				//String source = parts[0]; // sourceCity
				//String destination = parts[1].trim(); // destinationCity

				City sourceCity = null;
				City destinationCity = null;
				

				// Loop for all buildings list to find sourceBuilding and destinationBuilding
				for (City city  : city) {
					if (city.cityName.equals(source))
						sourceCity = city;
					else if (city.cityName.equals(destination))
						destinationCity = city;
				}

				double distance = getDistance (sourceCity.Latitude , sourceCity.Longitude , destinationCity.Latitude , destinationCity.Longitude );// Distance between those two city

				Edges edge = new Edges(sourceCity, destinationCity, distance);
				//System.out.println(edge.Source.cityName+" , "+ edge.destenation.cityName+ " , "+edge.weight);
				
				edges.add(edge); // Add all edges to the list
				
				
				}
				
			} 
		}
			
			
		}//end While 
		
		reader.close(); // Close the BufferedReader

	 

	 
	 for (City cityy : city) // Add all buildings to the graph
		g.addCity(cityy);

	for (Edges edge : edges) // Add all edges to the graph
		g.addEdges(edge.Source, edge.destenation, edge.weight);

	return g; // Return graph with all buildings and edges

}

	
	//__________________________________________________________________________________________________________________________
	
	// this method to get a distance between two city according to the Latitude and longitude :- 
	
	public static double getDistance (double lat1, double lon1, double lat2, double lon2) {  
		
        double R = 6371; // Earth radius in kilometres

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    
	}
	
	//_____________________________________________________________________________________________________________________________
	// this method to convert the city to a circle to add in the pane :-
	
	private Circle buildCityCircle  (City citys) {  // convert
		double xCoordinate = citys.X;
		double yCoordinate = citys.Y;
		
		Circle cc = new Circle(8);
		cc.setLayoutX(xCoordinate );
		cc.setLayoutY(yCoordinate);
		cc.setFill(Color.GREEN);

		
		cc.setOnMouseClicked(e -> {
			if (SourceBox.getValue() == null) {// Change the comboBoxSource value from the circle
				SourceBox.setValue(citys);
				cc.setFill(Color.RED);
			}
			else { // Change the comboBoxDestination value from the circle
				TargetBox.setValue(citys);
				cc.setFill(Color.RED);
			}
		});

		return cc; // Return circle

	}
	
	
	//_________________________________________________________________________________________________________________________________
	// this method to draw line between source and destination according to the X and Y for the circle :-
	
	private Line drawCityConnection (City source, City destination) {    //drowline
		Line line = new Line();
		// Get x and y from the circle coordinate
		line.setStartX(buildCityCircle(source).getLayoutX());
		line.setStartY(buildCityCircle(source).getLayoutY());
		line.setEndX(buildCityCircle(destination).getLayoutX());
		line.setEndY(buildCityCircle(destination).getLayoutY());
		line.setStroke(Color.BLACK);
		
		
       // Line.setStrokeWidth(5); // Set the stroke width to make it bold

		
		/*
		 double arrowSize = 10;
		 double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX());
		  double arrowX1 = line.getEndX() - arrowSize * Math.cos(angle - Math.toRadians(45));
		  double arrowY1 = line.getEndY() - arrowSize * Math.sin(angle - Math.toRadians(45));
		    double arrowX2 = line.getEndX() - arrowSize * Math.cos(angle + Math.toRadians(45));
		    double arrowY2 = line.getEndY() - arrowSize * Math.sin(angle + Math.toRadians(45));

		    Polygon arrowhead = new Polygon(line.getEndX(), line.getEndY(), arrowX1, arrowY1, arrowX2, arrowY2);
		    arrowhead.setFill(Color.BLACK);

		    Group arrowGroup = new Group(line, arrowhead);
		    
		    root.getChildren().add(arrowGroup);
		    
		*/
		    

		return line;
	}
	
	
	
	//_________________________________________________________________________________________________________________________________
	// this method to Change the City name to a Label to put in the pane :-
	
	private Label createCityLabel (City citys) {  // generateLabel
		Circle circle = buildCityCircle(citys); // Invoke method which return a circle

		if (circle == null) // If no circle
			return null;

		Font fontBuildings = Font.font("Courier New", FontWeight.BOLD, 12);// The font size of the label

		Label label = new Label(citys.cityName); // city name
		label.setLayoutX(circle.getLayoutX() + 10); // Get the x coordinate from the circle
		label.setLayoutY(circle.getLayoutY() - 10); // Get the y coordinate from the circle
		label.setFont(fontBuildings);
		label.setTextFill(Color.BLACK);
		
		
		return label;
	}
	
	//____________________________________________________________________________________________________________________
	
	
	private double FindX(double lon) {
		
		double X = ((WxMax *(lon-MxMin)) / (MxMin - MxMax));
		
		return Math.abs(X);
		
	}
	
	
	//____________________________________________________________________________________________________________________

	private double FindY(double lat ) {
		
		double Y = ((WyMax *(lat-MyMin)) / (MyMin - MyMax));
		return Math.abs(Y);
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//__________________________________________________________________________________________________________________
	
	public CityMap readFileCity () throws IOException{
		
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Lenovo\\Desktop\\City.txt"));
			String line ;
			boolean isCity = true ;  // if the line continue city name 
			String firstLine = reader.readLine();  // first lint with numOfCity and numOfEdges 
			
			System.out.println(firstLine);
			String[] part = firstLine.split(",");
			
			int  vartecNumber = Integer.parseInt(part[0])    ;
			int  edgesNumber = Integer.parseInt(part[1]);
			

			while ((line = reader.readLine()) != null) {
				

				if (line.equals(firstLine))
					continue;

				// If the numOfBuildings has been finished
				if (line.equals("Edgies:")) {
					isCity = false;
					System.out.println("end");
					
					continue;
				}
				
				else if (isCity) {// If the line contains of city name with it's x and y
					
					String[] parts = line.split(",");
					String index = parts[0]; // index is the City name
					
					
					double lat = Double.parseDouble(parts[1]); //  Latitude coordinate 
					double lon = Double.parseDouble(parts[2]); //  longitude coordinate
					
					
					double Xcoordenaat = FindX(lon);
					double Ycorrdanat = FindY(lat);
				
					City Newcity = new City(index, lat, lon , Xcoordenaat , Ycorrdanat);
					System.out.println(Newcity.getCityName()+" , "+ Newcity.getLatitude()+" , "+Newcity.getLongitude()+" , " +Newcity.X+" ,"+Newcity.Y);
					city.add(Newcity); // Add all buildings to the list

					
				}	

				else {
					
					String[] parts = line.split(",");
					if (parts.length >= 2) {
						
						
					    String source = parts[0].trim(); // sourCity
					    String destination = parts[1].trim(); // destinationCity
					    
					//String source = parts[0]; // sourceCity
					//String destination = parts[1].trim(); // destinationCity

					City sourceCity = null;
					City destinationCity = null;
					

					// Loop for all buildings list to find sourceBuilding and destinationBuilding
					for (City city  : city) {
						if (city.cityName.equals(source))
							sourceCity = city;
						else if (city.cityName.equals(destination))
							destinationCity = city;
					}

					double distance = getDistance (sourceCity.Latitude , sourceCity.Longitude , destinationCity.Latitude , destinationCity.Longitude );// Distance between those two city

					Edges edge = new Edges(sourceCity, destinationCity, distance);
					System.out.println(edge.Source.cityName+" , "+ edge.destenation.cityName+ " , "+edge.weight);
					
					edges.add(edge); // Add all edges to the list
					
					
					
					
				} 
			}
				
				
			}//end While 
			
			reader.close(); // Close the BufferedReader

			CityMap g = new CityMap();

   	 
   	 for (City cityy : city) // Add all buildings to the graph
			g.addCity(cityy);

		for (Edges edge : edges) // Add all edges to the graph
			g.addEdges(edge.Source, edge.destenation, edge.weight);

		return g; // Return graph with all buildings and edges

	}
	
	
	

	
}
