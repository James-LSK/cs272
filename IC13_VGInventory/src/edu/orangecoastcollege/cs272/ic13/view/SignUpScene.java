package edu.orangecoastcollege.cs272.ic13.view;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import edu.orangecoastcollege.cs272.ic13.controller.Controller;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;

public class SignUpScene {
	
    private static Controller controller = Controller.getInstance();

	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label signUpErrorLabel;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField emailAddressTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;

	// Event Listener on Button.onAction
    @FXML
    public boolean signUp(ActionEvent event) {
        String name = nameTF.getText();
        String email = emailAddressTF.getText();
        String password = passwordTF.getText();

        nameErrorLabel.setVisible(name.isEmpty());
        emailErrorLabel.setVisible(email.isEmpty());
        passwordErrorLabel.setVisible(password.isEmpty());

        if (nameErrorLabel.isVisible()
                || emailErrorLabel.isVisible()
                || passwordErrorLabel.isVisible())
            return false;

        String result = controller.signUpUser(name, email, password);

        if (result.equalsIgnoreCase("SUCCESS")) {
            signUpErrorLabel.setVisible(false);
            ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
            return true;
        }
        signUpErrorLabel.setText(result);
        signUpErrorLabel.setVisible(true);

        return false;
    }

	// Event Listener on Label.onMouseClicked
	@FXML
	public void loadSignIn(MouseEvent event) {
	    ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
	}
}