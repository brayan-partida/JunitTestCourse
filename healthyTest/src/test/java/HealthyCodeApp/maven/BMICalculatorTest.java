package HealthyCodeApp.maven;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}

	@Test
	void shouldReturnFalseWhenDietNotRecommended() {
		double weight = 80.0;
		double height = 1.70;

		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		assertTrue(recommended);
	}
	
	
	@ParameterizedTest(name = "weight={0}, height={1}")
	@CsvSource(value = { "89.0, 1.72", "95.0,1.75", "110, 1.78" })
	/**
	 * 
	 * @param weightParametres
	 * @param heightParametres
	 */
	void shouldReturnTruesWhenDietNotRecommended(double weightParametres, double heightParametres) {
		// given
		double weight = weightParametres;
		double height = 1.72;
		// when
		boolean recommended = BMICalculator.isDietRecommended(weight, height);
		// then
		assertTrue(recommended);

	}

	@Test
	void shouldThowArithmeticExceptionWhenHeightZero() {
		// give
		double weight = 50.0;
		double height = 0.0;

		// when
		Executable excutable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(ArithmeticException.class, excutable);

	}

	@Test
	void shouldReturnCodeWithWorstBMIWhenCoderListNoEmpty() {
		// given
		List<Coder> coderList = new ArrayList<Coder>();
		coderList.add(new Coder(1.80, 60.0));
		coderList.add(new Coder(1.82, 98.0));
		coderList.add(new Coder(1.82, 64.7));
		// when
		Coder CoderBmi = BMICalculator.findCoderWithWorstBMI(coderList);
		// then
		assertAll(() -> assertEquals(1.82, CoderBmi.getHeight()), () -> assertEquals(98.0, CoderBmi.getWeight()));

	}

	@Test
	void shouldReturnNull_WithWorstBMIWhenCoderListNoEmpty() {
		// given
		List<Coder> coderList = new ArrayList<Coder>();

		// when
		Coder CoderBmi = BMICalculator.findCoderWithWorstBMI(coderList);
		// then
		assertNull(CoderBmi);
	}

	@Test
	void shouldReturnArrayEquals_WithWorstBMIWhenCoderListNoEmpty() {
		// given
		List<Coder> coderList = new ArrayList<Coder>();
		coderList.add(new Coder(1.80, 60.0));
		coderList.add(new Coder(1.82, 98.0));
		coderList.add(new Coder(1.82, 64.7));

		double[] expected = { 18.52, 29.59, 19.53 };
		// when
		double[] CoderBmi = BMICalculator.getBMIScores(coderList);
		// then
		assertArrayEquals(CoderBmi, expected);
	}

}