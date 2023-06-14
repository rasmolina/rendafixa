package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.consultarEmissora;
import static br.edu.ifsp.rendafixa.application.main.Main.removerEntidadeEmissora;


public class EmissoraManagementUIController {
    @FXML
    private TableView<Emissora> tableView;
    @FXML
    private TableColumn<Emissora, String> cNome;
    @FXML
    private TableColumn<Emissora, String> cDescricao;
    @FXML
    private TableColumn<Emissora, String> cSigla;

    private ObservableList<Emissora> tableData;

    @FXML
    private void initialize(){
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        cSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));

    }

    private void loadDataAndShow() {
        List<Emissora> emissoras = consultarEmissora.findAll();
        tableData.clear();
        tableData.addAll(emissoras);
    }

    public void createEmissora(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("EmissoraUI");
    }


    public void editEmissora(ActionEvent actionEvent) throws IOException {
        showEmissoraInMode(UIMode.UPDATE);
    }

    public void deleteEmissora(ActionEvent actionEvent) {
        Emissora selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            removerEntidadeEmissora.remove(selectedItem);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void visualizeEmissora(ActionEvent actionEvent) throws IOException {
        showEmissoraInMode(UIMode.VIEW);
    }

    private void showEmissoraInMode(UIMode mode) throws IOException {
        Emissora selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            WindowLoader.setRoot("EmissoraUI");
            EmissoraUIController controller = (EmissoraUIController) WindowLoader.getController();
            controller.setEmissora(selectedItem, mode);
        }
    }
}
