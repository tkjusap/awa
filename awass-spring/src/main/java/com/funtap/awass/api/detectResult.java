package com.funtap.awass.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class detectResult {

    public boolean detect(String jsonTrain, String jsonTest) {
        String reElement = "\\\"([^\\\"]*)\\\":";
        List<String> listTest = new ArrayList<>();
        List<String> listTrain = new ArrayList<>();
        listTest = getListTaget(jsonTest, reElement);
        listTrain = getListTaget(jsonTrain, reElement);
        if (jsonTest.equals(jsonTrain)) return false;
        if (listTest.size() != listTrain.size()) {
            return false;
        } else {
            for (String element : listTest) {
                if (listTest.contains(element)) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> getListTaget(String json, String regex) {
        List<String> listResult = new ArrayList<String>();
        String result = "";
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(json);
            while (m.find()) {
                result = m.group(0);
                listResult.add(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listResult;

    }
}
