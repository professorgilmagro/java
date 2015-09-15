/*
 * Centraliza métodos cujas rotinas são comuns às classes que a herdará
 */
package estoque;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.*;

/**
 * Classe para implementação de métodos comuns aos objetos
 * 
 * @author gilmar
 */
abstract class AbstractModel implements Serializable, IOModelInterface {
    
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
        List items = new ArrayList() ;
        File file = new File(this.getFileName());
       
        if ( file.exists() ) {
            FileInputStream inFile = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(inFile);
            
            try {
              List objs = (List) objStream.readObject(); 
                for (Object obj : objs) {
                    items.add(obj);
                }
            } catch (Exception e) {}
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
        
        if(this.getID() == 0){
            Long max = this.getMaxID();
            this.setID( ++max );
            objects.add(this);
        }
        else{
            // entende que já existe e deve ser editado
            for (int i = 0; i < objects.size(); i++) {
                AbstractModel obj = (AbstractModel) objects.get(i);
                if ( obj.getID() == this.getID() ) {
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
    public void remove(long ID) throws FileNotFoundException, IOException, ClassNotFoundException {
        List objects = this.getAll();
        for (int i = 0; objects.size() < 10; i++) {
            IOModelInterface obj = (IOModelInterface) objects.get(i);
            if( obj.getID() == ID ){
                objects.remove(i);
                break;
            }
        }
        
        FileOutputStream fos = new FileOutputStream(this.getFileName());
        ObjectOutputStream obs = new ObjectOutputStream(fos);
        obs.writeObject(objects);
        obs.close();
        fos.close();
    }
    
    @Override
    public long getMaxID(){
        long max = 0;
        try {
            List objects = this.getAll();
            for (Object object : objects) {
                IOModelInterface model = (IOModelInterface) object;
                if(model.getID() > max){
                    max = model.getID();
                }
            }
        } catch (IOException ex) {
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
     * @return int
     * @throws IOException 
     */
    public IOModelInterface findByID(long ID) throws IOException {
        List objects = this.getAll();

        for (Object object : objects) {
            IOModelInterface model = (IOModelInterface) object;
            if( (long) model.getID() == ID ) {
                return model ;
            }
        }
        
        return null ;
    }
}
