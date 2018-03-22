// Copyright (c) 2016 Grzegorz "Golui" Łazarski

#pragma once

#include <stdio.h>
#include <string>

namespace Logging
{
	enum Priority
	{
		VERBOSE = 0,
		DEBUG,
		INFO,
		WARN,
		ERROR,
		CRITICAL
	};

	void verbose(const char* fmt, ...);

	void debug(const char* fmt, ...);

	void info(const char* fmt, ...);

	void warn(const char* fmt, ...);

	void error(const char* fmt, ...);

	void critical(const char* fmt, ...);

	void log(Priority p, const char* fmt, ...);
} // namespace Logging
