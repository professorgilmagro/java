/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
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
        window.pack();
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
}
