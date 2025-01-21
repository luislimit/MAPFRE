package com.mdsql.ui.model;

import com.mdsql.bussiness.entities.ReentranteComentarioColumna;
import java.util.List;
import com.mdsql.ui.model.cabeceras.TablaParametroReentranteCabecera;
import com.mdval.ui.model.DefaultTableModel;
import com.mdval.ui.model.cabeceras.Cabecera;

/**
 *
 * @author LVARONA
 */
public class ComentarioReentranteTableModel extends DefaultTableModel<ReentranteComentarioColumna> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ComentarioReentranteTableModel() {
		super(new TablaParametroReentranteCabecera());
	}        
        
	/**
	 * @param cabecera
	 */
	public ComentarioReentranteTableModel(Cabecera cabecera) {
		super(cabecera);
	}

	/**
	 * @param columnNames
	 * @param columnClasses
	 */
	public ComentarioReentranteTableModel(List<String> columnNames, List<Class<?>> columnClasses) {
		super(columnNames, columnClasses);
	}

	/**
	 * @param data
	 * @param columnNames
	 * @param columnClasses
	 */
	public ComentarioReentranteTableModel(List<ReentranteComentarioColumna> data, List<String> columnNames, List<Class<?>> columnClasses) {
		super(data, columnNames, columnClasses);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ReentranteComentarioColumna row = data.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return row.getNombreColumna();
                case 1:
                    return row.getIdioma();
                case 2:
                    return row.getTipo();
                case 3:
                    return row.getRdo();
                case 4:
                    return row.getComentario();
                case 5:
                    return row.getComentarioGenerico();
                default:
                    break;
            }

		return null;
	}
}



