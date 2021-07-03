package utility;

import org.junit.Test;

public class MyStringCellFactoryTest {
    @Test
    public void testDouble() throws Exception {
        System.out.println(doubleNormalGosterim(null));
    }

    public String doubleNormalGosterim(final Double d) {
        if (d == null) return "0";
        final String value = String.valueOf(d);
        final String tamSayi = value.substring(0, value.indexOf("."));
        final String ondalik = value.substring(value.indexOf(".") + 1);
        return ondalik.equals("0")?tamSayi:value;
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme