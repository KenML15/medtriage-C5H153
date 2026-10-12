import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import {
  CitaMedica,
  CrearCitaPayload
} from '../models/cita.model';

@Injectable({
  providedIn: 'root'
})
export class CitaService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.API_URL}citas`;

  getCitas(): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(this.apiUrl);
  }

  getCitasPorPrioridad(
    prioridad: string
  ): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(
      `${this.apiUrl}/prioridad/${prioridad}`
    );
  }

  crearCita(
    payload: CrearCitaPayload
  ): Observable<CitaMedica> {
    return this.http.post<CitaMedica>(
      this.apiUrl,
      payload
    );
  }

  actualizarEstado(
    id: number,
    estado: string
  ): Observable<CitaMedica> {
    const params = new HttpParams()
      .set('nuevoEstado', estado);

    return this.http.patch<CitaMedica>(
      `${this.apiUrl}/${id}/estado`,
      {},
      { params }
    );
  }
}