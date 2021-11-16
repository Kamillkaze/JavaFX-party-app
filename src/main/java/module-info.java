module com.manhpd {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.manhpd to javafx.fxml;
    exports com.manhpd;
}