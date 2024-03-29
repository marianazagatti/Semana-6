package br.com.farmacia.dao;

import br.com.farmacia.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDao {
    private Connection conn;
    public ProdutoDao(Connection connection){
        this.conn = connection;
    }

    public void insert(int id, double preco, String nome, String fabricante){
        String sql = "INSERT INTO produto(id, preco, nome, fabricante) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setString(3, nome);
            preparedStatement.setString(4, fabricante);
            preparedStatement.execute();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Set<Produto> viewList(){
        Set<Produto> produtos = new HashSet<>();
        String sql = "SELECT * FROM produto";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                double preco = resultSet.getDouble(2);
                String nome = resultSet.getString(3);
                String fabricante = resultSet.getString(4);

                Produto produto = new Produto(id, preco, nome, fabricante);
                produtos.add(produto);
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produtos;
    }


    public Produto viewListByID(int id){
        String sql = "SELECT * FROM produto WHERE id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Produto produto = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int idRecuperado = resultSet.getInt(1);
                double preco = resultSet.getDouble(2);
                String nome = resultSet.getString(3);
                String fabricante = resultSet.getString(4);

                produto = new Produto(idRecuperado, preco, nome, fabricante);
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return produto;
    }

    public void update(int id, double preco){
    PreparedStatement preparedStatement;
    String sql = "UPDATE produto SET preco = ? WHERE id = ?";

    try {
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setDouble(1, preco);
        preparedStatement.setInt(2, id);

        preparedStatement.execute();
        preparedStatement.close();
        conn.close();
    }
    catch (SQLException e){
        throw new RuntimeException(e);
    }
    }

    public void delete(int id){
        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
