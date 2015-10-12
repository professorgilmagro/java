/*
 * Tela de vendas
 */
package view;

import controller.ClienteController;
import controller.VendaController;
import dao.ModelInterface;
import model.CepService;
import model.Cliente;
import model.Produto;
import model.Venda;
import model.Util;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author gilmar
 */
public class JPanelVendas extends javax.swing.JPanel{
    final JFrame frame = new JFrame();
    
    private Long clienteID ;
    
     /**
     * Recebe o controlador desta view
     */
    private VendaController controller ;
    
    /**
     * Creates new form jPanelProdutos
     */
    public JPanelVendas() {
        initComponents();
        this.controller = new VendaController();
        this.btnAlterarEndereco.setEnabled(false);
        this.btnFinalizarPedido.setEnabled(false);
    }
    
    /**
     * Remove um item da lista de produtos
     */
    public void removeItem(){
        int row = this.tableItems.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um item para excluir.");
            return;
        }
       
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        String item = (String) model.getValueAt(row, 1);
        String message = String.format("Tem certeza que deseja excluir o item '%s'?", item);
        
        if(Util.showConfirm(message, "Remover item?")) {
            Double total = Util.convertCurrencyToDouble(jLabelTotal.getText());
            Double sub = Util.convertCurrencyToDouble((String) model.getValueAt(row, 5));
            this.jLabelTotal.setText(Util.convertDoubleToCurrency(total-sub));
            model.removeRow(row);
        }
    }
    
    /**
     * Carrega os dados referentes à um novo pedido
     */
    public void novoPedido() {
        String code = Util.showInput("Digite o código do cliente");
        
        if(code == null){
            return;
        }
        
        if( this.loadCliente(Long.parseLong(code))){
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 0; i < this.tableItems.getColumnCount(); i++) {
                model.addColumn(this.tableItems.getColumnName(i));
            }
            
            this.tableItems.setModel(model);
            this.jLabelTotal.setText(Util.convertDoubleToCurrency(0.0));
            this.jTextObs.setText("");
            
            while (true) {
                this.addItem();
                if ( Util.showConfirm( "Gostaria de adicionar mais produto?" , "Produtos") == false ) {
                    break;
                }
            }
            
            this.btnAddItem.setEnabled(true);
            this.btnDelete.setEnabled(true);
            this.btnFinalizarPedido.setEnabled(true);
            this.btnAlterarEndereco.setEnabled(true);
        }
    }
    
    /**
     * Carrega as informações do cliente na tela
     * @param ID
     * @return 
     */
    public Boolean loadCliente(Long ID) {
        try {
           this.clienteID = null;
           
           Cliente cli = (Cliente) new Cliente().findBy(ID).get(0);
           if( cli == null ){
               Util.showMessage("Cliente não cadastrado no sistema.", JOptionPane.WARNING_MESSAGE);
               this.jLabelNome.setText("Nome do cliente");
               this.jLabelCPF.setText("CPF");
               this.jLabelEmail.setText("E-mail");
               this.jLabelTelefone.setText("Telefone");
               this.jLabelEndereco.setText("");
               return false;
           }
           
           this.clienteID = ID ;
           this.jLabelNome.setText(cli.getFullName());
           this.jLabelCPF.setText(String.format("CPF: %s", cli.getFormatCPF()));
           this.jLabelEmail.setText(String.format("E-mail: %s",cli.getEmail()));
           this.jLabelTelefone.setText(String.format("Telefone: %s",cli.getTelefone()));
           this.jLabelEndereco.setText(cli.getEndereco());
        } catch (Exception ex) {
           Util.showMessage("Cliente não localizado.", JOptionPane.WARNING_MESSAGE);
           return false ;
        }
        
       return true;
    }
    
    /**
     * Remove um item da lista de produtos
     */
    public void addItem(){
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        String code = Util.showInput("Digite o código do produto.");
        
        Double total = Util.convertCurrencyToDouble(this.jLabelTotal.getText());
        if(code == null) return;
        
        Produto produto = new Produto();
        Produto p = (Produto) produto.findBy(Long.parseLong(code)).get(0);
        if(p.hashCode() == 0){
            Util.showMessage("Produto não encontrado.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String productDescription = String.format(
                "%s - %s (%s kg)\nPreço unitário: %s\n\nDigite a quantidade.",
                p.getNome(), p.getDescricao(), p.getPeso(), p.getFormatPrice()
        );
        
        String qtde = Util.showInput(productDescription, p.getNome());
        if(qtde == null) return;
        if (p.getSaldoEstoque() < Integer.parseInt(qtde)) {
            String message = String.format(
                    "Estoque insuficiente para a quantidade informada. Saldo atual: %s.",
                    p.getSaldoEstoque()
            ) ;
            Util.showMessage(message, p.getNome(), JOptionPane.WARNING_MESSAGE);
            return ;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        Double subtotal = p.getValor() * Integer.parseInt(qtde);
        total += subtotal;
        Object[] data = {
            p.getCodigo(),
            p.getNome(),
            p.getDescricao(),
            p.getFormatPrice(),
            Integer.parseInt(qtde),
            String.format("R$ %s", df.format(subtotal))
        };
        model.addRow(data);
        this.jLabelTotal.setText(String.format("R$ %s", df.format(total)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLoadOrder = new javax.swing.JButton();
        jPanelDetalhes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelCodigoPedido = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        btnAlterarEndereco = new javax.swing.JButton();
        btnAddItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextObs = new javax.swing.JTextPane();
        btnFinalizarPedido = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnDisplayClientes = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        tableItems.setAutoCreateRowSorter(true);
        tableItems.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Descrição", "Valor unitário", "Quantidade", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableItems.setName(""); // NOI18N
        tableItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableItemsMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableItems);
        if (tableItems.getColumnModel().getColumnCount() > 0) {
            tableItems.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        btnAdd.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Ecommerce-Shopping-Cart-Empty-icon.png"))); // NOI18N
        btnAdd.setMnemonic('n');
        btnAdd.setText("Nova venda");
        btnAdd.setToolTipText("");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Ecommerce-Put-Out-icon.png"))); // NOI18N
        btnDelete.setMnemonic('x');
        btnDelete.setText("Excluir item");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnLoadOrder.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnLoadOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Search-25.png"))); // NOI18N
        btnLoadOrder.setMnemonic('v');
        btnLoadOrder.setText("Consultar vendas ...");
        btnLoadOrder.setToolTipText("");
        btnLoadOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoadOrderMouseReleased(evt);
            }
        });
        btnLoadOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadOrderActionPerformed(evt);
            }
        });

        jPanelDetalhes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Endereço para entrega:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelEndereco.setText("-");
        jLabelEndereco.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelNome.setText("Nome do cliente");

        jLabelCPF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/card-identifier.png"))); // NOI18N
        jLabelCPF.setText("CPF");

        jLabelEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/email.png"))); // NOI18N
        jLabelEmail.setText("E-mail");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/64x64/icon-venda.png"))); // NOI18N

        jLabelCodigoPedido.setFont(new java.awt.Font("Noto Sans", 1, 36)); // NOI18N
        jLabelCodigoPedido.setForeground(new java.awt.Color(74, 86, 142));
        jLabelCodigoPedido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCodigoPedido.setText("Nº 0000");
        jLabelCodigoPedido.setToolTipText("");

        jLabelData.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelData.setForeground(new java.awt.Color(83, 106, 253));
        jLabelData.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelData.setText("-");
        jLabelData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelTelefone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/16x16/phone.png"))); // NOI18N
        jLabelTelefone.setText("Telefone:");

        javax.swing.GroupLayout jPanelDetalhesLayout = new javax.swing.GroupLayout(jPanelDetalhes);
        jPanelDetalhes.setLayout(jPanelDetalhesLayout);
        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addComponent(jLabelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46))))
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelCodigoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelCodigoPedido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelData)
                            .addComponent(jLabelCPF)))
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addComponent(jLabelIcon)
                        .addGap(3, 3, 3)))
                .addGap(3, 3, 3)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTelefone)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelTotal.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotal.setText("R$ 0,00");

        btnAlterarEndereco.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnAlterarEndereco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Cinema-Adventures-icon.png"))); // NOI18N
        btnAlterarEndereco.setMnemonic('e');
        btnAlterarEndereco.setText("Alterar endereço de entrega");
        btnAlterarEndereco.setToolTipText("");
        btnAlterarEndereco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAlterarEnderecoMouseReleased(evt);
            }
        });
        btnAlterarEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarEnderecoActionPerformed(evt);
            }
        });

        btnAddItem.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnAddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Ecommerce-Put-In-icon.png"))); // NOI18N
        btnAddItem.setMnemonic('a');
        btnAddItem.setText("Adicionar item");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        jTextObs.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observações"));
        jScrollPane1.setViewportView(jTextObs);

        btnFinalizarPedido.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnFinalizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Ecommerce-Shopping-Cart-Loaded-icon.png"))); // NOI18N
        btnFinalizarPedido.setMnemonic('F');
        btnFinalizarPedido.setText("Finalizar pedido");
        btnFinalizarPedido.setToolTipText("");
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Controle de Vendas");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnDisplayClientes.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnDisplayClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Contacts-25.png"))); // NOI18N
        btnDisplayClientes.setMnemonic('c');
        btnDisplayClientes.setText("Clientes");
        btnDisplayClientes.setToolTipText("");
        btnDisplayClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDisplayClientesMouseReleased(evt);
            }
        });
        btnDisplayClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanelDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoadOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterarEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDisplayClientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(btnFinalizarPedido))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete)
                        .addComponent(btnLoadOrder)
                        .addComponent(btnAlterarEndereco)
                        .addComponent(btnAddItem)
                        .addComponent(btnFinalizarPedido)
                        .addComponent(btnDisplayClientes)))
                .addContainerGap())
        );

        btnLoadOrder.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadOrderMouseReleased
    }//GEN-LAST:event_btnLoadOrderMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.removeItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableItemsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItemsMouseReleased
        
    }//GEN-LAST:event_tableItemsMouseReleased

    private void btnLoadOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadOrderActionPerformed
        try {
            List<ModelInterface> orders = this.controller.search();
            if(orders.isEmpty()) return;
            
            if( orders.size() > 1 ){
                int[] withColumns = {80,250,160,120};
                String choose = Util.showTableOptions(this.controller.getTableModel(orders), "Resultados da pesquisa de pedidos", withColumns);
                if( ! choose.isEmpty() ){
                    orders = this.controller.getObjModel().findBy(Long.parseLong(choose));
                }
            }
            
            if(orders.size() == 1 && orders.get(0).hashCode() == 0){
                Util.showMessage("Pedido não encontrado!", "Busca", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Venda order = (Venda) orders.get(0);
            this.controller.setModel(order);
            Cliente cli = order.getCliente();
            
            this.jLabelNome.setText(cli.getFullName());
            this.jLabelCPF.setText(cli.getFormatCPF());
            this.jLabelEmail.setText(cli.getEmail());
            this.jLabelTelefone.setText(cli.getTelefone());
            this.jTextObs.setText(order.getObs());
            this.tableItems.setModel(this.controller.getModelItems());
            this.jLabelTotal.setText(Util.convertDoubleToCurrency(order.getTotal()));
            this.jLabelCodigoPedido.setText(String.format("%06d", order.hashCode()));
            this.jLabelData.setText(order.getFormatDataVenda());
            
            this.btnAddItem.setEnabled(false);
            this.btnDelete.setEnabled(false);
            this.btnFinalizarPedido.setEnabled(false);
            this.btnAlterarEndereco.setEnabled(false);
        } catch (Exception ex) {
            Util.showMessage("Pedido não encontrado.", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnLoadOrderActionPerformed

    private void btnAlterarEnderecoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarEnderecoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarEnderecoMouseReleased

    private void btnAlterarEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarEnderecoActionPerformed
        String cep = Util.onlyNumber(Util.showInput("Digite o CEP"));
        Integer numero = Integer.parseInt(Util.showInput("Digite o número da residência."));
        Cliente cli = (Cliente) new Cliente();
        CepService cws = new CepService(cep);
        cli.fillFromService(cws);
        cli.setCEP(Long.parseLong(cep));
        cli.setNumero(numero);

        String message = String.format( "O endereço abaixo está correto?\n%s", cli.getEndereco());
        if(Util.showConfirm(message, "Confirmação de endereço") == false){
            String logradouro = Util.showInput("Digite o logradouro.");
            String bairro = Util.showInput("Digite o bairro.");
            String cidade = Util.showInput("Digite a cidade.");
            String estado = Util.showInput("Digite o estado.");
            cli.setLogadouro(logradouro);
            cli.setBairro(bairro);
            cli.setCidade(cidade);
            cli.setEstado(estado);
        }
        
        this.jLabelEndereco.setText(cli.getEndereco());
    }//GEN-LAST:event_btnAlterarEnderecoActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        this.novoPedido();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        this.addItem();
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        Venda v = new Venda();
        v.setClienteID(this.clienteID);
        v.setDataVenda(new Date());
        v.setEnderecoEntrega(this.jLabelEndereco.getText());
        v.setObs(this.jTextObs.getText());
        this.jLabelData.setText(v.getFormatDataVenda());
        
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        
        List items = new ArrayList();
        for (int i = 0; i < model.getRowCount(); i++) {
           Object[] row = {
               model.getValueAt(i, 0),
               model.getValueAt(i, 1),
               model.getValueAt(i, 2),
               model.getValueAt(i, 3).toString(),
               model.getValueAt(i, 4).toString(),
               model.getValueAt(i, 5).toString(),
           };
          
           items.add(row);
        }
        
        v.setItens(items);
        v.setValorFrete(0.0);
        v.setDescontos(0.0);
        
        try {
            v.save();
            this.jLabelCodigoPedido.setText(String.format("%06d", v.hashCode()));
        } catch (Exception ex) {
           Util.showMessage("Houve um erro na tentativa de salvar o pedido. Tenta mais tarde.", JOptionPane.ERROR_MESSAGE );
        }
        
        this.btnAddItem.setEnabled(false);
        this.btnDelete.setEnabled(false);
        this.btnFinalizarPedido.setEnabled(false);
        this.btnAlterarEndereco.setEnabled(false);
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private void btnDisplayClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDisplayClientesMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDisplayClientesMouseReleased

    private void btnDisplayClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayClientesActionPerformed
        ClienteController.make().displayView();
    }//GEN-LAST:event_btnDisplayClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAlterarEndereco;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDisplayClientes;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnLoadOrder;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCodigoPedido;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanelDetalhes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextObs;
    private javax.swing.JTable tableItems;
    // End of variables declaration//GEN-END:variables
   
}
