import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidenavContainerComponent } from './sidenav-container/sidenav-container.component';
import { MaterialModule } from '../material/material.module';
import { SnackComponent } from './snack/snack.component';
import { LoadingComponent } from './loading/loading.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { PedidosDialogComponent } from './pedidos-dialog/pedidos-dialog.component';
import { ProductosDialogComponent } from './productos-dialog/productos-dialog.component';
import { ClientesDialogComponent } from './clientes-dialog/clientes-dialog.component';



@NgModule({
  declarations: [
    SidenavContainerComponent,
    SnackComponent,
    LoadingComponent,
    ConfirmationDialogComponent,
    PedidosDialogComponent,
    ProductosDialogComponent,
    ClientesDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    SidenavContainerComponent
  ]
})
export class SharedModule { }
