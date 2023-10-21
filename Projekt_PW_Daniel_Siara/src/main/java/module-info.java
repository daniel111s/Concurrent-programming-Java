module com.example.obslugakaspw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.obslugakaspw to javafx.fxml;
    exports com.example.obslugakaspw;
}