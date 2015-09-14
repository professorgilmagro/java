/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package estoque;

import estoque.forms.MainScreen;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * Vendas
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Venda extends AbstractModel{
    
    private long codigo;
    private long clienteID;
    private String enderecoEntrega;
    private String descricao;
    private Date dataVenda;
    private List itens;
    private String obs;
    private Double descontos;
    private Double valorFrete;
    
    public Venda(){}
       
    @Override
    public String toString() {
        return String.format("%d - %s", this.getID(), this.getCliente());
    }
    
    /**
     * Retorna a instância do cliente de relação com o objeto
     * 
     * @return Cliente
     */
    public Cliente getCliente(){
        Cliente cli = new Cliente();
        try {
            return (Cliente) cli.findByID(this.getClienteID());
        } catch (IOException ex) {
            return cli;
        }
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    public long getCodigo(){
        return this.codigo;
    }

    @Override
    public String getFileName() {
        return String.format("%svendas.dat" , this.getStorageDir()) ;
    }

    @Override
    public long getID() {
        return this.getCodigo();
    }
    
    @Override
    public void setID(long ID) {
        this.setCodigo(ID);
    }
    
    public long getClienteID() {
        return clienteID;
    }

    public void setClienteID(long clienteID) {
        this.clienteID = clienteID;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
        
    public String getFormatDataVenda(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date);
    }

    public List getItens() {
        return itens;
    }

    public void setItens(List itens) {
        this.itens = itens;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getDescontos() {
        return descontos;
    }

    public void setDescontos(Double descontos) {
        this.descontos = descontos;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }
    
     @Override
    public TableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Descrição");
        
        try {
            List produtos = this.getAll();
            for (Object item : produtos) {
                Venda prod = (Venda) item;
                Object[] data = {
                    prod.getCodigo(),
                    prod.getCliente(),
                    prod.getCodigo()
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
} 
