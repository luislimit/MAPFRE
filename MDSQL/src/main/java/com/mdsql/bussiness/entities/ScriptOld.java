package com.mdsql.bussiness.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScriptOld implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4494380619327570796L;
	private String nombreScriptOld;
    private String nombreScriptNew;

}
