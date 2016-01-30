package recyclerview.spring.com.demo.sample;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import recyclerview.spring.com.demo.R;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class DummyViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.demoTextView)
    public TextView textView;
    @Bind(R.id.demoImage)
    public ImageView imageView;
    public DummyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setTextView(int text){
        textView.setText("Count:" + text);
    }
    public void setImageViewX(float offset){
        imageView.setX(offset);
    }
    public void setCircleReveal(int offset){
        int cx = imageView.getWidth()/2;
        int cy = imageView.getHeight()/2;
        float finalRadius = (float)Math.hypot(cx, cy);
        if(imageView.isAttachedToWindow()){
            Animator animator = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0, finalRadius);
            animator.setDuration(5000);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        }

    }
}
