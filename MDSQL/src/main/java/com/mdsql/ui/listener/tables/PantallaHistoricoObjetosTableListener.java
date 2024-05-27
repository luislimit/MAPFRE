package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.HistoricoProceso;
import com.mdsql.ui.PantallaHistoricoCambios;
import com.mdsql.ui.model.HistoricoObjetoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaHistoricoObjetosTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaHistoricoCambios pantallaHistoricoCambios;

	public PantallaHistoricoObjetosTableListener(PantallaHistoricoCambios pantallaHistoricoCambios) {
		super();
		this.pantallaHistoricoCambios = pantallaHistoricoCambios;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		HistoricoObjetoTableModel tableModel = (HistoricoObjetoTableModel) pantallaHistoricoCambios.getTblHistoricoObjetos().getModel();
		
		HistoricoProceso seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaHistoricoCambios.setSeleccionado(seleccionado);
			pantallaHistoricoCambios.getBtnResumen().setEnabled(Boolean.TRUE);
			pantallaHistoricoCambios.getBtnVerDetalle().setEnabled(Boolean.TRUE);
		}
	}

	
}
