package com.bootdo.testDemo;

import com.bootdo.system.adptor.Converter;
import com.bootdo.system.adptor.DataSheetParser;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.dto.TBCode4ImportDTO;
import com.bootdo.system.service.TbOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCodeImport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCodeImport.class);

    private TbOrderService tbOrderService;

    @Autowired
    public void setTbOrderService(TbOrderService tbOrderService) {
        this.tbOrderService = tbOrderService;
    }

    public static void main(String[] args) {
        final String filePath = "/Users/charles/personal/rip_current/tb_code_data_template.xlsx";
        List<TBCode4ImportDTO> dataList = readFromFile(filePath);
    }

    @Test
    public void tesInitPro() {
        final String filePath = "/Users/charles/personal/rip_current/tb_code_data.xlsx";
        List<TBCode4ImportDTO> dataList = readFromFile(filePath);

        dataList.forEach(e -> {
            final TbOrderDO orderDO = Converter.convert(e);
            // 查询存不存在
            TbOrderDO existence = tbOrderService.queryByEntireProperties(orderDO);
            if (existence == null) {
                tbOrderService.save(orderDO);
            } else {
                LOGGER.warn("已存在的记录，不予重复添加");
            }
        });

    }

    private static List<TBCode4ImportDTO> readFromFile(String filePath) {
        return new DataSheetParser().readData(filePath);
    }

}
