package com.benet.system.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMeta {
    private String title;
    private String locale;
    private String icon;
    private boolean requiresAuth;
}
