#include <jni.h>
#include "AudioEngine.h"

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
