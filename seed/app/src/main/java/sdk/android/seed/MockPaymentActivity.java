package sdk.android.seed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vtex.payment.sdk.PaymentActivity;
import vtex.payment.sdk.dto.PaymentRequest;
import vtex.payment.sdk.dto.PaymentResponse;
import vtex.payment.sdk.dto.PaymentPayload;
import vtex.payment.sdk.dto.PaymentReversalPayload;
import vtex.payment.sdk.dto.PaymentReversalRequest;
import vtex.payment.sdk.dto.PaymentReversalResponse;

public class MockPaymentActivity extends PaymentActivity {

  private TextView textView;

  private Button paymentReversalResponseButton;

  private Button paymentResponseButton;

  @Override
  public void onPaymentRequest(PaymentRequest paymentRequest) {
    super.onPaymentRequest(paymentRequest);
    paymentReversalResponseButton.setVisibility(View.GONE);
    paymentResponseButton.setVisibility(View.VISIBLE);
    this.setTextViewAccordingToTheRequest(paymentRequest.extras);
  }

  @Override
  public void onPaymentReversalRequest(PaymentReversalRequest paymentReversalRequest) {
    super.onPaymentReversalRequest(paymentReversalRequest);
    paymentReversalResponseButton.setVisibility(View.VISIBLE);
    paymentResponseButton.setVisibility(View.GONE);
    this.setTextViewAccordingToTheRequest(paymentReversalRequest.extras);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.payment);
    textView = (TextView) findViewById(R.id.payment_intent_text_view);
    paymentReversalResponseButton = (Button) findViewById(R.id.paymentReversalResponseButton);
    paymentResponseButton = (Button) findViewById(R.id.paymentResponseButton);
    super.onCreate(savedInstanceState);
  }

  private void setTextViewAccordingToTheRequest(Bundle extras) {
    if (extras != null && this.textView != null) {
      String allExtras = "";
      for (String key : extras.keySet()) {
        allExtras = allExtras.concat(key + " : " + extras.getString(key) + "\n");
      }
      this.textView.setText(allExtras);
    }
  }

  public void onSendPaymentResponse(View view) {
    PaymentPayload mockPaymentPayload = MockUtils.getMockPaymentResponse();
    PaymentResponse paymentResponse = new PaymentResponse(mockPaymentPayload);
    Intent inStoreIntent = paymentResponse.intent;
    startActivity(inStoreIntent);
  }

  public void onSendPaymentReversalResponse(View view) {
    PaymentReversalPayload mockPaymentReversalPayload = MockUtils.getMockPaymentReversalResponse();
    PaymentReversalResponse paymentReversalResponse =
        new PaymentReversalResponse(mockPaymentReversalPayload);
    Intent inStoreIntent = paymentReversalResponse.intent;
    startActivity(inStoreIntent);
  }
}
