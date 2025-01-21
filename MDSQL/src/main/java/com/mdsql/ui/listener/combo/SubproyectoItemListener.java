package com.mdsql.ui.listener.combo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;

import com.mdsql.bussiness.entities.BBDD;
import com.mdsql.bussiness.entities.Modelo;
import com.mdsql.bussiness.entities.OutputConsulta;
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

    private final PantallaProcesarScript pantalla;

    public SubproyectoItemListener(PantallaProcesarScript pantalla) {
        super();
        this.pantalla = pantalla;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            Object item = event.getItem();

            if (!Objects.isNull(item)) {
                try {
                    SubProyecto subproyecto = (SubProyecto) item;
                    LogWrapper.debug(log, "Selected: %s", subproyecto.toString());
                    pantalla.setSubModelo(subproyecto);

                    Modelo modeloSeleccionado = pantalla.getModelo();
                    fillBBDD(modeloSeleccionado, subproyecto);
                } catch (ServiceException e) {
                    MDSQLUIHelper.showErrors(pantalla.getFrameParent(), e);
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

        OutputConsulta<BBDD> output = bbddService.consultaBBDDModelo(codigoProyecto, codigoSubproyecto);
        MDSQLUIHelper.showWarnings(pantalla, output.getWarnings());

        pantalla.setBbdds(output.getLista());
        if (CollectionUtils.isNotEmpty(output.getLista())) {
            BBDDComboBoxModel modelBBDD = new BBDDComboBoxModel(output.getLista());
            pantalla.getCmbBBDD().setModel(modelBBDD);
        }
    }
}
