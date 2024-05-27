package com.mdsql.bussiness.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SeleccionHistorico {
	
	private Boolean configurado;
	private String objeto;
	private String tipo;
	private Boolean historico;
	private Boolean vigente;
	private Boolean editable;
}
