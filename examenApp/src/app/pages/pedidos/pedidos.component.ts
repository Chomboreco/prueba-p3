import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Column, IConfirmationData, Pedido } from 'src/app/interfaces/patito.interfaces';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { PedidosDialogComponent } from 'src/app/shared/pedidos-dialog/pedidos-dialog.component';
import { ProductosDialogComponent } from 'src/app/shared/productos-dialog/productos-dialog.component';
import { SnackComponent } from 'src/app/shared/snack/snack.component';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {
  columns: Column[] = [
    { header: 'id', show: true, headerText: 'ID' },
    { header: 'status', show: true, headerText: 'Status' },
    { header: 'fechaPedido', show: true, headerText: 'Fecha pedido' },
    { header: 'fechaEntrega', show: true, headerText: 'Fecha entrega' },
    { header: 'ip', show: true, headerText: 'IP' },
    { header: 'cliente', show: true, headerText: 'Cliente' },
    { header: 'productos', show: true, headerText: 'Productos' },
    { header: 'vendedor', show: true, headerText: 'Vendedor' },
    { header: 'acciones', show: true, headerText: '' }
  ];

  displayedColumns: string[] = [];
  current: Pedido = {} as Pedido;
  dataSource!: MatTableDataSource<Pedido>;
  pageSize: number = 10;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _apiRest: ApiRestService, public dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.setColumns();
    this.pedidosGetData();
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

  agregarProductos(pedido: Pedido) {
    const dialogRef = this.dialog.open(ProductosDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      console.log('Pedidos dialog:', res);

      // if (res) {
      //   this._apiRest.savePedido(res)
      //     .subscribe({
      //       next: () => {
      //         const snackData: IConfirmationData = { text: 'Registro agregado exitosamente', type: 'o' };
      //         this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
      //         this.pedidosGetData();
      //       },
      //       error: () => {
      //         const snackData: IConfirmationData = { text: 'No se pudo agregar el registro', type: 'e' };
      //         this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
      //       }
      //     });
      // }
    });
  }

  createPedido() {
    const dialogRef = this.dialog.open(PedidosDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this._apiRest.savePedido(res)
          .subscribe({
            next: () => {
              const snackData: IConfirmationData = { text: 'Registro agregado exitosamente', type: 'o' };
              this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
              this.pedidosGetData();
            },
            error: () => {
              const snackData: IConfirmationData = { text: 'No se pudo agregar el registro', type: 'e' };
              this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
            }
          });
      }
    });
  }

  pedidosGetData() {
    this._apiRest.getPedidos()
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
  }

  setCurrent(element: Pedido) {
    this.current = element;
  }
}
