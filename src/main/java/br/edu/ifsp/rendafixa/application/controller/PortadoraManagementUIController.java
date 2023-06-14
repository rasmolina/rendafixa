package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PortadoraManagementUIController {

    @FXML
    private TableView<Portadora> tableView;
    @FXML
    private TableColumn<Portadora, String> cNome;
    @FXML
    private TableColumn<Portadora, String> cDescricao;
    @FXML
    private TableColumn<Portadora, String> cSigla;

    private ObservableList<Portadora> tableData;

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
        
    }


    public void createPortadora(ActionEvent actionEvent) {
    }

    public void editPortadora(ActionEvent actionEvent) {
    }

    public void deletePortadora(ActionEvent actionEvent) {
    }

    public void backToPreviousScene(ActionEvent actionEvent) {
    }

    public void visualizePortadora(ActionEvent actionEvent) {
    }
}
