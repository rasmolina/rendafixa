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
            statement.addBatch(createCarteira());
            statement.addBatch(createEmissoraTable());
            statement.addBatch(createIndexadorTable());
            statement.addBatch(createItemAtivoTable());
            statement.addBatch(createPortadoraTable());
            statement.addBatch(transacaoTable());
            statement.addBatch(createTableComprasAtivo());
            statement.addBatch(ativos_carteira());
            statement.executeBatch();

            System.out.println("Banco de dados criado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAtivoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ativo (\n");
        builder.append("id integer primary key,");
        builder.append("nome text,");
        builder.append("liquidez_diaria real,");
        builder.append("data_vencimento text,");
        builder.append("categoria_ativo text,");
        builder.append("emissora integer,");
        builder.append("portadora integer,");
        builder.append("indexador integer,");
        builder.append("categoria_rentabilidade integer,");
        builder.append("porcentagem_indexador real,");
        builder.append("rentabilidade real,");
        builder.append("FOREIGN KEY(categoria_ativo) REFERENCES categoria_ativos(id),");
        builder.append("FOREIGN KEY(emissora) REFERENCES emissora(id),");
        builder.append("FOREIGN KEY(portadora) REFERENCES portadora(id),");
        builder.append("FOREIGN KEY(indexador) REFERENCES indexador(id),");
        builder.append("FOREIGN KEY(categoria_rentabilidade) REFERENCES categoria_rentabilidade(id)");
        builder.append(");");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCarteira()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE carteira(\n");
        builder.append("id integer primary key,");
        builder.append("valor_disponivel real");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createEmissoraTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE emissora(\n");
        builder.append("id integer primary key,");
        builder.append("nome text,");
        builder.append("descricao text,");
        builder.append("sigla text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createIndexadorTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE indexador(\n");
        builder.append("id integer primary key,");
        builder.append("sigla integer,");
        builder.append("nome text,");
        builder.append("valor real,");
        builder.append("FOREIGN KEY(sigla) REFERENCES sigla_indexador(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createItemAtivoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE item_ativo(\n");
        builder.append("id integer primary key,");
        builder.append("id_ativo integer,");
        builder.append("data_compra text,");
        builder.append("valor_compra real,");
        builder.append("FOREIGN KEY(id_ativo) REFERENCES ativos_carteira(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createTableComprasAtivo()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE compras_ativo(\n");
        builder.append("id integer primary key,");
        builder.append("id_ativo integer,");
        builder.append("id_item_ativo integer,");
        builder.append("FOREIGN KEY(id_ativo) REFERENCES ativo(id),");
        builder.append("FOREIGN KEY(id_item_ativo) REFERENCES item_ativo(id))");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPortadoraTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE portadora(\n");
        builder.append("id integer primary key,");
        builder.append("nome text,");
        builder.append("descricao text,");
        builder.append("sigla text");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String transacaoTable()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE transacao(\n");
        builder.append("id integer primary key,");
        builder.append("dataTransacao text,");
        builder.append("dataVenda text,");
        builder.append("dataCompra text,");
        builder.append("ativo integer,");
        builder.append("valorTransacao real,");
        builder.append("tipoTransacao integer,");
        builder.append("FOREIGN KEY(ativo) REFERENCES ativo(id),");
        builder.append("FOREIGN KEY(tipoTransacao) REFERENCES tipo_transacao(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }


    private String ativos_carteira()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ativos_carteira(\n");
        builder.append("id integer primary key,");
        builder.append("id_carteira integer,");
        builder.append("id_ativo integer,");
        builder.append("FOREIGN KEY(id_carteira) REFERENCES carteira(id),");
        builder.append("FOREIGN KEY(id_ativo) REFERENCES ativo(id)");
        builder.append(");");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
