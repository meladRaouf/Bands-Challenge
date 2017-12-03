package net.wemakesites.em.bandschallenge.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public final class KeyboardUtil {

    private KeyboardUtil() {

    }

    public static void hideKeyboard(final Activity activity) {
        final View view = activity.getCurrentFocus();
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
