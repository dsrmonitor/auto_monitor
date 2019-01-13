export class Vehicle{
  public id?: number;
  public license?: string;
  public name?: string;
  public imei?: string;
  public phone_number?:string;
  public chassi_number?:string;
  public description?:string;
  public updated_at?: string;
  public deleted_at?: string;
  public last_update?: string;
  public last_west_coord?: number;
  public last_south_coord?: number;
  public last_speed_info?: string;
  public inMenu?: boolean;

  constructor(){
    this.inMenu = false;
  }
}


