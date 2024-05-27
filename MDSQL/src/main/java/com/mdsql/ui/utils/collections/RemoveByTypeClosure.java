package com.mdsql.ui.utils.collections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.collections.Closure;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveByTypeClosure implements Closure {
	
	private String[] extensions;
	
	private Boolean onlyDir;

	public RemoveByTypeClosure(String[] extensions, Boolean onlyDir) {
		super();
		this.extensions = extensions;
		this.onlyDir = onlyDir;
	}

	@Override
	public void execute(Object input) {
		File file = (File) input;
		
		for (String extension : extensions) {
			if (file.isDirectory() && onlyDir) {
				checkAndRemove(file, extension);
			}
		}
	}

	private void checkAndRemove(File file, String extension) {
		if (file.getName().toLowerCase().contains(extension.toLowerCase())) {
			try {
				log.info("Borrando {}", file.getAbsolutePath());
				Files.delete(Paths.get(file.getAbsolutePath()));
			} catch (IOException e) {
				log.warn(e.getMessage());
			}
		}
	}

}
