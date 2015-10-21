/*
 * Tela principal do Sistema
 */
package view;

import controller.CategoriaController;
import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

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
        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
        getContentPane().setBackground(Color.decode("#fdb242"));
        this.setLocationRelativeTo(null);
        
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.jPanelScreen.getSize().width;
        int h = this.jPanelScreen.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        
        System.out.println(x);
        System.out.println(y);

        // Move the window
        this.jPanelScreen.setAlignmentX(x);
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
        jLabelCriadoPor = new javax.swing.JLabel();
        jLabelOrientador = new javax.swing.JLabel();
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
        setTitle("AIEC - Atividade Unidade IV");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 242, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(59, 45, 45));
        setPreferredSize(new java.awt.Dimension(800, 554));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        jPanelScreen.setBackground(new java.awt.Color(253, 178, 66));
        jPanelScreen.setBorder(null);
        jPanelScreen.setForeground(new java.awt.Color(109, 128, 231));

        jLabelDisciplina.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabelDisciplina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDisciplina.setText("Programação Orientada a Objetos");

        jLabelUnidade.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelUnidade.setText("Unidade IV");

        jLabelCriadoPor.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jLabelCriadoPor.setText("Criado por: Gilmar Soares");

        jLabelOrientador.setText("Orientador: Andrei Cardoso Vanderlei");

        jLabelDescricao.setBackground(new java.awt.Color(46, 40, 90));
        jLabelDescricao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelDescricao.setForeground(new java.awt.Color(80, 75, 178));
        jLabelDescricao.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelDescricao.setText("Controle de Vendas");

        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/logo.png"))); // NOI18N
        jLabelLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelScreenLayout = new javax.swing.GroupLayout(jPanelScreen);
        jPanelScreen.setLayout(jPanelScreenLayout);
        jPanelScreenLayout.setHorizontalGroup(
            jPanelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScreenLayout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addGroup(jPanelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScreenLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabelCriadoPor))
                    .addGroup(jPanelScreenLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabelUnidade))
                    .addComponent(jLabelDisciplina)
                    .addGroup(jPanelScreenLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDescricao)
                            .addComponent(jLabelLogo)))
                    .addGroup(jPanelScreenLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabelOrientador)))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanelScreenLayout.setVerticalGroup(
            jPanelScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelScreenLayout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jLabelLogo)
                .addGap(4, 4, 4)
                .addComponent(jLabelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDisciplina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUnidade)
                .addGap(18, 18, 18)
                .addComponent(jLabelCriadoPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelOrientador)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        jMenuBarMain.setBackground(new java.awt.Color(149, 190, 253));
        jMenuBarMain.setBorder(null);
        jMenuBarMain.setMargin(new java.awt.Insets(10, 20, 0, 0));

        jMenuVendas.setForeground(new java.awt.Color(17, 17, 17));
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

        jMenuProdutos.setForeground(new java.awt.Color(17, 17, 17));
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

        jMenuClientes.setForeground(new java.awt.Color(17, 17, 17));
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

        jMenuConfig.setForeground(new java.awt.Color(17, 17, 17));
        jMenuConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-config.png"))); // NOI18N
        jMenuConfig.setMnemonic('o');
        jMenuConfig.setText("Configurações");
        jMenuConfig.setToolTipText("Backups");
        jMenuConfig.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jMenuConfig.setMargin(new java.awt.Insets(0, 0, 0, 25));
        jMenuBarMain.add(jMenuConfig);

        jMenuSair.setBackground(new java.awt.Color(214, 214, 214));
        jMenuSair.setForeground(new java.awt.Color(17, 17, 17));
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
                .addGap(154, 154, 154)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(174, 174, 174))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanelScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
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
        ProdutoController.make().showView();
    }//GEN-LAST:event_jMenuProdutoCadastroActionPerformed
    
     /**
     * Exibe a tela de cadastro de Clientes
     * 
     * @param evt 
     */
    private void jMenuClienteCadastroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClienteCadastroMouseReleased
       ClienteController.make().showView();
    }//GEN-LAST:event_jMenuClienteCadastroMouseReleased

    private void jMenuProdCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProdCategoriaActionPerformed
       CategoriaController.make().showView();
    }//GEN-LAST:event_jMenuProdCategoriaActionPerformed

    private void jMenuAddVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddVendaActionPerformed
        VendaController.make().showView();
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
