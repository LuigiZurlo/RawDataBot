/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Date;

/**
 *
 * @author luigizurlo
 */
@Entity
@Table(name = "measurement", schema = "raw_data_test")
@SequenceGenerator(name = "measurement_gen", sequenceName = "raw_data_test.measurement_seq_id", allocationSize = 1)

public class Measurement implements Serializable {

   private static final long serialVersionUID = -8211695100936100048L;

//        //@ManyToOne (fetch = FetchType.LAZY)
//	@JoinColumn (name = "location_id", referencedColumnName = "id")
//	private Long location_id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "location_id", referencedColumnName = "id")
   private Location location;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parameter_id", referencedColumnName = "id")
   private Parameter parameter;

   @Column(name = "timestamp")
   private Date timestamp;

   @Column(name = "value")
   private BigDecimal valore;

   public Measurement() {
   }

   public Measurement(Location location, Parameter parameter, Date timestamp, BigDecimal valore) {
      this.location = location;
      this.parameter = parameter;
      this.timestamp = timestamp;
      this.valore = valore;
   }

   public BigDecimal getValore() {
      return valore;
   }

   public void setValore(BigDecimal valore) {
      this.valore = valore;
   }

   
//   @SequenceGenerator(name="measurement_gen", sequenceName="measurement_id_seq", allocationSize=1)
//   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_gen")
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_gen")   
   @Column(name = "id", columnDefinition = "bigserial", insertable = true, updatable = true, unique = true)
   private Long id;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }



   public Parameter getParameter() {
      return parameter;
   }

   public void setParameter(Parameter parameter) {
      this.parameter = parameter;
   }

   public Date getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
   }

   public Location getLocation() {
      return location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

    @Override
    public boolean equals(Object o) {
        Measurement m = (Measurement) o;
        if(this.getTimestamp().equals(m.getTimestamp())) {
            return true;
        } else {
            return false;
        }
    }
   
   

}
