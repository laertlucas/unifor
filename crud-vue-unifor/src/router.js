import { createRouter, createWebHistory } from 'vue-router';

const routes =  [
  {
    path: "/",
    alias: "/cursos",
    name: "cursos-list",
    component: () => import("./components/CursoList")
  },
  {
    path: "/cursos/:id",
    name: "curso-details",
    component: () => import("./components/Curso")
  },
  {
    path: "/add",
    name: "curso-add",
    component: () => import("./components/CursosAdd")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;