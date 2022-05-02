package com.leosimons.gildedrose;

class TrashItemQualityUpdater extends DefaultItemQualityUpdater {
    @Override
    public boolean match(final Item item) {
        return item.getQuality() <= MINIMUM_QUALITY;
    }

    @Override
    public void update(final Item item) {
        decreaseSellIn(item);
    }
}
