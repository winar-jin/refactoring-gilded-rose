package com.gildedrose;

class GildedRose {
    Item[] items;

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final int MAX_QUALITY = 50;
    private final int MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASS)) {
                if (item.quality > MIN_QUALITY) {
                    if (!item.name.equals(SULFURAS)) {
                        subtractQuality(item);
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    increaseQuality(item);

                    if (item.name.equals(BACKSTAGE_PASS)) {
                        if (item.sell_in < 11) {
                            if (item.quality < MAX_QUALITY) {
                                increaseQuality(item);
                            }
                        }

                        if (item.sell_in < 6) {
                            if (item.quality < MAX_QUALITY) {
                                increaseQuality(item);
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sell_in = item.sell_in - 1;
            }

            if (item.sell_in < MIN_QUALITY) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASS)) {
                        if (item.quality > MIN_QUALITY) {
                            if (!item.name.equals(SULFURAS)) {
                                subtractQuality(item);
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        increaseQuality(item);
                    }
                }
            }
        }
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }

    private void subtractQuality(Item item) {
        item.quality = item.quality - 1;
    }
}
