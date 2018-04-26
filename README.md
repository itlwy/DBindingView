<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. Screenshots</a></li>
<li><a href="#sec-2">2. How to import</a>
<ul>
<li><a href="#sec-2-1">2.1. step1</a></li>
<li><a href="#sec-2-2">2.2. step2</a></li>
</ul>
</li>
<li><a href="#sec-3">3. How to use</a>
<ul>
<li><a href="#sec-3-1">3.1. 一般控件</a>
<ul>
<li><a href="#sec-3-1-1">3.1.1. 涉及到的类</a></li>
<li><a href="#sec-3-1-2">3.1.2. 如上面的动态图,目前展示了基本控件的进行一些初始化的封装,目的是将一些繁琐的初始化操作自动化掉</a></li>
</ul>
</li>
<li><a href="#sec-3-2">3.2. RecycleView</a>
<ul>
<li><a href="#sec-3-2-1">3.2.1. 属性</a></li>
<li><a href="#sec-3-2-2">3.2.2. 代码说明</a></li>
</ul>
</li>
</ul>
</li>
</ul>
</div>
</div>


# 一个对databinding的常用封装库,包含基础控件及recycleview等


# Screenshots<a id="sec-1" name="sec-1"></a>

![normalWidget](https://github.com/itlwy/DBindingView/blob/master/pic/dbindingview.gif) ![recycleview](https://github.com/itlwy/DBindingView/blob/master/pic/dbindingview1.gif)

# How to import<a id="sec-2" name="sec-2"></a>

## step1<a id="sec-2-1" name="sec-2-1"></a>

Add the JitPack repository to your build file

    allprojects {
                            repositories {
                                    ...
                                    maven { url 'https://jitpack.io' }
                            }
                    }

## step2<a id="sec-2-2" name="sec-2-2"></a>

Add the dependency

    dependencies {
                    compile 'com.github.itlwy:DBindingView:v1.0.1'
            }

# How to use<a id="sec-3" name="sec-3"></a>

## 一般控件<a id="sec-3-1" name="sec-3-1"></a>

### 涉及到的类<a id="sec-3-1-1" name="sec-3-1-1"></a>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="left" />

<col  class="left" />

<col  class="left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="left">类名</th>
<th scope="col" class="left">包名</th>
<th scope="col" class="left">说明</th>
</tr>
</thead>

<tbody>
<tr>
<td class="left">KeyValue</td>
<td class="left">com.lwy.dbindingview.data</td>
<td class="left">存储key(Integer)-value(String)</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">主要用于spinner、checkbox、radiogroup</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">等,实现数据源的key-label模式</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="left">BindingSpinner</td>
<td class="left">com.lwy.dbindingview.bindingadapter.spinner</td>
<td class="left">封装的双向绑定自定义Spinner</td>
</tr>


<tr>
<td class="left">DataBindingRadioGroup</td>
<td class="left">com.lwy.dbindingview.bindingadapter.radiogroup</td>
<td class="left">封装的双向绑定自定义RadioGroup</td>
</tr>


<tr>
<td class="left">DataBindingRadioButton</td>
<td class="left">com.lwy.dbindingview.bindingadapter.radiogroup</td>
<td class="left">封装的双向绑定自定义RadioButton</td>
</tr>


<tr>
<td class="left">BindingCheckGroup</td>
<td class="left">com.lwy.dbindingview.bindingadapter.checkbox</td>
<td class="left">封装的双向绑定自定义LinearLayout</td>
</tr>


<tr>
<td class="left">BindingCheckBox</td>
<td class="left">com.lwy.dbindingview.bindingadapter.checkbox</td>
<td class="left">封装的双向绑定自定义CheckBox</td>
</tr>
</tbody>
</table>

### 如上面的动态图,目前展示了基本控件的进行一些初始化的封装,目的是将一些繁琐的初始化操作自动化掉<a id="sec-3-1-2" name="sec-3-1-2"></a>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="left" />

<col  class="left" />

<col  class="left" />

<col  class="left" />

<col  class="left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="left">控件</th>
<th scope="col" class="left">自定义属性</th>
<th scope="col" class="left">值类型</th>
<th scope="col" class="left">值</th>
<th scope="col" class="left">说明</th>
</tr>
</thead>

<tbody>
<tr>
<td class="left">BindingSpinner</td>
<td class="left">selectedValue</td>
<td class="left">KeyValue</td>
<td class="left">&#xa0;</td>
<td class="left">绑定选中的值</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">spinneritems</td>
<td class="left">List&lt;KeyValue&gt;</td>
<td class="left">&#xa0;</td>
<td class="left">spinner的适配器数据源</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="left">DataBindingRadioGroup</td>
<td class="left">checkedValue</td>
<td class="left">KeyValue</td>
<td class="left">&#xa0;</td>
<td class="left">绑定RadioGroup选中的值</td>
</tr>


<tr>
<td class="left">DataBindingRadioButton</td>
<td class="left">value</td>
<td class="left">KeyValue</td>
<td class="left">&#xa0;</td>
<td class="left">初始化RadioButton的值</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="left">BindingCheckGroup</td>
<td class="left">selectedValues</td>
<td class="left">String</td>
<td class="left">&#xa0;</td>
<td class="left">存储checkbox选中的值,默认用,分割</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">separator</td>
<td class="left">String</td>
<td class="left">&#xa0;</td>
<td class="left">设置checkbox选中项的拼接符号</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="left">ImageView</td>
<td class="left">uri</td>
<td class="left">String or ObservableField<String></td>
<td class="left">&#xa0;</td>
<td class="left">图片的url,用的加载框架是glide</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">placeholderImageRes</td>
<td class="left">int</td>
<td class="left">eg:R.mipmap.ic\_launcher</td>
<td class="left">占位图</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">request\_width、request\_width</td>
<td class="left">int</td>
<td class="left">&#xa0;</td>
<td class="left">设置图片的大小,不设置默认用view的大小,2个属性必修同时设置才有效</td>
</tr>
</tbody>

<tbody>
<tr>
<td class="left">View</td>
<td class="left">clickCommand</td>
<td class="left">ReplyCommand</td>
<td class="left">&#xa0;</td>
<td class="left">点击事件触发的命令</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">display</td>
<td class="left">boolean</td>
<td class="left">&#xa0;</td>
<td class="left">控制view的Visibility</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
</tr>
</tbody>
</table>

## RecycleView<a id="sec-3-2" name="sec-3-2"></a>

### 属性<a id="sec-3-2-1" name="sec-3-2-1"></a>

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="left" />

<col  class="left" />

<col  class="left" />

<col  class="left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="left">属性</th>
<th scope="col" class="left">值类型</th>
<th scope="col" class="left">值</th>
<th scope="col" class="left">说明</th>
</tr>
</thead>

<tbody>
<tr>
<td class="left">itemBinding</td>
<td class="left">ItemBinding</td>
<td class="left">&#xa0;</td>
<td class="left">必填,item的布局和变量的绑定关系包装类</td>
</tr>


<tr>
<td class="left">items</td>
<td class="left">List&lt;T&gt;</td>
<td class="left">&#xa0;</td>
<td class="left">必填,数据源</td>
</tr>


<tr>
<td class="left">adapter</td>
<td class="left">BindingRecyclerViewAdapter&lt;T&gt;</td>
<td class="left">&#xa0;</td>
<td class="left">选填,可继承BindingRecyclerViewAdapter自定义适配器</td>
</tr>


<tr>
<td class="left">itemIds</td>
<td class="left">BindingRecyclerViewAdapter.ItemIds&lt;? super T&gt;</td>
<td class="left">&#xa0;</td>
<td class="left">选填,不设置则默认使用position</td>
</tr>


<tr>
<td class="left">viewHolder</td>
<td class="left">BindingRecyclerViewAdapter.ViewHolderFactory</td>
<td class="left">&#xa0;</td>
<td class="left">选填,可继承以实现自定义ViewHolder</td>
</tr>


<tr>
<td class="left">onItemClick</td>
<td class="left">BindingRecyclerViewAdapter.OnItemClickListener</td>
<td class="left">&#xa0;</td>
<td class="left">item点击事件</td>
</tr>


<tr>
<td class="left">layoutManager</td>
<td class="left">LayoutManagers.LayoutManagerFactory</td>
<td class="left">&#xa0;</td>
<td class="left">必填,布局方式,如线性:LayoutManagers.linear()</td>
</tr>


<tr>
<td class="left">footerVM</td>
<td class="left">RcVFooterVM</td>
<td class="left">&#xa0;</td>
<td class="left">选填,上拉加载更多的viewmodel,具体布局等特性继承RcVFooterVM扩充</td>
</tr>


<tr>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
<td class="left">&#xa0;</td>
</tr>
</tbody>
</table>

### 代码说明<a id="sec-3-2-2" name="sec-3-2-2"></a>

1.  单个布局的简单列表代码片段

    1、定义viewmodel
    
        public class ItemVM extends BaseObservable {
            public final boolean checkable; // for now,it's useless
            @Bindable
            private int index;
            @Bindable
            private boolean checked;
        
            public ItemVM(int index, boolean checkable) {
                this.index = index;
                this.checkable = checkable;
            }
        
            public int getIndex() {
                return index;
            }
        
            public boolean isChecked() {
                return checked;
            }
        
            public boolean onToggleChecked(View v) {
                if (!checkable) {
                    return false;
                }
                checked = !checked;
        //        notifyPropertyChanged(com.lwy.dbindingview.BR.checked);
                return true;
            }
        }
    
    2、定义Item布局文件
    
        <layout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools">
        
            <data>
        
                <variable
                    name="item"
                    type="com.lwy.dbindingview.recycleview.vm.ItemVM" />
        
                <import type="android.view.View" />
            </data>
        
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="?selectableItemBackground"
                android:onLongClickListener="@{item::onToggleChecked}"
                android:longClickable="@{item.checkable}"
                android:orientation="horizontal">
        
                <TextView
                    style="@style/TextAppearance.AppCompat.Body1"
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:padding="16dp"
                    android:text='@{"Item " + (item.index + 1)}'
                    tools:text="Item 1" />
        
                <ImageView
                    android:layout_width="48dp"
                    android:layout_height="48dp"
                    android:src="@mipmap/ic_action_check"
                    android:visibility="@{item.checked ? View.VISIBLE : View.GONE}" />
            </LinearLayout>
        </layout>
    
    3、创建layout和viewmodel变量的绑定关系包装类
    
        public final ItemBinding<ItemVM> singleItem = ItemBinding.of(com.lwy.dbindingview.BR.item, R.layout.item);
    
    4、创建数据源
    
        public final ObservableList<ItemVM> items = new ObservableArrayList<>();
    
    5、设置RecycleView的属性
    
        ...
         <android.support.v7.widget.RecyclerView
                       android:id="@+id/list"
                       android:layout_width="match_parent"
                       android:layout_height="match_parent"
                       app:itemBinding="@{viewmodel.singleItem}"
                       app:items="@{viewmodel.items}"
                       app:layoutManager="@{LayoutManagers.linear()}"/>
         ...

2.  复杂布局代码片段

    1、定义viewmodel(同上)
    
    2、定义Item布局文件R.layout.item(同上)
    
    3、创建footer的viewmodel
    
        public class FooterVM extends RcVFooterVM {
        
            private ReplyCommand<Integer> callback;
            public final ObservableField<String> noMoreTip = new ObservableField<>();
            /*
                state : 0 loading
                state : 1 idle
             */
            public final ObservableField<Integer> state = new ObservableField<>();
        
        
            public FooterVM(ReplyCommand<Integer> callback) {
                super();
                this.callback = callback;
                state.set(1);
                noMoreTip.set("暂无更多");
            }
        
            @Override
            protected ReplyCommand<Integer> geneOnLoadMoreCommand() {
                return new ReplyCommand<>(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        FooterVM.this.callback.execute(integer);
        //                switchLoading(true);
                    }
                });
            }
        
            @Override
            public void switchLoading(boolean flag) {
                if (flag) {
                    state.set(0);
                } else {
                    state.set(1);
                }
                super.switchLoading(flag);
            }
        }
    
    4、创建footer的布局文件R.layout.item\_header\_footer
    
        <?xml version="1.0" encoding="utf-8"?>
        <layout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools">
        
            <data>
        
                <variable
                    name="item"
                    type="String" />
            </data>
        
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">
        
                <TextView
                    style="@style/TextAppearance.AppCompat.Body1"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="#dcdcdc"
                    android:padding="16dp"
                    android:text="@{item}"
                    tools:text="Header" />
            </LinearLayout>
        </layout>
    
    5、创建layout和viewmodel变量的绑定关系包装类
    
        // 这里根据class类型来控制不同的item
        public final ItemBinding<Object> multipleItems = ItemBinding.of(new OnItemBindClass<>()
                    .map(FooterVM.class, BR.footerVM, R.layout.default_loading)
                    .map(String.class, com.lwy.dbindingview.BR.item, R.layout.item_header_footer)
                    .map(ItemVM.class, com.lwy.dbindingview.BR.item, R.layout.item));
    
    6、创建数据源
    
        public final FooterVM footerVM = new FooterVM(new ReplyCommand<Integer>(new Action1<Integer>() {
        
                @Override
                public void call(Integer integer) {
                    // 异步执行加载数据 完了需要调用 "footerVM.switchLoading(false)" 取消加载状态
                }
            }));
        
        public final ObservableList<ItemVM> items = new ObservableArrayList<>();
        public final MergeObservableList<Object> headerFooterItems = new MergeObservableList<>()
                    .insertItem("Header")
                    .insertList(items)
                    .insertItem(footerVM);
    
    5、设置RecycleView的属性
    
        ...
        <android.support.v7.widget.RecyclerView
                       android:id="@+id/list"
                       android:layout_width="match_parent"
                       android:layout_height="match_parent"
                       app:footerVM="@{viewmodel.footerVM}"
                       app:itemBinding="@{viewmodel.multipleItems}"
                       app:items="@{viewmodel.headerFooterItems}"
                       app:layoutManager="@{LayoutManagers.linear()}"/>
        ...