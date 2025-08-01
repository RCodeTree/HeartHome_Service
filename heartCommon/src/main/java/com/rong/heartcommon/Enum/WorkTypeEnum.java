package com.rong.heartcommon.Enum;

/**
 * 作品类型枚举
 */
public enum WorkTypeEnum {

    /**
     * 图片标题简述
     */
    IMAGE_TITLE_DESC("image_title_desc", "图片标题简述"),

    /**
     * 仅标题简述
     */
    TITLE_DESC("title_desc", "仅标题简述"),

    /**
     * 仅图片
     */
    IMAGE_ONLY("image_only", "仅图片");

    private final String value; // 枚举值
    private final String description; // 枚举描述

    WorkTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据值获取枚举
     *
     * @param value 枚举值
     * @return 对应的枚举，如果不存在则返回null
     */
    public static WorkTypeEnum fromValue(String value) {
        for (WorkTypeEnum workType : WorkTypeEnum.values()) {
            if (workType.getValue().equals(value)) {
                return workType;
            }
        }
        return null;
    }

    /**
     * 检查是否需要图片
     *
     * @return 如果需要图片返回true，否则返回false
     */
    public boolean requiresImage() {
        return this == IMAGE_TITLE_DESC || this == IMAGE_ONLY;
    }

    /**
     * 检查是否需要标题
     *
     * @return 如果需要标题返回true，否则返回false
     */
    public boolean requiresTitle() {
        return this == IMAGE_TITLE_DESC || this == TITLE_DESC;
    }

    /**
     * 检查是否需要简述
     *
     * @return 如果需要简述返回true，否则返回false
     */
    public boolean requiresDescription() {
        return this == IMAGE_TITLE_DESC || this == TITLE_DESC;
    }
}
