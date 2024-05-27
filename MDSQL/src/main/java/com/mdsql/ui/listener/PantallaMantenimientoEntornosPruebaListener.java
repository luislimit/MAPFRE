package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.InputMntoEntornoPrueba;
import com.mdsql.bussiness.entities.OutputConsultarEntornosPrueba;
import com.mdsql.bussiness.entities.OutputMntoEntornoPrueba;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.EntornosPruebaService;
import com.mdsql.ui.PantallaMantenimientoEntornosPrueba;
import com.mdsql.ui.model.EntornosPruebaTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.utils.AppHelper;

public class PantallaMantenimientoEntornosPruebaListener extends ListenerSupport implements ActionListener, OnLoadListener {
	private PantallaMantenimientoEntornosPrueba pantallaMantenimientoEntornosPrueba;
	
	public PantallaMantenimientoEntornosPruebaListener(PantallaMantenimientoEntornosPrueba pantallaMantenimientoEntornosPrueba) {
		super();
		this.pantallaMantenimientoEntornosPrueba = pantallaMantenimientoEntornosPrueba;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_GUARDAR.equals(jButton.getActionCommand())) {
			eventBtnGuardar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_PRUEBA_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoEntornosPrueba.dispose();
		}
	}
	
	private void eventBtnGuardar() {
		try {
			EntornosPruebaService entornosPruebaService = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();

			InputMntoEntornoPrueba inputMntoEntornoPrueba = new InputMntoEntornoPrueba();

			String nombreEntorno = pantallaMantenimientoEntornosPrueba.getTxtNombreEntorno().getText();
			String bbdd = pantallaMantenimientoEntornosPrueba.getTxtBBDD().getText();
			String esquema = pantallaMantenimientoEntornosPrueba.getTxtEsquema().getText();
			String descripcion = pantallaMantenimientoEntornosPrueba.getTxtDescripcion().getText();
			String tablespace = pantallaMantenimientoEntornosPrueba.getTxtTablespace().getText();
			String gradoParal = pantallaMantenimientoEntornosPrueba.getTxtGradoparal().getText();
			String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaMantenimientoEntornosPrueba.getChkHabilitada().isSelected());
			
			inputMntoEntornoPrueba.setNombreEntorno(nombreEntorno);
			inputMntoEntornoPrueba.setBbdd(bbdd);
			inputMntoEntornoPrueba.setEsquema(esquema);
			inputMntoEntornoPrueba.setDescripcion(descripcion);
			inputMntoEntornoPrueba.setTablespace(tablespace);
			inputMntoEntornoPrueba.setGradoParal(gradoParal);
			inputMntoEntornoPrueba.setMcaHabilitado(mcaHabilitado);

			OutputMntoEntornoPrueba outputMntoEntornoPrueba = entornosPruebaService.guardarEntorno(inputMntoEntornoPrueba, codUsr);
			
			// Hay avisos
			if (outputMntoEntornoPrueba.getResult() == 2) {
				ServiceException serviceException = outputMntoEntornoPrueba.getServiceException();
				Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
				MDSQLUIHelper.showPopup(pantallaMantenimientoEntornosPrueba.getFrameParent(), MDSQLConstants.CMD_WARN, params);
			}
			
			pantallaMantenimientoEntornosPrueba.getBtnGuardar().setEnabled(Boolean.FALSE);
			populateEntornosPrueba();
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoEntornosPrueba.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	@Override
	public void onLoad() {
		populateEntornosPrueba();
	}
	
	private void populateEntornosPrueba() {
		try {
			EntornosPruebaService entornosPruebaService = (EntornosPruebaService) getService(MDSQLConstants.ENTORNOS_PRUEBA_SERVICE);
			
			OutputConsultarEntornosPrueba consultarEntornosPrueba = entornosPruebaService.consultarEntornos();
			
			if (CollectionUtils.isNotEmpty(consultarEntornosPrueba.getEntornos())) {
				// Obtiene el modelo y lo actualiza
				EntornosPruebaTableModel tableModel = (EntornosPruebaTableModel) pantallaMantenimientoEntornosPrueba.getTblMantenimientoEntornosPrueba()
						.getModel();
				tableModel.setData(consultarEntornosPrueba.getEntornos());
			}
		} catch (ServiceException e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoEntornosPrueba.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}
}
