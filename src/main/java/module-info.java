module com.example.kp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.kp to javafx.fxml;
    exports com.example.kp;
    exports com.example.kp.Database;
    opens com.example.kp.Database to javafx.fxml;
}