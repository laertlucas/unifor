<template>
  <div v-if="currentCurso" class="edit-form">
    <h4>Curso</h4>
    <form>
      <div class="form-group">
        <label for="nome">Nome</label>
        <input type="text" class="form-control" id="nome"
          v-model="currentCurso.name"
        />
      </div>
      <div class="form-group">
        <label for="categoria">Categoria</label>
        <input type="text" class="form-control" id="categoria"
          v-model="currentCurso.category"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentCurso.published ? "Published" : "Pending" }}
      </div>
    </form>

    <button class="badge badge-primary mr-2"
      v-if="currentCurso.published"
      @click="updatePublished(false)"
    >
      Não publicado
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)"
    >
      Publicado
    </button>

    <button class="badge badge-danger mr-2"
      @click="deleteCurso"
    >
      Deletar
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateCurso"
    >
      Atualizar
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Por favor selecione um Curso...</p>
  </div>
</template>

<script>
import CursoDataService from "../services/CursoDataService";

export default {
  name: "curso-details",
  data() {
    return {
        currentCurso: null,
      message: ''
    };
  },
  methods: {
    getCurso(id) {
        CursoDataService.get(id)
        .then(response => {
          this.currentCurso = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    updatePublished(status) {
      let data = {
        id: this.currentCurso.id,
        title: this.currentCurso.title,
        description: this.currentCurso.description,
        published: status
      };

      CursoDataService.update(this.currentCurso.id, data)
        .then(response => {
          this.currentCurso.published = status;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    updateCurso() {
        CursoDataService.update(this.currentCurso.id, this.currentCurso)
        .then(response => {
          console.log(response.data);
          this.message = 'The curso was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteCurso() {
        CursoDataService.delete(this.currentCurso.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "cursos" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getCurso(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>