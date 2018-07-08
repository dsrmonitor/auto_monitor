package com.sys.frotainteligente.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "VEHICLES")
public class Vehicles {
    @Id
    @Column(name = "ID")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column( name = "LICENSE")
    @Size(max=8)
    private String license;

    @Column( name = "NAME")
    @Size(max=30)
    private String name;

    @Column( name = "PHONE_NUMBER")
    @Size(max=30)
    private String phone_number;

    @Column( name = "CHASSI_NUMBER")
    @Size(max=30)
    private String chassi_number;

    @Column( name = "DESCRIPTION")
    @Size(max=200)
    private String description;

    @Column( name = "UPDATED_AT")
    private Date updated_at;

    @Column( name = "DELETED_AT")
    private Date deleted_at;
	
	@Column( name = "LAST_UPDATE")
    private Date last_update;
	
	@Column( name = "LAST_WEST_COORD")
    @Size(max=40)
    private String last_west_coord;
	
	@Column( name = "LAST_SOUTH_COORD")
    @Size(max=40)
    private String last_south_coord;
	
	@Column( name = "LAST_SPEED_INFO")
    @Size(max=40)
    private String last_speed_info;

    public Vehicles(@Size(max = 8) String license, @Size(max = 30) String name, @Size(max = 30) String phone_number, @Size(max = 30) String chassi_number, @Size(max = 200) String description, Date updated_at, 
	                Date deleted_at, Date last_update, @Size(max = 40) String last_west_coord, @Size(max = 40) String last_south_coord,
					@Size(max = 40) String last_speed_info) {
						
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

    public Vehicles(){

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

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
	
	public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
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
        return "Vehicles{" +
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
