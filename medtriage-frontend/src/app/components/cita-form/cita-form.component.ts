import {
  Component,
  inject,
  signal
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { CitaService } from '../../services/cita.service';
import { CrearCitaPayload } from '../../models/cita.model';

@Component({
  selector: 'app-cita-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cita-form.component.html',
  styleUrl: './cita-form.component.css'
})
export class CitaFormComponent {
  private readonly citaService = inject(CitaService);
  private readonly router = inject(Router);

  readonly enviando = signal(false);
  readonly mensaje = signal('');

  form: CrearCitaPayload = {
    pacienteId: 1,
    doctorId: 1,
    nivelPrioridad: 'ALTA',
    motivoConsulta: '',
    montoConsulta: 0,
    fechaCita: this.getFechaActual()
  };

  onSubmit(): void {
    this.enviando.set(true);
    this.mensaje.set('');

    this.citaService.crearCita(this.form).subscribe({
      next: () => {
        this.mensaje.set('Cita registrada correctamente.');
        this.enviando.set(false);

        setTimeout(() => {
          this.router.navigate(['/citas']);
        }, 800);
      },
      error: () => {
        this.mensaje.set('Error al registrar la cita.');
        this.enviando.set(false);
      }
    });
  }

  private getFechaActual(): string {
    const now = new Date();
    const offset = now.getTimezoneOffset();
    const local = new Date(now.getTime() - offset * 60000);

    return local.toISOString().slice(0, 16);
  }
}