package com.mdval;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.mdsql.config.OracleDataSourceConfig;
import com.mdsql.utils.LiteralesSingleton;

@SpringBootTest
@ContextConfiguration(classes = {OracleDataSourceConfig.class})
class MDSQLApplicationTests {

	@MockBean
	private DataSource dataSource;

	@Test
	void contextLoads() {
		try {
			LiteralesSingleton literales = LiteralesSingleton.getInstance();
			//assertEquals("Alta", literales.getLiteral("tblGlosarios.fecAlta"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
