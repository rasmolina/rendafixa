package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ItemAtivoDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteItemAtivoDAO implements ItemAtivoDAO {
    @Override
    public Integer create(ItemAtivo itemAtivo) {
        String sql = "INSERT INTO item_ativo(id_ativo,data_compra,valor_compra) VALUES(?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,itemAtivo.getAtivo().getId());
            stmt.setString(2, itemAtivo.getDataDaCompra().toString());
            stmt.setDouble(3,itemAtivo.getValorDaCompra());
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
    public Optional<ItemAtivo> findOne(Integer key) {
        String sql = "SELECT * FROM item_ativo WHERE id = ?";
        ItemAtivo itemAtivo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                itemAtivo = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(itemAtivo);
    }

    @Override
    public List<ItemAtivo> findAll() {
        String sql = "SELECT * FROM item_ativo";
        List<ItemAtivo> itens_ativos = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                ItemAtivo itemAtivo = resultSetToEntity(resultSet);
                itens_ativos.add(itemAtivo);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return itens_ativos;
    }

    @Override
    public boolean update(ItemAtivo itemAtivo) {
        String sql = "UPDATE item_ativo id_ativo=?,data_compra=?,valor_compra=? WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,itemAtivo.getId());
            stmt.setString(2, itemAtivo.getDataDaCompra().toString());
            stmt.setDouble(3,itemAtivo.getValorDaCompra());
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

        String sql = "DELETE FROM item_ativo WHERE id=?";

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
    public boolean delete(ItemAtivo itemAtivo) {
        if(itemAtivo == null || itemAtivo.getId() == null)
            throw new IllegalArgumentException("Item Ativo e id item ativo não pode ser nulo!");
        return deleteByKey(itemAtivo.getId());
    }

    private ItemAtivo resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        Ativo ativo = new SqliteAtivoDAO().buscar(resultSet.getInt("ativo"));

        return new ItemAtivo(ativo,resultSet.getDate("data_compra").toLocalDate(),resultSet.getDouble("valor_compra"));
    }
}
