/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoque;

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
     * 
     * @return String
     */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog( JOptionPane.getRootFrame(), message );
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
     * @param message   Mensagem a s'er exibida
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
    
}
