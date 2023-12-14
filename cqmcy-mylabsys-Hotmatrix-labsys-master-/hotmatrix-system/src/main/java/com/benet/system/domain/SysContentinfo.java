package com.benet.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.benet.common.annotation.Excel;
import com.benet.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 内容信息对象 sys_contentinfo
 * 
 * @author yoxking
 * @date 2020-04-06
 */
@Data
public class SysContentinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 内容ID */
    @Excel(name = "内容ID")
    private String contzNo;

    /** 类型ID */
    @Excel(name = "类型ID")
    private String classNo;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pubdate;

    /** 海报 */
    @Excel(name = "海报")
    private String poster;

    /** 简介 */
    @Excel(name = "简介")
    private String abstracts;

    /** 正文内容 */
    @Excel(name = "正文内容")
    private String ncontents;

    /** 点击次数 */
    @Excel(name = "点击次数")
    private Integer hitCount;

    /** 状态（1正常 0停用） */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private String checkState;

    /** 分支编号 */
    @Excel(name = "分支编号")
    private String branchNo;

    /** 删除标志（1代表存在 0代表删除） */
    @Excel(name = "删除标志", readConverterExp = "1=代表存在,0=代表删除")
    private String deleteFlag;

    /** 更新者 */
    @Excel(name = "更新者")
    private String comments;

    /** 应用编码 */
    @Excel(name = "应用编码")
    private String appCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

}
