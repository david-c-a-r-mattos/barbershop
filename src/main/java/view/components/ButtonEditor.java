package view.components;


import Controller.ClientController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Clients;

public  class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private int row;
    private JTable table;
    private final ClientController controller;
    private final view.Clients clientsview;
    private final view.Client clientview;
    private final String buttonType;

    public ButtonEditor(JCheckBox checkBox, String buttonType, view.Clients clientsview, view.Client clientview) {
        super(checkBox);
        this.buttonType = buttonType;
        button = new JButton();
        button.setOpaque(true);
        button.setBorderPainted(false);
        
        if ("edit".equals(buttonType)) {
            button.setBackground(new Color(46, 204, 113)); // Verde
            button.setForeground(Color.WHITE);
            button.setText("Editar"); // Texto fixo para editar
        } else if ("delete".equals(buttonType)) {
            button.setBackground(new Color(231, 76, 60)); // Vermelho
            button.setForeground(Color.WHITE);
            button.setText("Excluir"); // Texto fixo para excluir
        }
        
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
        });
        this.clientview = clientview;
        this.clientsview = clientsview;
        this.controller = new ClientController(clientview, clientsview);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) 
    {
        this.table = table;
        this.row = row;
        isPushed = true;
        return button; // Retorna o botão com o texto já definido
    }

    @Override
    public Object getCellEditorValue() 
    {
        if (isPushed) 
        {
            if ("edit".equals(buttonType)) 
            {
                int id = (int) table.getValueAt(row, 0);
                try {
                    controller.editClient(id);
                } catch (SQLException ex) {
                    Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else if ("delete".equals(buttonType)) 
            {
                int id = (int) table.getValueAt(row, 0);
                
                int confirm = JOptionPane.showConfirmDialog(button, 
                    "Deseja realmente excluir o cliente ID: " + id + "?", 
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        ((Clients) SwingUtilities.getWindowAncestor(table)).getController().deleteClient(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        isPushed = false;
        return button.getText(); // Retorna o texto do botão
    }
}
