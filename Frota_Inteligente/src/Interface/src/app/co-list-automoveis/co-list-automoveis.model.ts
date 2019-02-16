export class Car{
  id ?: number;
  license ?: string;
  name ?: string;
  imei ?: string;
  phoneNumber ?: string;
  chassiNumber ?: string;
  description ?: string;
  updatedAt ?: Date;
  deletedAt ?: Date;
  lastUpdate ?: Date;
  lastWstCoord ?: number;
  lastSouthCoord ?: number;
  lastSpeedInfo ?: string;

  constructor(){}
}

export interface CarInterface{
  id ?: number;
  license ?: string;
  name ?: string;
  imei ?: string;
  phoneNumber ?: string;
  chassiNumber ?: string;
  description ?: string;
  updatedAt ?: Date;
  deletedAt ?: Date;
  lastUpdate ?: Date;
  lastWstCoord ?: string;
  lastSouthCoord ?: string;
  lastSpeedInfo ?: string;
}
