/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author LuigiZ
 */
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"created_at",
"entry_id",
"field1",
"field2",
"field3",
"field4",
"field5",
"field6",
"field7",
"field8"
})
public class Feed {

@JsonProperty("created_at")
//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date createdAt;
@JsonProperty("entry_id")
private Integer entryId;
@JsonProperty("field1")
private BigDecimal field1;
@JsonProperty("field2")
private BigDecimal field2;
@JsonProperty("field3")
private BigDecimal field3;
@JsonProperty("field4")
private BigDecimal field4;
@JsonProperty("field5")
private BigDecimal field5;
@JsonProperty("field6")
private BigDecimal field6;
@JsonProperty("field7")
private BigDecimal field7;
@JsonProperty("field8")
private BigDecimal field8;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Feed() {
    }

    public Feed(Date createdAt, Integer entryId, BigDecimal field1, BigDecimal field2, BigDecimal field3, BigDecimal field4, BigDecimal field5, BigDecimal field6, BigDecimal field7, BigDecimal field8) {
        this.createdAt = createdAt;
        this.entryId = entryId;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
        this.field7 = field7;
        this.field8 = field8;
    }





@JsonProperty("created_at")
public Date getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(Date createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("entry_id")
public Integer getEntryId() {
return entryId;
}

@JsonProperty("entry_id")
public void setEntryId(Integer entryId) {
this.entryId = entryId;
}

    public BigDecimal getField1() {
        return field1;
    }

    public void setField1(BigDecimal field1) {
        this.field1 = field1;
    }

    public BigDecimal getField2() {
        return field2;
    }

    public void setField2(BigDecimal field2) {
        this.field2 = field2;
    }

    public BigDecimal getField3() {
        return field3;
    }

    public void setField3(BigDecimal field3) {
        this.field3 = field3;
    }

    public BigDecimal getField4() {
        return field4;
    }

    public void setField4(BigDecimal field4) {
        this.field4 = field4;
    }

    public BigDecimal getField5() {
        return field5;
    }

    public void setField5(BigDecimal field5) {
        this.field5 = field5;
    }

    public BigDecimal getField6() {
        return field6;
    }

    public void setField6(BigDecimal field6) {
        this.field6 = field6;
    }

    public BigDecimal getField7() {
        return field7;
    }

    public void setField7(BigDecimal field7) {
        this.field7 = field7;
    }

    public BigDecimal getField8() {
        return field8;
    }

    public void setField8(BigDecimal field8) {
        this.field8 = field8;
    }


    public BigDecimal getField(int nField){
        BigDecimal valore = null;
        switch (nField) {
            case 1:
                valore = getField1();
                break;
            case 2:
                valore = getField2();
                break;
            case 3:
                valore = getField3();
                break;
            case 4:
                valore = getField4();
                break;
            case 5:
                valore = getField5();
                break;
            case 6:
                valore = getField6();
                break;
            case 7:
                valore = getField7();
                break;
            case 8:
                valore = getField8();
                break;
            default:
                throw new AssertionError();
        }
        return valore;
    }

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

    @Override
    public String toString() {
        return "Feed{" + "createdAt=" + createdAt + ", entryId=" + entryId + ", field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + ", field4=" + field4 + ", field5=" + field5 + ", field6=" + field6 + ", field7=" + field7 + ", field8=" + field8 + ", additionalProperties=" + additionalProperties + '}';
    }

}