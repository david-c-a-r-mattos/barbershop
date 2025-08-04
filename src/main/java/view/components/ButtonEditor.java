package view.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Clients;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private int row;
    private JTable table;
    private String buttonType;

    public ButtonEditor(JCheckBox checkBox, String buttonType) {
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
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        isPushed = true;
        return button; // Retorna o botão com o texto já definido
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            if ("edit".equals(buttonType)) {
                int id = (int) table.getValueAt(row, 0);
                JOptionPane.showMessageDialog(button, "Editando cliente ID: " + id, "Editar", JOptionPane.INFORMATION_MESSAGE);
            } else if ("delete".equals(buttonType)) {
                int id = (int) table.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(button, 
                    "Deseja realmente excluir o cliente ID: " + id + "?", 
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((Clients) SwingUtilities.getWindowAncestor(table)).getController().deleteClient(id);
                }
            }
        }
        isPushed = false;
        return button.getText(); // Retorna o texto do botão
    }
}
