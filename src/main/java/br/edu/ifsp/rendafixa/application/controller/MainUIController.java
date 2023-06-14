package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainUIController {

    public void moveToEmissoraManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("EmissoraManagementUI");
    }

    public void moveToAtivoManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AtivoManagementUI");
    }

    public void moveToCarteiraManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CarteiraManagementUI");
    }

    public void moveToPortadoraManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PortadoraManagementUI");
    }

    public void moveToIndexadorManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("IndexadorManagementUI");
    }

    public void moveToTransacoes(ActionEvent actionEvent) {
    }
}
