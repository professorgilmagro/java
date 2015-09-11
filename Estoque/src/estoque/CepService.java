package estoque;

/*
 * WebService de busca de CEP - Republica Virtual
 */

import java.net.URL;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * WebService de busca de CEP - Republica Virtual
 * 
 * @author Gilmar Soares
 */
public final class CepService {
    
    private String UF;
    private String Cidade;
    private String Bairro;
    private String TipoLogradouro;
    private String Logradouro;
        
    private int status_code = 0;
    private String status_description;
    
    /** 
     * Construtor da classe
     * 
     * @param cep 
     */
    public CepService(String cep) {
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            Document document = getDocumento(url);
            Element root = document.getRootElement();

            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                
                if (element.getQualifiedName().equals("uf")){
                    this.setEstado(element.getText());                
                }
                
                if (element.getQualifiedName().equals("cidade")){
                    this.setCidade(element.getText());                
                }
                
                if (element.getQualifiedName().equals("bairro")){
                    this.setBairro(element.getText());                
                }
                
                if (element.getQualifiedName().equals("tipo_logradouro")){
                    this.setTipoLogradouro(element.getText());   
                }
                
                if (element.getQualifiedName().equals("logradouro")){
                    this.setLogradouro(element.getText());
                }
                
                if (element.getQualifiedName().equals("status_code")){
                    this.setResultado(Integer.parseInt(element.getText()));
                }
                
                if (element.getQualifiedName().equals("status_description")){
                    this.setStatusDescription(element.getText());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    /**
     * Lê a url do serviço
     * 
     * @param url
     * 
     * @return Document
     * @throws DocumentException
     */
    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    } 
    
    /**
     * Retorna o Estado
     * 
     * @return String
     */
    public String getEstado() {
        return UF;
    }

    /**
     * Atribui o valor do Estado
     * 
     * @param UF
     */
    public void setEstado(String UF) {
        this.UF = UF;
    }

    /**
     *
     * @return
     */
    public String getCidade() {
        return this.Cidade;
    }

    /**
     *
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.Cidade = cidade;
    }

    /**
     * Retorna o bairro
     * 
     * @return
     */
    public String getBairro() {
        return this.Bairro;
    }

    /**
     * Atribui o valor do Bairro
     * 
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.Bairro = bairro;
    }

    /**
     * Retorna o Tipo de Logradrouro
     * 
     * @return
     */
    public String getTipoLogradouro() {
        return this.TipoLogradouro;
    }

    /**
     * Atribui um valor para o tipo logradrouro
     * 
     * @param tipoLogradouro
     */
    public void setTipoLogradouro(String tipoLogradouro) {
        this.TipoLogradouro = tipoLogradouro;
    }

    /**
     * Recupera o Logradouro
     * 
     * @return
     */
    public String getLogradouro() {
        return this.Logradouro;
    }

    /**
     * Atribui o Logradouro
     * 
     * @param logradouro
     */
    public void setLogradouro(String logradouro) {
        this.Logradouro = logradouro;
    }

    /**
     * Retorna o código com o status de requisição do serviço
     * 
     * @return
     */
    public int getStatusCode() {
        return status_code;
    }

    /**
     * Atribui o código do status
     * 
     * @param status_code
     */
    public void setResultado(int status_code) {
        this.status_code = status_code;
    }

    /**
     * Retorna a Descrição de Status do serviço
     * 
     * @return
     */
    public String getStatusDescription() {
        return status_description;
    }

    /**
     * Atribui a descrição do status
     * 
     * @param status_description
     */ 
    public void setStatusDescription(String status_description) {
        this.status_description = status_description;
    }
}