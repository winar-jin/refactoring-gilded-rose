package com.gildedrose;

public class AgedBrie extends Item {
    public AgedBrie(String name, int sell_in, int quality) {
        super(name, sell_in, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    @Override
    protected void updateSellIn() {
        sell_in = sell_in - 1;
    }
}
