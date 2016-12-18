package com.cmfs.resgenerator;

import java.util.Locale;

/**
 * @author cmfs
 */

class Logger {

    static void d(String tag, String format, Object... args) {
        Logger.d(tag, String.format(Locale.US, format, args));
    }

}
