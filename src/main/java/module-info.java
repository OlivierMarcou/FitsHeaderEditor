module net.arkaine.fhe {
    requires javafx.controls;
    requires javafx.fxml;
    requires nom.tam.fits;

    requires org.controlsfx.controls;

    opens net.arkaine.fhe to javafx.fxml;
    exports net.arkaine.fhe;
}