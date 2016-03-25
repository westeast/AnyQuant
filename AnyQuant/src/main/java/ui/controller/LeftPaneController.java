package ui.controller;
/**
* 左边栏控制器
* @author Qiang
* @date Mar 22, 2016
*/

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller of the leftPane
 *
 * @author Qiang
 *
 */
public class LeftPaneController{
	@FXML
	private Button stockButton;
	@FXML
	private Button benchButton;
	@FXML
	private Button kButton;

	private RightPaneController rightPaneController;
	/**
	 * the leftPane
	 */
	private static Pane pane;
	private static LeftPaneController instance;

	/**
	 * use SingleTon pattern
	 *
	 */
	public static LeftPaneController getLeftPaneController() {
		if (instance == null) {
			instance = new LeftPaneController();
		}
		return instance;
	}

	public LeftPaneController() {
		if (instance == null) {
			instance = this;
		}
		rightPaneController = RightPaneController.getRightPaneController();
	}

	@FXML
	private void handleStockButton(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			stockButton.getStyleClass().clear();
			stockButton.getStyleClass().add("stockButton-clicked");
			rightPaneController.showStockListPane();
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			stockButton.getStyleClass().clear();
			stockButton.getStyleClass().add("stockButton-entered");
		} else {
			stockButton.getStyleClass().clear();
			stockButton.getStyleClass().add("stockButton");
		}

	}

	@FXML
	private void handleBenchButton(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			benchButton.getStyleClass().clear();
			benchButton.getStyleClass().add("benchButton-clicked");
			rightPaneController.showBenchMarkListPane();
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			benchButton.getStyleClass().clear();
			benchButton.getStyleClass().add("benchButton-entered");
		} else {
			benchButton.getStyleClass().clear();
			benchButton.getStyleClass().add("benchButton");
		}

	}

	@FXML
	private void handleKButton(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			kButton.getStyleClass().clear();
			kButton.getStyleClass().add("kButton-clicked");
			rightPaneController.showCandleStickPane();
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			kButton.getStyleClass().clear();
			kButton.getStyleClass().add("kButton-entered");
		} else {
			kButton.getStyleClass().clear();
			kButton.getStyleClass().add("kButton");
		}

	}

	/*
	 * Set it's pane,this is because it's constructor is called by the system So
	 * we cannot put it in the constructor
	 */
	static void setPane(Pane pane) {
		if (LeftPaneController.pane == null) {
			LeftPaneController.pane = pane;
		}
	}

}