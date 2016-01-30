package recyclerview.spring.com.library.interfaces;

import android.support.v7.widget.RecyclerView;

import recyclerview.spring.com.library.animator.ViewHolderAnimator;

/**
 * Created by Spring80917 on 31/01/2016.
 */
public interface ViewHolderFilter {
    public Boolean skipViewHolder(RecyclerView.ViewHolder holder, ViewHolderAnimator viewHolderAnimator);
}
