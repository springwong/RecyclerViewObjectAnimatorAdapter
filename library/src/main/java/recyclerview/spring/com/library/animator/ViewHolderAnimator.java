package recyclerview.spring.com.library.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;

import java.util.List;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class ViewHolderAnimator {
    public static int FUNC_DISABLE_VALUE = -1;
    protected AnimatorSet objectAnimators;
    protected Boolean isAccessViewHolder = false;
    protected int applyOnRow = FUNC_DISABLE_VALUE;
    protected int applyOnCol = FUNC_DISABLE_VALUE;
    protected int numOfCol = FUNC_DISABLE_VALUE;
    public ViewHolderAnimator(List<Animator> animators) {
        assignAnimatorSet(animators);
    }
    public ViewHolderAnimator(List<Animator> animators, Boolean isAccessViewHolder){
        assignAnimatorSet(animators);
        this.isAccessViewHolder = isAccessViewHolder;
    }
    public ViewHolderAnimator(List<Animator> animators, Boolean isAccessViewHolder, int applyOnRow, int applyOnCol, int numOfCol){
        assignAnimatorSet(animators);
        this.isAccessViewHolder = isAccessViewHolder;
        this.applyOnRow = applyOnRow;
        this.applyOnCol = applyOnCol;
        this.numOfCol = numOfCol;
    }
    private void assignAnimatorSet(List<Animator> animators){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        this.objectAnimators = animatorSet;
    }

    public int getApplyOnRow() {
        return applyOnRow;
    }

    public int getApplyOnCol() {
        return applyOnCol;
    }

    public int getNumOfCol() {
        return numOfCol;
    }

    public AnimatorSet getObjectAnimators() {
        return objectAnimators;
    }
    public Boolean getIsAccessViewHolder(){
        return isAccessViewHolder;
    }
}
