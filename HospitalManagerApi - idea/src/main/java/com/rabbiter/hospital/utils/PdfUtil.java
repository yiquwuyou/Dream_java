package com.rabbiter.hospital.utils;

import com.rabbiter.hospital.pojo.Orders;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//这是一个用于生成PDF文件的Java类，它使用了iText库来创建PDF文档。这个类的主要功能是生成医院病情报告单的PDF文件，包括患者信息、病情症状、检查项目、药物信息、医生诊断等内容。
//
//        以下是这个类的主要步骤和功能：
//
//        设置HTTP响应头，告诉浏览器响应内容为PDF文件。
//        创建一个PDF文档对象。
//        添加文档的元数据，如标题、作者、关键字等。
//        创建并添加PDF文档的标题和提示信息。
//        创建并添加患者信息的表格。
//        创建并添加病情报告的表格，包括症状、检查项目、药物信息和医生诊断等。
//        添加底部的版权说明。
//        关闭文档。
//        这个类还使用了中文字体（STSong-Light）来支持中文内容的显示，以及一个示例的logo添加（被注释掉了），并且在底部添加了版权说明。
//
//        需要注意的是，这个类依赖于iText库，你需要确保项目中已经引入了该库的相关依赖。此外，你还可以根据需要修改和定制生成PDF的内容和样式这是一个用于生成PDF文件的Java类，
//        它使用了iText库来创建PDF文档。这个类的主要功能是生成医院病情报告单的PDF文件，包括患者信息、病情症状、检查项目、药物信息、医生诊断等内容。
//
//        以下是这个类的主要步骤和功能：
//
//        设置HTTP响应头，告诉浏览器响应内容为PDF文件。
//        创建一个PDF文档对象。
//        添加文档的元数据，如标题、作者、关键字等。
//        创建并添加PDF文档的标题和提示信息。
//        创建并添加患者信息的表格。
//        创建并添加病情报告的表格，包括症状、检查项目、药物信息和医生诊断等。
//        添加底部的版权说明。
//        关闭文档。
//        这个类还使用了中文字体（STSong-Light）来支持中文内容的显示，以及一个示例的logo添加（被注释掉了），并且在底部添加了版权说明。
//
//        需要注意的是，这个类依赖于iText库，你需要确保项目中已经引入了该库的相关依赖。此外，你还可以根据需要修改和定制生成PDF的内容和样式
public class PdfUtil {

    public static void ExportPdf(HttpServletRequest request, HttpServletResponse response, Orders order) throws Exception {
        //告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        //下载文件的默认名称
       // response.setHeader("Content-Disposition", "attachment;filename=XXX.pdf");
        //设置中文
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        //创建一个文档
        Document document = new Document(PageSize.A4);
        //创建第一个段落
        Paragraph titleParagraph = new Paragraph();
        //支持中文
        titleParagraph.setFont(new Font(bfChinese, 20, Font.NORMAL));
        //设置居中显示
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        titleParagraph.add("医院病情报告单");
        //创建第二个段落
        Paragraph tipsParagraph = new Paragraph();
        tipsParagraph.setFont(new Font(bfChinese, 10, Font.NORMAL));
        tipsParagraph.setAlignment(Element.ALIGN_CENTER);
        tipsParagraph.setLeading(tipsParagraph.getTotalLeading()+10);
        tipsParagraph.add("打印时间：" + TodayUtil.getTodayYmd());

        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        // 打开文档
        document.open();
        //设置文档标题
        document.addTitle("医院");
        //设置文档作者
        document.addAuthor("兔兔科技工作室");
        document.addCreationDate();
        //设置关键字
        document.addKeywords("iText");
        document.addLanguage("中文");
        //增加段落进入文档
        document.add(titleParagraph);
        document.add(tipsParagraph);
        //表格
        PdfPTable tableMessage = new PdfPTable(4);
        tableMessage.setSpacingBefore(8f);
        tableMessage.setSpacingAfter(8f);
        //设置表格无边框
        tableMessage.getDefaultCell().setBorder(0);
        //设置表格宽度
        tableMessage.setTotalWidth(new float[] { 30, 120, 30, 120 });
        tableMessage.addCell(new Paragraph("姓名", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPName(), FontChinese));
        tableMessage.addCell(new Paragraph("性别", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPGender(), FontChinese));
        tableMessage.addCell(new Paragraph("年龄", FontChinese));
        tableMessage.addCell(new Paragraph(order.getPatient().getPAge() +" 岁", FontChinese));
        tableMessage.addCell(new Paragraph("单号", FontChinese));
        tableMessage.addCell(String.valueOf(order.getOId()));
        tableMessage.addCell(new Paragraph("日期", FontChinese));
        tableMessage.addCell(order.getOEnd());
        tableMessage.addCell(new Paragraph("电话", FontChinese));
        tableMessage.addCell(order.getPatient().getPPhone());
        document.add(tableMessage);



        //病情表格
        PdfPTable tableOrder = new PdfPTable(1);
        //设置表格无边框
        tableOrder.getDefaultCell().setBorder(0);
        tableOrder.setSpacingBefore(30f);
        tableOrder.setSpacingAfter(10f);

        PdfPCell cell1 = new PdfPCell(new Paragraph("症状", new Font(bfChinese, 14, Font.NORMAL)));
        cell1.setFixedHeight(25);
        cell1.setBorder(0);
        PdfPCell cell2 = new PdfPCell(new Paragraph(order.getORecord(), new Font(bfChinese, 10, Font.NORMAL)));
        cell2.setFixedHeight(30);
        cell2.setBorder(0);
        cell2.setPaddingLeft(10);
        PdfPCell cell3 = new PdfPCell(new Paragraph("检查项目及价钱", new Font(bfChinese, 14, Font.NORMAL)));
        cell3.setFixedHeight(25);
        cell3.setBorder(0);
        PdfPCell cell4 = new PdfPCell(new Paragraph(order.getOCheck(), new Font(bfChinese, 10, Font.NORMAL)));
        cell4.setFixedHeight(30);
        cell4.setBorder(0);
        cell4.setPaddingLeft(10);
        PdfPCell cell5 = new PdfPCell(new Paragraph("药物及价钱", new Font(bfChinese, 14, Font.NORMAL)));
        cell5.setFixedHeight(25);
        cell5.setBorder(0);
        PdfPCell cell6 = new PdfPCell(new Paragraph(order.getODrug(), new Font(bfChinese, 10, Font.NORMAL)));
        cell6.setFixedHeight(30);
        cell6.setBorder(0);
        cell6.setPaddingLeft(10);
        PdfPCell cell7 = new PdfPCell(new Paragraph("诊断/医生意见", new Font(bfChinese, 14, Font.NORMAL)));
        cell7.setFixedHeight(25);
        cell7.setBorder(0);
        PdfPCell cell8 = new PdfPCell(new Paragraph(order.getOAdvice(), new Font(bfChinese, 10, Font.NORMAL)));
        cell8.setFixedHeight(100);
        cell8.setBorder(0);
        cell8.setPaddingLeft(10);

        tableOrder.addCell(cell1);
        tableOrder.addCell(cell2);
        tableOrder.addCell(cell3);
        tableOrder.addCell(cell4);
        tableOrder.addCell(cell5);
        tableOrder.addCell(cell6);
        tableOrder.addCell(cell7);
        tableOrder.addCell(cell8);
        document.add(tableOrder);

        //增加logo，绝对定位居于右上角
//        Image image = Image.getInstance("src/main/resources/static/images/dgut.jpeg");
//        image.setAbsolutePosition(440,690);
//        document.add(image);

        //设置pdf底部版权说明
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf= BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.EMBEDDED);
        cb.beginText();
        cb.setFontAndSize(bf, 11);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,  "该报告单仅供参考", 300, 40, 0);
        cb.setFontAndSize(bf,13);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER,  "版权医院所有", 300, 20, 0);
        cb.endText();





        document.close();
    }
}
