package com.mdsql.ui.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.mdsql.utils.MDSQLConstants;
import com.mdsql.utils.DateFormatter;
import com.mdsql.utils.LiteralesSingleton;
import com.mdval.exceptions.ServiceException;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import com.mdval.ui.utils.UIHelper;
import com.mdval.ui.utils.observer.Observable;
import com.mdval.utils.AppHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public abstract class ListenerSupport extends Observable {
	
	protected LiteralesSingleton literales;
	
	protected DateFormatter dateFormatter;
	
	protected DateFormatter dateBuscarFormatter;
	
	protected DateFormatter dateInformeFormatter;
	
	protected DateFormatter dateInputFormatter;
	
	public ListenerSupport() {
		try {
			literales = LiteralesSingleton.getInstance();
			dateFormatter = new DateFormatter();
			dateBuscarFormatter = new DateFormatter(MDSQLConstants.FORMATO_FECHA_BUSCADOR_PETICIONES);
			dateInformeFormatter = new DateFormatter(MDSQLConstants.INFORME_DATE_FORMAT);
			dateInputFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
		} catch (IOException e) {
			log.warn("ERROR:", e);
		}
	}
	
	/**
	 * @param nameService
	 * @return
	 */
	protected Object getService(String nameService) {
		return AppHelper.getBean(nameService);
	}
	
	/**
	 * @param cmd
	 */
	protected void showFrame(String cmd, Boolean modal) {
		JFrame frame = MDSQLUIHelper.createFrame(cmd, modal);
		UIHelper.show(frame);
	}
	
	/**
	 * @param parent
	 * @param cmd
	 */
	protected void showFrame(FrameSupport parent, String cmd, Boolean modal) {
		JFrame frame = MDSQLUIHelper.createFrame(parent, cmd, modal);
		UIHelper.show(frame);
	}
	
	/**
	 * @param cmd
	 * @param params
	 */
	protected void showFrame(String cmd, Boolean modal, Map<String, Object> params) {
		JFrame frame = MDSQLUIHelper.createFrame(cmd, modal, params);
		UIHelper.show(frame);
	}
	
	/**
	 * @param parent
	 * @param cmd
	 * @param params
	 */
	protected void showFrame(FrameSupport parent, String cmd, Boolean modal, Map<String, Object> params) {
		JFrame frame = MDSQLUIHelper.createFrame(parent, cmd, modal, params);
		UIHelper.show(frame);
	}
	
	/**
	 * @param frame
	 * @param cmd
	 */
	protected void showPopup(FrameSupport frame, String cmd) {
		DialogSupport dialog = MDSQLUIHelper.createDialog(frame, cmd);
		UIHelper.show(dialog);
	}
	
	/**
	 * @param frame
	 * @param cmd
	 * @param params
	 */
	protected void showPopup(FrameSupport frame, String cmd, Map<String, Object> params) {
		DialogSupport dialog = MDSQLUIHelper.createDialog(frame, cmd, params);
		UIHelper.show(dialog);
	}
	
	/**
	 * @param e
	 * @return
	 */
	protected Map<String, Object> buildError(Exception e) {
		Map<String, Object> params = new HashMap<>();

		if (e instanceof ServiceException) {
			params.put(MDSQLConstants.SERVICE_ERROR, e);
		} else {
			params.put(MDSQLConstants.ERROR, e);
		}
		return params;
	}
	
	/**
	 * @param e
	 * @return
	 */
	protected Map<String, Object> buildWarning(ServiceException e) {
		Map<String, Object> params = new HashMap<>();

		params.put(MDSQLConstants.SERVICE_ERROR, e);
		params.put(MDSQLConstants.TYPE, MDSQLConstants.WARN);
		
		return params;
	}
	
	/**
	 * @param cmd
	 */
	protected void updateObservers(String cmd) {
		this.setChanged();
		this.notifyObservers(cmd);
	}
	
	/**
	 * 
	 */
	public void onSeleccionado() {}
}
