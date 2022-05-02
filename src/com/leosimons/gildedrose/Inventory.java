package com.leosimons.gildedrose;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Inventory {
    private static final ItemUpdater[] UPDATERS = new ItemUpdater[] {
            new LegendaryItemUpdater(),
            new TrashItemUpdater(),
            new AgedItemUpdater(),
            new BackstagePassesUpdater(),
            new ConjuredItemUpdater()
    };
    private static final ItemUpdater DEFAULT_UPDATER = new DefaultItemUpdater();
    private final Item[] items;

    public Inventory(final Item... items) {
        this.items = items;
    }

    public void updateItems() {
        for (final Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(final Item item) {
        for (ItemUpdater updater : UPDATERS) {
            if (updater.match(item)) {
                updater.update(item);
                return;
            }
        }
        DEFAULT_UPDATER.update(item);
    }
}
