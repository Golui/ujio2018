LOCAL_PATH := $(call my-dir)/../
include $(CLEAR_VARS)
LOCAL_MODULE := jurpizza-native

INCLUDE := include
SOURCE := src

LOCAL_CPPFLAGS += -D_DEBUG -MMD -MP -Werror -Wall -g -pedantic -O0 -std=c++14 -include General.hpp

LOCAL_C_INCLUDES := $(INCLUDE) $(INCLUDE)/lib

LOCAL_LDLIBS := -lm

LOCAL_SRC_FILES := $(shell find $(SOURCE) -name "*.cpp")

include $(BUILD_SHARED_LIBRARY)
