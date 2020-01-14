package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> theTimeEntries = new HashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long nextId = this.nextId.getAndIncrement();
        theTimeEntries.put(nextId, clone(nextId, timeEntry));
        return theTimeEntries.get(nextId);
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return theTimeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return List.copyOf(theTimeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry toUpdate) {
        if (theTimeEntries.containsKey(id)) {
            theTimeEntries.put(id, clone(id, toUpdate));
            return theTimeEntries.get(id);
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        theTimeEntries.remove(timeEntryId);
    }

    private TimeEntry clone(long nextId, TimeEntry original) {
        return new TimeEntry(nextId, original.getProjectId(), original.getUserId(),
                original.getDate(), original.getHours());
    }
}
