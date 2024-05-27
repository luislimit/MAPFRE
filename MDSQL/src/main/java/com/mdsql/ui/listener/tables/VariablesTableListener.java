package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mdsql.bussiness.entities.Variable;
import com.mdsql.ui.PantallaMantenimientoVariables;
import com.mdsql.ui.model.VariableTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.DateFormatter;
import com.mdval.utils.AppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariablesTableListener extends ListenerSupport implements ListSelectionListener {

	private PantallaMantenimientoVariables pantallaMantenimientoVariables;

	private DateFormatter dateFormatter;

	public VariablesTableListener(PantallaMantenimientoVariables pantallaMantenimientoVariables) {
		super();
		this.pantallaMantenimientoVariables = pantallaMantenimientoVariables;

		dateFormatter = new DateFormatter();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		VariableTableModel tableModel = (VariableTableModel) pantallaMantenimientoVariables.getTblVariables().getModel();
		
		Variable seleccionada = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionada)) {
			LogWrapper.debug(log, "Selected: %s", seleccionada.toString());

			pantallaMantenimientoVariables.getTxtCodigoVariable().setText(seleccionada.getCodigoVariable());
			pantallaMantenimientoVariables.getTxtValorVariable().setText(seleccionada.getValor());
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbUsoInterno(), seleccionada.getUsoInterno());
			pantallaMantenimientoVariables.getTxtPeticion().setText(seleccionada.getPeticion());
			pantallaMantenimientoVariables.getTxtValorVariableSustituir().setText(seleccionada.getValorSustituir());
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbTipoVariable(), seleccionada.getTipo());
			pantallaMantenimientoVariables.getTxtBBDD().setText(seleccionada.getBbdd());
			MDSQLUIHelper.setSelectedItem(pantallaMantenimientoVariables.getCmbEntorno(), seleccionada.getEntorno());
			pantallaMantenimientoVariables.getChkHabilitada().setSelected(AppHelper.normalizeCheckValue(seleccionada.getHabilitada()));
			pantallaMantenimientoVariables.getTxtUsuarioAlta().setText(seleccionada.getUsrAlta());

			String sFecha = dateFormatter.dateToString(seleccionada.getFechaAlta());
			pantallaMantenimientoVariables.getTxtFechaAlta().setText(sFecha);

			pantallaMantenimientoVariables.getTxtUsuarioModificacion().setText(seleccionada.getUsrModificacion());

			sFecha = dateFormatter.dateToString(seleccionada.getFechaModificacion());
			pantallaMantenimientoVariables.getTxtFechaModificacion().setText(sFecha);

			pantallaMantenimientoVariables.getBtnGuardar().setEnabled(Boolean.TRUE);
		}
	}

	
}
