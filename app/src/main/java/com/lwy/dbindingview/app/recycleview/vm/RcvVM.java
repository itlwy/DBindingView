package com.lwy.dbindingview.app.recycleview.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwy.dbindingview.ItemBinding;
import com.lwy.dbindingview.adapter.BindingRecyclerViewAdapter;
import com.lwy.dbindingview.adapter.wrapper.EmptyRecyclerViewAdapter;
import com.lwy.dbindingview.app.R;
import com.lwy.dbindingview.collections.MergeObservableList;
import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action0;
import com.lwy.dbindingview.itembindings.OnItemBindClass;


public class RcvVM {
    private boolean checkable;  // for now,it's useless

    private Handler mHandler = new Handler();

    /***** control view's some behaviour *****/
    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableField<Boolean> isRefreshing = new ObservableField<>();

    }


    public final ReplyCommand onRefreshCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (items.size() > 0)
                        items.clear();
                    for (int i = 0; i < 3; i++) {
                        items.add(new ItemVM(i, checkable));
                    }
                    viewStyle.isRefreshing.set(false);
                }
            }, 3000);

        }
    });

    public final FooterVM footerVM = new FooterVM(new ReplyCommand(new Action0() {

        @Override
        public void call() {
            loadMore();
        }

    }));

    public final ObservableList<ItemVM> items = new ObservableArrayList<>();


    /**
     * Custom adapter that logs calls.
     */
    public BindingRecyclerViewAdapter<Object> adapter;

    public RcvVM() {
        EmptyRecyclerViewAdapter<Object> wrapper = new EmptyRecyclerViewAdapter<>();
        wrapper.setEmptyView(R.layout.empty);
        adapter = wrapper;
//        for (int i = 0; i < 15; i++) {
//            items.add(new ItemVM(i, checkable));
//        }
    }

    private void loadMore() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                footerVM.switchLoading(false);
                if (items.size() > 20)
                    return;
                for (int i = 0; i < 15; i++) {
                    addItem();
                }
                super.onPostExecute(integer);
            }
        }.execute();
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    /**
     * Items merged with a header on top and footer on bottom.
     */
    public final MergeObservableList<Object> headerFooterItems = new MergeObservableList<>()
//            .insertItem("Header")
            .insertList(items)
            .insertItem(footerVM);


    /**
     * Binds a homogeneous list of items to a layout.
     */
    public final ItemBinding<ItemVM> singleItem = ItemBinding.of(com.lwy.dbindingview.BR.item, R.layout.item);

    /**
     * Binds multiple items types to different layouts based on class. This could have also be
     * written manually as
     * <pre>{@code
     * public final OnItemBind<Object> multipleItems = new OnItemBind<Object>() {
     *     @Override
     *     public void onItemBind(ItemBinding itemBinding, int position, Object item) {
     *         if (String.class.equals(item.getClass())) {
     *             itemBinding.set(BR.item, R.layout.item_header_footer);
     *         } else if (ItemVM.class.equals(item.getClass())) {
     *             itemBinding.set(BR.item, R.layout.item);
     *         }
     *     }
     * };
     * }</pre>
     */
    public final ItemBinding<Object> multipleItems = ItemBinding.of(new OnItemBindClass<>()
            .map(FooterVM.class, com.lwy.dbindingview.app.BR.footerVM, R.layout.default_loading)
            .map(String.class, com.lwy.dbindingview.app.BR.item, R.layout.item_header_footer)
            .map(ItemVM.class, com.lwy.dbindingview.app.BR.item, R.layout.item));


    /**
     * Define page titles for a ViewPager
     */
//    public final BindingViewPagerAdapter.PageTitles<ItemVM> pageTitles = new BindingViewPagerAdapter.PageTitles<ItemVM>() {
//        @Override
//        public CharSequence getPageTitle(int position, ItemVM item) {
//            return "Item " + (item.getIndex() + 1);
//        }
//    };

    /**
     * Custom view holders for RecyclerView
     */
    public final BindingRecyclerViewAdapter.ViewHolderFactory viewHolder = new BindingRecyclerViewAdapter.ViewHolderFactory() {
        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
            return new MyAwesomeViewHolder(binding.getRoot());
        }
    };

    private static class MyAwesomeViewHolder extends RecyclerView.ViewHolder {
        public MyAwesomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addItem() {
        items.add(new ItemVM(items.size(), checkable));
    }

    public void removeItem() {
        if (items.size() > 1) {
            items.remove(items.size() - 1);
        }
    }
}
