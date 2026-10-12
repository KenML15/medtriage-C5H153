import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'citas',
    pathMatch: 'full'
  },
  {
    path: 'citas',
    loadComponent: () =>
      import('./components/cita-list/cita-list.component')
        .then(m => m.CitaListComponent)
  },
  {
    path: 'nueva-cita',
    loadComponent: () =>
      import('./components/cita-form/cita-form.component')
        .then(m => m.CitaFormComponent)
  },
  {
    path: '**',
    redirectTo: 'citas'
  }
];