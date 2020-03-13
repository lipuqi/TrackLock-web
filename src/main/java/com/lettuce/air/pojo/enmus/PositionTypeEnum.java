package com.lettuce.air.pojo.enmus;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum PositionTypeEnum implements IEnum<Integer> {
	
    AUTO_TYPE(0, "自动定位"),

    MANUAL_TYPE(1, "手动定位"),
    
    LOCK_TYPE(2, "开锁"),

    UN_LOCK_TYPE(3, "解锁");

    /**
     * key值
     */
    private int val;
    /**
     * 描述
     */
    private String desc;

    PositionTypeEnum(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }
    
	@Override
	public Integer getValue() {
		return this.val;
	}

    public int getVal() {
        return this.val;
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * 通过val值 获取枚举对象
     *
     * @param val
     * @return
     */
    public static PositionTypeEnum getObj(int val) {
        for (PositionTypeEnum d : PositionTypeEnum.values()) {
            if (d.getVal() == val) {
                return d;
            }
        }
        return null;
    }

}
