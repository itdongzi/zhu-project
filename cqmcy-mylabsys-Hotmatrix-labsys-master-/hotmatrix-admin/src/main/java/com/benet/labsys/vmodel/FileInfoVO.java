package com.benet.labsys.vmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author 这颗甜
 * @Date 2022/9/5 10:54
 * @description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoVO {
    private String rescNo;
    private String fileNo;
    private String lastModified;
    private LocalDateTime lastModifiedDate;
    private String name;
    private String size;
    private String status;
    private String type;
    private String uid;
    private String url;
    private String fileAddress;
}
