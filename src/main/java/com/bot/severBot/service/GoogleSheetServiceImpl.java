package com.bot.severBot.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GoogleSheetServiceImpl implements GoogleSheetService {
    private Sheets sheets;
    private SendBotMessageService sendBotMessageService;
    @Value("${spreadsheet_id}")
    private String SPREADSHEED_ID;

    @Autowired
    public GoogleSheetServiceImpl(Sheets sheets) {
        this.sheets = sheets;
    }

    public List<List<Object>> getRange(String range) {
        List<List<Object>> result = new ArrayList<>();
        try {
            List<String> ranges = Arrays.asList(range);
            BatchGetValuesResponse readResult = sheets.spreadsheets().values()
                    .batchGet(SPREADSHEED_ID)
                    .setRanges(ranges)
                    .execute();
            result = readResult.getValueRanges().get(0).getValues();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
