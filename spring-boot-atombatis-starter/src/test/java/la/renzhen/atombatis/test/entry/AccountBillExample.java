package la.renzhen.atombatis.test.entry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import la.renzhen.db.atombatis.OffsetLimit;
import la.renzhen.db.atombatis.ShardSelect;
import la.renzhen.db.atombatis.TableSubfix;

public class AccountBillExample implements ShardSelect<Integer>, OffsetLimit, TableSubfix {
    /**
    account_bill
     */
    protected String orderByClause;

    /**
    account_bill
     */
    protected boolean distinct;

    /**
    account_bill
     */
    protected List<Criteria> oredCriteria;

    private String tableSubfix;

    Integer offset;

    Integer limit;

    List<Integer> a_b_slave = new ArrayList<java.lang.Integer>();

    public AccountBillExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        criteria.a_b_sample = this;
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setTableSubfix(String tableSubfix) {
        this.tableSubfix=tableSubfix;
    }

    public String getTableSubfix() {
        return tableSubfix;
    }

    public void setOffset(Integer offset) {
        this.offset=offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setLimit(Integer limit) {
        this.limit=limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setABSlave(List<Integer> a_b_slave) {
        this.a_b_slave=a_b_slave;
    }

    public List<Integer> getABSlave() {
        return a_b_slave;
    }

    /**
    account_bill
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        AccountBillExample a_b_sample;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            addSlave(property,value);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            addSlave(property,value1);
            addSlave(property,value2);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("operator_id not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andFromAmtIsNull() {
            addCriterion("from_amt is null");
            return (Criteria) this;
        }

        public Criteria andFromAmtIsNotNull() {
            addCriterion("from_amt is not null");
            return (Criteria) this;
        }

        public Criteria andFromAmtEqualTo(BigDecimal value) {
            addCriterion("from_amt =", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtNotEqualTo(BigDecimal value) {
            addCriterion("from_amt <>", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtGreaterThan(BigDecimal value) {
            addCriterion("from_amt >", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("from_amt >=", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtLessThan(BigDecimal value) {
            addCriterion("from_amt <", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("from_amt <=", value, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtIn(List<BigDecimal> values) {
            addCriterion("from_amt in", values, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtNotIn(List<BigDecimal> values) {
            addCriterion("from_amt not in", values, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("from_amt between", value1, value2, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andFromAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("from_amt not between", value1, value2, "fromAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtIsNull() {
            addCriterion("trans_amt is null");
            return (Criteria) this;
        }

        public Criteria andTransAmtIsNotNull() {
            addCriterion("trans_amt is not null");
            return (Criteria) this;
        }

        public Criteria andTransAmtEqualTo(BigDecimal value) {
            addCriterion("trans_amt =", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotEqualTo(BigDecimal value) {
            addCriterion("trans_amt <>", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtGreaterThan(BigDecimal value) {
            addCriterion("trans_amt >", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trans_amt >=", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtLessThan(BigDecimal value) {
            addCriterion("trans_amt <", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trans_amt <=", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtIn(List<BigDecimal> values) {
            addCriterion("trans_amt in", values, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotIn(List<BigDecimal> values) {
            addCriterion("trans_amt not in", values, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trans_amt between", value1, value2, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trans_amt not between", value1, value2, "transAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtIsNull() {
            addCriterion("to_amt is null");
            return (Criteria) this;
        }

        public Criteria andToAmtIsNotNull() {
            addCriterion("to_amt is not null");
            return (Criteria) this;
        }

        public Criteria andToAmtEqualTo(BigDecimal value) {
            addCriterion("to_amt =", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtNotEqualTo(BigDecimal value) {
            addCriterion("to_amt <>", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtGreaterThan(BigDecimal value) {
            addCriterion("to_amt >", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("to_amt >=", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtLessThan(BigDecimal value) {
            addCriterion("to_amt <", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("to_amt <=", value, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtIn(List<BigDecimal> values) {
            addCriterion("to_amt in", values, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtNotIn(List<BigDecimal> values) {
            addCriterion("to_amt not in", values, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("to_amt between", value1, value2, "toAmt");
            return (Criteria) this;
        }

        public Criteria andToAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("to_amt not between", value1, value2, "toAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtIsNull() {
            addCriterion("actual_amt is null");
            return (Criteria) this;
        }

        public Criteria andActualAmtIsNotNull() {
            addCriterion("actual_amt is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmtEqualTo(BigDecimal value) {
            addCriterion("actual_amt =", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtNotEqualTo(BigDecimal value) {
            addCriterion("actual_amt <>", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtGreaterThan(BigDecimal value) {
            addCriterion("actual_amt >", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_amt >=", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtLessThan(BigDecimal value) {
            addCriterion("actual_amt <", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_amt <=", value, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtIn(List<BigDecimal> values) {
            addCriterion("actual_amt in", values, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtNotIn(List<BigDecimal> values) {
            addCriterion("actual_amt not in", values, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_amt between", value1, value2, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andActualAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_amt not between", value1, value2, "actualAmt");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIsNull() {
            addCriterion("charge_fee is null");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIsNotNull() {
            addCriterion("charge_fee is not null");
            return (Criteria) this;
        }

        public Criteria andChargeFeeEqualTo(BigDecimal value) {
            addCriterion("charge_fee =", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotEqualTo(BigDecimal value) {
            addCriterion("charge_fee <>", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeGreaterThan(BigDecimal value) {
            addCriterion("charge_fee >", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_fee >=", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeLessThan(BigDecimal value) {
            addCriterion("charge_fee <", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("charge_fee <=", value, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeIn(List<BigDecimal> values) {
            addCriterion("charge_fee in", values, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotIn(List<BigDecimal> values) {
            addCriterion("charge_fee not in", values, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_fee between", value1, value2, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andChargeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("charge_fee not between", value1, value2, "chargeFee");
            return (Criteria) this;
        }

        public Criteria andBondIdIsNull() {
            addCriterion("bond_id is null");
            return (Criteria) this;
        }

        public Criteria andBondIdIsNotNull() {
            addCriterion("bond_id is not null");
            return (Criteria) this;
        }

        public Criteria andBondIdEqualTo(Integer value) {
            addCriterion("bond_id =", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotEqualTo(Integer value) {
            addCriterion("bond_id <>", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdGreaterThan(Integer value) {
            addCriterion("bond_id >", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bond_id >=", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdLessThan(Integer value) {
            addCriterion("bond_id <", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdLessThanOrEqualTo(Integer value) {
            addCriterion("bond_id <=", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdIn(List<Integer> values) {
            addCriterion("bond_id in", values, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotIn(List<Integer> values) {
            addCriterion("bond_id not in", values, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdBetween(Integer value1, Integer value2) {
            addCriterion("bond_id between", value1, value2, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bond_id not between", value1, value2, "bondId");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNull() {
            addCriterion("bill_type is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("bill_type is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(Byte value) {
            addCriterion("bill_type =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(Byte value) {
            addCriterion("bill_type <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(Byte value) {
            addCriterion("bill_type >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("bill_type >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(Byte value) {
            addCriterion("bill_type <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(Byte value) {
            addCriterion("bill_type <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<Byte> values) {
            addCriterion("bill_type in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<Byte> values) {
            addCriterion("bill_type not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(Byte value1, Byte value2) {
            addCriterion("bill_type between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("bill_type not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andUserInfoIsNull() {
            addCriterion("user_info is null");
            return (Criteria) this;
        }

        public Criteria andUserInfoIsNotNull() {
            addCriterion("user_info is not null");
            return (Criteria) this;
        }

        public Criteria andUserInfoEqualTo(String value) {
            addCriterion("user_info =", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoNotEqualTo(String value) {
            addCriterion("user_info <>", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoGreaterThan(String value) {
            addCriterion("user_info >", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoGreaterThanOrEqualTo(String value) {
            addCriterion("user_info >=", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoLessThan(String value) {
            addCriterion("user_info <", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoLessThanOrEqualTo(String value) {
            addCriterion("user_info <=", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoLike(String value) {
            addCriterion("user_info like", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoNotLike(String value) {
            addCriterion("user_info not like", value, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoIn(List<String> values) {
            addCriterion("user_info in", values, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoNotIn(List<String> values) {
            addCriterion("user_info not in", values, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoBetween(String value1, String value2) {
            addCriterion("user_info between", value1, value2, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUserInfoNotBetween(String value1, String value2) {
            addCriterion("user_info not between", value1, value2, "userInfo");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        void addSlave(String property, Object value) {
            if(property.equals("userId")){
                  List list = new ArrayList();
                  if(value instanceof List){
                        list.addAll((List)value);
                      }else{
                              list.add(value);
                          }
                          for (Object o : list) {
                                  a_b_sample.a_b_slave.add((java.lang.Integer)o);
                              }
                        }
                    }
    }

    /**
     * account_bill
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
    account_bill
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}