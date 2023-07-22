import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { PedidosComponent } from './pages/pedidos/pedidos.component';
import { AuthGuardGuard } from './guards/auth-guard.guard';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "home",
    component: HomeComponent,
    canActivate: [AuthGuardGuard]
  },
  {
    path: "pedidos",
    component: PedidosComponent,
    canActivate: [AuthGuardGuard]
  },
  {
    path: 'catalogos',
    loadChildren: () => import('./pages/catalogos/catalogos.module')
      .then(m => m.CatalogosModule),
    canActivate: [AuthGuardGuard]
  },
  {
    path: "**",
    redirectTo: "login"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
