module com.uady.sistemavotaciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.uady.sistemavotaciones to javafx.fxml;
    exports com.uady.sistemavotaciones;
    exports com.uady.sistemavotaciones.controller;
    opens com.uady.sistemavotaciones.controller to javafx.fxml;
    exports com.uady.sistemavotaciones.util;
    opens com.uady.sistemavotaciones.util to javafx.fxml;
}