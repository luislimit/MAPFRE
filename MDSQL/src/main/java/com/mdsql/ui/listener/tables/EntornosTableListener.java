package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Entorno;
import com.mdsql.ui.PantallaMantenimientoEntornos;
import com.mdsql.ui.model.EntornoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntornosTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaMantenimientoEntornos pantallaMantenimientoEntornos;

	public EntornosTableListener(PantallaMantenimientoEntornos pantallaMantenimientoEntornos) {
		super();
		this.pantallaMantenimientoEntornos = pantallaMantenimientoEntornos;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		EntornoTableModel tableModel = (EntornoTableModel) pantallaMantenimientoEntornos.getTblMantenimientoEntornos().getModel();
		
		Entorno seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaMantenimientoEntornos.getTxtBBDD().setText(seleccionado.getBbdd());
			pantallaMantenimientoEntornos.getTxtEsquema().setText(seleccionado.getEsquema());
			pantallaMantenimientoEntornos.getTxtPassword().setText(seleccionado.getPassword());
			pantallaMantenimientoEntornos.getTxtComentario().setText(seleccionado.getComentario());

			Boolean habilitada = AppHelper.normalizeCheckValue(seleccionado.getHabilitado());
			pantallaMantenimientoEntornos.getChkHabilitada().setSelected(habilitada);

			pantallaMantenimientoEntornos.getBtnGrabar().setEnabled(Boolean.TRUE);
		}
	}

	
}
