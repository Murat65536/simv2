package murat.simv2.simulation.mirror.net.minecraft.client.gui.screen;

// Generated mirror stub for simulation closure.
public class DownloadingTerrainScreen extends murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOTER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HEADER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INWORLD_FOOTER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INWORLD_HEADER_SEPARATOR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier MENU_BACKGROUND_TEXTURE;
    public static long MIN_LOAD_TIME_MS;
    public static java.lang.Object PANORAMA_RENDERER;
    public static java.lang.Object ROTATING_PANORAMA_RENDERER;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text TEXT;
    public java.lang.Object backgroundSprite;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.util.concurrent.Executor executor;
    public int height;
    public long loadStartTime;
    public java.lang.Object narratorToggleButton;
    public java.util.function.BooleanSupplier shouldClose;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer textRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text title;
    public int width;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason worldEntryReason;

    public DownloadingTerrainScreen(java.util.function.BooleanSupplier p0, murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason p1) {
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

    public java.lang.Object getBackgroundSprite() {
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

    public void setFocused(boolean p0) {
    }

    public void setFocused(java.lang.Object p0) {
    }

    public void setInitialFocus() {
    }

    public void setInitialFocus(java.lang.Object p0) {
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

    public DownloadingTerrainScreen() {
    }

    public static class WorldEntryReason {
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason END_PORTAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason NETHER_PORTAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason OTHER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason[] field_51490;

        public WorldEntryReason(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason[] values() {
            return null;
        }

        public WorldEntryReason() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
