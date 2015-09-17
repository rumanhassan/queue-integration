package org.lottery.common.message;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class SimpleTest {

    @Test
    public void testJson() {
        System.out.println(JSON.toJSONString("AAAAAAAAA"));
        System.out.println(JSON.toJSONString('1'));
        System.out.println(JSON.toJSONString(1));
        System.out.println(JSON.toJSONString(1.5F));

        System.out.println(JSON.parseObject(JSON.toJSONString("AAAAAAAAA"), String.class));
        System.out.println(JSON.parseObject(JSON.toJSONString('1'), Character.class));
        System.out.println(JSON.parseObject(JSON.toJSONString(1), Integer.class));
        System.out.println(JSON.parseObject(JSON.toJSONString(1.5F), Float.class));
    }
}
