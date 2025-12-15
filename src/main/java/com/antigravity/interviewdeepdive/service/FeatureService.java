package com.antigravity.interviewdeepdive.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class FeatureService {

    public String robustOptionalDemo(String input) {
        // Demonstrate Optional.ofNullable, map, orElse
        return Optional.ofNullable(input)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("J"))
                .orElse("Default Value (Input was null or didn't start with J)");
    }

    public String calculateAge(String birthDateStr) {
        // Demonstrate Date/Time API
        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            LocalDate currentDate = LocalDate.now();

            if (birthDate.isAfter(currentDate)) {
                return "Birth date cannot be in the future";
            }

            Period period = Period.between(birthDate, currentDate);
            return String.format("Age is %d years, %d months, and %d days",
                    period.getYears(), period.getMonths(), period.getDays());
        } catch (Exception e) {
            return "Invalid date format. Use YYYY-MM-DD";
        }
    }

    // Demonstrate Functional Interface usage
    public String applyFunction(String input, Function<String, String> processor) {
        return processor.apply(input);
    }

    // 1. Function Composition (Pipeline)
    public String textPipeline(String input) {
        Function<String, String> trim = String::trim;
        Function<String, String> upper = String::toUpperCase;
        Function<String, String> mask = s -> s.replaceAll(".", "*"); // Naive masking

        // Compose them: trim -> upper -> mask
        return trim.andThen(upper).andThen(mask).apply(input);
    }

    // 2. Strategy Pattern using Map
    private final Map<String, Function<Double, Double>> discounts = Map.of(
            "XMAS", price -> price * 0.9,
            "NEWYEAR", price -> price * 0.8,
            "VIP", price -> price * 0.5);

    public Double calculateDiscount(String code, double price) {
        return discounts.getOrDefault(code, Function.identity()).apply(price);
    }

    // 3. Lazy Evaluation using Supplier
    public String heavyComputation(boolean perform, java.util.function.Supplier<String> expensiveOperation) {
        if (!perform) {
            return "Skipped";
        }
        return expensiveOperation.get();
    }

    // 4. "Tricky" Checked Exception Handling Wrapper
    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        R apply(T t) throws Exception;
    }

    public <T, R> Function<T, R> wrap(CheckedFunction<T, R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
