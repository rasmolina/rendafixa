module br.edu.ifsp.rendafixa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.rendafixa to javafx.fxml;
    exports br.edu.ifsp.rendafixa;
}