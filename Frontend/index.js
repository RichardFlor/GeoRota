const apiUrl = 'http://localhost:8080/mapa';

// Adicionar Local
document.getElementById('addLocationForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const nomePonto = document.getElementById('nomePonto').value;
  const logradouro = document.getElementById('logradouro').value;

  const response = await fetch(`${apiUrl}/adicionar?nomePonto=${encodeURIComponent(nomePonto)}&logradouro=${encodeURIComponent(logradouro)}`, {
    method: 'POST'
  });
  const result = await response.json();
  document.getElementById('addLocationResult').innerText = JSON.stringify(result);
});

// Buscar Local
document.getElementById('searchLocationForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const nomePonto = document.getElementById('searchNomePonto').value;

  const response = await fetch(`${apiUrl}/buscar-local?nomePonto=${encodeURIComponent(nomePonto)}`, {
    method: 'GET'
  });
  if (response.status === 200) {
    const result = await response.json();
    document.getElementById('searchLocationResult').innerText = JSON.stringify(result);
  } else {
    document.getElementById('searchLocationResult').innerText = 'Local não encontrado';
  }
});

// Conectar Elos
document.getElementById('connectPointsForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const nomeOrigem = document.getElementById('nomeOrigem').value;
  const nomeDestino = document.getElementById('nomeDestino').value;

  const response = await fetch(`${apiUrl}/conectar-elos?nomeOrigem=${encodeURIComponent(nomeOrigem)}&nomeDestino=${encodeURIComponent(nomeDestino)}`, {
    method: 'POST'
  });
  const result = await response.json();
  document.getElementById('connectPointsResult').innerText = JSON.stringify(result);
});

// Obter Conexões
document.getElementById('getConnectionsBtn').addEventListener('click', async () => {
  const response = await fetch(`${apiUrl}/conexoes`, {
    method: 'GET'
  });
  const result = await response.json();
  
  if (Array.isArray(result) && result.length > 0) {
    let html = '<table class="table table-bordered">';
    html += '<thead><tr><th>Ponto</th><th>Conexões</th></tr></thead><tbody>';
    
    result.forEach(item => {
      const conexoes = item.Conexões.map(c => c.Nome).join(', ');
      html += `<tr><td>${item.Ponto}</td><td>${conexoes}</td></tr>`;
    });
    
    html += '</tbody></table>';
    document.getElementById('connectionsResult').innerHTML = html;
  } else {
    document.getElementById('connectionsResult').innerText = 'Nenhuma conexão encontrada.';
  }
});

// Melhor Rota
document.getElementById('bestRouteForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const nomeOrigem = document.getElementById('routeNomeOrigem').value;
  const nomeDestino = document.getElementById('routeNomeDestino').value;
  const modo = document.getElementById('modo').value;

  const response = await fetch(`${apiUrl}/melhor-rota?nomeOrigem=${encodeURIComponent(nomeOrigem)}&nomeDestino=${encodeURIComponent(nomeDestino)}&modo=${encodeURIComponent(modo)}`, {
    method: 'GET'
  });
  if (response.status === 200) {
    const result = await response.json();
    let html = `<h4>Detalhes da Rota</h4>
                <p><strong>Início:</strong> ${result.inicio}</p>
                <p><strong>Fim:</strong> ${result.fim}</p>
                <p><strong>Distância Total:</strong> ${result.distancia_total}</p>
                <p><strong>Duração Total:</strong> ${result.duracao_total}</p>
                <h5>Passos:</h5>
                <ol>`;
    result.passos.forEach(passo => {
      html += `<li>
                 <p><strong>Instruções:</strong> ${passo.instrucoes}</p>
                 <p><strong>Distância:</strong> ${passo.distancia}</p>
                 <p><strong>Duração:</strong> ${passo.duracao}</p>
               </li>`;
    });
    html += '</ol>';
    document.getElementById('bestRouteResult').innerHTML = html;
  } else {
    document.getElementById('bestRouteResult').innerText = 'Erro ao obter a rota';
  }
});