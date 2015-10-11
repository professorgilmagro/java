/*
 * Tela principal do Sistema
 */
package view;

import controller.CategoriaController;
import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import model.Util;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

/**
 * Tela principal do sistema
 * 
 * @author gilmar
 */
public final class MainScreen extends javax.swing.JFrame {

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
        // Redimensiona a tela para o tamanho 1024x800
        this.setSize(1024,680);
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
        jLabelOrientador = new javax.swing.JLabel();
        jLabelCriadoPor = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenuVendas = new javax.swing.JMenu();
        jMenuAddVenda = new javax.swing.JMenuItem();
        jMenuProdutos = new javax.swing.JMenu();
        jMenuProdutoCadastro = new javax.swing.JMenuItem();
        jMenuProdCategoria = new javax.swing.JMenuItem();
        jMenuClientes = new javax.swing.JMenu();
        jMenuClienteCadastro = new javax.swing.JMenuItem();
        jMenuConfig = new javax.swing.JMenu();
        jMenuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AIEC - Atividade Unidade III");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(169, 177, 219));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(59, 45, 45));
        setPreferredSize(new java.awt.Dimension(800, 554));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanelScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelDisciplina.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabelDisciplina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDisciplina.setText("Programação Orientada a Objetos");
        jPanelScreen.add(jLabelDisciplina, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabelUnidade.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelUnidade.setText("Unidade III");
        jPanelScreen.add(jLabelUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        jLabelOrientador.setText("Orientador: Andrei Cardoso Vanderlei");
        jPanelScreen.add(jLabelOrientador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jLabelCriadoPor.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jLabelCriadoPor.setText("Criado por: Gilmar Soares");
        jPanelScreen.add(jLabelCriadoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        jLabelDescricao.setBackground(new java.awt.Color(46, 40, 90));
        jLabelDescricao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelDescricao.setForeground(new java.awt.Color(80, 75, 178));
        jLabelDescricao.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelDescricao.setText("Controle de Vendas");
        jPanelScreen.add(jLabelDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/logo-aiec.png"))); // NOI18N
        jPanelScreen.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, 66));

        jMenuBarMain.setBackground(new java.awt.Color(149, 190, 253));
        jMenuBarMain.setBorder(null);
        jMenuBarMain.setMargin(new java.awt.Insets(10, 20, 0, 0));

        jMenuVendas.setForeground(new java.awt.Color(254, 254, 254));
        jMenuVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-venda.png"))); // NOI18N
        jMenuVendas.setMnemonic('v');
        jMenuVendas.setText("Vendas");
        jMenuVendas.setToolTipText("Gerir vendas, pedidos e orçamentos");
        jMenuVendas.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuVendas.setMargin(new java.awt.Insets(0, 0, 0, 10));

        jMenuAddVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/order.png"))); // NOI18N
        jMenuAddVenda.setMnemonic('p');
        jMenuAddVenda.setText("Pedido");
        jMenuAddVenda.setToolTipText("");
        jMenuAddVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddVendaActionPerformed(evt);
            }
        });
        jMenuVendas.add(jMenuAddVenda);

        jMenuBarMain.add(jMenuVendas);

        jMenuProdutos.setForeground(new java.awt.Color(254, 254, 254));
        jMenuProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-produto.png"))); // NOI18N
        jMenuProdutos.setMnemonic('p');
        jMenuProdutos.setText("Produtos");
        jMenuProdutos.setToolTipText("Gerir produtos");
        jMenuProdutos.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuProdutos.setMargin(new java.awt.Insets(0, 0, 0, 25));

        jMenuProdutoCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/product-icon.png"))); // NOI18N
        jMenuProdutoCadastro.setMnemonic('c');
        jMenuProdutoCadastro.setText("Cadastro");
        jMenuProdutoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuProdutoCadastroActionPerformed(evt);
            }
        });
        jMenuProdutos.add(jMenuProdutoCadastro);

        jMenuProdCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/category-item-icon.png"))); // NOI18N
        jMenuProdCategoria.setMnemonic('a');
        jMenuProdCategoria.setText("Categoria");
        jMenuProdCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuProdCategoriaActionPerformed(evt);
            }
        });
        jMenuProdutos.add(jMenuProdCategoria);

        jMenuBarMain.add(jMenuProdutos);

        jMenuClientes.setForeground(new java.awt.Color(254, 254, 254));
        jMenuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-cliente.png"))); // NOI18N
        jMenuClientes.setMnemonic('c');
        jMenuClientes.setText("Clientes");
        jMenuClientes.setToolTipText("Gerir clientes");
        jMenuClientes.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuClientes.setMargin(new java.awt.Insets(0, 0, 0, 25));

        jMenuClienteCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/client-icon.png"))); // NOI18N
        jMenuClienteCadastro.setMnemonic('c');
        jMenuClienteCadastro.setText("Cadastrar");
        jMenuClienteCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuClienteCadastroMouseReleased(evt);
            }
        });
        jMenuClientes.add(jMenuClienteCadastro);

        jMenuBarMain.add(jMenuClientes);

        jMenuConfig.setForeground(new java.awt.Color(254, 254, 254));
        jMenuConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-config.png"))); // NOI18N
        jMenuConfig.setMnemonic('o');
        jMenuConfig.setText("Configurações");
        jMenuConfig.setToolTipText("Backups");
        jMenuConfig.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuConfig.setMargin(new java.awt.Insets(0, 0, 0, 25));
        jMenuBarMain.add(jMenuConfig);

        jMenuSair.setBackground(new java.awt.Color(0, 161, 206));
        jMenuSair.setForeground(new java.awt.Color(254, 254, 254));
        jMenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-sair.png"))); // NOI18N
        jMenuSair.setMnemonic('s');
        jMenuSair.setToolTipText("Sair do Sistema");
        jMenuSair.setContentAreaFilled(false);
        jMenuSair.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuSair.setLabel("Sair");
        jMenuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSairMouseClicked(evt);
            }
        });
        jMenuBarMain.add(jMenuSair);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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
        ProdutoController.make().displayView();
    }//GEN-LAST:event_jMenuProdutoCadastroActionPerformed
    
     /**
     * Exibe a tela de cadastro de Clientes
     * 
     * @param evt 
     */
    private void jMenuClienteCadastroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClienteCadastroMouseReleased
       ClienteController.make().displayView();
    }//GEN-LAST:event_jMenuClienteCadastroMouseReleased

    private void jMenuProdCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProdCategoriaActionPerformed
       CategoriaController.make().displayView();
    }//GEN-LAST:event_jMenuProdCategoriaActionPerformed

    private void jMenuAddVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddVendaActionPerformed
        VendaController.make().displayView();
    }//GEN-LAST:event_jMenuAddVendaActionPerformed

    private void jMenuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSairMouseClicked
         this.dispose();
         System.exit(0);
    }//GEN-LAST:event_jMenuSairMouseClicked
    
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCriadoPor;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelOrientador;
    private javax.swing.JLabel jLabelUnidade;
    private javax.swing.JMenuItem jMenuAddVenda;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenuItem jMenuClienteCadastro;
    private javax.swing.JMenu jMenuClientes;
    private javax.swing.JMenu jMenuConfig;
    private javax.swing.JMenuItem jMenuProdCategoria;
    private javax.swing.JMenuItem jMenuProdutoCadastro;
    private javax.swing.JMenu jMenuProdutos;
    private javax.swing.JMenu jMenuSair;
    private javax.swing.JMenu jMenuVendas;
    private javax.swing.JPanel jPanelScreen;
    // End of variables declaration//GEN-END:variables
}
