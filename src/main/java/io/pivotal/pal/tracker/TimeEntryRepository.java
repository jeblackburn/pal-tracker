package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry timeEntry);

    public TimeEntry find(long timeEntryId);

    public List<TimeEntry> list();

    public TimeEntry update(long id, TimeEntry toUpdate);

    public void delete(long timeEntryId);
}
