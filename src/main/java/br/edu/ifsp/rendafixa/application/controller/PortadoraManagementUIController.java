package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.RemoverEntidadePortadora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.consultarPortadora;
import static br.edu.ifsp.rendafixa.application.main.Main.removerEntidadePortadora;

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
        List<Portadora> portadoras = consultarPortadora.findAll();
        tableData.clear();
        tableData.addAll(portadoras);
    }


    public void createPortadora(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PortadoraUI");
    }

    public void editPortadora(ActionEvent actionEvent) throws IOException {
        showPortadoraInMode(UIMode.UPDATE);
    }

    public void deletePortadora(ActionEvent actionEvent) {
        Portadora selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            removerEntidadePortadora.remove(selectedItem);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void visualizePortadora(ActionEvent actionEvent) throws IOException {
        showPortadoraInMode(UIMode.VIEW);
    }

    private void showPortadoraInMode(UIMode mode) throws IOException {
        Portadora selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            WindowLoader.setRoot("PortadoraUI");
            PortadoraUIController controller = (PortadoraUIController) WindowLoader.getController();
            controller.setPortadora(selectedItem, mode);
        }
    }
}
