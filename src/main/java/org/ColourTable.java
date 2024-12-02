package org.example;


import java.util.ArrayList;
import java.util.List;

public class ColourTable {
    private final List<Integer> palette; // Stores the 24-bit RGB colours
    private final int capacity;         // Maximum number of colours the table can hold

    /**
     * Constructs a ColourTable with the specified number of colours.
     *
     * @param size The number of colours in the palette (must be a power of 2 and greater than 1).
     * @throws IllegalArgumentException if the size is not a power of 2 or <= 1.
     */
    public ColourTable(int size) {
        if (size <= 1 || (size & (size - 1)) != 0) { // Check if size is a power of 2 and > 1
            throw new IllegalArgumentException("Palette size must be a power of 2 and greater than 1.");
        }
        this.capacity = size;
        this.palette = new ArrayList<>(size);
    }

    /**
     * Adds a 24-bit RGB colour to the palette.
     *
     * @param red   The red component (0-255).
     * @param green The green component (0-255).
     * @param blue  The blue component (0-255).
     * @throws IllegalArgumentException if RGB values are out of range.
     * @throws IllegalStateException if the ColourTable has reached its capacity.
     */
    public void add(int red, int green, int blue) {
        if (palette.size() >= capacity) {
            throw new IllegalStateException("ColourTable is at full capacity.");
        }
        if (!isValidColour(red, green, blue)) {
            throw new IllegalArgumentException("Invalid RGB values. Each component must be in the range 0-255.");
        }
        int rgb = (red << 16) | (green << 8) | blue; // Combine RGB components into a single integer
        palette.add(rgb);
    }

    /**
     * Gets the colour at the specified index in the palette.
     *
     * @param index The index of the colour.
     * @return The 24-bit RGB colour at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public int getColour(int index) {
        if (index < 0 || index >= palette.size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return palette.get(index);
    }

    /**
     * Gets the current number of colours in the palette.
     *
     * @return The number of colours in the palette.
     */
    public int getColourCount() {
        return palette.size();
    }

    /**
     * Validates the RGB colour components.
     *
     * @param red   The red component.
     * @param green The green component.
     * @param blue  The blue component.
     * @return True if all components are in the range 0-255, otherwise false.
     */
    private boolean isValidColour(int red, int green, int blue) {
        return (red >= 0 && red <= 255) && (green >= 0 && green <= 255) && (blue >= 0 && blue <= 255);
    }
}
