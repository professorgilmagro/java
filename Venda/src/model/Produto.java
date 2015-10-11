/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package model;

import java.text.DecimalFormat;

/**
 * Produto
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Produto extends GenericModel{
    
    private long codigo;
    private String nome, descricao;
    private double valor;
    private double peso;
    private long codCategoria;
    private int saldoEstoque;
    private int estoqueNivelCritico;
    
    /**
     * Inicializa o nível de estoque
     */
    public Produto(){
        this.estoqueNivelCritico = 0;
    }
    
    /**
     * Facilita a criação do produto
     * 
     * @param Nome          Nome do produto
     * @param Descricao     Descrição
     * @param Valor         Valor unitário
     */
    public Produto(String Nome, String Descricao, double Valor ){
        this.estoqueNivelCritico = 0;
        this.nome = Nome;
        this.descricao = Descricao;
        this.valor = Valor;
    }
    
     @Override
    public int hashCode() {
        return (int) this.getCodigo();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
       
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }

        return true;
    }
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("%s - %s - R$ %s" , this.getNome(), 
            this.getDescricao(), df.format(this.getValor()));
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    public void setDescricao(String Descricao){
        this.descricao = Descricao;
    }
    
    public void setNome(String Nome){
        this.nome = Nome;
    }
    
    public void setPeso(double Peso){
        this.peso = Peso;
    }
    
    public void setValor(double Valor){
        this.valor = Valor;
    }

    public long getCodigo(){
        return this.codigo;
    }

    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }

    public double getPeso(){
        return this.peso;
    }
    
    public double getValor(){
        return this.valor;
    }

    @Override
    public void setID(long ID) {
        this.setCodigo(ID);
    }
    
    public String getFormatPrice(){
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("R$ %s", df.format(this.getValor()));
    }

    public Long getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(long codCategoria) {
        this.codCategoria = codCategoria;
    }
    
    public void setCodCategoria(Categoria Cat) {
        this.codCategoria = Cat.getCodigo();
    }
    
    /**
     * Retorna uma instância da Categoria de relação com este objeto
     * 
     * @return Categoria
     */
    public Categoria getCategoria() {
        Categoria cat = new Categoria();
        return (Categoria) cat.findBy(this.getCodCategoria()).get(0);
    }
    
    /**
     * Retorna a quantidade disponível em estoque
     * 
     * @return int
     */
    public int getSaldoEstoque() {
        return this.saldoEstoque;
    }
    
    /**
     * Define a quantidade em estoque
     * 
     * @param saldoEstoque
     */
    public void setSaldoEstoque(int saldoEstoque) {
        this.saldoEstoque = saldoEstoque;
    }
    
    /**
     * Retorna o nível crítico para o estoque
     * 
     * @return int
     */
    public int getNivelCritico() {
        return estoqueNivelCritico;
    }
    
     /**
     * Define o nível crítico de estoque para o produto
     * 
     * @param nivel
     */
    public void setNivelCritico(int nivel) {
        this.estoqueNivelCritico = nivel;
    }
    
    /**
     * Retorna se o estoque está em nível crítico
     * 
     * @return boolean
     */
    public boolean estoqueCritico() {
        return this.getSaldoEstoque() < this.getNivelCritico() ;
    }
    
    /**
     * Permite adicionar novas quantidades ao estoque do objeto
     * @param qtde  Quantidade a ser adicionada
     */
    public void addEstque( int qtde ) {
       int estoqueAtual = this.getSaldoEstoque() ;
       this.setSaldoEstoque( estoqueAtual + qtde );
    }
} 
