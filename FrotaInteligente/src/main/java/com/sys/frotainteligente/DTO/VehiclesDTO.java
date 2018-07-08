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
    private String last_update;
    private String last_west_coord;
    private String last_south_coord;
    private String last_speed_info;
	

    public VehiclesDTO(long id, String license, String name, String phone_number, String chassi_number, String description, String updated_at, String deleted_at,
	                   String last_update, String last_west_coord, String last_south_coord, String last_speed_info) {
        this.id = id;
        this.license = license;
        this.name = name;
        this.phone_number = phone_number;
        this.chassi_number = chassi_number;
        this.description = description;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
		this.last_update = last_update;
		this.last_west_coord = last_west_coord;
		this.last_south_coord = last_south_coord;
		this.last_speed_info = last_speed_info;
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
	
	public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
	
	public String getLast_west_coord() {
        return last_west_coord;
    }

    public void setLast_west_coord(String last_west_coord) {
        this.last_west_coord = last_west_coord;
    }
	
	public String getLast_south_coord() {
        return last_south_coord;
    }

    public void setLast_south_coord(String last_south_coord) {
        this.last_south_coord = last_south_coord;
    }
	
	public String getLast_speed_info() {
        return last_speed_info;
    }

    public void setLast_speed_info(String last_speed_info) {
        this.last_speed_info = last_speed_info;
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
				", last_update=" + last_update +
				", last_west_coord=" + last_west_coord +
				", last_south_coord=" + last_south_coord +
				", last_speed_info=" + last_speed_info +
                '}';
    }
}
