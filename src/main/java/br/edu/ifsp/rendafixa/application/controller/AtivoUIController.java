package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.edu.ifsp.rendafixa.application.main.Main.*;


public class AtivoUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<CategoriaAtivo> cbCategoriaAtivo;
    private ObservableList<CategoriaAtivo> obsCategoriaAtivo;
    @FXML
    private ComboBox<Emissora> cbEmissora;
    private ObservableList<Emissora> obsEmissoras;
    @FXML
    private ComboBox<Portadora> cbPortadora;
    private ObservableList<Portadora> obsPortadora;
    @FXML
    private ComboBox<CategoriaRentabilidade> cbCategoriaRentabilidade;
    private ObservableList<CategoriaRentabilidade> obsCategoriaRentabilidade;

    @FXML
    private CheckBox ckLiquidez;
    @FXML
    private DatePicker dpVencimento;
    @FXML
    private ComboBox<Indexador> cbIndexador;
    private ObservableList<Indexador> obsIndexador;
    @FXML
    private TextField txtPorcentagemSobreIndexador;
    @FXML
    private TextField txtRentabilidade;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Ativo ativo;

    @FXML
    private void initialize(){
        carregarEntidades();
    }

    public void carregarEntidades() {
        List<Emissora> emissoras = consultarEmissora.findAll();
        List<Portadora> portadoras = consultarPortadora.findAll();
        List<Indexador> indexadores = consultarIndexador.findAll();
        List<CategoriaAtivo> categorias = List.of(CategoriaAtivo.values());
        List<CategoriaRentabilidade> categoriasrent = List.of(CategoriaRentabilidade.values());

        obsEmissoras = FXCollections.observableArrayList(emissoras);
        obsPortadora = FXCollections.observableArrayList(portadoras);
        obsIndexador = FXCollections.observableArrayList(indexadores);
        obsCategoriaAtivo = FXCollections.observableArrayList(categorias);
        obsCategoriaRentabilidade = FXCollections.observableArrayList(categoriasrent);
        cbEmissora.setItems(obsEmissoras);
        cbPortadora.setItems(obsPortadora);
        cbIndexador.setItems(obsIndexador);
        cbCategoriaAtivo.setItems(obsCategoriaAtivo);
        cbCategoriaRentabilidade.setItems(obsCategoriaRentabilidade);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (ativo.getId()== null)
            cadastrarAtivo.insert(ativo);
        else
            atualizarAtivo.update(ativo);
        WindowLoader.setRoot("AtivoManagementUI");
    }

    private void getEntityToView() {
        if (ativo == null){
            ativo = new Ativo();
        }
        ativo.setNome(txtNome.getText());
        ativo.setCategoriaAtivo(cbCategoriaAtivo.getSelectionModel().getSelectedItem());
        ativo.setEmissora(cbEmissora.getSelectionModel().getSelectedItem());
        ativo.setPortadora(cbPortadora.getSelectionModel().getSelectedItem());
        ativo.setCategoriaRentabilidade(cbCategoriaRentabilidade.getSelectionModel().getSelectedItem());
        ativo.setLiquidezDiaria(ckLiquidez.isSelected());
        ativo.setDataVencimento(dpVencimento.getValue());
        ativo.setPorcentagemSobreIndexador(Double.valueOf(txtPorcentagemSobreIndexador.getText()));
        ativo.setRentabilidade(Double.valueOf(txtRentabilidade.getText()));
    }

    private void setEntityIntoView(){
        txtNome.setText(ativo.getNome());
        cbCategoriaAtivo.setValue(ativo.getCategoriaAtivo());
        cbEmissora.setValue(ativo.getEmissora());
        cbPortadora.setValue(ativo.getPortadora());
        cbCategoriaRentabilidade.setValue(ativo.getCategoriaRentabilidade());
        ckLiquidez.setSelected(ativo.isLiquidezDiaria());
        dpVencimento.setValue(ativo.getDataVencimento());
        txtPorcentagemSobreIndexador.setText(String.valueOf(ativo.getPorcentagemSobreIndexador()));
        txtRentabilidade.setText(String.valueOf(ativo.getRentabilidade()));
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AtivoManagementUI");
    }

    public void configureAtivoDate(ActionEvent actionEvent) {
        boolean trueIfLiquidez = ckLiquidez.isSelected();
        dpVencimento.setValue(LocalDate.now());
        dpVencimento.setDisable(trueIfLiquidez);
    }

    public void setAtivo(Ativo ativo, UIMode mode) {
        if (ativo == null)
            throw new IllegalArgumentException("Ativo não pode ser nulo");

        this.ativo = ativo;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    public void configureIndexador(ActionEvent actionEvent) {
        cbCategoriaRentabilidade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(CategoriaRentabilidade.PRE_FIXADO)) {
                cbIndexador.setDisable(true);
                txtPorcentagemSobreIndexador.setDisable(true);
            } else {
                cbIndexador.setDisable(false);
                txtPorcentagemSobreIndexador.setDisable(false);
            }
        });
    }

    private void configureViewMode() {
        btnCancelar.setLayoutX(btnSalvar.getLayoutX());
        btnCancelar.setLayoutY(btnSalvar.getLayoutY());
        btnCancelar.setText("Fechar");

        btnSalvar.setVisible(false);

        txtNome.setDisable(true);

    }
}
