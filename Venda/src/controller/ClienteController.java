/*
 * Controlador da tela de categoria
 */
package controller;

import dao.ModelInterface;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.CepService;
import model.Cliente;
import model.Util;
import view.MainScreen;
import view.jPanelClientes;

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
    public void displayView() {
       JFrame mainFrame = new MainScreen();
       JPanel panel = new jPanelClientes();
       JDialog window = Util.getDefaultWindow(panel, mainFrame, "Clientes");
       window.setSize(800, 600);
       window.setLocation(mainFrame.getX() + 20, mainFrame.getY() + 50);
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
        model.addColumn("Nascimento");
        model.addColumn("Telefone");
        model.addColumn("Email");
        
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
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            List <ModelInterface> clientes = this.getObjModel().fetchAll() ;
            System.out.println(clientes.size());
            for (ModelInterface cliente : clientes) {
                Cliente cli = (Cliente) cliente ;
                Object[] data = {
                    cli.getCodigo(),
                    cli.getFullName(),
                    dt.format(cli.getDataNascimento()),
                    cli.getTelefone(),
                    cli.getEmail(),
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return model;
    }
    
    /**
     * Ação para adicionar novo cliente
     * 
     * @return boolean
     */
    public boolean create() {
        try {
            Cliente cli = new Cliente();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String nome = Util.showInput("Digite o nome."); 
            if( nome.isEmpty() ) return false;
            
            String sobrenome = Util.showInput("Digite o sobrenome.");
            if( nome.isEmpty() ) return false;
             
            Long cpf = Long.parseLong(Util.showInput("Digite o CPF."));
            if( nome.isEmpty() ) return false;
             
            String cep = Util.showInput("Digite o Código Postal.");
            if( nome.isEmpty() ) return false;
             
            Integer numero = Integer.parseInt(Util.showInput("Digite o número da residência."));
            CepService cws = new CepService(cep);
            cli.fillFromService(cws);
            cli.setCEP(Long.parseLong(cep));
            cli.setNumero(numero);

            String message = String.format( "O endereço abaixo está correto?\n%s", cli.getEndereco());
            if(Util.showConfirm(message, "Confirmação de endereço") == false){
                String logradouro = Util.showInput("Digite o logradouro.");
                String bairro = Util.showInput("Digite o bairro.");
                String cidade = Util.showInput("Digite a cidade.");
                String estado = Util.showInput("Digite o estado.");
                cli.setLogadouro(logradouro);
                cli.setBairro(bairro);
                cli.setCidade(cidade);
                cli.setEstado(estado);
            }
            
            String sexo = Util.showOptions("Selecione o sexo.", cli.getMapSexo(), "Sexo").toString();
            Date nascimento = formatter.parse(Util.showInput("Digite a data de Nascimento."));
            String email = Util.showInput("Digite o email.");
            
            String telefone = Util.showInput("Digite o telefone.");

            cli.setSexo(sexo);
            cli.setDataNascimento(nascimento);
            cli.setEmail(email);
            cli.setNome(nome);
            cli.setSobrenome(sobrenome);
            cli.setCPF(cpf);
            cli.setTelefone(telefone);

            cli.save();
            Util.showMessage("Cliente salvo com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }        

        return true;
    }
    
    /**
     * Permite fazer a busca do objeto a partir do código ou nome informado
     * 
     * @return ModelInterface
     */
    @Override
    public ModelInterface search() {
        ModelInterface objCli = this.getObjModel();
        objCli.setID(-1);
        
        String[] options = {"Código", "Nome", "CPF", "E-mail"};
        Object option = Util.showOptions("Selecione o tipo de pesquisa.", options, "Selecione o tipo de pesquisa");
        if( option == null ) return objCli;
        
        String search = Util.showInput(String.format("Entre com o %s a ser procurado", option.toString()));
        if( search == null ) return objCli;

        if(option.equals("CPF")){
            Cliente cli = (Cliente) this.getObjModel();
            search = search.replace(".", "").replace("-", "");
            return (ModelInterface) cli.findByCPF(Long.parseLong(search));
        }
        
        if(option.equals("E-mail")){
            Cliente cli = (Cliente) this.getObjModel();
            return (ModelInterface) cli.findByEmail(search);
        }
        
        if ( Util.isNumeric(search) ) {
            return this.getObjModel().findBy(Long.parseLong(search));
        }
       
       return this.getObjModel().findBy(search);
    }
    
}
