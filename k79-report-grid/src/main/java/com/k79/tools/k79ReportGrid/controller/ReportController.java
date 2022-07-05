package com.k79.tools.k79ReportGrid.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.k79.tools.k79ReportGrid.dto.GenerateReportDto;
import com.k79.tools.k79ReportGrid.util.JarToolUtil;
import gridreport.jni.BinaryObject;
import gridreport.jni.ExportType;
import gridreport.jni.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

@Slf4j
@Controller
public class ReportController {

    /**
     * <p>描述： 模板+json=报表 </p>
     *
     * @param generateReportDto
     * @param httpServletResponse
     * @return void
     * @author k79
     * <p>创建时间： 2022-07-05 11:32</p>
     */
    @ResponseBody
    @PostMapping("/api/report/generateReport")
    public void generateReport(@RequestBody GenerateReportDto generateReportDto, HttpServletResponse httpServletResponse) throws IOException {
        try {
            if (StringUtils.isEmpty(generateReportDto.getGrfTemplateName())) {
                throw new IllegalArgumentException("模板名称不能为空");
            }
            if (StringUtils.isEmpty(generateReportDto.getReportData())) {
                throw new IllegalArgumentException("模板数据不能为空");
            }
            if (generateReportDto.getExportType()==null){
                generateReportDto.setExportType(ExportType.PDF);
            }
//            验证数据是否为JSON
            JsonNode jsonNode = new ObjectMapper().readTree(generateReportDto.getReportData());

            String templateName = generateReportDto.getGrfTemplateName();
            String templatePath = JarToolUtil.getSpringBootJarDir() + File.separator + "grfDir" + File.separator + templateName + ".grf";
            File templateFile = new File(templatePath);
            if (templateFile.exists() == false) {
                throw new IllegalArgumentException(templateName + ".grf文件不存在");
            }

            ExportType exportType = generateReportDto.getExportType();//导出文件类型
            String outFileName = templateName + "." + exportType.toString().toLowerCase();
            //创建报表对象
            Report report = new Report();
            //载入报表模板
            report.LoadFromFile(templatePath);
            //载入报表数据
            report.LoadDataFromXML(generateReportDto.getReportData());
            BinaryObject bo = report.ExportDirectToBinaryObject(exportType);
            byte[] responseBytes = bo.getDataBuf();
            //释放报表对象，释放对象占用的资源，报表对象成为不可用的。此步操作不是必须的
            report.Release();
            responseOutStream(httpServletResponse, outFileName, responseBytes);
        } catch (Throwable t) {
            log.error("wrong", t);
            responsePrint(httpServletResponse, t.getMessage());
        }
    }

    @GetMapping("/api/report/template")
    public void template(String templateName, HttpServletResponse httpServletResponse) throws Exception {
        try {
            String templatePath = JarToolUtil.getSpringBootJarDir() + File.separator + "grfDir" + File.separator + templateName + ".grf";
//            String templatePath = "C:\\Users\\Administrator\\Desktop\\123456\\grfDir" + File.separator + templateName + ".grf";
            String templateDataPath = JarToolUtil.getSpringBootJarDir() + File.separator + "grfDir" + File.separator + templateName + ".txt";
//            String templateDataPath = "C:\\Users\\Administrator\\Desktop\\123456\\grfDir" + File.separator + templateName + ".txt";
            File templateFile = new File(templatePath);
            if (templateFile.exists() == false) {
                throw new IllegalArgumentException(templateName + ".grf文件不存在");
            }

            File templateDataFile = new File(templateDataPath);
            if (templateDataFile.exists() == false) {
                throw new IllegalArgumentException(templateName + ".txt文件不存在");
            }

            ExportType exportType = ExportType.PDF;
            String outFileName = templateName + "." + exportType.toString().toLowerCase();
            //创建报表对象
            Report report = new Report();
            //载入报表模板
            report.LoadFromFile(templatePath);
            //载入报表数据
            report.LoadDataFromURL(templateDataPath);
            BinaryObject bo = report.ExportDirectToBinaryObject(exportType);
            byte[] responseBytes = bo.getDataBuf();
            //释放报表对象，释放对象占用的资源，报表对象成为不可用的。此步操作不是必须的
            report.Release();
            responseOutStream(httpServletResponse, outFileName, responseBytes);
        } catch (Throwable t) {
            log.error("wrong", t);
            responsePrint(httpServletResponse, t.getMessage());
        }
    }

    /**
     * <p>描述： 验证程序环境变量是否配置正确 </p>
     *
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author k79
     * <p>创建时间： 2022-07-05 9:34</p>
     */
    @GetMapping("/api/report/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("about");
        try {
            Report report = new Report();
            if (report.getNativeHandle() == 0) {
                throw new RuntimeException("请配置环境变量");
            }
            String html = "当前环境可以运行锐浪报表程序。";
            html += "程序版本:" + Report.getModuleVersion();
            modelAndView.addObject("message", html);
        } catch (Throwable t) {
            String html = "当前环境不能运行锐浪报表程序。";
            html += "请配置环境变量";
            modelAndView.addObject("message", html);
        }
        return modelAndView;
    }

    private void responsePrint(final HttpServletResponse httpServletResponse, String errMessage) throws IOException {
        httpServletResponse.setStatus(200);
        //跨域请求
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //设置编码格式
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/plain;charset=UTF-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.write(errMessage);
        pw.flush();
        pw.close();
    }

    private void responseOutStream(final HttpServletResponse httpServletResponse, String outFileName, byte[] responseBytes) throws IOException {
        httpServletResponse.setStatus(200);
        //跨域请求
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(outFileName, "UTF-8"));
        OutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }
}
