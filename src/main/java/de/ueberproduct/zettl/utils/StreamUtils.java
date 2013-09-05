package de.ueberproduct.zettl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
	
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[512];
		int numberOfBytes = in.read(buffer);
		while (numberOfBytes > -1) {
			out.write(buffer, 0, numberOfBytes);
			numberOfBytes = in.read(buffer);
		}
	}

}
