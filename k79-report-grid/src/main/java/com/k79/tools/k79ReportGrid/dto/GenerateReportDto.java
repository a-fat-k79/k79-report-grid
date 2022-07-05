package com.k79.tools.k79ReportGrid.dto;

import gridreport.jni.ExportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生成模板文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateReportDto {
    /**
     * 模板文件名称
     */
    private String grfTemplateName;

    /**
     * 报表数据
     */
    private String reportData;

    /**
     * 导出文件类型
     */
    ExportType exportType;
}
