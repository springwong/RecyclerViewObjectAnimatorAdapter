package recyclerview.spring.com.library.helper;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by Spring80917 on 31/01/2016.
 */
public class ViewHelper {
    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.animate(v).setInterpolator(null);
    }
}
