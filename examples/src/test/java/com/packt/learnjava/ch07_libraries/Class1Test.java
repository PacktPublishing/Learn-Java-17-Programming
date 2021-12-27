package com.packt.learnjava.ch07_libraries;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class Class1Test {

    @Test
    public void multiplyByTwo() {
        Class1 class1 = new Class1();
        int result = class1.multiplyByTwo(2);
        Assert.assertEquals(4, result);
    }

    @Test
    public void multiplyByTwo2() {
        Class2 class2Mock = Mockito.mock(Class2.class);
        Mockito.when(class2Mock.getValue()).thenReturn(5);

        Class1 class1 = new Class1();
        int result = class1.multiplyByTwo2(class2Mock);
        Assert.assertEquals(10, result);
    }
}