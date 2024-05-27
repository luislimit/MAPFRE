package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.hibernate.service.spi.ServiceException;

import com.mdsql.bussiness.entities.DetObjeto;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.PantallaDetalleScript;
import com.mdsql.ui.model.DetalleScriptTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaDetalleScriptListener extends ListenerSupport implements ActionListener, OnLoadListener {

	private PantallaDetalleScript pantallaDetalleScript;
	
	public PantallaDetalleScriptListener(PantallaDetalleScript pantallaDetalleScript) {
		super();
		this.pantallaDetalleScript = pantallaDetalleScript;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_DETALLE_SCRIPT_CANCELAR.equals(jButton.getActionCommand())) {
			cancelar();
		}
		
	}
	
	private void cancelar() {
		pantallaDetalleScript.dispose();
	}

	@Override
	public void onLoad() {
		try {
			ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
			
			BigDecimal idProceso = (BigDecimal) pantallaDetalleScript.getParams().get("proceso");
			BigDecimal numeroOrden = (BigDecimal) pantallaDetalleScript.getParams().get("numeroOrden");
			
			List<DetObjeto> detalleObjetosScripts = scriptService.detalleObjetosScripts(idProceso, numeroOrden);
			
			populateModel(detalleObjetosScripts);
			
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaDetalleScript.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
	
	/**
	 * @param modelos
	 */
	private void populateModel(List<DetObjeto> objetos) {
		// Obtiene el modelo y lo actualiza
		DetalleScriptTableModel tableModel = (DetalleScriptTableModel) pantallaDetalleScript
				.getTblDetalle().getModel();
		tableModel.setData(objetos);
		
		pantallaDetalleScript.getTblDetalle().repaint();
	}

}
