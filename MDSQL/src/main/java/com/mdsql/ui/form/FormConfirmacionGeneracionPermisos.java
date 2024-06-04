/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mdsql.ui.form;

import com.mdsql.ui.form.listener.FormConfirmacionGeneracionPermisosListener;
import com.mdsql.ui.model.BBDDComboBoxModel;
import com.mdsql.ui.renderer.BBDDRenderer;
import com.mdval.ui.utils.DialogSupport;
import com.mdval.ui.utils.FrameSupport;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.mdsql.bussiness.entities.BBDD;

/**
 *
 * @author Luis-Enrique.Varona
 */
public class FormConfirmacionGeneracionPermisos extends DialogSupport {

    public FormConfirmacionGeneracionPermisos(FrameSupport parent, Boolean modal) {
        super(parent, modal);
    }

    public FormConfirmacionGeneracionPermisos(FrameSupport parent, Boolean modal, Map<String, Object> params) {
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

        lblPeticion = new javax.swing.JLabel();
        txtPeticion = new javax.swing.JTextField();
        chkGenerarPermisosGenerales = new javax.swing.JCheckBox();
        lblSolicitadaPor = new javax.swing.JLabel();
        txtSolictadaPor = new javax.swing.JTextField();
        chkGenerarRestoPermisosPersonalizados = new javax.swing.JCheckBox();
        lblBBDD = new javax.swing.JLabel();
        cmbBBDD = new javax.swing.JComboBox<>();
        lblDemanda = new javax.swing.JLabel();
        txtDemanda = new javax.swing.JTextField();
        lblRuta = new javax.swing.JLabel();
        btnRuta = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        lblComentario = new javax.swing.JLabel();
        scrollPaneComentario = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblPeticion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPeticion.setText("Petición");

        txtPeticion.setToolTipText("");

        chkGenerarPermisosGenerales.setText("Generar Permisos Generales");
        chkGenerarPermisosGenerales.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblSolicitadaPor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSolicitadaPor.setText("Solicitada Por");

        txtSolictadaPor.setToolTipText("");

        chkGenerarRestoPermisosPersonalizados.setText("Generar Resto Permisos Personalizados");
        chkGenerarRestoPermisosPersonalizados.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lblBBDD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBBDD.setText("BBDD");

        lblDemanda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDemanda.setText("Demanda");

        txtDemanda.setToolTipText("");

        lblRuta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRuta.setText("Ruta");

        btnRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder-open.png"))); // NOI18N
        btnRuta.setToolTipText("");

        txtRuta.setEditable(false);

        lblComentario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblComentario.setText("Comentario");

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        scrollPaneComentario.setViewportView(txtComentario);

        btnAceptar.setText("Aceptar");

        btnCancelar.setText("CANCELAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSolicitadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSolictadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkGenerarRestoPermisosPersonalizados)
                            .addComponent(chkGenerarPermisosGenerales, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(scrollPaneComentario)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRuta)
                        .addGap(7, 7, 7)
                        .addComponent(txtRuta)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAceptar, btnCancelar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkGenerarPermisosGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkGenerarRestoPermisosPersonalizados, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSolicitadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSolictadaPor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRuta;
    private javax.swing.JCheckBox chkGenerarPermisosGenerales;
    private javax.swing.JCheckBox chkGenerarRestoPermisosPersonalizados;
    private javax.swing.JComboBox<BBDD> cmbBBDD;
    private javax.swing.JLabel lblBBDD;
    private javax.swing.JLabel lblComentario;
    private javax.swing.JLabel lblDemanda;
    private javax.swing.JLabel lblPeticion;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblSolicitadaPor;
    private javax.swing.JScrollPane scrollPaneComentario;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtDemanda;
    private javax.swing.JTextField txtPeticion;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtSolictadaPor;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void setupComponents() {
        initComponents();
    }

    @Override
    protected void initEvents() {
        FormConfirmacionGeneracionPermisosListener formListener
                = new FormConfirmacionGeneracionPermisosListener(this);
        btnAceptar.addActionListener(formListener);
        btnCancelar.addActionListener(formListener);
        btnRuta.addActionListener(formListener);
        addOnLoadListener(formListener);
    }

    @Override
    protected void setupLiterals() {
        setTitle(literales.getLiteral("FormConfirmacionGeneracionPermisos.title"));
        lblPeticion.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblPeticion"));
        chkGenerarPermisosGenerales.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.chkGenerarPermisosGenerales"));
        lblSolicitadaPor.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblSolicitadaPor"));
        chkGenerarRestoPermisosPersonalizados.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.chkGenerarRestoPermisosPersonalizados"));
        lblBBDD.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblBBDD"));
        lblDemanda.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblDemanda"));
        lblRuta.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblRuta"));
        lblComentario.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.lblComentario"));
        btnAceptar.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.btnAceptar"));
        btnCancelar.setText(literales.getLiteral("FormConfirmacionGeneracionPermisos.btnCancelar"));
    }

    @Override
    protected void initModels() {
        BBDDComboBoxModel bbddModel = new BBDDComboBoxModel();
        cmbBBDD.setModel(bbddModel);
        cmbBBDD.setRenderer(new BBDDRenderer());
    }

    @Override
    protected void initialState() {
        chkGenerarPermisosGenerales.setSelected(false);
        chkGenerarRestoPermisosPersonalizados.setSelected(false);
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JTextField getTxtPeticion() {
        return txtPeticion;
    }

    public JButton getBtnRuta() {
        return btnRuta;
    }

    public JCheckBox getChkGenerarPermisosGenerales() {
        return chkGenerarPermisosGenerales;
    }

    public JCheckBox getChkGenerarRestoPermisosPersonalizados() {
        return chkGenerarRestoPermisosPersonalizados;
    }

    public JTextArea getTxtComentario() {
        return txtComentario;
    }

    public JTextField getTxtDemanda() {
        return txtDemanda;
    }

    public JTextField getTxtRuta() {
        return txtRuta;
    }

    public JTextField getTxtSolictadaPor() {
        return txtSolictadaPor;
    }

    public JComboBox<BBDD> getCmbBBDD() {
        return cmbBBDD;
    }
}
