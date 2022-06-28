# DonkeyKong--Atari

  O presente trabalho consiste na produção de uma réplica do jogo Donkey Kong do Atari desenvolvido pela Nintendo em 1981. A programação do jogo faz uso da linguagem Java juntamente com a biblioteca gráfica LibGDX. Seu objetivo consiste em explorar os aspectos da linguagem de programação e os recursos da biblioteca LibGDX para, dessa forma, conseguir um bom desempenho do jogo.
  
O jogo possui um cenário composto por 8 plataformas, 4 escadas, 4 buracos, os barris e os personagens (Mario, Donkey Kong e a princesa). O objetivo do jogo é conseguir subir até o final da última escada sem que nenhum barril encoste no personagem Mario. Para controlar o Mário, o código pega o evento das teclas “UP”, “DOWN”, “LEFT”, “RIGHT” e “SPACE”. 

# Solução Proposta (Desenvolvimento) 

As soluções encontradas para o desdobramento do trabalho proposto foram: 

- Desenvolvimento do código na IDE IntelliJ, utilizando programação orientada a objetos com a linguagem de programação Java;
- Jogo com interface fluida, com bastante interação com o usuário.
- Uso de sons para  maior entretenimento durante cada partida;
- Para a sincronização dos áudios, se fez necessário o corte para que se encaixasse perfeitamente em cada ação (andar, pular, perder e ganhar) e a elaboração de uma lógica para que ocorra apenas uma vez em cada uma.


# Funcionamento 

  Desenvolvido no Windows 10 e usando a IDE IntelliJ. O programa consiste em desenvolver em linguagem Java de programação e utilizando a biblioteca LibGDX, um jogo bastante famoso, conhecido como Donkey Kong.  
  
# Partes do programa 

Ao entrar no jogo, tem-se a tela inicial, na qual se encontra o cenário a ser explorado pelo jogador, com os três personagens, as plataformas e as escadas.

Neste momento, começa a partida através da interação do usuário por meio dos eventos captados das teclas selecionadas por ele.

Após clicar nas teclas, é sinalizado o resultado no cenário (através da movimentação do Mário).

O fim do jogo pode ocorrer caso o jogador consiga subir até o final da última escada sem que nenhum barril encoste no personagem Mario ou quando um barril se choca com o mesmo. Neste momento, aparece uma imagem indicando se o usuário venceu ou perdeu.

O programa faz uso de programação orientada a objeto na linguagem Java, juntamente com a biblioteca gráfica LibGDX, a qual foi trabalhada separadamente em partes lógicas que executam todos os comandos do jogo (seu funcionamento se dá através de um arquivo executável gerado através do cmd) e a parte visual que é a interação com o usuário. 
 
