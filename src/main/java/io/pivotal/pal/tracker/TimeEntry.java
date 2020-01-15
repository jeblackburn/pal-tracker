package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {
    private Long id;
    private Long projectId;
    private Long userId;
    private LocalDate date;
    private Integer hours;

    public Long getId() {
        return this.id;
    }


    public Long getProjectId() {
        return this.projectId;
    }


    public Long getUserId() {
        return this.userId;
    }


    public LocalDate getDate() {
        return this.date;
    }


    public Integer getHours() {
        return this.hours;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setProjectId(final Long projectId) {
        this.projectId = projectId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public void setHours(final Integer hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TimeEntry)) return false;
        final TimeEntry other = (TimeEntry) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        final Object this$projectId = this.getProjectId();
        final Object other$projectId = other.getProjectId();
        if (!Objects.equals(this$projectId, other$projectId)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (!Objects.equals(this$userId, other$userId)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (!Objects.equals(this$date, other$date)) return false;
        final Object this$hours = this.getHours();
        final Object other$hours = other.getHours();
        return Objects.equals(this$hours, other$hours);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeEntry;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $projectId = this.getProjectId();
        result = result * PRIME + ($projectId == null ? 43 : $projectId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $hours = this.getHours();
        result = result * PRIME + ($hours == null ? 43 : $hours.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TimeEntry(id=" + this.getId() + ", projectId=" + this.getProjectId() + ", userId=" + this.getUserId() + ", date=" + this.getDate() + ", hours=" + this.getHours() + ")";
    }

    public TimeEntry(final Long id, final Long projectId, final Long userId, final LocalDate date, final Integer hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry() {
    }

    public TimeEntry(final Long projectId, final Long userId, final LocalDate date, final Integer hours) {
        if (projectId == null) {
            throw new NullPointerException("projectId is marked non-null but is null");
        }
        if (userId == null) {
            throw new NullPointerException("userId is marked non-null but is null");
        }
        if (date == null) {
            throw new NullPointerException("date is marked non-null but is null");
        }
        if (hours == null) {
            throw new NullPointerException("hours is marked non-null but is null");
        }
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }
}
