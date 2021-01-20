package sdk.android.seed;

import android.content.Intent;
import android.net.Uri;

import org.json.JSONObject;

import vtex.payment.sdk.dto.PaymentPayload;
import vtex.payment.sdk.dto.PaymentReversalPayload;

public class MockUtils {

  private static String getRandomString() {
    int min = 11111111;
    int max = 99999999;
    // Generate random double value from 50 to 100
    System.out.println("Random value in double from " + min + " to " + max + ":");
    int random_int = (int) (Math.random() * (max - min + 1) + min);
    return "" + random_int;
  }

  public static PaymentPayload getMockPaymentResponse() {
    JSONObject jsonObject = new JSONObject();
    PaymentPayload paymentPayload = new PaymentPayload();

    paymentPayload.scheme = "mock";
    paymentPayload.action = "mock";
    paymentPayload.paymentId = MockUtils.getRandomString();
    paymentPayload.cardBrandName = "mock";
    paymentPayload.firstDigits = "mock";
    paymentPayload.lastDigits = "mock";
    paymentPayload.cardToken = "mock";
    paymentPayload.acquirerName = "mock";
    paymentPayload.nsu = MockUtils.getRandomString();
    paymentPayload.merchantReceipt = "mock";
    paymentPayload.customerReceipt = "mock";
    paymentPayload.responsecode = "mock";
    paymentPayload.reason = "mock";
    paymentPayload.success = "mock";
    paymentPayload.tid = MockUtils.getRandomString();
    paymentPayload.acquirerAuthorizationCode = "mock";
    paymentPayload.authorizationId = MockUtils.getRandomString();

    return paymentPayload;
  }

  public static PaymentReversalPayload getMockPaymentReversalResponse() {
    PaymentReversalPayload paymentReversalPayload =
        new PaymentReversalPayload();

    paymentReversalPayload.scheme = "mock";
    paymentReversalPayload.action = "mock";
    paymentReversalPayload.paymentId = MockUtils.getRandomString();
    paymentReversalPayload.acquirerName = "mock";
    paymentReversalPayload.tid = MockUtils.getRandomString();
    paymentReversalPayload.acquirerAuthorizationCode = "mock";
    paymentReversalPayload.nsu = MockUtils.getRandomString();
    paymentReversalPayload.merchantReceipt = "mock";
    paymentReversalPayload.customerReceipt = "mock";
    paymentReversalPayload.responsecode = "mock";
    paymentReversalPayload.reason = "mock";
    paymentReversalPayload.success = "mock";

    return paymentReversalPayload;
  }

  public static Intent getMockPaymentRequestIntent() {
    final Intent sendPaymentIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sdk://payment"));
    sendPaymentIntent.putExtra("scheme", "mock");
    sendPaymentIntent.putExtra("action", "mock");
    sendPaymentIntent.putExtra("paymentId", MockUtils.getRandomString());
    sendPaymentIntent.putExtra("firstDigits", "mock");
    sendPaymentIntent.putExtra("acquirerName", "mock");
    sendPaymentIntent.putExtra("merchantReceipt", "mock");
    sendPaymentIntent.putExtra("customerReceipt", "mock");
    sendPaymentIntent.putExtra("responsecode", "mock");
    sendPaymentIntent.putExtra("success", "mock");
    sendPaymentIntent.putExtra("authorizationId", MockUtils.getRandomString());
    sendPaymentIntent.putExtra("mobileLinkingUrl", "mock");
    sendPaymentIntent.putExtra("acquirerProtocol", "mock");
    sendPaymentIntent.putExtra("acquirer", "mock");
    sendPaymentIntent.putExtra("brand", "mock");
    sendPaymentIntent.putExtra("tid", MockUtils.getRandomString());

    return sendPaymentIntent;
  }

  public static Intent getMockPaymentReversalRequestIntent() {
    final Intent sendPaymentReversalIntent =
        new Intent(Intent.ACTION_VIEW, Uri.parse("sdk://payment-reversal"));

    sendPaymentReversalIntent.putExtra("acquirer", "mock");
    sendPaymentReversalIntent.putExtra("acquirerAuthorizationCode", "mock");
    sendPaymentReversalIntent.putExtra("acquirerFee", "mock");
    sendPaymentReversalIntent.putExtra("acquirerProtocol", "mock");
    sendPaymentReversalIntent.putExtra("action", "mock");
    sendPaymentReversalIntent.putExtra("administrativeCode", MockUtils.getRandomString());
    sendPaymentReversalIntent.putExtra("appKey", "mock");
    sendPaymentReversalIntent.putExtra("appToken", "mock");
    sendPaymentReversalIntent.putExtra("autoConfirm", "mock");
    sendPaymentReversalIntent.putExtra("brand", "mock");
    sendPaymentReversalIntent.putExtra("customerReceipt", "mock");
    sendPaymentReversalIntent.putExtra("firstDigits", "mock");
    sendPaymentReversalIntent.putExtra("installmentType", "mock");
    sendPaymentReversalIntent.putExtra("lastDigits", "mock");
    sendPaymentReversalIntent.putExtra("linkedAffiliationId", "mock");
    sendPaymentReversalIntent.putExtra("merchantReceipt", "mock");
    sendPaymentReversalIntent.putExtra("nsu", MockUtils.getRandomString());
    sendPaymentReversalIntent.putExtra("paymentGroupName", "mock");
    sendPaymentReversalIntent.putExtra("paymentId", MockUtils.getRandomString());
    sendPaymentReversalIntent.putExtra("paymentProcessor", "mock");
    sendPaymentReversalIntent.putExtra("paymentSystem", "mock");
    sendPaymentReversalIntent.putExtra("paymentSystemName", "mock");
    sendPaymentReversalIntent.putExtra("responsecode", "mock");
    sendPaymentReversalIntent.putExtra("scheme", "mock");
    sendPaymentReversalIntent.putExtra("sellerName", "mock");
    sendPaymentReversalIntent.putExtra("splitChargeProcessingFee", "mock");
    sendPaymentReversalIntent.putExtra("splitChargebackLiable", "mock");
    sendPaymentReversalIntent.putExtra("splitSendRecipients", "mock");
    sendPaymentReversalIntent.putExtra("success", "mock");
    sendPaymentReversalIntent.putExtra("tid", MockUtils.getRandomString());
    sendPaymentReversalIntent.putExtra("transactionId", MockUtils.getRandomString());
    sendPaymentReversalIntent.putExtra("value", "mock");
    return sendPaymentReversalIntent;
  }
}
