package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.EmissoraDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteEmissoraDAO implements EmissoraDAO {
    @Override
    public Optional<Emissora> buscaPorNomeEmissora(String nomeEmissora) {
        String sql = "SELECT * FROM emissora WHERE nome LIKE ?";
        Emissora emissora = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,"%"+nomeEmissora.toLowerCase()+"%");
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
               emissora = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(emissora);
    }

    @Override
    public Emissora buscar(int id) {

        String sql = "SELECT * FROM emissora WHERE id=?";
        Emissora emissora = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                emissora = new Emissora(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("sigla"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return emissora;
    }

    @Override
    public Integer create(Emissora emissora) {
        String sql = "INSERT INTO emissora(nome,descricao,sigla)VALUES(?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,emissora.getNome());
            stmt.setString(2,emissora.getDescricao());
            stmt.setString(3,emissora.getSigla());
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            int id_gerada = resultSet.getInt(1);
            return id_gerada;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Emissora> findOne(Integer key) {

        String sql = "SELECT * FROM ativo WHERE id = ?";
        Emissora emissora = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                emissora = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(emissora);
    }

    @Override
    public List<Emissora> findAll() {
        String sql = "SELECT * FROM emissora";
        List<Emissora> emissoras = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Emissora emissora = resultSetToEntity(resultSet);
                emissoras.add(emissora);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return emissoras;
    }

    @Override
    public boolean update(Emissora emissora) {
        String sql = "UPDATE emissora nome=?,descricao=?,sigla=? WHERE id=?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,emissora.getNome());
            stmt.setString(2,emissora.getDescricao());
            stmt.setString(3,emissora.getSigla());
            stmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM emissora WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql))
        {
            stmt.setInt(1,key);
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
    public boolean delete(Emissora emissora) {
        if(emissora == null || emissora.getId() == null)
            throw new IllegalArgumentException("Emissora e id emissora não pode ser nulo!");
        return deleteByKey(emissora.getId());
    }

    private Emissora resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        return  new Emissora(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("descricao"),
                resultSet.getString("sigla"));
    }
}
