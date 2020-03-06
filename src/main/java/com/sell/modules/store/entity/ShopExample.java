package com.sell.modules.store.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopExample() {
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
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

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
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
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

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andLogoImgIsNull() {
            addCriterion("logo_img is null");
            return (Criteria) this;
        }

        public Criteria andLogoImgIsNotNull() {
            addCriterion("logo_img is not null");
            return (Criteria) this;
        }

        public Criteria andLogoImgEqualTo(String value) {
            addCriterion("logo_img =", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotEqualTo(String value) {
            addCriterion("logo_img <>", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgGreaterThan(String value) {
            addCriterion("logo_img >", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgGreaterThanOrEqualTo(String value) {
            addCriterion("logo_img >=", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLessThan(String value) {
            addCriterion("logo_img <", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLessThanOrEqualTo(String value) {
            addCriterion("logo_img <=", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLike(String value) {
            addCriterion("logo_img like", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotLike(String value) {
            addCriterion("logo_img not like", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgIn(List<String> values) {
            addCriterion("logo_img in", values, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotIn(List<String> values) {
            addCriterion("logo_img not in", values, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgBetween(String value1, String value2) {
            addCriterion("logo_img between", value1, value2, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotBetween(String value1, String value2) {
            addCriterion("logo_img not between", value1, value2, "logoImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgIsNull() {
            addCriterion("store_img is null");
            return (Criteria) this;
        }

        public Criteria andStoreImgIsNotNull() {
            addCriterion("store_img is not null");
            return (Criteria) this;
        }

        public Criteria andStoreImgEqualTo(String value) {
            addCriterion("store_img =", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgNotEqualTo(String value) {
            addCriterion("store_img <>", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgGreaterThan(String value) {
            addCriterion("store_img >", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgGreaterThanOrEqualTo(String value) {
            addCriterion("store_img >=", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgLessThan(String value) {
            addCriterion("store_img <", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgLessThanOrEqualTo(String value) {
            addCriterion("store_img <=", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgLike(String value) {
            addCriterion("store_img like", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgNotLike(String value) {
            addCriterion("store_img not like", value, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgIn(List<String> values) {
            addCriterion("store_img in", values, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgNotIn(List<String> values) {
            addCriterion("store_img not in", values, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgBetween(String value1, String value2) {
            addCriterion("store_img between", value1, value2, "storeImg");
            return (Criteria) this;
        }

        public Criteria andStoreImgNotBetween(String value1, String value2) {
            addCriterion("store_img not between", value1, value2, "storeImg");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("category_id like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("category_id not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursIsNull() {
            addCriterion("opening_hours is null");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursIsNotNull() {
            addCriterion("opening_hours is not null");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursEqualTo(String value) {
            addCriterion("opening_hours =", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursNotEqualTo(String value) {
            addCriterion("opening_hours <>", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursGreaterThan(String value) {
            addCriterion("opening_hours >", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursGreaterThanOrEqualTo(String value) {
            addCriterion("opening_hours >=", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursLessThan(String value) {
            addCriterion("opening_hours <", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursLessThanOrEqualTo(String value) {
            addCriterion("opening_hours <=", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursLike(String value) {
            addCriterion("opening_hours like", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursNotLike(String value) {
            addCriterion("opening_hours not like", value, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursIn(List<String> values) {
            addCriterion("opening_hours in", values, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursNotIn(List<String> values) {
            addCriterion("opening_hours not in", values, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursBetween(String value1, String value2) {
            addCriterion("opening_hours between", value1, value2, "openingHours");
            return (Criteria) this;
        }

        public Criteria andOpeningHoursNotBetween(String value1, String value2) {
            addCriterion("opening_hours not between", value1, value2, "openingHours");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNull() {
            addCriterion("notice is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIsNotNull() {
            addCriterion("notice is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEqualTo(String value) {
            addCriterion("notice =", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotEqualTo(String value) {
            addCriterion("notice <>", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThan(String value) {
            addCriterion("notice >", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("notice >=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThan(String value) {
            addCriterion("notice <", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLessThanOrEqualTo(String value) {
            addCriterion("notice <=", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeLike(String value) {
            addCriterion("notice like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotLike(String value) {
            addCriterion("notice not like", value, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeIn(List<String> values) {
            addCriterion("notice in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotIn(List<String> values) {
            addCriterion("notice not in", values, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeBetween(String value1, String value2) {
            addCriterion("notice between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andNoticeNotBetween(String value1, String value2) {
            addCriterion("notice not between", value1, value2, "notice");
            return (Criteria) this;
        }

        public Criteria andSendCostIsNull() {
            addCriterion("send_cost is null");
            return (Criteria) this;
        }

        public Criteria andSendCostIsNotNull() {
            addCriterion("send_cost is not null");
            return (Criteria) this;
        }

        public Criteria andSendCostEqualTo(BigDecimal value) {
            addCriterion("send_cost =", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostNotEqualTo(BigDecimal value) {
            addCriterion("send_cost <>", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostGreaterThan(BigDecimal value) {
            addCriterion("send_cost >", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_cost >=", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostLessThan(BigDecimal value) {
            addCriterion("send_cost <", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_cost <=", value, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostIn(List<BigDecimal> values) {
            addCriterion("send_cost in", values, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostNotIn(List<BigDecimal> values) {
            addCriterion("send_cost not in", values, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_cost between", value1, value2, "sendCost");
            return (Criteria) this;
        }

        public Criteria andSendCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_cost not between", value1, value2, "sendCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostIsNull() {
            addCriterion("delivery_cost is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostIsNotNull() {
            addCriterion("delivery_cost is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostEqualTo(BigDecimal value) {
            addCriterion("delivery_cost =", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostNotEqualTo(BigDecimal value) {
            addCriterion("delivery_cost <>", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostGreaterThan(BigDecimal value) {
            addCriterion("delivery_cost >", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_cost >=", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostLessThan(BigDecimal value) {
            addCriterion("delivery_cost <", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_cost <=", value, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostIn(List<BigDecimal> values) {
            addCriterion("delivery_cost in", values, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostNotIn(List<BigDecimal> values) {
            addCriterion("delivery_cost not in", values, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_cost between", value1, value2, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_cost not between", value1, value2, "deliveryCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostIsNull() {
            addCriterion("box_cost is null");
            return (Criteria) this;
        }

        public Criteria andBoxCostIsNotNull() {
            addCriterion("box_cost is not null");
            return (Criteria) this;
        }

        public Criteria andBoxCostEqualTo(BigDecimal value) {
            addCriterion("box_cost =", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostNotEqualTo(BigDecimal value) {
            addCriterion("box_cost <>", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostGreaterThan(BigDecimal value) {
            addCriterion("box_cost >", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("box_cost >=", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostLessThan(BigDecimal value) {
            addCriterion("box_cost <", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("box_cost <=", value, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostIn(List<BigDecimal> values) {
            addCriterion("box_cost in", values, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostNotIn(List<BigDecimal> values) {
            addCriterion("box_cost not in", values, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("box_cost between", value1, value2, "boxCost");
            return (Criteria) this;
        }

        public Criteria andBoxCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("box_cost not between", value1, value2, "boxCost");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Integer value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Integer value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Integer value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Integer value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Integer> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Integer> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Integer value1, Integer value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<BigDecimal> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<BigDecimal> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andFoodScoreIsNull() {
            addCriterion("food_score is null");
            return (Criteria) this;
        }

        public Criteria andFoodScoreIsNotNull() {
            addCriterion("food_score is not null");
            return (Criteria) this;
        }

        public Criteria andFoodScoreEqualTo(BigDecimal value) {
            addCriterion("food_score =", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreNotEqualTo(BigDecimal value) {
            addCriterion("food_score <>", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreGreaterThan(BigDecimal value) {
            addCriterion("food_score >", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("food_score >=", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreLessThan(BigDecimal value) {
            addCriterion("food_score <", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("food_score <=", value, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreIn(List<BigDecimal> values) {
            addCriterion("food_score in", values, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreNotIn(List<BigDecimal> values) {
            addCriterion("food_score not in", values, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("food_score between", value1, value2, "foodScore");
            return (Criteria) this;
        }

        public Criteria andFoodScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("food_score not between", value1, value2, "foodScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreIsNull() {
            addCriterion("pack_score is null");
            return (Criteria) this;
        }

        public Criteria andPackScoreIsNotNull() {
            addCriterion("pack_score is not null");
            return (Criteria) this;
        }

        public Criteria andPackScoreEqualTo(BigDecimal value) {
            addCriterion("pack_score =", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreNotEqualTo(BigDecimal value) {
            addCriterion("pack_score <>", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreGreaterThan(BigDecimal value) {
            addCriterion("pack_score >", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pack_score >=", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreLessThan(BigDecimal value) {
            addCriterion("pack_score <", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pack_score <=", value, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreIn(List<BigDecimal> values) {
            addCriterion("pack_score in", values, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreNotIn(List<BigDecimal> values) {
            addCriterion("pack_score not in", values, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pack_score between", value1, value2, "packScore");
            return (Criteria) this;
        }

        public Criteria andPackScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pack_score not between", value1, value2, "packScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreIsNull() {
            addCriterion("delivery_score is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreIsNotNull() {
            addCriterion("delivery_score is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreEqualTo(BigDecimal value) {
            addCriterion("delivery_score =", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreNotEqualTo(BigDecimal value) {
            addCriterion("delivery_score <>", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreGreaterThan(BigDecimal value) {
            addCriterion("delivery_score >", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_score >=", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreLessThan(BigDecimal value) {
            addCriterion("delivery_score <", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_score <=", value, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreIn(List<BigDecimal> values) {
            addCriterion("delivery_score in", values, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreNotIn(List<BigDecimal> values) {
            addCriterion("delivery_score not in", values, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_score between", value1, value2, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andDeliveryScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_score not between", value1, value2, "deliveryScore");
            return (Criteria) this;
        }

        public Criteria andTotalSalesIsNull() {
            addCriterion("total_sales is null");
            return (Criteria) this;
        }

        public Criteria andTotalSalesIsNotNull() {
            addCriterion("total_sales is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSalesEqualTo(Integer value) {
            addCriterion("total_sales =", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotEqualTo(Integer value) {
            addCriterion("total_sales <>", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesGreaterThan(Integer value) {
            addCriterion("total_sales >", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_sales >=", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesLessThan(Integer value) {
            addCriterion("total_sales <", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesLessThanOrEqualTo(Integer value) {
            addCriterion("total_sales <=", value, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesIn(List<Integer> values) {
            addCriterion("total_sales in", values, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotIn(List<Integer> values) {
            addCriterion("total_sales not in", values, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesBetween(Integer value1, Integer value2) {
            addCriterion("total_sales between", value1, value2, "totalSales");
            return (Criteria) this;
        }

        public Criteria andTotalSalesNotBetween(Integer value1, Integer value2) {
            addCriterion("total_sales not between", value1, value2, "totalSales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesIsNull() {
            addCriterion("monthly_sales is null");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesIsNotNull() {
            addCriterion("monthly_sales is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesEqualTo(String value) {
            addCriterion("monthly_sales =", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesNotEqualTo(String value) {
            addCriterion("monthly_sales <>", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesGreaterThan(String value) {
            addCriterion("monthly_sales >", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesGreaterThanOrEqualTo(String value) {
            addCriterion("monthly_sales >=", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesLessThan(String value) {
            addCriterion("monthly_sales <", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesLessThanOrEqualTo(String value) {
            addCriterion("monthly_sales <=", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesLike(String value) {
            addCriterion("monthly_sales like", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesNotLike(String value) {
            addCriterion("monthly_sales not like", value, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesIn(List<String> values) {
            addCriterion("monthly_sales in", values, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesNotIn(List<String> values) {
            addCriterion("monthly_sales not in", values, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesBetween(String value1, String value2) {
            addCriterion("monthly_sales between", value1, value2, "monthlySales");
            return (Criteria) this;
        }

        public Criteria andMonthlySalesNotBetween(String value1, String value2) {
            addCriterion("monthly_sales not between", value1, value2, "monthlySales");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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