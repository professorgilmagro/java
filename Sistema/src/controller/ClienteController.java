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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Util;
import view.MainScreen;
import view.JPanelClientes;

/**
 * Controller para Manutenção de Categorias de Produtos
 * 
 * @author gilmar
 */
public class ClienteController extends GenericController{
    
    /**
     * Ao ser instanciado, define o Model padrão para o controlador
     */
    public ClienteController() {
        Cliente cli = new Cliente();
        super.setModel(cli);
    }
    
    /**
     * Factory para facilitar a criação do controlador
     * 
     * @return ClienteController
     */
    public static ClienteController make(){
        ClienteController cc = new ClienteController();
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
       JPanel panel = new JPanelClientes();
       JDialog window = Util.getDefaultWindow(panel, mainFrame, "Clientes");
       window.setSize(790, 600);
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
        model.addColumn("Nome");
        model.addColumn("Telefone");
        model.addColumn("CPF");
        
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
    public DefaultTableModel getTableModel(List items){
        DefaultTableModel model = this.getHeaderTableModel();
        for(Object item : items) {
            Cliente cli = (Cliente) item ;
            Object[] data = {
                cli.getCodigo(),
                cli.getFullName(),
                cli.getTelefone(),
                cli.getFormatCPF(),
            };
            
            model.addRow(data);
        }
        
        return model;
    }
       
    /**
     * Permite fazer a busca do objeto a partir do código ou nome informado
     * 
     * @return ModelInterface
     */
    @Override
    public List<ModelInterface> search() {
        List <ModelInterface> items = new ArrayList();
        
        String[] options = {"Código", "Nome", "CPF", "E-mail"};
        Object option = Util.showOptions("Selecione o tipo de pesquisa.", options, "Selecione o tipo de pesquisa");
        if( option == null ) return items;
        
        String search = Util.showInput(String.format("Entre com o %s a ser procurado", option.toString()));
        if( option == null ) return items;
        
        Cliente cli = (Cliente) this.getObjModel();
        if(option.equals("CPF")){
            search = Util.onlyNumber(search);
            return cli.findByCPF(Long.parseLong(search));
        }
        
        if(option.equals("E-mail")){
            return cli.findByEmail(search);
        }
        
        if(option.equals("Nome")){
            return cli.findBy(search);
        }
        
        if ( Util.isNumeric(search) ) {
           return cli.findBy(Long.parseLong(search));
        }
        
        this.getObjModel().setID(0);
        items.add(this.getObjModel());
       
       return items;
    }
    
    /**
     * Rertona a coleção de clientes ordernados
     * 
     * @param asc
     * @return List
     */
    public List<Cliente> fetchSortedItems(boolean asc){
        List<Cliente> clientes = new ArrayList<>();
        try {
           List<ModelInterface> items = this.getObjModel().fetchAll();
            for(ModelInterface item : items) {
                clientes.add((Cliente)item);
            }
        } catch (IOException ex) {
            Logger.getLogger(JPanelClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(asc) Collections.sort(clientes);
        if(!asc) Collections.sort(clientes, Collections.reverseOrder());
        
        return clientes;
    }
}
