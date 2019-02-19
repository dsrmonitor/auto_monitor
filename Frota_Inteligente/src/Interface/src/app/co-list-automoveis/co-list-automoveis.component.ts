import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSnackBar, MatSort, MatTableDataSource} from "@angular/material";
import {SelectionModel} from '@angular/cdk/collections';
import {Router} from "@angular/router";
import {CarService} from "./co-list-automoveis.service";
import {CarInterface} from "./co-list-automoveis.model";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ConstantesUtil} from "../util/constantes.util";


@Component({
  selector: 'app-co-list-automoveis',
  templateUrl: './co-list-automoveis.component.html',
  styleUrls: ['./co-list-automoveis.component.css']
})
export class CoListAutomoveisComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @BlockUI() blockUI: NgBlockUI;

  constructor( private router: Router, private carService: CarService,private snackBar: MatSnackBar) { }

  displayedColumns: string[] = ['select','name', 'chassiNumber', 'imei', 'phoneNumber'];
  dataSource;
  selection;

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit() {
    this.selection = new SelectionModel<CarInterface>(true, []);
    this.loadingCars();
  }

  loadingCars(){
    this.blockUI.start(ConstantesUtil.LOADING);
    this.carService.getCars().subscribe(res=>{
      this.blockUI.stop();
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }, err=>{
      this.blockUI.stop();
      this.snackBar.open(ConstantesUtil.FAIL_LOAD_DATA,ConstantesUtil.CLOSE,{duration: 3000});
    });
  }

  newCar(){
    this.router.navigate(['frota-inteligente/new-cars']);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  editCar(){
    this.router.navigate(['frota-inteligente/edit-cars',this.selection.selected[0].id]);
  }

  deleteCar(){
    let car: number[] = [];
    for(let item of this.selection.selected){
      car.push(item.id);
    }
    this.carService.deleteCar(car).subscribe(res =>{
      this.snackBar.open(ConstantesUtil.REMOVE_CARS,ConstantesUtil.CLOSE,{duration: 3000});
      this.loadingCars();
    });
  }
}
