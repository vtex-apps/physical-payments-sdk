package sdk.android.seed;

import android.content.Intent;
import android.net.Uri;

import vtex.payment.sdk.dto.output.PaymentPayload;
import vtex.payment.sdk.dto.output.PaymentReversalPayload;

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
    PaymentPayload paymentPayload = new PaymentPayload();

    paymentPayload.setPaymentId(MockUtils.getRandomString());
    paymentPayload.setCardBrandName("mock");
    paymentPayload.setFirstDigits("mock");
    paymentPayload.setLastDigits("mock");
    paymentPayload.setNsu(MockUtils.getRandomString());
    paymentPayload.setTid(MockUtils.getRandomString());
    paymentPayload.setAuthorizationId(MockUtils.getRandomString());

    paymentPayload.addCustomField("scheme", "mock");
    paymentPayload.addCustomField("action", "mock");
    paymentPayload.addCustomField("merchantReceipt", "mock");
    paymentPayload.addCustomField("customerReceipt", "mock");
    paymentPayload.addCustomField("responsecode", "mock");
    paymentPayload.addCustomField("reason", "mock");
    paymentPayload.addCustomField("success", "mock");
    paymentPayload.addCustomField("acquirerAuthorizationCode", "mock");
    paymentPayload.addCustomField("cardToken", "mock");
    paymentPayload.addCustomField("acquirerName", "mock");

    return paymentPayload;
  }

  public static PaymentReversalPayload getMockPaymentReversalResponse() {
    PaymentReversalPayload paymentReversalPayload = new PaymentReversalPayload();

    paymentReversalPayload.setTid(MockUtils.getRandomString());

    paymentReversalPayload.addCustomField("scheme", "mock");
    paymentReversalPayload.addCustomField("action", "mock");
    paymentReversalPayload.addCustomField("paymentId", MockUtils.getRandomString());
    paymentReversalPayload.addCustomField("acquirerName", "mock");
    paymentReversalPayload.addCustomField("acquirierAuthorizationCode", "mock");
    paymentReversalPayload.addCustomField("nsu", MockUtils.getRandomString());
    paymentReversalPayload.addCustomField("merchantReceipt", "mock");
    paymentReversalPayload.addCustomField("customerReceipt", "mock");
    paymentReversalPayload.addCustomField("responsecode", "mock");
    paymentReversalPayload.addCustomField("reason", "mock");
    paymentReversalPayload.addCustomField("success", "mock");

    return paymentReversalPayload;
  }

  public static Intent getMockPaymentRequestIntent() {
    Uri.Builder builder = Uri.parse("sdk://payment").buildUpon();
    builder.appendQueryParameter("acquirerProtocol", "mock");
    builder.appendQueryParameter("action", "mock");
    builder.appendQueryParameter("installmentType", MockUtils.getRandomString());
    builder.appendQueryParameter("installments", "mock");
    builder.appendQueryParameter("paymentType", "mock");
    builder.appendQueryParameter("amount", "mock");
    builder.appendQueryParameter("scheme", "mock");
    builder.appendQueryParameter("autoConfirm", "mock");
    builder.appendQueryParameter("installmentsInterestRate", "mock");
    builder.appendQueryParameter("acquirerFee", "mock");
    builder.appendQueryParameter("merchantName", "mock");
    builder.appendQueryParameter("externalReference", "mock");
    builder.appendQueryParameter("corporateDocument", "mock");
    builder.appendQueryParameter("paymentProcessor", "mock");
    builder.appendQueryParameter("appKey", "mock");
    builder.appendQueryParameter("appToken", "mock");
    builder.appendQueryParameter("paymentId", MockUtils.getRandomString());
    builder.appendQueryParameter("paymentSystem", "mock");
    builder.appendQueryParameter("paymentSystemName", "mock");

    return new Intent(Intent.ACTION_VIEW, builder.build());
  }

  public static Intent getMockPaymentReversalRequestIntent() {
    Uri.Builder builder = Uri.parse("sdk://payment-reversal").buildUpon();

    builder.appendQueryParameter("acquirer", "mock");
    builder.appendQueryParameter("acquirerAuthorizationCode", "mock");
    builder.appendQueryParameter("acquirerFee", "mock");
    builder.appendQueryParameter("acquirerProtocol", "mock");
    builder.appendQueryParameter("action", "mock");
    builder.appendQueryParameter("administrativeCode", MockUtils.getRandomString());
    builder.appendQueryParameter("appKey", "mock");
    builder.appendQueryParameter("appToken", "mock");
    builder.appendQueryParameter("autoConfirm", "mock");
    builder.appendQueryParameter("brand", "mock");
    builder.appendQueryParameter("customerReceipt", "mock");
    builder.appendQueryParameter("firstDigits", "mock");
    builder.appendQueryParameter("installmentType", "mock");
    builder.appendQueryParameter("lastDigits", "mock");
    builder.appendQueryParameter("linkedAffiliationId", "mock");
    builder.appendQueryParameter("merchantReceipt", "mock");
    builder.appendQueryParameter("nsu", MockUtils.getRandomString());
    builder.appendQueryParameter("paymentGroupName", "mock");
    builder.appendQueryParameter("paymentId", MockUtils.getRandomString());
    builder.appendQueryParameter("paymentProcessor", "mock");
    builder.appendQueryParameter("paymentSystem", "mock");
    builder.appendQueryParameter("paymentSystemName", "mock");
    builder.appendQueryParameter("responsecode", "mock");
    builder.appendQueryParameter("scheme", "mock");
    builder.appendQueryParameter("sellerName", "mock");
    builder.appendQueryParameter("splitChargeProcessingFee", "mock");
    builder.appendQueryParameter("splitChargebackLiable", "mock");
    builder.appendQueryParameter("splitSendRecipients", "mock");
    builder.appendQueryParameter("success", "mock");
    builder.appendQueryParameter("tid", MockUtils.getRandomString());
    builder.appendQueryParameter("transactionId", MockUtils.getRandomString());
    builder.appendQueryParameter("value", "mock");

    return new Intent(Intent.ACTION_VIEW, builder.build());
  }
}
