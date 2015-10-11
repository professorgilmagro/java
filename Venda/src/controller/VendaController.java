/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.awt.GraphicsEnvironment;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Cliente;
import model.Produto;
import model.Util;
import model.Venda;
import view.JPanelVendas;

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
        JPanel panel = new JPanelVendas();
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
       model.addColumn("Cliente");
       model.addColumn("Data venda");
       model.addColumn("Total");
        
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
                vd.getFormatDataVenda(),
                vd.getFormatTotal()
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
    
    /**
     * Permite fazer a busca do objeto a partir do código do pedido, cliente, CPF e e-mail informado
     * 
     * @return ModelInterface
     */
    @Override
    public List<ModelInterface> search() {
        List <ModelInterface> items = new ArrayList();
        
        String[] options = {"Pedido", "Nome", "CPF", "E-mail", "Data de venda"};
        Object option = Util.showOptions("Selecione o tipo de pesquisa.", options, "Selecione o tipo de pesquisa");
        if( option == null ) return items;
        
        String search = Util.showInput(String.format("Entre com o %s a ser procurado", option.toString()));
        if( option == null ) return items;
        
        Venda order = (Venda) this.getObjModel();
        if(option.equals("CPF")){
            search = Util.onlyNumber(search);
            return order.findByClientCPF(Long.parseLong(search));
        }
        
        if(option.equals("E-mail")){
            return order.findByClientEmail(search);
        }
        
        if(option.equals("Data de venda")){
            return order.findBySaleDate(search);
        }
        
        if(option.equals("Nome")){
            return order.findByClientName(search);
        }
        
        if ( Util.isNumeric(search) ) {
           return order.findBy(Long.parseLong(search));
        }
        
        this.getObjModel().setID(0);
        items.add(this.getObjModel());
       
       return items;
    }
}
