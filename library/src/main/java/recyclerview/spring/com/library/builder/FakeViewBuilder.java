package recyclerview.spring.com.library.builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Spring80917 on 31/01/2016.
 */
public class FakeViewBuilder {
    private View fakeView;
    private ViewGroup parentView;
    private DisplayMetrics displayMetrics;

    public FakeViewBuilder(Context context) {
        displayMetrics = context.getResources().getDisplayMetrics();
        fakeView = new View(context);
        parentView = new FrameLayout(context);
        parentView.addView(fakeView);
    }
    public View create(){
        return fakeView;
    }
    public FakeViewBuilder setView(View view){
        fakeView = view;
        return this;
    }
    public FakeViewBuilder setParentView(ViewGroup viewGroup){
        parentView.removeView(fakeView);
        viewGroup.addView(fakeView);
        parentView = null;
        parentView = viewGroup;
        return this;
    }

    public FakeViewBuilder setParentRect(int left, int top, int right, int bottom){
        parentView.setLeft(left);
        parentView.setRight(right);
        parentView.setTop(top);
        parentView.setBottom(bottom);
        return this;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FakeViewBuilder setParentPosition(float x, float y, float z){
        parentView.setX(x);
        parentView.setY(y);
        parentView.setZ(z);
        return this;
    }
    public FakeViewBuilder setParentPosition(float x, float y){
        parentView.setX(x);
        parentView.setY(y);
        return this;
    }
    public FakeViewBuilder setParentAlpha(float alpha){
        parentView.setAlpha(alpha);
        return this;
    }
    public FakeViewBuilder setParentPivot(float pivotX, float pivotY){
        parentView.setPivotX(pivotX);
        parentView.setPivotY(pivotY);
        return this;
    }
    public FakeViewBuilder setParentPadding(int left, int top, int right, int bottom){
        parentView.setPadding(left, top, right, bottom);
        return this;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FakeViewBuilder setParentTranslation(float translationX, float translationY, float translationZ){
        parentView.setTranslationX(translationX);
        parentView.setTranslationY(translationY);
        parentView.setTranslationZ(translationZ);
        return this;
    }
    public FakeViewBuilder setParentTranslation(float translationX, float translationY){
        parentView.setTranslationX(translationX);
        parentView.setTranslationY(translationY);
        return this;
    }
    public FakeViewBuilder setParentSizeByFullScreenPercent(float widthPercent, float heightPercent){
        parentView.setLeft(0);
        parentView.setRight((int) (displayMetrics.widthPixels * widthPercent));
        parentView.setTop(0);
        parentView.setBottom((int) (displayMetrics.heightPixels * heightPercent));
        return this;
    }
    public FakeViewBuilder setViewRect(int left, int top, int right, int bottom){
        fakeView.setLeft(left);
        fakeView.setRight(right);
        fakeView.setTop(top);
        fakeView.setBottom(bottom);
        return this;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FakeViewBuilder setViewPosition(float x, float y, float z){
        fakeView.setX(x);
        fakeView.setY(y);
        fakeView.setZ(z);
        return this;
    }
    public FakeViewBuilder setViewPosition(float x, float y){
        fakeView.setX(x);
        fakeView.setY(y);
        return this;
    }
    public FakeViewBuilder setViewAlpha(float alpha){
        fakeView.setAlpha(alpha);
        return this;
    }
    public FakeViewBuilder setViewPivot(float pivotX, float pivotY){
        fakeView.setPivotX(pivotX);
        fakeView.setPivotY(pivotY);
        return this;
    }
    public FakeViewBuilder setViewPadding(int left, int top, int right, int bottom){
        fakeView.setPadding(left, top, right, bottom);
        return this;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FakeViewBuilder setViewTranslation(float translationX, float translationY, float translationZ){
        fakeView.setTranslationX(translationX);
        fakeView.setTranslationY(translationY);
        fakeView.setTranslationZ(translationZ);
        return this;
    }
    public FakeViewBuilder setViewTranslation(float translationX, float translationY){
        fakeView.setTranslationX(translationX);
        fakeView.setTranslationY(translationY);
        return this;
    }

    public FakeViewBuilder setViewSizeByFullScreenPercent(float widthPercent, float heightPercent){
        fakeView.setLeft(0);
        fakeView.setRight((int) (displayMetrics.widthPixels * widthPercent / 100));
        fakeView.setTop(0);
        fakeView.setBottom((int)(displayMetrics.heightPixels * heightPercent / 100));
        return this;
    }
}
