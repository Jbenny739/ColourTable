package example.org;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColourTableTest {


        @Test
        void testValidPaletteSize() {
            // Test that a ColourTable object is created successfully with a valid size
            ColourTable table = new ColourTable(4);
            assertNotNull(table, "ColourTable should not be null when created with a valid size.");
        }

        @Test
        void testInvalidPaletteSizeThrowsException() {
            // Test invalid palette sizes and ensure exceptions are thrown
            assertThrows(IllegalArgumentException.class, () -> new ColourTable(3),
                    "Palette size of 3 should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> new ColourTable(1),
                    "Palette size of 1 should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> new ColourTable(0),
                    "Palette size of 0 should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> new ColourTable(-4),
                    "Negative palette size should throw IllegalArgumentException.");
        }

        @Test
        void testAddValidRGBColour() {
            // Test adding valid RGB colors
            ColourTable table = new ColourTable(4);
            table.add(255, 0, 0); // Adding Red
            table.add(0, 255, 0); // Adding Green
            table.add(0, 0, 255); // Adding Blue
            assertEquals(3, table.getColourCount(), "There should be three colours in the ColourTable.");
        }

        @Test
        void testAddInvalidRGBColourThrowsException() {
            // Test adding invalid RGB colors and ensure exceptions are thrown
            ColourTable table = new ColourTable(4);
            assertThrows(IllegalArgumentException.class, () -> table.add(256, 0, 0),
                    "Red component greater than 255 should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> table.add(-1, 0, 0),
                    "Negative red component should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> table.add(0, 256, 0),
                    "Green component greater than 255 should throw IllegalArgumentException.");
            assertThrows(IllegalArgumentException.class, () -> table.add(0, 0, -1),
                    "Negative blue component should throw IllegalArgumentException.");
        }

        @Test
        void testAddBeyondCapacityThrowsException() {
            // Test adding colors beyond the ColourTable's capacity
            ColourTable table = new ColourTable(2);
            table.add(255, 0, 0); // Red
            table.add(0, 255, 0); // Green
            assertThrows(IllegalStateException.class, () -> table.add(0, 0, 255),
                    "Adding beyond the capacity should throw IllegalStateException.");
        }

        @Test
        void testGetColour() {
            // Test getting colors from the ColourTable
            ColourTable table = new ColourTable(4);
            table.add(255, 0, 0); // Red
            table.add(0, 255, 0); // Green
            int redColour = table.getColour(0);
            int greenColour = table.getColour(1);

            assertEquals(0xFF0000, redColour, "The colour at index 0 should be red (0xFF0000).");
            assertEquals(0x00FF00, greenColour, "The colour at index 1 should be green (0x00FF00).");
        }

        @Test
        void testGetColourOutOfBoundsThrowsException() {
            // Test accessing a color at an invalid index
            ColourTable table = new ColourTable(4);
            table.add(255, 0, 0); // Adding one color
            assertThrows(IndexOutOfBoundsException.class, () -> table.getColour(1),
                    "Accessing an index out of bounds should throw IndexOutOfBoundsException.");
            assertThrows(IndexOutOfBoundsException.class, () -> table.getColour(-1),
                    "Accessing a negative index should throw IndexOutOfBoundsException.");
        }
    }

