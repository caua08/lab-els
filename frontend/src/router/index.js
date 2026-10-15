import { createRouter, createWebHistory } from 'vue-router'
import LaboratoriosListView from '@/views/LaboratoriosListView.vue'
import LaboratorioFormView from '@/views/LaboratorioFormView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: { name: 'laboratorios' } },
    { path: '/laboratorios', name: 'laboratorios', component: LaboratoriosListView },
    { path: '/laboratorios/novo', name: 'laboratorio-novo', component: LaboratorioFormView },
    {
      path: '/laboratorios/:id/editar',
      name: 'laboratorio-editar',
      component: LaboratorioFormView,
    },
  ],
})

export default router
