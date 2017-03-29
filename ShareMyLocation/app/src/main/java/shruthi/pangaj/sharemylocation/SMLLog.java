package shruthi.pangaj;

import android.util.Log;

/**
 * Created by Pangaj on 27/03/17.
 */

public class SMLLog {
    /**
     * Verbose level logcat message (only output for debug builds)
     *
     * @param tag tag
     * @param message message
     */
    public static void v(String tag, String message) {
        if (SMLBuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * Debug level logcat message (only output for debug builds)
     *
     * @param tag tag
     * @param message message
     */
    public static void d(String tag, String message) {
        if (SMLBuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * Information level logcat message (always output)
     *
     * @param tag tag
     * @param message message
     */
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * Warning level logcat message (always output)
     *
     * @param tag tag
     * @param message message
     */
    public static void w(String tag, String message) {
        Log.w(tag, message);
    }

    /**
     * Error level logcat message (always output)
     *
     * @param tag tag
     * @param message message
     */
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }
}