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
import java.awt.Component;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Categoria;

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
        initComponents();
        
        this.controller = new ProdutoController();
        this.loadItems();
    }
    
    /**
     * Preenche o combo de categorias
     */
    public void fillCategorias(){
        List<Categoria> categorias = CategoriaController.make().fetchSortedItems(true);
        this.cmbCategoria.setModel(new DefaultComboBoxModel(categorias.toArray()));
    }
    
    /**
     * Carrega todos os itens de produto
     */
    public void loadItems(){
        TableModel model = this.controller.getTableModel();
        this.tableProdutos.setModel(model);
        this.tableProdutos.setAutoCreateRowSorter(true);
        this.tableProdutos.enableInputMethods(false);
        
        if(tableProdutos.getColumnModel().getColumnCount() > 0) {
           tableProdutos.getColumnModel().getColumn(0).setMinWidth(75);
           tableProdutos.getColumnModel().getColumn(1).setMinWidth(400);
           tableProdutos.getColumnModel().getColumn(1).setMaxWidth(200);
           tableProdutos.getColumnModel().getColumn(2).setMinWidth(120);
           tableProdutos.getColumnModel().getColumn(3).setMinWidth(200);
        }
        
        this.fillCategorias();
        btnAddEstoque.setEnabled(true);
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
        btnLoadFile = new javax.swing.JButton();
        jPanelDetalhes2 = new javax.swing.JPanel();
        jLabelEstoque = new javax.swing.JLabel();
        jLabelCategoria1 = new javax.swing.JLabel();
        btnAddCategoria = new javax.swing.JButton();
        lblSource = new javax.swing.JLabel();
        btnSaveToFile = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAddEstoque = new javax.swing.JButton();
        txtValorUnitario = new javax.swing.JFormattedTextField();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDataCriado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDataModificado = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 450));

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
        btnAddProduto.setMnemonic('n');
        btnAddProduto.setText("Novo");
        btnAddProduto.setPreferredSize(null);
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

        btnLoadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/load-from-file.png"))); // NOI18N
        btnLoadFile.setToolTipText("");
        btnLoadFile.setLabel("Carregar arquivo...");
        btnLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFileActionPerformed(evt);
            }
        });

        jPanelDetalhes2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhes2Layout.createSequentialGroup()
                        .addComponent(jLabelEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhes2Layout.createSequentialGroup()
                        .addComponent(jLabelCategoria1)
                        .addContainerGap())))
        );
        jPanelDetalhes2Layout.setVerticalGroup(
            jPanelDetalhes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhes2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelCategoria1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelEstoque)
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

        jLabel3.setText("Código");

        txtCodigo.setEnabled(false);

        jLabel4.setText("Nome");

        jLabel18.setText("Categoria");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Descrição");

        jLabel5.setText("Peso");

        jLabel6.setText("Valor unitário");

        btnAddEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/stock-icon.png"))); // NOI18N
        btnAddEstoque.setMnemonic('e');
        btnAddEstoque.setText("Abastecer Estoque");
        btnAddEstoque.setToolTipText("");
        btnAddEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEstoqueActionPerformed(evt);
            }
        });

        txtValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorUnitarioActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/save.png"))); // NOI18N
        btnSave.setMnemonic('s');
        btnSave.setText("Salvar");
        btnSave.setPreferredSize(null);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/clock.png"))); // NOI18N
        jLabel1.setText("Criado em:");

        lblDataCriado.setText("00/00/00");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/clock.png"))); // NOI18N
        jLabel2.setText("Modificado em:");

        lblDataModificado.setText("00/00/00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataCriado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(227, 227, 227)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataModificado, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCriado)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lblDataModificado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadFile))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveToFile))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtValorUnitario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAddEstoque))
                                    .addComponent(jLabel18)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel19)
                            .addComponent(jLabel3))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel18))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddEstoque)))))
                    .addComponent(jPanelDetalhes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelProduto)
                    .addComponent(btnBuscaProduto)
                    .addComponent(btnAddProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCategoria)
                    .addComponent(btnReload)
                    .addComponent(btnSaveToFile)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSource, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadFile))
                .addGap(18, 18, 18))
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
        if (this.txtCodigo.getText().isEmpty()) {
            Util.showMessage("Selecione um cliente para excluir.", "Sistema de vendas", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if( this.controller.remove(Long.parseLong(this.txtCodigo.getText())) == 1 ){
            this.btnAddProdutoActionPerformed(null);
            this.loadItems();
        }
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
            this.txtCodigo.setText(Long.toString(p.getCodigo()));
            this.txtNome.setText(p.getNome());
            this.txtDescricao.setText(p.getDescricao());
            this.txtPeso.setText(Double.toString(p.getPeso()));
            this.jLabelEstoque.setText(df.format(estoque));
            this.txtValorUnitario.setText(p.getFormatPrice());
            this.cmbCategoria.setSelectedItem(p.getCategoria());
            jLabelEstoque.setForeground(Color.BLUE);
            jLabelEstoque.setToolTipText("");
            lblDataCriado.setText(Util.asDateTime(p.getCreationDate()));
            lblDataModificado.setText(Util.asDateTime(p.getModificationDate()));
            btnAddEstoque.setEnabled(true);
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
        this.btnReload.setEnabled(true);
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
        for (Component c : this.getComponents()) {
            if (c instanceof JTextField) {
                JTextField t = (JTextField) c;
                t.setText("");
            }

            if (c instanceof JComboBox) {
                JComboBox t = (JComboBox) c;
                t.setSelectedIndex(0);
            }
        }
        
        btnAddEstoque.setEnabled(false);
        txtNome.requestFocus();
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

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Produto prod = new Produto();

        if (!this._is_valid()) {
            return ;
        }

        if (!this.txtCodigo.getText().isEmpty()) {
            prod.setCodigo(Long.parseLong(txtCodigo.getText()));
        }
        
        jLabelEstoque.setText("0");

        prod.setNome(txtNome.getText());
        prod.setDescricao(txtDescricao.getText());
        prod.setValor(Util.convertCurrencyToDouble(txtValorUnitario.getText()));
        prod.setPeso(Util.convertCurrencyToDouble(txtPeso.getText()));
        prod.setCodCategoria((Categoria) this.cmbCategoria.getSelectedItem());

        try {
            prod.save();
            this.controller.setModel(prod);
            this.txtCodigo.setText(Long.toString(prod.getCodigo()));
            btnAddEstoque.setEnabled(true);
            this.lblDataCriado.setText(Util.asDateTime(prod.getCreationDate()));
            this.lblDataModificado.setText(Util.asDateTime(prod.getModificationDate()));
            Util.showMessage("Produto salvo com sucesso.");
            loadItems();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            Util.showMessage("Houve um problema na tentativa de gravar o registro. \nVerifique os dados e tente novamente");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUnitarioActionPerformed
    
    /**
     * Valida se os campos requeridos foram devidamente preenchidos
     * 
     * @return boolean
     */
    private boolean _is_valid(){
        if(txtNome.getText().isEmpty()){
            Util.showMessage("O nome do produto deve ser informado.", "Sistema de vendas", JOptionPane.ERROR_MESSAGE);
            txtNome.requestFocus();
            return false ;
        }
        
        if(txtDescricao.getText().isEmpty()){
            Util.showMessage("A Descrição deve ser informada.", "Sistema de vendas", JOptionPane.ERROR_MESSAGE);
            txtDescricao.requestFocus();
            return false;
        }
        
        if(Util.onlyNumber(txtValorUnitario.getText()).isEmpty()){
            Util.showMessage("O Valor Unitário deve ser preenchido.", "Sistema de vendas", JOptionPane.ERROR_MESSAGE);
            txtValorUnitario.requestFocus();
            return false;
        }
        
        if(Util.onlyNumber(txtPeso.getText()).isEmpty()){
            Util.showMessage("O Peso é de preechimento obrigatório.", "Sistema de vendas", JOptionPane.ERROR_MESSAGE);
            txtValorUnitario.requestFocus();
            return false;
        }
        
        return true ;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategoria;
    private javax.swing.JButton btnAddEstoque;
    private javax.swing.JButton btnAddProduto;
    private javax.swing.JButton btnBuscaProduto;
    private javax.swing.JButton btnDelProduto;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveToFile;
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCategoria1;
    private javax.swing.JLabel jLabelEstoque;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDetalhes2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDataCriado;
    private javax.swing.JLabel lblDataModificado;
    private javax.swing.JLabel lblSource;
    private javax.swing.JTable tableProdutos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JFormattedTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
