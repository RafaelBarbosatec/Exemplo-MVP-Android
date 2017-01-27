# Exemplo MVP no Android

![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)

O padrão MVP tem a finalidade de separar a camada de apresentação das camadas de dados e regras de negócio. Vamos falar um pouco sobre sua composição.

**Model–view–presenter (MVP)** é uma derivação do padrão de software model-view-controller (MVC), usado também para construir principalmente interfaces gráficas.(WIKIPEDIA, 2016)

Em MVP a camada Presenter assume a função de mediadora (executada pelo Controller em MVC). Além disso, a View é responsável por manipular os eventos UI (como mouseDown, keyDown, etc.), que era o trabalho da Controller. Finalmente, a Model se torna estritamente um modelo de domínio.(WIKIPEDIA, 2016)

Ele é dividido em três partes bem distintas com responsabilidades específicas, são elas o Model, View e Presenter, como ilustrado abaixo:

![N|Solid](https://github.com/RafaelBarbosatec/Exemplo-MVP-Android/blob/master/imagens/MVP.png = 250x250)

## Como implementar?
Essa questão é bastante discutida entre a comunidade. Existe muitas divergências sobre a melhor maneira de implementálo e como adapta-lo ao nosso ambiente Android. Ele pode variar de acordo com a responsabilidade que o Presenter contem. Esse exemplo que implementei foi baseado na implementação disponibilizada pela google em seu [Codelab](https://codelabs.developers.google.com/codelabs/android-testing/index.html#0). Que incluse ensina juntamente a implementar diversos testes. Mas de qualquer forma, a definição das partes do MVP não é alterado:
### Presenter:
"O presenter age como intermediário entre a view e o model. Ele retira os dados do modelo e retorna para a view. Mas, diferente de típicos MVC, ele também decide o que acontece quando usuário interage com a view."(TIN MEGALI, 2014)

### View
"O view, normalmente implementado por uma Atividade (também pode ser um Fragmento ou qualquer elemento UI, dependendo da estrutura do aplicativo), vai conter uma referência para o presenter.  O presenter pode ser criado pela Activity ou fornecido via injeção de dependência. A única responsabilidade da View é chamar métodos no Presenter toda vez que o usuário interage com ela."(TIN MEGALI, 2014)

### Model
"O model é responsável pelos dados que serão exibidos na interface do usuário. Poderíamos considerar como modelo, além dos dados, qualquer lógica de manipulação e acesso destes dados."

Como não existe uma fórmula "correta", ficará ao seu critério como e qual caminho escolher, portanto que o cerne da arquitetura MVP seja preservado.

 Referencias:
- MEGALI, Tin. MODEL VIEW PRESENTER (MVP) NO ANDROID, PARTE 1, 2016. Disponível em:< http://www.tinmegali.com/pt/model-view-presenter-mvp-no-android-introducao/ />. Acesso em: 25 de jan. 2017.
- WIKIPEDIA. Model-view-presenter, 2016. Disponível em:< https://pt.wikipedia.org/wiki/Model-view-presenter/>. Acesso em: 25 de jan. 2017.
