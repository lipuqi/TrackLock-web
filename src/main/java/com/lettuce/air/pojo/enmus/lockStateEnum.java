package com.lettuce.air.pojo.enmus;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum lockStateEnum implements IEnum<Integer> {

    LOCK(0, "开锁"),

    UN_LOCK(1, "解锁");

    /**
     * key值
     */
    private int val;
    /**
     * 描述
     */
    private String desc;

    lockStateEnum(int val, String desc) {
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
    public static lockStateEnum getObj(int val) {
        for (lockStateEnum d : lockStateEnum.values()) {
            if (d.getVal() == val) {
                return d;
            }
        }
        return null;
    }

}
