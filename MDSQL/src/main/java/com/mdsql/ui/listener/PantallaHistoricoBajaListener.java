package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputBajaHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.ui.PantallaHistoricoBaja;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

public class PantallaHistoricoBajaListener extends ListenerSupport implements ActionListener {

	private PantallaHistoricoBaja pantallaHistoricoBaja;

	public PantallaHistoricoBajaListener(PantallaHistoricoBaja pantallaHistoricoBaja) {
		super();
		this.pantallaHistoricoBaja = pantallaHistoricoBaja;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			baja();
		}

		if (MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaHistoricoBaja.getReturnParams().put("response", "KO");
			pantallaHistoricoBaja.dispose();
		}
	}

	private void baja() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();

			Modelo modeloSeleccionado = pantallaHistoricoBaja.getModeloSeleccionado();
			Historico seleccionado = pantallaHistoricoBaja.getSeleccionado();
			
			if (!Objects.isNull(modeloSeleccionado)) {
				String codigoProyecto = modeloSeleccionado.getCodigoProyecto();
				String nombreObjeto = seleccionado.getNombreObjeto();
				String peticion = pantallaHistoricoBaja.getTxtPeticion().getText();

				OutputBajaHistorico outputBajaHistorico = historicoService.bajaHistorico(codigoProyecto, nombreObjeto, peticion, codUsr);
			
				// Hay avisos
				if (outputBajaHistorico.getResult() == 2) {
					ServiceException serviceException = outputBajaHistorico.getServiceException();
					Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
					MDSQLUIHelper.showPopup(pantallaHistoricoBaja.getFrameParent(), MDSQLConstants.CMD_WARN, params);
				}
			}
			
			pantallaHistoricoBaja.getReturnParams().put("response", "OK");
			pantallaHistoricoBaja.dispose();
		} catch (ServiceException e) {
			pantallaHistoricoBaja.getReturnParams().put("response", "KO");
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaHistoricoBaja.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
}
