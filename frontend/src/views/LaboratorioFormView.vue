<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { buscar } from '@/api/laboratorios'
import { useLaboratoriosStore } from '@/stores/laboratorios'

const route = useRoute()
const router = useRouter()
const store = useLaboratoriosStore()

const id = computed(() => route.params.id)
const form = reactive({ nome: '', localizacao: '', capacidade: 1, descricao: '' })
const erros = ref({})
const erroGeral = ref('')
const salvando = ref(false)

onMounted(async () => {
  if (!id.value) return
  try {
    Object.assign(form, await buscar(id.value))
  } catch (e) {
    erroGeral.value = e.message
  }
})

async function enviar() {
  erros.value = {}
  erroGeral.value = ''
  salvando.value = true
  try {
    const { nome, localizacao, capacidade, descricao } = form
    await store.salvar(id.value, { nome, localizacao, capacidade, descricao })
    router.push({ name: 'laboratorios' })
  } catch (e) {
    erros.value = e.fields ?? {}
    erroGeral.value = e.message
  } finally {
    salvando.value = false
  }
}
</script>

<template>
  <section>
    <h1>{{ id ? 'Editar laboratório' : 'Novo laboratório' }}</h1>
    <p v-if="erroGeral" class="erro" role="alert">{{ erroGeral }}</p>

    <form @submit.prevent="enviar">
      <label>
        Nome
        <input v-model="form.nome" required maxlength="120" />
        <small v-if="erros.nome" class="erro">{{ erros.nome }}</small>
      </label>
      <label>
        Localização
        <input v-model="form.localizacao" maxlength="120" />
        <small v-if="erros.localizacao" class="erro">{{ erros.localizacao }}</small>
      </label>
      <label>
        Capacidade
        <input v-model.number="form.capacidade" type="number" min="1" required />
        <small v-if="erros.capacidade" class="erro">{{ erros.capacidade }}</small>
      </label>
      <label>
        Descrição
        <textarea v-model="form.descricao" maxlength="500" rows="3" />
        <small v-if="erros.descricao" class="erro">{{ erros.descricao }}</small>
      </label>

      <div class="acoes">
        <button type="submit" :disabled="salvando">Salvar</button>
        <RouterLink :to="{ name: 'laboratorios' }">Cancelar</RouterLink>
      </div>
    </form>
  </section>
</template>
