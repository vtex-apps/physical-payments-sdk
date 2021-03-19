package sdk.android.seed;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import vtex.payment.sdk.PaymentActivity;
import vtex.payment.sdk.dto.input.PaymentRequest;
import vtex.payment.sdk.dto.input.PaymentReversalRequest;
import vtex.payment.sdk.dto.output.PaymentResponse;
import vtex.payment.sdk.dto.output.PaymentPayload;
import vtex.payment.sdk.dto.output.PaymentReversalPayload;
import vtex.payment.sdk.dto.output.PaymentReversalResponse;

public class MockPaymentActivity extends PaymentActivity {

  private TextView textView;

  private Button paymentReversalResponseButton;

  private Button paymentResponseButton;

  @Override
  public void onPaymentRequest(PaymentRequest paymentRequest) {
    paymentReversalResponseButton.setVisibility(View.GONE);
    paymentResponseButton.setVisibility(View.VISIBLE);
    this.setTextViewAccordingToTheRequest(paymentRequest.toJSONObject(), paymentRequest.getCustomFields());
  }

  @Override
  public void onPaymentReversalRequest(PaymentReversalRequest paymentReversalRequest) {
    paymentReversalResponseButton.setVisibility(View.VISIBLE);
    paymentResponseButton.setVisibility(View.GONE);
    this.setTextViewAccordingToTheRequest(paymentReversalRequest.toJSONObject(), paymentReversalRequest.getCustomFields());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.payment);
    textView = (TextView) findViewById(R.id.payment_intent_text_view);
    paymentReversalResponseButton = (Button) findViewById(R.id.paymentReversalResponseButton);
    paymentResponseButton = (Button) findViewById(R.id.paymentResponseButton);
    super.onCreate(savedInstanceState);
  }

  private void setTextViewAccordingToTheRequest(JSONObject dataJsonObject, JSONObject customFieldsJsonObject) {
    if(this.textView != null) {
      String allData = "";

      if (dataJsonObject != null) {
        dataJsonObject.remove("customFields");
        try {
          Iterator<String> keys = dataJsonObject.keys();
          while (keys.hasNext()) {
            String key = keys.next();
            allData = allData.concat(key + " : " + dataJsonObject.getString(key) + "\n");
          }
        } catch (JSONException err) {
          throw new RuntimeException(err);
        }
      }

      if (customFieldsJsonObject != null) {
        allData = allData.concat("\n Custom Fields: \n");

        try {
          Iterator<String> keys = customFieldsJsonObject.keys();
          while (keys.hasNext()) {
            String key = keys.next();
            allData = allData.concat(key + " : " + customFieldsJsonObject.getString(key) + "\n");
          }
        } catch (JSONException err) {
          throw new RuntimeException(err);
        }
      }

      this.textView.setText(allData);
    }
  }

  public void onSendPaymentResponse(View view) {
    PaymentPayload mockPaymentPayload = MockUtils.getMockPaymentResponse();
    PaymentResponse paymentResponse = new PaymentResponse(mockPaymentPayload);
    startActivity(paymentResponse);
  }

  public void onSendPaymentReversalResponse(View view) {
    PaymentReversalPayload mockPaymentReversalPayload = MockUtils.getMockPaymentReversalResponse();
    PaymentReversalResponse paymentReversalResponse =
        new PaymentReversalResponse(mockPaymentReversalPayload);
    startActivity(paymentReversalResponse);
  }
}
