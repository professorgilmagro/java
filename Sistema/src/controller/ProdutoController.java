/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import model.Util;
import view.MainScreen;
import view.JPanelProdutos;

/**
 * Controller para Manutenção de Categorias de Produtos
 * 
 * @author gilmar
 */
public class ProdutoController extends GenericController{
    
    /**
     * Ao ser instanciado, define o Model padrão para o controlador
     */
    public ProdutoController() {
        Produto prod = new Produto();
        super.setModel(prod);
    }
    
    /**
     * Factory para facilitar a criação do controlador
     * 
     * @return ProdutoController
     */
    public static ProdutoController make(){
        ProdutoController pc = new ProdutoController();
        return pc;
    }
    
    /**
     * Renderiza o janela em modo gráfico
     * 
     * @see GenericController
     */
    @Override
    public void showView() {
        JFrame mainFrame = new MainScreen();
        JPanel panel = new JPanelProdutos();
        JDialog window = Util.getDefaultWindow(panel, mainFrame, "Produtos");
        window.setSize(820, 430);
        window.setLocationRelativeTo(null);
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
        model.addColumn("Produto");
        model.addColumn("Categoria");
        model.addColumn("Vlr. Unitário");
        
        return model ;
    } 
    
    /**
     * Retorna o modelo para renderização da tabela na tela
     * 
     * @param items Itens de produtos as serem adicionados
     * 
     * @see GenericController
     * @return DefaultTableModel
     */
    @Override
    public DefaultTableModel getTableModel(List <ModelInterface> items){
        DefaultTableModel model = this.getHeaderTableModel();
        
        for(ModelInterface item : items) {
            Produto prod = (Produto) item;
            Object[] data = {
                prod.getCodigo(),
                String.format("%s %s", prod.getNome(), prod.getDescricao()),
                prod.getCategoria(),
                prod.getFormatPrice()
            };

            model.addRow(data);
        }
        
        return model;
    }
    
    /**
     * Permite abastecer o estoque para um determinado produto a partir de um JTable
     * 
     * @param panel
     * @return boolean
     */
    public boolean supplyStockFrom(JPanelProdutos panel ){
        String codigo = panel.txtCodigo.getText();
        codigo = codigo.isEmpty() ? "0" : codigo ;
        
        if(panel.txtCodigo.getText().isEmpty()){
            Util.showMessage("Selecione um item para adicionar estoque.");
            return false;
        }
        
        String message = String.format("Digite a quantidade desejada para adicionar ao estoque do produto '%s'", panel.txtNome.getText()) ;
        String qtde = Util.showInput(message);
        if( qtde == null) return false ;
        
        return this.supplyStock(Long.parseLong(codigo), Integer.parseInt(qtde), panel.jLabelEstoque);
    }
    
    /**
     * Permite abastecer o estoque para um determinado produto
     * 
     * @param produtoID Código do produto
     * @param value     Valor a ser adicionado ao Estoque
     * @param lblNivel  Label de exibição de quantidade
     * 
     * @return boolean
     */
    public boolean supplyStock(Long produtoID, int value, JLabel lblNivel ){
        try {
            Produto p = (Produto) this.getObjModel().findBy(produtoID).get(0);
            p.addEstque(value);
            
            DecimalFormat df = new DecimalFormat("#,###") ;
            String qtde = df.format(p.getSaldoEstoque());
            String message = String.format("O saldo após a adição da quantidade informada será de %s.\nConfirma a operação?", qtde);
            if( Util.showConfirm(message, "Abastecimento de estoque") ) {
                p.save();
                lblNivel.setText(qtde);
                lblNivel.setForeground(Color.BLUE);
                
                if ( p.estoqueCritico() ) {
                    lblNivel.setForeground(Color.RED);
                }
                
                Util.showMessage("Estoque atualizado com sucesso.");
                return true;
            }
        } catch ( Exception ex) {
            System.out.println(ex.getMessage());
            Util.showMessage("Houve um problema na tentativa de adicionar o estoque.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
