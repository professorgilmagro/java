/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package model;

/**
 * Categoria de produto
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Categoria extends GenericModel{
    
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
    public int hashCode() {
        return (int) this.getCodigo();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.getCodigo() != other.getCodigo()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", this.getNome(), this.getDescricao());
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
    public void setID(long ID) {
        this.setCodigo(ID);
    }
} 
