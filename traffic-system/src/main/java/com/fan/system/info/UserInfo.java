package com.fan.system.info;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 对接数据库的实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_user")
public class UserInfo implements Serializable {


  private long uid;
  private String uname;
  private String uaccount;
  private String upass;
  private String umail;
  private String uphone;
  private String t1;
  private String t2;

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getUaccount() {
    return uaccount;
  }

  public void setUaccount(String uaccount) {
    this.uaccount = uaccount;
  }

  public String getUpass() {
    return upass;
  }

  public void setUpass(String upass) {
    this.upass = upass;
  }

  public String getUmail() {
    return umail;
  }

  public void setUmail(String umail) {
    this.umail = umail;
  }

  public String getUphone() {
    return uphone;
  }

  public void setUphone(String uphone) {
    this.uphone = uphone;
  }

  public String getT1() {
    return t1;
  }

  public void setT1(String t1) {
    this.t1 = t1;
  }

  public String getT2() {
    return t2;
  }

  public void setT2(String t2) {
    this.t2 = t2;
  }

}
