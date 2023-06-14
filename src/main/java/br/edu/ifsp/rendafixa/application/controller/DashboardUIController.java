package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.consultarEmissora;
import static br.edu.ifsp.rendafixa.application.main.Main.consultarItemAtivo;

public class DashboardUIController {
    @FXML
    public ComboBox<Ativo> cdAtivos;
    private ObservableList<Ativo> obsAtivos;

    private Ativo ativo;


    @FXML
    private void initialize(){
        carregarEntidades();
    }

    private void carregarEntidades() {
        List<ItemAtivo> itemsativos = consultarItemAtivo.findAll();
        List<Ativo> ativos = new ArrayList<>();
        for (ItemAtivo itemsativo: itemsativos) {
            ativos.add(itemsativo.getAtivo());
        }
        obsAtivos = FXCollections.observableArrayList(ativos);
        cdAtivos.setItems(obsAtivos);
    }

    public void voltarCarteira(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CarteiraManagementUI");
    }

    public void dashComp(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("DashboardComp");
    }
}
