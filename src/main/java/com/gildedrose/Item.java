package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    void updateQualityAndSellIn() {
        updateQuality();

        updateSellIn();

        updateAfterExpiration();
    }

    protected void updateAfterExpiration() {
        if (sellIn < 0 && quality > 0) {
            quality = quality - 1;
        }
    }

    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

    protected void updateQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }
}
