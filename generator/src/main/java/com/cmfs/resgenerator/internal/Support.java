package com.cmfs.resgenerator.internal;

/**
 * @author cmfs
 */

class Support {

    public final int width;
    public final int height;
    public final Density widthDensity;
    public final Density heightDensity;
    public final Density scaleDensity;

    public Support(int width, int height, Density widthDensity, Density heightDensity, Density scaleDensity) {
        this.height = height;
        this.width = width;
        this.widthDensity = widthDensity;
        this.heightDensity = heightDensity;
        this.scaleDensity = scaleDensity;
    }

    public Support(int width, int height, Density widthDensity, Density heightDensity) {
        this.height = height;
        this.heightDensity = heightDensity;
        this.width = width;
        this.widthDensity = widthDensity;
        this.scaleDensity = widthDensity;
    }

    public Support(int width, int height, Density density) {
        this.height = height;
        this.width = width;
        this.widthDensity = density;
        this.heightDensity = density;
        this.scaleDensity = density;
    }

    public Density getWidthDensity() {
        return widthDensity;
    }

    public Density getHeightDensity() {
        return heightDensity;
    }

    public Density getDensity() {
        if (widthDensity != heightDensity) {
            throw new UnsupportedOperationException();
        }
        return widthDensity;
    }

    public Density getScaleDensity() {
        return scaleDensity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Support{");
        sb.append("height=").append(height);
        sb.append(", width=").append(width);
        sb.append(", widthDensity=").append(widthDensity);
        sb.append(", heightDensity=").append(heightDensity);
        sb.append('}');
        return sb.toString();
    }

}
