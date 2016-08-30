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
	private static final Color LIGHTNING_YELLOW = new Color(245, 171, 53);
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color BLACK = new Color(0, 0, 0);
	private static final Color OLD_BRICK = new Color(150, 40, 27);
	private static final Color PLUM = new Color(145, 61, 136);
	private static final Color SALEM = new Color(30, 130, 76);

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		setOpaque(true);
		setFont(new Font("Consolas", Font.PLAIN, 14));

		if (value instanceof Table) {
			Table table = (Table) value;
			setText(table.getTabela());

			if (isSelected) {
				setBackground(PICTON_BLUE);
				if (table.isCube()) {
					setForeground(LIGHTNING_YELLOW);
				} else {
					setForeground(list.getSelectionForeground());
				}
			} else {
				setBackground(WHITE);
				if (table.isCube()) {
					setForeground(OLD_BRICK);
				} else {
					setForeground(BLACK);
				}
			}
		} else if (value instanceof Column) {
			Column column = (Column) value;
			setText(column.getName());
			if (isSelected) {
				setBackground(PICTON_BLUE);
				if (column.isMeasure()) {
					setForeground(PLUM);
				} else if (column.isPrimaryKey()) {
					setForeground(LIGHTNING_YELLOW);
				} else {
					setForeground(list.getSelectionForeground());
				}

			} else {
				setBackground(WHITE);
				if (column.isMeasure()) {
					setForeground(SALEM);
				} else if (column.isPrimaryKey()) {
					setForeground(OLD_BRICK);
				} else {
					setForeground(BLACK);
				}
			}
		}
		return this;
	}

}
