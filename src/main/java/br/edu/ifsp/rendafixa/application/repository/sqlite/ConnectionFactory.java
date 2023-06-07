package br.edu.ifsp.rendafixa.application.repository.sqlite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;
public class ConnectionFactory implements AutoCloseable {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    public static Connection createConnection()
    {
        try {
            instatiateConnectionIfnull();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }

    private static void instatiateConnectionIfnull() throws SQLException
    {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:banco_renda_fixa.db");
        if(connection == null)
        {
            connection = ds.getConnection();
        }
    }


    public static PreparedStatement createPreparedStatement(String sql)
    {
        try{
            preparedStatement = createConnection().prepareStatement(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Statement createStatement()
    {
        try{
            statement = createConnection().createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return statement;
    }


    @Override
    public void close() throws Exception {
        closeConnectionIfNotNull();
        closeStatementIfNotNull();
    }

    public void closeConnectionIfNotNull() throws SQLException
    {
        if(connection != null)
        {
            connection.close();
        }
    }

    public void closeStatementIfNotNull() throws SQLException
    {
        if(preparedStatement != null)
        {
            preparedStatement.close();
        }
        if(statement != null)
        {
            statement.close();
        }
    }




}
