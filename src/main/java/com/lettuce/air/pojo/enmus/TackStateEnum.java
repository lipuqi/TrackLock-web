package com.lettuce.air.pojo.enmus;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum TackStateEnum implements IEnum<String> {

	PENDING("PENDING", "缓存未下发"),
    
	EXPIRED("EXPIRED", "过期"),
    
	SUCCESSFUL("SUCCESSFUL", "成功执行"),
    
	FAILED("FAILED", "执行失败"),
    
	TIMEOUT("TIMEOUT", "执行超时"),
    
	CANCELED("CANCELED", "撤销执行"),
    
	DELIVERED("DELIVERED", "送达设备"),
    
	SENT("SENT", "正在下发");

    /**
     * key值
     */
    private String val;
    /**
     * 描述
     */
    private String desc;

    TackStateEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
    
	@Override
	public String getValue() {
		return this.val;
	}

    public String getVal() {
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
    public static TackStateEnum getObj(String val) {
        for (TackStateEnum d : TackStateEnum.values()) {
            if (d.getVal().equals(val)) {
                return d;
            }
        }
        return null;
    }

}
