/*
 * Tela de vendas
 */
package estoque.forms;

import estoque.CepService;
import estoque.Cliente;
import estoque.Produto;
import estoque.Venda;
import estoque.util;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gilmar
 */
public class jPanelVendas extends javax.swing.JPanel{
    final JFrame frame = new JFrame();
    
    private Long clienteID ;
    
    /**
     * Creates new form jPanelProdutos
     */
    public jPanelVendas() {
        initComponents();
    }
    
    /**
     * Remove um item da lista de produtos
     */
    public void removeItem(){
        int row = this.tableItems.getSelectedRow();
                
        if(row == -1){
            util.showMessage("Selecione um item para excluir.");
            return;
        }
       
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        String item = (String) model.getValueAt(row, 1);
        String message = String.format("Tem certeza que deseja excluir o item '%s'?", item);
        
        if(util.showConfirm(message, "Remover item?")) {
            Double total = util.convertCurrencyToDouble(jLabelTotal.getText());
            Double sub = util.convertCurrencyToDouble((String) model.getValueAt(row, 5));
            this.jLabelTotal.setText(util.convertDoubleToCurrency(total-sub));
            model.removeRow(row);
        }
    }
    
    /**
     * Carrega os dados referentes à um novo pedido
     */
    public void novoPedido() {
       String code = util.showInput("Digite o código do cliente");
        if( this.loadCliente(Long.parseLong(code))){
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 0; i < this.tableItems.getColumnCount(); i++) {
                model.addColumn(this.tableItems.getColumnName(i));
            }
            
            this.tableItems.setModel(model);
            this.jLabelTotal.setText(util.convertDoubleToCurrency(0.0));
            this.jTextObs.setText("");
            
            while (true) {
                this.addItem();
                if ( util.showConfirm( "Gostaria de adicionar mais produto?" , "Produtos") == false ) {
                    break;
                }
            }
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
           
           Cliente cli = (Cliente) new Cliente().findByID(ID);
           if( cli == null ){
               util.showMessage("Cliente não localizado.", JOptionPane.WARNING_MESSAGE);
               this.jLabelNome.setText("Nome do cliente");
               this.jLabelCPF.setText("CPF");
               this.jLabelEmail.setText("E-mail");
               this.jLabelEndereco.setText("");
               return false;
           }
           
           this.clienteID = ID ;
           this.jLabelNome.setText(cli.getFullName());
           this.jLabelCPF.setText(cli.getFormatCPF());
           this.jLabelEmail.setText(cli.getEmail());
           this.jLabelEndereco.setText(cli.getEndereco());
        } catch (IOException ex) {
           util.showMessage("Cliente não localizado.", JOptionPane.WARNING_MESSAGE);
           return false ;
        }
        
       return true;
    }
    
    /**
     * Remove um item da lista de produtos
     */
    public void addItem(){
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        String code = util.showInput("Digite o código do produto.");
        String qtde = util.showInput("Digite a quantidade.");
        Double total = util.convertCurrencyToDouble(this.jLabelTotal.getText());
        
        try {
            Produto produto = new Produto();
            Produto p = (Produto) produto.findByID(Long.parseLong(code));
            
            if (p.getSaldoEstoque() < Integer.parseInt(qtde)) {
                String message = String.format(
                        "Estoque insuficiente para a quantidade informada. Saldo atual: %s.", 
                        p.getSaldoEstoque()
                ) ;
                util.showMessage(message, p.getNome(), JOptionPane.WARNING_MESSAGE);
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
        } catch (IOException ex) {
            util.showMessage("Produto não encontrado.", JOptionPane.WARNING_MESSAGE);
        }
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
        jLabel1 = new javax.swing.JLabel();
        btnLoadFile = new javax.swing.JButton();
        jPanelDetalhes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        btnLoadFile1 = new javax.swing.JButton();
        btnAddItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextObs = new javax.swing.JTextPane();
        btnFinalizarPedido = new javax.swing.JButton();

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
        btnAdd.setMnemonic('n');
        btnAdd.setText("Nova venda");
        btnAdd.setToolTipText("");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnDelete.setMnemonic('x');
        btnDelete.setText("Excluir item");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Vendas");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnLoadFile.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnLoadFile.setText("Carregar venda...");
        btnLoadFile.setToolTipText("");
        btnLoadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoadFileMouseReleased(evt);
            }
        });
        btnLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFileActionPerformed(evt);
            }
        });

        jPanelDetalhes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Endereço de entrega:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelEndereco.setText("-");
        jLabelEndereco.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelNome.setText("Nome do cliente");

        jLabelCPF.setText("CPF");

        jLabelEmail.setText("E-mail");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estoque/forms/icon-venda.png"))); // NOI18N

        javax.swing.GroupLayout jPanelDetalhesLayout = new javax.swing.GroupLayout(jPanelDetalhes);
        jPanelDetalhes.setLayout(jPanelDetalhesLayout);
        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addComponent(jLabelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46)))))
                .addContainerGap())
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addComponent(jLabelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCPF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEmail))
                    .addComponent(jLabelIcon))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelTotal.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotal.setText("R$ 0,00");

        btnLoadFile1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnLoadFile1.setMnemonic('e');
        btnLoadFile1.setText("Alterar endereço de entrega");
        btnLoadFile1.setToolTipText("");
        btnLoadFile1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoadFile1MouseReleased(evt);
            }
        });
        btnLoadFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadFile1ActionPerformed(evt);
            }
        });

        btnAddItem.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
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
        btnFinalizarPedido.setMnemonic('F');
        btnFinalizarPedido.setText("Finalizar pedido");
        btnFinalizarPedido.setToolTipText("");
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanelDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoadFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoadFile1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizarPedido)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jPanelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete)
                        .addComponent(btnLoadFile)
                        .addComponent(btnLoadFile1)
                        .addComponent(btnAddItem)
                        .addComponent(btnFinalizarPedido)))
                .addContainerGap())
        );

        btnLoadFile.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFileMouseReleased
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File outfile = chooser.getSelectedFile();
        }
    }//GEN-LAST:event_btnLoadFileMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.removeItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void fillCliente(){
        int row = this.tableItems.getSelectedRow();
        long ID = (long) this.tableItems.getModel().getValueAt(row, 0);
        String item = (String) this.tableItems.getModel().getValueAt(row, 1);
        Cliente cli = new Cliente();
        try {
            Cliente c = (Cliente) cli.findByID(ID);
            this.jLabelCPF.setText("CPF: " + c.getFormatCPF());
            this.jLabelNome.setText(c.getFullName());
            this.jLabelEmail.setText("E-mail: " + c.getEmail());
            this.jLabelEndereco.setText(c.getEndereco());
        } catch (IOException ex) {
            Logger.getLogger(jPanelVendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tableItemsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItemsMouseReleased
        
    }//GEN-LAST:event_tableItemsMouseReleased

    private void btnLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoadFileActionPerformed

    private void btnLoadFile1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFile1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoadFile1MouseReleased

    private void btnLoadFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadFile1ActionPerformed
        String cep = util.showInput("Digite o CEP");
        Integer numero = Integer.parseInt(util.showInput("Digite o número da residência."));
        Cliente cli = (Cliente) new Cliente();
        CepService cws = new CepService(cep);
        cli.fillFromService(cws);
        cli.setCEP(Long.parseLong(cep));
        cli.setNumero(numero);

        String message = String.format( "O endereço abaixo está correto?\n%s", cli.getEndereco());
        if(util.showConfirm(message, "Confirmação de endereço") == false){
            String logradouro = util.showInput("Digite o logradouro.");
            String bairro = util.showInput("Digite o bairro.");
            String cidade = util.showInput("Digite a cidade.");
            String estado = util.showInput("Digite o estado.");
            cli.setLogadouro(logradouro);
            cli.setBairro(bairro);
            cli.setCidade(cidade);
            cli.setEstado(estado);
        }
        
        this.jLabelEndereco.setText(cli.getEndereco());
    }//GEN-LAST:event_btnLoadFile1ActionPerformed

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
        
        DefaultTableModel model = (DefaultTableModel) this.tableItems.getModel();
        
        List items = new ArrayList();
        for (int i = 0; i < model.getRowCount(); i++) {
           Object[] row = {
               model.getValueAt(i, 0),
               model.getValueAt(i, 1),
               model.getValueAt(i, 2),
               util.convertCurrencyToDouble(model.getValueAt(i, 3).toString()),
               Integer.parseInt(model.getValueAt(i, 4).toString()),
               util.convertCurrencyToDouble(model.getValueAt(i, 5).toString()),
           };
          
           items.add(row);
        }
        
        v.setItens(items);
        v.setValorFrete(0.0);
        v.setDescontos(0.0);
        
        try {
            v.save();
            util.showMessage("Pedido salvo com sucesso.");
        } catch (Exception ex) {
           util.showMessage("Houve um erro na tentativa de salvar o pedido. Tenta mais tarde.", JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JButton btnLoadFile1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanelDetalhes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextObs;
    private javax.swing.JTable tableItems;
    // End of variables declaration//GEN-END:variables
   
}
