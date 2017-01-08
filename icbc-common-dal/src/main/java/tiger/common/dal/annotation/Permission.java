package tiger.common.dal.annotation;

import tiger.common.dal.enums.PermissionEnum;
import tiger.common.dal.enums.PermissionRelation;
import tiger.common.dal.enums.RoleEnum;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 权限值
     */
    PermissionEnum[] permission() default {};

    /**
     * 角色需求
     *  ~ 至少有一个角色才能访问
     */
    RoleEnum[] role() default {RoleEnum.ADMIN, RoleEnum.CUSTOMER, RoleEnum.OWNER};

    /**
     * 是否是团队权限
     *  ~ 默认是团队权限，如果没有进入团队会直接报错
     */
    boolean workspace() default true;

    /**
     * 权限的关系
     *  ~ 默认permission之间是or关系
     */
    PermissionRelation relation() default PermissionRelation.OR;
}
