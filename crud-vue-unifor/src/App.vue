<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">Unifor</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/cursos" class="nav-link">Listar Cursos</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Adicionar Cursos</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>