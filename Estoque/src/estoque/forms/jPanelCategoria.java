/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoque.forms;

import estoque.Categoria;
import estoque.IOModelInterface;
import estoque.Produto;
import estoque.util;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

/**
 *
 * @author gilmar
 */
public final class jPanelCategoria extends javax.swing.JPanel {

    /**
     * Creates new form jPanelProdutos
     */
    public jPanelCategoria() {
        initComponents();
        this.loadItems();
    }
    
    public void loadItems(){
        Categoria cat = new Categoria();
        
        TableModel model = cat.getTableModel() ;
        this.tableCategoria.setModel(model);
        this.tableCategoria.setAutoCreateRowSorter(true);
        this.tableCategoria.enableInputMethods(false);
        this.jTextFile.setText(String.format("Arquivo: %s" , cat.getFileName()));
    }
    
    public void addCategoria() {
        while (true) {
            try {
                long codigo = (long) Integer.parseInt(util.showInput("Digite o código da categoria.")) ;
                String nome = util.showInput("Digite o nome.");
                String descricao = util.showInput("Digite a descrição.");

                Categoria cat = new Categoria(codigo, nome, descricao);
                cat.save();
                this.loadItems();
                
                util.showMessage("Produto salvo com sucesso");
                if ( util.showConfirm( "Gostaria de cadastrar mais um novo produto?" , "Estoque") == false ) {
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
        tableCategoria = new javax.swing.JTable();
        btnAddCategoria = new javax.swing.JButton();
        btnDelCategoria = new javax.swing.JButton();
        btnBuscaCategoria = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFile = new javax.swing.JTextPane();
        btnLoadFile = new javax.swing.JButton();

        tableCategoria.setAutoCreateRowSorter(true);
        tableCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        tableCategoria.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableCategoria.setName(""); // NOI18N
        jScrollPane2.setViewportView(tableCategoria);

        btnAddCategoria.setMnemonic('a');
        btnAddCategoria.setText("Adicionar");
        btnAddCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddCategoriaMouseReleased(evt);
            }
        });
        btnAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoriaActionPerformed(evt);
            }
        });

        btnDelCategoria.setMnemonic('x');
        btnDelCategoria.setText("Excluir");
        btnDelCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCategoriaActionPerformed(evt);
            }
        });

        btnBuscaCategoria.setMnemonic('l');
        btnBuscaCategoria.setText("Localizar");
        btnBuscaCategoria.setToolTipText("");
        btnBuscaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBuscaCategoriaMouseReleased(evt);
            }
        });

        jLabelTitulo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Categoria de Produtos");

        jTextFile.setBackground(new java.awt.Color(254, 240, 156));
        jTextFile.setText("Dados oriundos do arquivo:");
        jScrollPane1.setViewportView(jTextFile);

        btnLoadFile.setMnemonic('c');
        btnLoadFile.setToolTipText("");
        btnLoadFile.setLabel("Carregar arquivo...");
        btnLoadFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoadFileMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnAddCategoria)
                                .addGap(6, 6, 6)
                                .addComponent(btnDelCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscaCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLoadFile))
                            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCategoria)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelCategoria)
                        .addComponent(btnBuscaCategoria)
                        .addComponent(btnLoadFile)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnLoadFile.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscaCategoriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaCategoriaMouseReleased
    }//GEN-LAST:event_btnBuscaCategoriaMouseReleased

    private void btnAddCategoriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCategoriaMouseReleased
        this.addCategoria();
    }//GEN-LAST:event_btnAddCategoriaMouseReleased

    private void btnLoadFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadFileMouseReleased
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File outfile = chooser.getSelectedFile();
             this.jTextFile.setText(String.format( "Dados extraidos do arquivo: %s" , outfile.getPath()));
        }
    }//GEN-LAST:event_btnLoadFileMouseReleased

    private void btnDelCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCategoriaActionPerformed
        int row = this.tableCategoria.getSelectedRow();
                
        if(row == -1){
            util.showMessage("Selecione um item para excluir.");
            return;
        }
       
        long ID = (long) this.tableCategoria.getModel().getValueAt(row, 0);
        String item = (String) this.tableCategoria.getModel().getValueAt(row, 1);
        String message = String.format("Tem certeza que deseja excluir o item '%s'?",item);
       if(util.showConfirm(message, "Remover categoria?")) {
           IOModelInterface cat = new Categoria();
            try {
                cat.remove(ID);
            } catch (IOException ex) {
                Logger.getLogger(jPanelCategoria.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(jPanelCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
           this.loadItems();
       }
    }//GEN-LAST:event_btnDelCategoriaActionPerformed

    private void btnAddCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCategoria;
    private javax.swing.JButton btnBuscaCategoria;
    private javax.swing.JButton btnDelCategoria;
    private javax.swing.JButton btnLoadFile;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextFile;
    private javax.swing.JTable tableCategoria;
    // End of variables declaration//GEN-END:variables
}
