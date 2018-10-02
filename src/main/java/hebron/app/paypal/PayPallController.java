package hebron.app.paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayPallController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayPallController.class);

    private PayPalService payPalService;

    @Autowired
    public PayPallController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }


    @PostMapping("/api//paypal/createPayment")
    public ResponseEntity createPayment(@RequestBody String totalAmount) {
        Payment payment = payPalService.createPayment(totalAmount);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/api/paypal/execute")
    public ResponseEntity executePayment(@RequestBody PaymentDto paymentDto) {
        LOGGER.debug("Execute Payment:[" + paymentDto + "]");
        Payment executePayment = payPalService.executePaymentAfterApprove(paymentDto);
        if (executePayment.getState().equalsIgnoreCase("approved")) {
            Amount amount = executePayment.getTransactions().get(0).getAmount();
            String total = amount.getTotal();
            String currency = amount.getCurrency();
        }
        return ResponseEntity.ok(executePayment);
    }
}

