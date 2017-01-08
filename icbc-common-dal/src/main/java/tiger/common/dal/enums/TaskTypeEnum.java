package tiger.common.dal.enums;

/**
 * Created by zhao on 2016/4/26.
 */
public enum  TaskTypeEnum implements BaseEnum{

    OPENLINK("OPENLINK","外部链接"),INTERNAL_POST("INTERNAL_POST","本地素材");

    private String code;
    private String value;

    TaskTypeEnum(String code,String value){
        this.code = code;
        this.value = value;
    }

    public static TaskTypeEnum getEnumByCode(String code){
        for (TaskTypeEnum bt : TaskTypeEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
