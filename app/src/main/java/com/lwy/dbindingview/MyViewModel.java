package com.lwy.dbindingview;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwy.dbindingview.adapter.BindingRecyclerViewAdapter;
import com.lwy.dbindingview.collections.MergeObservableList;
import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action1;
import com.lwy.dbindingview.itembindings.OnItemBindClass;

public class MyViewModel {
    private boolean checkable;

    public final ObservableList<ItemViewModel> items = new ObservableArrayList<>();


    public final FooterViewModel footerViewModel = new FooterViewModel(new ReplyCommand<Integer>(new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            for (int i = 0; i < 3; i++) {
                addItem();
            }
            footerViewModel.switchLoading(false);
        }
    }));

    /**
     * Items merged with a header on top and footer on bottom.
     */
    public final MergeObservableList<Object> headerFooterItems = new MergeObservableList<>()
            .insertItem("Header")
            .insertList(items)
            .insertItem(footerViewModel);

    /**
     * Custom adapter that logs calls.
     */
    public final LoggingRecyclerViewAdapter<Object> adapter = new LoggingRecyclerViewAdapter<>();

    public MyViewModel() {
        for (int i = 0; i < 3; i++) {
            items.add(new ItemViewModel(i, checkable));
        }
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    /**
     * Binds a homogeneous list of items to a layout.
     */
    public final ItemBinding<ItemViewModel> singleItem = ItemBinding.of(com.lwy.dbindingview.BR.item, R.layout.item);

    /**
     * Binds multiple items types to different layouts based on class. This could have also be
     * written manually as
     * <pre>{@code
     * public final OnItemBind<Object> multipleItems = new OnItemBind<Object>() {
     *     @Override
     *     public void onItemBind(ItemBinding itemBinding, int position, Object item) {
     *         if (String.class.equals(item.getClass())) {
     *             itemBinding.set(BR.item, R.layout.item_header_footer);
     *         } else if (ItemViewModel.class.equals(item.getClass())) {
     *             itemBinding.set(BR.item, R.layout.item);
     *         }
     *     }
     * };
     * }</pre>
     */
    public final ItemBinding<Object> multipleItems = ItemBinding.of(new OnItemBindClass<>()
            .map(FooterViewModel.class, com.lwy.dbindingview.BR.footerViewModel, R.layout.default_loading)
            .map(String.class, com.lwy.dbindingview.BR.item, R.layout.item_header_footer)
            .map(ItemViewModel.class, com.lwy.dbindingview.BR.item, R.layout.item));


    /**
     * Define page titles for a ViewPager
     */
//    public final BindingViewPagerAdapter.PageTitles<ItemViewModel> pageTitles = new BindingViewPagerAdapter.PageTitles<ItemViewModel>() {
//        @Override
//        public CharSequence getPageTitle(int position, ItemViewModel item) {
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
        items.add(new ItemViewModel(items.size(), checkable));
    }

    public void removeItem() {
        if (items.size() > 1) {
            items.remove(items.size() - 1);
        }
    }
}
