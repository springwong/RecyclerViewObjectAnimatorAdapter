package recyclerview.spring.com.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recyclerview.spring.com.demo.sample.DummyRecyclerAdapter;
import recyclerview.spring.com.library.adapter.RecyclerViewAnimationAdapter;
import recyclerview.spring.com.library.animator.ViewHolderAnimator;
import recyclerview.spring.com.library.builder.ViewHolderAnimatorBuilder;
import recyclerview.spring.com.library.helper.AnimatorLoader;

public class DemoActivity extends AppCompatActivity {

    @Bind(R.id.demoRecyclerView)
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @OnClick(R.id.basicAnimationButton)
    protected void basicAnimation(){
        Animator anim = AnimatorLoader.loadAnimator(this, R.animator.fade_in_animator);

        ViewHolderAnimator animator = new ViewHolderAnimatorBuilder()
                .addObjectAnimator(anim)
                        .create();

        DummyRecyclerAdapter dummyRecyclerAdapter = new DummyRecyclerAdapter(this);
        RecyclerViewAnimationAdapter recyclerViewAnimationAdapter = new RecyclerViewAnimationAdapter(dummyRecyclerAdapter);
        recyclerViewAnimationAdapter.addAdapterAnimator(animator);
        recyclerView.setAdapter(recyclerViewAnimationAdapter);
    }
}
