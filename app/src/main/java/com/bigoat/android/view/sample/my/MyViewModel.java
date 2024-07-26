package com.bigoat.android.view.sample.my;

import com.bigoat.android.arch.BaseLiveData;
import com.bigoat.android.arch.BaseViewModel;


public abstract class MyViewModel extends BaseViewModel {
//    public RemoteDataSource dataSource = DataSourceFactory.get(RemoteDataSource.class);

    public BaseLiveData<Integer> layout = new BaseLiveData<>(0);

    public void setLayout(int layout) {
        this.layout.value(layout);
    }
}
