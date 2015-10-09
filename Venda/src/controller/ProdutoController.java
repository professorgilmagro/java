/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Produto;
import model.Util;
import view.MainScreen;
import view.jPanelProdutos;

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
    public void displayView() {
        JFrame mainFrame = new MainScreen();
        JPanel panel = new jPanelProdutos();
        JDialog window = Util.getDefaultWindow(panel, mainFrame, "Produtos");
        window.setSize(930, 550);
        window.setLocation(mainFrame.getX() + 50, mainFrame.getY() + 100);
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
        model.addColumn("Categoria");
        model.addColumn("Peso");
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
                prod.getNome(),
                prod.getDescricao(),
                prod.getCategoria(),
                prod.getPeso(),
                prod.getFormatPrice()
            };

            model.addRow(data);
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
     * Permite abastecer o estoque para um determinado produto a partir de um JTable
     * 
     * @param table Tabela 
     * @param lblNivel  Label para exibição do nivel de estoque
     * @return boolean
     */
    public boolean supplyStockFromTable(JTable table, JLabel lblNivel ){
        int row = table.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para adicionar estoque.");
            return false;
        }
        
        long ID = (long) table.getModel().getValueAt(row, 0);
        String item = (String) table.getModel().getValueAt(row, 1);
        
        String message = String.format("Digite a quantidade desejada para adicionar ao estoque do produto '%s'", item ) ;
        String qtde = Util.showInput(message);
        if( qtde == null) return false ;
        
        return this.supplyStock(ID, Integer.parseInt(qtde), lblNivel);
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
            Produto p = (Produto) this.getObjModel().findBy(produtoID);
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
