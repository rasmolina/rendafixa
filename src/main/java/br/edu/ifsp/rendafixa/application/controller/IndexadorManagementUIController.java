package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.consultarIndexador;
import static br.edu.ifsp.rendafixa.application.main.Main.removerIndexador;


public class IndexadorManagementUIController {

    @FXML
    private TableView<Indexador> tableView;
    @FXML
    private TableColumn<Indexador, String> cNome;
    @FXML
    private TableColumn<Indexador, Double> cValor;
    @FXML
    private TableColumn<Indexador, String> cSigla;

    private ObservableList<Indexador> tableData;

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
        cValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        cSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));

    }

    private void loadDataAndShow() {
        List<Indexador> indexadores = consultarIndexador.findAll();
        tableData.clear();
        tableData.addAll(indexadores);
    }

    public void createIndexador(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("IndexadorUI");
    }

    public void editIndexador(ActionEvent actionEvent) throws IOException {
        showIndexadorInMode(UIMode.UPDATE);
    }

    public void deleteIndexador(ActionEvent actionEvent) {
        Indexador selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            removerIndexador.remove(selectedItem);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void visualizeIndexador(ActionEvent actionEvent) throws IOException {
        showIndexadorInMode(UIMode.VIEW);
    }

    private void showIndexadorInMode(UIMode mode) throws IOException {
        Indexador selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            WindowLoader.setRoot("IndexadorUI");
            IndexadorUIController controller = (IndexadorUIController) WindowLoader.getController();
            controller.setIndexador(selectedItem, mode);
        }
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
