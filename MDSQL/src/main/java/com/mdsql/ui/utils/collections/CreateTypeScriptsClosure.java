package com.mdsql.ui.utils.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mdsql.bussiness.entities.ScriptType;
import com.mdsql.bussiness.entities.Type;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateTypeScriptsClosure implements Closure {
	
	private String selectedRoute;

	public CreateTypeScriptsClosure(String selectedRoute) {
		super();
		this.selectedRoute = selectedRoute;
	}

	@Override
	public void execute(Object input) {
		Type type = (Type) input;
		try {
			String nombreObjeto = StringUtils.EMPTY;
			
			// Sólo crea la ruta si no tiene scripts de tipo DROP
			String drop = !Objects.isNull(type.getDROP()) ? type.getDROP() : "N";
			if (StringUtils.isNotBlank(type.getNombreObjeto()) && !drop.equals("S")) {
				nombreObjeto = type.getNombreObjeto();
				MDSQLAppHelper.createFolder(selectedRoute, nombreObjeto);
			}
			
			List<ScriptType> scriptTypes = type.getScriptType();
			if (CollectionUtils.isNotEmpty(scriptTypes)) {
				for (ScriptType scriptType : scriptTypes) {
					String nombreScript = scriptType.getNombreScript();
					Path filePath = Paths.get(selectedRoute, nombreObjeto + File.separator + nombreScript);
					MDSQLAppHelper.dumpLinesToFile(scriptType.getTxtScript(), filePath.toFile());
				}
			}
		} catch (IOException e) {
			LogWrapper.error(log, "ERROR: ", e);
		}
		
	}

}
