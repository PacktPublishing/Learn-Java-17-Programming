package com.packt.learnjava.ch07_libraries;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SomeClassTest {

    @Test
    public void multiplyByTwo() {
        SomeClass someClass = new SomeClass();
        int result = someClass.multiplyByTwo(2);
        Assert.assertEquals(4, result);
    }

    @Test
    public void multiplyByTwoTheValueFromSomeOtherClass() {
        SomeOtherClass mo = Mockito.mock(SomeOtherClass.class);
        Mockito.when(mo.getValue()).thenReturn(5);

        SomeClass someClass = new SomeClass();
        int result = someClass.multiplyByTwoTheValueFromSomeOtherClass(mo);
        Assert.assertEquals(10, result);
    }
}