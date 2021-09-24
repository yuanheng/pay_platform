package com.bootdo.system.enums;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
public enum FileTypeEnum {

    XLSX(".xlsx"),

    XLS(".xls"),

    CSV(".csv"),
    UNKNOWN("无法识别"),
    ;

    private String suffix;

    FileTypeEnum(String suffix) {
        this.suffix = suffix;
    }

    public static FileTypeEnum of(String filePath) {
        for (FileTypeEnum vType : FileTypeEnum.values()) {
            if (filePath.endsWith(vType.getSuffix())) {
                return vType;
            }
        }
        return UNKNOWN;
    }

    public String getSuffix() {
        return suffix;
    }
}
