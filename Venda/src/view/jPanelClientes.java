/*
 * Tela de clientes
 */
package view;

import model.CepService;
import model.Cliente;
import dao.ModelInterface;
import model.Util;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import java.util.Date;

/**
 *
 * @author gilmar
 */
public class jPanelClientes extends javax.swing.JPanel {

    /**
     * Creates new form jPanelProdutos
     */
    public jPanelClientes() {
        initComponents();
        this.loadItems();
    }
    
    /**
     * Carrega todos os clientes na tabela
     */
    public void loadItems(){
        Cliente cli = new Cliente();
        
        TableModel model = cli.getTableModel() ;
        this.tableClientes.setModel(model);
        this.tableClientes.setAutoCreateRowSorter(true);
        this.tableClientes.enableInputMethods(false);
        this.jTextFile.setText( String.format( "Dados extraidos do arquivo: %s" , cli.getFileName()) );
    }
    
    /**
     * Cadastrar um novo cliente
     */
    public void addCliente() {
        while (true) {
            try {
                Cliente cli = new Cliente();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                
                String nome = Util.showInput("Digite o nome.");
                String sobrenome = Util.showInput("Digite o sobrenome.");
                Long cpf = Long.parseLong(Util.showInput("Digite o CPF."));
                String cep = Util.showInput("Digite o Código Postal.");
                String sexo = Util.showOptions("Selecione o sexo.", cli.getMapSexo(), "Sexo").toString();
                Integer numero = Integer.parseInt(Util.showInput("Digite o número da residência."));
                CepService cws = new CepService(cep);
                cli.fillFromService(cws);
                cli.setCEP(Long.parseLong(cep));
                cli.setNumero(numero);
                cli.setSexo(sexo);
                
                
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
                
                Date nascimento = formatter.parse(Util.showInput("Digite a data de Nascimento."));
                String email = Util.showInput("Digite o email.");
                String telefone = Util.showInput("Digite o telefone.");

                cli.setDataNascimento(nascimento);
                cli.setEmail(email);
                cli.setNome(nome);
                cli.setSobrenome(sobrenome);
                cli.setCPF(cpf);
                cli.setTelefone(telefone);
                
                cli.save();
                this.loadItems();
                
                Util.showMessage("Cliente salvo com sucesso");
                if ( Util.showConfirm( "Gostaria de cadastrar mais um cliente?" , "Cliente") == false ) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
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
        tableClientes = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBusca = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFile = new javax.swing.JTextPane();
        btnLoadFile = new javax.swing.JButton();
        btnOdernar = new javax.swing.JButton();
        jPanelDetalhes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelSexo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        tableClientes.setAutoCreateRowSorter(true);
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableClientes.setName(""); // NOI18N
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableClientesMouseReleased(evt);
            }
        });
        tableClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableClientesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableClientes);

        btnAdd.setMnemonic('a');
        btnAdd.setText("Adicionar");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddMouseReleased(evt);
            }
        });

        btnDelete.setMnemonic('x');
        btnDelete.setText("Excluir");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnBusca.setMnemonic('l');
        btnBusca.setText("Localizar");
        btnBusca.setToolTipText("");
        btnBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBuscaMouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Clientes");

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

        btnOdernar.setMnemonic('o');
        btnOdernar.setToolTipText("");
        btnOdernar.setLabel("Odernar");

        jPanelDetalhes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Endereço:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelEndereco.setText("-");
        jLabelEndereco.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabelNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelNome.setText("Nome do cliente");

        jLabelCPF.setText("CPF");

        jLabelEmail.setText("E-mail");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icon-cliente.png"))); // NOI18N

        jLabelSexo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelSexo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSexo.setText("Sexo:");
        jLabelSexo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanelDetalhesLayout = new javax.swing.GroupLayout(jPanelDetalhes);
        jPanelDetalhes.setLayout(jPanelDetalhesLayout);
        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addComponent(jLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                                .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(6, 6, 6)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOdernar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoadFile)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete)
                        .addComponent(btnBusca)
                        .addComponent(btnOdernar)))
                .addGap(27, 27, 27)
                .addComponent(jPanelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadFile))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnLoadFile.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaMouseReleased
    }//GEN-LAST:event_btnBuscaMouseReleased
    
    /**
     * Aciona o evento para adicionar um novo cliente
     * 
     * @param evt 
     */
    private void btnAddMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseReleased
        this.addCliente();
    }//GEN-LAST:event_btnAddMouseReleased
    
    /**
     * Permite carregar um arquivo externo
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
     * Remove o cliente selecionado na lista
     * 
     * @param evt 
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = this.tableClientes.getSelectedRow();
                
        if(row == -1){
            Util.showMessage("Selecione um cliente para excluir.");
            return;
        }
       
        long ID = (long) this.tableClientes.getModel().getValueAt(row, 0);
        String item = (String) this.tableClientes.getModel().getValueAt(row, 1);
        String message = String.format("Tem certeza que deseja excluir o cliente '%s'?",item);
        if(Util.showConfirm(message, "Remover cliente?")) {
           ModelInterface cli = new Cliente();
            try {
                cli.remove(ID);
            } catch (IOException ex) {
                Logger.getLogger(jPanelClientes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(jPanelClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            this.loadItems();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    
   
    /**
     * Ao clicar na linha de registro, atualiza os campos com os detalhes
     * 
     * @param evt 
     */
    private void tableClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseReleased
        int row = this.tableClientes.getSelectedRow();
        long ID = (long) this.tableClientes.getModel().getValueAt(row, 0);
        Cliente cli = new Cliente();
        try {
            Cliente c = (Cliente) cli.findBy(ID);
            this.jLabelCPF.setText("CPF: " + c.getFormatCPF());
            this.jLabelNome.setText(c.getFullName());
            this.jLabelEmail.setText("E-mail: " + c.getEmail());
            this.jLabelEndereco.setText(c.getEndereco());
            this.jLabelSexo.setText(c.getFormatSexo());
        } catch (IOException ex) {
            Logger.getLogger(jPanelClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableClientesMouseReleased
    
    /**
     * Atualiza os campos de detalhes ao pressionar as teclas "Para cima" ou "Para baixo"
     * 
     * @param evt 
     */
    private void tableClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableClientesKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
            this.tableClientesMouseReleased(null);
        }
    }//GEN-LAST:event_tableClientesKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JButton btnOdernar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JPanel jPanelDetalhes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextFile;
    private javax.swing.JTable tableClientes;
    // End of variables declaration//GEN-END:variables
}
