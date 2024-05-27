package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.ui.PantallaConsultaPeticiones;
import com.mdsql.ui.model.ConsultaPeticionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaConsultaPeticionesTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaConsultaPeticiones pantallaConsultaPeticiones;

	public PantallaConsultaPeticionesTableListener(PantallaConsultaPeticiones pantallaConsultaPeticiones) {
		super();
		this.pantallaConsultaPeticiones = pantallaConsultaPeticiones;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		ConsultaPeticionesTableModel tableModel = (ConsultaPeticionesTableModel) pantallaConsultaPeticiones
				.getTblPeticiones().getModel();
		
		Proceso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaConsultaPeticiones.setSeleccionado(seleccionado);
			pantallaConsultaPeticiones.getBtnCargar().setEnabled(Boolean.TRUE);
		}
	}

	
}
