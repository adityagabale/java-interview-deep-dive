package com.antigravity.interviewdeepdive.service;

import com.antigravity.interviewdeepdive.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StreamService {

    private final List<Employee> employees;

    public StreamService() {
        // Initialize with some dummy data
        employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 75000, LocalDate.of(2018, 5, 20), List.of("Project A", "Project B")),
                new Employee(2, "Bob", "HR", 50000, LocalDate.of(2019, 3, 15), List.of("Recruitment", "Policy")),
                new Employee(3, "Charlie", "IT", 80000, LocalDate.of(2017, 7, 10), List.of("Project A", "Project C")),
                new Employee(4, "David", "Finance", 60000, LocalDate.of(2020, 1, 5), List.of("Budgeting")),
                new Employee(5, "Eva", "HR", 55000, LocalDate.of(2021, 11, 25), List.of("Policy", "Training")),
                new Employee(6, "Frank", "Finance", 65000, LocalDate.of(2016, 8, 30),
                        List.of("Auditing", "Budgeting")));
    }

    public List<String> getAllDistinctProjects() {
        return employees.stream()
                .flatMap(e -> e.projects().stream()) // Stream<List<String>> -> Stream<String>
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Employee> filterByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.department().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<String> mapToNames() {
        return employees.stream()
                .map(Employee::name)
                .collect(Collectors.toList());
    }

    public Map<String, List<Employee>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::department));
    }

    public Map<Boolean, List<Employee>> partitionBySalary(double salaryThreshold) {
        return employees.stream()
                .collect(Collectors.partitioningBy(e -> e.salary() > salaryThreshold));
    }

    public Double calculateTotalSalary() {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .sum();
    }

    public Optional<Employee> getHighestPaidEmployee() {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));
    }

    // Advanced Features

    public List<Employee> complexFilter(String department, double minSalary, LocalDate joinedAfter) {
        return employees.stream()
                .filter(e -> e.department().equalsIgnoreCase(department))
                .filter(e -> e.salary() > minSalary)
                .filter(e -> e.joiningDate().isAfter(joinedAfter))
                .collect(Collectors.toList());
    }

    public List<Employee> multiLevelSort() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::department)
                        .thenComparingDouble(Employee::salary).reversed()) // Highest salary first in each dept
                .collect(Collectors.toList());
    }

    public Map<String, Double> getDepartmentsWithAvgSalaryGreaterThan(double threshold) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
