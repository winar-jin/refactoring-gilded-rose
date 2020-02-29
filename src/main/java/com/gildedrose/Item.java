package com.gildedrose;

public class Item {

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final int MAX_QUALITY = 50;
    private final int MIN_QUALITY = 0;
    private final int MIN_SELLIN = 0;

    private String name;

    private int sell_in;

    private int quality;

    public Item(String name, int sell_in, int quality) {
        this.name = name;
        this.sell_in = sell_in;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sell_in + ", " + this.quality;
    }

    void updateQuality() {
        if (specialItem()) {
            increaseQuality();
        }

        subtractQuality();
        subtractSellIn();

        if (greaterThanMinSellIn()) {
            return;
        }

        if (increaseQualityAfterSellIn()) {
            increaseQuality();
        }

        if (qualityBeZeroAfterSellIn()) {
            quality = MIN_QUALITY;
        }

        subtractQuality();
    }

    private boolean specialItem() {
        return increaseQualityAfterSellIn() || qualityBeZeroAfterSellIn();
    }

    private boolean qualityBeZeroAfterSellIn() {
        return name.equals(BACKSTAGE_PASS);
    }

    private boolean increaseQualityAfterSellIn() {
        return name.equals(AGED_BRIE);
    }

    private boolean greaterThanMinSellIn() {
        return sell_in >= MIN_SELLIN;
    }

    private void subtractSellIn() {
        if (noSellInWithSameQuality()) {
            return;
        }
        sell_in = sell_in - 1;
    }

    private void increaseQuality() {
        if (greaterThanMaxQuality()) {
            return;
        }
        quality = quality + 1;
    }

    private boolean greaterThanMaxQuality() {
        return quality >= MAX_QUALITY;
    }

    private void subtractQuality() {
        if (noSellInWithSameQuality() || noQuality()) {
            return;
        }

        quality = quality - 1;
    }

    private boolean noQuality() {
        return quality <= MIN_QUALITY;
    }

    private boolean noSellInWithSameQuality() {
        return name.equals(SULFURAS);
    }
}
