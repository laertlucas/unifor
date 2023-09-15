<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Buscar por nome"
          v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchName"
          >
            Buscar
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Lista de Cursos</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(curso, index) in cursos"
          :key="index"
          @click="setActiveCurso(curso, index)"
        >
          {{ curso.name }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllCursos">
        Remover todos
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentCurso">
        <h4>Curso</h4>
        <div>
          <label><strong>Nome:</strong></label> {{ currentCurso.name }}
        </div>
        <div>
          <label><strong>Categoria:</strong></label> {{ currentCurso.category }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentCurso.published ? "Ativo" : "Pendente" }}
        </div>

        <a class="badge badge-warning"
          :href="'/curso/' + currentCurso.id"
        >
          Editar
        </a>
      </div>
      <div v-else>
        <br />
        <p>Por favor selecione um Curso...</p>
      </div>
    </div>
  </div>
</template>

<script>
import CursoDataService from "../services/CursoDataService";

export default {
  name: "cursos-list",
  data() {
    return {
      cursos: [],
      currentCurso: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrieveCursos() {
      CursoDataService.getAll()
        .then(response => {
          this.cursos = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveCursos();
      this.currentCurso = null;
      this.currentIndex = -1;
    },

    setActiveCurso(curso, index) {
      this.currentCurso = curso;
      this.currentIndex = index;
    },

    removeAllCursos() {
      CursoDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchName() {
      CursoDataService.findByName(this.name)
        .then(response => {
          this.cursos = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveCursos();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>