package edu.orangecoastcollege.cs272.ic13.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class SignInScene {
	
    private static Controller controller = Controller.getInstance();

	@FXML
	private TextField emailAddressTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label signInErrorLabel;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;

	// Event Listener on Button.onAction
	@FXML
	public boolean signIn(ActionEvent event) {
	    // Let's read information from the user
	    String email = emailAddressTF.getText();
	    String password = passwordTF.getText();

	    // Let's check to see if the error labels should be made visible
        emailErrorLabel.setVisible(email.isEmpty());
        passwordErrorLabel.setVisible(password.isEmpty());

        // If either one is visible, return false
        if (emailErrorLabel.isVisible()
                || passwordErrorLabel.isVisible())
            return false;
        String result = controller.signInUser(email, password);

        if (result.equalsIgnoreCase("SUCCESS")) {
            signInErrorLabel.setVisible(false);
            ViewNavigator.loadScene("Video Games List", ViewNavigator.VIDEO_GAME_LIST_SCENE);
            return true;
        }
        signInErrorLabel.setText(result);
        signInErrorLabel.setVisible(true);

        return true;
	}
	// Event Listener on Label.onMouseClicked
	@FXML
	public void loadSignUp(MouseEvent event) {
	    ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
	}
}