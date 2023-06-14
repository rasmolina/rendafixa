package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.rendafixa.application.main.Main.atualizarIndexador;
import static br.edu.ifsp.rendafixa.application.main.Main.cadastrarIndexador;


public class IndexadorUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValor;
    @FXML
    private ComboBox<SiglaIndexador> cbSiglas;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;



    @FXML
    private void initialize(){
        cbSiglas.getItems().setAll(SiglaIndexador.values());
    }

    private Indexador indexador;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (indexador.getId()== null)
            cadastrarIndexador.insert(indexador);
        else
            atualizarIndexador.update(indexador);
        WindowLoader.setRoot("IndexadorManagementUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("IndexadorManagementUI");
    }

    public void setIndexador(Indexador indexador, UIMode mode) {
        if (indexador == null)
            throw new IllegalArgumentException("Indexador não pode ser nulo");

        this.indexador = indexador;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void getEntityToView(){
        if (indexador == null){
            indexador = new Indexador();
        }
        indexador.setNome(txtNome.getText());
        indexador.setValor(Double.valueOf(txtValor.getText()));
        indexador.setSigla(cbSiglas.getValue());
    }

    private void setEntityIntoView(){
        txtNome.setText(indexador.getNome());
        txtValor.setText(String.valueOf(indexador.getValor()));
        cbSiglas.setValue(indexador.getSigla());
    }

    private void configureViewMode() {
        btnCancelar.setLayoutX(btnSalvar.getLayoutX());
        btnCancelar.setLayoutY(btnSalvar.getLayoutY());
        btnCancelar.setText("Fechar");

        btnSalvar.setVisible(false);

        txtNome.setDisable(true);
        txtValor.setDisable(true);
        cbSiglas.setDisable(true);

    }
}
