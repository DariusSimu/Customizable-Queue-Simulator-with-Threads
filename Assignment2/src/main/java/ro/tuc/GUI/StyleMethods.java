package ro.tuc.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class StyleMethods extends JFrame {
    public void styleTitle(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.DARK_GRAY);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void styleLabel(JLabel label) {
        label.setForeground(Color.DARK_GRAY);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void styleTextField(JTextField textField) {
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        textField.setPreferredSize(new Dimension(200,30));
    }
    public void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120,30));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
    }
    public void addPlaceholder(JTextField textField, String placeholderText) {
        textField.setText(placeholderText);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    public void styleTable(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.DARK_GRAY);
        table.setGridColor(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }
    public void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(Color.LIGHT_GRAY);
        comboBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(200, 30));
    }

}
