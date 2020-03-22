package play.util;

import org.junit.Test;

/**
 * @author LiJie
 */
public class ClassUtilTest {

    @Test
    public void isArray() {
        byte[] bs = new byte[20];
        System.out.println(bs.getClass().isArray());
    }

    @Test
    public void isInt() {
        Object x = 1;
        if (Integer.class.equals(x.getClass())) {
        }
    }
}
