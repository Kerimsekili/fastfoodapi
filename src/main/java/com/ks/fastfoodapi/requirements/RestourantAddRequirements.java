package com.ks.fastfoodapi.requirements;

import lombok.Data;

@Data
public class RestourantAddRequirements {
    private Long id;

    private String name;

    private String address;

    private Long managerId;
}
