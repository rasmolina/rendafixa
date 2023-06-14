package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteTransacaoDAO implements TransacaoDAO{
    @Override
    public Optional<Transacao> buscaPorTipo(TipoTransacao tipo) {

        String sql = "SELECT * FROM indexador WHERE tipo = ?";
        Transacao transacao = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, tipo.toString());
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                transacao = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(transacao);
    }

    @Override
    public Integer create(Transacao transacao) {
        String sql = "INSERT INTO transacao(dataTransacao,dataCompra,ativo,valorTransacao,tipoTransacao) VALUES(?,?,?,?,?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String data_compra = transacao.getDataCompra().format(formatter);
            String data_transacao = transacao.getDataTransacao().format(formatter);

            stmt.setString(1,data_transacao);
            stmt.setString(2,data_compra);
            stmt.setInt(3,transacao.getAtivo().getId());
            stmt.setDouble(4,transacao.getValorTransacao());
            stmt.setString(5,transacao.getTipo().toString());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            int id_gerada = resultSet.getInt(1);
            return id_gerada;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Transacao> findOne(Integer key) {
        String sql = "SELECT * FROM transacao WHERE id=?";

        Transacao transacao = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                transacao = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(transacao);
    }

    @Override
    public List<Transacao> findAll() {
        String sql = "SELECT * FROM transacao";
        List<Transacao> transacoes = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Transacao transacao = resultSetToEntity(resultSet);
                transacoes.add(transacao);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return transacoes;
    }

    @Override
    public boolean update(Transacao transacao) {
        String sql = "UPDATE transacao dataTransacao=?,dataVenda=?,dataCompra=?,ativo=?,valorTransacao=?,tipoTransacao=?) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,transacao.getDataTransacao().toString());
            stmt.setString(2,transacao.getDataVenda().toString());
            stmt.setString(3,transacao.getDataCompra().toString());
            stmt.setInt(4,transacao.getAtivo().getId());
            stmt.setDouble(5,transacao.getValorTransacao());
            stmt.setString(6,transacao.getTipo().toString());
            stmt.execute();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {

        String sql = "DELETE FROM transacao WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Transacao transacao) {
        if(transacao== null ||transacao.getId() == null)
            throw new IllegalArgumentException("Transação e id transação não pode ser nulo!");
        return deleteByKey(transacao.getId());
    }


    private Transacao resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        Ativo ativo = new SqliteAtivoDAO().buscar(resultSet.getInt("ativo"));
        if(resultSet.getDate("data_compra").toLocalDate() != null)
        {
            return new Transacao(resultSet.getDate("dataTransacao").toLocalDate(),
                    resultSet.getDate("data_compra").toLocalDate(),ativo,resultSet.getDouble("valorTransacao"),
                    TipoTransacao.toEnum(resultSet.getString("tipoTransacao"))
                    );
        }
        else
        {
            return new Transacao(resultSet.getDate("dataTransacao").toLocalDate(),resultSet.getDate("data_compra").toLocalDate(),
                    ativo,resultSet.getDouble("valorTransacao"),TipoTransacao.toEnum(resultSet.getString("tipoTransacao")));
        }

    }
}
