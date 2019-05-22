package com.zhaofan.studaydemo.scrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.zhaofan.studaydemo.R;


/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/13
 * description:
 */
public class ItemDataAdapter extends RecyclerView.Adapter<ItemDataAdapter.ViewHolder> {
    private static final String TAG = "ItemDataAdapter";
    Context context;
    ItemDataAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ItemDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDataAdapter.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ScrollView scrollView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scrollView = itemView.findViewById(R.id.scrollView);
        }


        public boolean isTouchNSV(int x,int y){
            int[] position = new int[2];
            scrollView.getLocationOnScreen(position);
            int width = scrollView.getWidth();
            int height = scrollView.getHeight();
            Log.d(TAG,"width>>>>"+width+">>>>>height"+height);
            Log.d(TAG,"x>>>>>"+x+">>>>>>y"+y);
            Log.d(TAG,"position[0]>>>>>"+position[0] );
            Log.d(TAG,"position[0]+width>>>>>"+position[0]+width);
            Log.d(TAG,"position[1]>>>>>"+position[1]);
            Log.d(TAG,"position[1]+height>>>>>"+position[1]+height);
            return x>=position[0] && x<=position[0]+width && y>=position[1] && y<=position[1]+height;
        }
    }
}
