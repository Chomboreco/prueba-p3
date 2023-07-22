import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MaterialModule } from '../material/material.module';
import { SharedModule } from "../shared/shared.module";
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PedidosComponent } from './pedidos/pedidos.component';



@NgModule({
    declarations: [
        LoginComponent,
        HomeComponent,
        PedidosComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        SharedModule
    ]
})
export class PagesModule { }
