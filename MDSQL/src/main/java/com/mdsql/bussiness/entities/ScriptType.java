package com.mdsql.bussiness.entities;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScriptType implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7757033182246284729L;
	private List<TextoLinea> txtScript;
    private String nombreScript;
    private String tipoScript;

}
