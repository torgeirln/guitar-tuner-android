#ifndef GUITARTUNER_UTILS_H
#include <string>
#define GUITARTUNER_UTILS_H

template <typename T>
uint32_t writeBuffer(uint8_t& dst, const T& src) {
    std::memcpy(&dst, &src, sizeof(T));
    return sizeof(T);
}

#endif  // GUITARTUNER_UTILS_H