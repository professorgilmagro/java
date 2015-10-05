package model;

/*
 * WebService de busca de CEP - WideNet
 */

import java.net.URL;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * WebService de busca de CEP - WideNet
 * 
 * @author Gilmar Soares
 */
public final class CepService {
    
    private final String URL_FORMAT = "http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=xml" ;
    
    private String logradouro;
    private String tipoLogradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String status;
    
    /** 
     * Construtor da classe
     * 
     * @param cep 
     */
    public CepService(String cep) {
        try {
            URL url = new URL(String.format(URL_FORMAT, cep));
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
                
                if (element.getQualifiedName().equals("resultado_txt")){
                    this.setStatus(element.getText());
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
        return uf;
    }

    /**
     * Atribui o valor do Estado
     * 
     * @param uf
     */
    public void setEstado(String uf) {
        this.uf = uf;
    }

    /**
     *
     * @return
     */
    public String getCidade() {
        return this.cidade;
    }

    /**
     *
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Retorna o bairro
     * 
     * @return
     */
    public String getBairro() {
        return this.bairro;
    }

    /**
     * Atribui o valor do Bairro
     * 
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Recupera o Logradouro
     * 
     * @return
     */
    public String getLogradouro() {
        return String.format("%s %s", this.logradouro , this.getTipoLogradouro());
    }

    /**
     * Atribui o Logradouro
     * 
     * @param logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Retorna o código com o status de requisição do serviço
     * 
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o valor do Estado
     * 
     * @param uf
     */
    public void setUF(String uf) {
        this.uf = uf;
    }

    /**
     * Define o valor do Status do serviço
     * 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    protected String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}