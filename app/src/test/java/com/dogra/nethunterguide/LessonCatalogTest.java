package com.dogra.nethunterguide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LessonCatalogTest {

    @Test
    public void titlesAndAssetsStayAligned() {
        assertEquals(LessonCatalog.TITLES.length, LessonCatalog.ASSETS.length);
        assertTrue(LessonCatalog.TITLES.length > 0);
    }

    @Test
    public void assetNamesAreUniqueAndResolvable() {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < LessonCatalog.ASSETS.length; i++) {
            String asset = LessonCatalog.ASSETS[i];
            assertTrue(seen.add(asset));
            assertEquals(i, LessonCatalog.indexOfAsset(asset));
        }
    }

    @Test
    public void unknownAssetsReturnMinusOne() {
        assertEquals(-1, LessonCatalog.indexOfAsset(null));
        assertEquals(-1, LessonCatalog.indexOfAsset("missing.txt"));
    }
}
