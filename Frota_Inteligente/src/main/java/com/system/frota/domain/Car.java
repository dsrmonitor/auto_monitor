package com.system.frota.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A Car.
 */
@Entity
@Table(name = "vehicles")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 8)
    @Column(name = "license", length = 8)
    private String license;

    @Size(max = 30)
    @Column(name = "name", length = 30)
    private String name;

    @Size(max = 40)
    @Column(name = "imei", length = 40)
    private String imei;

    @Size(max = 30)
    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Size(max = 30)
    @Column(name = "chassi_number", length = 30)
    private String chassiNumber;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Size(max = 40)
    @Column(name = "last_west_coord", length = 40)
    private String lastWstCoord;

    @Size(max = 40)
    @Column(name = "last_south_coord", length = 40)
    private String lastSouthCoord;

    @Size(max = 40)
    @Column(name = "last_speed_info", length = 40)
    private String lastSpeedInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public Car license(String license) {
        this.license = license;
        return this;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public Car name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public Car imei(String imei) {
        this.imei = imei;
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Car phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChassiNumber() {
        return chassiNumber;
    }

    public Car chassiNumber(String chassiNumber) {
        this.chassiNumber = chassiNumber;
        return this;
    }

    public void setChassiNumber(String chassiNumber) {
        this.chassiNumber = chassiNumber;
    }

    public String getDescription() {
        return description;
    }

    public Car description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Car updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Car deletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Car lastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastWstCoord() {
        return lastWstCoord;
    }

    public Car lastWstCoord(String lastWstCoord) {
        this.lastWstCoord = lastWstCoord;
        return this;
    }

    public void setLastWstCoord(String lastWstCoord) {
        this.lastWstCoord = lastWstCoord;
    }

    public String getLastSouthCoord() {
        return lastSouthCoord;
    }

    public Car lastSouthCoord(String lastSouthCoord) {
        this.lastSouthCoord = lastSouthCoord;
        return this;
    }

    public void setLastSouthCoord(String lastSouthCoord) {
        this.lastSouthCoord = lastSouthCoord;
    }

    public String getLastSpeedInfo() {
        return lastSpeedInfo;
    }

    public Car lastSpeedInfo(String lastSpeedInfo) {
        this.lastSpeedInfo = lastSpeedInfo;
        return this;
    }

    public void setLastSpeedInfo(String lastSpeedInfo) {
        this.lastSpeedInfo = lastSpeedInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        if (car.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + getId() +
            ", license='" + getLicense() + "'" +
            ", name='" + getName() + "'" +
            ", imei='" + getImei() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", chassiNumber='" + getChassiNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", lastUpdate='" + getLastUpdate() + "'" +
            ", lastWstCoord='" + getLastWstCoord() + "'" +
            ", lastSouthCoord='" + getLastSouthCoord() + "'" +
            ", lastSpeedInfo='" + getLastSpeedInfo() + "'" +
            "}";
    }
}
