package com.fan.system.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 对接数据库的实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {


  private long uid;

  private String uname;
  private String uaccount;
  private String upass;
  private String umail;
  private String uphone;
  private String desc;
  private String desc2;


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


  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  public String getDesc2() {
    return desc2;
  }

  public void setDesc2(String desc2) {
    this.desc2 = desc2;
  }

}
