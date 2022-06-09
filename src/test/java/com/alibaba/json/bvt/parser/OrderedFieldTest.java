package com.alibaba.json.bvt.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderedFieldTest {
    private String json;
    private Model model;
    private int expected;

    public OrderedFieldTest(String json, int expected) {
        this.json = json;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection getParameters() {
        return Arrays.asList(new Object[][] {
                {"{\"id\":1001}", 1001},
                {"{\"id\":1}", 1}
        });
    }

    @Before
    public void configure() {
        this.model = JSON.parseObject(this.json, Model.class, Feature.OrderedField);
    }

    @Test
    public void testOrderedFieldParseObject() {
        Assert.assertEquals(this.expected, this.model.getId());
    }

    @Test
    public void testOrderedFieldToJSONString() {
        Assert.assertEquals(this.json, JSON.toJSONString(this.model));
    }

    public static interface Model {
        public int getId();
        public void setId(int value);
    }
}

/*
public class OrderedFieldTest extends TestCase {
    public void test_ordered_field() throws Exception {
        String text = "{\"id\":1001}";
        Model model = JSON.parseObject(text, Model.class, Feature.OrderedField);
        Assert.assertEquals(1001, model.getId());
        String text2 = JSON.toJSONString(model);
        Assert.assertEquals(text, text2);
        
    }
    
    public static interface Model {
        public int getId();
        public void setId(int value);
    }


}
*/