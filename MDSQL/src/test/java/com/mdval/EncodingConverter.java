package com.mdval;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.any23.encoding.TikaEncodingDetector;

class EncodingConverter {

	public static void main(String[] a) {
		String inFile = "SMD_Tabla_A.sql";
		String inCharsetName = "ISO-8859-1";
		String outFile = "SMD_Tabla_A_encoded.sql";
		String outCharsetName = "UTF-8";

		try (InputStream is = new FileInputStream(inFile)) {
			Charset cs = Charset.forName(new TikaEncodingDetector().guessEncoding(is));
			System.out.println("Charset: " + cs.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try (InputStreamReader in = new InputStreamReader(new FileInputStream(inFile), inCharsetName);
				OutputStreamWriter out = new OutputStreamWriter(bos, outCharsetName)) {
			int c = in.read();
			int n = 0;

			while (c != -1) {
				out.write(c);
				n++;
				c = in.read();
			}

			System.out.println("Number of characters: " + n);
			System.out.println("Number of input bytes: " + (new File(inFile)).length());
			//System.out.println("Number of output bytes: " + (new File(outFile)).length());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		byte[] bytes = bos.toByteArray();
		System.out.println("Number of output bytes: " + bytes.length);
		String s = new String(bytes);
		System.out.println(s);
	}
}
