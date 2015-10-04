/*
 * Centraliza os métodos e recursos comuns às controllers
 */
package controller;

import dao.ModelInterface;
import javax.swing.JTable;
import javax.swing.table.TableModel;
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
     * Retorna um modelo de dados para renderização da tabela em objetos swing 
     * 
     * @return TableModel
     */
    abstract public TableModel getTableModel();
    
    public int remove( JTable table ) {
       int row = table.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para excluir.");
            return 0;
        }
       
       long ID = (long) table.getModel().getValueAt(row, 0);
       ModelInterface item = this.getObjModel().findBy(ID);
       
       String message = String.format("Tem certeza que deseja excluir o item '%s'?", item.toString());
       
       if(Util.showConfirm(message, "Remover?")) {
            try {
                item.remove();
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
    public ModelInterface search() {
       String search = Util.showInput("Entre com o código ou nome a ser procurado");
       if ( Util.isNumeric(search) ) {
           return this.getObjModel().findBy(Long.parseLong(search));
       }
       
       return this.getObjModel().findBy(search);
    }
}
