package com.fan.system.info;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user-role表  用户-角色表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_user_role")
public class UserRoleInfo {

  @TableId(value = "id", type = IdType.AUTO)
  private long id;
  private long uid;
  private long rid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }

}
