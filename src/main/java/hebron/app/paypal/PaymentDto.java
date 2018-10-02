package hebron.app.paypal;

import lombok.Data;

@Data
public class PaymentDto {

    private String paymentId;
    private String payerId;
    private String token;

}

