package recyclerview.spring.com.library.interfaces;

import android.support.v7.widget.RecyclerView;

import recyclerview.spring.com.library.animator.ViewHolderAnimator;

/**
 * Created by Spring80917 on 31/01/2016.
 */
public class ViewHolderFilterImpl implements ViewHolderFilter {
    @Override
    public Boolean skipViewHolder(RecyclerView.ViewHolder holder, ViewHolderAnimator viewHolderAnimator) {
        Boolean isSkipViewHolder = false;
        if(viewHolderAnimator.getNumOfCol() != ViewHolderAnimator.FUNC_DISABLE_VALUE){
            int position = holder.getLayoutPosition() ;
            int mod = position % viewHolderAnimator.getNumOfCol();
            int divide = (int)(position / viewHolderAnimator.getNumOfCol());
            if(viewHolderAnimator.getApplyOnCol() != ViewHolderAnimator.FUNC_DISABLE_VALUE && viewHolderAnimator.getApplyOnRow() != ViewHolderAnimator.FUNC_DISABLE_VALUE){
                //apply on specify cell
                if(divide != viewHolderAnimator.getApplyOnRow()
                        ||mod != viewHolderAnimator.getApplyOnCol()){
                    isSkipViewHolder = true;
                }
            }
            else if(viewHolderAnimator.getApplyOnCol() == ViewHolderAnimator.FUNC_DISABLE_VALUE && viewHolderAnimator.getApplyOnRow() != ViewHolderAnimator.FUNC_DISABLE_VALUE){
                //apply on specify row
                if(divide != viewHolderAnimator.getApplyOnRow()) {
                    isSkipViewHolder = true;
                }
            }
            else if(viewHolderAnimator.getApplyOnCol() != ViewHolderAnimator.FUNC_DISABLE_VALUE && viewHolderAnimator.getApplyOnRow() == ViewHolderAnimator.FUNC_DISABLE_VALUE){
                //apply on specify col
                if(mod != viewHolderAnimator.getApplyOnCol()){
                    isSkipViewHolder = true;
                }
            }
        }
        return isSkipViewHolder;
    }
}
