<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Curso</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="curso.name"
          name="name"
        />
      </div>

      <div class="form-group">
        <label for="description">Categoria</label>
        <input
          class="form-control"
          id="categoria"
          required
          v-model="curso.category"
          name="categoria"
        />
      </div>

      <button @click="saveCurso" class="btn btn-success">Enviar</button>
    </div>

    <div v-else>
      <h4>Você enviou com sucesso!</h4>
      <button class="btn btn-success" @click="newCurso">Adicionar</button>
    </div>
  </div>
</template>

<script>
import CursoDataService from "../services/CursoDataService";

export default {
  name: "curso-add",
  data() {
    return {
      curso: {
        id: null,
        name: "",
        category: "",
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveCurso() {
      let data = {
        name: this.curso.name,
        category: this.curso.category
      };

      CursoDataService.create(data)
        .then(response => {
          this.curso.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newCurso() {
      this.submitted = false;
      this.curso = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>