package br.edu.ifsp.rendafixa.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void builderDataBaseIfMissing(){
        if(isDatabaseMissing())
        {
            System.out.println("Database is missing.Building database: \n");
            buildeTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("banco_renda_fixa.db"));
    }

    private void buildeTables()
    {
        try (Statement statement = ConnectionFactory.createStatement()){
            statement.addBatch(createAtivoTable());
            statement.addBatch(createAtivoCategoria());
            statement.addBatch(createRentabilidadeTable());
            statement.addBatch(createAtivoTable());
            statement.addBatch(createAtivoTable());
            statement.addBatch(createCarteiraAtivo());
            statement.addBatch(createEmissoraTable());
            statement.addBatch(createIndexadorTable());
            statement.addBatch(createSiglaIndexadorTable());
            statement.addBatch(createItemAtivoTable());
            statement.addBatch(createPortadoraTable());
            statement.addBatch(tipoTransacaoTable());
            statement.addBatch(transacaoTable());
            statement.executeBatch();

            System.out.println("Banco de dados criado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAtivoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ativos (\n");
        builder.append("id integer primary key");
        builder.append("liquidez_diaria real");
        builder.append("data_vencimento text");
        builder.append("categoria_ativo integer");
        builder.append("emissora integer");
        builder.append("portadora integer");
        builder.append("indexador integer");
        builder.append("categoria_rentabilidade integer");
        builder.append("porcentagem_indexador real");
        builder.append("rentabilidade real");
        builder.append("id_carteira integer");
        builder.append("FOREIGN KEY(carteira) REFERENCES carteira(id)");
        builder.append("FOREIGN KEY(categoria_ativo) REFERENCES categoria_ativos(id)");
        builder.append("FOREIGN KEY(emissora) REFERENCES emissora(id)");
        builder.append("FOREIGN KEY(portadora) REFERENCES portadora(id)");
        builder.append("FOREIGN KEY(indexador) REFERENCES indexador(id)");
        builder.append("FOREIGN KEY(categoria_rentabilidade) REFERENCES categoria_rentabilidade(id)");
        builder.append(");");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createAtivoCategoria()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE categoria_ativos(\n");
        builder.append("id integer primary key");
        builder.append("categoria_ativo text");
        builder.append("item_ativo integer");
        builder.append("FOREIGN KEY(item_ativo) REFERENCES item_ativo(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createRentabilidadeTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE categoria_rentabilidade(\n");
        builder.append("id integer primary key");
        builder.append("categoria_rentabilidade text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }


    private String createCarteiraAtivo()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE carteira(\n");
        builder.append("id integer primary key");
        builder.append("valor_disponivel real");
        builder.append(");");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createEmissoraTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE emissora(\n");
        builder.append("id integer primary key");
        builder.append("nome text");
        builder.append("descricao text");
        builder.append("sigla text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createIndexadorTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE indexador(\n");
        builder.append("id integer primary key");
        builder.append("sigla integer");
        builder.append("nome text");
        builder.append("valor real");
        builder.append("FOREIGN KEY(sigla) REFERENCES sigla_indexador(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createSiglaIndexadorTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE sigla_indexador(\n");
        builder.append("id integer primary key");
        builder.append("sigla text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createItemAtivoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE item_ativo(\n");
        builder.append("id integer primary key");
        builder.append("id_ativo integer");
        builder.append("data_compra text");
        builder.append("valor_compra real");
        builder.append("FOREIGN KEY(ativo) REFERENCES ativo(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPortadoraTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE portadora(\n");
        builder.append("id integer primary key");
        builder.append("nome text");
        builder.append("descricao text");
        builder.append("sigla text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String tipoTransacaoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE tipo_transacao(\n");
        builder.append("id integer primary key");
        builder.append("tipo text not null");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String transacaoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE transacao(\n");
        builder.append("id integer primary key");
        builder.append("dataTransacao text");
        builder.append("dataVenda text");
        builder.append("dataCompra text");
        builder.append("ativo integer");
        builder.append("valorTransacao real");
        builder.append("tipoTransacao integer");
        builder.append("FOREIGN KEY(ativo) REFERENCES ativo(id)");
        builder.append("FOREIGN KEY(tipoTransacao) REFERENCES tipo_transacao(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }



}
