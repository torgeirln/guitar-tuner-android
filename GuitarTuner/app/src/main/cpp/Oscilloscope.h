
#ifndef GUITARTUNER_OSCILLOSCOPE_H
#include <android/log.h>
#include <stdint.h>
#define GUITARTUNER_OSCILLOSCOPE_H

class Oscilloscope {
public:
    void render(float *audioData, int32_t numFrames);
    void setSampleRate(int32_t sampleRate);
private:
};

#endif //GUITARTUNER_OSCILLOSCOPE_H
