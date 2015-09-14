/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package estoque;

import estoque.forms.MainScreen;
import java.io.IOException;
import java.util.List;
import javax.swing.table.TableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * Categoria de produto
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Categoria extends AbstractModel{
    
    private long codigo;
    private String nome;
    private String descricao;
    
    public Categoria(){}
    
    /**
     * Facilita a criação da categoria
     * 
     * @param Nome          Nome do produto
     * @param Descricao     Descrição
     */
    public Categoria(String Nome, String Descricao){
        this.nome = Nome;
        this.descricao = Descricao;
    }
    
    @Override
    public String toString() {
        return String.format("%d - %s", this.getID(), this.getNome());
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

    public long getCodigo(){
        return this.codigo;
    }

    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }

    @Override
    public String getFileName() {
        return String.format("%scategorias.dat" , this.getStorageDir()) ;
    }

    @Override
    public long getID() {
        return this.getCodigo();
    }
    
    @Override
    public void setID(long ID) {
        this.setCodigo(ID);
    }
    
    @Override
    public TableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        
        try {
            List produtos = this.getAll();
            for (Object item : produtos) {
                Categoria prod = (Categoria) item;
                Object[] data = {
                    prod.getCodigo(),
                    prod.getNome(),
                    prod.getDescricao()
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
} 
