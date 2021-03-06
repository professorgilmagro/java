/*
 * Interface para utilização nas classes Model, visando:
 *
 * 1- Garantir a existência dos métodos nesta interface nas classes
 * 2- Referenciar as classes que a implementam de uma mesma forma
 */
package estoque;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.table.*;

/**
 *
 * @author gilmar
 */
public interface IOModelInterface {
    /**
     * Retorna uma lista com os objetos do model
     * 
     * @return List
     * @throws java.io.FileNotFoundException
     */
     public List getAll() throws FileNotFoundException, IOException;
    
     /**
     * Salva os dados do objeto
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public void save() throws FileNotFoundException, IOException, ClassNotFoundException ;
    
    /**
     * Remove o objeto pelo ID
     * 
     * @param ID    Código do objeto
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public void remove(long ID) throws FileNotFoundException, IOException, ClassNotFoundException ;
    
    /**
     * Retorna o código identificador do objeto
     * 
     * @return boolean
     */
    public long getID();
    
    /**
     * Define o código identificador do objeto
     * 
     * @param ID 
     */
    public void setID(long ID);
    
    /**
     * Retorna o maior código identificador utilizado na coleção de dados
     * 
     * @return long
     */
    public long getMaxID();
    
    /**
     * Retorna o diretório onde os arquivos de dados são guardados
     * 
     * @return String
     */
    public String getStorageDir();
    
    /**
     * Retorna um modelo de dados para renderização da tabela em objetos swing 
     * 
     * @return TableModel
     */
    public TableModel getTableModel();
    
    /**
     * Permite localizar um objeto a partir do ID
     * 
     * @param ID    Código do objeto
     * 
     * @return int
     * @throws IOException 
     */
    public IOModelInterface findByID(long ID) throws IOException ;
}
