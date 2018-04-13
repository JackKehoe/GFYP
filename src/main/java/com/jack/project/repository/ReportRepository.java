package com.jack.project.repository;

import com.jack.project.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>{

	Report findById(int id);
}
