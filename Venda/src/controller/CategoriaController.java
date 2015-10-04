/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.io.IOException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Util;
import view.MainScreen;
import view.jPanelCategorias;

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
    public void displayView() {
        JFrame mainFrame = new MainScreen();
        JPanel panel = new jPanelCategorias();
        JDialog window = Util.getDefaultWindow(panel, mainFrame, "Categorias");
        window.setLocationRelativeTo(null);
        window.setSize(800,480);
        window.setLocation(mainFrame.getX(), mainFrame.getY() + 100);
        window.setVisible(true);
    }
    
    /**
     * Retorna o modelo para renderização da tabela na tela
     * 
     * @see GenericController
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        
        try {
            ModelInterface cat = this.getObjModel();
            List <ModelInterface> categorias = cat.fetchAll() ;
            for (ModelInterface item : categorias) {
                Categoria c = (Categoria) item ;
                Object[] data = {
                    c.getCodigo(),
                    c.getNome(),
                    c.getDescricao()
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
            return false;
        }
        
        return true;
    }
}
