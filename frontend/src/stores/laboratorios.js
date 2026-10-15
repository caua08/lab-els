import { ref } from 'vue'
import { defineStore } from 'pinia'
import * as api from '@/api/laboratorios'

export const useLaboratoriosStore = defineStore('laboratorios', () => {
  const itens = ref([])
  const carregando = ref(false)
  const erro = ref('')

  async function carregar() {
    carregando.value = true
    erro.value = ''
    try {
      itens.value = await api.listar()
    } catch (e) {
      erro.value = e.message
    } finally {
      carregando.value = false
    }
  }

  async function salvar(id, dados) {
    if (id) await api.atualizar(id, dados)
    else await api.criar(dados)
  }

  async function remover(id) {
    await api.remover(id)
    itens.value = itens.value.filter((l) => l.id !== id)
  }

  return { itens, carregando, erro, carregar, salvar, remover }
})
