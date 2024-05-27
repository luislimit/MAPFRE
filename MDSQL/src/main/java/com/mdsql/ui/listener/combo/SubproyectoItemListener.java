package com.mdsql.ui.listener.combo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.SubProyecto;
import com.mdsql.bussiness.service.BBDDService;
import com.mdsql.ui.PantallaProcesarScript;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.exceptions.ServiceException;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubproyectoItemListener extends ListenerSupport implements ItemListener {

	private PantallaProcesarScript pantallaProcesarScript;

	public SubproyectoItemListener(PantallaProcesarScript pantallaProcesarScript) {
		super();
		this.pantallaProcesarScript = pantallaProcesarScript;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();

			if (!Objects.isNull(item)) {
				try {
					SubProyecto subproyecto = (SubProyecto) item;
					LogWrapper.debug(log, "Selected: %s", subproyecto.toString());
					pantallaProcesarScript.setSubproyectoSeleccionado(subproyecto);

					Modelo modeloSeleccionado = pantallaProcesarScript.getModeloSeleccionado();
					fillBBDD(modeloSeleccionado, subproyecto);
				} catch (ServiceException e) {
					Map<String, Object> params = MDSQLUIHelper.buildError(e);
					MDSQLUIHelper.showPopup(pantallaProcesarScript.getFrameParent(), MDSQLConstants.CMD_ERROR, params);
				}
			}
		}
	}

	/**
	 * @param seleccionado
	 */
	private void fillBBDD(Modelo modelo, SubProyecto subproyecto) throws ServiceException {
		BBDDService bbddService = (BBDDService) getService(MDSQLConstants.BBDD_SERVICE);

		String codigoProyecto = modelo.getCodigoProyecto();
		String codigoSubproyecto = !Objects.isNull(subproyecto) ? subproyecto.getCodigoSubProyecto() : null;

		List<BBDD> bbdds = bbddService.consultaBBDDModelo(codigoProyecto, codigoSubproyecto);
		pantallaProcesarScript.setBbdds(bbdds);
		if (CollectionUtils.isNotEmpty(bbdds)) {
			BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(bbdds);
			pantallaProcesarScript.getCmbBBDD().setModel(modelBBDD);
		}
	}
}