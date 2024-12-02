package org;



import java.util.ArrayList;
import java.util.List;

/**
 */
public class ColourTable {
    private final List<Integer> palette; // Stores the 24-bit RGB colors
    private final int capacity;         // Maximum number of colors the table can hold


    public ColourTable(int size) {
        if (size <= 1 || (size & (size - 1)) != 0) { // Check if size is a power of 2 and > 1
            throw new IllegalArgumentException("Palette size must be a power of 2 and greater than 1.");
        }
        this.capacity = size;
        this.palette = new ArrayList<>(size);
    }

    /**
     * Adds a 24-bit RGB color to the palette
     * @throws IllegalArgumentException if RGB values are out of range.
     * @throws IllegalStateException    if the ColourTable has reached its capacity.
     */
    public void add(int red, int green, int blue) {
        if (palette.size() >= capacity) {
            throw new IllegalStateException("ColourTable is at full capacity.");
        }
        if (!isValidRGB(red, green, blue)) {
            throw new IllegalArgumentException("Invalid RGB values. Each component must be in the range 0-255.");
        }
        int rgb = (red << 16) | (green << 8) | blue; // Combine RGB components into a single integer
        palette.add(rgb);
    }

    /**
     * Gets the color at the specified index in the palette.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public int getColour(int index) {
        if (index < 0 || index >= palette.size()) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        return palette.get(index);
    }

    /**
     * Gets the current number of colors in the palette.
     * @return The number of colors in the palette.
     */
    public int getColourCount() {
        return palette.size();
    }

    /**
     * Validates the RGB color components
     * @return True if all components are in the range 0-255, otherwise false.
     */
    private boolean isValidRGB(int red, int green, int blue) {
        return (red >= 0 && red <= 255) && (green >= 0 && green <= 255) && (blue >= 0 && blue <= 255);
    }
}
