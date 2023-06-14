package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardCompUIController {
    public void VoltarDash(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("Dashboard");
    }
}
