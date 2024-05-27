package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Entorno;
import com.mdsql.bussiness.entities.OutputConsultarEntornos;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.EntornoService;
import com.mdsql.ui.PantallaMantenimientoEntornos;
import com.mdsql.ui.model.EntornoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.AppHelper;

public class PantallaMantenimientoEntornosListener extends ListenerSupport implements ActionListener {
	private PantallaMantenimientoEntornos pantallaMantenimientoEntornos;

	public PantallaMantenimientoEntornosListener(PantallaMantenimientoEntornos pantallaMantenimientoEntornos) {
		super();
		this.pantallaMantenimientoEntornos = pantallaMantenimientoEntornos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_BUSCAR.equals(jButton.getActionCommand())) {
			eventBtnBuscar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_GRABAR.equals(jButton.getActionCommand())) {
			eventBtnGrabar();
		}

		if (MDSQLConstants.PANTALLA_MANTENIMIENTO_ENTORNOS_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaMantenimientoEntornos.dispose();
		}
	}
	
	private void eventBtnBuscar() {
		try {
			actualizarEntornos();
		} catch (ServiceException | IOException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoEntornos.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	private void eventBtnGrabar() {
		try {
			EntornoService entornoService = (EntornoService) getService(MDSQLConstants.ENTORNO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();

			String claveEncriptacion = ConfigurationSingleton.getInstance().getConfig("TOKEN");

			String nomBBDD = pantallaMantenimientoEntornos.getTxtBBDD().getText();
			String nomEsquema = pantallaMantenimientoEntornos.getTxtEsquema().getText();
			String password = pantallaMantenimientoEntornos.getTxtPassword().getText();
			String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaMantenimientoEntornos.getChkHabilitada().isSelected());
			String comentario = pantallaMantenimientoEntornos.getTxtComentario().getText();

			entornoService.guardarEntorno(nomBBDD, nomEsquema, claveEncriptacion, password, mcaHabilitado, comentario, codUsr);

			clearForm();
			actualizarEntornos();
		} catch (ServiceException | IOException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaMantenimientoEntornos.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}

	}

	private void clearForm() {
		pantallaMantenimientoEntornos.getTxtBBDD().setText(StringUtils.EMPTY);
		pantallaMantenimientoEntornos.getTxtEsquema().setText(StringUtils.EMPTY);
		pantallaMantenimientoEntornos.getTxtPassword().setText(StringUtils.EMPTY);
		pantallaMantenimientoEntornos.getChkHabilitada().setSelected(Boolean.FALSE);
		pantallaMantenimientoEntornos.getTxtComentario().setText(StringUtils.EMPTY);

		pantallaMantenimientoEntornos.getBtnGrabar().setEnabled(Boolean.TRUE);
	}

	private void actualizarEntornos() throws ServiceException, IOException {
		EntornoService entornoService = (EntornoService) getService(MDSQLConstants.ENTORNO_SERVICE);
		String claveEncriptacion = ConfigurationSingleton.getInstance().getConfig("TOKEN");

		String nomBBDD = pantallaMantenimientoEntornos.getTxtBBDD().getText();
		String nomEsquema = pantallaMantenimientoEntornos.getTxtEsquema().getText();
		String mcaHabilitado = AppHelper.normalizeValueToCheck(pantallaMantenimientoEntornos.getChkHabilitada().isSelected());

		OutputConsultarEntornos outputConsultarEntornos  = entornoService.consultarEntornos(nomBBDD, nomEsquema, claveEncriptacion, mcaHabilitado);
		
		// Hay avisos
		if (outputConsultarEntornos.getResult() == 2) {
			ServiceException serviceException = outputConsultarEntornos.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaMantenimientoEntornos.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}

		fillEntornos(outputConsultarEntornos.getEntornos());
	}

	private void fillEntornos(List<Entorno> list) throws ServiceException {
		// Obtiene el modelo y lo actualiza
		EntornoTableModel tableModel = (EntornoTableModel) pantallaMantenimientoEntornos
				.getTblMantenimientoEntornos().getModel();
		tableModel.clearData();

		tableModel.setData(list);
	}
}
