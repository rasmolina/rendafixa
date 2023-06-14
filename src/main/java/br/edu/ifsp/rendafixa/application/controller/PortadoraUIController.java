package br.edu.ifsp.rendafixa.application.controller;

import br.edu.ifsp.rendafixa.application.view.WindowLoader;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.rendafixa.application.main.Main.atualizarEntidadePortadora;
import static br.edu.ifsp.rendafixa.application.main.Main.cadastrarEntidadePortadora;

public class PortadoraUIController {

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

    private Portadora portadora;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (portadora.getId()== null)
            cadastrarEntidadePortadora.insert(portadora);
        else
            atualizarEntidadePortadora.update(portadora);
        WindowLoader.setRoot("PortadoraManagementUI");
    }

    private void getEntityToView(){
        if (portadora == null){
            portadora = new Portadora();
        }
        portadora.setNome(txtNome.getText());
        portadora.setDescricao(txtDescricao.getText());
        portadora.setSigla(txtSigla.getText());
    }



    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PortadoraManagementUI");
    }

    public void setPortadora(Portadora portadora, UIMode mode) {
        if (portadora == null)
            throw new IllegalArgumentException("Portadora não pode ser nula");

        this.portadora = portadora;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }



    private void setEntityIntoView(){
        txtNome.setText(portadora.getNome());
        txtDescricao.setText(portadora.getDescricao());
        txtSigla.setText(portadora.getSigla());
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
