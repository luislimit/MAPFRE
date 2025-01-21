/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.HistoricoProc;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.utils.DateFormatter;
import java.util.List;

/**
 *
 * @author LVARONA
 */
public class MovimientosProcesadoTableModel extends DefaultTableModel<HistoricoProc> {

    /**
     *
     */
    private static final long serialVersionUID = -4191724356955356391L;

    /**
     * @param cabecera
     */
    public MovimientosProcesadoTableModel(Cabecera cabecera) {
        super(cabecera);
    }

    /**
     * @param columnNames
     * @param columnClasses
     */
    public MovimientosProcesadoTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
        super(columnNames, columnClasses);
    }

    /**
     * @param data
     * @param columnNames
     * @param columnClasses
     */
    public MovimientosProcesadoTableModel(List<HistoricoProc> data, List<String> columnNames, List<Class<?>> columnClasses) {
        super(data, columnNames, columnClasses);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HistoricoProc row = data.get(rowIndex);
        DateFormatter dateInformeFormatter;

        switch (columnIndex) {
            case 0:
                dateInformeFormatter = new DateFormatter(MDSQLConstants.INPUT_DATE_FORMAT);
                return dateInformeFormatter.dateToString(row.getFechaCambio());
            case 1:
                return row.getTipoCambio();
            case 2:
                return row.getValorCambio();
            case 3:
                return row.getCodUsr();
            default:
                break;
        }
        return null;
    }
}
