/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form;

import com.mdsql.bussiness.entities.Grant;
import com.mdsql.bussiness.entities.Propietario;
import com.mdsql.ui.form.listener.FormConsultaPermisosPersonalizadosListener;
import com.mdsql.ui.model.PermisosColumnaTableModel;
import com.mdsql.ui.model.SinonimosObjetoTableModel;
import com.mdsql.ui.model.cabeceras.TablaPermisosColumnaCabecera;
import com.mdsql.ui.model.cabeceras.TablaSinonimosObjetoCabecera;
import com.mdsql.ui.renderer.TableSelectionRenderer;
import com.mdsql.ui.utils.DialogSupportModeloPermiso;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdval.ui.model.cabeceras.Cabecera;
import com.mdval.ui.utils.FrameSupport;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormConsultaPermisosPersonalizados extends DialogSupportModeloPermiso {

    /**
     * Creates new form FormConsultaPermisosPersonalizados 2.1.2.5 - Pantalla
     * Consulta de permisos personalizados
     *
     * @param parent
     * @param modal
     */
    public FormConsultaPermisosPersonalizados(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }

    public FormConsultaPermisosPersonalizados(FrameSupport parent, Boolean modal, Map<String, Object> params) {
        super(parent, modal, params);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblModeloProyecto = new javax.swing.JLabel();
        txtModeloProyecto = new javax.swing.JTextField();
        btnModeloProyecto = new javax.swing.JButton();
        lblNombreObjeto = new javax.swing.JLabel();
        txtNombreObjeto = new javax.swing.JTextField();
        lblColumna = new javax.swing.JLabel();
        txtColumna = new javax.swing.JTextField();
        lblPermisoSinonimo = new javax.swing.JLabel();
        cmbPermisoSinonimo = new javax.swing.JComboBox<>();
        lblPropietarioSinonimo = new javax.swing.JLabel();
        cmbPropietarioSinonimo = new javax.swing.JComboBox<>();
        lblWithGrantOption = new javax.swing.JLabel();
        cmbWithGrantOption = new javax.swing.JComboBox<>();
        lblPeticion = new javax.swing.JLabel();
        txtPeticion = new javax.swing.JTextField();
        lblFuncionNombre = new javax.swing.JLabel();
        txtFuncionNombre = new javax.swing.JTextField();
        lblUsuarioModificacion = new javax.swing.JLabel();
        txtUsuarioModificacion = new javax.swing.JTextField();
        lblTipoObjeto = new javax.swing.JLabel();
        cmbTipoObjeto = new javax.swing.JComboBox<>();
        lblReceptorPermisos = new javax.swing.JLabel();
        cmbReceptorPermisos = new javax.swing.JComboBox<>();
        lblFechaDesde = new javax.swing.JLabel();
        txtFechaDesde = new javax.swing.JTextField();
        lblPermiso = new javax.swing.JLabel();
        cmbPermiso = new javax.swing.JComboBox<>();
        lblIncluirPDC = new javax.swing.JLabel();
        cmbIncluirPDC = new javax.swing.JComboBox<>();
        lblFechaHasta = new javax.swing.JLabel();
        txtFechaHasta = new javax.swing.JTextField();
        lblEntorno = new javax.swing.JLabel();
        cmbEntorno = new javax.swing.JComboBox<>();
        chkHabilitada = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblTablaPermisos = new javax.swing.JLabel();
        scrollPanePermisos = new javax.swing.JScrollPane();
        tblPermisos = new javax.swing.JTable();
        lblSinonimos = new javax.swing.JLabel();
        scrollPaneSinonimos = new javax.swing.JScrollPane();
        tblSinonimos = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblModeloProyecto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblModeloProyecto.setText("Modelo o Proyecto");

        btnModeloProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loupe.png"))); // NOI18N

        lblNombreObjeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombreObjeto.setText("Nombre Objeto");

        lblColumna.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblColumna.setText("Columna");

        lblPermisoSinonimo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPermisoSinonimo.setText("Permiso/Sinónimo");

        lblPropietarioSinonimo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPropietarioSinonimo.setText("Propietario Sinónimo");

        lblWithGrantOption.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWithGrantOption.setText("WITH GRANT OPTION");

        lblPeticion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPeticion.setText("Petición");

        lblFuncionNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncionNombre.setText("Función Nombre");

        txtFuncionNombre.setToolTipText("");

        lblUsuarioModificacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuarioModificacion.setText("Usuario Modificación");

        lblTipoObjeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTipoObjeto.setText("Tipo de Objeto");

        lblReceptorPermisos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblReceptorPermisos.setText("Receptor Permisos");

        lblFechaDesde.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaDesde.setText("Fecha Desde");

        lblPermiso.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPermiso.setText("Permiso");

        lblIncluirPDC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIncluirPDC.setText("Incluir en PDC");

        lblFechaHasta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaHasta.setText("Fecha Hasta");

        lblEntorno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntorno.setText("Entorno");

        chkHabilitada.setText("Habilitada");
        chkHabilitada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkHabilitada.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        btnBuscar.setText("Buscar");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");

        lblTablaPermisos.setText("Permisos");

        tblPermisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Receptor", "Modelo", "Objeto", "Columna", "Tipo Objeto", "Permiso", "Entorno", "Grant Option", "Incluir en PDC", "Habilitada", "Petición", "Usuario Alta", "Fecha Alta", "Usuario Modificacion", "Fecha Modificacion"
            }
        ));
        tblPermisos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPermisos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPanePermisos.setViewportView(tblPermisos);

        lblSinonimos.setText("Sinónimos");

        tblSinonimos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Receptor", "Modelo", "Objeto", "Tipo Objeto", "Entorno", "Propietario", "Incluir en PDC", "Habilitada", "Petición", "Función Nombre", "Usuario Alta", "Fecha Alta", "Usuario Modificacion", "Fecha Modificacion"
            }
        ));
        tblSinonimos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPaneSinonimos.setViewportView(tblSinonimos);

        btnCancelar.setText("CANCELAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneSinonimos)
                    .addComponent(scrollPanePermisos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSinonimos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTablaPermisos)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblPermisoSinonimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModeloProyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(320, 320, 320)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreObjeto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPropietarioSinonimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuncionNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblReceptorPermisos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIncluirPDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(chkHabilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(320, 320, 320)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblWithGrantOption, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFechaDesde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUsuarioModificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblColumna, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbWithGrantOption, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(743, 743, 743)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBuscar, btnCancelar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtModeloProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPermisoSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEntorno, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombreObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbPropietarioSinonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFuncionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbReceptorPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbIncluirPDC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblWithGrantOption, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbWithGrantOption, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHabilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addGap(5, 5, 5)
                .addComponent(lblTablaPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanePermisos, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSinonimos)
                .addGap(9, 9, 9)
                .addComponent(scrollPaneSinonimos, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        cmbPermisoSinonimo.getAccessibleContext().setAccessibleName("");
        cmbPropietarioSinonimo.getAccessibleContext().setAccessibleName("");
        txtPeticion.getAccessibleContext().setAccessibleName("");
        txtFuncionNombre.getAccessibleContext().setAccessibleName("");
        cmbTipoObjeto.getAccessibleContext().setAccessibleName("");
        cmbReceptorPermisos.getAccessibleContext().setAccessibleName("");
        cmbIncluirPDC.getAccessibleContext().setAccessibleName("");
        cmbEntorno.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModeloProyecto;
    private javax.swing.JCheckBox chkHabilitada;
    private javax.swing.JComboBox<String> cmbEntorno;
    private javax.swing.JComboBox<String> cmbIncluirPDC;
    private javax.swing.JComboBox<String> cmbPermiso;
    private javax.swing.JComboBox<String> cmbPermisoSinonimo;
    private javax.swing.JComboBox<Propietario> cmbPropietarioSinonimo;
    private javax.swing.JComboBox<Grant> cmbReceptorPermisos;
    private javax.swing.JComboBox<String> cmbTipoObjeto;
    private javax.swing.JComboBox<String> cmbWithGrantOption;
    private javax.swing.JLabel lblColumna;
    private javax.swing.JLabel lblEntorno;
    private javax.swing.JLabel lblFechaDesde;
    private javax.swing.JLabel lblFechaHasta;
    private javax.swing.JLabel lblFuncionNombre;
    private javax.swing.JLabel lblIncluirPDC;
    private javax.swing.JLabel lblModeloProyecto;
    private javax.swing.JLabel lblNombreObjeto;
    private javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lblPermisoSinonimo;
    private javax.swing.JLabel lblPeticion;
    private javax.swing.JLabel lblPropietarioSinonimo;
    private javax.swing.JLabel lblReceptorPermisos;
    private javax.swing.JLabel lblSinonimos;
    private javax.swing.JLabel lblTablaPermisos;
    private javax.swing.JLabel lblTipoObjeto;
    private javax.swing.JLabel lblUsuarioModificacion;
    private javax.swing.JLabel lblWithGrantOption;
    private javax.swing.JScrollPane scrollPanePermisos;
    private javax.swing.JScrollPane scrollPaneSinonimos;
    private javax.swing.JTable tblPermisos;
    private javax.swing.JTable tblSinonimos;
    private javax.swing.JTextField txtColumna;
    private javax.swing.JTextField txtFechaDesde;
    private javax.swing.JTextField txtFechaHasta;
    private javax.swing.JTextField txtFuncionNombre;
    private javax.swing.JTextField txtModeloProyecto;
    private javax.swing.JTextField txtNombreObjeto;
    private javax.swing.JTextField txtPeticion;
    private javax.swing.JTextField txtUsuarioModificacion;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void setupComponents() {
        initComponents();
    }

    @Override
    protected void initEvents() {
        formListener = new FormConsultaPermisosPersonalizadosListener(this);
        btnBuscar.addActionListener(formListener);
        btnLimpiar.addActionListener(formListener);
        tblPermisos.getSelectionModel().addListSelectionListener((FormConsultaPermisosPersonalizadosListener) formListener);
        tblSinonimos.getSelectionModel().addListSelectionListener((FormConsultaPermisosPersonalizadosListener) formListener);
        addOnLoadListener(formListener);
    }

    @Override
    protected void setupLiterals() {
        setTitle(literales.getLiteral("FormConsultaPermisosPersonalizados.title"));
        lblModeloProyecto.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblModeloProyecto"));
        lblNombreObjeto.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblNombreObjeto"));
        lblColumna.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblColumna"));
        lblPermisoSinonimo.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblPermisoSinonimo"));
        lblPropietarioSinonimo.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblPropietarioSinonimo"));
        lblWithGrantOption.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblWithGrantOption"));
        lblPeticion.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblPeticion"));
        lblFuncionNombre.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblFuncionNombre"));
        lblUsuarioModificacion.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblUsuarioModificacion"));
        lblTipoObjeto.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblTipoObjeto"));
        lblReceptorPermisos.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblReceptorPermisos"));
        lblFechaDesde.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblFechaDesde"));
        lblPermiso.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblPermiso"));
        lblIncluirPDC.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblIncluirPDC"));
        lblFechaHasta.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblFechaHasta"));
        lblEntorno.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.lblEntorno"));
        chkHabilitada.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.chkHabilitada"));
        btnBuscar.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.btnBuscar"));
        btnLimpiar.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.btnLimpiar"));
        btnCancelar.setText(literales.getLiteral("FormConsultaPermisosPersonalizados.btnCancelar"));
    }

    @Override
    protected void initModels() {
        Cabecera cabeceraPermisos = new TablaPermisosColumnaCabecera();
        MDSQLUIHelper.setTableModel(tblPermisos, new PermisosColumnaTableModel(cabeceraPermisos));
        //
        Cabecera cabeceraSinonimo = new TablaSinonimosObjetoCabecera();
        MDSQLUIHelper.setTableModel(tblSinonimos, new SinonimosObjetoTableModel(cabeceraSinonimo));
        //
        tblPermisos.setDefaultRenderer(String.class, new TableSelectionRenderer());
        tblPermisos.setCellSelectionEnabled(true);
        tblPermisos.setRowSelectionAllowed(true);
        tblPermisos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //
        tblSinonimos.setDefaultRenderer(String.class, new TableSelectionRenderer());
        tblSinonimos.setCellSelectionEnabled(true);
        tblSinonimos.setRowSelectionAllowed(true);        
        tblSinonimos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    protected void initialState() {
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    @Override
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    @Override
    public JButton getBtnModeloProyecto() {
        return btnModeloProyecto;
    }

    @Override
    public JComboBox<String> getCmbTipoObjeto() {
        return cmbTipoObjeto;
    }

    @Override
    public JComboBox<Propietario> getCmbPropietarioSinonimo() {
        return cmbPropietarioSinonimo;
    }

    @Override
    public JComboBox<Grant> getCmbReceptorPermisos() {
        return cmbReceptorPermisos;
    }

    public JTable getTblPermisos() {
        return tblPermisos;
    }

    public JTable getTblSinonimos() {
        return tblSinonimos;
    }

    @Override
    public JTextField getTxtModeloProyecto() {
        return txtModeloProyecto;
    }

    @Override
    public JCheckBox getChkHabilitada() {
        return chkHabilitada;
    }

    @Override
    public JComboBox<String> getCmbEntorno() {
        return cmbEntorno;
    }

    @Override
    public JComboBox<String> getCmbIncluirPDC() {
        return cmbIncluirPDC;
    }

    @Override
    public JComboBox<String> getCmbPermiso() {
        return cmbPermiso;
    }

    @Override
    public JComboBox<String> getCmbPermisoSinonimo() {
        return cmbPermisoSinonimo;
    }

    @Override
    public JComboBox<String> getCmbWithGrantOption() {
        return cmbWithGrantOption;
    }

    public JTextField getTxtColumna() {
        return txtColumna;
    }

    public JTextField getTxtFechaDesde() {
        return txtFechaDesde;
    }

    public JTextField getTxtFechaHasta() {
        return txtFechaHasta;
    }

    @Override
    public JTextField getTxtFuncionNombre() {
        return txtFuncionNombre;
    }

    public JTextField getTxtNombreObjeto() {
        return txtNombreObjeto;
    }

    @Override
    public JTextField getTxtPeticion() {
        return txtPeticion;
    }

    public JTextField getTxtUsuarioModificacion() {
        return txtUsuarioModificacion;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
   
}
