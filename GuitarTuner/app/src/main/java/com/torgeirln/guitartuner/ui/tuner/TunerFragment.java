package com.torgeirln.guitartuner.ui.tuner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.torgeirln.guitartuner.databinding.FragmentTunerBinding;
import com.torgeirln.guitartuner.domain.models.Frequency;
import com.torgeirln.guitartuner.ui.base.BaseFragment;

import timber.log.Timber;

public class TunerFragment extends BaseFragment {
    private FragmentTunerBinding binding;
    private TunerViewModel viewModel;

    @NonNull
    public static TunerFragment getInstance() {
        return new TunerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(TunerViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentTunerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCurrentFrequencyLiveData().observe(getViewLifecycleOwner(), this::onFrequencyChanged);
        viewModel.getTargetFrequencyLiveData().observe(getViewLifecycleOwner(), this::onTargetChanged);
    }

    private void onFrequencyChanged(@NonNull Frequency frequency) {
        Timber.d("onFrequencyChanged: %s", viewModel);
        binding.tunerIndicator.setValue(frequency.value);
    }

    private void onTargetChanged(@NonNull Frequency frequency) {
        binding.tunerIndicator.setTarget(frequency.value);
    }

}