package com.appturismo.show.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtributeModel {
    private String className;
    private String attributeName;
    private String attributeValue;
}
