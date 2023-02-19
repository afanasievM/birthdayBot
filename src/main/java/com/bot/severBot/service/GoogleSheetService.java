package com.bot.severBot.service;

import java.util.List;

public interface GoogleSheetService {
    List<List<Object>> getRange(String range);
}