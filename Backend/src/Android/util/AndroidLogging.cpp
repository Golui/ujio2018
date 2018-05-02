// Copyright (c) 2018 Gabriela Rogowska, Martin Jurczyk, Grzegorz "Golui" Łazarski

#include "util/Logging.hpp"

#include <iostream>
#include <string>
#include <chrono>
#include <iomanip>

void logV(Logging::Priority p, const char* fmt, va_list va)
{
	static const char* pris[] = {
		"VERB",
		"DEBG",
		"INFO",
		"WARN",
		"ERRO",
		"CRIT"
	};
	auto t = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
	std::cout  << "[" << std::put_time(std::localtime(&t),"%T.") << "]" << "[" << pris[p] << "] ";
	vprintf(fmt, va);
	std::cout << std::endl;
}

void Logging::log(Priority pri, const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	// In sync with LogPriority
	//static const SDL_LogPriority pris[] = {SDL_LOG_PRIORITY_VERBOSE, SDL_LOG_PRIORITY_DEBUG, SDL_LOG_PRIORITY_INFO, SDL_LOG_PRIORITY_WARN, SDL_LOG_PRIORITY_ERROR, SDL_LOG_PRIORITY_CRITICAL};
	logV(pri, fmt, va);
	va_end(va);
}

void Logging::verbose(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::VERBOSE, fmt, va);
	va_end(va);
}

void Logging::debug(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::DEBUG, fmt, va);
	va_end(va);
}

void Logging::info(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::INFO, fmt, va);
	va_end(va);
}

void Logging::warn(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::WARN, fmt, va);
	va_end(va);
}

void Logging::error(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::ERROR, fmt, va);
	va_end(va);
}

void Logging::critical(const char* fmt, ...)
{
	va_list va;
	va_start(va, fmt);
	logV(Priority::CRITICAL, fmt, va);
	va_end(va);
}

// void Logging::logSdlError()
// {
// 	error("(SDL) %s", SDL_GetError());
// }
