module br.edu.ifsp.rendafixa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.rendafixa.application.view to javafx.fxml;
    opens br.edu.ifsp.rendafixa.application.controller to javafx.fxml;
    opens br.edu.ifsp.rendafixa.domain.entities.emissora to javafx.base;
    opens br.edu.ifsp.rendafixa.domain.entities.portadora to javafx.base;
    opens br.edu.ifsp.rendafixa.domain.entities.indexadores to javafx.base;
    opens br.edu.ifsp.rendafixa.domain.entities.ativos to javafx.base;


    exports br.edu.ifsp.rendafixa.application.view;
    exports br.edu.ifsp.rendafixa.application.controller;



}