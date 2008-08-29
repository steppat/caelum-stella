package br.com.caelum.stella.inwords;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Victor dos Santos Pereira
 * @author Leonardo Bessa
 * 
 */
public class IntegerNumericToWordsConverterEN_US_Test {

    private final NumericToWordsConverter converter = new NumericToWordsConverter(
            new FormatoDeInteiro());
    @Before
    public void setLocale(){
        Locale.setDefault(new Locale("EN","US"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTransformNegativeLong() {
        long negative = -1;
        converter.toWords(negative);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotTransformNegativeDouble() {
        double negative = -1;
        converter.toWords(negative);
    }

    @Test
    public void shouldTransformMaxLong() {
        long max = Long.MAX_VALUE;
        String actual = converter.toWords(max);
        assertEquals(
                "nove quintilhões, duzentos e vinte e três quatrilhões,"
                        + " trezentos e setenta e dois trilhões, trinta e seis bilhões,"
                        + " oitocentos e cinquenta e quatro milhões, setecentos e setenta"
                        + " e cinco mil e oitocentos e sete inteiros", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotTransformAMissingResource() {
        double max = Double.MAX_VALUE;
        converter.toWords(max);
    }

    @Test
    public void shouldTransform0InWords() {
        double zero = 0;
        String actual = converter.toWords(zero);
        assertEquals("zero", actual);
    }

    @Test
    public void shouldTransform1InWords() {
        double one = 1;
        String actual = converter.toWords(one);
        assertEquals("um inteiro", actual);
    }

    @Test
    public void shouldTransform2InWords() {
        double two = 2;
        String actual = converter.toWords(two);
        assertEquals("dois inteiros", actual);
    }

    @Test
    public void shouldTransform14InWords() {
        double fourteen = 14;
        String actual = converter.toWords(fourteen);
        assertEquals("quatorze inteiros", actual);
    }

    @Test
    public void shouldTransform53InWordsUsingAnd() {
        double fiftyThree = 53;
        String actual = converter.toWords(fiftyThree);
        assertEquals("cinquenta e três inteiros", actual);
    }

    @Test
    public void shouldTransformOneHundredInWords() {
        double oneHundred = 100;
        String actual = converter.toWords(oneHundred);
        assertEquals("cem inteiros", actual);
    }

    @Test
    public void shouldTransformOneHundredInWordsUsingAnd() {
        double oneHundredAndNine = 109;
        String actual = converter.toWords(oneHundredAndNine);
        assertEquals("cento e nove inteiros", actual);
    }

    @Test
    public void shouldTransformTwoHundredInWords() {
        double twoHundred = 200;
        String actual = converter.toWords(twoHundred);
        assertEquals("duzentos inteiros", actual);
    }

    @Test
    public void shouldTransformThousandInWords() {
        double thousand = 1000;
        String actual = converter.toWords(thousand);
        assertEquals("um mil inteiros", actual);
    }

    @Test
    public void shouldTransformThousandInWordsUsingAnd() {
        double thousand = 1031;
        String actual = converter.toWords(thousand);
        assertEquals("um mil e trinta e um inteiros", actual);
    }

    @Test
    public void shouldTransformAMillionIntoNumberInWordsUsingSingular() {
        double oneMillion = 1000000;
        String actual = converter.toWords(oneMillion);
        assertEquals("um milhão de inteiros", actual);
    }

    @Test
    public void shouldTransformAMillionIntoNumberInWordsUsingAnd() {
        double twoHundred = 1000150.99;
        String actual = converter.toWords(twoHundred);
        assertEquals(
                "um milhão e cento e cinquenta inteiros e novecentos e noventa milésimos",
                actual);
    }

    @Test
    public void shouldTransformAMillionAndThousandIntoNumberInWordsUsingAnd() {
        double twoHundred = 1023850;
        String actual = converter.toWords(twoHundred);
        assertEquals(
                "um milhão, vinte e três mil e oitocentos e cinquenta inteiros",
                actual);
    }

    @Test
    public void shouldTransformTwoMillionUsingPlural() {
        double twoHundred = 2e6;
        String actual = converter.toWords(twoHundred);
        assertEquals("dois milhões de inteiros", actual);
    }

    @Test
    public void shouldTransformANumberInWordsUsingFraction() {
        double twoHundred = 0.222;
        String actual = converter.toWords(twoHundred);
        assertEquals("duzentos e vinte e dois milésimos", actual);
    }

}
