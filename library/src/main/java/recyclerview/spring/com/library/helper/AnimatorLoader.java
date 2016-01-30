package recyclerview.spring.com.library.helper;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class AnimatorLoader {
    public static Animator loadAnimator(Context context , int resId){
        return AnimatorInflater.loadAnimator(context, resId);
    }
}
