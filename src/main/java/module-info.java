module br.edu.ifsp.rendafixa {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsp.rendafixa to javafx.fxml;
    exports br.edu.ifsp.rendafixa;
}