package com.mdsql.bussiness.service;

import com.mdsql.bussiness.entities.CodigoDescripcion;
import com.mdsql.bussiness.entities.OutputConsulta;
import com.mdsql.bussiness.entities.OutputWarning;
import com.mdsql.bussiness.entities.TablasDiagrama;
import com.mdval.exceptions.ServiceException;

/**
 *
 * @author LVARONA
 */
public interface SubjectAreaDiagramaService {
    
    /*
      procedure p_con_subjectareas_modelo(p_cod_proyecto       IN VARCHAR2,
                                      p_lista_subject_area OUT NOCOPY t_t_subject_area,
                                      p_resultado          IN OUT NOCOPY INTEGER,
                                      p_lista_errores      IN OUT NOCOPY t_t_error);
    */
    OutputConsulta<CodigoDescripcion> ConsultaSubjectAreasModelo(String codProyecto) throws ServiceException;
    
    /*
      procedure p_alta_sa(p_cod_proyecto     IN VARCHAR2,
                      p_cod_subject_area IN VARCHAR2,
                      p_des_subject_area IN VARCHAR2,
                      p_cod_usr          IN VARCHAR2,
                      p_resultado        IN OUT NOCOPY INTEGER,
                      p_lista_errores    IN OUT NOCOPY t_t_error);  
    */
    OutputWarning guardarSubjectArea(String codProyecto, String codigo, String Descripcion, String usuario) throws ServiceException;
    
    /*
     procedure p_con_diagramas_sa(p_cod_proyecto     IN VARCHAR2,
                                  p_cod_subject_area IN VARCHAR2,
                                  p_lista_diagramas  OUT NOCOPY t_t_diagrama,
                                  p_resultado        IN OUT NOCOPY INTEGER,
                                  p_lista_errores    IN OUT NOCOPY t_t_error);
     */
     OutputConsulta<CodigoDescripcion> ConsultaDiagramas(String codProyecto, String codSubjectArea) throws ServiceException;
     
     /*
       procedure p_alta_diagrama (p_cod_proyecto     IN VARCHAR2,
                      p_cod_subject_area IN VARCHAR2,
                      p_cod_diagrama     IN VARCHAR2,
                      p_des_diagrama     IN VARCHAR2,
                      p_cod_usr          IN VARCHAR2,
                      p_resultado        IN OUT NOCOPY INTEGER,
                      p_lista_errores    IN OUT NOCOPY t_t_error);   
     */
     OutputWarning guardarDiagrama(String codProyecto, String codSubjectArea, String codigo, String Descripcion, String usuario) throws ServiceException;
     
     /*
       procedure p_con_tablas_diagrama(p_cod_proyecto     IN VARCHAR2,
                                  p_cod_subject_area IN VARCHAR2,
                                  p_cod_diagrama     IN VARCHAR2,
                                  p_tablas_diagrama  OUT NOCOPY t_t_tablas_diagrama,
                                  p_resultado        IN OUT NOCOPY INTEGER,
                                  p_lista_errores    IN OUT NOCOPY t_t_error);  
     */
     OutputConsulta<TablasDiagrama> consultaTablasDiagrama(String codProyecto, String codSubjectArea, String codDiagrama) throws ServiceException;
     
     /*
       procedure p_del_tabla_diagrama(p_cod_proyecto     IN VARCHAR2,
                                  p_cod_subject_area IN VARCHAR2,
                                  p_cod_diagrama     IN VARCHAR2,
                                  p_nom_tabla        IN VARCHAR2,
                                  p_txt_comentario   IN VARCHAR2,
                                  p_cod_peticion     IN VARCHAR2,
                                  p_cod_usr          IN VARCHAR2,
                                  p_resultado        IN OUT NOCOPY INTEGER,
                                  p_lista_errores    IN OUT NOCOPY t_t_error);    
     */
    OutputWarning deshabilitaTablaDiagrama(
            String codProyecto, String codSubjectArea, String codDiagrama, String nomTabla, 
            String comentario, String codPeticion, String usuario
    ) throws ServiceException;      
    
    /*
      procedure p_mnto_diagrama(p_cod_proyecto     IN VARCHAR2,
                            p_cod_subject_area IN VARCHAR2,
                            p_des_subject_area IN VARCHAR2,
                            p_cod_diagrama     IN VARCHAR2,
                            p_des_diagrama     IN VARCHAR2,
                            p_cod_usr          IN VARCHAR2,
                            p_resultado        IN OUT NOCOPY INTEGER,
                            p_lista_errores    IN OUT NOCOPY t_t_error);  
    */
    OutputWarning mantenimientoDiagrama(
             String codProyecto, String codSubjectArea, String desSubjectArea, 
            String codDiagrama, String desDiagrama, String usuario
     ) throws ServiceException;
    
    
    /* Devuelve las tablas de un diagrama
      procedure p_busca_diagramas(p_cod_proyecto     IN VARCHAR2,
                              p_cod_subject_area IN VARCHAR2,
                              p_cod_diagrama     IN VARCHAR2,
                              p_nom_tabla        IN VARCHAR2,
                              p_cod_peticion     IN VARCHAR2,
                              p_fec_desde        IN VARCHAR2,
                              p_fec_hasta        IN VARCHAR2,
                              p_mostrar_inh      IN VARCHAR2,
                              p_tablas_diagrama  OUT NOCOPY t_t_tablas_diagrama,
                              p_resultado        IN OUT NOCOPY INTEGER,
                              p_lista_errores    IN OUT NOCOPY t_t_error); 
    */
    OutputConsulta<TablasDiagrama> buscaDiagramas(
        String codProyecto, String codSubjectArea, String codDiagrama, String nomTabla,
            String codPeticion, String fecDesde, String fecHasta, String mostrarInh
    ) throws ServiceException;
    
    
    /*
    procedure p_alta_tabla_diagrama(p_cod_proyecto     IN VARCHAR2,
                                    p_cod_subject_area IN VARCHAR2,
                                    p_cod_diagrama     IN VARCHAR2,
                                    p_nom_tabla        IN VARCHAR2,
                                    p_txt_comentario   IN VARCHAR2,
                                    p_cod_peticion     IN VARCHAR2,
                                    p_cod_usr          IN VARCHAR2,
                                    p_resultado        IN OUT NOCOPY INTEGER,
                                    p_lista_errores    IN OUT NOCOPY t_t_error);       
    */
    OutputWarning guardaTablasDiagrama(
             String codProyecto, String codSubjectArea,String codDiagrama, 
             String nomTabla, String comentario, String codPeticion, String usuario
     ) throws ServiceException;    
}
