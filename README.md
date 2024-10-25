# JotaGe Cactus Storage Plugin 1.0

<p>Este plugin permite que jogadores coletem e armazenem cactos automaticamente dentro de um armazém, com a capacidade de realizar upgrades, venda automática e interação via GUIs. Ele foi desenvolvido para a versão Minecraft 1.8.8 e utiliza várias funcionalidades como economia e sistemas de progressão.</p>

---

## Funcionalidades
### Armazenamento automático de cactos:
Cactos são armazenados automaticamente na GUI do armazém.

### Sistema de upgrades: 
Jogadores podem melhorar o armazém para aumentar o valor de venda e a taxa de produção.

### Venda automática: 
O sistema vende cactos automaticamente, caso ativado.

### GUI interativa: 
Toda a interação com o armazém e as melhorias é feita através de menus gráficos.

### Notificações sonoras: 
Sons personalizados são tocados para interações de sucesso ou erro.

---

## Dependências
PlotSquared: Gerenciamento de plots.

Vault: Integração com sistemas de economia.

---

## Comandos
/armazem: Abre a interface gráfica do armazém para visualizar e coletar cactos.

/armazem reload: Reinicia as configurações do plugin.

---

# Config:

```yml
upgrades:
  valor:
    # Defina aqui o valor unitário inicial do cacto do Upgrade de VALOR.
    inicialcactusvalue: 5000
    # Defina aqui o custo de upgrade inicial do Upgrade de VALOR.
    inicialprice: 2000000000
    # Defina aqui o nível máximo do Upgrade de VALOR.
    maxlevel: 12
  fortune:
    # Defina aqui o valor inicial da fotuna do Upgrade de FORTUNA.
    inicialfortunevalue: 1
    # Defina aqui o custo de upgrade inicial do Upgrade de FORTUNA.
    inicialprice: 100000000
    # Defina aqui o nível máximo do Upgrade de FORTUNA.
    maxlevel: 8
  limit:
    # Defina aqui o tamnho inicial do armazem do Upgrade de LIMITE.
    iniciallimitvalue: 1000
    # Defina aqui o custo de upgrade inicial do Upgrade de LIMITE.
    inicialprice: 100000000000
    # Defina aqui o nível máximo do Upgrade de LIMITE.
    maxlevel: 5
```

---
