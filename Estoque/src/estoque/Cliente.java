/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package estoque;

import java.util.Date;
import estoque.forms.MainScreen;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.TableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

/**
 * Cliente
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Cliente extends AbstractModel{
    
    private long Codigo;
    private String Nome, Sobrenome ;
    private Date DataNascimento;
    private String Sexo;
    private Long CPF;
    private Long CEP;
    private String Telefone;
    private String Logadouro;
    private String Estado;
    private String Cidade;
    private String Bairro;
    private String Email;  
    private int Numero ;
    
    private final String SEXO_M = "M";
    private final String SEXO_F = "F";
        
    public Cliente(){}
    
    /**
     * Facilita a criação de um novo cliente
     * 
     * @param Nome          
     * @param Sobrenome
     * @param CPF
     * @param CEP
     */
    public Cliente(String Nome, String Sobrenome, Long CPF, Long CEP ){
        this.Nome = Nome;
        this.Sobrenome = Sobrenome;
        this.CPF = CPF;
        this.CEP = CEP;
    }
    
    @Override
    public String toString() {
        return this.getFullName();
    }
    
    public long getCodigo() {
        return Codigo;
    }

    public void setCodigo(long Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getFullName(){
        return String.format("%s %s", this.Nome, this.Sobrenome);
    }
    
    public String getEndereco(){
        return String.format( 
            "%s, %d, %s, %s - %s", this.getLogadouro(), this.getNumero(), 
            this.getBairro(), this.getCidade(), this.getEstado()
        );
    }
 
    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public Long getCEP() {
        return CEP;
    }

    public void setCEP(Long CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getLogadouro() {
        return Logadouro;
    }

    public void setLogadouro(String Logadouro) {
        this.Logadouro = Logadouro;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    @Override
    public long getID() {
        return this.getCodigo();
    }
    
    @Override
    public void setID(long ID) {
        this.setCodigo(ID);
    }
    
    @Override
    public String getFileName() {
        File file = new java.io.File("");
        return String.format("%sclientes.dat" , this.getStorageDir()) ;
    }
    
    @Override
    public TableModel getTableModel(){
        DefaultTableModel model = new DefaultTableModel();
                     
        model.addColumn("Código");
        model.addColumn("Nome");
        model.addColumn("Nascimento");
        model.addColumn("Telefone");
        model.addColumn("Email");
        
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            List clientes = this.getAll();
            for (Object item : clientes) {
                Cliente cli = (Cliente) item;
                Object[] data = {
                    cli.getID(),
                    cli.getFullName(),
                    dt.format(cli.getDataNascimento()),
                    cli.getTelefone(),
                    cli.getEmail(),
                };
                
                model.addRow(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
   
    /**
     * Preenche o objeto a partir do webservice
     * 
     * @param cws 
     */
    public void fillFromService(CepService cws){
        this.setLogadouro(cws.getLogradouro());
        this.setBairro(cws.getBairro());
        this.setCidade(cws.getCidade());
        this.setEstado(cws.getEstado());
    }
    
    /**
     * Retorna o CPF formatado
     * 
     * @return String
     */
    public String getFormatCPF() {  
        String cpf = String.format("%011d", this.getCPF());
        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");  
        Matcher matcher = pattern.matcher(cpf);  
        if (matcher.matches()){   
            cpf = matcher.replaceAll("$1.$2.$3-$4");
        }
        
        return cpf;       
    }
    
     /**
     * Retorna o Sexo formatado
     * 
     * @return String
     */
    public String getFormatSexo() {
        if(this.getSexo().equals(SEXO_F)){
            return "Feminino";
        }
        
        if(this.getSexo().equals(SEXO_M)){
            return "Masculino";
        }
        
        return this.getSexo();
    }
    
    /**
     * Retorna um map com as opções de sexo
     * 
     * @return Map
     */
    public Map getMapSexo(){
        Map<String, String> map = new HashMap<>();
        map.put(SEXO_M, "Masculino");
        map.put(SEXO_F, "Feminino");
        
        return map;
    }
}
