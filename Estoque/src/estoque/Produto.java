/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package estoque;

import estoque.forms.MainScreen;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.TableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * Controle de produto
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Produto extends AbstractModel{
    
    private long codigo;
    private String nome, descricao;
    private double valor;
    private double peso;
    private int codCategoria;
    
    public Produto(){}
    
    /**
     * Facilita a criação do produto
     * 
     * @param Nome          Nome do produto
     * @param Descricao     Descrição
     * @param Valor         Valor unitário
     */
    public Produto(String Nome, String Descricao, double Valor ){
        this.nome = Nome;
        this.descricao = Descricao;
        this.valor = Valor;
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
    
    public void setCategoria(int Categoria){
        this.codCategoria = Categoria;
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
    
    public int getCategoria(){
        return this.codCategoria;
    }
    
    public double getValor(){
        return this.valor;
    }

    @Override
    public String getFileName() {
        return String.format("%sprodutos.dat" , this.getStorageDir()) ;
    }

    @Override
    public long getID() {
        return this.getCodigo();
    }
    
    @Override
    public TableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        model.addColumn("Categoria");
        model.addColumn("Peso");
        model.addColumn("Valor");
        
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            List produtos = this.getAll();
            for (Object item : produtos) {
                Produto prod = (Produto) item;
                Object[] data = {
                    prod.getCodigo(),
                    prod.getNome(),
                    prod.getDescricao(),
                    prod.getCategoria(),
                    prod.getPeso(),
                    df.format(prod.getValor())
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
}
