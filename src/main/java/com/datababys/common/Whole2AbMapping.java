package com.datababys.common;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shysoong on 15/12/24.
 */
public class Whole2AbMapping {
    public static String getAbbreviation(String wholeWord) {
        Map<String, String> whole2ab = new HashMap<String, String>();
        whole2ab.put("dataadministration", "cp");
        whole2ab.put("emergenceresource", "er");
        whole2ab.put("decisionsupport", "ds");
        whole2ab.put("maternalchildhealth", "mic");
        whole2ab.put("dualreferral", "tt");
        whole2ab.put("chs", "chs");
        whole2ab.put("rm", "rm");
        whole2ab.put("app", "app");
        return whole2ab.get(wholeWord);
    }

    public static String getResourcefromSystemCode(String systemCode) {
        return "HC_GY_" + StringUtils.upperCase(systemCode);
    }
}
