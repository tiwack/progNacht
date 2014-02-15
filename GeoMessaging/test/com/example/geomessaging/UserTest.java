package com.example.geomessaging;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class UserTest {

	@Test
	@Parameters({ "mathe@master.de, true", "tim@htwg-konstanz.de, true",
			"kÄÄÄÄsewurst@mensa.org, false", "wurst@mensa.org, true",
			"@halt.de, false", "john@wayne , false", "bla@bla.bla, false" })
	void checkRegexMail(String mail, boolean result) {
		boolean temp = User.checkRegexMail(mail);
		assertTrue(result ? temp : !temp);

	}

}
