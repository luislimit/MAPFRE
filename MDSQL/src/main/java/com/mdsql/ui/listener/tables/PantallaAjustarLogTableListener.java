package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.LogEjecucion;
import com.mdsql.ui.PantallaAjustarLogEjecucion;
import com.mdsql.ui.model.AjustarLogEjecucionTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PantallaAjustarLogTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaAjustarLogEjecucion pantallaAjustarLogEjecucion;

	public PantallaAjustarLogTableListener(PantallaAjustarLogEjecucion pantallaAjustarLogEjecucion) {
		super();
		this.pantallaAjustarLogEjecucion = pantallaAjustarLogEjecucion;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		AjustarLogEjecucionTableModel tableModel = (AjustarLogEjecucionTableModel) pantallaAjustarLogEjecucion.getTblAjustarLog().getModel();
		
		LogEjecucion seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			
			Boolean consulta = (Boolean) pantallaAjustarLogEjecucion.getParams().get("consulta");
			
			if (Boolean.FALSE.equals(consulta)) {
				pantallaAjustarLogEjecucion.setSeleccionado(seleccionado);
				pantallaAjustarLogEjecucion.getBtnEliminar().setEnabled(Boolean.TRUE);
				pantallaAjustarLogEjecucion.getTxtComentario().setEnabled(Boolean.TRUE);
			}
		}
	}

	
}
