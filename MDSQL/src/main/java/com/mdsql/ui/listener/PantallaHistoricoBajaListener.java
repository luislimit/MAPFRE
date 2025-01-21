package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.HistoricoService;
import com.mdsql.ui.PantallaHistoricoBaja;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;

public class PantallaHistoricoBajaListener extends ListenerSupport implements ActionListener {

	private final PantallaHistoricoBaja pantalla;

	public PantallaHistoricoBajaListener(PantallaHistoricoBaja pantalla) {
		super();
		this.pantalla = pantalla;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			baja();
		}

		if (MDSQLConstants.PANTALLA_HISTORICO_BAJA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			pantalla.getReturnParams().put("response", "KO");
			pantalla.dispose();
		}
	}

	private void baja() {
		try {
			HistoricoService historicoService = (HistoricoService) getService(MDSQLConstants.HISTORICO_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codUsr = session.getCodUsr();

			Modelo modeloSeleccionado = pantalla.getModeloSeleccionado();
			Historico seleccionado = pantalla.getSeleccionado();
			
			if (!Objects.isNull(modeloSeleccionado)) {
				String codigoProyecto = modeloSeleccionado.getCodigoProyecto();
				String nombreObjeto = seleccionado.getNombreObjeto();
				String peticion = pantalla.getTxtPeticion().getText();

				OutputWarning output = historicoService.bajaHistorico(codigoProyecto, nombreObjeto, peticion, codUsr);
			
                                MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());
			}
			
			pantalla.getReturnParams().put("response", "OK");
			pantalla.dispose();
		} catch (ServiceException e) {
			pantalla.getReturnParams().put("response", "KO");
			MDSQLUIHelper.showErrors(pantalla, e);
		}
	}
}
