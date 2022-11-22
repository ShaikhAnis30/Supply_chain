module com.example.supplychain19nov {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychain19nov to javafx.fxml;
    exports com.example.supplychain19nov;
}