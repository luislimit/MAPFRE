
package com.mdsql.ui.model.cabeceras;

import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.ui.model.cabeceras.Cabecera;
import java.math.BigDecimal;

/**
 *
 * @author LVARONA
 */
public class TablaParametroScriptReentranteCabecera extends Cabecera {
	
        @Override
	public void setupCabecera() {
            addColumn("colParametro", 100, String.class);
            addColumn("colValor", 30, String.class);
                
		/*columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblParametro"));
		columnIdentifiers.add(MDSQLUIHelper.getKeyTextValue("lblValor"));
		
		columnClasses.add(String.class);
		columnClasses.add(String.class);
		
		columnSizes.add(100);
		columnSizes.add(30);*/
	}
}
