package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JMenuItem;

import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.ui.PantallaEjecutarScriptInicialEntornoPrueba;
import com.mdsql.ui.PantallaMantenimientoEntornos;
import com.mdsql.ui.PantallaMantenimientoEntornosPrueba;
import com.mdsql.ui.PantallaMantenimientoHistorico;
import com.mdsql.ui.PantallaMantenimientoNotasModelos;
import com.mdsql.ui.PantallaMantenimientoVariables;
import com.mdsql.ui.PantallaPermisosGeneralesporModeloporTipoObjeto;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.FrameSupport;

/**
 * @author federico
 *
 */
public class MenuMantenimientoActionListener extends ListenerSupport implements ActionListener {

	private FrameSupport framePrincipal;

	/**
	 * @param framePrincipal
	 */
	public MenuMantenimientoActionListener(FrameSupport framePrincipal) {
		this.framePrincipal = framePrincipal;
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		String actionCommand = item.getActionCommand();

		if (MDSQLConstants.MNU_PERMISOS_GENERALES.equals(actionCommand)) {
			evtPermisosGenerales();
		}

		if (MDSQLConstants.MNU_CONSULTA_PERMISOS.equals(actionCommand)) {
			evtPermisosObjeto();
		}

		if (MDSQLConstants.MNU_MANTENIMIENTO_HISTORICO.equals(actionCommand)) {
			evtMntoHistorico();
		}

		if (MDSQLConstants.MNU_NOTAS_MODELOS.equals(actionCommand)) {
			evtNotasModelos();
		}

		if (MDSQLConstants.MNU_ENTORNOS.equals(actionCommand)) {
			evtMntoEntornos();
		}
		
		if (MDSQLConstants.MNU_MANTENIMIENTO_ENTORNOS_PRUEBAS.equals(actionCommand)) {
			evtMntoEntornosPruebas();
		}
		
		if (MDSQLConstants.MNU_EJECUCION_SCRIPT_INICIAL.equals(actionCommand)) {
			evtScriptInicial();
		}

		if (MDSQLConstants.MNU_VARIABLES.equals(actionCommand)) {
			evtMntoVariables();
		}
	}

	private void evtScriptInicial() {
		Map<String, Object> params = new HashMap<>();

		PantallaEjecutarScriptInicialEntornoPrueba ejecutarScriptInicialEntornoPrueba = (PantallaEjecutarScriptInicialEntornoPrueba) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_MNTO_SCRIPT_INICIAL, params);
		MDSQLUIHelper.show(ejecutarScriptInicialEntornoPrueba);
	}

	private void evtMntoVariables() {
		Map<String, Object> params = new HashMap<>();
		params.put("opcion", "mntoVariables");
		Modelo seleccionado = getModelo(params);

		if (!Objects.isNull(seleccionado)) {
			params = new HashMap<>();

			params.put("modelo", seleccionado);

			PantallaMantenimientoVariables pantallaMantenimientoVariables = (PantallaMantenimientoVariables) MDSQLUIHelper.createDialog(framePrincipal,
					MDSQLConstants.CMD_MNTO_VARIABLES, params);
			MDSQLUIHelper.show(pantallaMantenimientoVariables);
		}
	}

	private void evtMntoEntornos() {
		Map<String, Object> params = new HashMap<>();

		PantallaMantenimientoEntornos pantallaMantenimientoEntornos = (PantallaMantenimientoEntornos) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_MNTO_ENTORNOS, params);
		MDSQLUIHelper.show(pantallaMantenimientoEntornos);
	}
	
	private void evtMntoEntornosPruebas() {
		Map<String, Object> params = new HashMap<>();

		PantallaMantenimientoEntornosPrueba pantallaMantenimientoEntornosPrueba = (PantallaMantenimientoEntornosPrueba) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_MNTO_ENTORNOS_PRUEBAS, params);
		MDSQLUIHelper.show(pantallaMantenimientoEntornosPrueba);
	}

	private void evtNotasModelos() {
		Map<String, Object> params = new HashMap<>();
		PantallaMantenimientoNotasModelos pantallaMantenimientoNotasModelos = (PantallaMantenimientoNotasModelos) MDSQLUIHelper.createDialog(framePrincipal,
					MDSQLConstants.CMD_MNTO_NOTAS, params);
		MDSQLUIHelper.show(pantallaMantenimientoNotasModelos);
	}

	private void evtMntoHistorico() {
		Map<String, Object> params = new HashMap<>();

		PantallaMantenimientoHistorico pantallaMantenimientoHistorico = (PantallaMantenimientoHistorico) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_MNTO_HISTORICO, params);
		MDSQLUIHelper.show(pantallaMantenimientoHistorico);
	}

	/**
	 * 
	 */
	private void evtPermisosGenerales() {
		Map<String, Object> params = new HashMap<>();
		params.put("opcion", "mntoPermisosGenerales");
		Modelo seleccionado = getModelo(params);

		if (!Objects.isNull(seleccionado)) {
			params = new HashMap<>();
			params.put("modelo", seleccionado);

			PantallaPermisosGeneralesporModeloporTipoObjeto pantallaPermisosGeneralesporModeloporTipoObjeto = (PantallaPermisosGeneralesporModeloporTipoObjeto) MDSQLUIHelper.createDialog(framePrincipal,
					MDSQLConstants.CMD_PERMISOS_GENERALES, params);
			MDSQLUIHelper.show(pantallaPermisosGeneralesporModeloporTipoObjeto);
		}
	}

	private void evtPermisosObjeto() {
		/*
		Modelo seleccionado = getModelo();
		Map<String, Object> params = new HashMap<>();

		params.put("modelo", seleccionado);

		PantallaDetallePermisosPorObjeto pantallaDetallePermisosPorObjeto = (PantallaDetallePermisosPorObjeto) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_PERMISOS_OBJETO, params);
		MDSQLUIHelper.show(pantallaDetallePermisosPorObjeto);
		 */
	}

	private Modelo getModelo(Map<String, Object> params) {
		PantallaSeleccionModelos pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(framePrincipal,
				MDSQLConstants.CMD_SEARCH_MODEL, params);
		MDSQLUIHelper.show(pantallaSeleccionModelos);
		Modelo seleccionado = pantallaSeleccionModelos.getSeleccionado();
		return seleccionado;
	}
}
