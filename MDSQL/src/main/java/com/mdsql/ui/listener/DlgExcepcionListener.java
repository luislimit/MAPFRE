package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import com.mdsql.bussiness.entities.OutputExcepcionScript;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.ui.DlgExcepcion;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdval.exceptions.ServiceException;

public class DlgExcepcionListener extends ListenerSupport implements ActionListener {
	
	private DlgExcepcion dlgExcepcion;
	
	public DlgExcepcionListener(DlgExcepcion dlgExcepcion) {
		super();
		this.dlgExcepcion = dlgExcepcion;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();
		
		if (MDSQLConstants.DLG_EXCEPTION_BTN_ACEPTAR.equals(jButton.getActionCommand())) {
			excepcion();
		}

		if (MDSQLConstants.DLG_EXCEPTION_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			dlgExcepcion.dispose();
		}
	}
	
	private void excepcion() {
		try {
			ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			
			Proceso proceso = (Proceso) dlgExcepcion.getParams().get("proceso");
			Script script = (Script) dlgExcepcion.getParams().get("script");
			String txtMotivoExcepcion = dlgExcepcion.getTxtMotivoExcepcion().getText();
			OutputExcepcionScript outputExcepcionScript = scriptService.excepcionScript(proceso, script, txtMotivoExcepcion, session.getCodUsr());
			
			dlgExcepcion.getReturnParams().put("estadoScript", outputExcepcionScript.getDescripcionEstadoScript());
			
			dlgExcepcion.dispose();
			
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(dlgExcepcion.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
}
