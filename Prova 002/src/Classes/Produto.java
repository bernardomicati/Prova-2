package Classes;
public class Produto {

    String Nome;
    int codigoProduto;
    int quantidadeEstoque;
    int preco;
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public int getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public String toString() {
        return String.format(" Código do produto: %d\n Nome do produto: %s\n Valor do produto: %d\n Quantidade no estoque: %d\n_",getCodigoProduto(), getNome(), getPreco(), getQuantidadeEstoque());
    }
    public void removerQuantidadeEstoque(int quantidadeDigitada) {
    }

    

    

}


