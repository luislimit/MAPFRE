package com.mdsql;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mdsql.bussiness.entities.Session;
import com.mdsql.ui.FramePrincipal;
import com.mdsql.ui.utils.MDSQLUIHelper;
import com.mdsql.utils.ConfigurationSingleton;
import com.mdsql.utils.MDSQLAppHelper;
import com.mdsql.utils.MDSQLConstants;
import com.mdval.utils.AppGlobalSingleton;
import com.mdval.utils.LogWrapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MDSQLApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MDSQLApplication.class);
		builder.headless(false);
		builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String version = System.getProperty("java.version");
		LogWrapper.debug(log, "Java version: %s", version);
		LogWrapper.debug(log, "Default charset for application: %s", MDSQLConstants.DEFAULT_CHARSET.toString());

		setupSpringContext();
		setupUIEnvironment();
		displayApp();
	}

	/**
	 * Inicializa el contexto de Spring y lo pone a disposición del
	 * aplicativo visual en el almacenamiento global.
	 */
	private void setupSpringContext() {
		AppGlobalSingleton appGlobalSingleton = AppGlobalSingleton.getInstance();

		LogWrapper.debug(log, "%s", applicationContext.getDisplayName());
		appGlobalSingleton.setProperty(MDSQLConstants.SPRING_CONTEXT, applicationContext);

		LogWrapper.debug(log, "Connection Polling datasource: %s", dataSource);
	}

	/**
	 * 
	 */
	private void setupUIEnvironment() {
		// Enter para los botones que tengan el foco
		UIManager.put("Button.focusInputMap",
				new UIDefaults.LazyInputMap(new Object[] { "ENTER", "pressed", "released ENTER", "released" }));
	}

	/**
	 * 
	 */
	private void displayApp() {
		/* Create and display the form */
		EventQueue.invokeLater(() -> {
			try {
				if (MDSQLAppHelper.confirmPayload()) {
					ConfigurationSingleton configuration = ConfigurationSingleton.getInstance();
					
					// If has session saved, set in global properties. If not, creates it
					Session session = (Session) MDSQLAppHelper.deserializeFromDisk(MDSQLConstants.SESSION);
					if (Objects.isNull(session)) {
						session = new Session();
						String codUsr = System.getenv(configuration.getConfig("user.field"));
						session.setCodUsr(codUsr);
						LogWrapper.debug(log, "Usuario: %s", codUsr);
					}
					
					MDSQLAppHelper.setGlobalProperty(MDSQLConstants.SESSION, session);
					
					FramePrincipal framePrincipal = new FramePrincipal();
					framePrincipal.setProcesado(session.getProcesado());
					MDSQLUIHelper.showMaximized(framePrincipal);
					framePrincipal.setVisible(Boolean.TRUE);
				}
				else {
					MDSQLAppHelper.doPayload();
				}
			} catch (IOException e) {
				Map<String, Object> params = MDSQLUIHelper.buildError(e);
				MDSQLUIHelper.showPopup(null, MDSQLConstants.CMD_ERROR, params);
			}
		});
	}
}
