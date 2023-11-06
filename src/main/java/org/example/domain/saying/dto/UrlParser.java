package org.example.domain.saying.dto;

import lombok.Getter;
import org.example.global.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UrlParser {

    private String cmd;
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;

    public UrlParser(String url) {
        paramsMap = new HashMap<>();

        this.cmd = url;

        String[] cmdBits = url.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length == 1) {
            return;
        }

        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        return StringUtil.parseIntWithDefault(paramsMap.get(paramName), defaultValue);
    }
}
