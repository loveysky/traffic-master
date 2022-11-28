package com.fan.system.info;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_role")
public class RoleInfo {

  @TableId(value = "rid", type = IdType.AUTO)
  private long rid;
  private String rname;
  private String rtype;
  private String t1;


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }


  public String getRname() {
    return rname;
  }

  public void setRname(String rname) {
    this.rname = rname;
  }


  public String getRtype() {
    return rtype;
  }

  public void setRtype(String rtype) {
    this.rtype = rtype;
  }


  public String getT1() {
    return t1;
  }

  public void setT1(String t1) {
    this.t1 = t1;
  }

}
