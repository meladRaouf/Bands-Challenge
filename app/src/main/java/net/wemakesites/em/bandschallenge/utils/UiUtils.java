package net.wemakesites.em.bandschallenge.utils;

import android.content.Context;
import android.view.View;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;


public final class UiUtils {
    private UiUtils() {

    }

    public static void showView(final View view) {
        view.setVisibility(View.VISIBLE);
    }


    public static void hideView(final View view) {
        view.setVisibility(View.GONE);
    }

    public static void showError(final Context context, final int errorMessageResId) {
        makeText(context, errorMessageResId, LENGTH_LONG).show();
    }
}
