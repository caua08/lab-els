<script setup>
import { onMounted } from 'vue'
import { useLaboratoriosStore } from '@/stores/laboratorios'

const store = useLaboratoriosStore()

onMounted(store.carregar)

async function excluir(lab) {
  if (!confirm(`Excluir o laboratório "${lab.nome}"?`)) return
  try {
    await store.remover(lab.id)
  } catch (e) {
    store.erro = e.message
  }
}
</script>

<template>
  <section>
    <header class="topo">
      <h1>Laboratórios</h1>
      <RouterLink class="botao" :to="{ name: 'laboratorio-novo' }">Novo laboratório</RouterLink>
    </header>

    <p v-if="store.erro" class="erro" role="alert">{{ store.erro }}</p>
    <p v-if="store.carregando">Carregando…</p>
    <p v-else-if="!store.itens.length && !store.erro">Nenhum laboratório cadastrado.</p>

    <table v-else-if="store.itens.length">
      <thead>
        <tr>
          <th>Nome</th>
          <th>Localização</th>
          <th>Capacidade</th>
          <th>Descrição</th>
          <th><span class="sr-only">Ações</span></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="lab in store.itens" :key="lab.id">
          <td>{{ lab.nome }}</td>
          <td>{{ lab.localizacao }}</td>
          <td>{{ lab.capacidade }}</td>
          <td>{{ lab.descricao }}</td>
          <td class="acoes">
            <RouterLink :to="{ name: 'laboratorio-editar', params: { id: lab.id } }">
              Editar
            </RouterLink>
            <button type="button" class="perigo" @click="excluir(lab)">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>
