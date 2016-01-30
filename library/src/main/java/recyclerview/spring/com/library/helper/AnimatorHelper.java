package recyclerview.spring.com.library.helper;

import android.animation.ObjectAnimator;
import android.view.animation.Interpolator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class AnimatorHelper {
    public static ObjectAnimator getFadeInAnimator(int duration, Interpolator interpolator, float fromAlphaPercent){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "alpha", fromAlphaPercent / 100, 1f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static List<ObjectAnimator> getScaleInAnimator(int duration , Interpolator interpolator, float fromSizePercent){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "scaleX", fromSizePercent / 100, 1f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(null, "scaleY", fromSizePercent / 100, 1f);
        animator2.setDuration(duration);
        animator2.setInterpolator(interpolator);
        return Arrays.asList(animator, animator2);
    }

    public static ObjectAnimator getSlideInRightAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "translationX", offset, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public static ObjectAnimator getSlideInLeftAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "translationX", -1 * offset, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static ObjectAnimator getSlideInTopAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "translationY", -1 * offset, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static ObjectAnimator getSlideInBottomAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "translationY",  offset, 0f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static ObjectAnimator getRotateXAxisAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "rotationX",  0, 360f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static ObjectAnimator getRotateYAxisAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "rotationX",  0, 360f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }
    public static ObjectAnimator getRotateZAxisAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "rotation",  0, 360f);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public static ObjectAnimator getTranslateZAnimator(int duration , Interpolator interpolator, float offset){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, "translationZ",  100, 0);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public static ObjectAnimator getPropertyAnimator(int duration, Interpolator interpolator, String propertyName, int from, int to){
        ObjectAnimator animator = ObjectAnimator.ofInt(null, propertyName, from, to);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public static ObjectAnimator getPropertyShakeAnimator(int duration, Interpolator interpolator, String propertyName, float leftMost, float rightMost){
        ObjectAnimator animator = ObjectAnimator.ofFloat(null, propertyName, 0, leftMost, 0, rightMost, 0);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public static ObjectAnimator getOneShotPropertyAnimator(String propertyName){
        ObjectAnimator animator = ObjectAnimator.ofInt(null, propertyName, 0, 0);
        animator.setDuration(0);
        animator.setInterpolator(null);
        return animator;
    }
}
