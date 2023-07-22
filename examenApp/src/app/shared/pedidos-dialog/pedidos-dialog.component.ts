import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthResponse, Cliente, IConfirmationData, Pedido, Select } from 'src/app/interfaces/patito.interfaces';
import { SnackComponent } from '../snack/snack.component';
import { ApiRestService } from 'src/app/services/api-rest.service';
import { ClientesDialogComponent } from '../clientes-dialog/clientes-dialog.component';

@Component({
  selector: 'app-pedidos-dialog',
  templateUrl: './pedidos-dialog.component.html',
  styleUrls: ['./pedidos-dialog.component.css']
})
export class PedidosDialogComponent implements OnInit {
  @ViewChild('umaForm')
  umaForm!: NgForm;

  dialogTitle: string = '';
  pedidoModel: Pedido = {} as Pedido;
  mode: number = 0;
  statusPedido: Select[] = [
    { value: 'P', viewValue: 'Pendiente' },
    { value: 'E', viewValue: 'Entregado' },
    { value: 'C', viewValue: 'Cancelado' },
  ];
  clientes: Cliente[] = [];


  constructor(private _apiRest: ApiRestService, @Inject(MAT_DIALOG_DATA) public editModel: any,
    public dialogRef: MatDialogRef<PedidosDialogComponent>, public dialogClientesRef: MatDialogRef<ClientesDialogComponent>, private _snackBar: MatSnackBar, public dialog: MatDialog) { }

  ngOnInit(): void {
    console.log('ngOnInit:', this.editModel);
    // Validamos el modo de operación
    if (this.editModel && this.editModel.mode === 1) {
      // Modo editar
      this.mode = this.editModel.mode;
      this.pedidoModel = { ...this.editModel.uma };
      this.dialogTitle = 'Editar Pedido';
    } else {
      // Modo nuevo
      this.mode = 0;
      this.pedidoModel = {} as Pedido;
      this.pedidoModel.fechaPedido = new Date();
      this.pedidoModel.status = 'P';
      // this.prestacionModel.aguinaldo = 15;
      this.dialogTitle = 'Nuevo Pedido';

      this._apiRest.getIp().subscribe((res: any) => {
        let ipAddress = res.ip;
        console.log('IP:', ipAddress);
        this.pedidoModel.ip = ipAddress;
      });

      this._apiRest.getClientes().subscribe((res: Cliente[]) => {
        this.clientes = res;
      });

      // Obteber de Localstorage el usuario
      let auth: AuthResponse = JSON.parse(localStorage.getItem('auth')!);

      this._apiRest.getVendedor(auth.email).subscribe({
        next: (res) => {
          res.password = '';
          this.pedidoModel.vendedor = res;
          console.log('DATA: ', this.pedidoModel);
          
        }
      });
    }
  }

  umaSubmit() {
    console.log('umaSubmit:', this.pedidoModel);
    console.log('editModel:', this.editModel);
    console.log('mode:', this.mode);

    if (this.mode == 0) {
      // Nuevo registro
      this._apiRest.savePedido(this.pedidoModel)
        .subscribe(res => {
          console.log('data:', res);

          if (!res) {
            // Guardamos si no existe
            this.dialogRef.close(this.pedidoModel);
          } else {
            // Si hay registro previo manda error
            const snackData: IConfirmationData = { text: 'Ya hay un registro con el año indicado', type: 'w' };
            this._snackBar.openFromComponent(SnackComponent, { data: snackData, duration: 3000, verticalPosition: 'top' });
          }
        });
    } else {
      this.dialogRef.close(this.pedidoModel);
    }
  }

  agregarCliente() {

  }

  data() {
    console.log(this.pedidoModel);
  }

}
