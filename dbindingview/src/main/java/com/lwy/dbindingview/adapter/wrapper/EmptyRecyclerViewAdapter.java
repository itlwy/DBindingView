package com.lwy.dbindingview.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lwy.dbindingview.adapter.BindingRecyclerViewAdapter;
import com.lwy.dbindingview.adapter.ViewHolder;
import com.lwy.dbindingview.utils.WrapperUtils;

/**
 * Created by lwy on 2018/8/27.
 */

public class EmptyRecyclerViewAdapter<T> extends BindingRecyclerViewAdapter<T> {
    public static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;

    //    private BindingRecyclerViewAdapter mInnerAdapter;
    private View mEmptyView;
    private int mEmptyLayoutId;


    public EmptyRecyclerViewAdapter() {
    }

    private boolean isEmpty() {
        return ((mEmptyView != null || mEmptyLayoutId != 0) && super.getItemCount() == 0);
//        return ((mEmptyView != null || mEmptyLayoutId != 0) && (getItems().size() == 0
//                || (getItems().size() == 1 && getItems().get(0) instanceof RcVFooterVM)));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isEmpty()) {
            RecyclerView.ViewHolder holder;
            if (mEmptyView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mEmptyView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mEmptyLayoutId);
            }
            return holder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isEmpty()) {
            WrapperUtils.setFullSpan(holder);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty()) {
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) return 1;
        return super.getItemCount();
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public void setEmptyView(int layoutId) {
        mEmptyLayoutId = layoutId;
    }


    public static ViewHolder createViewHolder(Context context, View itemView) {
        ViewHolder holder = new ViewHolder(context, itemView);
        return holder;
    }

    public static ViewHolder createViewHolder(Context context,
                                              ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolder holder = new ViewHolder(context, itemView);
        return holder;
    }

}
