package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(value = "/time-entries", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping(value = "/time-entries/{timeEntryId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/time-entries", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping(value = "/time-entries/{timeEntryId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,
                                            @RequestBody TimeEntry toUpdate) {
        TimeEntry updated = timeEntryRepository.update(timeEntryId, toUpdate);
        return updated == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{entryId}")
    public ResponseEntity delete(@PathVariable long entryId) {
        timeEntryRepository.delete(entryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
