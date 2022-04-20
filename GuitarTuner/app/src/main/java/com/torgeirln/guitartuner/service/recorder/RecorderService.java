package com.torgeirln.guitartuner.service.recorder;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.torgeirln.guitartuner.domain.contracts.IRecorderService;

import javax.inject.Singleton;

import io.reactivex.rxjava3.annotations.NonNull;

@Singleton
public class RecorderService implements IRecorderService {
    public static final int AUDIO_SOURCE = MediaRecorder.AudioSource.DEFAULT;
    public static final int SAMPLING_RATE_IN_HZ = 44100;
    public static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    public static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    public static final int BUFFER_SIZE_FACTOR = 2; // Larger value = less drop, but more memory
    public static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLING_RATE_IN_HZ,
            CHANNEL_CONFIG, AUDIO_FORMAT) * BUFFER_SIZE_FACTOR;

    private final AudioRecord audioRecord;

    public RecorderService(@NonNull AudioRecord audioRecord) {
        this.audioRecord = audioRecord;
    }

    @Override
    public void startRecording() {
        audioRecord.startRecording();
    }

    @Override
    public void stopRecording() {
        audioRecord.stop();
    }
}