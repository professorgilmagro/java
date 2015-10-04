/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Categoria;
import model.Util;
import view.MainScreen;
import view.jPanelCategorias;

/**
 *
 * @author gilmar
 */
public class CategoriaController extends GenericController{

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
    
    @Override
    public void displayView() {
        JFrame mainFrame = new MainScreen();
        JPanel panel = new jPanelCategorias();
        JDialog window = Util.getDefaultWindow(panel, mainFrame, "Categorias");
        window.setLocationRelativeTo(null);
        window.setSize(560,480);
        window.setLocation(mainFrame.getX() + 100, mainFrame.getY() + 100);
        window.setVisible(true);
    }
        
    @Override
    public TableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        
        try {
            ModelInterface cat = this.getObjModel();
            List <ModelInterface> categorias = cat.getAll() ;
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
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
    
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
