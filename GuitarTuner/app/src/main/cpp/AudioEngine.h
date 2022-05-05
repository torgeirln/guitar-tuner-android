
#ifndef GUITARTUNER_AUDIOENGINE_H
#include <aaudio/AAudio.h>
#include <android/log.h>
#include "Oscilloscope.h"
#define GUITARTUNER_AUDIOENGINE_H

class AudioEngine {
public:
    bool start();
    void stop();
    void restart();

private:
    AAudioStream *stream_;
    Oscilloscope oscilloscope_;
};

#endif //GUITARTUNER_AUDIOENGINE_H
