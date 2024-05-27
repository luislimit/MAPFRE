package com.mdsql.ui.listener.tables;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.CuadreObjeto;
import com.mdsql.bussiness.entities.CuadreOperacion;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.ScriptEjecutado;
import com.mdsql.bussiness.service.CuadreService;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.model.ResumenProcesadoObjetosTableModel;
import com.mdsql.ui.model.ResumenProcesadoOperacionesTableModel;
import com.mdsql.ui.model.ResumenProcesadoScriptsTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

public class ResumenProcesadoScriptsTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaResumenProcesado pantallaResumenProcesado;

	public ResumenProcesadoScriptsTableListener(PantallaResumenProcesado pantallaResumenProcesado) {
		super();
		this.pantallaResumenProcesado = pantallaResumenProcesado;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			if (e.getValueIsAdjusting())
				return;
	
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			Integer index = lsm.getMinSelectionIndex();
			
			ResumenProcesadoScriptsTableModel tableModel = (ResumenProcesadoScriptsTableModel) pantallaResumenProcesado.getTblScripts().getModel();
	
			ScriptEjecutado seleccionado = tableModel.getSelectedRow(index);
	
			if (!Objects.isNull(seleccionado)) {
				
				CuadreService cuadreService = (CuadreService) getService(MDSQLConstants.CUADRE_SERVICE);
				Proceso procesoSeleccionado = pantallaResumenProcesado.getProcesoSeleccionado();
				BigDecimal idProceso = procesoSeleccionado.getIdProceso();
				
				List<CuadreOperacion> cuadreOperaciones = cuadreService.consultaCuadreOperacionesScript(idProceso, seleccionado.getNumeroOrden());
				List<CuadreObjeto> cuadreObjetos = cuadreService.consultaCuadreOperacionesObjetoScript(idProceso, seleccionado.getNumeroOrden());
			
				populateTablaOperaciones(cuadreOperaciones);
				populateTablaObjetos(cuadreObjetos);
				
				pantallaResumenProcesado.setSeleccionado(seleccionado);
				
				pantallaResumenProcesado.getBtnVerErrores().setEnabled(Boolean.TRUE);
				pantallaResumenProcesado.getBtnDetalleScript().setEnabled(Boolean.TRUE);
				pantallaResumenProcesado.getBtnVerLog().setEnabled(Boolean.TRUE);
			}
		} catch (ServiceException se) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(se);
			MDSQLUIHelper.showPopup(pantallaResumenProcesado.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
	
	/**
	 * @param peticiones
	 */
	private void populateTablaOperaciones(List<CuadreOperacion> cuadreOperaciones) {
		// Obtiene el modelo y lo actualiza
		ResumenProcesadoOperacionesTableModel tableModel = (ResumenProcesadoOperacionesTableModel) pantallaResumenProcesado
				.getTblOperaciones().getModel();
		tableModel.setData(cuadreOperaciones);
		
		pantallaResumenProcesado.getTblOperaciones().forceRepaintColumn(0);
	}
	
	/**
	 * @param peticiones
	 */
	private void populateTablaObjetos(List<CuadreObjeto> cuadreObjetos) {
		// Obtiene el modelo y lo actualiza
		ResumenProcesadoObjetosTableModel tableModel = (ResumenProcesadoObjetosTableModel) pantallaResumenProcesado
				.getTblObjetos().getModel();
		tableModel.setData(cuadreObjetos);
		
		pantallaResumenProcesado.getTblObjetos().forceRepaintColumn(0);
	}

}
