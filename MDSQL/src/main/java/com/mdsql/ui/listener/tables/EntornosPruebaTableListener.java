package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.EntornoPrueba;
import com.mdsql.ui.PantallaMantenimientoEntornosPrueba;
import com.mdsql.ui.model.EntornosPruebaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntornosPruebaTableListener extends ListenerSupport implements ListSelectionListener {
	
	private PantallaMantenimientoEntornosPrueba pantallaMantenimientoEntornosPrueba;

	public EntornosPruebaTableListener(PantallaMantenimientoEntornosPrueba pantallaMantenimientoEntornosPrueba) {
		super();
		this.pantallaMantenimientoEntornosPrueba = pantallaMantenimientoEntornosPrueba;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		EntornosPruebaTableModel tableModel = (EntornosPruebaTableModel) pantallaMantenimientoEntornosPrueba.getTblMantenimientoEntornosPrueba().getModel();
		
		EntornoPrueba seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaMantenimientoEntornosPrueba.getTxtNombreEntorno().setText(seleccionado.getNombreEntorno());
			pantallaMantenimientoEntornosPrueba.getTxtBBDD().setText(seleccionado.getBbdd());
			pantallaMantenimientoEntornosPrueba.getTxtEsquema().setText(seleccionado.getEsquema());
			pantallaMantenimientoEntornosPrueba.getTxtTablespace().setText(seleccionado.getTablespace());
			pantallaMantenimientoEntornosPrueba.getTxtGradoparal().setText(seleccionado.getGradoParal());
			pantallaMantenimientoEntornosPrueba.getTxtDescripcion().setText(seleccionado.getDescripcion());
			
			Boolean habilitada = AppHelper.normalizeCheckValue(seleccionado.getMcaHabilitado());
			pantallaMantenimientoEntornosPrueba.getChkHabilitada().setSelected(habilitada);
			
			pantallaMantenimientoEntornosPrueba.getBtnGuardar().setEnabled(Boolean.TRUE);
		}
	}
}
