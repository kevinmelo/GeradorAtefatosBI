package objects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class ListRender extends JLabel implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		setOpaque(true);
		setFont(new Font("Consolas", Font.PLAIN, 14));

		if (value instanceof Table) {
			Table table = (Table) value;
			setText(table.getTabela());

			if (isSelected) {
				setBackground(new Color(89, 171, 227));
				if (table.isCube()) {
					setForeground(new Color(245, 215, 110));
				} else {
					setForeground(list.getSelectionForeground());
				}
			} else {
				setBackground(new Color(255, 255, 255));
				if (table.isCube()) {
					setForeground(new Color(150, 40, 27));
				} else {
					setForeground(new Color(0, 0, 0));
				}
			}
		} else if (value instanceof String) {
			String column = (String) value;
			setText(column);
			if (isSelected) {
				setBackground(new Color(89, 171, 227));
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(new Color(255, 255, 255));
				setForeground(new Color(0, 0, 0));
			}
		}
		return this;
	}

}
