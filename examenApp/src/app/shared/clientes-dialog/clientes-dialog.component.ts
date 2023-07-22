import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente, Column, IConfirmationData } from 'src/app/interfaces/patito.interfaces';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { SnackComponent } from '../snack/snack.component';

@Component({
  selector: 'app-clientes-dialog',
  templateUrl: './clientes-dialog.component.html',
  styleUrls: ['./clientes-dialog.component.css']
})
export class ClientesDialogComponent implements OnInit {

  columns: Column[] = [
    { header: 'id', show: false, headerText: 'ID' },
    { header: 'nombre', show: true, headerText: 'Nombre' },
    { header: 'direccion', show: true, headerText: 'Dirección' },
    { header: 'telefono', show: true, headerText: 'Telefono' },
    { header: 'acciones', show: true, headerText: '' }
  ];

  displayedColumns: string[] = [];
  current: Cliente = {} as Cliente;
  dataSource!: MatTableDataSource<Cliente>;
  pageSize: number = 10;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private _apiRest: ApiRestService, public dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.setColumns();
    this._prestacionesGetData();
  }

   _prestacionesGetData() {
    this._apiRest.getClientes()
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

  agregarCliente() {
    
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
}
