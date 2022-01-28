package events.clock

import java.time.Instant

class SettableClock(var now: Instant) : Clock {
    override fun now(): Instant {
        return now
    }
}