package com.gildedrose.testing;

import static org.junit.Assert.*;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void correctNaming() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void lowersValues() {
        Item[] items = new Item[] { new Item("foo", 5, 4) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void valueLowersTwiceAfterSellIn() {
        Item[] items = new Item[] { new Item("foo", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void qualityIsNotNegative() {
        Item[] items = new Item[] { new Item("foo", 3, 0) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void agingItemsIncreasesQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(7, app.items[0].quality);
    }

    @Test
    public void qualityIsNotOver50() {
        Item[] items = new Item[] { new Item("foo1", 3, 51),
                new Item("Aged Brie", 3, 49) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    public void legendaryItemsDontChange() {
        Item[] items = new Item[] { new Item("Sulfuras", 42, 80) };
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(80, app.items[0].quality);
        assertEquals(42, app.items[0].sellIn);
    }

    @Test
    public void phasingItemsChange() {
        Item[] items = new Item[] { new Item("Backstage pass", 11, 10),
                new Item("Backstage pass", 6, 10),
                new Item("Backstage pass", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(12, app.items[0].quality);
        assertEquals(13, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
    }

    @Test
    public void conjuringItemsDegradeFaster() {
        Item[] items = new Item[] { new Item("Eggs", 10, 6),
                new Item("Eggs", 0, 6)};
        GildedRose app = new GildedRose(items);
        app.nextDay();
        assertEquals(4, app.items[0].quality);
        assertEquals(2, app.items[1].quality);
    }
}
