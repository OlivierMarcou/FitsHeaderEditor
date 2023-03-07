package net.arkaine.tifheadereditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label lblOpen;

    @FXML
    private Label lblSave;

    @FXML
    protected void btnOpen() {
        lblOpen.setText("Welcome !");
    }

    @FXML
    public void btnSave(ActionEvent actionEvent) {
    }
}