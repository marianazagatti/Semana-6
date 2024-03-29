import br.com.farmacia.service.ProdutoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        var opcao = exibirMenu();
        while (opcao != 5) {
            try {
                switch (opcao) {
                    case 1:
                        cadastrarProduto();
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        alterarPrecoProduto();
                        break;
                    case 4:
                        deletarProduto();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }
            opcao = exibirMenu();
        }

        System.out.println("Finalizando a aplicação.");
    }

    //MENU DE OPÇÕES
    private static int exibirMenu() {
        System.out.println("""
                FARMÁCIA - ESCOLHA UMA OPÇÃO:
                        1 - Adicionar produto
                        2 - Listar produtos
                        3 - Alterar preço
                        4 - Excluir produto
                        5 - Sair
                """);
        return teclado.nextInt();
    }

        //CADASTRAR PRODUTOS
        private static void cadastrarProduto() {
            System.out.println("Digite o numero do ID:");
            var id = teclado.nextInt();
            System.out.println("Digite o preço:");
            var preco = teclado.nextDouble();
            System.out.println("Digite o nome do produto:");
            var nome = teclado.next();
            teclado.nextLine();
            System.out.println("Digite o fabricante:");
            var fabricante = teclado.next();
            teclado.nextLine();

            ProdutoService produtoService = new ProdutoService();
            produtoService.registerProduct(id, preco, nome, fabricante);

            System.out.println("Produto cadastrado com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
            teclado.next();
        }

        /* LISTAR PRODUTOS */
        private static void listarProdutos() {
            System.out.println("Produtos cadastrados:");
            ProdutoService produtoService = new ProdutoService();
            var produtos = produtoService.viewProduct();
            produtos.stream().forEach(System.out::println);

            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
            teclado.next();
        }

        //LISTAR POR ID
//        private static void listarProdutoPorID() {
//            ProdutoService produtoService = new ProdutoService();
//            System.out.println("Produto cadastrado:");
//            var produto = produtoService.viewProductByID(3);
//            System.out.println(produto.toString());
//    }


        //ALTERAR PREÇO
        private static void alterarPrecoProduto() {
            System.out.println("Digite o número do ID:");
            var id = teclado.nextInt();

            System.out.println("Digite o valor do produto:");
            var preco = teclado.nextDouble();

            ProdutoService produtoService = new ProdutoService();
            produtoService.updateProduct(id, preco);

            System.out.println("Preço alterado com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
            teclado.next();
        }

        //DELETAR PRODUTO
        private static void deletarProduto() {
            System.out.println("Digite o ID do produto:");
            var id = teclado.nextInt();

            ProdutoService produtoService = new ProdutoService();
            produtoService.deleteProduct(id);
            System.out.println("Produto excluído!");
            teclado.next();
        }
    }
