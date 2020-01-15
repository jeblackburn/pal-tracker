package io.pivotal.pal.tracker;

import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        String insertSql = String.format(
                "insert into time_entries (project_id, user_id, date, hours) values (%s, %s, '%s', %s)",
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                timeEntry.getHours());
        jdbcTemplate.execute(insertSql);
        Integer newId = jdbcTemplate.queryForObject(
                "select id from time_entries where project_id = ? and user_id = ? and date = ?",
                Integer.class,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate()
        );
        return timeEntry.toBuilder().id(Long.valueOf(Objects.requireNonNull(newId).toString())).build();
    }

    @SneakyThrows
    private <T> Long doStuff(CallableStatement callableStatement) {
        return callableStatement.getLong(1);
    }

    private <T> Long processRow(ResultSet resultSet, int row) {
        return null;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        String sql = "select id, project_id, user_id, date, hours from time_entries where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    (row, rowNum) -> new TimeEntry(row.getLong(1), row.getLong(2), row.getLong(3), LocalDate.parse(row.getString(4)), row.getInt(5)),
                    timeEntryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<TimeEntry> list() {
        String sql = "select id, project_id, user_id, date, hours from time_entries";
        return jdbcTemplate.query(
                sql,
                (row, rowNum) -> new TimeEntry(row.getLong(1), row.getLong(2), row.getLong(3), LocalDate.parse(row.getString(4)), row.getInt(5)));
    }

    @Override
    public TimeEntry update(long id, TimeEntry toUpdate) {
        String sql = "update time_entries set project_id = ?, user_id = ?, date = ?, hours = ? where id = ?";
        jdbcTemplate.update(sql, toUpdate.getProjectId(), toUpdate.getUserId(), toUpdate.getDate(), toUpdate.getHours(), id);
        return find(id);
    }

    @Override
    public void delete(long timeEntryId) {
        jdbcTemplate.update("delete from time_entries where id = ?", timeEntryId);
    }
}
