package com.utils;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class TrainSectionTest {

    @Test
    public void testSecAInitialize() {
        ArrayList<String> expectedSecA = new ArrayList<>(Arrays.asList("A1", "A2", "A3"));
        assertEquals(expectedSecA, TrainSection.secA);
    }

    @Test
    public void testSecBInitialize() {
        ArrayList<String> expectedSecB = new ArrayList<>(Arrays.asList("B1", "B2", "B3"));
        assertEquals(expectedSecB, TrainSection.secB);
    }

    @Test
    public void testSecAContentModification() {
        TrainSection.secA.add("A4");
        ArrayList<String> expectedSecA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4"));
        assertEquals(expectedSecA, TrainSection.secA);
    }

    @Test
    public void testSecBContentModification() {
        TrainSection.secB.remove("B3");
        ArrayList<String> expectedSecB = new ArrayList<>(Arrays.asList("B1", "B2"));
        assertEquals(expectedSecB, TrainSection.secB);
    }
}
