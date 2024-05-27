package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.OutputSeleccionarHistorico;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.ui.PantallaSeleccionHistorico;
import com.mdsql.ui.model.SeleccionHistoricoTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.ui.utils.collections.SeleccionHistoricoPredicate;
import com.mdsql.ui.utils.collections.SeleccionHistoricoUpdateClosure;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.OnLoadListener;
import com.mdval.ui.utils.observer.Observable;
import com.mdval.ui.utils.observer.Observer;

public class PantallaSeleccionHistoricoListener extends ListenerSupport
		implements ActionListener, OnLoadListener, Observer {

	private PantallaSeleccionHistorico pantallaSeleccionHistorico;

	public PantallaSeleccionHistoricoListener(PantallaSeleccionHistorico pantallaSeleccionHistorico) {
		super();
		this.pantallaSeleccionHistorico = pantallaSeleccionHistorico;
	}

	public void addObservador(Observer o) {
		this.addObserver(o);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_ADD.equals(jButton.getActionCommand())) {
			addToHistorico();
		}

		if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_GENERAR.equals(jButton.getActionCommand())) {
			generarHistorico();
		}

		if (MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_CANCELAR.equals(jButton.getActionCommand())) {
			cancelar();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onLoad() {
		try {
			ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

			String codigoProyecto = (String) pantallaSeleccionHistorico.getParams().get("codigoProyecto");
			List<TextoLinea> lineas = (List<TextoLinea>) pantallaSeleccionHistorico.getParams().get("script");
			OutputSeleccionarHistorico outputSeleccionarHistorico = procesoService.seleccionarHistorico(codigoProyecto, lineas);
			
			MDSQLUIHelper.showWarningsIfExists(outputSeleccionarHistorico.getWarnings(), pantallaSeleccionHistorico.getFrameParent());
			
			List<SeleccionHistorico> seleccion = outputSeleccionarHistorico.getSeleccion();

			populateModelSeleccion(seleccion);

			SeleccionHistoricoTableModel model = (SeleccionHistoricoTableModel) pantallaSeleccionHistorico
					.getTblHistorico().getModel();

			if (model.checkAllConfigured()) {
				pantallaSeleccionHistorico.getBtnAddHistorico().setEnabled(Boolean.FALSE);
			}
		} catch (ServiceException e) {
			pantallaSeleccionHistorico.setErrorOnload(Boolean.TRUE);
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaSeleccionHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void addToHistorico() {
		try {
			ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);

			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
			String codigoUsuario = session.getCodUsr();
			String codigoProyecto = (String) pantallaSeleccionHistorico.getParams().get("codigoProyecto");
			String codigoPeticion = (String) pantallaSeleccionHistorico.getParams().get("codigoPeticion");

			List<SeleccionHistorico> listaObjetos = ((SeleccionHistoricoTableModel) pantallaSeleccionHistorico
					.getTblHistorico().getModel()).getData();

			// Filtramos sólo los que tienen marcado el check de histórico
			List<SeleccionHistorico> listaSeleccionados = (List<SeleccionHistorico>) CollectionUtils
					.select(listaObjetos, new SeleccionHistoricoPredicate());

			ServiceException serviceException = procesoService.altaHistorico(listaSeleccionados, codigoProyecto, codigoPeticion, codigoUsuario);
			if (!Objects.isNull(serviceException)) {
				if (serviceException.getType().equals(2)) {
					CollectionUtils.forAllDo(listaObjetos, new SeleccionHistoricoUpdateClosure(listaSeleccionados));
					pantallaSeleccionHistorico.getTblHistorico().repaint();
					
					Map<String, Object> params = MDSQLUIHelper.buildError(serviceException);
					MDSQLUIHelper.showPopup(pantallaSeleccionHistorico.getFrameParent(), MDSQLConstants.CMD_WARN, params);
				}
				else {
					throw serviceException;
				}
			}
			else { // No ha dado ni error ni aviso, se marcan los seleccionados como configurados en histórico
				CollectionUtils.forAllDo(listaObjetos, new SeleccionHistoricoUpdateClosure(listaSeleccionados));
				pantallaSeleccionHistorico.getTblHistorico().repaint();
			}
		} catch (Exception e) {
			Map<String, Object> params = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaSeleccionHistorico.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
		}
	}

	private void generarHistorico() {
		int dialogResult = MDSQLUIHelper.showConfirm("¿Desea continuar con el procesado?", "Atención");

		Boolean result = (dialogResult == JOptionPane.YES_OPTION) ? Boolean.TRUE : Boolean.FALSE;
		pantallaSeleccionHistorico.getReturnParams().put("procesado", result);

		List<SeleccionHistorico> listaObjetos = ((SeleccionHistoricoTableModel) pantallaSeleccionHistorico.getTblHistorico()
				.getModel()).getData();
		
//		List<SeleccionHistorico> listaSeleccionados = (List<SeleccionHistorico>) CollectionUtils
//				.select(listaObjetos, new SeleccionHistoricoPredicate());
		pantallaSeleccionHistorico.getReturnParams().put("objetosHistorico", listaObjetos);

		updateObservers(MDSQLConstants.PANTALLA_SELECCION_HISTORICA_BTN_GENERAR);
		pantallaSeleccionHistorico.dispose();
	}

	private void cancelar() {
		pantallaSeleccionHistorico.getReturnParams().put("procesado", Boolean.FALSE);
		pantallaSeleccionHistorico.dispose();
	}

	/**
	 * @param avisos
	 */
	private void populateModelSeleccion(List<SeleccionHistorico> seleccion) {
		// Obtiene el modelo y lo actualiza
		SeleccionHistoricoTableModel tableModel = (SeleccionHistoricoTableModel) pantallaSeleccionHistorico
				.getTblHistorico().getModel();
		tableModel.setData(seleccion);
	}

	@Override
	public void update(Observable o, Object cmd) {
		// TODO Auto-generated method stub

	}
}
