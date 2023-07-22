import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Column, IConfirmationData, Pedido, Producto } from 'src/app/interfaces/patito.interfaces';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { PedidosDialogComponent } from 'src/app/shared/pedidos-dialog/pedidos-dialog.component';
import { ProductosDialogComponent } from 'src/app/shared/productos-dialog/productos-dialog.component';
import { SnackComponent } from 'src/app/shared/snack/snack.component';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  
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
