package br.com.farmacia.service;

import br.com.farmacia.dao.ConnectionFactory;
import br.com.farmacia.dao.ProdutoDao;
import br.com.farmacia.modelo.Produto;

import java.sql.Connection;
import java.util.Set;

public class ProdutoService {
    private ConnectionFactory connection;
    public ProdutoService(){
        this.connection = new ConnectionFactory();
    }

    public void registerProduct(int id, double preco, String nome, String fabricante){
        Connection conn = connection.startConection();
        new ProdutoDao(conn).insert(id, preco, nome, fabricante);
    }

//    private Set<Produto> products = new HashSet<>();
    public Set<Produto> viewProduct(){
        Connection conn = connection.startConection();
        return new ProdutoDao(conn).viewList();
    }

    public Produto viewProductByID(int id){
        Connection conn = connection.startConection();
        Produto produto =new ProdutoDao(conn).viewListByID(id);
        if(produto != null) {
            return produto;
        } else {
            System.out.println("Não existe produto cadastrado com esse ID!");
        }
        return produto;
    }

    public void updateProduct(int id, double preco){
        var produto = viewProductByID(id);
        Connection conn = connection.startConection();
        new ProdutoDao(conn).update(id, preco);
    }

    public void deleteProduct(int id){
        var produto = viewProductByID(id);
        Connection conn = connection.startConection();

        new ProdutoDao(conn).delete(id);
    }
}