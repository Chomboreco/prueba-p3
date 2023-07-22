export interface IConfirmationData {
  title?: string;
  text: string;
  icon?: string;
  type?: string;
}

export interface Column {
  header: string;
  show: boolean;
  headerText: string;
}

export interface Pedido {
  id: number;
  status: string;
  fechaPedido: Date;
  fechaEntrega: Date;
  ip: string;
  cliente: Cliente;
  productos: Producto[];
  vendedor: Vendedor;
}

export interface Cliente {
  id: number;
  nombre: string;
  direccion: string;
  telefono: string;
}

export interface Producto {
  id: number;
  hawa: string;
  nombre: string;
  precio: number;
  descuento: number;
  existencia: number;
  cantidad: number;
}

export interface Vendedor {
  id: number;
  nombre: string;
  email: string;
  password: string;
  tienda: Tienda;
}

export interface Tienda {
  id: number;
  nombre: string;
  direccion: string;
}

export interface AuthResponse {
  email: string;
  accessToken: string;
}

export interface Select {
  value: string;
  viewValue: string;
}

export interface Login {
  email: string;
  password: string;
}