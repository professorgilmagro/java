/*
 * Tela principal do Sistema
 */
package estoque.forms;

import javax.swing.*;

/**
 * Tela principal do sistema
 * 
 * @author gilmar
 */
public class MainScreen extends javax.swing.JFrame {
    private jPanelProdutos panel;
    private JFrame frame;

    /**
     * Creates new form tela_principal
     */
    public MainScreen() {
        initComponents();
        this.resetScreen();
    }
    
    /**
     * Inicializa as propriedades da tela com valores pré-determinados
     */
    protected void resetScreen(){
        // Redimensiona a tela para o tamanho 800x600
        this.setSize(800,600);
    }

    /**
     * Inicializa os compontentes gráficos do sistema
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelScreen = new javax.swing.JPanel();
        jLabelDisciplina = new javax.swing.JLabel();
        jLabelUnidade = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelOrientador = new javax.swing.JLabel();
        jLabelCriadoPor = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuVendas = new javax.swing.JMenu();
        jMenuProdutos = new javax.swing.JMenu();
        jMenuProdutoCadastro = new javax.swing.JMenuItem();
        jMenuClientes = new javax.swing.JMenu();
        jMenuClienteCadastro = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AIEC - Atividade Módulo 2");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(800, 600));

        jPanelScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelDisciplina.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabelDisciplina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDisciplina.setText("Programação Orientada a Objetos");
        jPanelScreen.add(jLabelDisciplina, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabelUnidade.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelUnidade.setText("Unidade 2");
        jPanelScreen.add(jLabelUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estoque/forms/logo-aiec.png"))); // NOI18N
        jPanelScreen.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, 66));

        jLabelOrientador.setText("Orientador: Andrei Cardoso Vanderlei");
        jPanelScreen.add(jLabelOrientador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jLabelCriadoPor.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jLabelCriadoPor.setText("Criado por: Gilmar Soares");
        jPanelScreen.add(jLabelCriadoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        jLabelDescricao.setBackground(new java.awt.Color(46, 40, 90));
        jLabelDescricao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelDescricao.setForeground(new java.awt.Color(80, 75, 178));
        jLabelDescricao.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelDescricao.setText("Cadastro de Vendas");
        jPanelScreen.add(jLabelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jMenuVendas.setText("Vendas");
        jMenuBarMain.add(jMenuVendas);

        jMenuProdutos.setText("Produtos");
        jMenuProdutos.setToolTipText("");

        jMenuProdutoCadastro.setText("Cadastro");
        jMenuProdutoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuProdutoCadastroActionPerformed(evt);
            }
        });
        jMenuProdutos.add(jMenuProdutoCadastro);

        jMenuBarMain.add(jMenuProdutos);

        jMenuClientes.setText("Clientes");

        jMenuClienteCadastro.setText("Cadastrar");
        jMenuClienteCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuClienteCadastroMouseReleased(evt);
            }
        });
        jMenuClientes.add(jMenuClienteCadastro);

        jMenuBarMain.add(jMenuClientes);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
           
    /**
     * Exibe a tela de cadastro de produtos
     * 
     * @param evt 
     */
    private void jMenuProdutoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProdutoCadastroActionPerformed
        JPanel panel = new jPanelProdutos();
        JFrame frame = this.getDefaultFrame(panel);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuProdutoCadastroActionPerformed
    
     /**
     * Exibe a tela de cadastro de Clientes
     * 
     * @param evt 
     */
    private void jMenuClienteCadastroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClienteCadastroMouseReleased
       JPanel panel = new jPanelClientes();
       JFrame frame = this.getDefaultFrame(panel);
       frame.setVisible(true);
    }//GEN-LAST:event_jMenuClienteCadastroMouseReleased

    /**
     * Start do Sistema
     * 
     * @param args
     */
    public static void main(String args[]) {
        // Define o tema
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Cria e exibe o formulário principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }
    
    /**
     * Cria uma Frame padrão para exibição dos Panels
     * 
     * @param panel
     * 
     * @return JFrame
     */    
    public JFrame getDefaultFrame( JPanel panel ){            
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(this.getSize());
        frame.setLocation(this.getLocation());
        
        return frame;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCriadoPor;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelOrientador;
    private javax.swing.JLabel jLabelUnidade;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenuItem jMenuClienteCadastro;
    private javax.swing.JMenu jMenuClientes;
    private javax.swing.JMenuItem jMenuProdutoCadastro;
    private javax.swing.JMenu jMenuProdutos;
    private javax.swing.JMenu jMenuVendas;
    private javax.swing.JPanel jPanelScreen;
    // End of variables declaration//GEN-END:variables
}
