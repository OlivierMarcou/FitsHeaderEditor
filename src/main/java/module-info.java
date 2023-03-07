module net.arkaine.tifheadereditor {
    requires javafx.controls;
    requires javafx.fxml;

    opens net.arkaine.tifheadereditor to javafx.fxml;
    exports net.arkaine.tifheadereditor;
}