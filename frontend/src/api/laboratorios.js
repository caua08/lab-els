const BASE = `${import.meta.env.VITE_API_URL ?? 'http://localhost:8080'}/api/laboratorios`

async function request(url, options = {}) {
  const res = await fetch(url, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  })
  if (!res.ok) {
    const problem = await res.json().catch(() => ({}))
    const error = new Error(problem.detail ?? `Erro ${res.status}`)
    error.fields = problem.erros ?? {}
    throw error
  }
  return res.status === 204 ? null : res.json()
}

export const listar = () => request(BASE)
export const buscar = (id) => request(`${BASE}/${id}`)
export const criar = (dados) => request(BASE, { method: 'POST', body: JSON.stringify(dados) })
export const atualizar = (id, dados) =>
  request(`${BASE}/${id}`, { method: 'PUT', body: JSON.stringify(dados) })
export const remover = (id) => request(`${BASE}/${id}`, { method: 'DELETE' })
