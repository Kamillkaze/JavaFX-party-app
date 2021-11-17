module com.manhpd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.manhpd to javafx.fxml;
    exports com.manhpd;
}