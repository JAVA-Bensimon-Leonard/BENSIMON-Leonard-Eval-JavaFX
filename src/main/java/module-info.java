module com.example.bensimonleonardevaljavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bensimonleonardevaljavafx to javafx.fxml;
    exports com.example.bensimonleonardevaljavafx;
}