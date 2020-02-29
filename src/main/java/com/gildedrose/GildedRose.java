package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final int MAX_QUALITY = 50;
    private final int MIN_QUALITY = 0;
    private final int MIN_SELLIN = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(this.items).forEach(this::updateQualitySingle);
    }

    private void updateQualitySingle(Item item) {
        if (specialItem(item)) {
            increaseQuality(item);
        }

        subtractQuality(item);
        subtractSellIn(item);

        if (greaterThanMinSellIn(item)) {
            return;
        }

        if (increaseQualityAfterSellIn(item)) {
            increaseQuality(item);
        }

        if (qualityBeZeroAfterSellIn(item)) {
            item.quality = MIN_QUALITY;
        }

        subtractQuality(item);
    }

    private boolean specialItem(Item item) {
        return increaseQualityAfterSellIn(item) || qualityBeZeroAfterSellIn(item);
    }

    private boolean qualityBeZeroAfterSellIn(Item item) {
        return item.name.equals(BACKSTAGE_PASS);
    }

    private boolean increaseQualityAfterSellIn(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean greaterThanMinSellIn(Item item) {
        return item.sell_in >= MIN_SELLIN;
    }

    private boolean greaterThanMaxQuality(Item item) {
        return item.quality >= MAX_QUALITY;
    }

    private void subtractSellIn(Item item) {
        if (noSellInWithSameQuality(item)) {
            return;
        }
        item.sell_in = item.sell_in - 1;
    }

    private void increaseQuality(Item item) {
        if (greaterThanMaxQuality(item)) {
            return;
        }
        item.quality = item.quality + 1;
    }

    private void subtractQuality(Item item) {
        if (noSellInWithSameQuality(item) || noQuality(item)) {
            return;
        }

        item.quality = item.quality - 1;
    }

    private boolean noQuality(Item item) {
        return item.quality <= MIN_QUALITY;
    }

    private boolean noSellInWithSameQuality(Item item) {
        return item.name.equals(SULFURAS);
    }
}
