package com.alibaba.json.bvt.serializer;

import java.util.*;

import clojure.lang.Obj;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class ListTest {
    private List list;
    private String expected;
    private Object[] parameters;
    public ListTest(String expected, Object... parameters) {
        super();
        this.expected = expected;
        this.parameters = parameters;
    }
    @Before
    public void configure() {
        this.list = new LinkedList();
        for(Object parameter: this.parameters) {
            this.list.add(parameter);
        }
    }
    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][] {
                {"[23L,45L]", new Object[] {23L, 45L} },
                {"[1L,1000000000000000L]", new Object[] {1L, 1000000000000000L} },
                {"[3.14F,9.8F]", new Object[] {3.14f, 9.8f} },
                {"[]", new Object[] {} },
                {"[null]", new Object[] {null} },
                {"[null,null]", new Object[] {null, null} },
                {"[1,2,3]", new Object[] {1, 2, 3} }
        });
    }
    @Test
    public void testNull() {
        Assert.assertEquals(this.expected, JSON.toJSONString(this.list, SerializerFeature.WriteClassName));
    }

}

/*
public class ListTest extends TestCase {

    public void test_null() throws Exception {
        List list = new LinkedList();
        list.add(23L);
        list.add(45L);

        Assert.assertEquals("[23L,45L]", JSON.toJSONString(list, SerializerFeature.WriteClassName));
    }

    public static class VO {

        private Object value;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

    }
}
*/