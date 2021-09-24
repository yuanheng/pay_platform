package com.bootdo.system.adptor;

import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.system.dto.TBCode4ImportDTO;
import com.bootdo.system.enums.FileTypeEnum;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
public class DataSheetParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSheetParser.class);

    private FileTypeEnum fileTypeEnum;

    private List<TBCode4ImportDTO> dataList;

    private final int startRow = 2;

    public DataSheetParser() {
        dataList = new ArrayList<>(32);
    }

    public List<TBCode4ImportDTO> readData(final String filePath) throws RuntimeException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            LOGGER.error("不存在的文件[{}]，请确认.", filePath);
        }
        fileTypeEnum = FileTypeEnum.of(filePath);
        Assert.isTrue(FileTypeEnum.UNKNOWN != fileTypeEnum, fileTypeEnum.getSuffix());
        readData(dataFile);
        return dataList;
    }

    private void readData(File dataFile) {
        try (InputStream input = new BufferedInputStream(new FileInputStream(dataFile))) {
            List<Object> roughList = new ArrayList<>(32);
            if (FileTypeEnum.XLSX == fileTypeEnum) {
                XSSFWorkbook wb = new XSSFWorkbook(input);
                roughList = readToDataList(wb);
            } else if (FileTypeEnum.XLS == fileTypeEnum) {
                HSSFWorkbook wb = new HSSFWorkbook(input);
                roughList = readToDataList(wb);
            }
            LOGGER.info("默认表头行号为{},读取到数据{}行.", startRow, roughList.size());
            pushIn(roughList);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void pushIn(List<Object> roughList) {
        roughList.forEach(e -> {
            TBCode4ImportDTO codeDTO = from(e);
            if (codeDTO != null) {
                dataList.add(codeDTO);
            } else {
                LOGGER.warn("存在无效数据[{}],将舍弃", JSONUtils.beanToJson(e));
            }
        });
    }

    private TBCode4ImportDTO from(Object e) {
        TBCode4ImportDTO dto = new TBCode4ImportDTO();
        final List<String> dataRow = (List<String>) e;

        long emptyCol = dataRow.stream().filter(xe -> StringUtils.isEmpty(xe)).count();
        if (emptyCol > 0) {
            return null;
        }

        final String tbAccountNo = dataRow.get(0);
        dto.setTbAccountNo(tbAccountNo);
        try {
            final BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(dataRow.get(1)));
            dto.setAmount(amount);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            dto.setAmount(BigDecimal.ZERO);
        }

        final String tbOrderNo = dataRow.get(2);
        dto.setTbOrderNo(tbOrderNo);
        Date tbOrderCreateTime = new Date();
        try {
            tbOrderCreateTime = DateUtils.newDate(dataRow.get(3));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        dto.setTbOrderCreateTime(tbOrderCreateTime);

        final String payUrl = dataRow.get(4);
        dto.setPayUrl(payUrl);

        String mid = dataRow.get(5);
        if (mid.contains(Constant.SYMBOL_DOT)) {
            mid = Splitter.on(Constant.SYMBOL_DOT).splitToList(mid).get(0);
        }
        dto.setMid(mid);
        LOGGER.info(JSONUtils.beanToJson(dto));
        return dto;
    }

    private List<Object> readToDataList(Workbook wb) {
        List<Object> data = Lists.newArrayList();
        //读取第一个工作页sheet
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int titleRowNo = startRow - 1;
        Row tr = sheet.getRow(titleRowNo);

        //默认第一行为表头
        int titleColCount = tr.getPhysicalNumberOfCells();

        int lastRowNo = sheet.getLastRowNum();
        for (int rowIdx = startRow; rowIdx < lastRowNo + 1; rowIdx++) {
            List<Object> row = Lists.newArrayList();
            for (int i = 0; i < titleColCount; i++) {
                Row hr = sheet.getRow(rowIdx);
                Cell hc = hr.getCell(i);
//                String hv = Head.DEFAULT_VACANT;
                String hv = "";
                if (null != hc) {
                    if (CellType.NUMERIC == hc.getCellTypeEnum()) {
                        if (HSSFDateUtil.isCellDateFormatted(hc)) {
                            hv = DateUtils.format(hc.getDateCellValue(), DateUtils.DATE_TIME_PATTERN);
                        } else {
                            hv = String.valueOf(hc.getNumericCellValue());
                        }
                    } else {
                        hc.setCellType(CellType.STRING);
                        hv = hc.getStringCellValue();
                    }
                }
                row.add(hv);
            }
            data.add(row);
        }
        return data;
    }

}
