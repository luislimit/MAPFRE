/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.bussiness.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 *
 * @author LVARONA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OutputParamInformeTRN extends OutputWarning implements Serializable {

    private static final long serialVersionUID = 3938170200941884495L;

    private String tipEnvio;
    private String servidor;
    private int puerto;
    private String usrFtp;
    private String pwdFtp;
    private String ruta;
}
