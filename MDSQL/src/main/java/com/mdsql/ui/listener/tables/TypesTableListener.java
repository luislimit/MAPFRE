package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Type;
import com.mdsql.ui.PantallaEjecutarTypes;
import com.mdsql.ui.model.TypesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TypesTableListener extends ListenerSupport implements ListSelectionListener {

	protected PantallaEjecutarTypes pantallaEjecutarTypes;

	public TypesTableListener(PantallaEjecutarTypes pantallaEjecutarTypes) {
		super();
		this.pantallaEjecutarTypes = pantallaEjecutarTypes;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		TypesTableModel tableModelTypes = (TypesTableModel) pantallaEjecutarTypes.getTblTypes().getModel();

		Type seleccionado = tableModelTypes.getSelectedRow(index);

		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaEjecutarTypes.setSeleccionado(seleccionado);

			pantallaEjecutarTypes.enableButtons(Boolean.FALSE);
			enableButtons(seleccionado);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void enableButtons(Type seleccionado) {
		// Según el estado del script, habilitar el resto de botones
		if ("Ejecutado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarTypes.getBtnVerCuadres().setEnabled(Boolean.TRUE);
			pantallaEjecutarTypes.getBtnVerErrores().setEnabled(Boolean.FALSE);
		}

		if ("Error".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarTypes.getBtnVerCuadres().setEnabled(Boolean.FALSE);
			pantallaEjecutarTypes.getBtnVerErrores().setEnabled(Boolean.TRUE);
			
		}

		if ("Descuadrado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarTypes.getBtnVerCuadres().setEnabled(Boolean.TRUE);
			pantallaEjecutarTypes.getBtnVerErrores().setEnabled(Boolean.FALSE);
		}
	}

}
