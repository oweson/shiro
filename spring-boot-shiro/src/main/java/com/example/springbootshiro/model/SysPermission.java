package com.example.springbootshiro.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 1 主键.
     */
    private String name;
    /**
     * 2 名称.
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;
    /**
     * 3 资源类型，[menu|button]
     */
    private String url;
    /**
     * 4 资源路径.
     */
    private String permission;
    /**
     * 5 权限字符串,menu例子：role:*，
     * button例子：role:create,role:update,role:delete,role:view
     */
    private Long parentId;
    /**
     * 6 父编号
     */
    private String parentIds;
    /**
     * 7 父编号列表
     */
    private Boolean available = Boolean.FALSE;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

}