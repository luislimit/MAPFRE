/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mdsql.bussiness.entities;

//import com.mdval.exceptions.ServiceException;
//import com.mdval.exceptions.ServiceException;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author LVARONA
 * @param <clase>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "childBuilder")
@EqualsAndHashCode(callSuper=true)
public class OutputConsulta<clase> extends OutputWarning implements Serializable {

    private static final long serialVersionUID = -8077635735910629830L;
    private List<clase> lista;	
}




