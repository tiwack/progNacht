package com.example.geomessaging;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class HashAndSaltTest {
	
	@Test
	@Parameters({"bla, d81e7fc0fc0ed2454048",
				"SalzUndPfeffer, 90c5c2a1d832ba31008b"		
				})
	void createMd5Hash(String input, String result){
		assertEquals(HashAndSalt.createMd5Hash(input), result);
	}
	@Test
	@Parameters({"bla",
				"SalzUndPfeffer"		
				})
	void istAlgStabil(String input){
		assertEquals(HashAndSalt.createMd5Hash(input), HashAndSalt.createMd5Hash(input));
		
	}
	
	

}
