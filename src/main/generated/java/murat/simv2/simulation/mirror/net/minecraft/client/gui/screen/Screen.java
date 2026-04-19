package murat.simv2.simulation.mirror.net.minecraft.client.gui.screen;

// Generated mirror stub for simulation closure.
public class Screen {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOTER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HEADER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INWORLD_FOOTER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INWORLD_HEADER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INWORLD_MENU_BACKGROUND_TEXTURE;
    public static long KEY_PRESS_NARRATION_DELAY;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier MENU_BACKGROUND_TEXTURE;
    public static long MOUSE_MOVE_NARRATION_DELAY;
    public static long MOUSE_PRESS_SCROLL_NARRATION_DELAY;
    public static long NARRATOR_MODE_CHANGE_DELAY;
    public static java.lang.Object PANORAMA_RENDERER;
    public static java.lang.Object ROTATING_PANORAMA_RENDERER;
    public static long SCREEN_INIT_NARRATION_DELAY;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text SCREEN_USAGE_TEXT;
    public java.util.List<java.lang.Object> children;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.util.List<java.lang.Object> drawables;
    public long elementNarrationStartTime;
    public java.util.concurrent.Executor executor;
    public int height;
    public java.lang.Object narrator;
    public java.lang.Object narratorToggleButton;
    public boolean screenInitialized;
    public long screenNarrationStartTime;
    public java.util.List<java.lang.Object> selectables;
    public java.lang.Object selected;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer textRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text title;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen.PositionedTooltip tooltip;
    public int width;

    public Screen(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void addCrashReportSection(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public java.lang.Object addDrawableChild(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object addDrawable(java.lang.Object p0) {
        return null;
    }

    public void addElementNarrations(java.lang.Object p0) {
    }

    public void addScreenNarrations(java.lang.Object p0) {
    }

    public java.lang.Object addSelectableChild(java.lang.Object p0) {
        return null;
    }

    public void applyBlur() {
    }

    public void applyKeyPressNarratorDelay() {
    }

    public void applyMouseMoveNarratorDelay() {
    }

    public void applyMousePressScrollNarratorDelay() {
    }

    public void blur() {
    }

    public boolean charTyped(char p0, int p1) {
        return false;
    }

    public java.util.List children() {
        return null;
    }

    public void clearAndInit() {
    }

    public void clearChildren() {
    }

    public void clearTooltip() {
    }

    public void close() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen.SelectedElementNarrationData findSelectedElementData(java.util.List p0, java.lang.Object p1) {
        return null;
    }

    public java.lang.Object getArrowNavigation(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getBorder(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getFocusedPath() {
        return null;
    }

    public java.lang.Object getFocused() {
        return null;
    }

    public java.lang.Object getMusic() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getNarratedTitle() {
        return null;
    }

    public java.lang.Object getNavigationFocus() {
        return null;
    }

    public int getNavigationOrder() {
        return 0;
    }

    public java.lang.Object getNavigationPath(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getTabNavigation() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer getTextRenderer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getTitle() {
        return null;
    }

    public static java.util.List getTooltipFromItem(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getUsageNarrationText() {
        return null;
    }

    public boolean handleTextClick(murat.simv2.simulation.mirror.net.minecraft.text.Style p0) {
        return false;
    }

    public static boolean hasAltDown() {
        return false;
    }

    public static boolean hasControlDown() {
        return false;
    }

    public static boolean hasShiftDown() {
        return false;
    }

    public boolean hasUsageText() {
        return false;
    }

    public java.util.Optional hoveredElement(double p0, double p1) {
        return null;
    }

    public void init() {
    }

    public void init(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, int p1, int p2) {
    }

    public void insertText(java.lang.String p0, boolean p1) {
    }

    public static boolean isCopy(int p0) {
        return false;
    }

    public static boolean isCut(int p0) {
        return false;
    }

    public boolean isDragging() {
        return false;
    }

    public boolean isFocused() {
        return false;
    }

    public boolean isMouseOver(double p0, double p1) {
        return false;
    }

    public boolean isNarratorActive() {
        return false;
    }

    public static boolean isPaste(int p0) {
        return false;
    }

    public static boolean isSelectAll(int p0) {
        return false;
    }

    public boolean isValidCharacterForName(java.lang.String p0, char p1, int p2) {
        return false;
    }

    public boolean keyPressed(int p0, int p1, int p2) {
        return false;
    }

    public boolean keyReleased(int p0, int p1, int p2) {
        return false;
    }

    public boolean mouseClicked(double p0, double p1, int p2) {
        return false;
    }

    public boolean mouseDragged(double p0, double p1, int p2, double p3, double p4) {
        return false;
    }

    public void mouseMoved(double p0, double p1) {
    }

    public boolean mouseReleased(double p0, double p1, int p2) {
        return false;
    }

    public boolean mouseScrolled(double p0, double p1, double p2, double p3) {
        return false;
    }

    public void narrateScreenIfNarrationEnabled(boolean p0) {
    }

    public void narrateScreen(boolean p0) {
    }

    public void onDisplayed() {
    }

    public void onFilesDropped(java.util.List p0) {
    }

    public void refreshNarrator(boolean p0) {
    }

    public void refreshWidgetPositions() {
    }

    public void removed() {
    }

    public void remove(java.lang.Object p0) {
    }

    public static void renderBackgroundTexture(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, int p2, int p3, float p4, float p5, int p6, int p7) {
    }

    public void renderBackground(java.lang.Object p0, int p1, int p2, float p3) {
    }

    public void renderDarkening(java.lang.Object p0) {
    }

    public void renderDarkening(java.lang.Object p0, int p1, int p2, int p3, int p4) {
    }

    public void renderInGameBackground(java.lang.Object p0) {
    }

    public void renderPanoramaBackground(java.lang.Object p0, float p1) {
    }

    public void renderWithTooltip(java.lang.Object p0, int p1, int p2, float p3) {
    }

    public void render(java.lang.Object p0, int p1, int p2, float p3) {
    }

    public void resize(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, int p1, int p2) {
    }

    public void setDragging(boolean p0) {
    }

    public void setElementNarrationDelay(long p0) {
    }

    public void setFocused(boolean p0) {
    }

    public void setFocused(java.lang.Object p0) {
    }

    public void setInitialFocus() {
    }

    public void setInitialFocus(java.lang.Object p0) {
    }

    public void setScreenNarrationDelay(long p0, boolean p1) {
    }

    public void setTooltip(java.lang.Object p0, java.lang.Object p1, boolean p2) {
    }

    public void setTooltip(java.util.List p0) {
    }

    public void setTooltip(java.util.List p0, java.lang.Object p1, boolean p2) {
    }

    public void setTooltip(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    public boolean shouldHideStatusEffectHud() {
        return false;
    }

    public boolean shouldPause() {
        return false;
    }

    public void switchFocus(java.lang.Object p0) {
    }

    public void tick() {
    }

    public void updateNarrator() {
    }

    public Screen() {
    }

    public static class PositionedTooltip {
        public java.lang.Object positioner;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.text.OrderedText> tooltip;

        public PositionedTooltip(java.util.List p0, java.lang.Object p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object positioner() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.util.List tooltip() {
            return null;
        }

        public PositionedTooltip() {
        }

    }

    public static class SelectedElementNarrationData extends java.lang.Object {
        public int index;
        public java.lang.Object selectType;
        public java.lang.Object selectable;

        public SelectedElementNarrationData(java.lang.Object p0, int p1, java.lang.Object p2) {
        }

        public SelectedElementNarrationData() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
