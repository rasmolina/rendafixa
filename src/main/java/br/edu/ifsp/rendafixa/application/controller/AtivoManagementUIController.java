package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.consultarAtivo;
import static br.edu.ifsp.rendafixa.application.main.Main.removerAtivo;


public class AtivoManagementUIController {

    @FXML
    private TableView<Ativo> tableView;
    @FXML
    private TableColumn<Ativo, String> cNome;
    @FXML
    private TableColumn<Ativo, String> cCategoria;
    @FXML
    private TableColumn<Ativo, Emissora> cEmissora;
    @FXML
    private TableColumn<Ativo, Portadora> cPortadora;
    @FXML
    private TableColumn<Ativo, String> cCategoriaRentabilidade;
    @FXML
    private TableColumn<Ativo, Boolean> cLiquidezDiaria;
    @FXML
    private TableColumn<Ativo, Date> cDataVencimento;
    @FXML
    private TableColumn<Ativo,Double> cPorcentagemIndexador;
    @FXML
    private TableColumn<Ativo, Double> cRentabilidade;

    private ObservableList<Ativo> tableData;

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
        cCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaAtivo"));
        cEmissora.setCellValueFactory(new PropertyValueFactory<>("emissora"));
        cPortadora.setCellValueFactory(new PropertyValueFactory<>("portadora"));
        cCategoriaRentabilidade.setCellValueFactory(new PropertyValueFactory<>("categoriaRentabilidade"));
        cLiquidezDiaria.setCellValueFactory(new PropertyValueFactory<>(""));
        cDataVencimento.setCellValueFactory(cellData -> {
            LocalDate localDate = cellData.getValue().getDataVencimento();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return new SimpleObjectProperty<>(date);
        });
        cPorcentagemIndexador.setCellValueFactory(new PropertyValueFactory<>("porcentagemSobreIndexador"));
        cRentabilidade.setCellValueFactory(new PropertyValueFactory<>("rentabilidade"));

    }

    private void loadDataAndShow() {
        List<Ativo> ativos = consultarAtivo.findAll();
        tableData.clear();
        tableData.addAll(ativos);

    }

    public void createAtivo(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AtivoUI");
    }

    public void editAtivo(ActionEvent actionEvent) throws IOException {
        showAtivoInMode(UIMode.UPDATE);
    }

    public void deleteAtivo(ActionEvent actionEvent) {
        Ativo selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            removerAtivo.remove(selectedItem);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void visualizeAtivo(ActionEvent actionEvent) throws IOException {
        showAtivoInMode(UIMode.VIEW);
    }
    private void showAtivoInMode(UIMode mode) throws IOException {
        Ativo selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            WindowLoader.setRoot("AtivoUI");
            AtivoUIController controller = (AtivoUIController) WindowLoader.getController();
            controller.setAtivo(selectedItem, mode);
        }
    }



}
