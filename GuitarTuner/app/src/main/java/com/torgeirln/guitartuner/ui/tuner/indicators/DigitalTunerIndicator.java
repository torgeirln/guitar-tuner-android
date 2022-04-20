package com.torgeirln.guitartuner.ui.tuner.indicators;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.torgeirln.guitartuner.databinding.TunerIndicatorDigitalBinding;

public class DigitalTunerIndicator extends ConstraintLayout {
    private static final String VALUE_UNKNOWN = "---";

    private TunerIndicatorDigitalBinding binding;

    public DigitalTunerIndicator(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DigitalTunerIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DigitalTunerIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        binding = TunerIndicatorDigitalBinding.inflate(LayoutInflater.from(context), this, true);
        binding.currentValue.setText(VALUE_UNKNOWN);
        binding.targetValue.setText(VALUE_UNKNOWN);
    }

    public void setValue(@Nullable Integer value) {
        String stringValue = value == null ? VALUE_UNKNOWN : String.valueOf(value);
        binding.currentValue.setText(stringValue);
    }

    public void setTarget(@Nullable Integer target) {
        String stringValue = target == null ? VALUE_UNKNOWN : String.valueOf(target);
        binding.targetValue.setText(stringValue);
    }

}