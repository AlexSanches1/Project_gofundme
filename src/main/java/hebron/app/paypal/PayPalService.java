package hebron.app.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import hebron.app.exceptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Paypal payment system can be found by the link.
 * https://developer.paypal.com/docs/api/quickstart/payments/#
 */
@Service
public class PayPalService {

    private static final String PAYPAL_CURRENCY_KEY = "paypal.currency";

    private static final Logger LOGGER = LoggerFactory.getLogger(PayPalService.class);

    private String walletPage;
    private PayPalClientFactory payPalClientFactory;

    @Autowired
    public PayPalService(@Value("${app.origin.url}")String siteUrl,
                         PayPalClientFactory payPalClientFactory) {
        this.walletPage = siteUrl + "/navigation/wallet";
        this.payPalClientFactory = payPalClientFactory;
    }

    public Payment createPayment(String totalAmount) {
        Payment payment = createPaymentObject(totalAmount);
        return createPayment(payment);
    }

    /**
     * This operation is can be called after payment is approved (by approve_url).
     * Approve call return link with PayerId,
     * which can be used for creating PaymentExecution.
     * @param paymentDto
     * @return Payment after execution
     */
    public Payment executePaymentAfterApprove(PaymentDto paymentDto) {
        LOGGER.debug("Execute payment with dto: [" + paymentDto + "]");
        //apiContext should be created every time
        APIContext apiContext = payPalClientFactory.createApiContext();
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(paymentDto.getPayerId());
        try {
            Payment payment = new Payment();
            payment.setId(paymentDto.getPaymentId());
            Payment createdPayment = payment.execute(apiContext, paymentExecution);
            LOGGER.debug("Payment created: [" + createdPayment + "]");
            return createdPayment;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    private Payment createPaymentObject(String totalAmount) {
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(totalAmount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Payment's transaction with totalAmount: " + totalAmount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(walletPage);
        redirectUrls.setReturnUrl(walletPage);
        payment.setRedirectUrls(redirectUrls);

        return payment;
}


    private Payment createPayment(Payment payment) {
        try {
            APIContext apiContext = payPalClientFactory.createApiContext();
            Payment createdPayment = payment.create(apiContext);
            LOGGER.info("Created payment: " + createdPayment.toString());
            return createdPayment;
        } catch (PayPalRESTException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}

