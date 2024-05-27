package com.mdsql.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.Aviso;
import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.InputProcesaScript;
import com.mdsql.bussiness.entities.InputProcesaType;
import com.mdsql.bussiness.entities.InputSeleccionarProcesados;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsultaModelos;
import com.mdsql.bussiness.entities.OutputProcesa;
import com.mdsql.bussiness.entities.OutputProcesaScript;
import com.mdsql.bussiness.entities.OutputProcesaType;
import com.mdsql.bussiness.entities.Proceso;
import com.mdsql.bussiness.entities.Script;
import com.mdsql.bussiness.entities.SeleccionHistorico;
import com.mdsql.bussiness.entities.Session;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.entities.TextoLinea;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.bussiness.service.AvisoService;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.bussiness.service.ModeloService;
import com.mdsql.bussiness.service.ProcesoService;
import com.mdsql.bussiness.service.ScriptService;
import com.mdsql.bussiness.service.TypeService;
import com.mdsql.ui.PantallaProcesarScript;
import com.mdsql.ui.PantallaResumenProcesado;
import com.mdsql.ui.PantallaSeleccionHistorico;
import com.mdsql.ui.PantallaSeleccionModelos;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.model.ProcesarScriptNotaTableModel;
import com.mdsql.ui.model.ProcesarScriptUltimasPeticionesTableModel;
import com.mdsql.ui.model.SubProyectoComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.MDSQLConstants.Procesado;
import com.mdval.exceptions.ServiceException;

/**
 * @author federico
 *
 */
public class PantallaProcesarScriptActionListener extends ListenerSupport implements ActionListener {

	private PantallaProcesarScript pantallaProcesarScript;
	
	private PantallaSeleccionModelos pantallaSeleccionModelos;
	
	private PantallaSeleccionHistorico pantallaSeleccionHistorico;

	/**
	 * @param framePrincipal
	 */
	public PantallaProcesarScriptActionListener(PantallaProcesarScript pantallaProcesarScript) {
		this.pantallaProcesarScript = pantallaProcesarScript;
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton) e.getSource();

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_SEARCH_MODEL.equals(jButton.getActionCommand())) {
			eventBtnSearchModel();
		}

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_CANCELAR.equals(jButton.getActionCommand())) {
			pantallaProcesarScript.dispose();
		}

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_PROCESAR.equals(jButton.getActionCommand())) {
			eventBtnProcesar();
		}

		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_LIMPIAR.equals(jButton.getActionCommand())) {
			eventBtnLimpiar();
		}
		
		if (MDSQLConstants.PANTALLA_PROCESADO_SCRIPT_VER_PROCESADO.equals(jButton.getActionCommand())) {
			eventBtnVerProcesado();
		}
	}

	private void eventBtnVerProcesado() {
		Map<String, Object> params = new HashMap<>();
		
		Proceso seleccionado = pantallaProcesarScript.getProcesoSeleccionado();
		
		params.put("proceso", seleccionado);
		params.put("entregar", Boolean.FALSE);
		
		PantallaResumenProcesado pantallaResumenProcesado = (PantallaResumenProcesado) MDSQLUIHelper.createDialog(pantallaProcesarScript.getFrameParent(),
				MDSQLConstants.CMD_RESUMEN_PROCESADO, params);
		MDSQLUIHelper.show(pantallaResumenProcesado);
	}

	/**
	 * 
	 */
	private void eventBtnLimpiar() {
		pantallaProcesarScript.getTxtModelo().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtEsquema().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getChkGenerarHistorico().setSelected(Boolean.FALSE);
		pantallaProcesarScript.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
		pantallaProcesarScript.getTxtEsquema().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtBBDDHistorico().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtEsquemaHistorico().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtDescripcion().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtSolicitadaPor().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtPeticion().setText(StringUtils.EMPTY);
		pantallaProcesarScript.getTxtDemanda().setText(StringUtils.EMPTY);

		((ProcesarScriptNotaTableModel) pantallaProcesarScript.getTblNotas().getModel()).clearData();
		((ProcesarScriptUltimasPeticionesTableModel) pantallaProcesarScript.getTblUltimasPeticiones().getModel())
				.clearData();
		
		// Clear comboboxes
		DefaultComboBoxModel<BBDD> bbddModel = new DefaultComboBoxModel<BBDD>();
		pantallaProcesarScript.getCmbBBDD().setModel(bbddModel);
		
		DefaultComboBoxModel<SubProyecto> subproyectoModel = new DefaultComboBoxModel<SubProyecto>();
		pantallaProcesarScript.getCmbSubmodelo().setModel(subproyectoModel);

		pantallaProcesarScript.getBtnLimpiar().setEnabled(Boolean.FALSE);
		pantallaProcesarScript.getBtnVerProcesado().setEnabled(Boolean.FALSE);
		
		pantallaProcesarScript.setModeloSeleccionado(null);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void eventBtnProcesar() {
		Modelo seleccionado = pantallaProcesarScript.getModeloSeleccionado();
		Procesado procesado = pantallaProcesarScript.getProcesado();
		
		if (!Objects.isNull(seleccionado)) {
			if (Procesado.TYPE.equals(procesado)) {
				procesarType();
			}
			else {
				// El modelo tiene histórico
				if (MDSQLConstants.S.equals(seleccionado.getMcaHis())) {
					Map<String, Object> params = new HashMap<>();
					params.put("codigoProyecto", seleccionado.getCodigoProyecto());
					params.put("script", pantallaProcesarScript.getParams().get("script"));
					params.put("codigoPeticion", pantallaProcesarScript.getTxtPeticion().getText());
					
					pantallaSeleccionHistorico = (PantallaSeleccionHistorico) MDSQLUIHelper.createDialog(pantallaProcesarScript.getFrameParent(),
							MDSQLConstants.CMD_SELECCION_HISTORICO, params);
					
					// Si al cargar hay error, la pantalla no se mostrará
					if (!pantallaSeleccionHistorico.getErrorOnload()) {
						MDSQLUIHelper.show(pantallaSeleccionHistorico);
						
						Boolean continuarProcesado = (Boolean) pantallaSeleccionHistorico.getReturnParams().get("procesado");
						if (!Objects.isNull(continuarProcesado) && continuarProcesado) {
							List<SeleccionHistorico> objetosHistorico = (List<SeleccionHistorico>) pantallaSeleccionHistorico.getReturnParams().get("objetosHistorico");
							procesarScript(objetosHistorico);
						}
						else {
							JOptionPane.showMessageDialog(pantallaProcesarScript.getFrameParent(), "Operación cancelada");
						}
					}
				}
				else {
					procesarScript(null);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void eventBtnSearchModel() {
		try {
			Modelo seleccionado = null;
			Map<String, Object> params = new HashMap<>();
			
			String codigoProyecto = pantallaProcesarScript.getTxtModelo().getText();
			
			List<Modelo> modelos = buscar(codigoProyecto, null, null);
			if (modelos.size() == 1) {
				seleccionado = modelos.get(0);
			}
			else {
				if (StringUtils.isNotBlank(codigoProyecto)) {
					params.put("codigoProyecto", codigoProyecto);
				}
				
				pantallaSeleccionModelos = (PantallaSeleccionModelos) MDSQLUIHelper.createDialog(pantallaProcesarScript.getFrameParent(),
						MDSQLConstants.CMD_SEARCH_MODEL, params);
				MDSQLUIHelper.show(pantallaSeleccionModelos);
				seleccionado = pantallaSeleccionModelos.getSeleccionado();
			}
			establecerModelo(seleccionado);
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void procesarModelo() throws ServiceException {
		Modelo seleccionado = pantallaProcesarScript.getModeloSeleccionado();
		pantallaProcesarScript.getTxtModelo().setText(seleccionado.getCodigoProyecto());
		pantallaProcesarScript.getTxtEsquema().setText(seleccionado.getNombreEsquema());

		fillSubModelos(seleccionado);

		fillUltimasPeticiones(seleccionado);

		fillAvisos(seleccionado);

		SubProyecto subproyecto = pantallaProcesarScript.getSubproyectoSeleccionado();
		if (!Objects.isNull(subproyecto)) {
			fillBBDD(seleccionado, subproyecto);
		}
		
		fillChkHistorico(seleccionado);

		pantallaProcesarScript.getBtnLimpiar().setEnabled(Boolean.TRUE);
	}

	/**
	 * @param seleccionado
	 */
	private void fillSubModelos(Modelo seleccionado) {
		List<SubProyecto> subProyectos = seleccionado.getSubproyectos();
		if (CollectionUtils.isNotEmpty(subProyectos)) {
			SubProyectoComboBoxModel modelSubProyectos = new SubProyectoComboBoxModel(subProyectos);
			pantallaProcesarScript.getCmbSubmodelo().setModel(modelSubProyectos);

			// Si el modelo solo tiene un solo submodelo, se seleccionará directamente en el
			// combo.
			if (subProyectos.size() == 1) {
				pantallaProcesarScript.setSubproyectoSeleccionado(subProyectos.get(0));
				pantallaProcesarScript.getCmbSubmodelo().setSelectedItem(subProyectos.get(0));
			}
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillUltimasPeticiones(Modelo seleccionado) throws ServiceException {
		// Limpiar la tabla de peticiones
		((ProcesarScriptUltimasPeticionesTableModel) pantallaProcesarScript.getTblUltimasPeticiones().getModel())
				.clearData();
		
		// Hacer la consulta
		InputSeleccionarProcesados inputSeleccionarProcesados = new InputSeleccionarProcesados();

		inputSeleccionarProcesados.setPCodigoproyecto(seleccionado.getCodigoProyecto());
		inputSeleccionarProcesados.setPUltimas(new BigDecimal(1));

		ProcesoService procesoService = (ProcesoService) getService(MDSQLConstants.PROCESO_SERVICE);
		List<Proceso> peticiones = procesoService.seleccionarProcesados(inputSeleccionarProcesados);

		if (CollectionUtils.isNotEmpty(peticiones)) {
			populateModelUltimasPeticiones(peticiones);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillAvisos(Modelo seleccionado) throws ServiceException {
		// Limpiar la tabla de avisos
		((ProcesarScriptNotaTableModel) pantallaProcesarScript.getTblNotas().getModel()).clearData();
		
		// Hacer la consulta
		AvisoService avisoService = (AvisoService) getService(MDSQLConstants.AVISO_SERVICE);
		List<Aviso> avisos = avisoService.consultaAvisosModelo(seleccionado.getCodigoProyecto());

		if (CollectionUtils.isNotEmpty(avisos)) {
			populateModelAvisos(avisos);
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillBBDD(Modelo modelo, SubProyecto subproyecto) throws ServiceException {
		BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);

		String codigoProyecto = modelo.getCodigoProyecto();
		String codigoSubproyecto = !Objects.isNull(subproyecto)
				? subproyecto.getCodigoSubProyecto()
				: null;

		List<BBDD> bbdds = bbddService.consultaBBDDModelo(codigoProyecto, codigoSubproyecto);
		pantallaProcesarScript.setBbdds(bbdds);
		if (CollectionUtils.isNotEmpty(bbdds)) {
			BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(bbdds);
			pantallaProcesarScript.getCmbBBDD().setModel(modelBBDD);
		}
		
		// Pone la base de datos por defecto
		String baseDatos = modelo.getNombreBbdd();
		if (StringUtils.isNotBlank(baseDatos)) {
			BBDDComboBoxModel modelBBDD = (BBDDComboBoxModel) pantallaProcesarScript.getCmbBBDD().getModel();
			for (int i = 0; i < modelBBDD.getSize(); i++) {
				BBDD bbdd = modelBBDD.getElementAt(i);
				if (bbdd.getNombreBBDD().equals(baseDatos)) {
					pantallaProcesarScript.getCmbBBDD().setSelectedItem(bbdd);
				}
			}
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillChkHistorico(Modelo seleccionado) {
		// En caso de no tener histórico el check de Generar histórico estará desmarcado
		// y deshabilitado.
		String tieneHistorico = seleccionado.getMcaHis();
		if (MDSQLConstants.N.equals(tieneHistorico)) {
			pantallaProcesarScript.getChkGenerarHistorico().setSelected(Boolean.FALSE);
			pantallaProcesarScript.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
		} else {
			pantallaProcesarScript.getChkGenerarHistorico().setSelected(Boolean.TRUE);
			pantallaProcesarScript.getChkGenerarHistorico().setEnabled(Boolean.FALSE);
		}
	}

	/**
	 * @param avisos
	 */
	private void populateModelAvisos(List<Aviso> avisos) {
		// Obtiene el modelo y lo actualiza
		ProcesarScriptNotaTableModel tableModel = (ProcesarScriptNotaTableModel) pantallaProcesarScript.getTblNotas()
				.getModel();
		tableModel.setData(avisos);
	}

	/**
	 * @param peticiones
	 */
	private void populateModelUltimasPeticiones(List<Proceso> peticiones) {
		// Obtiene el modelo y lo actualiza
		ProcesarScriptUltimasPeticionesTableModel tableModel = (ProcesarScriptUltimasPeticionesTableModel) pantallaProcesarScript
				.getTblUltimasPeticiones().getModel();
		tableModel.setData(peticiones);
	}
	
	/**
	 * @param objetosHistorico
	 */
	private void procesarScript(List<SeleccionHistorico> objetosHistorico) {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
	        String usuario = session.getCodUsr();
			
	        ScriptService scriptService = (ScriptService) getService(MDSQLConstants.SCRIPT_SERVICE);
			
			Modelo seleccionado = pantallaProcesarScript.getModeloSeleccionado();
			if (!Objects.isNull(seleccionado)) {
				SubProyecto subProyecto = pantallaProcesarScript.getSubproyectoSeleccionado();
			
				InputProcesaScript inputProcesaScript = new InputProcesaScript();
				List<TextoLinea> lineasScript = pantallaProcesarScript.getScript();
				inputProcesaScript.setLineasScript(lineasScript);
				inputProcesaScript.setPCodigoProyecto(seleccionado.getCodigoProyecto());
				
				if (!Objects.isNull(subProyecto)) {
					inputProcesaScript.setPCodigoSubProyecto(subProyecto.getCodigoSubProyecto());
				}
				
				inputProcesaScript.setPCodigoPeticion(pantallaProcesarScript.getTxtPeticion().getText());
				inputProcesaScript.setPCodigoDemanda(pantallaProcesarScript.getTxtDemanda().getText());
				inputProcesaScript.setPMcaReprocesa(MDSQLConstants.N);
				inputProcesaScript.setPCodigoUsr(usuario);
				inputProcesaScript.setPCodigoUsrPeticion(pantallaProcesarScript.getTxtSolicitadaPor().getText());
				inputProcesaScript.setPMcaHIS(MDSQLAppHelper.normalizeValueToCheck(pantallaProcesarScript.getChkGenerarHistorico().isSelected()));
				
				BBDD selectedBBDD = (BBDD) pantallaProcesarScript.getCmbBBDD().getSelectedItem();
				if (!Objects.isNull(selectedBBDD)) {
					inputProcesaScript.setPNombreBBDD(selectedBBDD.getNombreBBDD());
					inputProcesaScript.setPNombreEsquema(selectedBBDD.getNombreEsquema());
					inputProcesaScript.setPNombreBBDDHIS(selectedBBDD.getNombreBBDDHis());
					inputProcesaScript.setPNombreEsquemaHis(selectedBBDD.getNombreEsquemaHis());
				}
				
				inputProcesaScript.setPTxtDescripcion(pantallaProcesarScript.getTxtDescripcion().getText());
				
				// Si la lista de objetos del histórico está vacía, no informa la lista a procesar
				if (CollectionUtils.isNotEmpty(objetosHistorico)) {
					inputProcesaScript.setListaObjetoHis(objetosHistorico);
				}
				
				File file = (File) pantallaProcesarScript.getParams().get("file");
				inputProcesaScript.setPNombreFichaEntrada(file.getName());
				
				String parentPath = file.getAbsoluteFile().getParent();
				inputProcesaScript.setPTxtRutaEntrada(parentPath);
				
				OutputProcesaScript outputProcesaScript = scriptService.procesarScript(inputProcesaScript);
				
				Proceso proceso = generateProceso(usuario, outputProcesaScript);
				proceso.setModelo(seleccionado);
				proceso.setSubproyecto(subProyecto);
				proceso.setBbdd(selectedBBDD);
				proceso.setBbdds(pantallaProcesarScript.getBbdds());
				
				pantallaProcesarScript.getReturnParams().put("proceso", proceso);
				
				List<Script> listaScripts = outputProcesaScript.getListaScripts();
				proceso.setScripts(listaScripts);
				
				if (CollectionUtils.isNotEmpty(listaScripts)) {
					pantallaProcesarScript.getReturnParams().put("scripts", listaScripts);
				}
				
				pantallaProcesarScript.dispose();
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
	
	/**
	 * 
	 */
	private void procesarType() {
		try {
			Session session = (Session) MDSQLAppHelper.getGlobalProperty(MDSQLConstants.SESSION);
	        String usuario = session.getCodUsr();
	        
			TypeService typeService = (TypeService) getService(MDSQLConstants.TYPE_SERVICE);
			
			Modelo seleccionado = pantallaProcesarScript.getModeloSeleccionado();
			SubProyecto subProyecto = pantallaProcesarScript.getSubproyectoSeleccionado();
		
			InputProcesaType inputProcesaType = new InputProcesaType();
			List<TextoLinea> lineasScript = pantallaProcesarScript.getScript();
			inputProcesaType.setLineasScript(lineasScript);
			inputProcesaType.setPCodigoProyecto(seleccionado.getCodigoProyecto());
			inputProcesaType.setPCodigoSubProyecto(subProyecto.getCodigoSubProyecto());
			inputProcesaType.setPCodigoPeticion(pantallaProcesarScript.getTxtPeticion().getText());
			inputProcesaType.setPCodigoDemanda(pantallaProcesarScript.getTxtDemanda().getText());
			inputProcesaType.setPCodigoUsr(usuario);
			inputProcesaType.setPCodigoUsrPeticion(pantallaProcesarScript.getTxtSolicitadaPor().getText());
			
			BBDD selectedBBDD = (BBDD) pantallaProcesarScript.getCmbBBDD().getSelectedItem();
			if (!Objects.isNull(selectedBBDD)) {
				inputProcesaType.setPNombreBBDD(selectedBBDD.getNombreBBDD());
				inputProcesaType.setPNombreEsquema(selectedBBDD.getNombreEsquema());
				inputProcesaType.setPNombreBBDDHIS(selectedBBDD.getNombreBBDDHis());
				inputProcesaType.setPNombreEsquemaHis(selectedBBDD.getNombreEsquemaHis());
			}
				
			inputProcesaType.setPTxtDescripcion(pantallaProcesarScript.getTxtDescripcion().getText());
			
			File file = (File) pantallaProcesarScript.getParams().get("file");
			inputProcesaType.setPNombreFichaEntrada(file.getName());
			
			String parentPath = file.getAbsoluteFile().getParent();
			inputProcesaType.setPTxtRutaEntrada(parentPath);
			
			OutputProcesaType outputProcesaType = typeService.procesarType(inputProcesaType);
			
			Proceso proceso = generateProceso(usuario, outputProcesaType);
			proceso.setModelo(seleccionado);
			proceso.setSubproyecto(subProyecto);
			proceso.setBbdd(selectedBBDD);
			proceso.setBbdds(pantallaProcesarScript.getBbdds());
			
			pantallaProcesarScript.getReturnParams().put("proceso", proceso);
			
			List<Type> listaTypes = outputProcesaType.getListaType();
			proceso.setTypes(listaTypes);
			
			String nombreScriptLanza = outputProcesaType.getPNombreScriptLanza();
			List<TextoLinea> scriptLanza = outputProcesaType.getTxtScriptLanza();
			
			String nombreFicheroLog = outputProcesaType.getPNombreScriptLog();
			proceso.setFicheroLog(nombreFicheroLog);
			
			if (CollectionUtils.isNotEmpty(listaTypes)) {
				pantallaProcesarScript.getReturnParams().put("types", listaTypes);
				pantallaProcesarScript.getReturnParams().put("nombreScriptLanza", nombreScriptLanza);
				pantallaProcesarScript.getReturnParams().put("scriptLanza", scriptLanza);
			}
			
			pantallaProcesarScript.dispose();
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}

	/**
	 * @param usuario
	 * @param outputProcesa
	 * @return
	 */
	private Proceso generateProceso(String usuario, OutputProcesa outputProcesa) {
		Proceso proceso = new Proceso();
		proceso.setIdProceso(outputProcesa.getIdProceso());
		proceso.setCodigoEstadoProceso(outputProcesa.getPCodigoEstadoProceso());
		proceso.setDescripcionEstadoProceso(outputProcesa.getPDescripcionEstadoProceso());
		
		proceso.setCodigoPeticion(pantallaProcesarScript.getTxtPeticion().getText());
		proceso.setCodigoDemanda(pantallaProcesarScript.getTxtDemanda().getText());
		proceso.setCodigoUsr(usuario);
		proceso.setCodigoUsrPeticion(pantallaProcesarScript.getTxtSolicitadaPor().getText());
		proceso.setTxtDescripcion(pantallaProcesarScript.getTxtDescripcion().getText());
		return proceso;
	}

	/**
	 * 
	 */
	private void establecerModelo(Modelo modelo) {
		try {
			if (!Objects.isNull(modelo)) {
				pantallaProcesarScript.setModeloSeleccionado(modelo);
				procesarModelo();
			}
		} catch (ServiceException e) {
			Map<String, Object> errParams = MDSQLUIHelper.buildError(e);
			MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_ERROR, errParams);
		}
	}
	
	/**
	 * @param codModelo
	 * @param nombreModelo
	 * @param codSubmodelo
	 * @return
	 * @throws ServiceException 
	 */
	private List<Modelo> buscar(String codModelo, String nombreModelo, String codSubmodelo) throws ServiceException {
		ModeloService modeloService = (ModeloService) getService(MDSQLConstants.MODELO_SERVICE);
		
		OutputConsultaModelos outputConsultaModelos = modeloService.consultaModelos(codModelo, nombreModelo, codSubmodelo);
		
		// Hay avisos
		if (outputConsultaModelos.getResult() == 2) {
			ServiceException serviceException = outputConsultaModelos.getServiceException();
			Map<String, Object> params = MDSQLUIHelper.buildWarnings(serviceException.getErrors());
			MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_WARN, params);
		}
		
		return outputConsultaModelos.getModelos(); 
	}
}
