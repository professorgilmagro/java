/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author gilmar
 */
public class Util {
    
    public static final String TITLE_DEFAULT = "Sistema de Vendas" ;

    public Util(){}
    
     /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     */
    public static void showMessage(String message) {
       Util.showMessage(message, Util.TITLE_DEFAULT, JOptionPane.INFORMATION_MESSAGE);
    }
    
     /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * @param type      Tipo (Informativa, Alerta, Erro)
     */
    public static void showMessage(String message, int type) {
        Util.showMessage(message, Util.TITLE_DEFAULT, type);
    }
    
    /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * @param title
     * @param type      Tipo (Informativa, Alerta, Erro)
     */
    public static void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message , title, type );
    }
    
    /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * 
     * @return String
     */
    public static String showInput(String message) {
        return Util.showInput(message, TITLE_DEFAULT);
    }
    
    /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * @param title     Título do dialog
     * 
     * @return String
     */
    public static String showInput(String message, String title) {
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Exibe uma mensagem de confirmação
     * 
     * @param message   Mensagem a ser exibida
     * @param title     Título
     * 
     * @return String
     */
    public static Boolean showConfirm(String message, String title) {
         int response = JOptionPane.showConfirmDialog(
                    JOptionPane.getRootFrame(), message , title,
                    JOptionPane.YES_NO_OPTION
            );
         
         return response == JOptionPane.YES_OPTION ;
    }
    
    /**
     * Exibe input com uma lista de opções para escolha
     * 
     * @param message   Mensagem a ser exibida
     * @param options   Opções
     * @param title     Título
     * 
     * @return String
     */
    public static Object showOptions(String message, Object[] options, String title){
        Object response = JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(), message , title,
                JOptionPane.PLAIN_MESSAGE, null, options, null
            );
        
        return response;
    }
    
     /**
     * Exibe input com uma lista de opções para escolha a partir de um objeto Map
     * 
     * @param message   Mensagem a ser exibida
     * @param options   Opções
     * @param title     Título
     * 
     * @return String
     */
    public static Object showOptions(String message, Map<String,String> options, String title){
        Object response = JOptionPane.showInputDialog(
            JOptionPane.getRootFrame(), message , title,
            JOptionPane.PLAIN_MESSAGE, null, options.values().toArray(), null
        );
        
        if(!response.toString().isEmpty()){
            for (Map.Entry<String, String> entrySet : options.entrySet()) {
                if(entrySet.getValue().equals(response.toString())){
                    return entrySet.getKey();
                }
            }
        }
        
        return response;
    }
    
     /**
     * Cria uma Frame padrão para exibição dos Panels
     * 
     * @param panel
     * @param parent
     * @param Title
     * 
     * @return JDialog
     */    
    public static JDialog getDefaultWindow( JPanel panel, JFrame parent, String Title ){            
        JDialog dialog = Util.getDefaultWindow(panel, Title);
        dialog.setSize(parent.getSize());
        dialog.setLocationRelativeTo(null);
        
        return dialog;
    }
    
     /**
     * Cria uma Frame padrão para exibição dos Panels
     * 
     * @param panel
     * @param Title
     * 
     * @return JDialog
     */    
    public static JDialog getDefaultWindow( JPanel panel, String Title ){            
        JDialog dialog = new JDialog();
        dialog.setTitle(Title);
        dialog.add(panel);
        dialog.setModal(true);
        
        return dialog;
    }
    
    /**
     * Converte uma string formatada em moeda corrente para um double
     * 
     * @param value
     * @return 
     */
    public static Double convertCurrencyToDouble(String value){
        return Util.convertCurrencyToDouble(value, "R$");
    }
    
    /**
     * Converte uma string formatada em moeda corrente para um double
     * 
     * @param value
     * @param simbol
     * @return 
     */
    public static Double convertCurrencyToDouble(String value, String simbol){
        return Double.parseDouble(value.replace(simbol, "").replace(",", ".").trim());
    }
    
    /**
     * Converte um valor em uma string formatada em moeda corrente
     * 
     * @param value
     * @return 
     */
    public static String convertDoubleToCurrency(Double value){
        return Util.convertDoubleToCurrency(value, "R$");
    }
    
    /**
     * Converte um valor em uma string formatada em moeda corrente
     * 
     * @param value
     * @param simbol
     * @return 
     */
    public static String convertDoubleToCurrency(Double value, String simbol){
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("%s %s", simbol, df.format(value));         
    }
    
    /**
     * Verifica se um dado valor é númerico
     * 
     * @param value
     * @return 
     */
    public static boolean isNumeric(String value) {
        return value.matches("\\d+");
    }

}
