package edu.orangecoastcollege.cs272.ic13.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import edu.orangecoastcollege.cs272.ic13.model.VideoGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

public class VideoGamesListScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ListView<VideoGame> allVideoGamesLV;
	@FXML
	private ComboBox<String> publishersCB;
	@FXML
	private ComboBox<String> platformsCB;
	@FXML
	private Slider yearSlider;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		publishersCB.setItems(controller.getDistinctPublishers());
		platformsCB.setItems(controller.getDistinctPlatforms());
		allVideoGamesLV.setItems(controller.getAllVideoGames());
		
		publishersCB.setOnAction(e -> filter());
		platformsCB.setOnAction(e -> filter());
		yearSlider.setOnMouseDragged(e -> filter());
		yearSlider.setOnMouseClicked(e -> filter());
	}
	
	private void filter() {
		String publisher = publishersCB.getSelectionModel().getSelectedItem();
		String platform = platformsCB.getSelectionModel().getSelectedItem();
		double minYear = yearSlider.getValue();
		
		allVideoGamesLV.setItems(controller.filter(vg -> (publisher == null || vg.getPublisher().equalsIgnoreCase(publisher))
				&& (platform == null || vg.getPlatform().equalsIgnoreCase(platform)) && vg.getYear() >= minYear));
	}

	@FXML
	public void addGameToInventory(ActionEvent event)
	{
		VideoGame selectedGame = allVideoGamesLV.getSelectionModel().getSelectedItem();
		controller.addGameToUsersInventory(selectedGame.getId());
	}
	
	@FXML
	public void viewInventory(ActionEvent event)
	{
		ViewNavigator.loadScene("Inventory", ViewNavigator.VIEW_INVENTORY_SCENE);
	}
}