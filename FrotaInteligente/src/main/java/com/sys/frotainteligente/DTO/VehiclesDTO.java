package com.sys.frotainteligente.DTO;

public class VehiclesDTO {

    private long id;
    private String license;
    private String name;
    private String phone_number;
    private String chassi_number;
    private String description;
    private String updated_at;
    private String deleted_at;

    public VehiclesDTO(long id, String license, String name, String phone_number, String chassi_number, String description, String updated_at, String deleted_at) {
        this.id = id;
        this.license = license;
        this.name = name;
        this.phone_number = phone_number;
        this.chassi_number = chassi_number;
        this.description = description;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }
    
    public VehiclesDTO(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getChassi_number() {
        return chassi_number;
    }

    public void setChassi_number(String chassi_number) {
        this.chassi_number = chassi_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpDated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        return "VehiclesDTO{" +
                "id=" + id +
                ", license='" + license + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", chassi_number='" + chassi_number + '\'' +
                ", description='" + description + '\'' +
                ", updated_at=" + updated_at +
                ", deleted_at=" + deleted_at +
                '}';
    }
}
