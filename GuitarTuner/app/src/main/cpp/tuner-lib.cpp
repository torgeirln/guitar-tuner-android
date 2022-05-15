#include <jni.h>
#include "AudioEngine.h"
#include "Utils.h"

static AudioEngine *audioEngine = new AudioEngine();

extern "C"
JNIEXPORT void JNICALL
Java_com_torgeirln_guitartuner_service_tuner_TunerService_startAudioEngineNative(
        JNIEnv *env, jobject thiz
) {
    audioEngine->start();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_torgeirln_guitartuner_service_tuner_TunerService_stopAudioEngineNative(
        JNIEnv *env, jobject thiz
) {
    audioEngine->stop();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_torgeirln_guitartuner_service_tuner_TunerService_getCurrentFrequencyNative(
        JNIEnv *env, jobject thiz, jobject byte_buffer
) {

    auto *data = (uint8_t *) env->GetDirectBufferAddress(byte_buffer);
    int index = 0;
    writeBuffer(data[index], 441.3f);

}