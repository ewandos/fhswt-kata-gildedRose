package com.gildedrose;

import java.util.Arrays;
import java.util.List;

public class GildedRose {
    public Item[] items;
    private List<String> agingItems = Arrays.asList("Aged Brie");
    private List<String> legendaryItems = Arrays.asList("Sulfuras");
    private List<String> phasingItems = Arrays.asList("Backstage pass");
    private List<String> conjuringItems = Arrays.asList("Eggs");

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void normalizeValues(Item item) {
        if (item.quality < 0)
            item.quality = 0;
        if (item.quality > 50)
            item.quality = 50;
    }

    private void updateQuality(Item item) {
        int qualityChange = -1;

        if (item.sellIn <= 0)
            qualityChange = -2;

        if (phasingItems.contains(item.name)) {
            if (item.sellIn < 0)
                qualityChange = -item.quality;
            else if (item.sellIn <= 5)
                qualityChange = 3;
            else if (item.sellIn <= 10)
                qualityChange = 2;
        }

        if (conjuringItems.contains(item.name))
            qualityChange *= 2;

        if (agingItems.contains(item.name))
            qualityChange = 1;

        item.quality += qualityChange;
    }

    public void nextDay() {
        for (Item item : items) {
            if (legendaryItems.contains(item.name))
                continue;
            item.sellIn--;
            updateQuality(item);
            normalizeValues(item);
        }
    }
}
