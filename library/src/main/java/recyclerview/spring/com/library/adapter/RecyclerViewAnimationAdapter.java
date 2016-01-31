package recyclerview.spring.com.library.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;

import recyclerview.spring.com.library.animator.ViewHolderAnimator;
import recyclerview.spring.com.library.helper.ViewHelper;
import recyclerview.spring.com.library.interfaces.ViewHolderFilter;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class RecyclerViewAnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private Boolean isFirstOnly = false;
    private List<ViewHolderAnimator> viewHolderAnimators;
    private Boolean disableFirstTimeAnimation = true;
    private ViewHolderFilter viewHolderFilter;
    private WeakHashMap<RecyclerView.ViewHolder, List<AnimatorSet>> holderAnimatorMap;
    //TODO: future try to add delay when start
    private int delayInterval = 400;

    public RecyclerViewAnimationAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        mAdapter = adapter;
        holderAnimatorMap = new WeakHashMap<>();
        viewHolderAnimators = new ArrayList<>();
    }
    public void addAdapterAnimator(Animator animator){
        viewHolderAnimators.add(new ViewHolderAnimator(Arrays.asList(animator)));
    }
    public void addAdapterAnimator(List<Animator> animators){
        viewHolderAnimators.add(new ViewHolderAnimator(animators));
    }
    public void addAdapterAnimator(ViewHolderAnimator animators){
        viewHolderAnimators.add(animators);
    }

    public void setViewHolderFilter(ViewHolderFilter viewHolderFilter) {
        this.viewHolderFilter = viewHolderFilter;
    }

    private void setIsFirstOnly(Boolean isFirstOnly) {
        this.isFirstOnly = isFirstOnly;
    }
    private void setDisableFirstTimeAnimation(Boolean disableFirstTimeAnimation) {
        this.disableFirstTimeAnimation = disableFirstTimeAnimation;
    }
    private void startAnimation(RecyclerView.ViewHolder holder, int delay){
        List<AnimatorSet> setList = new ArrayList<>();
        for (ViewHolderAnimator viewHolderAnimator : viewHolderAnimators){
            if(viewHolderFilter != null && viewHolderFilter.skipViewHolder(holder, viewHolderAnimator)){
                continue;
            }
            AnimatorSet set = viewHolderAnimator.getObjectAnimators().clone();
            if(viewHolderAnimator.getIsAccessViewHolder())
                set.setTarget(holder);
            else
                set.setTarget(holder.itemView);
            set.setStartDelay(delay);
            set.start();
            setList.add(set);
        }
        holderAnimatorMap.put(holder, setList);
    }
    private void startAnimation(RecyclerView.ViewHolder holder){
        startAnimation(holder, 0);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder = mAdapter.onCreateViewHolder(parent, viewType);
        //TODO : unused currently as startup animation is success to display. future test on first show animation
        if(!disableFirstTimeAnimation){
            /*holder.itemView.animate()
                    //infinite minimum value of duration
                    .setDuration(1)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (isFirstOnly) {
                                if (holder.itemView.getY() % holder.itemView.getHeight() == 0
                                        && holder.itemView.getX() % holder.itemView.getWidth() == 0) {
                                    holder.itemView.clearAnimation();
                                    startAnimation(holder, delayInterval * holder.getLayoutPosition());
                                }
                            } else {
                                if (holder.itemView.getY() % holder.itemView.getHeight() != 0
                                        || holder.itemView.getX() % holder.itemView.getWidth() != 0) {
                                    startAnimation(holder);
                                }
                            }
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {}
                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }
                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    }).start();*/
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
        if(!isFirstOnly){
            startAnimation(holder);
        }
    }
    @Override public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mAdapter.registerAdapterDataObserver(observer);
    }
    @Override public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mAdapter.unregisterAdapterDataObserver(observer);
    }
    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }
    @Override public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }
    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }
    public RecyclerView.Adapter<RecyclerView.ViewHolder> getWrappedAdapter() {
        return mAdapter;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        ViewHelper.clear(holder.itemView);
        if(holderAnimatorMap.containsKey(holder)){
            List<AnimatorSet> setList = holderAnimatorMap.get(holder);
            for(AnimatorSet set : setList){
                set.cancel();
            }
            holderAnimatorMap.remove(holder);
        }
        mAdapter.onViewRecycled(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }
}