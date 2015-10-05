/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Vendas
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Venda extends GenericModel{
    
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
        return (Cliente) cli.findBy(this.getClienteID());
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
            Produto p = (Produto) prod.findBy(produtoID);
            
            int estoque = p.getSaldoEstoque();
            p.setSaldoEstoque(estoque - qtde);
            p.save();
        }
    }
} 
