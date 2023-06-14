import { createRouter, createWebHistory } from '@ionic/vue-router';
import MemoriesPage from '../pages/memoriespage.vue';

const routes  = [
  {
    path: '/',
    redirect: '/memories'
  },
  {
    path: '/memories',
    component: MemoriesPage
  },
  {
    path: '/memories/:id',
    component: () => import('../pages/memorydetails.vue')
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
