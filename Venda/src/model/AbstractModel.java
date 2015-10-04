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
abstract class AbstractModel implements Serializable, Comparable, ModelInterface {
    
    final protected String STORAGE_DIR = "storage/" ;
    
    /**
     * Retorna todos os objetos de um determinado model a partir do arquivo
     * 
     * @return List
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Override
    public List getAll() throws FileNotFoundException, IOException {
        List<AbstractModel> items = new ArrayList<>();
        File file = new File(this.getFileName());
       
        if ( file.exists() ) {
            FileInputStream inFile = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(inFile);
            
            try {
              List objs = (List<AbstractModel>) objStream.readObject(); 
                for (Object obj : objs) {
                    items.add( (AbstractModel) obj );
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
        List objects = this.getAll();
        
        if(this.hashCode()== 0){
            Long max = this.getMaxID();
            this.setID( ++max );
            objects.add(this);
        }
        else{
            // entende que já existe e deve ser editado
            for (int i = 0; i < objects.size(); i++) {
                AbstractModel obj = (AbstractModel) objects.get(i);
                if ( obj.hashCode()== this.hashCode() ) {
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
        List <ModelInterface> objects = this.getAll();
        
        Boolean found = false ;
        for (int i = 0; objects.size() < 10; i++) {
            ModelInterface obj = (ModelInterface) objects.get(i);
            if( obj.hashCode() == this.hashCode() ){
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
            List objects = this.getAll();
            for (Object object : objects) {
                ModelInterface model = (ModelInterface) object;
                if(model.hashCode() > max){
                    max = model.hashCode();
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
        return String.format(
                "%s%s.dat", this.getStorageDir(), 
                this.getClass().getName().replace("estoque.", "").toLowerCase()
        );
    }
    
    /**
     * Permite localizar um objeto a partir do ID
     * 
     * @param ID    Código do objeto
     * 
     * @return ModelInterface
     */
    public ModelInterface findBy(long ID) {
        try {
            List objects = this.getAll();
            
            for (Object object : objects) {
                ModelInterface model = (ModelInterface) object;
                if( (long) model.hashCode() == ID ) {
                    return model ;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setID(0);
        return this ;
    }
    
    /**
     * Permite localizar um objeto a partir do ID
     * 
     * @param name    Nome, literal de busca
     * 
     * @return ModelInterface
     */
    @Override
    public ModelInterface findBy(String name) {
        try {
            List objects = this.getAll();
            
            for (Object object : objects) {
                ModelInterface model = (ModelInterface) object;
                if( model.toString().toLowerCase().startsWith(name.toLowerCase()) ) {
                    return model ;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setID(0);
        return this ;
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
        AbstractModel obj = (AbstractModel) o ;
        if (this.hashCode()< obj.hashCode()) {
            return -1;
        }
        
        if (this.hashCode() == obj.hashCode()) {
            return 0;
        }
        
        assert this.hashCode() > obj.hashCode();
        return 1;
    }
}
