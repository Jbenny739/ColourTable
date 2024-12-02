package org.example;

@Test
void testAddValidRGB() {
    ColourTable table = new ColourTable(4);
    table.add(255, 0, 0);
    assertEquals(1, table.getColourCount());
}

@Test
void testAddInvalidRGB() {
    ColourTable table = new ColourTable(4);
    assertThrows(IllegalArgumentException.class, () -> table.add(256, 0, 0));
}

@Test
void testAddBeyondCapacity() {
    ColourTable table = new ColourTable(2);
    table.add(255, 0, 0);
    table.add(0, 255, 0);
    assertThrows(IllegalStateException.class, () -> table.add(0, 0, 255));
}

public void main() {
}
