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
public final class Categoria extends GenericModel{
    private static final long serialVersionUID = 1L;
    
    private long codigo;
    private String nome;
    private String descricao;
    
    public Categoria(){}
    
    /**
     * Permite instanciar um objeto a partir de um código existente
     * 
     * @param ID
     */
    public Categoria( Long ID ){
        Categoria cat = new Categoria();
        cat = (Categoria) cat.findBy(ID).get(0);
        this.setCodigo(cat.getCodigo());
        this.setNome(cat.getNome());
        this.setCreationDate(cat.getCreationDate());
        this.setModificationDate(cat.getModificationDate());
    }
    
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
        return String.format("%s", this.getNome());
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
