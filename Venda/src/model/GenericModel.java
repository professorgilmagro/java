/*
 * Centraliza métodos cujas rotinas são comuns às classes que a herdará
 */
package model;

import dao.ModelInterface;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para implementação de métodos comuns aos objetos
 * 
 * @author gilmar
 */
abstract class GenericModel implements Serializable, Comparable, ModelInterface {
    
    final protected String STORAGE_DIR = "storage/" ;
    
    private String filename = null ;
    
    /**
     * Retorna todos os objetos de um determinado model a partir do arquivo
     * 
     * @return List
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Override
    public List<ModelInterface> fetchAll() throws FileNotFoundException, IOException {
        List<ModelInterface> items = new ArrayList<>();
        File file = new File(this.getFileName());
       
        if ( file.exists() ) {
            try {
                FileInputStream inFile = new FileInputStream(file);
                ObjectInputStream objStream = new ObjectInputStream(inFile);
                List objs = (List<ModelInterface>) objStream.readObject();
                
                for(Object obj : objs) {
                    items.add((ModelInterface) obj );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
         
        return items;
    }
    
    /**
     * Salva os dados do objeto em arquivo
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */    
    @Override
    public void save() throws FileNotFoundException, IOException, ClassNotFoundException {
        List<ModelInterface> objects = this.fetchAll();
        
        if(this.hashCode()== 0){
            Long max = this.getMaxID();
            this.setID( ++max );
            objects.add(this);
        }
        else{
            // entende que já existe e deve ser editado
            for (int i = 0; i < objects.size(); i++) {
                if ( objects.get(i).hashCode() == this.hashCode() ) {
                    objects.set(i, this);
                    break;
                }
            }
        }
                
        FileOutputStream fos = new FileOutputStream(this.getFileName());
        ObjectOutputStream obs = new ObjectOutputStream(fos);
        obs.writeObject(objects);
        obs.close();
        fos.close();
    }
    
    @Override
    public void remove() throws FileNotFoundException, IOException, ClassNotFoundException {
        List <ModelInterface> objects = this.fetchAll();
        
        Boolean found = false ;
        for (int i = 0; objects.size() < 10; i++) {
            if( objects.get(i).hashCode() == this.hashCode() ){
                objects.remove(i);
                found = true;
                break;
            }
        }
        
        if ( found ){
            FileOutputStream fos = new FileOutputStream(this.getFileName());
            ObjectOutputStream obs = new ObjectOutputStream(fos);
            obs.writeObject(objects);
            obs.close();
            fos.close();
        }
    }
    
    @Override
    public long getMaxID(){
        long max = 0;
        try {
            List <ModelInterface> objects = this.fetchAll();
            for (Object object : objects) {
                if(object.hashCode() > max){
                    max = object.hashCode();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return max;
    }
    
    /**
     * Retorna o caminho do diretório os arquivos de dados são guardados
     * 
     * @return String
     */
    @Override
    public String getStorageDir() {
        CodeSource codeSource = Cliente.class.getProtectionDomain().getCodeSource();
        String appDir;
        
        try {
            File app = new File(codeSource.getLocation().toURI().getPath());
            appDir = app.getParentFile().getPath() ;
        } catch (URISyntaxException ex) {
            appDir = "" ;
        }
        
        String storageDir = String.format("%s/%s", appDir, this.STORAGE_DIR) ;
        new File(storageDir).mkdirs();
        
        return storageDir ;
    }
    
    /**
     * Retorna o nome do arquivo onde os dados serão recuperados e gravados
     * 
     * @return String
     */
    @Override
    public String getFileName() {
        if ( this.filename == null ) {
            this.filename = String.format(
                "%s%s.dat", this.getStorageDir(), 
                this.getClass().getName().replace("model.", "").toLowerCase() 
            );
        }
        
        return this.filename ;
    }
    
    /**
     * Permite localizar um objeto a partir do ID
     * 
     * @param ID    Código do objeto
     * 
     * @return ModelInterface
     */
    @Override
    public List <ModelInterface> findBy(long ID) {
        List results = new ArrayList();
        try {
            List <ModelInterface> objects = this.fetchAll();
            for (ModelInterface object : objects) {
                if( (long) object.hashCode() == ID ) {
                    results.add(object);
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results ;
    }
    
    /**
     * Permite localizar um ou mais objetos a partir do nome
     * 
     * @param name    Nome, literal de busca
     * 
     * @return List
     */
    @Override
    public List <ModelInterface> findBy(String name) {
        List results = new ArrayList();
        
        try {
            List objects = this.fetchAll();
            
            for(Object object : objects){
                ModelInterface model = (ModelInterface) object;
                if( model.toString().toLowerCase().startsWith(name.toLowerCase()) ) {
                    results.add(object);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results; 
    }
    
     /**
     * Permite fazer a comparação dos objetos para ordernação
     * 
     * @param AbstractModel obj   Objeto a ser comparado
     * 
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        return this.toString().compareToIgnoreCase(o.toString());
    }
    
    /**
     * Permite associar uma outra origem para o arquivo
     * 
     * @param filename 
     */
    @Override
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
