package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.IndexadoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteIndexadorDAO implements IndexadoresDAO {
    @Override
    public Optional<Indexador> buscaPorNome(String nome_indexador) {
        String sql = "SELECT * FROM indexador WHERE nome LIKE ?";
        Indexador indexador = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,"%"+nome_indexador.toLowerCase()+"%");
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                indexador = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(indexador);
    }

    @Override
    public Indexador buscar(int id) {

        String sql = "SELECT * FROM indexador WHERE id=?";
        Indexador indexador = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                indexador = new Indexador(rs.getInt("id"),
                        SiglaIndexador.toEnum(rs.getString("sigla")),
                        rs.getString("nome"),
                        rs.getDouble("valor"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return indexador;
    }

    @Override
    public Integer create(Indexador indexador) {
        String sql = "INSERT INTO indexador(sigla,nome,valor) VALUES (?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,indexador.getId());
            stmt.setString(2,indexador.getNome());
            stmt.setDouble(3,indexador.getValor());
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
    public Optional<Indexador> findOne(Integer key) {
        String sql = "SELECT * FROM indexador WHERE id = ?";
        Indexador indexador = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                indexador = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(indexador);
    }

    @Override
    public List<Indexador> findAll() {

        String sql = "SELECT * FROM indexador";
        List<Indexador> indexadores = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Indexador indexador = resultSetToEntity(resultSet);
                indexadores.add(indexador);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return indexadores;
    }

    @Override
    public boolean update(Indexador indexador) {
        String sql = "UPDATE indexador SET sigla=?,nome=?,valor=? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,indexador.getSigla().toString());
            stmt.setString(2,indexador.getNome());
            stmt.setDouble(3,indexador.getValor());
            stmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM indexador WHERE id = ?";

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
    public boolean delete(Indexador indexador) {
        if(indexador== null || indexador.getId() == null)
            throw new IllegalArgumentException("Indexador e id indexador não pode ser nulo!");
        return deleteByKey(indexador.getId());
    }

    private Indexador resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        return new Indexador(resultSet.getInt("id"), SiglaIndexador.toEnum(resultSet.getString("sigla")),
                resultSet.getString("nome"),
                resultSet.getDouble("valor"));
    }
}
