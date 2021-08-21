package com.bootdo.system.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 会员表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-20 14:59:38
 */
public class MemberDO implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getTeamProfitAmount() {
    return teamProfitAmount;
  }

  public void setTeamProfitAmount(String teamProfitAmount) {
    this.teamProfitAmount = teamProfitAmount;
  }

  //
  private Integer id;
  //会员昵称
  private String nickName;
  //用户名
  private String username;
  //密码
  private String password;
  //创建时间
  private Date createTime;
  //个人的邀请码
  private String ownerInviteCode;
  //推荐人的邀请码
  private String sourceInviteCode;
  //总额
  private String totalAmount;
  //可用余额
  private String freeAmount;
  //冻结额度
  private String lockAmount;
  //总盈利
  private String profitAmount;

  //在途资金
  private String transitAmount;

  //会员等级
  private Integer level;

  //佣金比例 万100， 万80， 万60
  private Integer commissionRate;
  //银行卡号
  private String bankNo;

  //开户人姓名
  private String bankName;

  //身份证
  private String cardNo;

  //真实姓名
  private String reallyName;

  //安卓的version

  private String android_verison;

  private String kefu_url;
  private String walletAddress;
  private List<MemberBankDO> banks;

  private Integer totalAmountUstd;
  private String cnyPrice;

  private String aiFreeAmount;


  public String getAndroid_verison() {
    return android_verison;
  }

  public void setAndroid_verison(String android_verison) {
    this.android_verison = android_verison;
  }

  public String getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(String totalIncome) {
    this.totalIncome = totalIncome;
  }

  public String getTotalOutcome() {
    return totalOutcome;
  }

  public void setTotalOutcome(String totalOutcome) {
    this.totalOutcome = totalOutcome;
  }

  //支行名称
  private String bankBranchName;

  //开户地址
  private String bankAddress;

  //当日收益
  private String dayProfitAmount;

  //团队收益
  private String teamProfitAmount;

  //智能合约
  private String agreemtFlag;

  //推荐链接
  private String myUrl;
  private String version;
  private String iosUrl;
  private String andriodUrl;
  private String notice;

  private String totalIncome;
  private String totalOutcome;

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  private String webSite;


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getIosUrl() {
    return iosUrl;
  }

  public void setIosUrl(String iosUrl) {
    this.iosUrl = iosUrl;
  }

  public String getAndriodUrl() {
    return andriodUrl;
  }

  public void setAndriodUrl(String andriodUrl) {
    this.andriodUrl = andriodUrl;
  }

  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

  public String getMyUrl() {
    return myUrl;
  }

  public void setMyUrl(String myUrl) {
    this.myUrl = myUrl;
  }

  public String getAgreemtFlag() {
    return agreemtFlag;
  }

  public void setAgreemtFlag(String agreemtFlag) {
    this.agreemtFlag = agreemtFlag;
  }

  public String getBankAddress() {
    return bankAddress;
  }

  public void setBankAddress(String bankAddress) {
    this.bankAddress = bankAddress;
  }

  public String getReallyName() {
    return reallyName;
  }

  public void setReallyName(String reallyName) {
    this.reallyName = reallyName;
  }

  public String getBankBranchName() {
    return bankBranchName;
  }

  public void setBankBranchName(String bankBranchName) {
    this.bankBranchName = bankBranchName;
  }

  /**
   * 设置：
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取：
   */
  public Integer getId() {
    return id;
  }

  /**
   * 设置：会员昵称
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  /**
   * 获取：会员昵称
   */
  public String getNickName() {
    return nickName;
  }

  /**
   * 设置：用户名
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * 获取：用户名
   */
  public String getUsername() {
    return username;
  }

  /**
   * 设置：密码
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 获取：密码
   */
  public String getPassword() {
    return password;
  }

  /**
   * 设置：创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 获取：创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 设置：个人的邀请码
   */
  public void setOwnerInviteCode(String ownerInviteCode) {
    this.ownerInviteCode = ownerInviteCode;
  }

  /**
   * 获取：个人的邀请码
   */
  public String getOwnerInviteCode() {
    return ownerInviteCode;
  }

  /**
   * 设置：推荐人的邀请码
   */
  public void setSourceInviteCode(String sourceInviteCode) {
    this.sourceInviteCode = sourceInviteCode;
  }

  /**
   * 获取：推荐人的邀请码
   */
  public String getSourceInviteCode() {
    return sourceInviteCode;
  }

  /**
   * 设置：总额
   */
  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;

  }

  /**
   * 获取：总额
   */
  public String getTotalAmount() {
    return totalAmount;
  }

  /**
   * 设置：可用余额
   */
  public void setFreeAmount(String freeAmount) {

    this.freeAmount = freeAmount;


  }

  /**
   * 获取：可用余额
   */
  public String getFreeAmount() {
    return this.freeAmount;
  }

  /**
   * 设置：冻结额度
   */
  public void setLockAmount(String lockAmount) {
    this.lockAmount = lockAmount;

  }

  /**
   * 获取：冻结额度
   */
  public String getLockAmount() {
    return this.lockAmount;
  }

  /**
   * 设置：总盈利
   */
  public void setProfitAmount(String profitAmount) {
    this.profitAmount = profitAmount;
  }

  /**
   * 获取：总盈利
   */
  public String getProfitAmount() {
    return this.profitAmount;
  }

  /**
   * 设置：在途资金
   */
  public void setTransitAmount(String transitAmount) {
    this.transitAmount = transitAmount;
  }

  /**
   * 获取：在途资金
   */
  public String getTransitAmount() {
    return this.transitAmount;
  }

  /**
   * 设置：会员等级
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * 获取：会员等级
   */
  public Integer getLevel() {
    return level;
  }

  /**
   * 设置：佣金比例 万100， 万80， 万60
   */
  public void setCommissionRate(Integer commissionRate) {
    this.commissionRate = commissionRate;
  }

  /**
   * 获取：佣金比例 万100， 万80， 万60
   */
  public Integer getCommissionRate() {
    return commissionRate;
  }

  /**
   * 设置：银行卡号
   */
  public void setBankNo(String bankNo) {
    this.bankNo = bankNo;
  }

  /**
   * 获取：银行卡号
   */
  public String getBankNo() {
    return bankNo;
  }

  /**
   * 设置：开户人姓名
   */
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  /**
   * 获取：开户人姓名
   */
  public String getBankName() {
    return bankName;
  }

  /**
   * 设置：银行卡号
   */
  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  /**
   * 获取：银行卡号
   */
  public String getCardNo() {
    return cardNo;
  }

  public String getDayProfitAmount() {

    if (dayProfitAmount == null) {
      return "0.00";
    }
    return dayProfitAmount;
  }

  public void setDayProfitAmount(String dayProfitAmount) {
    this.dayProfitAmount = dayProfitAmount;
  }

  public String getKefu_url() {
    return kefu_url;
  }

  public void setKefu_url(String kefu_url) {
    this.kefu_url = kefu_url;
  }

  public String getWalletAddress() {
    return walletAddress;
  }

  public void setWalletAddress(String walletAddress) {
    this.walletAddress = walletAddress;
  }

  public List<MemberBankDO> getBanks() {
    return banks;
  }

  public void setBanks(List<MemberBankDO> banks) {
    this.banks = banks;
  }

  public Integer getTotalAmountUstd() {
    return totalAmountUstd;
  }

  public void setTotalAmountUstd(Integer totalAmountUstd) {
    this.totalAmountUstd = totalAmountUstd;
  }

  public String getCnyPrice() {
    return cnyPrice;
  }

  public void setCnyPrice(String cnyPrice) {
    this.cnyPrice = cnyPrice;
  }

  public String getAiFreeAmount() {
    return aiFreeAmount;
  }

  public void setAiFreeAmount(String aiFreeAmount) {
    this.aiFreeAmount = aiFreeAmount;
  }
}

