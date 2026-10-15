<template>
  <main>
    <RouterView />
  </main>
</template>

<style>
body {
  font-family: system-ui, sans-serif;
  margin: 0;
  color: #1f2933;
  background: #f5f7fa;
}
main {
  max-width: 56rem;
  margin: 0 auto;
  padding: 1.5rem 1rem;
}
.topo {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
table {
  width: 100%;
  border-collapse: collapse;
  background: #fff;
}
th,
td {
  text-align: left;
  padding: 0.6rem;
  border-bottom: 1px solid #e4e7eb;
}
form {
  display: grid;
  gap: 1rem;
  max-width: 28rem;
}
label {
  display: grid;
  gap: 0.25rem;
}
input,
textarea {
  padding: 0.5rem;
  font: inherit;
}
button,
.botao {
  padding: 0.5rem 0.9rem;
  border: 0;
  border-radius: 4px;
  background: #1c64f2;
  color: #fff;
  font: inherit;
  cursor: pointer;
  text-decoration: none;
}
button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
button.perigo {
  background: #c0392b;
}
.acoes {
  display: flex;
  gap: 0.75rem;
  align-items: center;
}
.erro {
  color: #c0392b;
}
.sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  overflow: hidden;
  clip: rect(0 0 0 0);
}
</style>
