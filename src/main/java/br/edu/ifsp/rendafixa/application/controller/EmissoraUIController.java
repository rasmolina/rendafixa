package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.rendafixa.application.main.Main.atualizarEntidadeEmissora;
import static br.edu.ifsp.rendafixa.application.main.Main.cadastrarEntidadeEmissora;


public class EmissoraUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtSigla;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Emissora emissora;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (emissora.getId()== null)
            cadastrarEntidadeEmissora.insert(emissora);
        else
            atualizarEntidadeEmissora.update(emissora);
        WindowLoader.setRoot("EmissoraManagementUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("EmissoraManagementUI");
    }

    public void setEmissora(Emissora emissora, UIMode mode) {
        if (emissora == null)
            throw new IllegalArgumentException("Emissora não pode ser nula");

        this.emissora = emissora;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void getEntityToView(){
        if (emissora == null){
            emissora = new Emissora();
        }
        emissora.setNome(txtNome.getText());
        emissora.setDescricao(txtDescricao.getText());
        emissora.setSigla(txtSigla.getText());
    }

    private void setEntityIntoView(){
        txtNome.setText(emissora.getNome());
        txtDescricao.setText(emissora.getDescricao());
        txtSigla.setText(emissora.getSigla());
    }

    private void configureViewMode() {
        btnCancelar.setLayoutX(btnSalvar.getLayoutX());
        btnCancelar.setLayoutY(btnSalvar.getLayoutY());
        btnCancelar.setText("Fechar");

        btnSalvar.setVisible(false);

        txtNome.setDisable(true);
        txtDescricao.setDisable(true);
        txtSigla.setDisable(true);

    }
}
