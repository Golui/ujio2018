// Copyright (c) 2017 Grzegorz "Golui" Łazarski

#pragma once

#include "event/EventHub.hpp"

/**
 * Helper struct; this makes it easy to keep track of EventSubscriberHandles,
 * whilst making sure they keep their properties.
 */

struct EventHandleStore
{
	template <typename Handler> void subscribe(Handler&& hand, String channel = EventHub::DEFAULT_CHANNEL, EventHub::HandlerPriority pri = EventHub::NORMAL)
	{
		this->theStore.push_back(std::move(EventHub::subscribe(std::move(hand), channel, pri)));
	}

	template <typename Handler> void subscribe(Handler&& hand, EventHub::HandlerPriority pri)
	{
		this->theStore.push_back(std::move(EventHub::subscribe(std::move(hand), pri)));
	}

	template <typename Caller, typename Event> void subscribe(Caller* that, void (Caller::*hand)(Event&), String channel = EventHub::DEFAULT_CHANNEL, EventHub::HandlerPriority pri = EventHub::NORMAL)
	{
		this->theStore.push_back(std::move(EventHub::subscribe(std::move([that, hand](Event& evt){(that->*hand)(evt);}), channel, pri)));
	}

	template <typename Caller, typename Event> void subscribe(Caller* that, void (Caller::*hand)(Event&), EventHub::HandlerPriority pri)
	{
		this->theStore.push_back(std::move(EventHub::subscribe(std::move([that, hand](Event& evt){(that->*hand)(evt);}), pri)));
	}

private:
	List<EventSubscriberHandle> theStore;
};
