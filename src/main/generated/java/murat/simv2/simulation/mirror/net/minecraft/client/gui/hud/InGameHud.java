package murat.simv2.simulation.mirror.net.minecraft.client.gui.hud;

// Generated mirror stub for simulation closure.
public class InGameHud extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier AIR_BURSTING_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier AIR_EMPTY_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier AIR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier ARMOR_EMPTY_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier ARMOR_FULL_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier ARMOR_HALF_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier CROSSHAIR_ATTACK_INDICATOR_BACKGROUND_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier CROSSHAIR_ATTACK_INDICATOR_FULL_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier CROSSHAIR_ATTACK_INDICATOR_PROGRESS_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier CROSSHAIR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text DEMO_EXPIRED_MESSAGE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EFFECT_BACKGROUND_AMBIENT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EFFECT_BACKGROUND_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EXPERIENCE_BAR_BACKGROUND_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EXPERIENCE_BAR_PROGRESS_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_EMPTY_HUNGER_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_EMPTY_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_FULL_HUNGER_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_FULL_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_HALF_HUNGER_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier FOOD_HALF_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_ATTACK_INDICATOR_BACKGROUND_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_ATTACK_INDICATOR_PROGRESS_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_OFFHAND_LEFT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_OFFHAND_RIGHT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_SELECTION_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier HOTBAR_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier JUMP_BAR_BACKGROUND_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier JUMP_BAR_COOLDOWN_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier JUMP_BAR_PROGRESS_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier NAUSEA_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier POWDER_SNOW_OUTLINE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text SAVING_LEVEL_TEXT;
    public static java.util.Comparator<java.lang.Object> SCOREBOARD_ENTRY_COMPARATOR;
    public static java.lang.String SCOREBOARD_JOINER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier SPYGLASS_SCOPE;
    public static int SUBMERGED_IN_WATER_AIR_BUBBLE_DELAY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier VEHICLE_CONTAINER_HEART_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier VEHICLE_FULL_HEART_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier VEHICLE_HALF_HEART_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier VIGNETTE_TEXTURE;
    public float autosaveIndicatorAlpha;
    public java.lang.Object bossBarHud;
    public boolean canShowChatDisabledScreen;
    public java.lang.Object chatHud;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack currentStack;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.DebugHud debugHud;
    public static float field_32168;
    public static int field_32169;
    public static int field_32170;
    public static float field_32172;
    public static int field_33942;
    public static int field_33943;
    public static float field_35431;
    public static int field_52769;
    public static int field_52770;
    public static int field_54914;
    public static int field_54915;
    public static int field_54916;
    public static int field_54917;
    public static float field_54920;
    public static float field_54921;
    public static float field_54922;
    public static float field_54923;
    public static int field_54924;
    public static int field_54925;
    public long heartJumpEndTick;
    public int heldItemTooltipFade;
    public float lastAutosaveIndicatorAlpha;
    public int lastBurstBubble;
    public long lastHealthCheckTime;
    public int lastHealthValue;
    public java.lang.Object layeredDrawer;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text overlayMessage;
    public int overlayRemaining;
    public boolean overlayTinted;
    public java.lang.Object playerListHud;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int renderHealthValue;
    public java.lang.Object spectatorHud;
    public float spyglassScale;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text subtitle;
    public java.lang.Object subtitlesHud;
    public int ticks;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text title;
    public int titleFadeInTicks;
    public int titleFadeOutTicks;
    public int titleRemainTicks;
    public int titleStayTicks;
    public float vignetteDarkness;

    public InGameHud(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void clearTitle() {
    }

    public void clear() {
    }

    public void drawHeart(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType p1, int p2, int p3, boolean p4, boolean p5, boolean p6) {
    }

    public static int getAirBubbleDelay(int p0, boolean p1) {
        return 0;
    }

    public int getAirBubbleY(int p0, int p1) {
        return 0;
    }

    public static int getAirBubbles(int p0, int p1, int p2) {
        return 0;
    }

    public java.lang.Object getBossBarHud() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getCameraPlayer() {
        return null;
    }

    public java.lang.Object getChatHud() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.DebugHud getDebugHud() {
        return null;
    }

    public int getHeartCount(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return 0;
    }

    public int getHeartRows(int p0) {
        return 0;
    }

    public java.lang.Object getPlayerListHud() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getRiddenEntity() {
        return null;
    }

    public java.lang.Object getSpectatorHud() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer getTextRenderer() {
        return null;
    }

    public int getTicks() {
        return 0;
    }

    public void playBurstSound(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, int p2) {
    }

    public void renderAirBubbles(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, int p2, int p3, int p4) {
    }

    public static void renderArmor(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, int p2, int p3, int p4, int p5) {
    }

    public void renderAutosaveIndicator(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderChat(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderCrosshair(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderDemoTimer(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderExperienceBar(java.lang.Object p0, int p1) {
    }

    public void renderExperienceLevel(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderFood(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, int p2, int p3) {
    }

    public void renderHealthBar(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, int p2, int p3, int p4, int p5, float p6, int p7, int p8, int p9, boolean p10) {
    }

    public void renderHeldItemTooltip(java.lang.Object p0) {
    }

    public void renderHotbarItem(java.lang.Object p0, int p1, int p2, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p5, int p6) {
    }

    public void renderHotbar(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderMainHud(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderMiscOverlays(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderMountHealth(java.lang.Object p0) {
    }

    public void renderMountJumpBar(murat.simv2.simulation.mirror.net.minecraft.entity.JumpingMount p0, java.lang.Object p1, int p2) {
    }

    public void renderNauseaOverlay(java.lang.Object p0, float p1) {
    }

    public void renderOverlayMessage(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderOverlay(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, float p2) {
    }

    public void renderPlayerList(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderPortalOverlay(java.lang.Object p0, float p1) {
    }

    public void renderScoreboardSidebar(java.lang.Object p0, java.lang.Object p1) {
    }

    public void renderScoreboardSidebar(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderSleepOverlay(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderSpyglassOverlay(java.lang.Object p0, float p1) {
    }

    public void renderStatusBars(java.lang.Object p0) {
    }

    public void renderStatusEffectOverlay(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderTitleAndSubtitle(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void renderVignetteOverlay(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void render(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1) {
    }

    public void resetDebugHudChunk() {
    }

    public void setCanShowChatDisabledScreen(boolean p0) {
    }

    public void setDefaultTitleFade() {
    }

    public void setOverlayMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, boolean p1) {
    }

    public void setRecordPlayingOverlay(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setSubtitle(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setTitleTicks(int p0, int p1, int p2) {
    }

    public void setTitle(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public boolean shouldRenderExperience() {
        return false;
    }

    public boolean shouldRenderSpectatorCrosshair(murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult p0) {
        return false;
    }

    public boolean shouldShowChatDisabledScreen() {
        return false;
    }

    public void tickAutosaveIndicator() {
    }

    public void tick() {
    }

    public void tick(boolean p0) {
    }

    public void updateVignetteDarkness(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public InGameHud() {
    }

    public static class HeartType {
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType ABSORBING;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType CONTAINER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType FROZEN;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType NORMAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType POISONED;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType WITHERED;
        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType[] field_33952;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fullBlinkingTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier fullTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier halfBlinkingTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier halfTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier hardcoreFullBlinkingTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier hardcoreFullTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier hardcoreHalfBlinkingTexture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier hardcoreHalfTexture;

        public HeartType(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p2, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p3, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p4, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p5, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p6, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p7, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p8, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p9) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType fromPlayerState(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getTexture(boolean p0, boolean p1, boolean p2) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud.HeartType[] values() {
            return null;
        }

        public HeartType() {
        }

    }

    public static class SidebarEntry {
        public murat.simv2.simulation.mirror.net.minecraft.text.Text name;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text score;
        public int scoreWidth;

        public SidebarEntry(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1, int p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text name() {
            return null;
        }

        public int scoreWidth() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text score() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public SidebarEntry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
