package view;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.Util;

/**
 * Cria um modal com uma tabela cujas linhas são as opções de escolha para uma
 * busca que retorna mais de um registro
 */
public class JSearchChooseOption extends JDialog{

    private static final long serialVersionUID = 1L;
    protected boolean isConfirmed;
    private String selectedValue = "" ;
    private JTable table;
    private JButton btnApply;
    private JButton btnCancel;
    private JLabel lblInternalTitle;
    
    /**
     * @param owner
     */
    public JSearchChooseOption(Frame owner){
        super(owner);
        initialize();
    }
    
     /**
     * Permite alterar o título interno da modal
     * 
     * @param title 
     */
    public void setInternalTitle(String title){
        if( title == null ) return ;
        this.lblInternalTitle.setText(title);
    }
    
    /**
     * Define os dados a serem mostrado na table
     * 
     * @param model 
     */
    public void setTableModel(TableModel model){
        this.table.setModel(model);
    }
    
    /**
     * Define os dados a serem mostrado na table
     * 
     * @param model 
     */
    public void setTableModel(TableModel model, int[] widthColumns){
        this.setTableModel(model);
        if( widthColumns == null ) return ;
        
        if (this.table.getColumnModel().getColumnCount() > 0) {
            this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            for (int i = 0; i < widthColumns.length; i++) {
                if(i > this.table.getColumnCount() - 1) break;
                this.table.getColumnModel().getColumn(i).setPreferredWidth(widthColumns[i]);   
            }
        }
    }
    
    public void getSelectedRow(){
        this.table.getSelectedRow();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize(){
        initComponents();
        this.setSize(680, 450);
        this.setLocationRelativeTo(null);
    }
    
    public String getSelectedValue(){
        return this.selectedValue;
    }
    
    /**
     * Cria os componentes gráficos
     */
    private void initComponents() {
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        this.table = new javax.swing.JTable();
        this.lblInternalTitle = new javax.swing.JLabel();
        JPanel mainPane = new javax.swing.JPanel();

        this.table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Código", "Cliente", "Data venda", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectedValue = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            }
       });
        
        jScrollPane1.setViewportView(this.table);
        if (this.table.getColumnModel().getColumnCount() > 0) {
            this.table.getColumnModel().getColumn(0).setResizable(false);
            this.table.getColumnModel().getColumn(1).setResizable(false);
            this.table.getColumnModel().getColumn(2).setResizable(false);
            this.table.getColumnModel().getColumn(3).setResizable(false);
        }

        lblInternalTitle.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        lblInternalTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInternalTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/Search-25.png"))); // NOI18N
        lblInternalTitle.setText("Resultados da pesquisa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.getBtnCancel())
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.getBtnApply()))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                        .addComponent(lblInternalTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblInternalTitle)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApply)
                    .addComponent(btnCancel))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        
        mainPane.setVisible(true);
        this.add(mainPane);
       
    }// </editor-fold>          

    /**
     * Retorna o botão Apply com as suas características e eventos
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getBtnApply(){
        if (btnApply == null){
            btnApply = new JButton();
            btnApply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/content-download.png"))); // NOI18N
            btnApply.setMnemonic('a');
            btnApply.setText("Aplicar");
            btnApply.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent e){
                    if(getSelectedValue().isEmpty()){
                        Util.showMessage("Selecione um item para aplicar.", "Selecionar item", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    isConfirmed = true;
                    setVisible(false);
                }
            });
        }
        
        return btnApply;
    }

    /**
     * Cria o botão cancelar com suas respectivas características e eventos
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getBtnCancel(){
        if (btnCancel == null){
            btnCancel = new JButton();
            btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/24x24/windows.png"))); // NOI18N
            btnCancel.setMnemonic('c');
            btnCancel.setText("Cancelar");
            btnCancel.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent e){
                    isConfirmed = false;
                    setVisible(false);
                }
            });
        }
        return btnCancel;
    }
    
    /**
     * Mostra a janela na tela (Modal)
     * 
     * @return 
     */
    public JSearchChooseOption display(){        
        isConfirmed = false; 
        setModal(true);        
        setVisible(true);      
        return this; 
    }
}
