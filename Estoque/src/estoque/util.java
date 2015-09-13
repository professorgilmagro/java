/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author gilmar
 */
public class util {

    public util(){}
    
    /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * @param type      Tipo (Informativa, Alerta, Erro)
     */
    public static void showMessage(String message, int type) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message , null, type );
    }
    
    public static void showMessage(String message) {
       util.showMessage(message, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Exibe uma mensagem do tipo input na tela
     * 
     * @param message   Mensagem a ser exibida
     * 
     * @return String
     */
    public static String showInput(String message) {
        return (String) JOptionPane.showInputDialog(message);
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
}
