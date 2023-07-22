import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { AuthResponse, Cliente, Login, Pedido, Producto, Vendedor } from '../interfaces/patito.interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiRestService implements OnInit {
  private apiDomine: string = 'http://localhost:8080/api/v1';
  private token: string = '';
  private headers = new HttpHeaders();

  ngOnInit(): void {
  }

  constructor(private _http: HttpClient) { 
    let auth: AuthResponse = JSON.parse(localStorage.getItem('auth')!);
    auth.accessToken = 'Bearer ' + auth.accessToken;
    this.headers = this.headers.set('Authorization', auth.accessToken);

  }

  getPedidos(): Observable<Pedido[]> {
    return this._http.get<Pedido[]>(`${this.apiDomine}/pedidos`, { 'headers': this.headers });
  }

  savePedido(pedido: Pedido): Observable<Pedido> {
    return this._http.post<Pedido>(`${this.apiDomine}/pedidos`, pedido, { headers: this.headers });
  }

  getProductos(): Observable<Producto[]> {
    return this._http.get<Producto[]>(`${this.apiDomine}/productos`, { headers: this.headers });
  }

  saveProducto(producto: Producto): Observable<Producto> {
    return this._http.post<Producto>(`${this.apiDomine}/productos`, producto, { headers: this.headers });
  }

  getClientes(): Observable<Cliente[]> {
    return this._http.get<Cliente[]>(`${this.apiDomine}/clientes`, { headers: this.headers });
  }

  getVendedor(email: string): Observable<Vendedor> {
    return this._http.get<Vendedor>(`${this.apiDomine}/vendedores?email=${email}`, { headers: this.headers });
  }

  login(login: Login): Observable<Login> {
    return this._http.post<Login>(`${this.apiDomine}/auth/login`, login);
  }

  getIp(): Observable<any> {
    let ipAddress = '';
    return this._http.get("http://api.ipify.org/?format=json");
  }
}

/*
this._apiRest.getVendedor(res1.email).subscribe({
          next: (res2) => {
            localStorage.setItem('user', JSON.stringify(res2));
          },
          complete: () => {
            this.router.navigate(['/home']);
          }
        });
        */
