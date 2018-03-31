package la.renzhen.atombatis.test.entry;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import la.renzhen.db.atombatis.ShardSelect;
import la.renzhen.db.atombatis.TableSubfix;

public class AccountBill implements ShardSelect<Integer>, TableSubfix, Serializable {
    /**
    
     */
    private Integer id;

    /**
    
     */
    private Integer userId;

    /**
    
     */
    private String operatorId;

    /**
    
     */
    private BigDecimal fromAmt;

    /**
    
     */
    private BigDecimal transAmt;

    /**
    
     */
    private BigDecimal toAmt;

    /**
    
     */
    private BigDecimal actualAmt;

    /**
    
     */
    private BigDecimal fee;

    /**
    
     */
    private BigDecimal chargeFee;

    /**
    
     */
    private Integer bondId;

    /**
    
     */
    private Byte billType;

    /**
    
     */
    private Byte status;

    /**
    
     */
    private String info;

    /**
    
     */
    private String userInfo;

    /**
    
     */
    private Date updateTime;

    /**
    
     */
    private Date createTime;

    /**
    account_bill
     */
    private static final long serialVersionUID = 1L;

    private String tableSubfix;

    List<Integer> a_b_slave = new ArrayList<java.lang.Integer>();

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return user_id 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
        this.a_b_slave.add(userId);
    }

    /**
     * 
     * @return operator_id 
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 
     * @param operatorId 
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     * 
     * @return from_amt 
     */
    public BigDecimal getFromAmt() {
        return fromAmt;
    }

    /**
     * 
     * @param fromAmt 
     */
    public void setFromAmt(BigDecimal fromAmt) {
        this.fromAmt = fromAmt;
    }

    /**
     * 
     * @return trans_amt 
     */
    public BigDecimal getTransAmt() {
        return transAmt;
    }

    /**
     * 
     * @param transAmt 
     */
    public void setTransAmt(BigDecimal transAmt) {
        this.transAmt = transAmt;
    }

    /**
     * 
     * @return to_amt 
     */
    public BigDecimal getToAmt() {
        return toAmt;
    }

    /**
     * 
     * @param toAmt 
     */
    public void setToAmt(BigDecimal toAmt) {
        this.toAmt = toAmt;
    }

    /**
     * 
     * @return actual_amt 
     */
    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    /**
     * 
     * @param actualAmt 
     */
    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }

    /**
     * 
     * @return fee 
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 
     * @param fee 
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 
     * @return charge_fee 
     */
    public BigDecimal getChargeFee() {
        return chargeFee;
    }

    /**
     * 
     * @param chargeFee 
     */
    public void setChargeFee(BigDecimal chargeFee) {
        this.chargeFee = chargeFee;
    }

    /**
     * 
     * @return bond_id 
     */
    public Integer getBondId() {
        return bondId;
    }

    /**
     * 
     * @param bondId 
     */
    public void setBondId(Integer bondId) {
        this.bondId = bondId;
    }

    /**
     * 
     * @return bill_type 
     */
    public Byte getBillType() {
        return billType;
    }

    /**
     * 
     * @param billType 
     */
    public void setBillType(Byte billType) {
        this.billType = billType;
    }

    /**
     * 
     * @return status 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 
     * @return info 
     */
    public String getInfo() {
        return info;
    }

    /**
     * 
     * @param info 
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * 
     * @return user_info 
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * 
     * @param userInfo 
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", fromAmt=").append(fromAmt);
        sb.append(", transAmt=").append(transAmt);
        sb.append(", toAmt=").append(toAmt);
        sb.append(", actualAmt=").append(actualAmt);
        sb.append(", fee=").append(fee);
        sb.append(", chargeFee=").append(chargeFee);
        sb.append(", bondId=").append(bondId);
        sb.append(", billType=").append(billType);
        sb.append(", status=").append(status);
        sb.append(", info=").append(info);
        sb.append(", userInfo=").append(userInfo);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public void setTableSubfix(String tableSubfix) {
        this.tableSubfix=tableSubfix;
    }

    public String getTableSubfix() {
        return tableSubfix;
    }

    public void setABSlave(List<Integer> a_b_slave) {
        this.a_b_slave=a_b_slave;
    }

    public List<Integer> getABSlave() {
        return a_b_slave;
    }
}