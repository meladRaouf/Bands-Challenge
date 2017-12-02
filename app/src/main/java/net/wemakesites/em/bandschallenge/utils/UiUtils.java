package net.wemakesites.em.bandschallenge.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


public class UiUtils {
    private UiUtils() {

    }

    public static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }


    public static void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    public static void showError(Context context, int errorMessageResId) {
        Toast.makeText(context, errorMessageResId, Toast.LENGTH_LONG).show();
    }
}
