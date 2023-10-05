[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=12219489&assignment_repo_type=AssignmentRepo)

# Gerência de Estacionamentos

## Xulambs Parking

O objetivo deste projeto é desenvolver um sistema de software para a Xulambs Inc. que gerencie seus estacionamentos. O sistema permitirá controle de vagas, registro de veículos e clientes, cobrança de uso por tempo, acesso ao histórico de uso e fornecimento de informações financeiras para a direção da empresa.

## Requisitos

- A Xulambs Parking pretende contar com entre 1 a 3 parques de estacionamento na cidade.

### Estacionamento e Vaga

- Cada parque de estacionamento terá um número pré-determinado de vagas.
  - As vagas serão identificadas **alfanumericamente**, por exemplo: vaga Y08 (fila Y, vaga 8).
- O uso do estacionamento envolve a seleção de uma vaga livre e a ocupação desta. A cobrança é feita por frações do tempo e segue essas regras:
  - **R$4,00** a cada **15 minutos**
  - valor limite: **R$50,00**
- Uma vaga **não** pode ser utilizada por dois clientes ao mesmo tempo.

### Veículo

- Os veículos que usarão o estacionamento serão registrados por **placa** e devem estar ligados a clientes.

### Cliente

- O cliente pode ser identificado com **nome** e **identificador** ou como "anônimo" (nome e identificador **neutros**).
- Um cliente pode ter cadastrado para si **mais de um** veículo.
- Um cliente identificado tem acesso a seu histórico de uso do estacionamento.
  - Este histórico pode ser completo ou filtrado por datas de início e fim.

---

## Alunos integrantes da equipe

- Ana Luiza Machado Alves
- Ana Luiza Santos Gomes
- Octávio Walter Rattes Soares
- Victor Hugo Criscollo Moreira

## Professores responsáveis

- João Caram Santos de Oliveira
