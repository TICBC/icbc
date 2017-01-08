package tiger.common.dal.enums;

/**
 * Created by globit on 9/1/15.
 */
public enum PermissionEnum implements BaseEnum {


    //Workspace相权限
    VIEW_WORKSPACE("VIEW_WORKSPACE", "查看团队空间"),
    VIEW_WORKSPACE_MEMBER("VIEW_WORKSPACE_MEMBER", "获取团队成员列表"),
    DELETE_WORKSPACE("DELETE_WORKSPACE", "解散团队"),
    DELETE_WORKSPACE_MEMBER("DELETE_WORKSPACE_MEMBER", "移除团队成员"),
    UPDATE_WORKSPACE_INFO("UPDATE_WORKSPACE_INFO", "更新团队基本信息"),
    UPDATE_WORKSPACE_MEMBER_ROLE("UPDATE_WORKSPACE_MEMBER_ROLE", "更新团队成员角色"),
    UPDATE_WORKSPACE_TRANSFER_OWNER("UPDATE_WORKSPACE_TRANSFER_OWNER", "移交团队所有者"),
    UPDATE_WORKSPACE_INVITE_MEMBER("UPDATE_WORKSPACE_INVITE_MEMBER", "邀请团队成员"),
    UPDATE_WORKSPACE_CONFIGURATION("UPDATE_WORKSPACE_CONFIGURATION", "修改团队配置"),

    //bi 相关权限
    MANAGE_ALL("MANAGE_ALL","管理员权限"),
    VIEW_ALL("VIEW_ALL","市长权限,可查看所有数据"),
    ENVIRONMENT("ENVIRONMENT","查看环境模块的权限"),
    ECONOMY("ECONOMY","查看经济模块的权限"),
    POPULATION("POPULATION","查看人口模块的权限"),

    //客户相关权限
    CREATE_CUSTOMER_MEMBER("CREATE_CUSTOMER_MEMBER","创建个人工作空间客户"),
    CREATE_CUSTOMER_ALL("CREATE_CUSTOMER_ALL","创建工作组客户"),
    VIEW_CUSTOMER_MEMBER("VIEW_CUSTOMER_MEMBER","查看个人工作空间客户"),
    VIEW_CUSTOMER_ALL("VIEW_CUSTOMER_ALL","查看工作组客户"),
    UPDATE_CUSTOMER_MEMBER("UPDATE_CUSTOMER_MEMBER","更新个人工作空间客户"),
    UPDATE_CUSTOMER_ALL("UPDATE_CUSTOMER_ALL","更新工作组客户"),
    DELETE_CUSTOMER_MEMBER("DELETE_CUSTOMER_MEMBER","删除个人工作空间客户"),
    DELETE_CUSTOMER_ALL("DELETE_CUSTOMER_ALL","删除工作组客户"),
    UPDATE_CUSTOMER_TRANSFER_MEMBER("UPDATE_CUSTOMER_TRANSFER_MEMBER","转移个人工作空间客户"),
    UPDATE_CUSTOMER_TRANSFER_ALL("UPDATE_CUSTOMER_TRANSFER_ALL","转移工作组客户"),
    ;
    /**
     * 枚举的值
     */
    private String code;

    /**
     * 枚举描述
     */
    private String value;

    PermissionEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static PermissionEnum getEnumByCode(String code) {
        for (PermissionEnum attachTypeEnum : PermissionEnum.values()) {
            if (attachTypeEnum.getCode().equals(code)) {
                return attachTypeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
