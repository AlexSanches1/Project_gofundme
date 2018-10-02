package hebron.app.paypal;

import com.paypal.base.rest.APIContext;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PayPalClientFactory {

    // Replace these values with your sandboxClientId and secret. You can use these to get started right now.
    //GO to: https://developer.paypal.com/developer/applications/edit/QVU1SHNWd19sbUcyVnR6ZWFIaHlqT2RpdzdjTHh5QkNBdDZPWVRuT0FDM19ScWcyNjVOcVdBOVQ5cFBtSnl4MkFVMndXQ0FjZlJseEpRdlA=
    private final String sandboxClientId = "AfeJgi9qyD8kxPwOeKBo0ctlQC-9MRK1ve8sHpdl1gjABGR4Cv9Hf8Gj5SRB5dO1CyH4FVw1sGN3ymax";
    private final String sandboxClientSecret = "EK7PB_4Rs2JQS05DQN0JRziR1_rgL8s8l8rRdtlFbMLGHH6VkuQ7XwWvPqwPw2MNpKZxF00LGrhMlGJF";

    private final String liveClientId = "AVQvMRAeyRKDiA92sEtzRrjG0NT4WwN5wKV4rX38YrNXNPj-mU1NI0Rfr_QG3gjwEwiHX5vf5Qtob7U0";
    private final String liveClientSecret = "EPMVJnEUAxTTrkZ7wK9scBCACYlGqXr9CcGq5R_LiRtBjWrKBiylxP0gntxb073P-_Qh38Y4SYxZprcx";

    private static final String SANDBOX_MODE = "sandbox";
    private static final String LIVE_MODE = "live";


    private String mode;

    @Autowired
    public PayPalClientFactory(@Value("${paypal.mode}") String mode) {
        this.mode = mode;
    }


    public APIContext createApiContext() {
        Assert.notNull(mode, "Error: PayPal mode is null.");
        APIContext apiContext = null;
        if (SANDBOX_MODE.equalsIgnoreCase(mode)) {
            apiContext = new APIContext(sandboxClientId, sandboxClientSecret, mode);
        } else if (LIVE_MODE.equalsIgnoreCase(mode)) {
            apiContext = new APIContext(liveClientId, liveClientSecret, mode);
        }
        return apiContext;
    }

}

