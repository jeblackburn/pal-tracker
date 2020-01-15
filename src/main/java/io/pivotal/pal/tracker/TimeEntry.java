package io.pivotal.pal.tracker;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class TimeEntry {
    private Long id;
    @NonNull private Long projectId;
    @NonNull private Long userId;
    @NonNull private LocalDate date;
    @NonNull private Integer hours;
}
