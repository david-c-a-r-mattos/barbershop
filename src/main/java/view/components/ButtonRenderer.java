package view.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer(String buttonType) {
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        
        if ("edit".equals(buttonType)) {
            setBackground(new Color(46, 204, 113)); // Verde
            setForeground(Color.WHITE);
            setText("Editar"); // Texto fixo para editar
        } else if ("delete".equals(buttonType)) {
            setBackground(new Color(231, 76, 60)); // Vermelho
            setForeground(Color.WHITE);
            setText("Excluir"); // Texto fixo para excluir
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // Mantemos o texto definido no construtor, ignorando o valor da célula
        return this;
    }
}