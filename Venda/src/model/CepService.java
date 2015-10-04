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
    
    private String Logradouro;
    private String Bairro;
    private String Cidade;
    private String UF;
    private int status = 0;
    
    /** 
     * Construtor da classe
     * 
     * @param cep 
     */
    public CepService(String cep) {
        try {
            URL url = new URL(String.format("http://apps.widenet.com.br/busca-cep/api/cep/%s.xml",cep));
            Document document = getDocumento(url);
            Element root = document.getRootElement();

            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                
                if (element.getQualifiedName().equals("state")){
                    this.setEstado(element.getText());                
                }
                
                if (element.getQualifiedName().equals("city")){
                    this.setCidade(element.getText());                
                }
                
                if (element.getQualifiedName().equals("district")){
                    this.setBairro(element.getText());                
                }
                
                if (element.getQualifiedName().equals("address")){
                    this.setLogradouro(element.getText());
                }
                
                if (element.getQualifiedName().equals("status")){
                    this.setStatus(Integer.parseInt(element.getText()));
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
    public int getStatus() {
        return status;
    }

    /**
     * Define o valor do Estado
     * 
     * @param UF
     */
    public void setUF(String UF) {
        this.UF = UF;
    }

    /**
     * Define o valor do Status do serviço
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }
}