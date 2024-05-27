package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Script;
import com.mdsql.ui.PantallaEjecutarScripts;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ScriptsTableListener extends ListenerSupport implements ListSelectionListener {

	protected PantallaEjecutarScripts pantallaEjecutarScripts;

	public ScriptsTableListener(PantallaEjecutarScripts pantallaEjecutarScripts) {
		super();
		this.pantallaEjecutarScripts = pantallaEjecutarScripts;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();

		Script seleccionado = getScriptSeleccionado(index);

		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			pantallaEjecutarScripts.setSeleccionado(seleccionado);

			pantallaEjecutarScripts.enableButtons(Boolean.FALSE);
			pantallaEjecutarScripts.getBtnDetalleScript().setEnabled(Boolean.TRUE);

			enableButtons(seleccionado);
			
			// FIXME - For develop purposes
//			pantallaEjecutarScripts.enableButtons(Boolean.TRUE);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void enableButtons(Script seleccionado) {
		// Según el estado del script, habilitar el resto de botones
		if ("Ejecutado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerCuadres().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnDescartar().setEnabled(Boolean.TRUE);
		}

		if ("Error".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnReparar().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnVerErrores().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnExcepcion().setEnabled(Boolean.TRUE);
			
		}

		if ("Descuadrado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnReparar().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnVerCuadres().setEnabled(Boolean.TRUE);
		}

		if ("Reparado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerErrores().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
		}

		if ("Descartado".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerErrores().setEnabled(Boolean.TRUE);
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
		}

		if ("Excepción".equals(seleccionado.getDescripcionEstadoScript())) {
			pantallaEjecutarScripts.getBtnVerLog().setEnabled(Boolean.TRUE);
		}
	}

	/**
	 * @param index
	 * @return
	 */
	protected abstract Script getScriptSeleccionado(Integer index);

}
