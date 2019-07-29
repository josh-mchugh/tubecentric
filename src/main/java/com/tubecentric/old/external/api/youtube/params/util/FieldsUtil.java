package com.tubecentric.old.external.api.youtube.params.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

public class FieldsUtil {

    public static Boolean hasSingleField(Set<String> fields) {

        if(CollectionUtils.isNotEmpty(fields)) {

            if (fields.size() == 1 && !fields.iterator().next().contains("(")) {

                return true;
            }
        }

        return false;
    }
}
