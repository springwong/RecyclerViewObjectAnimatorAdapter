package recyclerview.spring.com.demo.sample;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import recyclerview.spring.com.demo.BuildConfig;
import recyclerview.spring.com.demo.R;

/**
 * Created by Spring80917 on 30/01/2016.
 */
public class DummyViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.demoTextView)
    public TextView textView;
    @Bind(R.id.demoImage)
    public ImageView imageView;
    @Bind(R.id.demoSeekBar)
    public SeekBar seekBar;
    public DummyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * input paramter have to match animator ofInt
     * @param text
     */
    public void setTextView(int text){
        textView.setText("Count:" + text);
        seekBar.setProgress(text);
    }

    /***
     * input paramter have to match animator ofFloat
     * @param offset
     */
    public void setImageViewX(float offset){
        imageView.setX(offset);
    }

    /***
     * just a function for one shot call, receive type is offset
     * @param nothing
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setCircleReveal(int nothing){
        int cx = imageView.getWidth()/2;
        int cy = imageView.getHeight()/2;
        float finalRadius = (float)Math.hypot(cx, cy);
        if(imageView.isAttachedToWindow()){
            Animator animator = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0, finalRadius);
            animator.setDuration(500);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        }
    }
    //suggest to reset you layout in your own adapter
    public void reset(){
        textView.setText("Android");
        seekBar.setProgress(50);
    }
}
