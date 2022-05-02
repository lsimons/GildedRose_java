package com.leosimons.gildedrose;

class LegendaryItemQualityUpdater extends DefaultItemQualityUpdater {
    @Override
    public boolean match(final Item item) {
        return item.getQuality() >= MAXIMUM_QUALITY;
    }

    @Override
    public void update(final Item item) {
        // do nothing
    }
}
