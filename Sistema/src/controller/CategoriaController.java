/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Produto;
import model.Util;
import view.MainScreen;
import view.JPanelCategorias;

/**
 * Controller para Manutenção de Categorias de Produtos
 * 
 * @author gilmar
 */
public class CategoriaController extends GenericController{
    
    /**
     * Ao ser instanciado, define o Model padrão para o controlador
     */
    public CategoriaController() {
        Categoria cat = new Categoria();
        super.setModel(cat);
    }
    
    /**
     * Factory para facilitar a criação do controlador
     * 
     * @return CategoriaController
     */
    public static CategoriaController make(){
        CategoriaController cc = new CategoriaController();
        return cc;
    }
    
    /**
     * Renderiza o janela em modo gráfico
     * 
     * @see GenericController
     */
    @Override
    public void showView() {
        JFrame mainFrame = new MainScreen();
        JPanel panel = new JPanelCategorias();
        JDialog window = Util.getDefaultWindow(panel, mainFrame, "Categorias");
        window.setLocationRelativeTo(null);
        window.setSize(625, 420);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    /**
     * Retorna o model com o cabeçalho padrão
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel getHeaderTableModel() {
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        
        return model ;
    }
    
    /**
     * Retorna o modelo para renderização da tabela na tela
     * 
     * @param items Lista de items a serem adicionados no TableModel
     * 
     * @see GenericController
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getTableModel(List<ModelInterface> items){
        DefaultTableModel model = this.getHeaderTableModel();
        
        for(ModelInterface item : items) {
            Categoria c = (Categoria) item ;
            Object[] data = {
                c.getCodigo(),
                c.getNome(),
                c.getDescricao()
            };
            
            model.addRow(data);
        }
        
        return model;
    }
    
    /**
     * Ação para criação de uma nova categoria
     * 
     * @return boolean
     */
    public boolean create() {
        try {
            String nome = Util.showInput("Digite o nome.");
            if( nome.isEmpty() ) return false;

            String descricao = Util.showInput("Digite a descrição.");

            Categoria cat = new Categoria(nome, descricao);
            cat.save();

            Util.showMessage("Categoria salvo com sucesso");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Rertona a coleção de Categorias ordernados
     * 
     * @param asc
     * @return List
     */
    public List<Categoria> fetchSortedItems(boolean asc){
        List<Categoria> categorias = new ArrayList<>();
        try {
           List<ModelInterface> items = this.getObjModel().fetchAll();
            for(ModelInterface item : items) {
                categorias.add((Categoria)item);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if(asc) Collections.sort(categorias);
        if(!asc) Collections.sort(categorias, Collections.reverseOrder());
        
        return categorias;
    }
    
   /**
     * Remove um registro a partir do código especificado
     * Sobrescrito para verificar se a categoria está sendo utilizada por
     * um ou mais produtos
     * 
     * @param ID
     * @return int
     */
    @Override
    public int remove( Long ID ) {
        try {
            List<ModelInterface> produtos = ProdutoController.make().getObjModel().fetchAll();
            
            Categoria cat = (Categoria) this.getObjModel().findBy(ID).get(0) ;
            for (ModelInterface produto : produtos) {
                Produto p = (Produto) produto ;
                
                if(p.getCategoria().hashCode() == cat.hashCode()){
                    Util.showMessage("Não é possível remover esta categoria de produto no momento.\nExiste um ou mais produtos associados à esta categoria.", JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            }
        } catch (IOException ex) {
            return -1;
        }
        
        return super.remove(ID);
    }
}
