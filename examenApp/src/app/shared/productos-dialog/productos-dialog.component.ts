import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Column, IConfirmationData, Producto } from 'src/app/interfaces/patito.interfaces';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { PedidosDialogComponent } from '../pedidos-dialog/pedidos-dialog.component';
import { SnackComponent } from '../snack/snack.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-productos-dialog',
  templateUrl: './productos-dialog.component.html',
  styleUrls: ['./productos-dialog.component.css']
})
export class ProductosDialogComponent implements OnInit {
  
  columns: Column[] = [
    { header: 'id', show: false, headerText: 'ID' },
    { header: 'hawa', show: true, headerText: 'HAWA' },
    { header: 'nombre', show: true, headerText: 'Nombre' },
    { header: 'precio', show: true, headerText: 'Precio' },
    { header: 'descuento', show: true, headerText: 'Descuento' },
    { header: 'existencia', show: true, headerText: 'Existencia' },
    { header: 'acciones', show: true, headerText: '' }
  ];
  displayedColumns: string[] = [];
  current: Producto = {} as Producto;
  dataSource!: MatTableDataSource<Producto>;
  pageSize: number = 10;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _apiRest: ApiRestService, public dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.setColumns();
    this.productosGetData();
    
  }

  setColumns() {
    this.displayedColumns = [];
    this.columns.forEach(
      h => {
        if (h.show)
          this.displayedColumns.push(h.header);
      }
    )
  }

  deleteUma(_t97: any) {
    throw new Error('Method not implemented.');
  }
  editUma(_t97: any) {
    throw new Error('Method not implemented.');
  }
  createUma() {
    const dialogRef = this.dialog.open(ProductosDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this._apiRest.savePedido(res)
          .subscribe({
            next: () => {
              const snackData: IConfirmationData = { text: 'Registro agregado exitosamente', type: 'o' };
              this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
              this.productosGetData();
            },
            error: () => {
              const snackData: IConfirmationData = { text: 'No se pudo agregar el registro', type: 'e' };
              this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
            }
          });
      }
    });
  }

  productosGetData() {
    this._apiRest.getProductos()
      .subscribe({
        next: res => {
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;

          console.log(this.dataSource.data);
          
        },
        error: err => {
          const snackData: IConfirmationData = { text: 'No se pudo obtener la información', type: 'e' };
          this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
        }
      });
  }

  hideShowColumns() {
    // const dialogRef = this.dialog.open(ColumnsDialogComponent, { data: this.columns, disableClose: true });
    // dialogRef.afterClosed().subscribe(res => {

    //   if (res) {
    //     this.columns = res;
    //     this.setColumns();
    //   }
    // });
  }

  setCurrent(element: Producto) {
    this.current = element;
  }
}
