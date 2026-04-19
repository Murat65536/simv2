package murat.simv2.simulation.mirror.net.minecraft.client.font;

// Generated mirror stub for simulation closure.
public class TextRenderer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int ARABIC_SHAPING_LETTERS_SHAPE;
    public static float FORWARD_SHIFT;
    public static float Z_INDEX;
    public static int field_55090;
    public int fontHeight;
    public java.util.function.Function<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> fontStorageAccessor;
    public java.lang.Object handler;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public boolean validateAdvance;

    public TextRenderer(java.util.function.Function p0, boolean p1) {
    }

    public int drawInternal(java.lang.String p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9, boolean p10) {
        return 0;
    }

    public int drawInternal(murat.simv2.simulation.mirror.net.minecraft.text.OrderedText p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9, boolean p10) {
        return 0;
    }

    public float drawLayer(java.lang.String p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9, boolean p10) {
        return 0.0F;
    }

    public float drawLayer(murat.simv2.simulation.mirror.net.minecraft.text.OrderedText p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9, boolean p10) {
        return 0.0F;
    }

    public void drawWithOutline(murat.simv2.simulation.mirror.net.minecraft.text.OrderedText p0, float p1, float p2, int p3, int p4, org.joml.Matrix4f p5, java.lang.Object p6, int p7) {
    }

    public int draw(java.lang.String p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9) {
        return 0;
    }

    public int draw(murat.simv2.simulation.mirror.net.minecraft.text.OrderedText p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9) {
        return 0;
    }

    public int draw(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9) {
        return 0;
    }

    public int draw(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, float p1, float p2, int p3, boolean p4, org.joml.Matrix4f p5, java.lang.Object p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8, int p9, boolean p10) {
        return 0;
    }

    public java.lang.Object getFontStorage(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object getTextHandler() {
        return null;
    }

    public int getWidth(java.lang.String p0) {
        return 0;
    }

    public int getWidth(murat.simv2.simulation.mirror.net.minecraft.text.OrderedText p0) {
        return 0;
    }

    public int getWidth(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable p0) {
        return 0;
    }

    public int getWrappedLinesHeight(java.lang.String p0, int p1) {
        return 0;
    }

    public int getWrappedLinesHeight(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable p0, int p1) {
        return 0;
    }

    public boolean isRightToLeft() {
        return false;
    }

    public java.lang.String mirror(java.lang.String p0) {
        return null;
    }

    public java.lang.String trimToWidth(java.lang.String p0, int p1) {
        return null;
    }

    public java.lang.String trimToWidth(java.lang.String p0, int p1, boolean p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable trimToWidth(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable p0, int p1) {
        return null;
    }

    public static int tweakTransparency(int p0) {
        return 0;
    }

    public java.util.List wrapLines(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable p0, int p1) {
        return null;
    }

    public TextRenderer() {
    }

    public static class Drawer extends java.lang.Object {
        public int backgroundColor;
        public int color;
        public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer field_24240;
        public java.util.List<java.lang.Object> glyphs;
        public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType layerType;
        public int light;
        public org.joml.Matrix4f matrix;
        public java.util.List<java.lang.Object> rectangles;
        public boolean shadow;
        public boolean swapZIndex;
        public java.lang.Object vertexConsumers;
        public float x;
        public float y;

        public Drawer(murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer p0, java.lang.Object p1, float p2, float p3, int p4, boolean p5, org.joml.Matrix4f p6, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p7, int p8) {
        }

        public Drawer(murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer p0, java.lang.Object p1, float p2, float p3, int p4, int p5, boolean p6, org.joml.Matrix4f p7, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType p8, int p9, boolean p10) {
        }

        public boolean accept(int p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1, int p2) {
            return false;
        }

        public void addRectangle(java.lang.Object p0) {
        }

        public void drawGlyphs() {
        }

        public float drawLayer(float p0) {
            return 0.0F;
        }

        public float getBackgroundZIndex() {
            return 0.0F;
        }

        public float getForegroundZIndex() {
            return 0.0F;
        }

        public int getRenderColor(java.lang.Object p0) {
            return 0;
        }

        public int getShadowColor(murat.simv2.simulation.mirror.net.minecraft.text.Style p0, int p1) {
            return 0;
        }

        public Drawer() {
        }

    }

    public static class TextLayerType {
        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType NORMAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType POLYGON_OFFSET;
        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType SEE_THROUGH;
        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType[] field_33996;

        public TextLayerType(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer.TextLayerType[] values() {
            return null;
        }

        public TextLayerType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
