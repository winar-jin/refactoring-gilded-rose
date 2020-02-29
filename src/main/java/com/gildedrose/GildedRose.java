package com.gildedrose;

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
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASS)) {
                subtractQuality(item);
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

            subtractSellInData(item);

            if (item.sell_in < MIN_SELLIN) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASS)) {
                        subtractQuality(item);
                    } else {
                        item.quality = MIN_QUALITY;
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        increaseQuality(item);
                    }
                }
            }
        }
    }

    private void subtractSellInData(Item item) {
        if (noSellInDataWithSameQuality(item)) {
            return;
        }
        item.sell_in = item.sell_in - 1;
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }

    private void subtractQuality(Item item) {
        if (noSellInDataWithSameQuality(item) || noQuality(item)) {
            return;
        }

        item.quality = item.quality - 1;
    }

    private boolean noQuality(Item item) {
        return item.quality <= MIN_QUALITY;
    }

    private boolean noSellInDataWithSameQuality(Item item) {
        return item.name.equals(SULFURAS);
    }
}
