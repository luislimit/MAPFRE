package com.mdsql.bussiness.entities;

import java.io.Serializable;

import com.mdval.exceptions.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputWarning implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8384575354150091276L;

    private Integer result;
    private ServiceException warnings;
    
    public void setOutputWarning(OutputWarning o){
      this.result = o.result;
      this.warnings = o.warnings;
    }
}
