/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
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
     * @see GenericController
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getTableModel(){
        DefaultTableModel model = this.getHeaderTableModel();
                     
        try {
           List <ModelInterface> vendas = this.getObjModel().fetchAll();
            for (ModelInterface item : vendas) {
                Venda vd = (Venda) item;
                Object[] data = {
                    vd.getCodigo(),
                    vd.getCliente(),
                    vd.getTotal()
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
     * Ação para criação de um novo produto
     * 
     * @return boolean
     */
    public boolean create() {
        try {
            Categoria cat = new Categoria();
            List objs = cat.fetchAll();

            if( objs.isEmpty() ) {
                Util.showMessage(
                    "Não há categoria de produto cadastrado.\n" +
                    "Cadastre as categorias desejadas antes de continuar.",
                    JOptionPane.WARNING_MESSAGE
                );
                
                return false ;
            }

            Categoria categoria = (Categoria) Util.showOptions("Selecione a categoria.", objs.toArray(), "Categoria") ;
            if( categoria == null ) return false;

            String nome = Util.showInput("Digite o nome.");
             if( nome == null ) return false ;

            String descricao = Util.showInput("Digite a descrição.");
             if( descricao == null ) return false ;

            double peso = Util.convertCurrencyToDouble(Util.showInput("Digite o peso (kg).")) ;
             if( peso == 0.00 ) return false ;

            double valor = Util.convertCurrencyToDouble(Util.showInput("Digite o valor unitário.")) ;
             if( valor == 0.00 ) return false ;

            int estoque = Integer.parseInt(Util.showInput("Digite a quantidade em estoque.")) ;
            int nivelCritico = Integer.parseInt(Util.showInput("Digite o nível crítico de estoque para este produto.")) ;

            Produto produto = new Produto(nome, descricao, valor);
            produto.setCodCategoria(categoria);
            produto.setPeso(peso);
            produto.setSaldoEstoque(estoque);
            produto.setNivelCritico(nivelCritico);
            produto.save();

            Util.showMessage("Produto salvo com sucesso");

        } catch (Exception e) {
            Util.showMessage("Um ou mais valores informados inválidos!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
     /**
     * Permite fazer a busca do objeto a partir do código ou nome informado
     * 
     * @return ModelInterface
     */
    public ModelInterface search() {
       ModelInterface order = super.search() ;
       super.setModel(order);
       return order;
    }
}
