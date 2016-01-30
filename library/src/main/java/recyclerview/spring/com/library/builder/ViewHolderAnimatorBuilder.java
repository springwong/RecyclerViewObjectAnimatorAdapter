package recyclerview.spring.com.library.builder;

import android.animation.Animator;
import android.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import recyclerview.spring.com.library.animator.ViewHolderAnimator;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class ViewHolderAnimatorBuilder {
    private List<Animator> animators;
    private int applyOnRow = ViewHolderAnimator.FUNC_DISABLE_VALUE;
    private int applyOnCol = ViewHolderAnimator.FUNC_DISABLE_VALUE;
    private int numOfCol = ViewHolderAnimator.FUNC_DISABLE_VALUE;
    private Boolean isAccessViewHolder = false;
    public ViewHolderAnimatorBuilder() {
        animators = new ArrayList<>();
    }
    public ViewHolderAnimator create(){
        return new ViewHolderAnimator(animators, isAccessViewHolder, applyOnRow, applyOnCol, numOfCol);
    }
//    public ViewHolderAnimator create(Boolean isAccessViewHolder){
//        return new ViewHolderAnimator(animators, isAccessViewHolder, applyOnRow, applyOnCol, numOfCol);
//    }
    public ViewHolderAnimatorBuilder setIsAccessViewHolder(Boolean isAccessViewHolder){
        this.isAccessViewHolder = isAccessViewHolder;
        return this;
    }
    public ViewHolderAnimatorBuilder setApplyOnCol(int applyOnCol) {
        this.applyOnCol = applyOnCol;
        return this;
    }

    public ViewHolderAnimatorBuilder setApplyOnRow(int applyOnRow) {
        this.applyOnRow = applyOnRow;
        return this;
    }

    public ViewHolderAnimatorBuilder setNumOfCol(int numOfCol) {
        this.numOfCol = numOfCol;
        return this;
    }

    public ViewHolderAnimatorBuilder addObjectAnimator(ObjectAnimator objectAnimator){
        animators.add(objectAnimator);
        return this;
    }
    public ViewHolderAnimatorBuilder addObjectAnimator(List<ObjectAnimator> objectAnimators){
        animators.addAll(objectAnimators);
        return this;
    }
}
