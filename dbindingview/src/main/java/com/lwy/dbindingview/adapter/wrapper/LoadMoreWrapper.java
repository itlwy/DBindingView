package com.lwy.dbindingview.adapter.wrapper;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lwy.dbindingview.ItemBinding;
import com.lwy.dbindingview.adapter.BindingRecyclerViewAdapter;
import com.lwy.dbindingview.adapter.ViewHolder;
import com.lwy.dbindingview.utils.WrapperUtils;

import java.util.List;

/**
 * Created by lwy on 2018/8/27.
 */

public class LoadMoreWrapper<T> extends BindingRecyclerViewAdapter<T> {
    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;

    private BindingRecyclerViewAdapter mInnerAdapter;
    private View mLoadMoreView;
    private int mLoadMoreLayoutId;


    public LoadMoreWrapper(BindingRecyclerViewAdapter innerAdapter) {
        mInnerAdapter = innerAdapter;
    }

    private boolean hasLoadMore() {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }


    @Override
    public void setItems(@Nullable List<T> items) {
        mInnerAdapter.setItems(items);
        super.setItems(items);
    }

    @Override
    public void setItemIds(@Nullable ItemIds<? super T> itemIds) {
        mInnerAdapter.setItemIds(itemIds);
    }

    @Override
    public void setItemBinding(ItemBinding<T> itemBinding) {
        mInnerAdapter.setItemBinding(itemBinding);
    }

    @Override
    public void setViewHolderFactory(@Nullable ViewHolderFactory factory) {
        mInnerAdapter.setViewHolderFactory(factory);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            if (mLoadMoreView != null) {
                holder = ViewHolder.createViewHolder(parent.getContext(), mLoadMoreView);
            } else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            }
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isShowLoadMore(holder.getLayoutPosition())) {
            WrapperUtils.setFullSpan(holder);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (isShowLoadMore(position)) {
//            if (mOnLoadMoreListener != null) {
//                mOnLoadMoreListener.onLoadMoreRequested();
//            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    public LoadMoreWrapper setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public LoadMoreWrapper setLoadMoreView(int layoutId) {
        mLoadMoreLayoutId = layoutId;
        return this;
    }

    protected OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public interface OnLoadMoreListener {
        void onLoadMoreRequested();
    }
}
