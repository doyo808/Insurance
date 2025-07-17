package customer.payment.gui.components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class UnpaidTablePanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedIndex = -1;
    
    private List<String[]> testmutipledata;

    public UnpaidTablePanel() {
        this(List.of(
            new String[]{"자동이체", "실손보험", "123451", "150000", "2025.05", "2025-07-31"},
            new String[]{"가상계좌", "실손보험", "123452", "90000", "2025.06", "2025-08-15"},
            new String[]{"카드결제", "치아보험", "123453", "120000", "2025.07", "2025-09-30"}
        ));
    }

    public UnpaidTablePanel(List<String[]> dataList) {
        setLayout(new BorderLayout());

        String[] columnNames = {"구분", "상품이름", "계약번호", "미납액", "납입월분", "납입기한", "선택"};

        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 6 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        for (String[] row : dataList) {
            Object[] newRow = new Object[row.length + 1];
            System.arraycopy(row, 0, newRow, 0, row.length);
            newRow[row.length] = false;  // 선택 초기값
            tableModel.addRow(newRow);
        }

        table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 13));
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(Color.CYAN);

        // 선택 컬럼 설정
        TableColumn selectColumn = table.getColumnModel().getColumn(6);
        selectColumn.setCellRenderer(new RadioButtonRenderer());
        selectColumn.setCellEditor(new RadioButtonEditor());

        // 너비 30px로 고정
        selectColumn.setPreferredWidth(30);
        selectColumn.setMaxWidth(30);
        selectColumn.setMinWidth(30);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(1000, 400));
    }

    public void addRow(String[] rowData) {
        Object[] newRow = new Object[rowData.length + 1];
        System.arraycopy(rowData, 0, newRow, 0, rowData.length);
        newRow[rowData.length] = false;
        tableModel.addRow(newRow);
    }

    public String[] getSelectedRowData() {
        if (selectedIndex >= 0 && selectedIndex < table.getRowCount()) {
            int columnCount = table.getColumnCount() - 1;
            String[] data = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                data[i] = String.valueOf(table.getValueAt(selectedIndex, i));
            }
            return data;
        }
        return null;
    }

    // ======================= 라디오 버튼 렌더러 =======================
    private static class RadioButtonRenderer extends JRadioButton implements TableCellRenderer {
        public RadioButtonRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setSelected(Boolean.TRUE.equals(value));
            return this;
        }
    }

    // ======================= 라디오 버튼 에디터 =======================
    private class RadioButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private final JRadioButton radioButton = new JRadioButton();
        private int editingRow = -1;

        public RadioButtonEditor() {
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.addActionListener(e -> {
                for (int i = 0; i < table.getRowCount(); i++) {
                    if (i != editingRow) {
                        table.setValueAt(false, i, 6);
                    }
                }
                selectedIndex = editingRow;
                fireEditingStopped();
            });
        }

        @Override
        public Object getCellEditorValue() {
            return radioButton.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            editingRow = row;
            radioButton.setSelected(Boolean.TRUE.equals(value));
            return radioButton;
        }
    }
}