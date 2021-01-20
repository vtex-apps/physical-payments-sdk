# Documentação técnica de integração com o VTEX inStore

## Overview

O fluxo de venda do inStore tem como um dos atores principais o app de pagamento. Esse app é independente do inStore, porém é necessário para que a compra seja finalizada. Pedidos de pagamento são enviados ao app pelo inStore e após a finalização do pagamento o app de pagamento deve responder ao inStore. Assim, completando o fluxo de compra.

Um protocolo de comunicação foi definido para formalizar essa comunicação entre o inStore e o app de pagamento. Esse protocolo define:
- O que deve ser enviado
- O que deve ser retornado
- Qual o meio de comunicação a ser utilizado

![stack Overflow](./image.png)

Além do protocolo, um SDK foi desenvolvido que implementa este protocolo e pode ser utilizado para abstrair parte dos desafios técnicos de implementação do protocolo.

## SDK

O SDK foi desenvolvido com o foco em Android nativo. O SDK disponibiliza:
- Classes que representam requisições de pagamento/estorno
- Classes que representam respostas de pagamento/estorno
- Classe abstrata que auxilia a implementação do mecanismo de comunicação.

### Deep Linking

O mecanismo utilizado para comunicação é o [Android Deep Link](https://developer.android.com/training/app-links) através de [Android intents](https://developer.android.com/reference/android/content/Intent). É preciso definir uma Android Activity que receba intents específicos com o scheme do Android para receber requisições de pagamento. Da mesma forma, é preciso criar intents e iniciar atividades com eles para enviar respostas ao inStore.

## Configuração Inicial

### Integração com SDK

Adicionar a dependência do SDK no projeto Android. Por exemplo, caso seu projeto utiliza gradle, adicione dentro ta tag de _dependencies_ a seguinte linha:

```gradle
dependencies {
   ...
   implementation files('<path>/VTEXPaymentSDK_0.0.1.aar')
   ...
}
```

### Android Manifest

Adicione ao AndroidManifest.xml dentro da tag da atividade, que irá realizar o processamento de requisições de pagamento, um bloco de `<intent-filter>` para que o Android disponibilize a comunicação entre aplicativos.

```xml
<activity
 android:name="<PaymentActivity>">
 ...
 <intent-filter>
     <action android:name="android.intent.action.VIEW" />
     <category android:name="android.intent.category.DEFAULT" />
     <category android:name="android.intent.category.BROWSABLE" />
     <data android:scheme="<scheme>" android:host="payment" />
     <data android:scheme="<scheme>" android:host="payment-reversal" />
 </intent-filter>
 ... 
</activity>
```

> O campo `<PaymentActivity>` representa o nome da atividade que irá processar as requisições de pagamento utilizando os módulos disponíveis no SDK.

> O campo `<scheme>` representa a string definindo o scheme a ser configurado no conector do gateway. Por exemplo, para o app do VTEX Payment é utilizado `vtex-payment` como scheme resultando em uris como `vtex-payment://payment/`.

## Processamento de Pagamentos e Estorno

Existem duas formas de ouvir e processar os pagamentos e estornos vindo do inStore. A forma mais simples é fazendo com que a sua atividade, que irá processar os pagamentos, extenda da classe `PaymentActivity` do SDK.

### Extends PaymentActivity

```java
import vtex.payment.sdk.PaymentActivity;
import vtex.payment.sdk.dto.input.PaymentRequest;
import vtex.payment.sdk.dto.input.PaymentReversalRequest;

public class ExamplePaymentActivity extends PaymentActivity {

    @Override
    public void onPaymentRequest(PaymentRequest paymentRequest) {
        // handling
    }

    @Override
    public void onPaymentReversalRequest(PaymentReversalRequest paymentReversalRequest) {
        // handling
    }
}
```

> No exemplo acima, os métodos `onPaymentRequest` e o `onPaymentReversalRequest` são os responsáveis por lidar com requisições de pagamento e estorno respectivamente.

> **Atenção**: A classe `ExamplePaymentActivity` deve ser a classe utilizada no AndroidManifest para adicionar os `<intent-filters>`.

### Implementando um IntentProcessor

Caso não seja possível utilizar a classe `PaymentActivity`, ou necessite de uma activity customizada, é possível implementar um módulo interno chamado `IntentProcessor`. A classe contém dois métodos similares aos vistos acima para o `PaymentActivity`. 

```java
private class ActivityIntentProcessor extends IntentProcessor {

    @Override
    protected void onPaymentRequestProcessed(PaymentRequest paymentRequest) {
        // handling
    }

    @Override
    protected void onPaymentRequestReversalProcessed(
            PaymentReversalRequest paymentReversalRequest) {
        // handling
    }
}
```

Os métodos `onPaymentRequestProcessed` e o `onPaymentRequestReversalProcessed` são os responsáveis por lidar com requisições de pagamento e estorno respectivamente.

> **Atenção**: ao utilizar o `IntentProcessor` é preciso vincular o objeto manualmente ao `Android Life cycle`. A classe contém um método chamado `processIntent` que deve ser chamado tanto no ciclo de `onCreate` quanto no ciclo de `onNewIntent` da atividade. Exemplo de implementação:

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   ...
   Intent intent = getIntent();
   activityIntentProcessor.processIntent(intent);
}

@Override
public void onNewIntent(Intent intent) {
   super.onNewIntent(intent)
   setIntent(intent);
   ...
   activityIntentProcessor.processIntent(intent);
}
```

> No exemplo acima, `activityIntentProcessor` é um objeto instanciado da classe `IntentProcessor`.


# Data Transfer Objects (DTO)

## Entrada / Requisições

Existem duas classes que representam requisições de entradas com seus respectivos dados: `PaymentRequest` e  `PaymentReversalRequest`.

Os campos dentro dessas classes podem ser acessados através de `getters` ou se preferir através do método `toJSONObject` que transforma a classe em um `JSONObject`.

Os campos acessíveis através de getters são os campos obrigatórios e determinados como necessários pelo protocolo de comunicação. Porém, é possível que sua integração demande de outros campos que o inStore pode passar a enviar.

Todo dado enviado pelo inStore que não está contemplado pelo protocolo de comunicação será armazenado no campo `customFields` o qual é acessível por um getter como também pelo método `toJSONObject`.

## Saída / Respostas de Sucesso

Existem duas classes que representam respostas de sucesso no SDK: `PaymentResponse` e  `PaymentReversalResponse`. Essas classes necessitam em seu construtor de um objeto do tipo `PaymentPayload` e `PaymentReversalPayload` respectivamente.

Dois construtores estão disponíveis para as classes do tipo "payload". Um construtor vazio, para ser utilizado em conjunto com os setters definidos na classe e um construtor que recebe um `JSONObject`, que extrai os dados do objeto passado.

> **Atenção**: Ao utilizar o construtor com `JSONObject` é preciso ter cuidado para que ao menos todos os campos obrigatórios estejam presentes no `JSONObject` ou uma exceção será lançada. __Campos extras passados através desse construtor são ignorados__.

Ambas as classes também contém um campo `customFields` que permite que dados, além daqueles definidos no protocolo, sejam enviados para o inStore. O método `addCustomField(String key, String value)` foi criado para facilitar a adição desses campos extras.

## Saída / Erros

Em caso de erro existem duas classes que representam respostas de erro no SDK: `GenericPaymentErrorResponse` e `GenericPaymentReversalErrorResponse`. Essas classes recebem um `JSONObject` em seu construtor. 

> O protocolo definido para mensagens de erro ainda é genérico e está em processo de definição.

Por convenção utilizados o seguintes campos dentro do payload de erro: 
- **error**: mensagem de erro
- **reason**: mais detalhes do erro
- **responsecode**: código do erro

# Enviando resposta ao inStore

O envio de respostas ao inStore ocorre pelo início de uma nova atividade utilizando um intent programado para envio de dados. Por exemplo, pode-se utilizar o método `startActivity` contido dentro do contexto de uma atividade Android. 

As seguintes classes `DTO` de saída fazem extends da classe [Android Intent](https://developer.android.com/reference/android/content/Intent):
- PaymentResponse
- PaymentReversalResponse
- GenericPaymentErrorResponse
- GenericPaymentReversalErrorResponse

Isso permite que essas classes possam ser diretamente utilizadas com um método que inicia uma atividade.

Exemplo de código contendo envio de resposta ao inStore:

```java
PaymentPayload paymentPayload = new PaymentPayload(resposta);
PaymentResponse paymentResponse = new PaymentResponse(paymentPayload);
startActivity(paymentResponse);
```
