module com.example.azs_fx_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.json;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.apache.httpcomponents.client5.httpclient5.fluent;
    requires com.fasterxml.jackson.databind;

    opens com.example.azs_fx_1 to javafx.fxml;
    exports com.example.azs_fx_1;
    exports com.example.azs_fx_1.dto;
    opens com.example.azs_fx_1.dto to javafx.fxml;
}
