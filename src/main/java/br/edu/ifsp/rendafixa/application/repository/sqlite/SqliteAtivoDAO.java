package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SqliteAtivoDAO implements AtivoDAO {
    @Override
    public Optional<Ativo> buscaPorCategoria(CategoriaAtivo categoriaAtivo) {
        return Optional.empty();
    }

    @Override
    public Optional<Ativo> buscaPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<Ativo> buscaPorVencimento(LocalDate vencimento) {
        return Optional.empty();
    }

    @Override
    public Integer create(Ativo ativo) {
        String sql = "INSERT INTO ativo(nome, liquidez_diaria, data_vencimento, categoria_ativo, emissora, portadora, indexador, " +
                "categoria_rentabilidade, porcentagem_indexador, rentabilidade)VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, ativo.getNome());
            stmt.setBoolean(2, ativo.isLiquidezDiaria());
            stmt.setObject(3,   ativo.getDataVencimento());
            stmt.setString(4, ativo.getCategoriaAtivo().toString());
            stmt.setInt(5, ativo.getEmissora().getId());
            stmt.setInt(6, ativo.getPortadora().getId());
            stmt.setInt(7, ativo.getIndexador().getId());
            stmt.setDouble(8, ativo.getRentabilidade());
            stmt.setDouble(9, ativo.getPorcentagemSobreIndexador());
            stmt.setDouble(10, ativo.getRentabilidade());
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
    public Optional<Ativo> findOne(Integer key) {
        return Optional.empty();
    }


    private Ativo resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        /*
        resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getBoolean("liquidezDiaria"),
                resultSet.getObject("dataVencimento"),
                CategoriaAtivo.toEnum(resultSet.getString("categoria_ativo")),
                resultSet.getInt("emissora"),
                resultSet.getInt("portadora"),
                resultSet.getInt("indexador"),
                CategoriaRentabilidade.toEnum(resultSet.getString("categoria_rentabilidade")),
                resultSet.getDouble("porcentagem_indexador"),
                resultSet.getDouble("rentabilidade")

         */
        //return new Ativo(resultSet.getInt("id"),resultSet.getString("nome"),resultSet.getBoolean("liquidez_diaria"),);
         return null;
    }



    @Override
    public List<Ativo> findAll() {
        return null;
    }

    @Override
    public boolean update(Ativo type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Ativo type) {
        return false;
    }
}
