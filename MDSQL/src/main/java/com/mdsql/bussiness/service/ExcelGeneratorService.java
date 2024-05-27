package com.mdsql.bussiness.service;

import java.util.Date;
import java.util.List;

import com.mdsql.bussiness.entities.Historico;
import com.mdsql.bussiness.entities.InformeCambios;
import com.mdsql.bussiness.entities.Permiso;
import com.mdsql.bussiness.entities.Sinonimo;
import javax.swing.JTable;

/**
 * @author hcarreno
 */
public interface ExcelGeneratorService {

    void generarExcelHistoricoCambios(List<InformeCambios> listaCambios, String path, String codigoProyecto,
            String fechaDesde, String fechaHasta);

    void generarExcelHistorico(List<Historico> lista, String path, String sufijo, String codigoProyecto, Date date);

    void generarExcelSinonimos(List<Sinonimo> sinonimosGenerales, String path, String sufijo, String codigoProyecto, Date date);

    void generarExcelPermisos(List<Permiso> permisosGenerales, String path, String sufijo, String codigoProyecto, Date date);

    void generarExcelSinonimos(JTable table, String sufijo, String codigoProyecto);

    void generarExcelPermisos(JTable table, String sufijo, String codigoProyecto);

}
