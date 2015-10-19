/*
 * Tela de produtos
 */
package view;

import controller.CategoriaController;
import controller.ProdutoController;
import dao.ModelInterface;
import model.Produto;
import model.Util;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Classe para controlar as funcionalidades de gerenciamento de proutos
 * 
 * @author gilmar
 */
public final class JPanelProdutos extends javax.swing.JPanel {
    
     /**
     * Recebe o controlador desta view
     */
    private ProdutoController controller ;

    /**
     * Inicializa e renderiza os objetos gráficos na tela
     */
    public JPanelProdutos() {
        this.controller = new ProdutoController();
        initComponents();
        this.loadItems();
    }
    
    /**
     * Carrega todos os itens de produto
     */
    public void loadItems(){
        TableModel model = this.controller.getTableModel();
        this.tableProdutos.setModel(model);
        this.tableProdutos.setAutoCreateRowSorter(true);
        this.tableProdutos.enableInputMethods(false);
        this.lblSource.setText(String.format("Dados extraidos do arquivo: %s", this.controller.getObjModel().getFileName()));
    }
    
    /**
     * Cadastra um novo produto
     */
    public void novoProduto() {
        while (true) {
            try {
                if(!this.controller.create()){
                    break;
                }
                
                this.loadItems();
                if ( Util.showConfirm("Gostaria de cadastrar mais um produto?", "Estoque") == false) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    /**
     * Inicializa os forms, objetos gráficos na tela.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        btnAddProduto = new javax.swing.JButton();
        btnDelProduto = new javax.swing.JButton();
        btnBuscaProduto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLoadFile = new javax.swing.JButton();
        btnAddEstoque = new javax.swing.JButton();
        jPanelDetalhes2 = new javax.swing.JPanel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelCategoria = new javax.swing.JLabel();
        jLabelEstoque = new javax.swing.JLabel();
        jLabelCategoria1 = new javax.swing.JLabel();
        btnAddCategoria = new javax.swing.JButton();
        lblSource = new javax.swing.JLabel();
        btnSaveToFile = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 600));

        tableProdutos.setAutoCreateRowSorter(true);
        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProdutos.setMaximumSize(new java.awt.Dimension(980, 200));
        tableProdutos.setName(""); // NOI18N
        tableProdutos.setPreferredSize(new java.awt.Dimension(980, 200));
        tableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableProdutosMouseReleased(evt);
            }
        });
        tableProdutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableProdutosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableProdutos);
        if (tableProdutos.getColumnModel().getColumnCount() > 0) {
            tableProdutos.getColumnModel().getColumn(0).setResizable(false);
            tableProdutos.getColumnModel().getColumn(1).setResizable(false);
            tableProdutos.getColumnModel().getColumn(2).setResizable(false);
            tableProdutos.getColumnModel().getColumn(3).setResizable(false);
        }

        btnAddProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/add2.png"))); // NOI18N
        btnAddProduto.setMnemonic('a');
        btnAddProduto.setText("Adicionar");
        btnAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProdutoActionPerformed(evt);
            }
        });

        btnDelProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/delete.png"))); // NOI18N
        btnDelProduto.setMnemonic('x');
        btnDelProduto.setText("Excluir");
        btnDelProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelProdutoActionPerformed(evt);
            }
        });

        btnBuscaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/search.png"))); // NOI18N
        btnBuscaProduto.setMnemonic('l');
        btnBuscaProduto.setText("Localizar");
        btnBuscaProduto.setToolTipText("");
        btnBuscaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBuscaProdutoMouseReleased(evt);
            }
        });
        btnBuscaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaProdutoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de produtos");

        btnLoadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/load-from-file.png"))); // NOI18N
        btnLoadFile.setToolTipText("");
        btnLoadFile.setLabel("Carregar arquivo...");
        btnLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFileActionPerformed(evt);
            }
        });

        btnAddEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/stock-icon.png"))); // NOI18N
        btnAddEstoque.setMnemonic('e');
        btnAddEstoque.setText("Abastecer Estoque");
        btnAddEstoque.setToolTipText("");
        btnAddEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEstoqueActionPerformed(evt);
            }
        });

        jPanelDetalhes2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelDescricao.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelDescricao.setText("Produto");

        jLabelPreco.setText("Valor:");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-produto.png"))); // NOI18N

        jLabelCategoria.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelCategoria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCategoria.setText("Sessão");

        jLabelEstoque.setFont(new java.awt.Font("Noto Sans", 1, 36)); // NOI18N
        jLabelEstoque.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelEstoque.setText("0");

        jLabelCategoria1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelCategoria1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCategoria1.setText("Saldo Estoque");

        javax.swing.GroupLayout jPanelDetalhes2Layout = new javax.swing.GroupLayout(jPanelDetalhes2);
        jPanelDetalhes2.setLayout(jPanelDetalhes2Layout);
        jPanelDetalhes2Layout.setHorizontalGroup(
            jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhes2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhes2Layout.createSequentialGroup()
                        .addComponent(jLabelCategoria1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhes2Layout.createSequentialGroup()
                        .addComponent(jLabelEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanelDetalhes2Layout.setVerticalGroup(
            jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhes2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetalhes2Layout.createSequentialGroup()
                        .addComponent(jLabelCategoria1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEstoque))
                    .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDetalhes2Layout.createSequentialGroup()
                            .addComponent(jLabelDescricao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelCategoria)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelPreco))
                        .addComponent(jLabelIcon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/category-item-icon.png"))); // NOI18N
        btnAddCategoria.setMnemonic('c');
        btnAddCategoria.setText("Categoria...");
        btnAddCategoria.setToolTipText("");
        btnAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoriaActionPerformed(evt);
            }
        });

        lblSource.setBackground(new java.awt.Color(248, 248, 193));
        lblSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/information-icon.png"))); // NOI18N
        lblSource.setText("Dados oriundos do arquivo:");
        lblSource.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 203, 111), 1, true));
        lblSource.setIconTextGap(5);
        lblSource.setOpaque(true);

        btnSaveToFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/save-on-file.png"))); // NOI18N
        btnSaveToFile.setMnemonic('s');
        btnSaveToFile.setText("Salvar em arquivo...");
        btnSaveToFile.setToolTipText("");
        btnSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveToFileActionPerformed(evt);
            }
        });

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/refresh-icon.gif"))); // NOI18N
        btnReload.setMnemonic('r');
        btnReload.setText("Recarregar");
        btnReload.setToolTipText("");
        btnReload.setAutoscrolls(true);
        btnReload.setEnabled(false);
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadFile))
                    .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAddProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelProduto)
                    .addComponent(btnBuscaProduto)
                    .addComponent(btnAddEstoque)
                    .addComponent(btnAddProduto)
                    .addComponent(btnAddCategoria)
                    .addComponent(btnReload)
                    .addComponent(btnSaveToFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSource, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadFile))
                .addGap(54, 54, 54))
        );

        btnLoadFile.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaProdutoMouseReleased
    }//GEN-LAST:event_btnBuscaProdutoMouseReleased

    /**
     * Gatilho para exclusão de um produto da lista
     * 
     * @param evt 
     */
    private void btnDelProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelProdutoActionPerformed
        this.controller.remove(this.tableProdutos);
        this.loadItems();
    }//GEN-LAST:event_btnDelProdutoActionPerformed
    
    /**
     * Gatilho para atualizar os detalhes do produto na tela
     * 
     * @param evt 
     */
    private void tableProdutosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosMouseReleased
        try {
            int row = this.tableProdutos.getSelectedRow();
            long ID = (long) this.tableProdutos.getModel().getValueAt(row, 0);

            Produto p = (Produto) this.controller.getObjModel().findBy(ID).get(0);
            Integer estoque = p.getSaldoEstoque();
            DecimalFormat df = new DecimalFormat("#,###");
            this.jLabelDescricao.setText(String.format("%s %s", p.getNome(), p.getDescricao()));
            this.jLabelEstoque.setText(df.format(estoque));
            this.jLabelPreco.setText(p.getFormatPrice());
            this.jLabelCategoria.setText(p.getCategoria().toString());
            jLabelEstoque.setForeground(Color.BLUE);
            jLabelEstoque.setToolTipText("");
            if(p.estoqueCritico()){
                jLabelEstoque.setToolTipText(String.format("Nível crítico, abaixo de %s",p.getNivelCritico()));
                jLabelEstoque.setForeground(Color.RED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tableProdutosMouseReleased
    
    /**
     * Gatilho para atualizar os detalhes ao pressionar as teclas "Seta para cima" ou "Seta para baixo"
     * 
     * @param evt 
     */
    private void tableProdutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProdutosKeyReleased
        if(evt.getKeyCode() == evt.VK_DOWN || evt.getKeyCode() == evt.VK_UP) {
            this.tableProdutosMouseReleased(null);
        }
    }//GEN-LAST:event_tableProdutosKeyReleased
    
    /**
     * Gatilho para exibição da tela de cadastro de categorias
     * 
     * @param evt 
     */
    private void btnAddCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoriaActionPerformed
        CategoriaController.make().showView();
    }//GEN-LAST:event_btnAddCategoriaActionPerformed
    
    /**
     * Gatilho para abastecimento de estoque
     * 
     * @param evt 
     */
    private void btnAddEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstoqueActionPerformed
       this.controller.supplyStockFromTable(this.tableProdutos, jLabelEstoque);
    }//GEN-LAST:event_btnAddEstoqueActionPerformed

    private void btnAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProdutoActionPerformed
       this.novoProduto();
    }//GEN-LAST:event_btnAddProdutoActionPerformed

    private void btnSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveToFileActionPerformed
        this.controller.saveToFile();
    }//GEN-LAST:event_btnSaveToFileActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        this.loadItems();
        this.btnReload.setEnabled(false);
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFileActionPerformed
        this.controller.loadFromFile(this.lblSource);
        this.loadItems();
    }//GEN-LAST:event_btnLoadFileActionPerformed

    private void btnBuscaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaProdutoActionPerformed
         List <ModelInterface> produtos = this.controller.search();
         
        // significa que o usuário cancelou a pesquisa
        if ( produtos.isEmpty() ) return ;
        
        if ( produtos.size() == 1 && produtos.get(0).hashCode() == 0 ) {
            Util.showMessage("Produto não encontrado.", "Buscador", JOptionPane.WARNING_MESSAGE );
            return ;
        }
        
        this.btnReload.setEnabled(true);
        DefaultTableModel model = ProdutoController.make().getTableModel(produtos);
        this.tableProdutos.setModel(model);
        this.tableProdutosMouseReleased(null);
        this.btnReload.setEnabled(true);
        this.tableProdutos.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btnBuscaProdutoActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategoria;
    private javax.swing.JButton btnAddEstoque;
    private javax.swing.JButton btnAddProduto;
    private javax.swing.JButton btnBuscaProduto;
    private javax.swing.JButton btnDelProduto;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSaveToFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelCategoria1;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelEstoque;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JPanel jPanelDetalhes2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSource;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
