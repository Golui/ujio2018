// Copyright (c) 2017 Grzegorz "Golui" Łazarski

#pragma once

// Inspired by https://stackoverflow.com/questions/15028609/event-handling-using-templates

namespace EventHub
{
	static const String DEFAULT_CHANNEL = "_default";

	/**
	 * Used for indexing the EventQueue::priorityEdge array
	 */
	enum HandlerPriority
	{
		HIGHEST = 0,
		HIGHER,
		HIGH,
		NORMAL,
		LOW,
		LOWER,
		LOWEST,

		/** Internal */
		_EVENT_PRIORITY_MIN = HIGHEST,

		/** Internal */
		_EVENT_PRIORITY_MAX = LOWEST
	};
}

struct EventSubscriberHandle
{

	EventSubscriberHandle() {}

	bool shouldUnsubscribe = true;

	EventSubscriberHandle(EventSubscriberHandle&& other)
	{
		this->shouldUnsubscribe = other.shouldUnsubscribe;
		other.shouldUnsubscribe = false;
	}

	void setEternal()
	{
		this->shouldUnsubscribe = false;
	}

	virtual void unsubscribe() { Logging::error("Called unsubscribe on a generic handle"); }

	virtual ~EventSubscriberHandle() {}

private:
	EventSubscriberHandle(EventSubscriberHandle& other);

};

/**
 * This class controls the Event system. In essence, any object can be an event.
 * The necessary queue will be lazy initialized when the first event of a type gets registered.
 *
 * Events need signalling, unles they are posted using the postAndSignal function.
 */
namespace EventHub
{
	namespace internal
	{

		struct EventQueueBase
		{
			virtual void unsubscribeAll() = 0;
			virtual ~EventQueueBase() {}
		};

		extern Array<EventQueueBase*> instantiatedQueues;

		template <typename Event> struct EventSubscriberHandleTemplate : public EventSubscriberHandle
		{
			using HandlerInternal = std::function<void(Event&)>;
			using IterType = typename List<HandlerInternal>::iterator;

			List<HandlerInternal>& handlerList;
			IterType it;

			EventSubscriberHandleTemplate(List<HandlerInternal>& list, IterType iter) : EventSubscriberHandle(), handlerList(list)
			{
				this->it = iter;
			}

			EventSubscriberHandleTemplate(EventSubscriberHandleTemplate&& other) : EventSubscriberHandle(), handlerList(other.handlerList)
			{
				this->shouldUnsubscribe = other.shouldUnsubscribe;
				other.shouldUnsubscribe = false;
				this->it = other.it;
			}

			void unsubscribe() override
			{
				// Safe erase, in case unsubscribeAll is called.
				for(auto b = this->handlerList.begin(); b != this->handlerList.end(); b++)
				{
					if(b == it)
					{
						this->handlerList.erase(b);
						break;
					}
				};
				this->shouldUnsubscribe = false;
			}

			~EventSubscriberHandleTemplate()
			{
				if(this->shouldUnsubscribe) this->unsubscribe();
			}
		};

		template <typename Event> struct EventChannel
		{

			using HandlerInternal = std::function<void(Event&)>;

			template <typename Handler> EventSubscriberHandleTemplate<Event> subscribe(Handler&& h, HandlerPriority pri)
			{
				auto it = this->handlers.begin();
				for(u32 i = 0; i <= pri; i++) std::advance(it, this->priorityEdge[i]);
				this->priorityEdge[pri]++;
				this->handlers.insert(it, std::move(h));
				EventSubscriberHandleTemplate<Event> esht = EventSubscriberHandleTemplate<Event>(this->handlers, --it);
				return std::move(esht);
			}

			// void postLater(Event&& evt)
			// {
			// 	this->pendingEvents.push_back(std::move(evt));
			// }

			void post(Event&& evt)
			{
				for(const auto& handler : this->handlers)
				{
					handler(evt);
				}
			}

			void unsubscribeAll()
			{
				this->handlers.clear();
				//this->pendingEvents.clear();
			}

			// void signal()
			// {
			// 	for(auto evtIt = this->pendingEvents.begin(); evtIt != this->pendingEvents.end();)
			// 	{
			// 		for(const auto& handler : this->handlers)
			// 		{
			// 			handler(*evtIt);
			// 		}
			// 		this->pendingEvents.erase(evtIt++);
			// 	}
			// }

		private:
			List<HandlerInternal> handlers;
			u32 priorityEdge[_EVENT_PRIORITY_MAX + 1] = {0};
			// List<Event> pendingEvents;

		};

		template <typename Event> struct EventQueue : public EventQueueBase
		{

			EventQueue()
			{
				instantiatedQueues.push_back(this);
			}

			static EventQueue<Event>& instance()
			{
				static EventQueue<Event> _instance;
				return _instance;
			};

			using HandlerInternal = std::function<void(Event&)>;

			template <typename Handler> EventSubscriberHandleTemplate<Event> subscribe(Handler&& h, HandlerPriority pri, String channel)
			{
				return std::move(this->channels[channel].subscribe(std::move(h), pri));
			}

			// void postLater(Event&& evt, String channel)
			// {
			// 	this->channels[channel].postLater(std::move(evt));
			// }

			void post(Event&& evt, String channel)
			{
				this->channels[channel].post(std::move(evt));
			}

			void unsubscribeAll() override
			{
				for(auto it : this->channels) it.second.unsubscribeAll();
			}

			// void signal() override
			// {
			// 	for(auto it : this->channels) it.second.signal();
			// }

		private:
			Umap<String, EventChannel<Event>> channels;

		};

		// We need to extract the type information from the function

		template <typename T> struct Traits : public Traits<decltype(&T::operator())> {};

		template <typename C, typename R, typename A>
		struct Traits<R(C::*)(A&) const>
		{
			typedef A type;
		};
	}

	// /**
	//  * Post an event to the hub. The event will wait in queue until signal is called.
	//  *
	//  * NOTE This may be remved as the codebase evolves to supp
	//  *
	//  * @param evt the event to be posted
	//  */
	// template <typename Event> void postLater(Event&& evt, String channel = EventHub::DEFAULT_CHANNEL)
	// {
	// 	internal::EventQueue<Event>::instance().postLater(std::move(evt), channel);
	// }

	/**
	 * Post and signals an event to the hub. The event will be handled immediately.
	 * @param evt the event to be posted
	 */
	template <typename Event> void post(Event&& evt, String channel = EventHub::DEFAULT_CHANNEL)
	{
		internal::EventQueue<Event>::instance().post(std::move(evt), channel);
	}

	/**
	 * Remove all subscribers from a given queue. Note the type argument.
	 */
	template <typename Event> void unsubscribeAll()
	{
		internal::EventQueue<Event>::instance().unsubscribeAll();
	}

	/**
	 * Register a subscription function. This function will remain active until handle falls out of scope, or until any of the unsibscribeAll functions get called.
	 * Higher priority means the handler will run sooner. IF priorities are equal, handlers run in registration order.
	 * @param hand the callable to handle the event
	 * @param pri the priority - defaults to NORMAL
	 */
	template <typename Handler> internal::EventSubscriberHandleTemplate<typename internal::Traits<Handler>::type> subscribe(Handler&& hand, String channel = DEFAULT_CHANNEL, HandlerPriority pri = NORMAL)
	{
		return std::move(internal::EventQueue<typename internal::Traits<Handler>::type>::instance().subscribe(std::move(hand), pri, channel));
	}

	/**
	 * Register a subscription function. This function will remain active until the handle falls out of scope, or until any of the unsibscribeAll functions get called.
	 * Higher priority means the handler will run sooner. IF priorities are equal, handlers run in registration order.
	 * @param hand the callable to handle the event
	 * @param pri the priority - defaults to NORMAL
	 */
	template <typename Handler> internal::EventSubscriberHandleTemplate<typename internal::Traits<Handler>::type> subscribe(Handler&& hand, HandlerPriority pri)
	{
		return std::move(subscribe(std::move(hand), DEFAULT_CHANNEL, pri));
	}

	/**
	 * Remove all subscribers from all queues.
	 */
	void unsubscribeAll();
}

#ifdef EVENT_SYSTEM_IMPLEMENTATION
	Array<EventHub::internal::EventQueueBase*> EventHub::internal::instantiatedQueues = Array<EventHub::internal::EventQueueBase*>();

	void EventHub::unsubscribeAll()
	{
		for(auto& it : internal::instantiatedQueues) it->unsubscribeAll();
	}

#endif
