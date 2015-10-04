/*
 * Tela de produtos
 */
package view;

import model.Categoria;
import dao.ModelInterface;
import model.Produto;
import model.Util;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 * Classe para controlar as funcionalidades de gerenciamento de proutos
 * 
 * @author gilmar
 */
public final class jPanelProdutos extends javax.swing.JPanel {

    /**
     * Inicializa e renderiza os objetos gráficos na tela
     */
    public jPanelProdutos() {
        initComponents();
        this.loadItems();
    }
    
    /**
     * Carrega todos os itens de produto
     */
    public void loadItems(){
        Produto prod = new Produto();
        
        TableModel model = prod.getTableModel() ;
        this.tableProdutos.setModel(model);
        this.tableProdutos.setAutoCreateRowSorter(true);
        this.tableProdutos.enableInputMethods(false);
        this.jTextFile.setText( String.format("Dados extraidos do arquivo: %s" , prod.getFileName()));
    }
    
    /**
     * Cadastra um novo produto
     */
    public void novoProduto() {
        while (true) {
            try {
                Categoria cat = new Categoria();
                List objs = cat.getAll();
                
                if( objs.isEmpty() ) {
                    Util.showMessage(
                        "Não há categoria de produto cadastrado.\n" +
                        "Cadastre as categorias desejadas antes de continuar.",
                        JOptionPane.WARNING_MESSAGE
                    );
                    
                    return ;
                }
                
                Categoria categoria = (Categoria) Util.showOptions("Selecione a categoria.", objs.toArray(), "Categoria") ;
                if( categoria == null ) return ;
                
                String nome = Util.showInput("Digite o nome.");
                 if( nome == null ) return ;
                 
                String descricao = Util.showInput("Digite a descrição.");
                 if( descricao == null ) return ;
                 
                double peso = Util.convertCurrencyToDouble(Util.showInput("Digite o peso (kg).")) ;
                 if( peso == 0.00 ) return ;
                 
                double valor = Util.convertCurrencyToDouble(Util.showInput("Digite o valor unitário.")) ;
                 if( valor == 0.00 ) return ;
                 
                int estoque = Integer.parseInt(Util.showInput("Digite a quantidade em estoque.")) ;
                int nivelCritico = Integer.parseInt(Util.showInput("Digite o nível crítico de estoque para este produto.")) ;

                model.Produto produto = new Produto(nome, descricao, valor);
                produto.setCodCategoria(categoria);
                produto.setPeso(peso);
                produto.setSaldoEstoque(estoque);
                produto.setNivelCritico(nivelCritico);
                produto.save();
                this.loadItems();
                
                Util.showMessage("Produto salvo com sucesso");
                if ( Util.showConfirm( "Gostaria de cadastrar mais um novo produto?" , "Estoque") == false ) {
                    break;
                }
            } catch (Exception e) {
                Util.showMessage("Valor inválido informado!", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFile = new javax.swing.JTextPane();
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

        setPreferredSize(new java.awt.Dimension(800, 600));

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
        tableProdutos.setName(""); // NOI18N
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

        btnAddProduto.setMnemonic('a');
        btnAddProduto.setText("Adicionar");
        btnAddProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddProdutoMouseReleased(evt);
            }
        });

        btnDelProduto.setMnemonic('x');
        btnDelProduto.setText("Excluir");
        btnDelProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelProdutoActionPerformed(evt);
            }
        });

        btnBuscaProduto.setMnemonic('l');
        btnBuscaProduto.setText("Localizar");
        btnBuscaProduto.setToolTipText("");
        btnBuscaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBuscaProdutoMouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de produtos");

        jTextFile.setBackground(new java.awt.Color(254, 240, 156));
        jTextFile.setText("Dados oriundos do arquivo:");
        jScrollPane1.setViewportView(jTextFile);

        btnLoadFile.setToolTipText("");
        btnLoadFile.setLabel("Carregar arquivo...");
        btnLoadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoadFileMouseReleased(evt);
            }
        });

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

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icon-produto.png"))); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
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

        btnAddCategoria.setMnemonic('c');
        btnAddCategoria.setText("Categoria...");
        btnAddCategoria.setToolTipText("");
        btnAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoadFile))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCategoria)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelProduto)
                    .addComponent(btnBuscaProduto)
                    .addComponent(btnAddEstoque)
                    .addComponent(btnAddProduto)
                    .addComponent(btnAddCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadFile))
                .addGap(36, 36, 36))
        );

        btnLoadFile.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaProdutoMouseReleased
    }//GEN-LAST:event_btnBuscaProdutoMouseReleased
    
    /**
     * Gatilho para cadastrar novo produto
     * 
     * @param evt 
     */
    private void btnAddProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddProdutoMouseReleased
        this.novoProduto();
    }//GEN-LAST:event_btnAddProdutoMouseReleased
    
    /**
     * Gatilho para carregamento de arquivo externo
     * 
     * @param evt 
     */
    private void btnLoadFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFileMouseReleased
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File outfile = chooser.getSelectedFile();
             this.jTextFile.setText( String.format( "Dados extraidos do arquivo: %s" , outfile.getPath()) );
        }
    }//GEN-LAST:event_btnLoadFileMouseReleased
    
    /**
     * Gatilho para exclusão de um produto da lista
     * 
     * @param evt 
     */
    private void btnDelProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelProdutoActionPerformed
        int row = this.tableProdutos.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para excluir.");
            return;
        }
       
        long ID = (long) this.tableProdutos.getModel().getValueAt(row, 0);
        String item = (String) this.tableProdutos.getModel().getValueAt(row, 1);
        String message = String.format("Tem certeza que deseja excluir o item '%s'?",item);
       if(Util.showConfirm(message, "Remover item?")) {
           ModelInterface produto = new Produto();
            try {
                produto.remove(ID);
            } catch (IOException ex) {
                Logger.getLogger(jPanelProdutos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(jPanelProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            this.loadItems();
       }
    }//GEN-LAST:event_btnDelProdutoActionPerformed
    
    /**
     * Gatilho para atualizar os detalhes do produto na tela
     * 
     * @param evt 
     */
    private void tableProdutosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosMouseReleased
        int row = this.tableProdutos.getSelectedRow();
        long ID = (long) this.tableProdutos.getModel().getValueAt(row, 0);
        Produto prod = new Produto();
        
        try {
            Produto p = (Produto) prod.findBy(ID);
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
            
        } catch (IOException ex) {
            Logger.getLogger(jPanelClientes.class.getName()).log(Level.SEVERE, null, ex);
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
        JDialog window = Util.getDefaultWindow(new jPanelCategorias(), "Categorias");
        window.setSize(450,400);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }//GEN-LAST:event_btnAddCategoriaActionPerformed
    
    /**
     * Gatilho para abastecimento de estoque
     * 
     * @param evt 
     */
    private void btnAddEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstoqueActionPerformed
       int row = this.tableProdutos.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para adicionar estoque.");
            return;
        }
        
        long ID = (long) this.tableProdutos.getModel().getValueAt(row, 0);
        String item = (String) this.tableProdutos.getModel().getValueAt(row, 1);
        
        String message = String.format("Digite a quantidade desejada para adicionar ao estoque do produto '%s'", item ) ;
        String qtde = Util.showInput(message);
        if( qtde == null) return ;
        
        ModelInterface produto = new Produto();
        try {
            Produto p = (Produto) produto.findBy(ID);
            p.addEstque(Integer.parseInt(qtde));
            
            DecimalFormat df = new DecimalFormat("#,###") ;
            qtde = df.format(p.getSaldoEstoque());
            message = String.format("O saldo após a adição da quantidade informada será de %s.\nConfirma a operação?", qtde);
            if( Util.showConfirm(message, "Abastecimento de estoque") ) {
                p.save();
                this.jLabelEstoque.setText(qtde);
                jLabelEstoque.setForeground(Color.BLUE);
                
                if ( p.estoqueCritico() ) {
                    jLabelEstoque.setForeground(Color.RED);
                }
                
                Util.showMessage("Estoque atualizado com sucesso.");
            }
        } catch ( Exception ex) {
            System.out.println(ex.getMessage());
            Util.showMessage("Houve um problema na tentativa de adicionar o estoque.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddEstoqueActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategoria;
    private javax.swing.JButton btnAddEstoque;
    private javax.swing.JButton btnAddProduto;
    private javax.swing.JButton btnBuscaProduto;
    private javax.swing.JButton btnDelProduto;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelCategoria1;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelEstoque;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JPanel jPanelDetalhes2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextFile;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
