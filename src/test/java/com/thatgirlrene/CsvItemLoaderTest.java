package com.thatgirlrene;

import com.thatgirlrene.utils.CsvItemLoader;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CsvItemLoaderTest {

    @Test
        void shouldLoadAllItemsFromFile() throws IOException {
            CsvItemLoader loader = new CsvItemLoader();
            assertEquals(6, loader.loadItems("src/main/resources/items.csv").size());
        }

        @Test
        void shouldThrowWhenFileDoesNotExist() {
            CsvItemLoader loader = new CsvItemLoader();
            assertThrows(IOException.class, () -> loader.loadItems("missing.csv"));
        }
    }

