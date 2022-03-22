package com.funtap.awass.repository.elasticsearch;

import com.funtap.awass.jpaentity.elasticsearch.ReportScanLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportScanLogRepository extends ElasticsearchRepository<ReportScanLog,String> {
    List<ReportScanLog> findByIdTarget(String idTarget);

    Iterable<ReportScanLog> findByUsername(String username);
}
