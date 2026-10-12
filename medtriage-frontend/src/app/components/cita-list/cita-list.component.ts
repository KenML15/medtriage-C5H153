import {
  ChangeDetectionStrategy,
  Component,
  computed,
  inject,
  signal
} from '@angular/core';

import { CommonModule } from '@angular/common';

import {
  CitaMedica
} from '../../models/cita.model';

import { CitaService } from '../../services/cita.service';

type FiltroPrioridad = 'TODAS' | 'ALTA' | 'MEDIA' | 'BAJA';

@Component({
  selector: 'app-cita-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cita-list.component.html',
  styleUrl: './cita-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CitaListComponent {

  private readonly citaService = inject(CitaService);

  readonly citas = signal<CitaMedica[]>([]);
  readonly prioridadSeleccionada =
    signal<FiltroPrioridad>('TODAS');

  readonly cargando = signal(false);
  readonly error = signal('');

  readonly citasFiltradas = computed(() => {
    const prioridad = this.prioridadSeleccionada();

    if (prioridad === 'TODAS') {
      return this.citas();
    }

    return this.citas().filter(cita =>
      cita.nivelPrioridad.toUpperCase() === prioridad
    );
  });

  readonly prioridades: FiltroPrioridad[] = [
    'TODAS',
    'ALTA',
    'MEDIA',
    'BAJA'
  ];

  constructor() {
    this.cargarCitas();
  }

  cargarCitas(): void {
    this.cargando.set(true);
    this.error.set('');

    this.citaService.getCitas().subscribe({
      next: citas => {
        this.citas.set(citas);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set(
          'No se pudieron cargar las citas médicas.'
        );
        this.cargando.set(false);
      }
    });
  }

  seleccionarPrioridad(
    prioridad: FiltroPrioridad
  ): void {
    this.prioridadSeleccionada.set(prioridad);
  }

  avanzarEstado(cita: CitaMedica): void {
    const siguienteEstado =
      this.obtenerSiguienteEstado(cita.estado);

    if (!siguienteEstado) {
      return;
    }

    this.citaService
      .actualizarEstado(cita.id, siguienteEstado)
      .subscribe({
        next: citaActualizada => {
          this.citas.update(citas =>
            citas.map(item =>
              item.id === citaActualizada.id
                ? citaActualizada
                : item
            )
          );
        },
        error: () => {
          this.error.set(
            'No se pudo actualizar el estado de la cita.'
          );
        }
      });
  }

  obtenerSiguienteEstado(
    estado: string
  ): string | null {
    switch (estado.toUpperCase()) {
      case 'PENDIENTE':
        return 'EN_ATENCION';

      case 'EN_ATENCION':
        return 'ATENDIDA';

      default:
        return null;
    }
  }

  puedeAvanzarEstado(estado: string): boolean {
    return estado === 'PENDIENTE'
      || estado === 'EN_ATENCION';
  }

  claseEstado(estado: string): string {
    return `estado-${estado
      .toLowerCase()
      .replace('_', '-')}`;
  }

  formatearEstado(estado: string): string {
    return estado.replace('_', ' ');
  }
}