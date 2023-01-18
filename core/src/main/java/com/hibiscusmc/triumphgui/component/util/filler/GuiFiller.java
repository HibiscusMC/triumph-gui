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

import com.hibiscusmc.triumphgui.gui.Gui;
import com.hibiscusmc.triumphgui.gui.GuiItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO: Verify correctness of Javadocs.
// TODO: Make some sort of sense of which methods should replace non-empty slots.
// (Like, #fillTop doesn't replace non-empty slots, but #fillBottom does? Weird.)
public interface GuiFiller {

    /**
     * Creates a {@code GuiFiller} that will call {@link Gui#setItem} when
     * putting an item in a slot.
     *
     * @param gui the {@code Gui} to modify
     * @return the new {@code GuiFiller}
     */
    static GuiFiller setting(final Gui gui) {
        return new SettingGuiFiller(gui);
    }

    /**
     * Creates a {@code GuiFiller} that will call {@link Gui#updateItem} when
     * putting an item in a slot.
     *
     * @param gui the {@code Gui} to modify
     * @return the new {@code GuiFiller}
     */
    static GuiFiller updating(final Gui gui) {
        return new UpdatingGuiFiller(gui);
    }

    /**
     * Fills the top portion of the GUI with an item. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItem The item.
     */
    void fillTop(@NotNull GuiItem guiItem);

    /**
     * Fills the top portion of the GUI with alternating items. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItems The items to alternate between.
     */
    void fillTop(@NotNull List<GuiItem> guiItems);

    /**
     * Fills the bottom portion of the GUI with an item. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItem The item.
     */
    void fillBottom(@NotNull GuiItem guiItem);

    /**
     * Fills the bottom portion of the GUI with alternating items. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItems The items to alternate between.
     */
    void fillBottom(@NotNull List<GuiItem> guiItems);

    /**
     * Fills the outside section of the GUI with an item. This <b>does</b> replace non-empty slots.
     *
     * @param guiItem The item.
     */
    void fillBorder(@NotNull GuiItem guiItem);

    /**
     * Fills the outside section of the GUI with alternating items. This <b>does</b> replace non-empty slots.
     *
     * @param guiItems The items to alternate between.
     */
    void fillBorder(@NotNull List<GuiItem> guiItems);

    /**
     * Fills a rectangle within the GUI with an item. This <b>does</b> replace non-empty slots.
     *
     * @param rowFrom Row point 1.
     * @param colFrom Col point 1.
     * @param rowTo   Row point 2.
     * @param colTo   Col point 2.
     * @param guiItem The item.
     */
    void fillBetweenPoints(int rowFrom, int colFrom, int rowTo, int colTo, @NotNull GuiItem guiItem);

    /**
     * Fills a rectangle within the GUI with alternating items. This <b>does</b> replace non-empty slots.
     *
     * @param rowFrom  Row point 1.
     * @param colFrom  Col point 1.
     * @param rowTo    Row point 2.
     * @param colTo    Col point 2.
     * @param guiItems The items to alternate between.
     */
    void fillBetweenPoints(int rowFrom, int colFrom, int rowTo, int colTo, @NotNull List<GuiItem> guiItems);

    /**
     * Fills the entire GUI with an item. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItem The item.
     */
    void fill(@NotNull GuiItem guiItem);

    /**
     * Fills the entire GUI with alternating items. This does <b>not</b> replace non-empty slots.
     *
     * @param guiItems The items to alternate between.
     */
    void fill(@NotNull List<GuiItem> guiItems);

}
