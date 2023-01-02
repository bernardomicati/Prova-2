import Classes.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.lang.model.element.ModuleElement.ProvidesDirective;

public class AppProdutos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao;
        Scanner in = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produtos");
            System.out.println("3 - Listar produtos cadastrados");
            System.out.println("4 - Vendas por periodo");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (opcao == 1) {
                    Produto p = new Produto();
                    try{
                    System.out.println("\nDigite o codigo do produto");
                    p.setCodigoProduto(in.nextInt());
                    in.nextLine();
                    }catch(Exception e){
                        in.nextLine();
                        System.out.println("\nO valor inserido é inválido!");
                        voltarMenu(in);
                        continue;
                    }
                    
                    //1 Primeira verificação: Verificar se existe um produto com o codigo igual ao fornecido pelo usuario(Fazer uma busca). 
                    //2 Segunda verificação: Verificar se o codigo digitado é inteiro (Tratar a excessão com try/cat)
                    System.out.println("\nNome do produto: ");
                    p.setNome(in.nextLine());
                    try{
                    System.out.println("\nQuantidade em estoque: ");
                    p.setQuantidadeEstoque(in.nextInt());
                    in.nextLine();
                    }catch(Exception e){
                        in.nextLine();
                        System.out.println("\nO valor inserido é inválido!");
                        voltarMenu(in);
                        continue;
                    }
                    // 1 Primeira verificação: garantir que a quantidade seja maior ou igual a 0
                    // 2 Segunda verificação: igual a verificação de cima
                    try{
                    System.out.println("\nPreço do produto: ");
                    p.setPreco(in.nextInt());
                    in.nextLine();

                    System.out.println("\nProduto cadastrado com sucesso!");
                    voltarMenu(in);                
                    // 1 Primeira verificação: garantir que a preço seja maior a 0
                    // 2 Segunda verificação: igual a verificação de cima
                    produtos.add(p);
                    }catch(Exception e){
                        in.nextLine();
                        System.out.println("\nO valor inserido é inválido !");
                        voltarMenu(in);
                    }

                    continue;
                }

                
                voltarMenu(in);
            } else if (opcao == 2) {
                
                if (produtos.size() == 0){
                    System.out.println("\nNão existem produtos a serem buscados.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("\nDigite o codigo do produto desejado: ");
                int escolha = in.nextInt();
                in.nextLine();

                try{

                for (Produto produto : produtos) {
                    if(escolha == produto.getCodigoProduto()){
                        System.out.println("\nProduto encontrado!");
                        System.out.printf("\nNome do produto: %s \nPreço do produto: %d \nCódigo do produto: %d \nQuantidade no estoque: %d", produto.getNome(), produto.getPreco(), produto.getCodigoProduto(), produto.getQuantidadeEstoque());
                        voltarMenu(in);
                    }
                }
                
            }catch (Exception e) {
            in.nextLine();
            System.out.println("Produto não encontrado!"); 
            voltarMenu(in);
            continue;
            }

                //Retornar "Nenhum produto encontrado" caso a escolha seja nenhum codigo dos produtos cadastrados
            
            } else if (opcao == 3) {
                //Se a lista estiver vazia não há o que procurar.

                if (produtos.size() == 0){
                    System.out.println("\nNão existem produtos a serem listados.");
                    voltarMenu(in);
                    continue;
                }
                //Ordenar pelo nome: usando o metodo sort separado ou na stream
                //Adequar a listagem ao anunciado da prova(Pode ser da quantidade em estoque ou do preço do produto)
        
                produtos.stream().forEach((produto) -> {
                    System.out.println("\nCódigo do produto: " + produto.getCodigoProduto());
                    System.out.println("Nome do produto: " + produto.getNome());
                    System.out.println("Valor da unidade: " + produto.getPreco());
                    System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEstoque());
                    System.out.println("\n**************************");
                });
                voltarMenu(in);
               
                voltarMenu(in);

            } else if (opcao == 4) {
                
                try{
                System.out.print("\nInforme a data inicial no formato 'aaaa-mm-dd': ");
                String dtInicial = in.nextLine();

                System.out.print("\nInforme a data final no formato 'aaaa-mm-dd': ");
                String dtFinal = in.nextLine();

                Venda vendasBt = new Venda();
                vendasBt.getDatesBetween(vendas, dtInicial, dtFinal);
                voltarMenu(in);

            }catch(Exception e){
                System.out.println("\nERROR: Não há vendas neste periodo de tempo, ou o formato da data está errado");
                voltarMenu(in);
            }
        }
    
            else if (opcao == 5){
                if (produtos.size() == 0){
                    System.out.println("Não há produtos cadastrados, verifique o estoque");
                    voltarMenu(in);
                    continue;
                }
                Venda v = new Venda();
                Produto p = new Produto();
                
                v.setDataVenda(LocalDate.now());
                try{
                System.out.println("Digite o codigo do produto: ");
                int buscarCodigo = in.nextInt();
                for (Produto produto : produtos) {
                    if(buscarCodigo == produto.getCodigoProduto()){
                        p = produto;
                    } else if (buscarCodigo != produto.getCodigoProduto()){
                        in.nextLine();
                    }
                } 
                }catch(Exception e){
                System.out.println("\nERROR: Insira apenas números!");
                voltarMenu(in);
             }
             try{
                 System.out.println("\nDigite a quantidade: ");
                 int quantidadeDigitada = in.nextInt();
                 if(quantidadeDigitada <= p.getQuantidadeEstoque()){
                     v.setQuantidadeVendida(quantidadeDigitada);
                     p.removerQuantidadeEstoque(quantidadeDigitada);
                     v.setProdutoVendido(p);
                    }
                    else{
                        System.out.printf("znERROR: Não há quantidade disponível no estoque, há apenas: %s !", p.getQuantidadeEstoque());
                        in.nextLine(); 
                        voltarMenu(in);
                        continue;
                    }
                }catch(Exception e){
                    in.nextLine();
                    System.out.println("\nERROR: Insira apenas números!");
                    voltarMenu(in);
            }
                vendas.add(v);
                System.out.println("\nVenda realizada com sucesso!"); 
                in.nextLine();
                voltarMenu(in);
            }      
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
                voltarMenu(in);
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");
        in.close();
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}