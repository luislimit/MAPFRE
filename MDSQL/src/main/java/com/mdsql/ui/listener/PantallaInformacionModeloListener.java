package com.mdsql.ui.listener;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.ui.PantallaInformacionModelo;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;

public class PantallaInformacionModeloListener extends ListenerSupport implements OnLoadListener {

	private PantallaInformacionModelo pantallaInformacionModelo;
	
	public PantallaInformacionModeloListener(PantallaInformacionModelo pantallaInformacionModelo) {
		super();
		this.pantallaInformacionModelo = pantallaInformacionModelo;
	}

	@Override
	public void onLoad() {
		try {
			AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
			
			String codigoProyecto = (String) pantallaInformacionModelo.getParams().get("codigoProyecto");
			
			List<Aviso> consultaAvisosModelo = avisoService.consultaAvisosModelo(codigoProyecto);
			
			if (CollectionUtils.isNotEmpty(consultaAvisosModelo)) {
				populateModelAvisos(consultaAvisosModelo);
			}
			
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaInformacionModelo.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
	
	/**
	 * @param avisos
	 */
	private void populateModelAvisos(List<Aviso> avisos) {
		// Obtiene el modelo y lo actualiza
		ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantallaInformacionModelo.getTblInformacion()
				.getModel();
		tableModel.setData(avisos);
	}
}
