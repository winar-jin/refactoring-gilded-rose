package com.gildedrose;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);
        app.update_quality();
        assertEquals("foo", app.items[0].name);
        assertThat(app.items[0].quality, is(4));
        assertThat(app.items[0].sell_in, is(0));
    }

    @Test
    public void test_gildedRose() throws FileNotFoundException {
        final File file = new File("src/test/java/com/gildedrose/results.txt");
        final Scanner scanner = new Scanner(file);
        final StringBuilder results = new StringBuilder();
        while (scanner.hasNextLine()) {
            final String data = scanner.nextLine();
            results.append(data);
            results.append("\n");
        }

        final StringBuilder expected = new StringBuilder();
        expected.append("OMGHAI!\n");

        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
            new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 3;

        for (int i = 0; i < days; i++) {
            expected.append("-------- day ")
                    .append(i)
                    .append(" --------\n")
                    .append("name, sellIn, quality\n");
            for (Item item : items) {
                expected.append(item).append("\n");
            }
            expected.append("\n");
            app.update_quality();
        }

        assertEquals(results.toString(), expected.toString());
    }

}
