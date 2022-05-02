package com.leosimons.gildedrose;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Inventory {
    private static final ItemQualityUpdater[] UPDATERS = new ItemQualityUpdater[] {
            new LegendaryItemQualityUpdater(),
            new TrashItemQualityUpdater(),
            new AgedItemQualityUpdater(),
            new BackstagePassesQualityUpdater()
    };
    private static final ItemQualityUpdater DEFAULT_UPDATER = new DefaultItemQualityUpdater();
    private final Item[] items;

    public Inventory(final Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQuality(final Item item) {
        for (ItemQualityUpdater updater : UPDATERS) {
            if (updater.match(item)) {
                updater.update(item);
                return;
            }
        }
        DEFAULT_UPDATER.update(item);
    }
}
