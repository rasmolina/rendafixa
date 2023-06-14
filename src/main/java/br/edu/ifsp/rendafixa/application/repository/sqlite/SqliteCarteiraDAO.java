package br.edu.ifsp.rendafixa.application.repository.sqlite;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.CarteiraDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCarteiraDAO implements CarteiraDAO {
    @Override

    public void incluirAtivoCarteira(Carteira carteira, Ativo ativo) {
        String verificarAtivo = "SELECT * FROM ativos_carteira WHERE id_ativo = ?";
        String sql = "INSERT INTO ativos_carteira (id_ativo, id_carteira) VALUES (?, ?)";

        try (PreparedStatement stmtVerificar = ConnectionFactory.createPreparedStatement(verificarAtivo);
             PreparedStatement stmtInserir = ConnectionFactory.createPreparedStatement(sql)) {
            stmtVerificar.setInt(1, ativo.getId());
            ResultSet resultado = stmtVerificar.executeQuery();

            if (resultado.next()) {
                System.out.println("O ativo já existe na tabela ativos_carteira!");
            } else {
                stmtInserir.setInt(1, ativo.getId());
                stmtInserir.setInt(2, carteira.getId());
                stmtInserir.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean removerAtivoCarteira(Carteira carteira, Ativo ativo) {
        String verificar = "SELECT * FROM compras_ativo WHERE id_ativo = ?";
        String sql = "DELETE FROM ativos_carteira WHERE id_carteira = ? AND id_ativo = ?";

        try (PreparedStatement stmtVerificar = ConnectionFactory.createPreparedStatement(verificar);
             PreparedStatement stmtRemover = ConnectionFactory.createPreparedStatement(sql)) {
            stmtVerificar.setInt(1, ativo.getId());
            ResultSet resultado = stmtVerificar.executeQuery();

            if (resultado.next()) {
                // O ativo está presente na tabela compras_ativo, não é possível remover
                System.out.println("Não é possível remover o ativo, existem aplicações para ele!");
                return false;
            } else {
                // O ativo não está presente na tabela compras_ativo, realizar a remoção
                stmtRemover.setInt(1, carteira.getId());
                stmtRemover.setInt(2, ativo.getId());
                stmtRemover.execute();
                System.out.println("Ativo removido com sucesso da carteira!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double CalcularTotalInvestidoPorAtivo(Ativo ativo) {
        String sql = "select sum(valor_compra) as total from item_ativo where id_ativo = ?";
        double total = 0;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, ativo.getId());
            stmt.execute();
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void comprarAtivo(Carteira carteira, Ativo ativo, double valorCompra, LocalDate dataCompra) {
        String sqlVerificarAtivo = "SELECT id_ativo FROM ativos_carteira WHERE id_carteira = ? AND id_ativo = ?";
        String sqlInserirItemAtivo = "INSERT INTO item_ativo (id_ativo, data_compra, valor_compra) VALUES (?, ?, ?)";

        try (PreparedStatement stmtVerificarAtivo = ConnectionFactory.createPreparedStatement(sqlVerificarAtivo);
             PreparedStatement stmtInserirItemAtivo = ConnectionFactory.createPreparedStatement(sqlInserirItemAtivo)) {

            stmtVerificarAtivo.setInt(1, carteira.getId());
            stmtVerificarAtivo.setInt(2, ativo.getId());

            ResultSet rs = stmtVerificarAtivo.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dataFormatada = dataCompra.format(formatter);

            if (rs.next()) {
                stmtInserirItemAtivo.setInt(1, ativo.getId());
                stmtInserirItemAtivo.setString(2,dataFormatada);
                stmtInserirItemAtivo.setDouble(3, valorCompra);

                stmtInserirItemAtivo.executeUpdate();
            } else {
                System.out.println("O ativo não está presente na carteira!.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void resgatarAtivo(Carteira carteira, Ativo ativo) {
            String sqlVerificarAtivo = "SELECT id_ativo FROM ativos_carteira WHERE id_carteira = ? AND id_ativo = ?";
            String sql = "SELECT * FROM ";

            try (PreparedStatement stmtVerificarAtivo = ConnectionFactory.createPreparedStatement(sqlVerificarAtivo);
                 PreparedStatement stmtInserirItemAtivo = ConnectionFactory.createPreparedStatement(sql))
            {
                //stmtInserirItemAtivo.setInt(1,ativo.g);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
    }

    @Override
    public void ResgatarAtivosVencidos(Carteira carteira) {
        String sql = "SELECT a.data_vencimento FROM item_ativo item INNER JOIN ativo a WHERE id_carteira = ?";
        List<Ativo> ativos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Emissora emissora = new SqliteEmissoraDAO().buscar(resultSet.getInt("emissora"));
                Portadora portadora = new SqlitePortadoraDAO().buscar(resultSet.getInt("portadora"));
                Indexador indexador = new SqliteIndexadorDAO().buscar(resultSet.getInt("indexador"));

                Ativo ativo = new SqliteAtivoDAO().buscar(resultSet.getInt("ativo"));
                //ItemAtivo itemAtivo = new ItemAtivo(ativo,);
                ativos.add(ativo);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        //return ativos;
    }

    @Override
    public boolean consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo) {
        String sql = "SELECT item.id FROM item_ativo item INNER JOIN ativos_carteira ativo ON item.id_ativo = ativo.id_ativo WHERE ativo.id_carteira = ? and ativo.id_ativo = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, idCarteira);
            stmt.setInt(2, ativo.getId());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public Carteira buscar(int id) {

        String sql = "SELECT * FROM carteira WHERE id=?";
        Carteira carteira = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                carteira = new Carteira(rs.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return carteira;
    }

    @Override
    public Integer create(Carteira carteira) {
        String sql = "INSERT INTO carteira(valor_disponivel) VALUES(?)";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setDouble(1,carteira.getValorDisponivelSaque());
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
    public Optional<Carteira> findOne(Integer key) {
        String sql = "SELECT * FROM ativo carteira id = ?";
        Carteira carteira = null;
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setInt(1,key);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                carteira = resultSetToEntity(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(carteira);
    }


    @Override
    public List<Carteira> findAll() {

        String sql = "SELECT * FROM carteira";
        List<Carteira> carteiras = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Carteira carteira = resultSetToEntity(resultSet);
                carteiras.add(carteira);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return carteiras;
    }


    @Override
    public boolean update(Carteira carteira) {
        String sql = "UPDATE carteira SET valor_disponivel = ? WHERE id=?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setDouble(1,carteira.getValorDisponivelSaque());
            stmt.execute();
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
       return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {

        String sql = "DELETE FROM carteira WHERE id=?";

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
    public boolean delete(Carteira carteira) {

        if(carteira == null ||carteira.getId() == null)
            throw new IllegalArgumentException("Carteira e id carteira não pode ser nulo!");
        return deleteByKey(carteira.getId());
    }

    private Carteira resultSetToEntity(ResultSet resultSet) throws SQLException
    {
        return  new Carteira(resultSet.getInt("id"));
    }

}
