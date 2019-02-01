package com.mycompany.myapp.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Car entity.
 */
public class CarDTO implements Serializable {

    private Long id;

    @Size(max = 8)
    private String license;

    @Size(max = 30)
    private String name;

    @Size(max = 40)
    private String imei;

    @Size(max = 30)
    private String phoneNumber;

    @Size(max = 30)
    private String chassiNumber;

    @Size(max = 200)
    private String description;

    private LocalDate updatedAt;

    private LocalDate deletedAt;

    private LocalDate lastUpdate;

    @Size(max = 40)
    private String lastWstCoord;

    @Size(max = 40)
    private String lastSouthCoord;

    @Size(max = 40)
    private String lastSpeedInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChassiNumber() {
        return chassiNumber;
    }

    public void setChassiNumber(String chassiNumber) {
        this.chassiNumber = chassiNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastWstCoord() {
        return lastWstCoord;
    }

    public void setLastWstCoord(String lastWstCoord) {
        this.lastWstCoord = lastWstCoord;
    }

    public String getLastSouthCoord() {
        return lastSouthCoord;
    }

    public void setLastSouthCoord(String lastSouthCoord) {
        this.lastSouthCoord = lastSouthCoord;
    }

    public String getLastSpeedInfo() {
        return lastSpeedInfo;
    }

    public void setLastSpeedInfo(String lastSpeedInfo) {
        this.lastSpeedInfo = lastSpeedInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarDTO carDTO = (CarDTO) o;
        if (carDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarDTO{" +
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
