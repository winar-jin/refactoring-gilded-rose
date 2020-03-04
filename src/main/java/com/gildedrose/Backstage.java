package com.gildedrose;

public class Backstage extends Item {
    public Backstage(String name, int sell_in, int quality) {
        super(name, sell_in, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;

            if (sellIn < 11) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }

            if (sellIn < 6) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }

    @Override
    protected void updateAfterExpiration() {
        if (sellIn < 0) {
            quality = 0;
        }
    }
}
