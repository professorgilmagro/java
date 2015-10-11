/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Vendas
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Venda extends GenericModel{
    
    private static final long serialVersionUID = 4125965356358329466L;
    
    private long codigo;
    private long clienteID = 0;
    private String enderecoEntrega;
    private Date dataVenda;
    private List itens;
    private String obs;
    private Double descontos;
    private Double valorFrete;
    
    public Venda(){}
       
    @Override
    public String toString() {
        return String.format("%s - compra realizada em %s", this.getCliente() , this.getDataVenda());
    }

    @Override
    public int hashCode() {
       return (int) this.getCodigo();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Retorna a instância do cliente de relação com o objeto
     * 
     * @return Cliente
     */
    public Cliente getCliente(){
        Cliente cli = new Cliente();
        return (Cliente) cli.findBy(this.getClienteID()).get(0);
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
    
    public String getFormatTotal(){
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("R$ %s", df.format(this.getTotal()));
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
    
    /**
     * Retorna o valor total do pedido
     * 
     * @return 
     */
    public Double getTotal(){
        Double total = 0.0 ;
        
        for (Iterator iterator = this.getItens().iterator(); iterator.hasNext();) {
            Object[] row = (Object[]) iterator.next();
            total += Util.convertCurrencyToDouble(row[5].toString());
        }
        
        return total + this.getValorFrete() - this.getDescontos() ;
    }
    
    /**
     * Sobrescrito para implementação da baixa de estoque
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    @Override
    public void save() throws IOException, FileNotFoundException, ClassNotFoundException{
        super.save();
        this._baixaEstoque();
    }
    
     /**
     * Efetua a baixa no estoque para os produtos vendidos
     */
    private void _baixaEstoque() throws IOException, FileNotFoundException, ClassNotFoundException{
        
        for (Iterator iterator = this.getItens().iterator(); iterator.hasNext();) {
            Object[] row = (Object[]) iterator.next();
            Long produtoID = Long.parseLong(row[0].toString());
            int qtde = Integer.parseInt(row[4].toString());
            
            Produto prod = new Produto();
            Produto p = (Produto) prod.findBy(produtoID).get(0);
            
            int estoque = p.getSaldoEstoque();
            p.setSaldoEstoque(estoque - qtde);
            p.save();
        }
    }
    
    /**
     * Permite localizar objetos de venda a partir do CPF do cliente
     * 
     * @param cpf    CPF a ser localizado
     * 
     * @return List
     */
    public List <dao.ModelInterface> findByClientCPF(long cpf) {
        List <dao.ModelInterface> results = new ArrayList();
        
        try {
            List<dao.ModelInterface> items = this.fetchAll();
            
            for (dao.ModelInterface item : items) {
                Venda model = (Venda) item;
                if(model.getCliente().getCPF() == cpf) {
                    results.add(model);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results ;
    }
    
    /**
     * Permite localizar objetos de venda a partir do E-mail do cliente
     * 
     * @param email    E-mail a ser localizado
     * 
     * @return List
     */
    public List <dao.ModelInterface> findByClientEmail(String email) {
        List <dao.ModelInterface> results = new ArrayList();
        
        try {
           List<dao.ModelInterface> items = this.fetchAll();
            
            for(dao.ModelInterface item : items) {
                Venda model = (Venda) item;
                if(model.getCliente().getEmail().toLowerCase().equals(email.toLowerCase())) {
                    results.add(model);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results ;    
    }
    
    /**
     * Permite localizar objetos de venda a partir do Nome do cliente
     * 
     * @param name    Nome do cliente a ser localizado
     * 
     * @return List
     */
    public List <dao.ModelInterface> findByClientName(String name) {
        List <dao.ModelInterface> results = new ArrayList();
        
        try {
           List<dao.ModelInterface> items = this.fetchAll();
            
            for(dao.ModelInterface item : items) {
                Venda model = (Venda) item;
                if(model.getCliente().getNome().toLowerCase().equals(name.toLowerCase())) {
                    results.add(model);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results ;    
    }
    
    /**
     * Permite localizar objetos de venda a partir da data de venda
     * 
     * @param date    Data da venda
     * 
     * @return List
     */
    public List <dao.ModelInterface> findBySaleDate(String date) {
        List <dao.ModelInterface> results = new ArrayList();
        
        try {
           List<dao.ModelInterface> items = this.fetchAll();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
            Date orderDate = (Date) df.parse(date);
            for(dao.ModelInterface item : items) {
                Venda model = (Venda) item;
                if(df.format(model.getDataVenda()).equals(df.format(orderDate))) {
                    results.add(model);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(results.isEmpty()){
            this.setID(0);
            results.add(this);
        }
        
        return results ;    
    }
} 
