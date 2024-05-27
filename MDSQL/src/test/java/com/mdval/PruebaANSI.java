package com.mdval;

import java.io.*;

public class PruebaANSI {
	private File csvFile;
	private BufferedReader reader;
	private StringBuffer strBuffer;
	private BufferedWriter writer;
	int startNumber = 0;
	private String strString[];

	public PruebaANSI(String location) {
		csvFile = new File(location);
		strBuffer = new StringBuffer("");

		// Read
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "Cp1252"));
			// reader = new BufferedReader(new FileReader(csvFile));
			String line = "";

			while ((line = reader.readLine()) != null) {
				strBuffer.append(line + ";" + "\r\n");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(strBuffer.toString());

		// Write
		try {
			File file = new File("C:/pruebaJAVA/destino.sql");
			FileWriter filewrite = new FileWriter(file);

			if (!file.exists()) {
				file.createNewFile();
			}

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Cp1252"));
			// writer = new BufferedWriter(filewrite);

			writer.write(strBuffer.toString());
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new PruebaANSI("C:/pruebaJAVA/origen.sql");
	}
}