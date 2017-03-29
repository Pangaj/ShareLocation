package shruthi.pangaj.sharemylocation.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.WindowManager;

import shruthi.pangaj.SMLLog;
import shruthi.pangaj.sharemylocation.R;

/**
 * Created by Jai on 29/03/17.
 */

public class SMLUtilities {
    private static final String TAG = "SMLUtilities";

    /**
     * Method to create progress dialog without text
     */
    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
            dialog.setCancelable(false);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setContentView(R.layout.sml_progress_dialog);
        } catch (WindowManager.BadTokenException exception) {
            Log.d(TAG, "Caught Exception" + exception.getMessage());
        }
        return dialog;
    }

    /**
     * Method to check the network connection
     *
     * @return Return the connection status
     */
    public static Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                return true;
            }
        } catch (Exception exception) {
            SMLLog.d(TAG, "CheckConnectivity Exception: " + exception.getMessage());
        }
        return false;
    }
}