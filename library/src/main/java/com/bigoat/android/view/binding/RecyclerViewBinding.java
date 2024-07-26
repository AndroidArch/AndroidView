//package com.bigoat.android.view.binding;
//
//import androidx.databinding.BindingAdapter;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//public class RecyclerViewBinding {
//
//    @BindingAdapter(value = {"itemLayout", "item", "layoutManager", "data"}, requireAll = false)
//    public static <T> void recyclerView(RecyclerView view, String itemLayout, String item, String layoutManager, List<T> data) {
//        RecyclerView.Adapter<?> adapter = view.getAdapter();
//
//        if (adapter!=null) {
//            if (adapter instanceof BaseBindAdapter) {
//                ((BaseBindAdapter) view.getAdapter()).setItems(data);
//                adapter.notifyDataSetChanged();
//                return;
//            }
//        }
//
//        if (itemLayout == null) return;
//
//        if (item == null) item = "item";
//
//        if (layoutManager == null || layoutManager.equals("vertical")) {
//            view.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
//        } else {
//            view.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
//        }
//
//        try {
//            Field layoutField = R.layout.class.getDeclaredField(itemLayout);
//            int layoutId = layoutField.getInt(null);
//
//            Field itemField = BR.class.getDeclaredField(item);
//            int itemData = itemField.getInt(null);
//            view.setAdapter(new BaseBindAdapter<>(layoutId, itemData, data));
//        } catch (Exception  e) {
//        }
//
//    }
//
//}
