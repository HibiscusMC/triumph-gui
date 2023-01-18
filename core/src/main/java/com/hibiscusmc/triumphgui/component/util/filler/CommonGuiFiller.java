/**
 * MIT License
 * <p>
 * Copyright (c) 2021 TriumphTeam
 * Copyright (c) 2022 HibiscusMC
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.hibiscusmc.triumphgui.component.util.filler;

import com.hibiscusmc.triumphgui.component.GuiType;
import com.hibiscusmc.triumphgui.component.exception.GuiException;
import com.hibiscusmc.triumphgui.gui.BaseGui;
import com.hibiscusmc.triumphgui.gui.GuiItem;
import com.hibiscusmc.triumphgui.gui.PaginatedGui;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommonGuiFiller implements GuiFiller {

    protected final BaseGui gui;

    public CommonGuiFiller(final BaseGui gui) {
        this.gui = gui;
    }

    protected abstract void putItem(final int slot, final GuiItem item);

    protected void putItem(final int slot, final GuiItem item, final boolean overwriteExistingItems) {
        if (!overwriteExistingItems && gui.getGuiItems().containsKey(slot)) {
            return;
        }
        putItem(slot, item);
    }

    @Override
    public void fillTop(final @NotNull GuiItem guiItem) {
        fillTop(Collections.singletonList(guiItem));
    }

    @Override
    public void fillTop(final @NotNull List<GuiItem> guiItems) {
        final List<GuiItem> items = repeatList(guiItems);
        for (int i = 0; i < 9; i++) {
            putItem(i, items.get(i), false);
        }
    }

    @Override
    public void fillBottom(final @NotNull GuiItem guiItem) {
        fillBottom(Collections.singletonList(guiItem));
    }

    @Override
    public void fillBottom(final @NotNull List<GuiItem> guiItems) {
        final int rows = gui.getRows();
        final List<GuiItem> items = repeatList(guiItems);
        for (int i = 9; i > 0; i--) {
            putItem((rows * 9) - i, items.get(i), false);
        }
    }

    @Override
    public void fillBorder(final @NotNull GuiItem guiItem) {
        fillBorder(Collections.singletonList(guiItem));
    }

    @Override
    public void fillBorder(final @NotNull List<GuiItem> guiItems) {
        final int rows = gui.getRows();
        if (rows <= 2) return;

        final List<GuiItem> items = repeatList(guiItems);

        for (int i = 0; i < rows * 9; i++) {
            if ((i <= 8)
                    || (i >= (rows * 9) - 8) && (i <= (rows * 9) - 2)
                    || i % 9 == 0
                    || i % 9 == 8) {
                putItem(i, items.get(i), true);
            }
        }
    }

    @Override
    public void fillBetweenPoints(final int rowFrom, final int colFrom, final int rowTo, final int colTo, final @NotNull GuiItem guiItem) {
        fillBetweenPoints(rowFrom, colFrom, rowTo, colTo, Collections.singletonList(guiItem));
    }

    @Override
    public void fillBetweenPoints(final int rowFrom, final int colFrom, final int rowTo, final int colTo, final @NotNull List<GuiItem> guiItems) {
        final int minRow = Math.min(rowFrom, rowTo);
        final int maxRow = Math.max(rowFrom, rowTo);
        final int minCol = Math.min(colFrom, colTo);
        final int maxCol = Math.max(colFrom, colTo);

        final int rows = gui.getRows();
        final List<GuiItem> items = repeatList(guiItems);

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= 9; col++) {
                final int slot = getSlotFromRowCol(row, col);
                if (!((row >= minRow && row <= maxRow) && (col >= minCol && col <= maxCol))) continue;

                putItem(slot, items.get(slot), true);
            }
        }
    }

    @Override
    public void fill(final @NotNull GuiItem guiItem) {
        fill(Collections.singletonList(guiItem));
    }

    @Override
    public void fill(final @NotNull List<GuiItem> guiItems) {
        if (gui instanceof PaginatedGui) {
            throw new GuiException("Paginated GUIs do not support full filling.");
        }

        final GuiType type = gui.guiType();

        final int fill;
        if (type == GuiType.CHEST) {
            fill = gui.getRows() * type.getLimit();
        } else {
            fill = type.getLimit();
        }

        final List<GuiItem> items = repeatList(guiItems);
        for (int i = 0; i < fill; i++) {
            putItem(i, items.get(i), false);
        }
    }

    /**
     * Repeats a list of items. This method allows for alternating items.
     * This stores a reference to existing objects, so it does not create new objects.
     *
     * @param guiItems Items to repeat.
     * @return A new list.
     */
    protected List<GuiItem> repeatList(final @NotNull List<GuiItem> guiItems) {
        final List<GuiItem> repeated = new ArrayList<>();
        Collections.nCopies(gui.getRows() * 9, guiItems).forEach(repeated::addAll);
        return repeated;
    }

    /**
     * Translates a row and a column to a slot.
     *
     * @param row The row.
     * @param col The column.
     * @return The corresponding slot.
     */
    protected static int getSlotFromRowCol(final int row, final int col) {
        return (col + (row - 1) * 9) - 1;
    }

}
