package com.mdsql.ui.listener.tables;

import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.ui.model.SeleccionHistoricoTableModel;
import com.mdval.ui.model.cabeceras.TableItemListener;
import com.mdval.ui.utils.TableSupport;

public class SeleccionHistoricoTableItemListener extends TableItemListener {
	
	private SeleccionHistoricoTableModel model;

	public SeleccionHistoricoTableItemListener(SeleccionHistoricoTableModel model, TableSupport table) {
		super(table);
		this.model = model;
	}

	@Override
	public void setValueAt(Boolean checked, int r, int c) {
		SeleccionHistorico row = model.getSelectedRow(r);
		
		if (0 == c || 3 == c) {
			if (model.isCellEditable(r, c) && row.getEditable()) {
				table.setValueAt(Boolean.valueOf(checked), r, c);
			}
		}
		else {
			table.setValueAt(Boolean.valueOf(checked), r, c);
		}
	}

}
