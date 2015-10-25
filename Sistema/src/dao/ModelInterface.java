/*
 * Interface para utilização nas classes Model, visando:
 *
 * 1- Garantir a existência dos métodos nesta interface nas classes
 * 2- Referenciar as classes que a implementam de uma mesma forma
 */
package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author gilmar
 */
public interface ModelInterface {
    /**
     * Retorna uma lista com os objetos do model
     * 
     * @return List
     * @throws java.io.FileNotFoundException
     */
     public List<ModelInterface> fetchAll() throws FileNotFoundException, IOException;
    
     /**
     * Salva os dados do objeto
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public void save() throws FileNotFoundException, IOException, ClassNotFoundException ;
    
    /**
     * Remove o objeto pelo ID
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public void remove() throws FileNotFoundException, IOException, ClassNotFoundException ;
    
    /**
     * Define o código identificador do objeto
     * 
     * @param ID 
     */
    public void setID(long ID);
    
    /**
     * Define a data de criação do objeto
     * 
     * @param date 
     */
    public void setCreationDate(Date date);
    
    /**
     * Define a data de modificação do objeto
     * 
     * @param date 
     */
    public void setModificationDate(Date date);
    
    /**
     * Retorna a data de modificação do objeto
     * 
     * @return Date 
     */
    public Date getModificationDate();
    
    /**
     * Retorna a data de criação do objeto
     * 
     * @return Date 
     */
    public Date getCreationDate();
    
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
     * Retorna o nome do arquivo para o objeto atual
     * 
     * @return String
     */
    public String getFileName() ;
    
     /**
     * Permite associar uma outra origem para o arquivo
     * 
     * @param filename
     */
    public void setFilename(String filename);
    
    /**
     * Permite localizar um objeto a partir do ID
     * 
     * @param ID    Código do objeto
     * 
     * @return ModelInterface
     */
    public List <ModelInterface> findBy(long ID);
    
    /**
     * Permite localizar um objeto a partir de um literal
     * 
     * @param name    Literal que representa o objeto a ser localizado
     * 
     * @return List
     */
    public List <ModelInterface> findBy(String name) ;
}
