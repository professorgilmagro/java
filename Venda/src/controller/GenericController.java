/*
 * Centraliza os métodos e recursos comuns às controllers
 */
package controller;

import dao.ModelInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Util;

/**
 * Controlador genérico
 * 
 * @author gilmar
 */
abstract class GenericController {

    private ModelInterface objModel = null;
    
    /**
     * Construtor
     */
    public void AbstractController(){}
    
     /**
     * Construtor sobrecarregado para permitir a injeção do model associado
     */
    public void AbstractController( ModelInterface model ){
        this.objModel = model ;
    }
    
    /**
     * Renderiza a tela para visualização gráfica
     */
    abstract public void displayView();
    
    /**
     * Permite definir o módel de relação com este controlador
     * 
     * @param model 
     */
    public void setModel(ModelInterface model) {
        this.objModel = model;
    }
    
    /**
     * Retorna o model de relação com este controlador
     * 
     * @return ModelInterface
     */
    public ModelInterface getObjModel() {
        return objModel;
    }
    
    /**
     * Retorna o modelo para renderização da tabela na tela
     * 
     * @see GenericController
     * @return DefaultTableModel
     */
    public DefaultTableModel getTableModel(){
        DefaultTableModel model = this.getHeaderTableModel();
                     
        try {
            List <ModelInterface> items = this.getObjModel().fetchAll();
            return this.getTableModel(items);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return model;
    }
    
    /**
     * Retorna um modelo de dados para renderização da tabela em objetos swing 
     * 
     * @return TableModel
     */
    abstract public DefaultTableModel getTableModel(List <ModelInterface> items);
    
    /**
     * Retorna um modelo de header para renderização das colunas da tabela
     * 
     * @return DefaultTableModel
     */
    abstract public DefaultTableModel getHeaderTableModel();
    
    /**
     * Remove um registro a partir de uma tabela swing
     * 
     * @param table Tabela cujo item será removido
     * @return int
     */
    public int remove( JTable table ) {
       int row = table.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para excluir.");
            return 0;
        }
       
       long ID = (long) table.getModel().getValueAt(row, 0);
       List <ModelInterface> item = this.getObjModel().findBy(ID);
       
       String message = String.format("Tem certeza que deseja excluir o item '%s'?", item.toString());
       
       if(Util.showConfirm(message, "Remover?")) {
            try {
                item.get(0).remove();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return -1;
            }
       }
       
       return 1 ;
    }
    
    /**
     * Permite fazer a busca do objeto a partir do código ou nome informado
     * 
     * @return ModelInterface
     */
    public List <ModelInterface> search() {
       String search = Util.showInput("Entre com o código ou nome a ser procurado");
       if( search == null ){
           return new ArrayList();
       }
       
       if ( Util.isNumeric(search) ) {
           return this.getObjModel().findBy(Long.parseLong(search));
       }
       
       return this.getObjModel().findBy(search);
    }
    
    /**
     * Permite salvar os dados em outro lugar (Backup)
     */
    public void saveToFile(){
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File source = new File(this.getObjModel().getFileName());
            File dest = chooser.getSelectedFile() ;
 
            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Util.showMessage(String.format( "Dados salvo com sucesso em: %s" , dest.getPath()));
        }
    }
    
    /**
     * Permite carregar os dados a partir de outro arquivo
     */
     public void loadFromFile(JLabel fileStatus){
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            
            try {
                FileInputStream inFile = new FileInputStream(source);
                ObjectInputStream objectInputStream = new ObjectInputStream(inFile);
            } catch (Exception ex) {
                Util.showMessage("O arquivo informado é inválido!", "Inválido", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            
            this.getObjModel().setFilename(source.getPath());
            fileStatus.setText(String.format( "Dados salvo com sucesso em: %s" , source.getPath()));
        }
    }
}
