package HealthyCodeApp.maven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BMICalculatorTest {

	@Test
	void shouldReturnFalseWhenDietNotRecommended() {
		double weight = 80.0;
		double height = 1.70;

		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		assertTrue(recommended);
	}

	@Test
	void shouldReturnTruesWhenDietNotRecommended() {
		double weight = 50.0;
		double height = 1.92;

		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		assertFalse(recommended);

	}
}
