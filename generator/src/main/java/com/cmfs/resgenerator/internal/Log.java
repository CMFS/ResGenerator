package com.cmfs.resgenerator.internal;

import java.util.Locale;

/**
 * @author cmfs
 */

class Log {

    public static void println(String format, Object... args) {
        System.out.println(String.format(Locale.US, format, args));
    }

}
