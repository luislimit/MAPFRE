package com.mdsql.ui.listener.tables;

import java.util.Objects;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.model.FramePrincipalTypesTableModel;
import com.mdsql.ui.utils.ListenerSupport;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListaObjetosTableListener extends ListenerSupport implements ListSelectionListener {

	private FramePrincipal framePrincipal;

	public ListaObjetosTableListener(FramePrincipal framePrincipal) {
		super();
		this.framePrincipal = framePrincipal;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
	        return;
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		Integer index = lsm.getMinSelectionIndex();
		
		FramePrincipalTypesTableModel tableModel = (FramePrincipalTypesTableModel) framePrincipal.getTblListaObjetos().getModel();
		
		Type seleccionado = tableModel.getSelectedRow(index);
		if (!Objects.isNull(seleccionado)) {
			LogWrapper.debug(log, "Selected: %s", seleccionado.toString());
			framePrincipal.setTypeSeleccionado(seleccionado);
			
			rellenarCuadros(seleccionado);
		}
	}

	private void rellenarCuadros(Type seleccionado) {
		clearCuadros();
		
		for (ScriptType type : seleccionado.getScriptType()) {
			LogWrapper.debug(log, "Script: %s", type.toString());
			if ("TYS".equals(type.getTipoScript())) {
				framePrincipal.getIfrmTYS().setTitle(type.getNombreScript());
				framePrincipal.getTxtScriptTYS().setText(StringUtils.EMPTY);
				MDSQLAppHelper.dumpContentToText(type.getTxtScript(), framePrincipal.getTxtScriptTYS());
				MDSQLUIHelper.resetCursor(framePrincipal.getTxtScriptTYS());
			}
			
			if ("TYB".equals(type.getTipoScript())) {
				framePrincipal.getIfrmTYB().setTitle(type.getNombreScript());
				framePrincipal.getTxtScriptTYB().setText(StringUtils.EMPTY);
				MDSQLAppHelper.dumpContentToText(type.getTxtScript(), framePrincipal.getTxtScriptTYB());
				MDSQLUIHelper.resetCursor(framePrincipal.getTxtScriptTYB());
			}

			if ("PDC".equals(type.getTipoScript())) {
				framePrincipal.getInternalFramePDC().setTitle(type.getNombreScript());
				framePrincipal.getTxtScriptPDC().setText(StringUtils.EMPTY);
				MDSQLAppHelper.dumpContentToText(type.getTxtScript(), framePrincipal.getTxtScriptPDC());
				MDSQLUIHelper.resetCursor(framePrincipal.getTxtScriptPDC());
			}
			
			if ("DROP".equals(type.getTipoScript())) {
				framePrincipal.getIfrmTYS().setTitle(type.getNombreScript());
				framePrincipal.getTxtScriptTYS().setText(StringUtils.EMPTY);
				MDSQLAppHelper.dumpContentToText(type.getTxtScript(), framePrincipal.getTxtScriptTYS());
				MDSQLUIHelper.resetCursor(framePrincipal.getTxtScriptTYS());
			}
			
			if ("DROPS".equals(type.getTipoScript())) {
				framePrincipal.getInternalFramePDC().setTitle(type.getNombreScript());
				framePrincipal.getTxtScriptPDC().setText(StringUtils.EMPTY);
				MDSQLAppHelper.dumpContentToText(type.getTxtScript(), framePrincipal.getTxtScriptPDC());
				MDSQLUIHelper.resetCursor(framePrincipal.getTxtScriptPDC());
			}
		}
		
	}

	private void clearCuadros() {
		framePrincipal.getIfrmTYS().setTitle(StringUtils.EMPTY);
		framePrincipal.getTxtScriptTYS().setText(StringUtils.EMPTY);
		framePrincipal.getIfrmTYB().setTitle(StringUtils.EMPTY);
		framePrincipal.getTxtScriptTYB().setText(StringUtils.EMPTY);
		framePrincipal.getInternalFramePDC().setTitle(StringUtils.EMPTY);
		framePrincipal.getTxtScriptPDC().setText(StringUtils.EMPTY);
	}

	
}
