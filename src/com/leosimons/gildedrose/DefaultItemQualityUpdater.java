package com.leosimons.gildedrose;

class DefaultItemQualityUpdater implements ItemQualityUpdater {
    public static final int MINIMUM_QUALITY = 0;
    public static final int MAXIMUM_QUALITY = 50;

    @Override
    public boolean match(final Item item) {
        return true;
    }

    @Override
    public void update(final Item item) {
        decreaseSellIn(item);
        decreaseQuality(item);
        if (item.getSellIn() <= 0) {
            decreaseQuality(item);
        }
    }

    protected void decreaseSellIn(final Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    protected void decreaseQuality(final Item item) {
        if (item.getQuality() > MINIMUM_QUALITY) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    protected void increaseQuality(final Item item) {
        if (item.getQuality() < MAXIMUM_QUALITY) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}
