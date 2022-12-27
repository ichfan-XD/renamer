package me.depan.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Test extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Test() {
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, "ddfgfd"},
			},
			new String[] {
				"ddsd", "xsd"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		add(table);

	}

}
