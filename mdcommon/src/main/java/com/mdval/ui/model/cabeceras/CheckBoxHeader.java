package com.mdval.ui.model.cabeceras;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4465458852406204136L;
	protected CheckBoxHeader rendererComponent;
	protected int column;
	protected boolean mousePressed = false;
	
	private String text;

	public CheckBoxHeader(ItemListener itemListener) {
		rendererComponent = this;
		rendererComponent.addItemListener(itemListener);
	}
	
	public CheckBoxHeader(ItemListener itemListener, String text, Boolean selected) {
		rendererComponent = this;
		rendererComponent.addItemListener(itemListener);
		this.text = text;
		this.setSelected(selected);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (table != null) {
			JTableHeader header = table.getTableHeader();
			if (header != null) {
				rendererComponent.setForeground(header.getForeground());
				rendererComponent.setBackground(header.getBackground());
				rendererComponent.setFont(new Font("Dialog", 0, 12));
				rendererComponent.setHorizontalAlignment(JLabel.CENTER);
				rendererComponent.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
				rendererComponent.setBorderPainted(true);
				header.addMouseListener(rendererComponent);
			}
		}
		setColumn(column);
		rendererComponent.setText(text);
		return rendererComponent;
	}

	protected void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	protected void handleClickEvent(MouseEvent e) {
		if (mousePressed) {
			mousePressed = false;
			JTableHeader header = (JTableHeader) (e.getSource());
			JTable tableView = header.getTable();
			TableColumnModel columnModel = tableView.getColumnModel();
			int viewColumn = columnModel.getColumnIndexAtX(e.getX());
			int column = tableView.convertColumnIndexToModel(viewColumn);

			if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
				doClick();
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		handleClickEvent(e);
		((JTableHeader) e.getSource()).repaint();
	}

	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}