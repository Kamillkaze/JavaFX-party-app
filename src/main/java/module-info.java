module com.manhpd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens sample to javafx.fxml;
    exports sample;
}