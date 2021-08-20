package com.bootdo.coin.service.huobi.parser.account;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.crossmargin.GeneralRepayLoanRecord;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class GeneralRepayLoanRecordParser implements HuobiModelParser<GeneralRepayLoanRecord> {
    @Override
    public GeneralRepayLoanRecord parse(JSONObject json) {
        return json.toJavaObject(GeneralRepayLoanRecord.class);
    }

    @Override
    public GeneralRepayLoanRecord parse(JSONArray json) {
        return null;
    }

    @Override
    public List<GeneralRepayLoanRecord> parseArray(JSONArray jsonArray) {
        return jsonArray.toJavaList(GeneralRepayLoanRecord.class);
    }
}
