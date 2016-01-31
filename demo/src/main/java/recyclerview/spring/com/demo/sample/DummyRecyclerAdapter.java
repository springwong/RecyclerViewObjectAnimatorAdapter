package recyclerview.spring.com.demo.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import recyclerview.spring.com.demo.R;

/**
 * Created by Spring80917 on 24/01/2016.
 */
public class DummyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private LayoutInflater mLayoutInflater;
    private Context context;

    public DummyRecyclerAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.demo_viewholder, null, false);
//        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(lp);
        DummyViewHolder holder = new DummyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 400;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        DummyViewHolder dummyViewHolder = (DummyViewHolder)holder;
        dummyViewHolder.reset();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
