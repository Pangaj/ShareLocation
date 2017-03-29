package shruthi.pangaj.sharemylocation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import shruthi.pangaj.SMLLog;

/**
 * Created by pangaj on 20/10/16.
 */
public class SMLAlertDialogFragment extends DialogFragment {
    private static final String TITLE_KEY = "title";
    private static final String MESSAGE_KEY = "message";
    private static final String CANCEL_KEY = "cancel";
    private static final String POSITIVE_KEY = "ok";
    private static final String NEGATIVE_KEY = "cancel";
    private static final String DEFAULT_TEXT_CHANGED = "defaultTextChanged";
    private static OnClickListener m_listener;
    private static SMLAlertDialogFragment mSingleDialogInstance;

    /**
     * Summary
     *
     * @param title    The title of the alert box.
     * @param message  The to be displayed.
     * @param listener Handle the user events.
     * @return Fragment value will be returned.
     */
    public static SMLAlertDialogFragment instance(String title, String message, boolean cancel, OnClickListener listener) {
        SMLAlertDialogFragment fragment = new SMLAlertDialogFragment();
        // Set parameters
        Bundle params = new Bundle();
        params.putString(TITLE_KEY, title);
        params.putString(MESSAGE_KEY, message);
        params.putBoolean(CANCEL_KEY, cancel);
        fragment.setArguments(params);
        m_listener = listener;
        return fragment;
    }

    public static SMLAlertDialogFragment instance(String title, String message, boolean cancel, boolean defaultTextChanged, String positiveButton, String negativeButton, OnClickListener listener) {
        SMLAlertDialogFragment fragment = new SMLAlertDialogFragment();
        // Set parameters
        Bundle params = new Bundle();
        params.putString(TITLE_KEY, title);
        params.putString(MESSAGE_KEY, message);
        params.putBoolean(CANCEL_KEY, cancel);
        params.putBoolean(DEFAULT_TEXT_CHANGED, defaultTextChanged);
        params.putString(POSITIVE_KEY, positiveButton);
        params.putString(NEGATIVE_KEY, negativeButton);
        fragment.setArguments(params);
        m_listener = listener;
        return fragment;
    }

    public static SMLAlertDialogFragment instance(String title, String message, String negativeButtonTitle, String positiveButtonTitle, OnClickListener listener) {
        SMLAlertDialogFragment fragment = new SMLAlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, title);
        bundle.putString(MESSAGE_KEY, message);
        if (negativeButtonTitle != null) {
            bundle.putString(NEGATIVE_KEY, negativeButtonTitle);
        }
        if (positiveButtonTitle != null) {
            bundle.putString(POSITIVE_KEY, positiveButtonTitle);
        }
        fragment.setArguments(bundle);
        m_listener = listener;
        return fragment;
    }

    /**
     * Creates the instance of alert dialog fragment if already one not exist
     *
     * @param title    The title of the alert dialog
     * @param message  The message to display in the alert dialog
     * @param cancel   The boolean to indicate whether cancel button in required or not
     * @param listener The listener for OK button
     * @return Returns the instance of an alert dialog
     */
    public static SMLAlertDialogFragment singleDialogInstance(String title, String message, boolean cancel, OnClickListener listener) {
        try {
            if (mSingleDialogInstance == null) {
                mSingleDialogInstance = new SMLAlertDialogFragment();
                // Set parameters
                Bundle params = new Bundle();
                params.putString(TITLE_KEY, title);
                params.putString(MESSAGE_KEY, message);
                params.putBoolean(CANCEL_KEY, cancel);
                mSingleDialogInstance.setArguments(params);
                m_listener = listener;
                return mSingleDialogInstance;
            } else {
                return null;
            }
        } catch (Exception e) {
            SMLLog.d("MeAlertDialogFragment", e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(TITLE_KEY);
        String message = getArguments().getString(MESSAGE_KEY);
        // Create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, m_listener);
        if (getArguments().getBoolean(CANCEL_KEY)) {
            builder.setNegativeButton(android.R.string.cancel, m_listener);
        }
        if (getArguments().getBoolean(DEFAULT_TEXT_CHANGED)) {
            builder.setNegativeButton(getArguments().getString(NEGATIVE_KEY), m_listener);
            builder.setPositiveButton(getArguments().getString(POSITIVE_KEY), m_listener);
        }
        Dialog alert = builder.create();
        setCancelable(false);
        return alert;
    }
}