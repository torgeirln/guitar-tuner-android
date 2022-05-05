package com.torgeirln.guitartuner.ui;

import android.Manifest;
import android.os.Bundle;

import com.torgeirln.guitartuner.R;
import com.torgeirln.guitartuner.databinding.ActivityMainBinding;
import com.torgeirln.guitartuner.ui.base.PermissionsActivity;
import com.torgeirln.guitartuner.ui.tuner.TunerFragment;

import io.reactivex.rxjava3.annotations.NonNull;
import timber.log.Timber;

public class MainActivity extends PermissionsActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 1;

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = getViewModel(MainViewModel.class);

        if (savedInstanceState == null) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        super.checkPermissions(new PermissionsRequest(
                PERMISSIONS_REQUEST_CODE,
                Manifest.permission.RECORD_AUDIO,
                this::onPermissionReceived
        ));
    }

    private void onPermissionReceived(@NonNull String permission, boolean permissionGranted) {
        Timber.d("requestPermissions: Permission %s, granted = %s", permission, permissionGranted);
        if (permission.equals(Manifest.permission.RECORD_AUDIO)) {
            viewModel.startTuner();
            showFragment(TunerFragment.getInstance(), R.id.fragment_container);
        }
    }

}