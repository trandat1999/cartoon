package com.thd.cartoon.common.type;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
public enum MovieAttribute {
    TIME("time","cartoon.attribute.time")
    ;
    private String code;
    private String value;

    MovieAttribute(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
