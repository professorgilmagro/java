/*
 * Atividade para colocar em prática os conceitos sobre listas, ordenamento, 
 * pesquisa e uso de arquivos em Java.
 */
package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cliente
 * 
 * @author gilmar <gilmar.santos@grupofolha.com.br>
 */
public class Cliente extends GenericModel{
    
    private long codigo;
    private String nome, Sobrenome ;
    private Date dataNascimento;
    private String sexo;
    private Long cpf;
    private Long cep;
    private String telefone;
    private String logadouro;
    private String estado;
    private String cidade;
    private String bairro;
    private String email;  
    private int numero ;
    
    private final String SEXO_M = "M";
    private final String SEXO_F = "F";
        
    public Cliente(){}

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
        final Cliente other = (Cliente) obj;
        return this.codigo == other.codigo;
    }

    /**
     * Facilita a criação de um novo cliente
     * 
     * @param Nome          
     * @param Sobrenome
     * @param cpf
     * @param cep
     */
    public Cliente(String Nome, String Sobrenome, Long cpf, Long cep ){
        this.nome = Nome;
        this.Sobrenome = Sobrenome;
        this.cpf = cpf;
        this.cep = cep;
    }
    
    @Override
    public String toString() {
        return this.getFullName();
    }
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long Codigo) {
        this.codigo = Codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }
    
    public String getFullName(){
        return String.format("%s %s", this.nome, this.Sobrenome);
    }
    
    public String getEndereco(){
        return String.format( 
            "%s, %d, %s, %s - %s", this.getLogadouro(), this.getNumero(), 
            this.getBairro(), this.getCidade(), this.getEstado()
        );
    }
 
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date DataNascimento) {
        this.dataNascimento = DataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String Sexo) {
        this.sexo = Sexo;
    }

    public Long getCPF() {
        return cpf;
    }

    public void setCPF(Long cpf) {
        this.cpf = cpf;
    }

    public Long getCEP() {
        return cep;
    }

    public void setCEP(Long cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String Telefone) {
        this.telefone = Telefone;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String Logadouro) {
        this.logadouro = Logadouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String Estado) {
        this.estado = Estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String Cidade) {
        this.cidade = Cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String Bairro) {
        this.bairro = Bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int Numero) {
        this.numero = Numero;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }
   
    @Override
    public void setID(long ID) {
        this.setCodigo(ID);
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
    
    /**
     * Permite localizar um objeto a partir do CPF
     * 
     * @param cpf    CPF a ser localizado
     * 
     * @return ModelInterface
     */
    public Cliente findByCPF(long cpf) {
        try {
            List objects = this.fetchAll();
            
            for (Object object : objects) {
                Cliente model = (Cliente) object;
                if(model.getCPF() == cpf) {
                    return model ;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setID(0);
        return this ;
    }
    
    /**
     * Permite localizar um objeto a partir do E-mail
     * 
     * @param email    E-mail a ser localizado
     * 
     * @return ModelInterface
     */
    public Cliente findByEmail(String email) {
        try {
            List objects = this.fetchAll();
            
            for (Object object : objects) {
                Cliente model = (Cliente) object;
                if(model.getEmail().toLowerCase().equals(email.toLowerCase())) {
                    return model ;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setID(0);
        return this ;
    }
}
