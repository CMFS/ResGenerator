package com.cmfs.resgenerator.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cmfs
 */

public enum Density {

    /**
     * 120dpi
     */
    @Deprecated
    LDPI(120),
    /**
     * 160dpi, 基准
     */
    MDPI(160),
    /**
     * 240dpi
     */
    HDPI(240),
    /**
     * 320dpi
     */
    XHDPI(320),
    /**
     * 480dpi
     */
    XXHDPI(480),
    /**
     * 640dpi
     */
    XXXHDPI(640);

    static final int BASE = 160;
    private static final Map<Float, Density> DENSITY_HASH_MAP = new HashMap<>();

    static {
        for (Density density : values()) {
            DENSITY_HASH_MAP.put(density.getValue(), density);
        }
    }

    private float density;

    Density(int dpi) {
        density = ((float) dpi) / BASE;
    }

    public static Density toEnum(double value) {
        return DENSITY_HASH_MAP.get(value);
    }

    public float getValue() {
        return density;
    }

}
