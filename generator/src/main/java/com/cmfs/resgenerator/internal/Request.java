package com.cmfs.resgenerator.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cmfs
 */

public class Request {

    private Map<Location, List<Support>> map;
    private Support defSupport;

    public Request() {
        map = new HashMap<>();
    }

    public static Request create() {
        return new Request();
    }

    public Request support(int width, int height, Density density) {
        support(width, height, density, density);
        return this;
    }

    public Request support(int width, int height, Density widthDensity, Density heightDensity) {
        Location location = new Location(width, height);
        List<Support> supportList = map.get(location);
        if (supportList == null) {
            supportList = new ArrayList<>();
            map.put(location, supportList);
        }
        supportList.add(new Support(width, height, widthDensity, heightDensity));
        return this;
    }

    public Request support(int width, int height, Density widthDensity, Density heightDensity, Density scaledDensity) {
        Location location = new Location(width, height);
        List<Support> supportList = map.get(location);
        if (supportList == null) {
            supportList = new ArrayList<>();
            map.put(location, supportList);
        }
        supportList.add(new Support(width, height, widthDensity, heightDensity, scaledDensity));
        return this;
    }

    public Request defaultSupport(int width, int height, Density density) {
        defSupport = new Support(width, height, density, density);
        return this;
    }

    public Request defaultSupport(int width, int height, Density widthDensity, Density heightDensity) {
        defSupport = new Support(width, height, widthDensity, heightDensity);
        return this;
    }

    public Executor build() {
        return new Executor(map, defSupport);
    }

    @Override
    public String toString() {
        return map.toString();
    }


}
