package recyclerview.spring.com.demo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import recyclerview.spring.com.demo.sample.DummyRecyclerAdapter;
import recyclerview.spring.com.library.adapter.RecyclerViewAnimatorAdapter;
import recyclerview.spring.com.library.animator.ViewHolderAnimator;
import recyclerview.spring.com.library.builder.FakeViewBuilder;
import recyclerview.spring.com.library.builder.ViewHolderAnimatorBuilder;
import recyclerview.spring.com.library.helper.AnimatorHelper;
import recyclerview.spring.com.library.helper.AnimatorLoader;
import recyclerview.spring.com.library.interfaces.ViewHolderFilterImpl;
import timber.log.Timber;

public class DemoActivity extends AppCompatActivity {

    @Bind(R.id.demoRecyclerView)
    protected RecyclerView recyclerView;
    private int numOfCol = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numOfCol);
        recyclerView.setLayoutManager(gridLayoutManager);

        basicAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_basic:
                basicAnimation();
                break;
            case R.id.menu_resource:
                resourceAnimation();
                break;
            case R.id.menu_animator_list:
                combineAnimation();
                break;
            case R.id.menu_translate:
                translateAnimation();
                break;
            case R.id.menu_view_holder_item:
                viewHolderItemAnimation();
                break;
            case R.id.menu_circle_reveal:
                viewHolderCircleRevealAnimation();
                break;
            case R.id.menu_single_col:
                singleColAnimation();
                break;
            case R.id.menu_multi_col:
                multipleColAnimation();
                break;
        }
        return true;
    }

    private RecyclerViewAnimatorAdapter renewAdapter(List<ViewHolderAnimator> list){
        DummyRecyclerAdapter dummyRecyclerAdapter = new DummyRecyclerAdapter(this);
        RecyclerViewAnimatorAdapter recyclerViewAnimatorAdapter = new RecyclerViewAnimatorAdapter(dummyRecyclerAdapter);
        for(ViewHolderAnimator animator : list)
            recyclerViewAnimatorAdapter.addAdapterAnimator(animator);
        recyclerView.setAdapter(recyclerViewAnimatorAdapter);
        return recyclerViewAnimatorAdapter;
    }
    protected void basicAnimation(){
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(ObjectAnimator.ofFloat(null, "alpha", 0.2f, 1f).setDuration(1000))
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }
    protected void resourceAnimation(){
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(anim)
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }

    protected void combineAnimation(){
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(anim)
                .addObjectAnimator(ObjectAnimator.ofFloat(null, "alpha", 0.2f, 1f).setDuration(1000))
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }

    protected void translateAnimation(){
        View fakeTarget = new FakeViewBuilder(this).setViewSizeByFullScreenPercent(30, 30).create();
        Animator animator = AnimatorHelper.getSlideInRightAnimator(1000, new DecelerateInterpolator(1), fakeTarget.getWidth());
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(animator)
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }
    protected void viewHolderItemAnimation(){
        Animator animator = AnimatorHelper.getPropertyAnimator(1000, new LinearInterpolator(), "textView", 0, 100);
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .setIsAccessViewHolder(true)
                .addObjectAnimator(animator)
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }
    protected void viewHolderCircleRevealAnimation(){
        Animator animator = AnimatorHelper.getOneShotPropertyAnimator("circleReveal");
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .setIsAccessViewHolder(true)
                .addObjectAnimator(animator)
                .create();
        renewAdapter(Arrays.asList(viewHolderAnimator));
    }
    protected void singleColAnimation(){
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .setNumOfCol(numOfCol)
                .setApplyOnCol(1)
                .addObjectAnimator(anim)
                .addObjectAnimator(ObjectAnimator.ofFloat(null, "alpha", 0.2f, 1f).setDuration(1000))
                .create();
        RecyclerViewAnimatorAdapter adapter = renewAdapter(Arrays.asList(viewHolderAnimator));
        adapter.setViewHolderFilter(new ViewHolderFilterImpl());
    }
    protected void multipleColAnimation(){
        View fakeTarget = new FakeViewBuilder(this).setViewSizeByFullScreenPercent(30, 30).create();
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);
        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
                .setNumOfCol(numOfCol)
                .setApplyOnCol(1)
                .addObjectAnimator(anim)
                .addObjectAnimator(ObjectAnimator.ofFloat(null, "alpha", 0.2f, 1f).setDuration(1000))
                .create();
        ViewHolderAnimator leftViewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(AnimatorHelper.getSlideInLeftAnimator(1000, new DecelerateInterpolator(1), fakeTarget.getWidth()))
                .setNumOfCol(numOfCol)
                .setApplyOnCol(0)
                .create();
        ViewHolderAnimator rightViewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(AnimatorHelper.getSlideInRightAnimator(1000, new DecelerateInterpolator(1), fakeTarget.getWidth()))
                .setNumOfCol(numOfCol)
                .setApplyOnCol(2)
                .create();
        RecyclerViewAnimatorAdapter adapter = renewAdapter(Arrays.asList(viewHolderAnimator, leftViewHolderAnimator, rightViewHolderAnimator));
        adapter.setViewHolderFilter(new ViewHolderFilterImpl());
    }
    protected void basicAnimation2(){
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        View fakeView = new FakeViewBuilder(this)
                .setViewRect(0, 0, displayMetrics.widthPixels /4, displayMetrics.heightPixels / 4)
                .setViewPadding(20, 20, 20, 20)
                .create();

        ViewHolderAnimator viewHolderAnimator = new ViewHolderAnimatorBuilder()
//                .addObjectAnimator(anim)
//                .addObjectAnimator(AnimatorHelper.getSlideInLeftAnimator(1000, new LinearInterpolator(), displayMetrics.widthPixels/1))
                .addObjectAnimator(AnimatorHelper.getStandUpAnimator(fakeView))
                .setNumOfCol(numOfCol)
                .setApplyOnCol(1)
                .create();
        ViewHolderAnimator propertyUpdateAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(AnimatorHelper.getPropertyAnimator(1000, new LinearInterpolator(), "textView", 0, 100))
                .addObjectAnimator(AnimatorHelper.getOneShotPropertyAnimator("circleReveal"))
                .setIsAccessViewHolder(true)
                .setNumOfCol(numOfCol)
                .setApplyOnCol(1)
                .create();

        ViewHolderAnimator leftViewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(AnimatorHelper.getSlideInLeftAnimator(1000, new DecelerateInterpolator(1), displayMetrics.widthPixels / 4))
                .setNumOfCol(numOfCol)
                .setApplyOnCol(0)
                .create();

        ViewHolderAnimator rightViewHolderAnimator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(AnimatorHelper.getSlideInRightAnimator(1000, new DecelerateInterpolator(1), displayMetrics.widthPixels / 4))
                .setNumOfCol(numOfCol)
                .setApplyOnCol(2)
                .create();

        DummyRecyclerAdapter dummyRecyclerAdapter = new DummyRecyclerAdapter(this);
        RecyclerViewAnimatorAdapter recyclerViewAnimatorAdapter = new RecyclerViewAnimatorAdapter(dummyRecyclerAdapter);
//        recyclerViewAnimatorAdapter.addAdapterAnimator(viewHolderAnimator);
        recyclerViewAnimatorAdapter.addAdapterAnimator(rightViewHolderAnimator);
        recyclerViewAnimatorAdapter.addAdapterAnimator(leftViewHolderAnimator);
        recyclerViewAnimatorAdapter.addAdapterAnimator(propertyUpdateAnimator);
        recyclerViewAnimatorAdapter.setViewHolderFilter(new ViewHolderFilterImpl());
        recyclerView.setAdapter(recyclerViewAnimatorAdapter);
    }

}
