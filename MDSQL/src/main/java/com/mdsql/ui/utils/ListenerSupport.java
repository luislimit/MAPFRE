package com.mdsql.ui.utils;

import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.utils.observer.Observable;
import com.mdval.utils.AppHelper;
import com.mdval.utils.DateFormatter;
import com.mdval.utils.LiteralesSingleton;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public abstract class ListenerSupport extends Observable {

    protected LiteralesSingleton literales;

    protected DateFormatter dateFormatter;

    protected DateFormatter dateInformeFormatter;

    public ListenerSupport() {
        try {
            literales = LiteralesSingleton.getInstance();
            dateFormatter = new DateFormatter();
            dateInformeFormatter = new DateFormatter(MDSQLConstants.INFORME_DATE_FORMAT);
        } catch (IOException e) {
            log.warn("ListenerSupport ERROR:", e);
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
    protected void updateObservers(String cmd) {
        this.setChanged();
        this.notifyObservers(cmd);
    }

}
