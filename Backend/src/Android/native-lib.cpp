#include <jni.h>
#include <string>
#include <iostream>
#include "JurPizza.hpp"

extern "C" JNIEXPORT jstring

JNICALL
Java_io2018_ii_uj_edu_pl_jurpizza_LaunchActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
