package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.CarteiraDAO;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.ListarAtivosCarteira;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.rendafixa.application.main.Main.*;

public class CarteiraManagementUIController {
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

    private void loadDataAndShow() {
        Optional<Carteira> carteira = consultarCarteira.findOne(1);
        List<Ativo> ativos = carteira.get().getAtivos();
        tableData.clear();
        tableData.addAll(ativos);
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

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    public void buyAtivo(ActionEvent actionEvent) throws IOException {
        Ativo selectedItem = tableView.getSelectionModel().getSelectedItem();
        List<Carteira> carteira = consultarCarteira.findAll();
        LocalDate data_compra = LocalDate.now();
        comprarAtivo.comprarAtivo(carteira.get(0), selectedItem, 100, data_compra);
        removerAtivoCarteira.removerAtivoCarteira(carteira.get(0), selectedItem);
        WindowLoader.setRoot("MainUI");
        showAlert("Sucesso!", "Ativo comprado com sucesso!", Alert.AlertType.CONFIRMATION);
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void removeAtivo(ActionEvent actionEvent) throws IOException {
        Ativo selectedItem = tableView.getSelectionModel().getSelectedItem();
        List<Carteira> carteira = consultarCarteira.findAll();
        removerAtivoCarteira.removerAtivoCarteira(carteira.get(0), selectedItem);
        WindowLoader.setRoot("MainUI");
    }

    public void moveToDashboard(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("Dashboard");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
