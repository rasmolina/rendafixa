package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.application.repository.sqlite.ConnectionFactory;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.ConsultarEmissora;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.ConsultarIndexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteAtivoDAO implements AtivoDAO {
    @Override
    public Optional<Ativo> buscaPorCategoria(CategoriaAtivo categoriaAtivo) {
        String sql = "SELECT * FROM ativo WHERE categoria_ativo = ?";
        Ativo ativo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, categoriaAtivo.name());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                ativo = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ativo);
    }


    @Override
    public Optional<Ativo> buscaPorNome(String nome) {

        String sql = "SELECT * FROM ativo WHERE nome LIKE ?";
        Ativo ativo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1,"%"+nome.toLowerCase()+"%");
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                ativo = new SqliteAtivoDAO().resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(ativo);

    }

    @Override
    public Optional<Ativo> buscaPorVencimento(LocalDate vencimento) {
        String sql = "SELECT * FROM ativo WHERE data_vencimento = ?";
        Ativo ativo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, vencimento.toString());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ativo = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(ativo);
    }


    @Override
    public Ativo buscar(int id) {
        String sql = "SELECT * FROM ativo WHERE id=?";
        Ativo ativo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emissora emissora = new SqliteEmissoraDAO().buscar(rs.getInt("emissora"));
                Portadora portadora = new SqlitePortadoraDAO().buscar(rs.getInt("portadora"));
                Indexador indexador = new SqliteIndexadorDAO().buscar(rs.getInt("indexador"));

                LocalDate dataVencimento = LocalDate.parse(rs.getString("data_vencimento"));

                ativo = new Ativo(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getBoolean("liquidez_diaria"),
                        dataVencimento,
                        CategoriaAtivo.toEnum(rs.getString("categoria_ativo")),
                        emissora,
                        portadora,
                        indexador,
                        CategoriaRentabilidade.toEnum(rs.getString("categoria_rentabilidade")),
                        rs.getDouble("porcentagem_indexador"),
                        rs.getDouble("rentabilidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ativo;
    }


    @Override
    public Integer create(Ativo ativo) {
        String sql = "INSERT INTO ativo(id,nome, liquidez_diaria, data_vencimento, categoria_ativo, emissora, portadora, indexador, " +
                "categoria_rentabilidade, porcentagem_indexador, rentabilidade)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,ativo.getId());
            stmt.setString(2, ativo.getNome());
            stmt.setBoolean(3, ativo.isLiquidezDiaria());
            stmt.setString(4, ativo.getDataVencimento().toString());
            stmt.setString(5, ativo.getCategoriaAtivo().name());
            stmt.setInt(6, ativo.getEmissora().getId());
            stmt.setInt(7, ativo.getPortadora().getId());
            stmt.setInt(8, ativo.getIndexador().getId());
            stmt.setString(9, ativo.getCategoriaRentabilidade().toString());
            stmt.setDouble(10, ativo.getPorcentagemSobreIndexador());
            stmt.setDouble(11, ativo.getRentabilidade());
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
        String sql = "SELECT * FROM ativo WHERE id = ?";
        Ativo ativo = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                ativo = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(ativo);
    }


    public Ativo resultSetToEntity(ResultSet resultSet) throws SQLException {
        Emissora emissora = new SqliteEmissoraDAO().findOne(resultSet.getInt("emissora"))
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado"));
        Portadora portadora = new SqlitePortadoraDAO().findOne(resultSet.getInt("portadora"))
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado"));
        Indexador indexador = new SqliteIndexadorDAO().findOne(resultSet.getInt("indexador"))
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado"));

        String dataVencimentoStr = resultSet.getString("data_vencimento");
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);

        return new Ativo(resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getBoolean("liquidez_diaria"),
                dataVencimento,
                CategoriaAtivo.toEnum(resultSet.getString("categoria_ativo")),
                emissora,
                portadora,
                indexador,
                CategoriaRentabilidade.toEnum(resultSet.getString("categoria_rentabilidade")),
                resultSet.getDouble("porcentagem_indexador"),
                resultSet.getDouble("rentabilidade"));
    }




    @Override
    public List<Ativo> findAll() {

        String sql = "SELECT * FROM ativo";
        List<Ativo> ativos = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Ativo ativo = resultSetToEntity(resultSet);
                ativos.add(ativo);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ativos;
    }

    @Override
    public boolean update(Ativo ativo) {
        String sql = "UPDATE ativo set nome=?, liquidez_diaria=?, data_vencimento=?, categoria_ativo=?, emissora=?, portadora=?, indexador=?, " +
                "categoria_rentabilidade=?, porcentagem_indexador=?, rentabilidade=? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, ativo.getNome());
            stmt.setBoolean(2, ativo.isLiquidezDiaria());
            stmt.setString(3,ativo.getDataVencimento().toString());
            stmt.setString(4, ativo.getCategoriaAtivo().toString());
            stmt.setInt(5, ativo.getEmissora().getId());
            stmt.setInt(6, ativo.getPortadora().getId());
            stmt.setInt(7, ativo.getIndexador().getId());
            stmt.setString(8, ativo.getCategoriaRentabilidade().toString());
            stmt.setDouble(9, ativo.getPorcentagemSobreIndexador());
            stmt.setDouble(10, ativo.getRentabilidade());
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        
        String sql = "DELETE FROM ativo WHERE id = ?";

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
    public boolean delete(Ativo ativo) {
        if(ativo == null || ativo.getId() == null)
            throw new IllegalArgumentException("Ativo e id ativo não pode ser nulo!");
            return deleteByKey(ativo.getId());
    }
}