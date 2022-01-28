package events

import events.clock.SettableClock
import events.statistics.EventsStatisticImpl
import events.statistics.EventsStatistic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Duration
import java.time.Instant
import kotlin.math.abs


class EventsStatisticTest {
    private lateinit var now: Instant
    private lateinit var clock: SettableClock
    private lateinit var eventsStatistic: EventsStatistic

    private val eps = 1e-9
    private val firstEvent = "some first event"
    private val secondEvent = "the second event"
    private val thirdEvent = "another event"

    @Before
    fun initializeClock() {
        now = Instant.now()
        clock = SettableClock(now)
        eventsStatistic = EventsStatisticImpl(clock)
    }

    private fun assertEpsEquals(a: Double, b: Double) {
        assertTrue(abs(a - b) <= eps)
    }

    private fun assertMapsEqual(expected: Map<String, Double>, actual: Map<String, Double>) {
        assertEquals(expected.keys, actual.keys)
        for ((key, value) in expected) {
            assertEpsEquals(value, actual.getValue(key))
        }
    }

    private fun hoursPassed(hoursAmount: Long) {
        now = now.plus(Duration.ofHours(hoursAmount))
        clock.now = now
    }

    @Test
    fun deleteAllEventsTest() {
        eventsStatistic.incEvent(firstEvent)
        eventsStatistic.incEvent(secondEvent)
        hoursPassed(1)
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(firstEvent))
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(secondEvent))
        eventsStatistic.incEvent(firstEvent)
        eventsStatistic.incEvent(secondEvent)
        eventsStatistic.incEvent(secondEvent)
        eventsStatistic.incEvent(thirdEvent)
        hoursPassed(1)
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(firstEvent))
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(secondEvent))
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(thirdEvent))
    }

    @Test
    fun getEventStatisticByNameTest() {
        eventsStatistic.incEvent(secondEvent)
        hoursPassed(1)
        eventsStatistic.incEvent(firstEvent)
        eventsStatistic.incEvent(firstEvent)
        eventsStatistic.incEvent(thirdEvent)
        assertEpsEquals(2.0 / 60, eventsStatistic.getEventStatisticByName(firstEvent))
        assertEpsEquals(0.0, eventsStatistic.getEventStatisticByName(secondEvent))
        assertEpsEquals(1.0 / 60, eventsStatistic.getEventStatisticByName(thirdEvent))
    }

    @Test
    fun getAllEventsStatisticTest() {
        eventsStatistic.incEvent(firstEvent)
        eventsStatistic.incEvent(secondEvent)
        eventsStatistic.incEvent(secondEvent)
        eventsStatistic.incEvent(thirdEvent)
        eventsStatistic.incEvent(thirdEvent)
        eventsStatistic.incEvent(thirdEvent)
        val expected = mapOf(Pair(firstEvent, 1.0 / 60), Pair(secondEvent, 2.0 / 60), Pair(thirdEvent, 3.0 / 60))
        assertMapsEqual(eventsStatistic.getAllEventsStatistic(), expected)
    }

    @Test
    fun randomTest() {
        val hours = (0..1).random().toLong()
        val firstEventAmount = (1..10).random()
        val secondEventAmount = (1..10).random()
        (0 until firstEventAmount).forEach { _ -> eventsStatistic.incEvent(firstEvent) }
        (0 until secondEventAmount).forEach { _ -> eventsStatistic.incEvent(secondEvent) }
        hoursPassed(hours)
        var expected = mapOf<String, Double>()
        if (hours < 1) {
            expected = mapOf(Pair(firstEvent, firstEventAmount / 60.0), Pair(secondEvent, secondEventAmount / 60.0))
        }
        assertMapsEqual(expected, eventsStatistic.getAllEventsStatistic())
    }
}