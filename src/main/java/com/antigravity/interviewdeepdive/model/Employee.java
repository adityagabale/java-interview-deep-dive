package com.antigravity.interviewdeepdive.model;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record Employee(
        long id,
        String name,
        String department,
        double salary,
        LocalDate joiningDate,
        java.util.List<String> projects) {
}
