package com.cellphoneshop.securityConfig;

import org.springframework.security.crypto.keygen.KeyGenerators;

public final class MyKeyGenerator {

	private static String stringKey = "";
	private static byte[] bytesKey = null;

	public static void generateNewStringKey() {
		stringKey = KeyGenerators.string().generateKey();
	}

	public static void generateNewBytesKey(int length) {
		bytesKey = KeyGenerators.secureRandom(length).generateKey();
	}

	public static void generateNewBytesKey() {
		bytesKey = KeyGenerators.secureRandom().generateKey();		// 8 bytes length
	}

	public static String getKeyAsString() {
		return stringKey;
	}

	public static byte[] getKeyAsBytesSequence() {
		if(null == bytesKey) generateNewBytesKey();
		return bytesKey;
	}
}
