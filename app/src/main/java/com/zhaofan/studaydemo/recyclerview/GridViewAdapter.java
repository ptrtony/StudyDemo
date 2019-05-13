package com.zhaofan.studaydemo.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhaofan.studaydemo.R;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/6
 * description:
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder> {
    private Context context;
    GridViewAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GridViewHolder(LayoutInflater.from(context).inflate(R.layout.ietm_grid_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        if (i % 3== 0){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }else if (i % 4 == 0){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }else if (i % 5 ==0 ){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_0905c2));
        }else if (i % 6 ==0){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_d408d1));
        }else if (i % 8 ==0){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_e4c203));
        }else if (i % 10 ==0){
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_09dcd5));
        }else{
            gridViewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.color_bbe70a));
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    static class GridViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearlayout);
        }
    }
}
