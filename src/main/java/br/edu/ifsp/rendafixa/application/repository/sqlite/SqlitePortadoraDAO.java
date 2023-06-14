package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.PortadoraDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePortadoraDAO implements PortadoraDAO {
    @Override

    public Optional<Portadora> buscaPorNomePortadora(String nomePortadora) {

        String sql = "SELECT * FROM portadora WHERE nome LIKE ?";
        Portadora portadora = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,"%"+nomePortadora.toLowerCase()+"%");
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                portadora = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(portadora);
    }


    @Override
    public Portadora buscar(int id) {

        String sql = "SELECT * FROM portadora WHERE id=?";
        Portadora portadora = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                portadora = new Portadora(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("sigla"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return portadora;
    }

    @Override
    public Integer create(Portadora portadora) {

        String sql = "INSERT INTO portadora(nome,descricao,sigla) VALUES (?,?,?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,portadora.getNome());
            stmt.setString(2,portadora.getDescricao());
            stmt.setString(3,portadora.getDescricao());
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
    public Optional<Portadora> findOne(Integer key) {
        String sql = "SELECT * FROM portadora WHERE id = ?";
        Portadora portadora = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setLong(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                portadora = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(portadora);
    }

    @Override
    public List<Portadora> findAll() {

        String sql = "SELECT * FROM portadora";
        List<Portadora> portadoras = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Portadora portadora = resultSetToEntity(resultSet);
                portadoras.add(portadora);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return portadoras;
    }

    @Override
    public boolean update(Portadora portadora) {

        String sql = "UPDATE portadora SET nome=?,descricao=?,sigla=? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,portadora.getNome());
            stmt.setString(2,portadora.getDescricao());
            stmt.setString(3,portadora.getDescricao());
            stmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {

        String sql = "DELETE FROM portadora WHERE id = ?";

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
    public boolean delete(Portadora portadora) {
        if(portadora == null || portadora.getId() == null)
            throw new IllegalArgumentException("Portadora e id portadora não pode ser nulo!");
        return deleteByKey(portadora.getId());
    }


    private Portadora resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        return new Portadora(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("descricao"),
                resultSet.getString("sigla"));
    }
}
