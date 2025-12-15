package com.antigravity.interviewdeepdive.controller;

import com.antigravity.interviewdeepdive.model.Employee;
import com.antigravity.interviewdeepdive.service.FeatureService;
import com.antigravity.interviewdeepdive.service.StreamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    private final StreamService streamService;
    private final FeatureService featureService;

    public DemoController(StreamService streamService, FeatureService featureService) {
        this.streamService = streamService;
        this.featureService = featureService;
    }

    @GetMapping("/streams/filter")
    public List<Employee> filterByDept(@RequestParam String dept) {
        return streamService.filterByDepartment(dept);
    }

    @GetMapping("/streams/names")
    public List<String> getNames() {
        return streamService.mapToNames();
    }

    @GetMapping("/streams/group-by-dept")
    public Map<String, List<Employee>> groupByDept() {
        return streamService.groupByDepartment();
    }

    @GetMapping("/streams/partition")
    public Map<Boolean, List<Employee>> partitionBySalary(@RequestParam double salary) {
        return streamService.partitionBySalary(salary);
    }

    @GetMapping("/streams/total-salary")
    public Double getTotalSalary() {
        return streamService.calculateTotalSalary();
    }

    @GetMapping("/streams/projects")
    public List<String> getAllProjects() {
        return streamService.getAllDistinctProjects();
    }

    @GetMapping("/streams/highest-paid")
    public Employee getHighestPaid() {
        return streamService.getHighestPaidEmployee().orElse(null);
    }

    @GetMapping("/features/optional")
    public String demoOptional(@RequestParam(required = false) String input) {
        return featureService.robustOptionalDemo(input);
    }

    @GetMapping("/features/age")
    public String calculateAge(@RequestParam String birthDate) {
        return featureService.calculateAge(birthDate);
    }

    @GetMapping("/features/functional")
    public String demoFunctional(@RequestParam String input, @RequestParam String mode) {
        if ("reverse".equalsIgnoreCase(mode)) {
            return featureService.applyFunction(input, s -> new StringBuilder(s).reverse().toString());
        } else if ("upper".equalsIgnoreCase(mode)) {
            return featureService.applyFunction(input, String::toUpperCase);
        }
        return "Unknown mode";
    }

    @GetMapping("/streams/complex-filter")
    public List<Employee> complexFilter(
            @RequestParam String dept,
            @RequestParam double minSalary,
            @RequestParam String afterDate) {
        return streamService.complexFilter(dept, minSalary, java.time.LocalDate.parse(afterDate));
    }

    @GetMapping("/streams/multi-sort")
    public List<Employee> multiSort() {
        return streamService.multiLevelSort();
    }

    @GetMapping("/streams/group-having")
    public Map<String, Double> groupHaving(@RequestParam double threshold) {
        return streamService.getDepartmentsWithAvgSalaryGreaterThan(threshold);
    }

    @GetMapping("/features/pipeline")
    public String demoPipeline(@RequestParam String input) {
        return featureService.textPipeline(input);
    }

    @GetMapping("/features/strategy")
    public Double demoStrategy(@RequestParam String code, @RequestParam double price) {
        return featureService.calculateDiscount(code, price);
    }

    @GetMapping("/features/lazy")
    public String demoLazy(@RequestParam boolean perform) {
        // Simulate an expensive operation that takes time
        return featureService.heavyComputation(perform, () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            return "Expensive Result Computed!";
        });
    }
}
