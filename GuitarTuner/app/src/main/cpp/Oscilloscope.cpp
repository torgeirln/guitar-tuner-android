
#include "Oscilloscope.h"

void Oscilloscope::render(float *audioData, int32_t numFrames) {
    __android_log_print(ANDROID_LOG_DEBUG, "Oscilloscope", "numFrames = %i", numFrames);
    for (int i = 0; i < numFrames; ++i) {
        __android_log_print(ANDROID_LOG_DEBUG, "Oscilloscope", "data[%i] = %f", i, audioData[i]);
    }
}

void Oscilloscope::setSampleRate(int32_t sampleRate) {
    __android_log_print(ANDROID_LOG_DEBUG, "Leif", "sampleRate = %i", sampleRate);
}
