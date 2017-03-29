package shruthi.pangaj.sharemylocation.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import shruthi.pangaj.SMLLog;
import shruthi.pangaj.sharemylocation.R;
import shruthi.pangaj.sharemylocation.fragments.SMLAlertDialogFragment;
import shruthi.pangaj.sharemylocation.helpers.SMLUtilities;

/**
 * Created by pangaj on 20/10/16.
 */
public class SMLBaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    private ProgressDialog mProgressDialog;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMLLog.v(TAG, "onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SMLLog.v(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        SMLLog.v(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SMLLog.v(TAG, "onResume() in base activity");

    }

    @Override
    protected void onPause() {
        super.onPause();
        SMLLog.v(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        SMLLog.v(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMLLog.v(TAG, "onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SMLLog.v(TAG, "onNewIntent()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SMLLog.v(TAG, "onConfigurationChanged()");
    }

    /**
     * Method to show the Progress dialog
     */
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = SMLUtilities.createProgressDialog(this);
            mProgressDialog.show();
        } else {
            mProgressDialog.show();
        }
    }

    /**
     * Method to hide Progress dialog.
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * Method to show alert dialog.
     *
     * @param message Message for the alert.
     */
    public void showAlertDialog(String title, String message) {
        try {
            SMLAlertDialogFragment alertDialog = SMLAlertDialogFragment.instance(title, message, false, null);
            alertDialog.show(getFragmentManager(), "alertDialog");
        } catch (WindowManager.BadTokenException e) {
            SMLLog.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Method to show alert dialog.
     *
     * @param message Message for the alert.
     */
    public void showToastAlertDialog(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to show alert dialog.
     *
     * @param title    The title for the alert.
     * @param message  Message for the alert.
     * @param cancel   The cancel.
     * @param listener Handle the user events.
     */
    public void showAlertDialog(String title, String message, boolean cancel, DialogInterface.OnClickListener listener) {
        try {
            SMLAlertDialogFragment alertDialog = SMLAlertDialogFragment.instance(title, message, cancel, listener);
            alertDialog.show(getFragmentManager(), "alertDialog");
        } catch (WindowManager.BadTokenException e) {
            SMLLog.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Method to show Snack bar
     *
     * @param layout  The layout
     * @param message The message
     */
    public void showSnackBar(View layout, final String message) {
        snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (message.equalsIgnoreCase(getString(R.string.no_internet_connection))) {
                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        }
                    }
                });
        snackbar.show();
    }

    /**
     * Method to hide the window keyboard with out view
     */
    public void dismissKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}