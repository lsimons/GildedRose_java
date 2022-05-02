package com.leosimons.gildedrose;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Inventory {
    private static final ItemUpdater[] UPDATERS = new ItemUpdater[] {
            new LegendaryItemUpdater(),
            new TrashItemUpdater(),
            new AgedItemUpdater(),
            new BackstagePassesUpdater()
    };
    private static final ItemUpdater DEFAULT_UPDATER = new DefaultItemUpdater();
    private final Item[] items;

    public Inventory(final Item... items) {
        this.items = items;
    }

    /**
     * Update item quality and sellIn properties.
     *
     * @deprecated use {@link #updateItems()} instead
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    public void updateQuality() {
        updateItems();
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
