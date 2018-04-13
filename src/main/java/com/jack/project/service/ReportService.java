package com.jack.project.service;

import com.jack.project.model.Report;

public interface ReportService {
    void save(Report report, String name);

    Report findById(int id);
}