package com.funtap.awass.FunX;

import com.funtap.awass.jpaentity.ScanTarget;
import com.funtap.awass.repository.ScanTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateJob {
    @Autowired
    ScanTargetRepository scanTargetRepository;
    public synchronized String updatejob(long id, int total) {
        try {
//            reportScanLogRepository.save(reportScan);
            ScanTarget scanTarget = scanTargetRepository.findById(id).get();
            if(scanTarget!= null) {
                if(total < 0) {
                    scanTarget.setJob(scanTarget.getJob() + 1);
                    scanTargetRepository.save(scanTarget);
                }else {
                    scanTarget.setTotaljob(total);
                    scanTarget.setStatus(1);
                    scanTargetRepository.save(scanTarget);
                }
                if(scanTarget.getTotaljob() != 0 && scanTarget.getTotaljob() <= scanTarget.getJob() ) {
                    scanTarget.setStatus(2);
                    scanTargetRepository.save(scanTarget);
                }
            }
            return "Update ok job ok";
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
