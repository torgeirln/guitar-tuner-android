package com.torgeirln.guitartuner.app.injection.components;

import android.content.Context;

import com.torgeirln.guitartuner.app.injection.modules.ServiceModule;
import com.torgeirln.guitartuner.app.injection.modules.ViewModelModule;
import com.torgeirln.guitartuner.ui.base.BaseActivity;
import com.torgeirln.guitartuner.ui.base.BaseFragment;
import com.torgeirln.guitartuner.ui.tuner.TunerFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(TunerFragment tunerFragment);

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context applicationContext);
    }

}
