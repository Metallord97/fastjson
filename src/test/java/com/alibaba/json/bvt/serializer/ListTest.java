package com.alibaba.json.bvt.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@RunWith(Parameterized.class)
public class ListTest {
    private List list;
    private String expected;
    private Object[] parameters;
    private SerializerFeature  serializerFeature;
    public ListTest(String expected, Object[] parameters, SerializerFeature serializerFeature) {
        super();
        this.expected = expected;
        this.parameters = parameters;
        this.serializerFeature = serializerFeature;
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
                {"[23L,45L]", new Object[] {23L, 45L}, SerializerFeature.WriteClassName },
                {"['Hello world.']", new Object[] {"Hello world."} , SerializerFeature.UseSingleQuotes},
                {"[54,101]", new Object[]{54, 101}, SerializerFeature.QuoteFieldNames},
                {"[23,45]", new Object[] {23L, 45L}, SerializerFeature.QuoteFieldNames},
                {"[5,10]", new Integer[]{5, 10}, SerializerFeature.QuoteFieldNames},
                {"[1L,1000000000000000L]", new Object[] {1L, 1000000000000000L}, SerializerFeature.WriteClassName },
                {"[3.14F,9.8F]", new Object[] {3.14f, 9.8f}, SerializerFeature.WriteClassName },
                {"[]", new Object[] {} , SerializerFeature.WriteClassName},
                {"[null]", new Object[] {null}, SerializerFeature.WriteClassName },
                {"[null,null]", new Object[] {null, null} ,SerializerFeature.WriteClassName},
                {"[1,2,3]", new Object[] {1, 2, 3} ,SerializerFeature.WriteClassName}
        });
    }
    @Test
    public void testNull() {
        Assert.assertEquals(this.expected, JSON.toJSONString(this.list, this.serializerFeature));
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