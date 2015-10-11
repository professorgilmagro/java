/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.awt.GraphicsEnvironment;
import java.util.Iterator;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Produto;
import model.Util;
import model.Venda;
import view.jPanelVendas;

/**
 * Controller para Manutenção de Vendas
 * 
 * @author gilmar
 */
public class VendaController extends GenericController{
    
    /**
     * Ao ser instanciado, define o Model padrão para o controlador
     */
    public VendaController() {
        Venda vd = new Venda();
        super.setModel(vd);
    }
    
    /**
     * Factory para facilitar a criação do controlador
     * 
     * @return ProdutoController
     */
    public static VendaController make(){
        VendaController vc = new VendaController();
        return vc;
    }
    
    /**
     * Renderiza o janela em modo gráfico
     * 
     * @see GenericController
     */
    @Override
    public void displayView() {
        JPanel panel = new jPanelVendas();
        JDialog window = Util.getDefaultWindow(panel, "Controle de Vendas");
        window.setModal(false);
        window.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
        window.setVisible(true);
    }
    
    /**
     * Retorna o model com o cabeçalho padrão
     * 
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getHeaderTableModel() {
       DefaultTableModel model = new DefaultTableModel();
                     
       model.addColumn("Código");
       model.addColumn("Nome");
       model.addColumn("Descrição");
        
        return model ;
    }
    
    /**
     * Retorna o model com o cabeçalho padrão dos itens
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel getHeaderItemsTableModel() {
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Produto");
        model.addColumn("Descrição");
        model.addColumn("Valor unitário");
        model.addColumn("Quantidade");
        model.addColumn("Subtotal");
        
        return model ;
    }
    
    /**
     * Retorna o modelo para renderização da tabela na tela
     * 
     * @param items
     * @see GenericController
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getTableModel(List <ModelInterface> items){
        DefaultTableModel model = this.getHeaderTableModel();
        for(ModelInterface item : items) {
            Venda vd = (Venda) item;
            Object[] data = {
                vd.getCodigo(),
                vd.getCliente(),
                vd.getTotal()
            };

            model.addRow(data);
        }
        
        return model;
    }
    
    /**
     * Prepara os itens e retorna o model para renderização em tabela
     * 
     * @return DefaultTableModel
     */
    public DefaultTableModel getModelItems(){
        DefaultTableModel model = this.getHeaderItemsTableModel();
        
        Venda vd = (Venda) this.getObjModel();
        for (Iterator iterator = vd.getItens().iterator(); iterator.hasNext();) {
            Object[] dataRow = (Object[]) iterator.next();
            model.addRow(dataRow);
        }

        return model;
    }
}
