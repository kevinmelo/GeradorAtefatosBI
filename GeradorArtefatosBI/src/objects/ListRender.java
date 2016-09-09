package objects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class ListRender extends JLabel implements ListCellRenderer<Object> {

	private static final Color PICTON_BLUE = new Color(89, 171, 227);
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color BLACK = new Color(0, 0, 0);

	private static final Color CUBE = new Color(190, 144, 212);
	private static final Color PRIMARY_KEY = new Color(30, 139, 195);
	private static final Color MEASURE = new Color(30, 130, 76);
	private static final Color FOREIGN_KEY = new Color(210, 77, 87);

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		setOpaque(true);
		setFont(new Font("Consolas", Font.PLAIN, 14));

		if (value instanceof Table) {
			Table table = (Table) value;
			setText(table.getName());
			if (isSelected) {
				setBackground(PICTON_BLUE);
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(WHITE);
				if (table.isCube()) {
					setForeground(CUBE);
				} else {
					setForeground(BLACK);
				}
			}
		} else if (value instanceof Column) {
			Column column = (Column) value;
			setText(column.getName());
			if (isSelected) {
				setBackground(PICTON_BLUE);
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(WHITE);
				if (column.isMeasure()) {
					setForeground(MEASURE);
				} else if (column.isPrimaryKey()) {
					setForeground(PRIMARY_KEY);
				} else if (column.isForeignKey()) {
					setForeground(FOREIGN_KEY);
				} else {
					setForeground(BLACK);
				}
			}
		}
		return this;
	}

}
