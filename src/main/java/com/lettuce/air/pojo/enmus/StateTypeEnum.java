package com.lettuce.air.pojo.enmus;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum StateTypeEnum implements IEnum<Integer> {

	STATE_INTERVAL(0, "状态间隔"),

	POSITION_INTERVAL(1, "定位间隔");

    /**
     * key值
     */
    private int val;
    /**
     * 描述
     */
    private String desc;

    StateTypeEnum(int val, String desc) {
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
    public static StateTypeEnum getObj(int val) {
        for (StateTypeEnum d : StateTypeEnum.values()) {
            if (d.getVal() == val) {
                return d;
            }
        }
        return null;
    }

}
