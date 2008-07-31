package br.com.caelum.stella.format;

import static br.com.caelum.stella.constraint.CPFConstraints.CPF_FORMATED;
import static br.com.caelum.stella.constraint.CPFConstraints.CPF_UNFORMATED;
import br.com.caelum.stella.format.Formatter;

import java.util.regex.Matcher;

/**
 * @author Leonardo Bessa
 * 
 */
public class CPFFormatter implements Formatter {

    public String format(String value) {
        String result = null;
        if (value != null) {
            Matcher matcher = CPF_UNFORMATED.matcher(value);
            if (matcher.matches()) {
                result = matcher.replaceAll("$1.$2.$3-$4");
            }
        } else {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public String unformat(String value) {
        String result = null;
        if (value != null) {
            Matcher matcher = CPF_FORMATED.matcher(value);
            if (matcher.matches()) {
                result = matcher.replaceAll("$1$2$3$4");
            }
        } else {
            throw new IllegalArgumentException();
        }
        return result;
    }

}