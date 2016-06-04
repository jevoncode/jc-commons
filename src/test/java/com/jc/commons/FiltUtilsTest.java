package com.jc.commons;

import java.util.Set;

import org.junit.Test;

public class FiltUtilsTest {
	@Test
	public void availableCharsetsTest() {
		Set<String> charsets = FileUtils.availableCharsets();
		for (String cs : charsets)
			System.out.println(cs);
	}
}
