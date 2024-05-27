package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.service.CuadreService;
import com.mdsql.ui.PantallaVerCuadresScript;
import com.mdsql.ui.model.CuadresObjetosTableModel;
import com.mdsql.ui.model.CuadresOperacionesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observer;

public class PantallaVerCuadresScriptListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaVerCuadresScript pantallaVerCuadresScript;

	public PantallaVerCuadresScriptListener(PantallaVerCuadresScript pantallaVerCuadresScript) {
		super();
		this.pantallaVerCuadresScript = pantallaVerCuadresScript;
	}

	public void addObservador(Observer o) {
		this.addObserver(o);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_VER_ERRORES_SCRIPT_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			cancelar();
		}
	}

	@Override
	public void onLoad() {
		try {
			CuadreService cuadreService = (CuadreService) getService(MDSQLConstants.CUADRE_SERVICE);

			Script script = (Script) pantallaVerCuadresScript.getParams().get("script");
			Proceso proceso = (Proceso) pantallaVerCuadresScript.getParams().get("proceso");
			BigDecimal orden = (BigDecimal) pantallaVerCuadresScript.getParams().get("orden");

			BigDecimal idProceso = proceso.getIdProceso();
			BigDecimal numeroOrden = (!Objects.isNull(script)) ? script.getNumeroOrden() : orden;
			List<CuadreObjeto> cuadreObjetos = cuadreService.consultaCuadreOperacionesObjetoScript(idProceso,
					numeroOrden);
			List<CuadreOperacion> cuadreOperaciones = cuadreService.consultaCuadreOperacionesScript(idProceso,
					numeroOrden);

			populateModels(cuadreOperaciones, cuadreObjetos);

		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaVerCuadresScript.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	private void cancelar() {
		pantallaVerCuadresScript.dispose();
	}

	/**
	 * @param cuadreOperaciones
	 * @param cuadreObjetos
	 */
	private void populateModels(List<CuadreOperacion> cuadreOperaciones, List<CuadreObjeto> cuadreObjetos) {
		// Obtiene el modelo y lo actualiza
		CuadresOperacionesTableModel tableModelOperaciones = (CuadresOperacionesTableModel) pantallaVerCuadresScript
				.getTblOperaciones().getModel();
		tableModelOperaciones.setData(cuadreOperaciones);

		// Obtiene el modelo y lo actualiza
		CuadresObjetosTableModel tableModelObjetos = (CuadresObjetosTableModel) pantallaVerCuadresScript
				.getTblObjetos().getModel();
		tableModelObjetos.setData(cuadreObjetos);

	}
}
