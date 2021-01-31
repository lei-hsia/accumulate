package com.learntest;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author lei
 * @date 01/31/2021 4:32 PM
 */
public class DemoTest {
//    public static void main(String[] args) {
//        MockTest();
//    }

    @Test
    public void MockTest() {
        ValueGenerator vgMock = Mockito.mock(ValueGenerator.class);
        when(vgMock.getValue()).thenReturn(7); // mock: if generate, then return 7

        Incrementor incrementor = new Incrementor();
        assertEquals(incrementor.increment(vgMock), 8);
    }
}
